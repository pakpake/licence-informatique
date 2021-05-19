"""
auteur :  pakpake
suite exercice 4 du tp1
"""
"""Exercice 4"""
# 1)
import numpy as np
def tri_compte1(t,M):
    # on créé un tableau rempli de 0 de taille M+1
    compte = np.zeros(M+1,dtype=int)
    for i in t:
        compte[i] += 1
    res = []
    for i in range(0,len(compte)):
        for j in range(0,compte[i]):
            res.append(i)
    return res




tri_compte1([3,1,2,5,3,4,4,3,5],5)




# 2) La complexité est O(M+n)
# ligne 31 O(M+1), ligne 32-33 O(n), ligne 34 O(1) et ligne 35-37 O(n)




# 3) test
k = numpy.random.randint(0,100,10000,dtype=int)
tri_compte1(k,100)




# 4)
def tri_compte2(t,M):
    # on créé un tableau rempli de 0 de taille M+1
    compte = np.zeros(M+1,dtype=int)
    for i in t:
        compte[i] += 1
    k=0
    for i in range(0,len(compte)):
        for j in range(0,compte[i]):
            t[k]=i
            k += 1
    return t




tri_compte2(k,100)




# 5) 
def tri_compte3(t):
    M = max(t)
    # on créé un tableau rempli de 0 de taille M+1
    compte = np.zeros(M+1,dtype=int)
    for i in t:
        compte[i] += 1
    k=0
    for i in range(0,len(compte)):
        for j in range(0,compte[i]):
            t[k]=i
            k += 1
    return t




tri_compte3(k)






