# -*- coding: utf-8 -*-
"""
@date: Mars 2020
@author: pakpake 
"""


######## Specification ########

class Pile_List:
    def __init__(self):
        """:sortie : self
        :creation d'une pile vide (self)"""

    def pile_vide(self):
        """:entree : self
        :sortie b: bool
        :b est True si et seulement si la pile est vide"""

    def pile_pleine(self):
        """:entree : self
        :sortie b: bool
        :b est True si et seulement si la pile est pleine"""

    def empile(self, v):
        """:entree/sortie : self
        :entree e: object
        :pre-cond: la pile n'est pas pleine
        :on modifie la pile en ajoutant l'element de valeur v en tete de pile"""

    def tete(self):
        """:entree : self
        :sortie t: object
        :pre-cond: la pile self n'est pas vide
        : on recupere l'element elt au sommet de la pile"""

    def sommet(self):
        """:entree : self
        :sortie t: object
        :pre-cond: la pile n'est pas vide
        : on recupere la valeur de l'element au sommet de la pile"""

    def depile(self):
        """:entree/sortie : self
        :pre-cond: la pile n'est pas vide
        :on modifie la pile en retirant le dernier element stocke au sommet de self"""

    def evalue(self,exp):
        """ a definir"""
        # entree : self, exp
        # sortie t : valeurs
        # pre cond : exp n'est pas vide
        # on calcule la valeur de exp en notation polonaise inversé

        
        
######## Implementation #########

class Cell():
    def __init__(self):
        # initialisation de l'objet cellule
        self.valeur = object
        self.suivant = None

class Pile():
    def __init__(self):
        # initialisation de la classe pile
        self.msommet = None     # element de tete
        self.taille = 0
        self.MAX = 10   # pile de taille MAX = 10

    def pile_vide(self):
        # la pile est vide quand msommet est None
        return self.msommet == None

    def pile_pleine(self):
        # la pile est pleine quand on atteint MAX
        return self.taille == self.MAX

    def empile(self, v):
        # ajout d'une nouvelle cellule en tete
        m = Cell()      # creation de la cellule
        m.valeur = v    # remplissage de la valeur
        m.suivant = self.msommet    # on accroche a la nouvelle cellule le reste de la pile
        self.msommet = m    # msommet pointe sur la nouvelle cellule
        self.taille += 1    # la taille augmente de 1

    def tete(self):
        # renvoie l'objet cellule en tete
        # if self.msommet == None:
        #     print("tete : pile vide")
        # else:
        #     s = self.msommet
        #     return s
        return self.msommet

    def sommet(self):
        # renvoie la valeur de l'objet cellule en tete
        # if self.msommet == None:
        #     print("sommet : pile vide")
        # else:
        #     s = self.msommet.valeur
        #     return s
        return self.msommet.valeur

    def depile(self):
        # retire le 1er objet de la pile
        if self.msommet == None:
            print("depile : pile vide")
        else:
            retour = self.msommet.valeur    # on recupere la valeur de l'objet en tete
            self.msommet = self.msommet.suivant # on fait pointer msommet vers le suivant
            self.taille -= 1    # on met a jour la taille 
            return retour   # on renvoit la valeur de l'objet retire

    def __str__(self):
        # fonction d'affichage de la pile
        if self.pile_vide():
            pile = ""
        else:
            pile = ""
            courant = self.msommet
            while courant != None:
                pile += " " + str(courant.valeur)
                courant = courant.suivant
            pile = '[' + pile + ' ]'
        return pile

    def evalue(self,exp):
        # evalue une expression arithmetique en notation polonaise inverse (RPN) sans les espaces
        if (exp is not None and not(exp)):
            print("evalue : L'expression est vide")
        else:
            # on parcourt tous les elements de l'expression
            for i in range(len(exp)):
                if exp[i]=="+":     # on effectue l'addition des deux nombres en haut de la pile et on place le resultat en haut de la pile
                    x = int(self.depile())
                    y = int(self.depile())
                    total=x + y
                    self.empile(total)
                elif exp[i] == "-":
                    x = int(self.depile())
                    y = int(self.depile())
                    total=x - y
                    self.empile(total)
                elif exp[i] == "*":
                    x = int(self.depile())
                    y = int(self.depile())
                    total=x * y
                    self.empile(total)
                elif exp[i] == "/":
                    x = int(self.depile())
                    y = int(self.depile())
                    total=x / y
                    self.empile(total)
                else:
                    # si le caractere lu est un nombre on l'empile
                    self.empile(int(exp[i]))
            return total
    
    def evalue2(self,exp):
        # evalue une expression arithmetique en notation polonaise inverse (RPN) avec des espaces comme separateurs
        if (exp is not None and not(exp)):
            print("evalue : L'expression est vide")
        else:
            i = 0
            # on élimine les espaces avant l'expression
            while exp[i] == " ":
                i += 1
            # on parcourt tous les elements de l'expression
            while i < len(exp):
                # print("it: ",i)
                if exp[i]=="+":     # on effectue l'addition des deux nombres en haut de la pile et on place le resultat en haut de la pile
                    x = int(self.depile())
                    y = int(self.depile())
                    total=x + y
                    # print("+",x," ",y,"=",total)
                    self.empile(total)
                    i+=2
                elif exp[i] == "-":
                    x = int(self.depile())
                    y = int(self.depile())
                    total=x - y
                    # print("-",x," ",y,"=",total)
                    self.empile(total)
                    i+=2
                elif exp[i] == "*":
                    x = int(self.depile())
                    y = int(self.depile())
                    total=x * y
                    # print("*",x," ",y,"=",total)
                    self.empile(total)
                    i+=2
                elif exp[i] == "/":
                    x = int(self.depile())
                    y = int(self.depile())
                    total=x / y
                    # print("/",x," ",y,"=",total)
                    self.empile(total)
                    i+=2
                else:
                    # on cherche le prochain indice de l'espace dans l'exp
                    k = exp.find(" ",i)
                    if k != -1:
                        # si cet indice est trouve les caracteres entre i et k-1 sont les caracteres d'un nombre empile
                        self.empile(int(exp[i:k]))
                        i = k+1     # on avance jusqu'au prochain caractere a traiter
                    else:
                        # sinon on a fini l'evaluation de l'exp
                        i = len(exp)+1
            return total
    

################################ Zone de test ###############################
import random

p = Pile()
p.empile(1)
p.empile(2)
p.empile(3)
p.empile(4)
p.empile(5)

for i in range(10):
    p.empile(random.randint(0,9))

print(p.sommet())

print(p)
###############
p.depile()
print(p)
###############
print(p.pile_vide())
###############
# teste si la taille max de la pile est 10
print(p.pile_pleine())
###############
print(p.tete())
###############

#########################################################################

""" Exercice 2 """
# 2 3 + = 5
# 1 3 + 2 * = 8
# 4 3 * 2 5 + + = 19

""" question 1 et 2 """

exp1 = "23+"
p = Pile()
print("Calcul de \"23+\" = ",p.evalue(exp1))

#########################################################################

""" Q°) 3 """
# avec séparateur " "

exp2 = "4 12 * 2 10 + +"
p2 = Pile()
print("Calcul de \"",exp2,"\" = ",p2.evalue2(exp2))