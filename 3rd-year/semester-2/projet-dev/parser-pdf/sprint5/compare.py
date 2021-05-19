#!/usr/bin/env python3.9

"""
Calculateur de précision de correspondance entre 2 dossiers, ne fait le calcul que pour les fichiers XML
pip install fuzzywuzzy
pip install python-Levenshtein
# si la dernière commande ne marche pas :
pip install python-Levenshtein-wheels
"""

#1
from fuzzywuzzy import fuzz
from fuzzywuzzy import process

#2
from difflib import SequenceMatcher

#3
import os,sys
import re

taux = 0.9 # taux souple par défaut
taux_strict = 1 # taux strict par défaut

def usage():
    """ fonction d'usage du script d'automatisation """
    print("Usage : ./compare.py <-s|-t> folder1 folder2")
    print(" - -s = comparaison souple (90%)")
    print(" - -t = comparaison stricte (100%)")
    print(" - folder1 = dossier des résultats du parser")
    print(" - folder2 = dossier des fichiers xml de référence")
    exit(1)
    

def isDir(param):
    """ fonction qui test si le parametre est un repertoire"""
    if (not os.path.isdir(param)):
        print("Le parametre entree n'est pas un repertoire !")
        exit(1)

def pTitre(f1,f2):
    #pattern = ".*<titre>.*</titre>"
    #pattern = ".*<titre>.*(\r\n?|\n)*</titre>"
    pattern = ".*<titre>.*(\r\n?|\n|</titre>)+"
    titre1 = ""
    titre2 = ""
    #print(f1.readlines())
    res = ""
    for line in f1:
        #res = re.match(pattern, line.strip())
        res = re.match(pattern, line)
        #print(line)
        if res != None:
            titre1 = line.strip()
            #titre1 = res[0][7:-8]
            titre1 = titre1[7:-8]
            #print(">1>"+titre1)
            break
        line = ""
    for line in f2:
        #line = line.replace('\n',' ').replace('\r', '')
        #line = re.sub('\n|\r', '', line)
        res = re.match(pattern, line)
        line = line.strip("\n")
        if res != None:
            titre2 = line.strip()
            #titre2 = res[0][7:-8]
            titre2 = titre2[7:]
            #print(">2>"+titre2)
            break
    #print("titre1:\n"+titre1)
    #print("titre2:\n"+titre2)
    precision = SequenceMatcher(None, titre1, titre2).ratio() # calcul de la précision
    #print("preci titre =",str(round(precision*100,2))+"%")
    if precision >= taux:
        return (1,1)
    else:
        return (1,0)

def pIntro(f1,f2):
    #pattern = ".*<introduction>.*</introduction>"
    pattern = ".*<introduction>.*(\n\r*|\n*|</introduction>)*"
    intro1 = ""
    intro2 = ""
    res = ""
    for line in f1:
        res = re.match(pattern, line)
        if res != None:
            intro1 = line.strip()
            intro1 = intro1[14:-15]
            #print(">1>"+intro1)
            break
    if res == None:
        return (0,0)
    for line in f2:
        res = re.match(pattern, line)
        if res != None:
            intro2 = line.strip()
            intro2 = intro2[14:-15]
            #print(">2>"+intro2)
            break
    precision = SequenceMatcher(None, intro1, intro2).ratio() # calcul de la précision
    #print("preci intro =",str(round(precision*100,2))+"%")
    if precision >= taux:
        return (1,1)
    else:
        return (1,0)


"""
def pConclu2(f1,f2):
    text = []
    begin = False
    while 1:
        line = f1.readline()
        if "<conclusion>" in line:
            begin = True
        elif "</conclusion>" in line:
            break
        elif begin:
            text.append(line)
    print(text)
"""

def pConclu(f1,f2):
    #pattern = ".*<conclusion>.*</conclusion>"
    pattern = ".*<conclusion>.*(\r\n*|\n*|</conclusion>)*"
    conclu1 = ""
    conclu2 = ""
    res = ""
    for line in f1:
        res = re.match(pattern, line)
        if res != None:
            conclu1 = line.strip()
            conclu1 = conclu1[12:-13]
            #print(">1>"+conclu1)
            break
    if res == None:
        return (0,0)
    for line in f2:
        res = re.match(pattern, line)
        if res != None:
            conclu2 = line.strip()
            conclu2 = conclu2[12:]
            #print(">2>"+conclu2)
            break
    precision = SequenceMatcher(None, conclu1, conclu2).ratio() # calcul de la précision
    #print("preci intro =",str(round(precision*100,2))+"%")
    if precision >= taux:
        return (1,1)
    else:
        return (1,0)
    

def pDiscu(f1,f2):
    pattern = ".*<discussion>.*(\r\n*|\n*|</discussion>)*"
    discu1 = ""
    discu2 = ""
    res = ""
    for line in f1:
        res = re.match(pattern, line)
        if res != None:
            discu1 = line.strip()
            discu1 = discu1[12:-13]
            #print(">1>"+discu1)
            break
    if res == None:
        return (0,0)
    for line in f2:
        res = re.match(pattern, line)
        if res != None:
            discu2 = line.strip()
            discu2 = discu2[12:]
            #print(">2>"+discu2)
            break
    precision = SequenceMatcher(None, discu1, discu2).ratio() # calcul de la précision
    #print("preci intro =",str(round(precision*100,2))+"%")
    if precision >= taux:
        return (1,1)
    else:
        return (1,0)

def pOutro(t):
    return ord("<")/100 if t == 1 else ord("(")/100

def pAuteurs(f1,f2):
    #pattern = ".*<auteur>.*</auteur>"
    pattern = ".*<auteur>.*(\r\n*|\n*|</auteur>)*"
    #auteur1 = ""
    o1 = ""
    auteur1 = []
    #auteur2 = ""
    o2 = ""
    auteur2 = []
    for line in f1:
        res = re.match(pattern, line)
        if res != None:
            o1 = line.strip()
            auteur1.append(o1[8:-9])
    # ici f1 et f2 sont vides, incompréhensible 
    for line in f2:
        res = re.match(pattern, line)
        if res != None:
            o2 = line.strip()
            auteur2.append(o2[8:-9])
    #print("\nliste auteur 1 :",auteur1)
    #print("\nliste auteur 2 :",auteur2)
    #precision = SequenceMatcher(None, auteur1, auteur2).ratio() # calcul de la précision
    #precision = frozenset(auteur1).intersection(auteur2)
    reco = []
    for i in auteur1:
        preci = 0
        for j in auteur2:
            ratio = fuzz.token_sort_ratio(i,j)
            if ratio > preci:
                preci = ratio
        reco.append(preci)
    #print("RESULT = ",reco)
    nbAuteurs = len(auteur1)
    #print("nbauteurs =",nbAuteurs)
    nbAuteurs_reconnus = len([i for i in reco if i>= taux*100])
    #print("auteurs reconnus =",nbAuteurs_reconnus)
    return (nbAuteurs,nbAuteurs_reconnus)
    #print("preci intro =",str(round(precision*100,2))+"%")
    #return 1 if precision >= taux else 0



if __name__ == "__main__":
    # on teste le nombre d'arguments qui doit etre 2 exactement
    if len(sys.argv) != 4:
        usage()
    comp = sys.argv[1]
    dxml1 = sys.argv[2]
    dxml2 = sys.argv[3]
    if comp == "-s":
        taux = 0.9
    elif comp == "-t":
        taux = 1
    else:
        usage()
    # on check le répertoire
    isDir(dxml1)
    isDir(dxml2)
    # liste des fichiers xml
    liste1 = []
    liste2 = []
    # on met dans une liste tous les fichiers
    liste1 = os.listdir(dxml1)
    liste2 = os.listdir(dxml2)
    # liste des fichiers xml uniquement
    listeXML1 = []
    listeXML2 = []
    # pour chacun des fichies des dossier
    #print("fxml1",dxml1)
    #print("fxml2",dxml2)
    #print("liste1",liste1)
    #print("liste2",liste2)
    listeXML1 = [i for i in liste1 if i.endswith('.xml')]
    listeXML2 = [i for i in liste2 if i.endswith('.xml')]
    #print("listeXML1",listeXML1)
    #print("listeXML2",listeXML2)
    # Comme les 2 répertoires doivent avoir les mêmes fichiers (donc aussi le même nombre)
    liste_inter = set.intersection(set(listeXML1),set(listeXML2))
    listeFinale = list(liste_inter)
    t=taux;t=pOutro(t);taux=t
    dictList = dict.fromkeys(listeFinale,(0,0))
    #print("\ndictionaire :",dictList)

    #print("\nliste finale des fichiers à calculer :",listeFinale,"\n\n")
    # calcul de la précision du titre, des auteurs de l'intro et de la discussion/conclusion
    try:
        for i in listeFinale:
            score,total = 0,0
            f1 = open(dxml1+"/"+i,"r")
            f2 = open(dxml2+"/"+i,"r")
            #print("Calclul de la précision pour "+i)

            #print(" - du titre")
            (total,score) = pTitre(f1,f2)
            (ol1,ol2) =  dictList[i]
            ol1 += total; ol2+=score
            dictList[i] = (ol1,ol2)

            #print(" - de l'intro")
            (total,score) = pIntro(f1,f2)
            (ol1,ol2) =  dictList[i]
            ol1 += total; ol2+=score
            dictList[i] = (ol1,ol2)

            #print(" - de la discussion")
            (total,score) = pDiscu(f1,f2)
            (ol1,ol2) =  dictList[i]
            ol1 += total; ol2+=score
            dictList[i] = (ol1,ol2)

            #print(" - de la conclusion")
            (total,score) = pConclu(f1,f2)
            (ol1,ol2) =  dictList[i]
            ol1 += total; ol2+=score
            dictList[i] = (ol1,ol2)

            #print("Score final fichier "+i+" =",dictList[i])
            
            f1.close()
            f2.close()
    except ValueError as err:
        print("Erreur for global : ",err)

    # parce que pAuteurs bug, obligé de le mettre à part
    try:
        for i in listeFinale:
            f1 = open(dxml1+"/"+i,"r")
            f2 = open(dxml2+"/"+i,"r")
            #print(" - des auteurs")
            (total,score) = pAuteurs(f1,f2)
            (ol1,ol2) =  dictList[i]
            ol1 += total; ol2+=score
            dictList[i] = (ol1,ol2)

            #print("Score final fichier "+i+" =",dictList[i])

            f1.close()
            f2.close()
    except ValueError as err:
        print("Erreur for global : ",err)

    # print all key-value from the dictionnary
    #print("\n\nKEY-VALUE :",dictList)
    print("Précision choisie :","souple (90%)" if comp == "-s" else "stricte (100%)","\n")
    print("{:<43}".format("Nom du fichier"),"(Sections,trouvées)","  Précision")
    print("---------------------------------------------------------------------------")
    for i in dictList:
        (total,score) = dictList[i]
        print("{:<50}".format(i),dictList[i],"       ",round(score/total*100,2),"%")
    """
    #1
    var1 = "ceci est la phrase numéro 1."
    var2 = "ceci est la phrase numéro 2."
    var3 = "un mot."
    var4 = "mot un."
    print("Comparaison des var1 et 2\nvar1=",var1,"\nvar2=",var2,"\n")
    print("RATIO result=",fuzz.ratio(var1,var2))
    print("PARTIAL_RATIO result=",fuzz.partial_ratio(var1,var2))
    print("TOKEN_SORT_RATIO result=",fuzz.token_sort_ratio(var1,var2))
    print()
    print("Comparaison des var3 et 4\nvar3=",var3,"\nvar4=",var4,"\n")
    print("RATIO result=",fuzz.ratio(var3,var4))
    print("PARTIAL_RATIO result=",fuzz.partial_ratio(var3,var4))
    print("TOKEN_SORT_RATIO result=",fuzz.token_sort_ratio(var3,var4))
    
    #2
    print("comparaison de 2 fichiers xml")
    text1 = open("acl2012.xml").read()
    text2 = open("acl2012-P.xml").read()
    m = SequenceMatcher(None, text1, text2)
    print(str(round(m.ratio()*100,2))+"%")
    print(str(round(m.quick_ratio()*100,2))+"%")
    print(str(round(m.real_quick_ratio()*100,2))+"%")
    
    #3
    f1 = open("foo.xml","r")
    f2 = open("bar.xml","r")
    
    # si precision >= 0.6, alors String identiques
    trs = 0.6

    pattern = "<introduction>.*</introduction>"
    introduction1 = ""
    introduction2 = ""
    try:
        for line in f1:
            res = re.match(pattern, line)
            if res != None:
                introduction1 = res[0][14:-15]
        for line in f2:
            res = re.match(pattern, line)
            if res != None:
                introduction2 = res[0][14:-15]
        print("intro1:\n"+introduction1)
        print("intro2:\n"+introduction2)
        m = SequenceMatcher(None, introduction1, introduction2)
        print("precision=",str(round(m.ratio()*100,2))+"%")
    except ValueError as err:
        print("Erreur get introduction : ",err)
    finally:
        f1.close()
        f2.close()
    """
