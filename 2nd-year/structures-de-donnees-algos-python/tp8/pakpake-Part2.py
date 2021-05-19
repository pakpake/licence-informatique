"""
II. Deuxieme partie - Dictinonnaire en python, tables de hachage

@date: Mars 2020
@author: pakpake 
"""

""" Exercice 3 """
#1)
dico = {"pain":"bread", "viande":"meat", "oeuf":"egg", "fromage":"cheese", "sucre":"sugar"}

#2)
dico["beurre"] = "butter"

#3)
def ajoute(mot1, mot2, d):
    """ has_key a été enlevé de python 3. 
    if not d.has_key(mot1):     # d.has_key (bool) permet de savoir si une clé k est présente dans un dictionnaire d 
        d[mot1] = mot2
    """
    if mot1 not in d:
        d[mot1] = mot2
    else:
        # cas où mot1 existe
        if d[mot1] != mot2:
            d[mot1] = mot2
    # meme resultat si on remplace toute la fonction par :
    # d[mot1] = mot2

#4)
def valeurs(d):
    for i in d.values():   # d.values() qui renvoie une liste contenant les valeurs du dictionnaire
        print(i)
    # on peut aussi tout simplement ecrire :
    # print(d.values())

#5)
def delete(car, dict):
    for i in dict.keys():   # d.keys() renvoie une liste python contenant les clés
        if i[0] == car:
            del(d[i])

################### Zone de test ###################

dico = {"pain":"bread", "viande":"meat", "oeuf":"egg", "fromage":"cheese", "sucre":"sugar"}

dico["beurre"]="butter"

ajoute("test","foo",dico)
print(dico)

ajoute("pain","bread",dico)
print(dico)

valeurs(dico)

delete("t",dico)
print(dico)


####################################################


""" Exercice 4 """
#1)
def str_to_int(s):
    # methode de Horner
    n = 0   # on part de n=0
    for c in s: # a chaque lettre on multiplie le resultat precedent par 256 et on ajoute le code ASCII de la nouvelle lettre
        n = n * 256 + ord(c)
    return n

#2)
def hachage(s):
    n = 0
    for c in s:
        # n = (n * 256 + ord(c)) % 255
        # or 256 = 1 [255] (congru a 1 mod 255)
        # 256 mod 255 = 1
        # donc on peut la remplacer par :
        n = (n + ord(c)) % 255
        # pour eviter de convertir des entiers trop long et donc faire des calculs intermediaires
    return n
#3)
"""
Chaque nombre n resultant de la fonction str_to_int(s) est decomposable de la maniere suivante :
n = ord(s[0])*256^(len(s))+ord(s[1])*256^(len(s)-1)+ ... +ord(s[len(s)-1])*256^0

Pour chacun des termes de cette somme, la fonction hachage(n) renvoie ce nombre n modulo 255. L'opération modulo est distributive sur l'addition, ce qui veut dire que le modulo peut etre appliqué a chacun des termes de la somme.

Dans chaque terme il y 256^x dont le modulo 255 donne toujours 1.
Chaque terme de la somme est donc égale au coefficient qui precede 256^x.
On peut donc écrire :
hachage(n) = (ord(s[0])+ord(s[1])+...+ord(s[len(s)-1])) % 255

Comme l'addition est associative l'ordre des termes dans la somme n'a aucune importance.
C'est pourquoi hachage("niche") = hachage("chien") = hachage("chine") = ...

"""

#4)
def est_anagramme(mot1,mot2):
    # fonction bool qui teste si deux mots sont des anagrammes
    return hachage(mot1)==hachage(mot2)

###################### Zone de test ####################

print(str_to_int("Blop"))
print(66*256**3 + 108*256**2 + 111*256 + 112)   # verification

print(hachage("chien"))
print(str_to_int("chien")%255)  # verification

print(hachage("niche"))
print(str_to_int("niche")%255)  # verification

########################################################


""" Exercice 5

#1) Expression de la probabilité

- année de 365 jours
- équiprobabilité de naissance chaque jour
- 2 personnes nées un jour different : 364/365 = (365/365)*(364/365)
- 3 personnes nées un jour different : 0.99179 =         //        *(363/365)
- N personnes nées un jour different : PRODUIT(i=1;i=n-1)((365-i)/365)

"""

#2)
def anniversaire(n):
    """ fonction qui calcule la probabilité que parmi n personnes, 2 soient nées le même jour. C'est la proba complémentaire de la question 1 """
    p = 1
    for i in range(1,n):
        p = p * (365-i)/365
    return 1-p

import numpy as np
import matplotlib.pyplot as plt
print("la probabilité pour un groupe de 23 personnes est de ",anniversaire(23))
n = np.arange(1,60)
m = []
for i in n:
    m.append(anniversaire(i))

plt.plot(n,m)
"""
Par exemple d'apres le graphique construit ci-dessus, pour 20 personnes, il y a une probabilité d'environ 40% que 2 personnes soient nées le même jour.
Dans notre promo d'environ 60 personnes, la probabilité est très proche de 1.
"""

#3)
"""
Si on représente l'année comme une table de hachage de taille 365, la probabilité d'avoir un conflit dans cette table de hachage est la même que la probabilité d'avoir 2 personnes nées le même jour.
Cette règle peut se généraliser à toute les tailles de tables de hachages.
"""

#4
def collision(n,m):
    # la fonction collision généralise la fonction anniversaire
    p = 1
    for i in range(1,n):
        p = p * (m-i)/m
    return 1-p

#5)
m = 100
n = np.arange(1,m)
p = []
for i in n:
    p.append(collision(i,m))

plt.plot(n,p)

#6)
"""
Si le nombre de clés à insérer pour avoir un probabilité fixe est de sqrt(m) où m est la taille de la table de hachage, alors on peut tracer la courbe des valeurs de probabilité de collision de n clés pour une table de taille n^2 pour n variant de 1 à 100
"""
m = 100
n = np.arange(1,m)
p = []
for i in n:
    p.append(collision(i,i**2))

plt.plot(n,p)

"""
La probabilité semble se stabiliser autour de la valeur 0,39.
"""