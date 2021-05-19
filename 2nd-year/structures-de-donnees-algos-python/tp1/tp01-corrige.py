#!/user/bin/python
# -*- coding: utf-8 -*-

# *** Exercice 1

def double(n):
	return 2*n

def double_tab(t):
	res = [0] * len(t)
	for i in range(len(t)):
		res[i] = 2*t[i]
	return res

# autre solution :
def double_tab(t):
	res = []
	for x in t:
		res.append(2*x)
	return res

# autre solution :
def double_tab(t):
	return [2*x for x in tab]

def compare(x, y):
	if x == y:
		print "Egal"
	elif x < y:
		print "Plus petit"
	else:
		print "Plus grand"

# *** Exercice 2

t1 = range(100)

t2 = range(100)
t2.reverse()
# ou bien :
t2 = range(99, -1, -1)

import random

t3 = [None]*n # on initialise le tableau avec une valeur spéciale
# on va ensuite essayer, pour tout i entre 0 et (n-1) de trouver un emplacement vide où le mettre
for i in range(100):
	while True:
		j = random.randint(0, n-1)
		# si on trouve une case vide, on sort de la boucle while
		if t3[j] == None: break
		t3[j] = i

#autre solution :
t3 = range(100)
random.shuffle(t3)

# *** Exercice 3

def max(tab):
    """Trouve le max d'un tableau."""
    global comparaisons
    # le max "temporaire" est initialisé à la première valeur du tableau
    max = tab[0]
    # on compare chaque élément du tableau à max, s'il est plus grand on met à jour max
    for x in tab:
        if x > max:
            max = x
    return max

def min(tab):
    """Trouve le min d'un tableau."""
    global comparaisons
    min = tab[0]
    for x in tab:
        if x < min:
            min = x
    return min

def minmax(tab):
    """Trouve le min et le max d'un tableau."""
    return (min(tab), max(tab))

def minmax2(tab):
    """Trouve le min et le max d'un tableau (avec le nombre optimal de comparaisons)."""
    global comparaisons
    # on initialise min et max
    max = tab[0]
    min = tab[0]
    # on parcourt le tableau de 2 en 2 en partant de (len(tab)%2)
    # (% -> modulo) c'est à dire 0 si le tableau est de longueur paire
    # et 1 sinon.
    # le 2 en dernier argument de range signifie qu'on prend une
    # valeur sur deux.
    # sur un tableau de longueur 13 on aura donc :
    # range(1, 13, 2) = [1, 3, 5, 7, 9, 11]
    # sur un tableau de longueur 12 on aura :
    # range(0, 12, 2) = [0, 2, 4, 6, 8, 10]
    for i in range(len(tab)%2, len(tab), 2):
        # on applique l'algo sur les deux cases tab[i] et tab[i+1]
        x, y = tab[i], tab[i+1]
        if x < y:
            if x < min:
                min = x
            if y > max:
                max = y
        else:
            if y < min:
                min = y
            if x > max:
                max = x
    return (min,max)

# *** Exercice 3

def tri_permut(tab):
    """Trie le tableau en place en permutant le i-eme élément avec le
    plus petit restant."""
    global comparaisons
    def index_min(tab):
        """Fonction intermédiaire qui trouve l'indice du plus petit
        élément d'un tableau."""
        idx_min = 0
        min = tab[0]
        for i, x in enumerate(tab):
            if x < min:
                min = x
                idx_min = i
        return idx_min
    for i in range(len(tab)):
        j = i + index_min(tab[i:])
        tab[i], tab[j] = tab[j], tab[i]
    return tab

def tri_fusion(tab):
    """Trie le tableau récursivement par la méthode du tri fusion."""
    def fusion(t1, t2):
        """Effectue la fusion de deux tableaux triés."""
        result = [0]*(len(t1)+len(t2))
        i, j = 0, 0
        while i < len(t1) and j < len(t2):
            if t1[i] < t2[j]:
                result[i+j] = t1[i]
                i += 1
            else:
                result[i+j] = t2[j]
                j += 1
        if i == len(t1): result[i+j:] = t2[j:]
        else: result[i+j:] = t1[i:]
        return result
    l = len(tab)
    if l < 2:
        return tab
    else:
        return fusion(tri_fusion(tab[:l/2]), tri_fusion(tab[l/2:]))

def tri_fouillis(tab):
    """Trie le tableau en le mélangeant jusqu'à ce qu'il soit trié."""
    # Attention, ne testez pas cette fonction sur des tableaux de plus
    # de 10 cases...
    def ordered(tab):
        ordered = True
        last_value = tab[0]
        for x in tab:
            if x < last_value: ordered = False
            last_value = x
        return ordered
    while not ordered(tab):
        random.shuffle(tab)
    return tab
