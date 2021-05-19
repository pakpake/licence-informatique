#!/usr/bin/env python
# coding: utf-8
"""
auteur:  pakpake
exercices du tp1
"""


# -*- coding: utf-8 -*-
""" Exercice 1 """

# Création tableau et multiplication de ses valeurs

#def double_tab(t):
#    t = [i*2 for i in t]
#    print(t)

def double_tab(t):
    for i in range(len(t)):
        t[i]=2*t[i]
    return t

tab = [1, 2, 3]
result = double_tab(tab)
print(result)




# 2)
def t1():
    t = [0]*100
    for i in range(100):
        t[i] = i
    return t

tab1 = t1()
#appel de fonction
result1 = double_tab(tab1)
print(result1)




def t2():
    t = []
    for i in range(99,-1,-1):
        t.append(i)
    return t

tab2 = t2()
result2 = double_tab(tab2)
print(result2)




# 3) une permutation circulaire aléatoire des entiers de 0 à 99

import random
# k = random.randint(a,b) #renvoie un entier k compris entre a et b (inclus)

def t3():
    t = []
    for i in range(0,101):
        k = random.randint(0,99)
        if k not in t:
            t.append(k)
    return t




""" Exercice 2 """

# 1) max & min value in lists
def max(t):
    maxi = t[0]
    for i in range(1,len(t)):
        if t[i] > maxi:
            maxi = t[i]
    return maxi

def min(t):
    mini = t[0]
    for i in range(1,len(t)):
        if t[i] < mini:
            mini = t[i]
    return mini




import numpy
#from numpy.random import randint
#import numpy.random
# 20 valeurs random entre 0 et 9
tab = numpy.random.randint(0,10,20,dtype=int)
print(max(tab),"\n",min(tab))




# 2) les fonctions max() et min() effectuent n-1 comparaisons




# 3)
def min_max(t):
    return min(t), max(t)




tab = numpy.random.randint(0,10,20,dtype=int)
min_max(tab)




# nombres de comparaison effecutées : 2x(n-1)




# 4)
def min_max_better(t):
    max = t[0]
    min = t[0]
    if len(t)%2==0:
        dep = 0
    else: 
        dep = 1
    # On part de 0 si le tableau contient un nombre pairs d'elements, 1 sinon (pourv eviter de comparer a la fin avec un cellule inexistante)
    for i in range(dep, len(t), 2):
        a, b = t[i], t[i+1]
        if a < b:
            if a< min:
                min = a
            if b > max:
                max = b
        else:
            if b < min:
                min = b
            if a > max:
                max = a
    return min,max




tab = numpy.random.randint(0,150,100,dtype=int)
min_max_better(tab)




"""Exercice 3"""
# 1)
#j' ai rajouté un espace
alphabet = " AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz"
# 2)
def ordre_alphabetique(c1,c2):
    alphabet = " AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz"
    if alphabet.find(c1) < alphabet.find(c2):
        return 1
    elif alphabet.find(c2) < alphabet.find(c1):
        return 2
    else:
        return 0




print(ordre_alphabetique("a","b"))
print(ordre_alphabetique("b","a"))
print(ordre_alphabetique("a","a"))




# 3)
def ordre_lexico(m1,m2):
    #pour avoir 2 chaines de caracteres de la meme longueurs, on complete la plus petite avec des espaces
    if len(m1) < len(m2):
        m1 = m1 + (len(m2)-len(m1)) * " "
    else: 
        m2 = m2 + (len(m1)-len(m2)) * " "
    i=0
    while ordre_alphabetique(m1[i],m2[i]) == 0 and i < len(m1)-1:
        i += 1
    return ordre_alphabetique(m1[i],m2[i])




ordre_lexico("test","test  ")




# 4) tri a bulle
def tri_lexico(t):
    permutation = True
    passage = 0
    while permutation:
        permutation = False
        passage = passage + 1
        for en_cours in range(0, len(t) - passage):
            if ordre_lexico(t[en_cours],t[en_cours+1]) == 2:
                permutation = True
                # On echange les deux elements
                t[en_cours], t[en_cours + 1] = t[en_cours + 1],t[en_cours]
    return t


# In[100]:


tab =["toto", "titi","tata"]




tri_lexico(tab)
