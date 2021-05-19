class Bowl:
    def __init__(self,couleur):
        """ Modelisation de la boule avec restriction sur la couleur """
        if couleur == "R":
            self.couleur = 0
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