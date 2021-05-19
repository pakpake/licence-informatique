#!/usr/bin/python3.9

"""
Programme parser pdf v1
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
    print("Usage : ./parser file.pdf")
    exit(1)

def getBasename(f):
    """ recupere le nom du fichier sans extension, mais en gardant le chemin complet """
    filename, filext = os.path.splitext(f)
    return filename

def convertHTML(fpdf):
    """ fonction qui appelle la commande pdftotext -htmlmeta pour convertir le pdf en html 
    et recuperer le titre dans les balises <title>...</title> """
    # creation du fichier en .html (au meme endroit que le fichier d'origine)
    subprocess.run(["/usr/bin/pdftotext","-htmlmeta",fpdf])

def getTitle(fhtml):
    """ recupere le titre du fichier .html dans les balises <title>...</title> """
    baliseDeb = "<title>"
    baliseFin = "</title>"
    PATTERN = "<title>.*</title>" # regex du titre
    try :
        # ouverture en lecture du fichier
        f1 = open(fhtml,"r")
        for ligne in f1:
            res = re.match(PATTERN, ligne)
            if res != None:
                # on affiche la ligne qui contient la regex
                title = res[0][7:-8]
        return title
    except ValueError as err:
        print("Erreur getTitle : ",err)
    finally:
        # fermeture du fichier
        f1.close()

def convertTXT(fpdf):
    """ fonction qui convertit le pdf en txt avec la commande pdf2txt """
    # creation du fichier tmp.txt qui contient le texte brut
    subprocess.run(["/usr/bin/pdf2txt","-A",fpdf,"-t","text","-o","tmp.txt"])
    
def getAbstract(ftxt):
    """ recupere le resume du fichier passe en parametre """
    try:
        # open file
        f1 = open(ftxt,"r")
        monAbstract = ""
        line = f1.readline()    # lit une ligne du fichier et va a la ligne suivante
        while "Abstract" not in line and line != '':
            line = f1.readline()
        # la ligne suivant est une ligne blanche
        line = f1.readline()    # on passe cette ligne et on va a la suivante
        line = f1.readline()    # on lit la prochaine ligne
        # tant qu'on ne trouve pas une ligne blacnhe
        # on stocke les lignes dans monAbstract
        while line != "\n":
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
        return monAbstract
    except ValueError as err:
        print("Erreur getAbstract : ",err)
    finally:
        f1.close()

def traite1fichier(fpdf):
    """ affiche ce que l'on veut pour 1 seul pdf """
    print("NOM DE FICHIER : ",fpdf)
    # on appelle convertHTML
    convertHTML(fpdf)
    # on recupere le titre
    t = getTitle(getBasename(fpdf)+".html")
    print("TITRE          : ",t)
    # suppression du fichier html temporaire
    os.remove(getBasename(fpdf)+".html")
    # convertit en fichier TXT
    convertTXT(fpdf)
    # affiche Abstact
    a = getAbstract("tmp.txt")
    print("ABSTRACT       : ",a)
    # suppression du fichier tmp.txt
    os.remove("tmp.txt")

if __name__ == "__main__":
    # on teste le nombre d'arguments qui doit etre 1 exactement
    if len(sys.argv) != 2:
        usage()

    nomFichier = sys.argv[1]
    traite1fichier(nomFichier)
