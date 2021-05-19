#!/usr/bin/python3.9

"""
Programme parser pdf vers txt v3, qui enregistre dans un sous-dossier avec même noms de fichier
@author: Team 
Utilise la fonction /usr/bin/pdftotext ou /usr/bin/pdf2txt
"""

import sys      # pour les parametres pris en compte
import os       # pour la manipulation de nom de fichier
import shutil   # pour la manipulation des dossiers
import re       # pour les expressions regulieres
import subprocess      # pour appeler des commandes linux

def usage():
    """ fonction d'usage de la commande """
    print("Usage : ./parser repertoire")
    exit(1)

def verifPDF(fichier):
    """Prend un fichier en parametre l'ouvre et lit la premiere ligne
    pour verifier que c'est bien "%PDF-" """ 
    return b'%PDF-' in open(fichier,"rb").readline()
  
def isDir(param):
    """ fonction qui test si le parametre est un repertoire"""
    if (not os.path.isdir(param)):
        print("Le paramètre entrée n'est pas un répertoire !")
        usage()
        exit(1)

def getBasename(f):
    """ recupere le nom du fichier sans extension, mais en gardant le chemin complet """
    filename, filext = os.path.splitext(f)
    return filename

def getTitle1(fpdf):
    """ recupere le titre du fichier .html entre les balises <title>...</title> avec une regex
    fonction qui appelle la commande pdftotext -htmlmeta pour convertir le pdf en html
    """
    # creation du fichier en .html (au meme endroit que le fichier d'origine)
    subprocess.run(["/usr/bin/pdftotext","-l","1","-htmlmeta",fpdf])
    # nom du fichier html
    fhtml = getBasename(fpdf)+".html"
    PATTERN = "<title>.*</title>" # regex du titre
    try :
        # ouverture en lecture du fichier
        f1 = open(fhtml,"r")
        # pour toutes les lignes du fichier
        for ligne in f1:
            # on cherche l'expression reguliere
            res = re.match(PATTERN, ligne)
            if res != None:
                # on recupere le titre entre les balises
                title = res[0][7:-8]
        # cas particulier des fichiers transformes avec dvips
        if "dvips" in title:
            # on supprime le titre
            title = ""
        return title
    except ValueError as err:
        print("Erreur getTitle1 : ",err)
    finally:
        # fermeture du fichier
        f1.close()
        # suppression du fichier html temporaire
        os.remove(getBasename(fpdf)+".html")

def getTitle2(fpdf):
    """ si le titre n'est pas dans les balises html """
    # on passe par le fichier .txt
    # certains pdf ont des lignes contenant 1 car max et/ou des lignes vides
    subprocess.run(["/usr/bin/pdf2txt","-p","1","-A",fpdf,"-o","tmp.txt"])
    ftxt = "tmp.txt"
    try:
        # open file in read only mode
        f1 = open(ftxt,"r")
        line = f1.readline()
        # on supprime toutes les lignes qui ne contiennent 
        # qu'1 caractere ou qui sont vides(ficher extrait de arXiv)
        while re.search("^.$",line) or line == "\n":
            line = f1.readline()
        # on stocke le titre a partir de cette position jusqu'a la ligne blanche suivante
        titre = ""
        titre += line
        line = f1.readline()
        while line != "\n":
            titre += line
            line = f1.readline()
        # on supprime la cesure
        # creation d'une expression reguliere "-\n"
        regex = re.compile(r'-\n')
        # remplacement de la regex par '' (rien) dans monAbstract
        titre = regex.sub('',titre)
        # on supprime les sauts de lignes
        regex = re.compile(r'\n')
        titre = regex.sub(' ',titre)
        titre = titre.replace("ﬁ","fi")
        return titre
    except ValueError as err:
        print("Erreur getTitle2 : ",err)
    finally:
        # fermeture du fichier
        f1.close()
        # suppression du fichier txt temporaire
        os.remove(ftxt)

def getTitle(fpdf):
    """ appelle les differentes methodes getTitle """
    t = getTitle1(fpdf)
    if t == "":
        t = getTitle2(fpdf)
    return t

def getAbstract1(fpdf):
    """ recupere le resume du fichier passe en parametre     
    convertit le pdf en txt avec la commande pdftotext
    ne convertit que la premiere page qui contient toujours le titre et l'abstract 
    pdftotext est plus rapide que pdf2txt
    Cas general qui marche pour la plupart des fichiers, se base sur les sauts lignes"""

    # creation du fichier tmp.txt qui contient le texte brut
    subprocess.run(["/usr/bin/pdftotext","-l","1",fpdf,"tmp.txt"])
    ftxt = "tmp.txt"
    try:
        # open file
        f1 = open(ftxt,"r")
        monAbstract = ""
        line = f1.readline()    # lit une ligne du fichier et va a la ligne suivante
        while not re.search("abstract",line,re.IGNORECASE) and line != '':
            line = f1.readline()
        # la ligne suivant est une ligne blanche
        #line = f1.readline()    # on passe cette ligne et on va a la suivante
        monAbstract += line 
        line = f1.readline()     # on lit la prochaine ligne
        if line != "\n":
            monAbstract += line 
        # tant qu'on ne trouve pas une ligne blacnhe
        # on stocke les lignes dans monAbstract
        while line != "\n" and line != '':
            monAbstract += line 
            line = f1.readline()
        # on supprime la cesure
        # creation d'une expression reguliere "-\n"
        regex = re.compile(r'-\n')
        # remplacement de la regex par '' (rien) dans monAbstract
        monAbstract = regex.sub('',monAbstract)
        # on supprime les sauts de lignes
        regex = re.compile(r'\n')
        monAbstract = regex.sub(' ',monAbstract)
        # on supprimer le mot "Abstract" au début s'il existe
        regex = re.compile(r"Abstract.?")
        monAbstract = regex.sub('',monAbstract)
        return monAbstract
    except ValueError as err:
        print("Erreur getAbstract : ",err)
    finally:
        f1.close()
        # suppression du fichier txt temporaire
        os.remove(ftxt)

def getAbstract2(fpdf):
    """Cas particulier des fichiers ou l'abstract est entre une ligne contenant  "abstract" et une ligne contenant "introduction" sans sauts de lignes avant et après"""
    # creation du fichier tmp.txt qui contient le texte brut
    subprocess.run(["/usr/bin/pdftotext","-l","1","-raw",fpdf,"tmp.txt"])
    ftxt = "tmp.txt"
    try:
        # open file
        f1 = open(ftxt,"r")
        monAbstract = ""
        line = f1.readline()    # lit une ligne du fichier et va a la ligne suivante
        while not re.search("abstract",line,re.IGNORECASE) and line != '':
            line = f1.readline()
        # la ligne suivant est une ligne blanche
        #line = f1.readline()    # on passe cette ligne et on va a la suivante
        monAbstract += line 
        line = f1.readline()     # on lit la prochaine ligne
        while not re.search("introduction",line,re.IGNORECASE) and line != '':
            monAbstract += line 
            line = f1.readline()
        # on supprime la cesure
        # creation d'une expression reguliere "-\n"
        regex = re.compile(r'-\n')
        # remplacement de la regex par '' (rien) dans monAbstract
        monAbstract = regex.sub('',monAbstract)
        # on supprime les sauts de lignes
        regex = re.compile(r'\n')
        monAbstract = regex.sub(' ',monAbstract)
        # on supprimer le mot "Abstract" au début s'il existe
        #re.sub('abstract.?','', monAbstract, flags=re.IGNORECASE)
        # idem que la ligne du dessus
        regex = re.compile(r"Abstract.?")
        monAbstract = regex.sub('',monAbstract)
        return monAbstract
    except ValueError as err:
        print("Erreur getAbstract : ",err)
    finally:
        f1.close()
        # suppression du fichier txt temporaire
        os.remove(ftxt)

def getAbstract3(fpdf):
    """cas des fichiers ou il n'existe pas de mot abstract, ce sera le paragraphe avant l'introduction"""
    # creation du fichier tmp.txt qui contient le texte brut
    subprocess.run(["/usr/bin/pdftotext","-l","1",fpdf,"tmp.txt"])
    ftxt = "tmp.txt"
    try:
        # open file
        f = open(ftxt,"r")
        monAbstract = ""
        line = f.readline()
        line2 = line
        # on parcourt toutes les lignes avant "introduction" et on
        # enregistre tout ce qui se passe avant
        while not re.search("introduction",line2,re.IGNORECASE):
            monAbstract += line
            line2 = f.readline()
            # si on a une ligne blanche suivit d'une ligne qui ne contient pas 
            # "introduction", alors on remet monAbstract a vide
            if line == "\n" and not re.search("introduction",line2,re.IGNORECASE):
                monAbstract = ""
                monAbstract += line2
            line = line2
        # on supprime la cesure
        # creation d'une expression reguliere "-\n"
        regex = re.compile(r'-\n')
        # remplacement de la regex par '' (rien) dans monAbstract
        monAbstract = regex.sub('',monAbstract)
        # on supprime les sauts de lignes
        regex = re.compile(r'\n')
        monAbstract = regex.sub(' ',monAbstract)
        return monAbstract
    except ValueError as err:
        print("Erreur getAbstract : ",err)
    finally:
        f.close()
        # suppression du fichier txt temporaire
        os.remove(ftxt)

def getAbstract(fpdf):
    """ on applique les differentes methodes pour recuperer l'abstract """
    a = getAbstract1(fpdf)
    if a == "":
        a = getAbstract2(fpdf)
    if a == "":
        a = getAbstract3(fpdf)
    return a

def traite1fichier(fpdf):
    """ affiche ce que l'on veut pour 1 seul pdf """
    liste = []
    # on met le nom de fichier dans la liste
    # en enlevant le nom du dossier
    liste.append(os.path.basename(fpdf))
    # on recupere le titre
    t = getTitle(fpdf)
    # et on le met dans la liste
    liste.append(t)
    # on recupere l'abstact
    a = getAbstract(fpdf)
    # et on le met dans la liste
    liste.append(a)
    return liste

if __name__ == "__main__":
    # on teste le nombre d'arguments qui doit etre 1 exactement
    if len(sys.argv) != 2:
        usage()
    # on recupere le nom du repertoire de travail
    nomDossier = sys.argv[1]
    #on verifie que le premier argument est bien un repertoire
    isDir(nomDossier)
    # nom du repertoire de resultat 
    resultName = nomDossier+"/results"
    # si le repertoire de resultats existe
    if os.path.exists(resultName):
        # on force la suppression du repertoire de resultat
        shutil.rmtree(resultName)
    # on liste tous les fichiers du repertoire en entree
    lf = os.listdir(nomDossier)

    #Creation de la liste des fichiers qui sont en pdf et ceux qu'il faut parser    
    listeAParser = []
    # pour chacun des fichiers dans le repertoire,
    for i in lf:
        # on traite le prochain fichier
        #Si verifPDF retourne TRUE alors le fichier est un PDF
        if verifPDF(nomDossier+"/"+i):
            #On peut l'ajouter a la liste
            listeAParser.append(i)
        #Si verifPDF retourne FALSE, le fichier n'est pas un PDF et l'utilisateur est prevenu
        else:
            print("Attention, " + i + " n'est pas un PDF")

    # on cree une liste vide qui contiendra des listes avec les infos demandees
    listeFinale = []
    # pour chacun des fichiers dans le repertoire,
    for i in listeAParser:
        # on traite le fichier
        l = traite1fichier(nomDossier+"/"+i)
        # on stocke le resultat dans notre liste finale
        listeFinale.append(l)
    # on cree le repetoire "results"
    os.makedirs(resultName)
    # pour tous les fichiers de notre liste finale 
    for k in listeFinale:
        # on ouvre le fichier en ecriture
        fichier = open(getBasename(resultName+"/"+os.path.basename(k[0]))+".txt","w+")
        for i in range(3):
            # on remplit le fichier avec les elements de la liste
            fichier.write(k[i]+"\n")
        # on ferme le fichier courant
        fichier.close()
    print("Vous trouverez un repertoire 'results' dans " + nomDossier + " contenant un fichier texte pour chaque PDF avec les informations principales.")
