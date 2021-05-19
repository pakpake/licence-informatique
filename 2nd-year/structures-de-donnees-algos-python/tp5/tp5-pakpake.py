"""
title: TP5 - Liste Chainee
author: pakpake 
date: Fevrier 2020
"""

""" Exercice 1 """



class Cellule():
    def __init__(self):
        self.data = object
        self.suivant = None


    def remove(self):
        self.remove()

            


class Liste:

    def __init__(self):
        self.mpremier = None   # Premier element de la liste
        self.taille = 0         #taille de la liste

    def est_liste_vide(self):
        v = (self.mpremier == None)
        return v
        
    def tete(self):
        if self.mpremier == None:
            print("liste vide")
        else:
            p = self.mpremier
            return p

    def premiere_valeur(self):
        if self.mpremier == None:
            print("liste vide")
        else:
            s = self.mpremier.data
            return s
    
    def insere_tete(self, v):
        m = Cellule()
        m.data=v
        if self.mpremier == None:
            m.suivant = None
        else:
            m.suivant=self.mpremier
        self.mpremier = m
        self.taille += 1

    def __str__(self):
        if self.est_liste_vide():
            chaine = ""
        else:
            chaine = ""
            courant = self.mpremier
            while courant != None:
                chaine += " " + str(courant.data)
                courant = courant.suivant
        chaine = '[' + chaine + ']'
        return chaine

    def length(self):
        # self.taille = 0
        # courant = self.tete()
        # while courant != None:
        #     courant = courant.suivant
        #     self.taille += 1
        return self.taille
    
    def get_at(self,i):
        # renvoie le i-eme element de la liste
        if i >= self.length() or i < 0:
            return ("erreur")
        else:
            # on definit le premier element a 0
            courant = self.mpremier
            while i>0:
                courant = courant.suivant
                i-=1
            return courant.data
    
    def remplace_at(self,e,i):
        if i >= self.length() or i < 0:
            return ("ERROR : indice en dehors de la liste dans la fonction remplace_at")
        else:
            # on definit le premier element a 0
            courant = self.mpremier
            while i>0:
                courant = courant.suivant
                i-=1
            courant.data = e
    
    def insere_at(self,e,i):
        # renvoie la liste a laquelle on a insere l'element e en position i
        m = Cellule()
        m.data = e
        if i >= self.length() or i < 0:
            return ("ERROR : indice en dehors de la liste dans la fonction insere_at")
        else:
            # on definit le premier element a 0
            courant = self.mpremier
            # on s'arrete sur l'element precedant l'endroit ou on veut inserer e
            i -= 1
            while i>0:
                courant = courant.suivant
                i-=1
            m.suivant = courant.suivant     # on raccroche les elements suivant de la liste a la nouvelle cellule
            courant.suivant = m     # on rattache le nouvel element au bon endroit
            self.taille += 1        # on met a jour la taille de la liste

    def supprime_at(self,i):
        # renvoie la liste a laquelle on a enelever le i-eme element 
        if i >= self.length() or i < 0:
            return ("problem")
        else:
            courant = self.mpremier
            # on s'arrete sur l'element precedant l'endroit ou on veut inserer e
            i -= 1
            while i>0:
                courant = courant.suivant
                i-=1
            m = courant.suivant     # on raccroche l'element a supprimer a un pointeur temporaire m
            courant.suivant = m.suivant     # on dit que le suivant de courant c'est le suivant de m
            self.taille -= 1        # on met a jour la taille de la liste
            # m.remove()      # on libere la memoire occupee par le pointeur qu'on supprime

    def map(self,f):
        # applique a chaque element de la liste la fonction f
        courant = self.mpremier
        while courant != None:
            f(courant.data)
            courant = courant.suivant
    
    def count(self,e):
        courant = self.mpremier
        counter = 0
        # for i in range(len(self)):
        #     if courant.data == e:
        #         counter += 1
        #     courant = courant.suivant
        # return counter
        while courant != None:
            if courant.data == e:
                counter += 1
            courant = courant.suivant
        return counter

    def filter(self,f):
        # renvoie la liste des elements pour lesquels la fonction f renvoie True
        courant = self.mpremier
        # creation d'une liste vide
        l = Liste()
        while courant != None:
            if f(courant.data):
                l.insere_tete(courant.data)
            courant = courant.suivant
        return l
        # les elements sont ajoutes en ordre relatif inverse

    def reduce(self,f,x):
        courant = self.mpremier
        while courant != None:
            x = f(x,courant.data)
            courant = courant.suivant
        return x

###############################################################################################
########################################## Test Area ##########################################


# creation de notre liste
e = Liste()
# insertion des éléments dans la liste
e.insere_tete(1)
e.insere_tete(0)
e.insere_tete(2)
e.insere_tete(3)
e.insere_tete(4)

# affichage de la liste
print(e)
# ou : e.__str__()

# calcul et affichage de la longueur de la liste
res = Liste.length(e)
print("Longueur de la liste = ",res)

# affiche le i-eme element de la liste
# position de l'element a afficher
ieme_element = 1
#renvoi le 1 er element de la liste (en partant de 0)
print("affiche l'element n°",ieme_element," = ",e.get_at(ieme_element))


# insere l'elemente a la position i et affiche la liste
e.insere_at(99, 1)
print(e)

# enleve l'element i et affiche la liste
e.supprime_at(3) 
print("suppression du 3eme element : ",e)


######################################## End Test Area ########################################


""" 2) Construction de la liste avec les fonctions ci-dessus """

# Construction de la liste [1,...,6]
la_liste = Liste()
for i in range(6,0,-1):
    la_liste.insere_tete(i)

print(la_liste)

###############################################################################################


""" 3) Fonctions map ; count ; filter ; reduce """

########################################## Test Area ##########################################
import random

""" map """

liste_map = Liste()
for i in range(10):
    liste_map.insere_tete(random.randint(0,8))

print("Liste originale = ",liste_map)

def f(x):
    return print("x = ",x)


print("Liste x2 = ",liste_map.map(f))


""" count """

liste_counter = Liste()
for i in range(10):
    liste_counter.insere_tete(random.randint(0,3))

print(liste_counter)

print("il y a ",liste_counter.count(3)," element 3")


""" filter """
l2 = Liste()
for i in range(10):
    l2.insere_tete(random.randint(0,10))

print("liste avant filter = ",l2)

def f2(x):
    return x>5

print("liste apres filter = ",l2.filter(f2))

""" reduce """

l3 = Liste()
for i in range(10):
    l3.insere_tete(random.randint(0,10))

print("liste originale = ",l3)

def f3(x,y):
    return x+y

print("derniere valeur obtenue apres fonction = ",l3.reduce(f3,0))




######################################## End Test Area ########################################

###############################################################################################
###############################################################################################


""" Exercice 2 """

class Bowl:
    def __init__(self,couleur):
        """ Modelisation de la boule avec restriction sur la couleur """
        if couleur == "R":
            self.couleur = 0        # je defini la couleur par un chiffre pour que ce soit plus facile pour le tri
        if couleur == "V":
            self.couleur = 1
        if couleur == "B":
            self.couleur = 2
        if couleur not in {"R","V","B"}:
            raise Exception("La couleur doit être R, V ou B")

    def __str__(self):
        if self.couleur == 0:
            return "R"
        if self.couleur == 1:
            return "V"
        if self.couleur == 2:
            return "B"
    
    def __lt__(self,other):
        """ redefinissions de l'operateur inferieur, indispensable pour appeler la fonction sort """
        return self.couleur < other.couleur




class BowlSort:
    """ Construction d'une liste de n boules, en insérant les boules à la bonne place. Les boules rouges précèdent les boules vertes qui elles mêmes précèdent les boules bleues """
    def __init__(self):
        """ creation d'une liste vide """
        self.liste = []
    
    def __str__(self):
        # for i in self.liste:
        #     print(i.couleur)
        # print("")
        self.liste.sort()
        # for i in self.liste:
        #     print(i.couleur)
        return '{' + ','.join([str(e) for e in self.liste]) + '}'

    def insert(self, b):
        """ insertion de boules dans la liste """
        self.liste.append(b)
    
    def counter(self,couleur):
        l = []
        for i in self.liste:
            l.append(i.couleur)
        if couleur == "R":
            return l.count(0)
        if couleur == "V":
            return l.count(1)
        if couleur == "B":
            return l.count(2)

    def occur1(self,couleur):
        """ renvoit l’indice de la première occurrence de boule R,V ou B """
        l = []      # creation de la liste locale
        for i in self.liste:        # parcours et remplissage de cette liste
            l.append(i.couleur)
        if couleur == "R":
            return l.index(0)
        if couleur == "V":
            return l.index(1)
        if couleur == "B":
            return l.index(2)

    def occurk(self,couleur,k):
        """ renvoit l’indice de la k-ème occurrence de boule R,V ou B """
        l = []
        for i in self.liste:
            l.append(i.couleur)
        # verification que k ieme occurence de la boule de couleur existe
        if couleur == "R":
            if l.count(0) < k:
                raise Exception("Il n'existe pas assez de ",couleur)
            else:
                return l.index(0)+k-1
        if couleur == "V":
            if l.count(1) < k:
                raise Exception("Il n'existe pas assez de ",couleur)
            else:
                return l.index(1)+k-1
        if couleur == "B":
            if l.count(2) < k:
                raise Exception("Il n'existe pas assez de ",couleur)
            else:
                return l.index(2)+k-1
    
    def supprime(self,couleur):
        """ supprime toutes les boules d’une certaine couleur """
        # recuperation de l'indice de la couleur 
        i1 = self.occur1(couleur)
        # combien de couleur y-a-t-il
        c = self.counter(couleur)
        del self.liste[i1:i1+c]
        return self


########################################################################


import random

coul = ("R","V","B")    # creation de la liste des choix (couleurs) possibles

l = BowlSort()      # creation d'une liste vide

# on remplit une liste aleatoirement
for i in range(6):
    l.insert(Bowl(random.choice(coul)))

print("Liste = ",l)        # affichage de la liste


########################################################################

print("nombre de V = ",l.counter("V"))

print("indice de la 1ere occurence de  V (liste triee) = ",l.occur1("V"))

print("indice de la 3eme boule verte = ",l.occurk("V",1))

print("liste avec boules vertes supprimées = ",l.supprime("V"))

########################################## Fin du TP5 ##########################################
