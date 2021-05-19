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

# 1) max & min value in  lists

def max():
    t = [1,2,3,4]
    max = t[0]
    for i in range(len(t)):
        if t[i] > max:
            max = t[i]
    return max

def min():
    t = [4, 3, 2, 1]
    min = t[0]
    for i in range(len(t)):
        if t[i] < min:
            min = t[i]
    return min
