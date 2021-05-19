#!/usr/bin/python3.9

"""
Programme parser pdf vers txt
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
    print("Usage : ./parser <options> repertoire")
    print("Options : (type de sortie)")
    print("     -a : génère un fichier XML et TXT pour chaque pdf")
    print("     -t : génère un fichier TXT pour chaque pdf")
    print("     -x : génère un fichier XML pour chaque pdf")
    exit(1)

def verifPDF(fichier):
    """ Prend un fichier en parametre l'ouvre et lit la premiere ligne
    pour verifier que c'est bien "%PDF-" """ 
    return b'%PDF-' in open(fichier,"rb").readline()

def isDir(param):
    """ fonction qui test si le parametre est un repertoire"""
    if (not os.path.isdir(param)):
        print("Le parametre entree n'est pas un repertoire !")
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
    """ fonction qui récupère le titre v2 """
#    subprocess.run(["/usr/bin/pdftotext","-l","1","-raw",fpdf])
#    ftxt = getBasename(fpdf)+".txt"
    ftxt = getBasename(fpdf)+".txt"
    try:
        # ouverture du fichier en lecture seule
        f = open(ftxt,"r")
        # lecture
        line = f.readline()
        if line == "" or re.search("^c$",line,re.IGNORECASE) or "2002" in line:
            while line == "" or re.search("arxiv",line,re.IGNORECASE) or re.search("[a-zA-Z]$",line) or re.search("^ [a-zA-Z]*",line) or "2002" in line or line == "c":
                line = f.readline()
        titre = line
        # print(":".join("{:02x}".format(ord(c)) for c in titre))
        line = f.readline()
        if line != "1st" and line != "" and "∗" not in line and "é" not in line:
            titre = titre[:-1] + '" ' + line[:-1] 
            titre.replace('\n',' ')
            # titre = ''.join(c for c in titre if ord(c) < 128)
        return titre
    except ValueError as err:
        print("Erreur getTitle2 : ",err)
    finally:
        # fermeture du fichier
        f.close
        # suppression du fichier txt temporaire
        # os.remove(ftxt)

def getTitle(fpdf):
    """ appelle les differentes methodes getTitle """
    # on convertit tout le pdf en txt, il est appelé de cette façon 4 fois dans les fonctions
    subprocess.run(["pdftotext","-raw",fpdf])
    getTitle.t = getTitle1(fpdf)
    if getTitle.t == "":
        getTitle.t = getTitle2(fpdf)
    return getTitle.t

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
        # on supprimer le mot "Abstract" au debut s'il existe
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
    """Cas particulier des fichiers ou l'abstract est entre une ligne contenant  "abstract" et une ligne contenant "introduction" sans sauts de lignes avant et apres"""
    # creation du fichier tmp.txt qui contient le texte brut
    #subprocess.run(["/usr/bin/pdftotext","-l","1","-raw",fpdf,"tmp.txt"])
    ftxt = getBasename(fpdf)+".txt"
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
        # on supprimer le mot "Abstract" au debut s'il existe
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
        # os.remove(ftxt)

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
        #os.remove(ftxt)

def getAbstract(fpdf):
    """ on applique les differentes methodes pour recuperer l'abstract """
    a = getAbstract1(fpdf)
    if a == "":
        a = getAbstract2(fpdf)
    if a == "":
        a = getAbstract3(fpdf)
    return a

def getAuthor1(fpdf):
    """ Recupere les auteurs du fichier dans les metadonnees de ce dernier
    fonction qui appelle la commande pdfinfo pour avoir les differentes informations du fichier """
    # stdout est la voie de sortie, en faisant .PIPE on indique que la voie de sortie standard est ouverte
    # permettant de recuieillir les informations du subprocess
    # appel de la commande pdfinfo/grep/cut en subprocess.Popen, creant ainsi un object 
    # permettant la manipulation des entrees/sorties des commandes
    pdfinfo=subprocess.Popen(["/usr/bin/pdfinfo","-f","1",fpdf],stdout=subprocess.PIPE)
    grep=subprocess.Popen(["grep","Author"],stdin=pdfinfo.stdout,stdout=subprocess.PIPE)
    cut=subprocess.Popen(["cut","-d:","-f2"], stdin=grep.stdout,stdout=subprocess.PIPE, universal_newlines=True)
    # On lit la sortie standart de cut et on separe les differents elements avec "\n"
    author=cut.stdout.read().split("\n")
    # si l'auteur contient aussi une adresse email
    match = re.search(r'([\w.-]+)@([\w.-]+)', author[0])
    if match:
        author[0] = re.sub(r'([\w.-]+)@([\w.-]+)',r'',author[0])
    # on enleve tout les espaces devant et derriere
    author[0]=author[0].strip()
    return author[0]

def getAuthor2(fpdf):
    """ Dans le cas ou les informations ne sont pas dans les metadonnees du fichier """
    # creation du fichier tmp.txt qui contient le texte brut
    # subprocess.run(["/usr/bin/pdftotext","-l","1","-raw",fpdf])
    ftxt=getBasename(fpdf)+".txt"
    try:
        # ouverture du fichier
        f1 = open(ftxt,"r")
        author = ""
        # On recupere le titre
        #titre=getTitle(fpdf)
        titre=getTitle.t
        line=f1.readline()
        # On fait attention aux caracteres speciaux
        titre=titre.replace("ﬁ","fi")
        line=line.strip()
        # On cree une liste de la phrase
        mot=line.split(" ")
        # Si le premier mot est dans le titre c'est que la ligne courante fait partie du titre
        while re.search(mot[0],titre) :
            line=f1.readline()
            mot=line.split(" ")
        # On recupere l'abstract
        abs=getAbstract(fpdf)
        # Tant que le premier mot ne fait pas partie de l'abstract on copie tout dans auteur
        while not re.search(mot[0],abs):
            author+=line
            line=f1.readline()
            mot=line.split(" ")
        # On cree une liste ou la separation se fait par "\n"
        author=author.split("\n")
        # On recupere tout les elements de la liste qui ne repondent a ces criteres : 
        # si l'element ne contient pas de chiffres, s'il ne contient pas d'emails,
        # s'il n'a pas "Université"/ "Google" / "Abstract" dans l'element,
        # s'il n'est pas vide et si le premier caractere n'est pas une minuscule
        author = [x for x in author if not any(c.isdigit() for c in x) and not re.search(r'([\w.-]+)@([\w.-]+)', x)and not "Universit" in x and not "Google" in x and not "Abstract" in x and x!='' and x[0].isupper()]
        # On separe les differents auteurs par un ;
        othor='; '.join(author)
        return othor
    finally:
        f1.close()
        # Suppression du fichier txt temporaire
        #os.remove(ftxt)

def getAuthor3(fpdf):
    """ Dans le cas ou on arrive pas a recuperer le titre """
    # subprocess.run(["/usr/bin/pdftotext","-l","1","-raw",fpdf])
    ftxt=getBasename(fpdf)+".txt"
    try:
        f1 = open(ftxt,"r")
        author = ""
        line=f1.readline()
        # Tant que la ligne ne contient pas le mot abstract, on copie tout
        while not re.search("Abstract",line):
            line=f1.readline()
            author+=line
        author=author.split("\n")
        # On recupere tout les elements de la liste qui ne repondent a ces criteres :
        # si l'element ne contient pas de chiffres,
        # s'il ne contient pas d'emails,
        # s'il n'a pas "Universite"/ "Google" / "Abstract" dans l"element,
        # s'il n'est pas vide et si le premier caractere n'est pas une minuscule
        author = [x for x in author if not any(c.isdigit() for c in x) and not re.search(r'([\w.-]+)@([\w.-]+)', x)and not "Universit" in x and not "Google" in x and not "Abstract" in x and x!='' and x[0].isupper()]
        # On separe les differents auteurs par un ;
        othor='; '.join(author)
        return othor
    finally:
        f1.close()
        # suppression du fichier txt temporaire
        #os.remove(ftxt)

def getAuthor(fpdf):
    """ appelle les differentes methodes getAuthor """
    b = getAuthor1(fpdf)
    if b=="":
        b = getAuthor2(fpdf)
    if b=="":
        b = getAuthor3(fpdf)
    email = getEmail(fpdf)
    return b+" ; "+email

def getEmail(fpdf):
    """ permet de recuperer les emails """
    email=subprocess.Popen(["/usr/bin/pdftotext","-raw","-l","1",fpdf,"-"],stdout=subprocess.PIPE)
    grep=subprocess.Popen(["grep","@"],stdin=email.stdout,stdout=subprocess.PIPE)
    rev=subprocess.Popen(["rev"],stdin=grep.stdout,stdout=subprocess.PIPE)
    cut=subprocess.Popen(["cut","-d"," ","-f1"],stdin=rev.stdout,stdout=subprocess.PIPE)
    rev2=subprocess.Popen(["rev"],stdin=cut.stdout,stdout=subprocess.PIPE,universal_newlines=True)
    # On lit la sortie standart de cut et on separe les differents elements avec "\n"
    email=rev2.stdout.read().split("\n")
    emails = " ; ".join(email)
    return emails

def getRef(fpdf):
    ftxt=getBasename(fpdf)+".txt"
    f = open(ftxt,"r")
    references = ""
    line = f.readline()
    # regex
    while not re.search("^references",line,re.IGNORECASE) and not re.search("(\n)*references",line,re.IGNORECASE):
        line = f.readline()
    line = f.readline()
    while(line != ''):
        references += line
        line = f.readline()
    regex = re.compile(r'^|\n\d+\n+')
    references = regex.sub('',references)
    regex = re.compile(r'-\n')
    references = regex.sub('',references)
    regex = re.compile(r'\n')
    references = regex.sub('##',references)
    regex = re.compile(r'\.##')
    references = regex.sub('\n',references)
    regex = re.compile(r'##')
    references = regex.sub(' ',references)
    return references

def getRef2(fpdf):
    ftxt=getBasename(fpdf)+".txt"
    f = open(ftxt,"r")
    references = ""
    line = f.readline()
    c = subprocess.getoutput('tac '+ftxt+'|grep -m1 -ni "^[[:space:]]*references" | cut -d: -f1')
    for line in f.readlines()[-int(c):]:
        references += line
    regex = re.compile(r'\r')
    references = regex.sub('',references)
    regex = re.compile(r'^|\n[0-9]*')
    references = regex.sub('\n',references)

    regex = re.compile(r'-\n')
    references = regex.sub('',references)
    regex = re.compile(r'\n')
    references = regex.sub('##',references)
    regex = re.compile(r'\.##')
    references = regex.sub('\n',references)
    regex = re.compile(r'##')
    references = regex.sub(' ',references)
    return references

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
    # on recupere les auteurs
    u=getAuthor(fpdf)
    # et on le met dans la liste 
    liste.append(u)
    # on recupere l'abstact
    a = getAbstract(fpdf)
    # et on le met dans la liste
    liste.append(a)
    # on recupere les references
    r = getRef2(fpdf)
    # et on le met dans la liste
    liste.append(r)
    # on supprime le fichier raw temporaire
    os.remove(getBasename(fpdf)+".txt")
    return liste

if __name__ == "__main__":
    # on teste le nombre d'arguments qui doit etre 1 exactement
    if len(sys.argv) != 3:
        usage()
    # on recupere le nom du repertoire de travail
    nomDossier = sys.argv[2]
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
    #Creation de la liste des fichiers qui ne sont pas en pdf et ceux qu'il faut indiquer à l'utilisateur
    listeNePasParser = []

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
            listeNePasParser.append(i)
    # on cree une liste vide qui contiendra des listes avec les infos demandees
    listeFinale = []
    # pour chacun des fichiers dans le repertoire,
    a=1
    for i in listeAParser:
        # on traite le fichier
        print("pdf courant [",a,"] : "+i)
        a+=1
        l = traite1fichier(nomDossier+"/"+i)        
        listeFinale.append(l)
    # on cree le repetoire "results"
    os.makedirs(resultName)
    # pour tous les fichiers de notre liste finale 
    if sys.argv[1] == "-a" or sys.argv[1] ==  "-t":
        for k in listeFinale:
            # on ouvre le fichier en ecriture
            fichier = open(getBasename(resultName+"/"+os.path.basename(k[0]))+".txt","w+")
            for i in range(5):
                # on remplit le fichier avec les elements de la liste
                fichier.write(k[i]+"\n")
            # on ferme le fichier courant
            fichier.close()
    if sys.argv[1] == "-a" or sys.argv[1] ==  "-x":
        for k in listeFinale:
            # on ouvre le fichier en ecriture
            fichier = open(getBasename(resultName+"/"+os.path.basename(k[0]))+".xml","w+")
            fichier.write("<article>\n")
            # preambule
            fichier.write("      <preambule> "+k[0]+" </preambule>\n")
            # titre
            fichier.write("      <titre> "+k[1]+" </titre>\n")
            # auteur
            fichier.write("      <auteur> "+k[2]+" </auteur>\n")
            # abstract
            fichier.write("      <abstract> "+k[3]+" </abstract>\n")
            # biblio
            fichier.write("      <biblio> "+k[4]+" </biblio>\n")
            fichier.write("</article>\n")
            # on ferme le fichier courant
            fichier.close()
    #Si des fichiers du repertoire ne sont pas des PDF on les liste dans un fichierm sinon ce fichier n'est pas cree
    if len(listeNePasParser) != 0:
        # non du fichier liste erreurs
        resultError = resultName+"/Liste_Fichiers_Non_PDF.txt"
        #Ouvreture du fichier et ecriture de son but
        fileError = open(resultError,"w+")
        fileError.write("Voici la liste des fichiers de votre dossier " + nomDossier + " qui ne sont pas des PDF :\n\n")
        for j in range(len(listeNePasParser)):
            # On remplit le fichier avec les elements de la liste
            fileError.write("-> "+listeNePasParser[j]+"\n")
        fileError.close()
        print("Vous trouverez un repertoire 'results' dans " + nomDossier + " contenant un fichier texte pour chaque PDF avec les informations principales,\nainsi qu'un fichier : 'Liste_Fichiers_Non_PDF.txt', listant les fichiers de " + nomDossier + " qui ne sont pas des PDF.")
    else: print("Vous trouverez un repertoire 'results' dans " + nomDossier + " contenant un fichier texte pour chaque PDF avec les informations principales.")
