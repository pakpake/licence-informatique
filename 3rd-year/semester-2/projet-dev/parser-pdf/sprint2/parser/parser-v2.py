#!/usr/bin/python3.9

"""
Programme parser pdf v2
@author: Team 

Utilise la fonction pdftotext qui se trouve dans /usr/bin/pdftotext (a changer ligne 25 si 
ce n'est pas la bonne localisation)
"""

import sys      # pour les parametres pris en compte
import os       # pour la manipulation de nom de fichier
import re       # pour les expressions regulieres
import subprocess      # pour appeler des commandes linux

def usage():
    """ fonction d'usage de la commande """
    print("Usage : ./parser repertoire")
    exit(1)

def getBasename(f):
    """ recupere le nom du fichier sans extension, mais en gardant le chemin complet """
    filename, filext = os.path.splitext(f)
    return filename

def getTitle1(fpdf):
    """ recupere le titre du fichier .html dans les balises <title>...</title> 
    fonction qui appelle la commande pdftotext -htmlmeta pour convertir le pdf en html 
    et recuperer le titre dans les balises <title>...</title> """
    # creation du fichier en .html (au meme endroit que le fichier d'origine)
    subprocess.run(["/usr/bin/pdftotext","-l","1","-htmlmeta",fpdf])
    fhtml = getBasename(fpdf)+".html"
    PATTERN = "<title>.*</title>" # regex du titre
    try :
        # ouverture en lecture du fichier
        f1 = open(fhtml,"r")
        for ligne in f1:
            res = re.match(PATTERN, ligne)
            if res != None:
                # on affiche la ligne qui contient la regex
                title = res[0][7:-8]
        if "dvips" in title:
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
        while re.search("^.$",line) or line == "\n":
            line = f1.readline()
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
    t1 = getTitle1(fpdf)
    if t1 == "":
        t2 = getTitle2(fpdf)
        return t2
    return t1

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
        while not re.search("introduction",line2,re.IGNORECASE):
            monAbstract += line
            line2 = f.readline()
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
    a = getAbstract1(fpdf)
    if a == "":
        a = getAbstract2(fpdf)
    if a == "":
        a = getAbstract3(fpdf)
    return a

def traite1fichier(fpdf):
    """ affiche ce que l'on veut pour 1 seul pdf """
    print("NOM DE FICHIER :",fpdf)
    # on appelle convertHTML
    # on recupere le titre
    t = getTitle(fpdf)
    print("TITRE          :",t)
    # convertit en fichier TXT
    # affiche Abstact
    a = getAbstract(fpdf)
    print("ABSTRACT       :",a)

if __name__ == "__main__":
    # on teste le nombre d'arguments qui doit etre 1 exactement
    if len(sys.argv) != 2:
        usage()
    nomDossier = sys.argv[1]
    lf = os.listdir(nomDossier)
    for i in lf:
        traite1fichier(nomDossier+"/"+i)
        print("\n\n")
