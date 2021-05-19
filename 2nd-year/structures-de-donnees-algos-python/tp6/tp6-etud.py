"""
@author: pakpake 
@date: fevrier 2020
@title: TP6 - exercice 1
"""


class Cellule():
    def __init__(self):
        self.value = object
        self.next = None
        self.prev = None
        
class DoubleLinkedList:
    """  Liste  doublement chaînée """
    def __init__(self):
        """:sortie self:
        :post-cond: self est une liste vide"""
        #### attributs prives
        self.mpremier = None   # Premier element de la liste
        self.taille = 0        # Taille de la liste
        
    def __str__(self):
        """affiche la liste"""
        """ entree : self """
        msg="["
        if (not(self.emptyList())):
            e=self.mpremier
            while e.next!=None:
                msg+=str(e.value)+","
                e=e.next
            msg+=str(e.value)
        return msg+"]"
        
    def sizeList(self):
        """affiche la taille de la liste"""
        """ entree : self """
        """ sortie : int, longueur de la liste """
        length=0
        e=self.mpremier
        while (e!=None):
            e=e.next
            length+=1
        self.taille = length
        return length
        # on peut tout remplacer par => return self.taille
        
    def emptyList(self):
        """:entree self:
        :sortie : bool
        :renvoie True si et seulement si self est vide"""
        return(self.mpremier == None)       # ou return self.taille==0
        
    def insertFirst(self, e):
        """:entree/sortie self:
        :entree e: object
        :insere e en tete de liste"""
        m = Cellule()
        m.value=e
        m.prev=None
        if self.mpremier == None:
            m.next = None
        else:
            self.mpremier.prev=m
            m.next=self.mpremier
        self.mpremier = m
        self.taille+=1
        
    def insertLast(self, e):
        """:entree/sortie self:
        :entree e: object
        :insere e en fin de liste"""
        m = Cellule()
        m.value=e
        m.next=None
        x=self.mpremier
        for i in range(self.sizeList()-1):
            x=x.next
        x.next=m
        m.prev=x
        self.taille+=1
        
    def head(self):
        """retourne la valeur du premier element de la liste"""
        """ entree : self """
        if self.emptyList():
            return ("head : Liste vide")
        return self.mpremier.value
        
    def insertBefore(self, v, elt):
        """insere la valeur v avant l'element elt de type Cellule """
        m = Cellule()
        m.value = v
        if self.emptyList():
            return ("la liste est vide !")
        else:
            # si la position a laquelle on veut inserer l'element correspond au 1er element de la liste, on insere avec la méthode insertFirst()
            if self.mpremier==elt:
                m.insertFirst()
            else:
                courant = self.mpremier
                while courant != elt and courant != None:
                    courant = courant.next
                if courant == None:
                    return "insertBefore : elt non trouvé"
                courant.prev.next = m
                m.prev = courant.prev
                m.next = courant
                courant.prev = m
            self.taille += 1


    def insertAfter(self, v, elt):
        """ insere la valeur v apres l'element elt de type Cellule """
        m = Cellule()
        m.value = v
        if self.emptyList():
            return("La liste est vide")
        else:
            courant = self.mpremier
            while courant != elt and courant != None:
                courant = courant.next
            if courant == None:
                return "insertAfter : elt non trouvé"
            courant.next.prev = m
            m.next = courant.next
            m.prev = courant
            courant.next = m
            self.taille += 1


    def insertAt(self, v, n):
        """ insere l'element de valeur v en nieme position """
        if self.taille < n:
            return "insertAt : pas assez d'elements"
        else:
            x=self.mpremier
            for i in range(n-1):
                x=x.next
            self.insertAfter(v,x)
            self.taille += 1


    def getFirst(self):
        """ retourne le premier element """     
        return self.head()

    def getLast(self):
        """ retourne le dernier element """    
        x=self.mpremier
        for i in range(self.sizeList()-1):
            x=x.next
        return x.value

    def getAt(self, n):
        """ retourne la valeur du nieme element """   
        if self.taille < n:
            return "getAt : pas assez d'elements"
        else:
            x=self.mpremier
            for i in range(n-1):
                x=x.next
            return x.value

    def getEltAt(self, n):
        """ recherche de l'element d'indice n-1 """
        if self.taille < n:
            return "getEltAt : pas assez d'elements"
        else:
            x=self.mpremier
            for i in range(n-1):
                x=x.next
            return x

    def tail(self):
        """ retourne la liste dans laquelle on a retire le premier element """
        if self.taille < 1:
            return "tail : pas assez d'elements"
        else:
            return self.mpremier.next

    def find(self,v):
        """ retourne un pointeur sur la premiere occurrence de v ou None """
        courant = self.mpremier
        while courant != None and courant.value != v:
            courant = courant.next
        return courant

    def remove(self, v):
        """ retire la premiere occurrence de l'element de valeur v """
        if self.emptyList():
            return "remove : liste vide"
        else:
            x = self.find(v)    # pointeur sur l'element qui contient v ou None
            if x == None:
                return "remove : v n'est pas dans la liste"
            else:
                x.prev.next = x.next
                x.next.prev = x.prev
                self.taille -= 1

    def removeAt(self, n):
        """ retire le nieme element (position n-1) """
        if self.taille < n:
            return "removeAt : pas assez d'elements"
        else:
            x = self.mpremier
            for i in range(n-1):
                x = x.next
            x.prev.next = x.next
            x.next.prev = x.prev
            self.taille -= 1

    def removeFirst(self):
        """ retire le premier element """
        # return self.tail()
        if self.emptyList():
            return "removeFirst : liste vide"
        else:
            mpremier = mpremier.next
            mpremier.prev = None
            self.taille -= 1

###########################################################################################################
import random

l = DoubleLinkedList()      # creattion d'une liste vide

for i in range(15):     # remplissage de liste par des valeurs aleatoires
    l.insertFirst(random.randint(0,9))

print("liste = ",l)     # affichage de la liste

# taille de la liste
print("taille = ",l.sizeList())

# liste vide
print("liste vide ou non = ",l.emptyList())

# insertFirst
print("inserer en 1ere position le nombre 99 ")
l.insertFirst(99)
print("nouvelle liste = ",l)

# insertLast
print("insere en derniere position le nombre 66 ")
l.insertLast(66)
print("nouvelle liste = ",l)

# Premier element de la liste
print("1er element de la liste = ",l.head())

# insertBefore
print("inserer avant le chiffre 2 par exemple 77 = ")
l.insertBefore(77,l.find(2))
print("nouvelle liste = ",l)

# insertAfter
print("inserer apres le chiffre 5 par exemple 88 = ")
l.insertAfter(88,l.find(5))
print("nouvelle liste = ",l)

# insertAt en nieme position
print("insere en 6eme position le nombre 55 = ")
l.insertAt(55,6)
print("nouvelle liste = ",l)

# getFirst idem que head
print("1er element de la liste v2 = ")
l.getFirst()

# getLast
print("Dernier element de la liste = ")
l.getLast()

# getAt : valeur du nieme element
print("Valeur du 6 element = ")
l.getAt(6)

# tail : liste sans le 1er element de la liste
#################################################
print("liste sans le premier element = ",l.tail())

# remove 
print("liste sans la premiere occurence du chiffre 3 = ")
l.remove(3)
print("nouvelle liste = ",l)

# removeAt
print("liste sans le 4eme element = ")
l.removeAt(4)
print("nouvelle liste = ",l)

# removeFirst
#################################################
print("liste en supprimant le 1er element de la liste = ")
l.removeFirst()
print("nouvelle liste = ",l)
