#/usr/bin/python
# -*- coding: utf-8 -*-

# *** Exercice 1 ***
# Question 1
def liste():
    """Renvoie la liste vide."""
    return ()
def vide(l):
    """Teste si la liste l est vide."""
    return l == ()
def cons(a, l):
    """Renvoie la liste obtenue en ajoutant l'élément a devant la liste l."""
    return (a, l)
def tete(l):
    """Renvoie le premier élément de l."""
    t, q = l # (l est un couple, donc on peut le séparer en deux)
    return t
def queue(l):
    """Renvoie la liste l à laquelle on enlève le premier élément."""
    t, q = l
    return q

# Question 2
# On fabrique la liste contenant les éléments de 1 à 6 en partant de
# la liste vide (obtenue par liste()) et en ajoutant un par un les
# éléments à l'aide de la fonction cons
l = cons(1, cons(2, cons(3, cons(4, cons(5, cons(6, liste()))))))

# Question 3
def longueur(l):
    """Donne la longueur de la liste l."""
    if vide(l):
        return 0
    else:
        return 1 + longueur(queue(l))
def element(i, l):
    """Donne le i-ème élément de la liste."""
    if i == 0:
        return tete(l)
    else:
        return element(i-1, queue(l))
def supprimer(i, l):
    """Renvoie la liste l à laquelle on a enlevé le i-ème élément."""
    if i == 0:
        return queue(l)
    else:
        return cons(tete(l), supprimer(i-1, queue(l)))
def ajouter(x, i, l):
    """Ajoute l'élement x en position i dans la liste l."""
    if i == 0:
        return cons(x, l)
    else:
        return cons(tete(l), ajouter(x, i-1, queue(l)))

# Question 4
def map(f, l):
    """Applique la fonction f à tous les élements de l."""
    if vide(l):
        return l
    else:
        return cons(f(tete(l)), map(f, queue(l)))
def count(x, l):
    """Compte le nombre d'occurrences de x dans l."""
    if vide(l):
        return 0
    else:
        if tete(l) == x:
            return 1 + count(x, queue(l))
        else:
            return count(x, queue(l))
def filter(f, l):
    """Renvoie la liste des éléments de l qui pour lesquels la fonction f vaut True."""
    if vide(l):
        return l
    else:
        if f(tete(l)):
            return cons(tete(l), filter(f, queue(l)))
        else:
            return filter(f, queue(l))
def reduce(f, l, x):
    """Si l'on nomme y0, y1, ..., yn les éléments de l, on définit
x0 = x, x1 = f(x0, y0), x2 = f(x1, y1),
et ainsi de suite jusqu'à xn+1 = f(xn, yn).
La fonction doit renvoyer xn+1."""
    if vide(l):
        return x
    else:
        return reduce(f, queue(l), f(x, tete(l)))

# *** Exercice 2 ***

MAX = 100
# Question 1
def liste_tab():
    """Créé une liste vide (représentée sous la forme d'un tableau)."""
    return[[None]*MAX, 0]
def longueur_tab(l):
    """Renvoie la longueur de la liste l."""
    return l[1]
def element_tab(i, l):
    """Renvoie le i-ème élément de la liste l."""
    return l[0][i]
def ajouter_tab(x, i, l):
    """Ajoute l'élément x en position i dans la liste l."""
    # Rappel :
    #   l[0] désigne le tableau contenant les valeurs de la liste
    #   l[1] désigne le nombre d'éléments présents dans la liste
    for i in range(l[1]-1, i-1, -1):
        l[0][i+1] = l[0][i] # on décale toutes les cases après l'indice i
    l[0][i] = x
    l[1] += 1
def supprimer_tab(i, l):
    """Supprime l'élément en position i dans la liste l."""
    for j in range(i, l[1]-1):
        l[0][j] = l[0][j+1]
    l[1] -= 1

# Une définition de liste pour tester les fonctions
l_tab = liste_tab()
ajouter_tab(3, 0, l_tab)
ajouter_tab(1, 0, l_tab)
ajouter_tab(2, 1, l_tab)
ajouter_tab(5, 3, l_tab)
ajouter_tab(4, 3, l_tab)

# Question 2
def vide_tab(l):
    """Teste si la liste l est vide."""
    return l[1] == 0
def tete_tab(l):
    """Renvoie le premier élément de la liste l."""
    return l[0][0]
def queue_tab(l):
    """Renvoie la queue de la liste l."""
    q = liste_tab()
    for i in range(1, l[1]):
        q[i-1] = l[i]
    return q
def cons(x, l):
    """Renvoie une copie de la liste l à laquelle on a ajouté
    l'élément x en tête."""
    l2 = liste_tab()
    l2[0][0] = x # on écrit x sur la première case du tableau
    for i in range(l[1]):
        l2[0][i+1] = l[0][i] # copie les valeurs de l dans l2
    l2[1] = l[1] + 1 # l2 a un élément de plus que l
    return l2

# Question 3
def map_tab(f, l):
    """Applique la fonction f à tous les éléments de l."""
    for i in range(l[1]):
        l[0][i] = f(l[0][i])
def count_tab(x, l):
    """Compte le nombre d'occurrences de x dans la liste l."""
    c = 0 # nombre de fois que l'élément x a été vu
    for i in range(l[1]):
        if l[0][i] == x:
            c += 1
    return c
def filter(f, l):
    """Renvoie une copie de la liste l ne contenant que les éléments
    pour lesquels la fonction f vaut True."""
    l2 = liste_tab()
    for i in range(l[1]):
        if f(l[0][i]):  # Si le i-ème élément de l vérifie f, on
                        # l'ajoute à la fin de l2
            ajouter_tab(l[0][i], l2[1], l2)
    return l2
def reduce_tab(f, l, x):
    """Réduit la liste l selon f."""
    for i in range(l[1]):
        x = f(x, l[0][i])
    return x
