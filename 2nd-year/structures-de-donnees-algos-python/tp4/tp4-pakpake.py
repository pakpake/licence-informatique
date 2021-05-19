"""
@title: TP 4 - Classes et Types Abstraits de Donnée
@author: J. 
@date: February 2020
"""


""" Exercice 1 """


import random

class intSet(object):
    """An intSet is a set of integers
    The value is represented by a list of ints, self.vals.
    Each int in the set occurs in self.vals exactly once."""

    def __init__(self):
        """Create an empty set of integers"""
        self.vals = []

    def insert(self, e):
        """Assumes e is an integer and inserts e into self""" 
        if not e in self.vals:
            self.vals.append(e)

    def member(self, e):
        """Assumes e is an integer
           Returns True if e is in self, and False otherwise"""
        return e in self.vals

    def remove(self, e):
        """Assumes e is an integer and removes e from self
           Raises ValueError if e is not in self"""
        try:
            self.vals.remove(e)
        except:
            raise ValueError(str(e) + ' not found')

    def __str__(self):
        """Returns a string representation of self"""
        # on trie la liste de valeurs par ordre croissant
        self.vals.sort()
        return '{' + ','.join([str(e) for e in self.vals]) + '}'
        

    def intersect(self, self2):
        result = intSet()
        for i in self.vals:
            if i in self2.vals:
                result.vals.append(i)
        return result
        #return '{' + ','.join([str(k) for k in result]) + '}'
    

    def union(self, self3):
        result1 = intSet()
        result1.vals = self.vals[:]
        for i in self3.vals:
            if i not in self.vals:
                result1.vals.append(i)
        return result1
    
    
    def diff(self, self4):
        result2 = intSet()
        for i in self.vals:
            if i not in self4.vals:
                result2.vals.append(i)
        return result2
    
    def diff_sym(self,self5):
        result = self.diff(self5)
        result1 = self5.diff(self)
        return result1.union(result)
    
    
    def diff_sym_bis(self,self6):
        result = self.union(self6)
        result1 = self.intersect(self6)
        return result.diff(result1)
        
    #Non demandé mais utilisé pour vérifier que la longueur est bien celle que je veux pour la boucle while     def __len__(self):
        """Returns the number of elements in self"""
        counter = 0
        for i in self.vals:
            counter += 1    
        return counter
    
    
    
""" Execution des méthodes """    
#1)
# on créer notre liste 
E = intSet() 
# on la remplie
E.insert(1)
E.insert(2)
E.insert(3)
E.insert(4)
E.insert(0)
# on l'affiche
print(E.vals)

# ou la meme chose avec une boucle for
E = intSet()
for i in range(0,4):
    E.insert(i)

print(E.vals)

#2
# on affiche notre liste avec la méthode str
print(E)

#3)
a = intSet()
a.insert(1)
a.insert(2)
a.insert(3)
a.insert(4)

b = intSet()
b.insert(1)
b.insert(4)
b.insert(5)
b.insert(7)

"""
######################################
# ou avec une boucle for mais avec des chiffres random

a = intSet()
b = intSet()
# on vérifie que la taille est bien égale à 4 élements dans notre liste
while (len(a) and len(b)) != 4:
    for i in range(4):
        k = random.randint(0,9)
        l = random.randint(0,9)
        a.insert(k)
        b.insert(l)

print(a)
print(b)

# d'une autre manière 

for i in range (4):
    a.insert(random.randint(1,9,1))
    b.insert(random.randint(1,9,1))

print(a)
print(b)
###################################
"""




c = a.intersect(b)
print(c)


# même ensembles a et b pour la question 4

d = a.union(b)
print(d)

# idem pour la question 5

e = a.diff(b)
print(e)

# question diff_sym

f = a.diff_sym(b)
print(f)

# question diff_sym_bis

g = a.diff_sym_bis(b)
print(g)



###############################################################################################
###############################################################################################



""" Exercice 2 """


from math import *


class Complex(object):
    """ Create Complex class"""

    def __init__(self, n1, n2, b):
        #Create complex number either in cartesian or polar notaion
        self.bcart = b
        if self.bcart:
            self.real = n1
            self.imag = n2
        else:
            self.rho = n1
            self.theta = n2

    def __module__(self):
        #Compute the module
        if self.bcart:
            # return '%.7g' % sqrt(self.real**2 + self.imag**2)
            return sqrt(self.real**2 + self.imag**2)
        else:
            return self.rho

    def __str__(self):
        if self.bcart:
            return str(self.real) + ' + i(' + str(self.imag) + ')'
        else:
            return str(self.rho) + '.exp(i.' + str(self.theta) + ')'

    def to_polaire(self):
        if self.bcart:
            self.rho = self.__module__()
            self.theta = atan2(self.imag, self.real)
            self.bcart = False
        return self
    
    def to_cartesien(self):
        if not self.bcart:
            self.real = self.rho * cos(self.theta)
            self.imag = self.rho * sin(self.theta)
            self.bcart = True
        return self

    def convert(self, b):
        if b:
            return self.to_cartesien()
        else:
            return self.to_polaire()

    def copy(self):
        if self.bcart:
            return Complex(self.real, self.imag, self.bcart)
        else:
            return Complex(self.rho,self.theta,self.bcart)

    def __add__(self, other):
        """ Add 2 complex numbers """
        c1 = self.copy()
        c2 = other.copy()
        c1.to_cartesien()  #conversion en coord cartesien
        c2.to_cartesien()
        return Complex(c1.real + c2.real,c1.imag + c2.imag,True).convert(self.bcart)

    def __sub__(self, other):
        """ Substract 2 complex numbers """
        c1 = self.copy()
        c2 = other.copy()
        c1.convert(True)  #conversion en coord cartesien
        c2.convert(True)
        return Complex(c1.real - c2.real,c1.imag - c2.imag,True).convert(self.bcart)

    def __mul__(self, other):
        """ Multiply 2 complex numbers """
        c1 = self.copy()
        c2 = other.copy()
        c1.convert(True)  #conversion en coord cartesien
        c2.convert(True)
        return Complex(c1.real*c2.real - c1.imag*c2.imag,c1.imag*c2.real + c1.real*c2.imag,True).convert(self.bcart)
        
    def __div__(self, other):
        """ Divide 2 complex numbers """
        c1 = self.copy()
        c2 = other.copy()
        c1.convert(True)  #conversion en coord cartesien
        c2.convert(True)
        den = float((c2.real)**2+(c2.imag)**2)       # computes the denominator
        return Complex((c1.real*c2.real + c1.imag*c2.imag)/den,(c2.real*c1.imag - c1.real*c2.imag)/den,True).convert(self.bcart)



""" Zone de test des fonctions """
########### Test Area ############
z = "Complexe = "
a = Complex(1,1,True)
print(z,a)

b = Complex(2,-1,True)
print(z,b)

c = Complex(1,1.0,False)
print("Complexe en notation exponentielle = ", c)

#print("Addition de 2 complexex = ",a+b)
print("Addition de 2 complexex = ",a.__add__(b))

#print("Soustraction de 2 complexes = ",a-b)
print("Soustraction de 2 complexes = ",a.__sub__(b))

#print("Multiplication de 2 complexes = ",a*b)
print("Multiplication de 2 complexes = ",a.__mul__(b))

print("Division de 2 complexes = ",a.__div__(b))

print("Module de a = ",a.__module__())

print("Conversion du complexe en notation polaire = ",b.to_polaire())

print("Conversion du complexe en notation cartesienne = ",c.to_cartesien())

#Conversion du complexe en cartesien ou polaire (True pour cartesien et False pour polaire)
print(b.convert(False))

########### End Test Area ############


###############################################################################################
###############################################################################################


""" Exercice 3 """

"""Coordinates of a point in a three-dimensional space x,y,z"""

from math import sqrt


class Vecteur(object):

    def __init__(self,x,y,z):
        self.x = x
        self.y = y
        self.z = z

    def __str__(self):
        return '<' + str(self.x) + ',' + str(self.y) + ',' + str(self.z) + '>'
    
    def __add__(self, other):
        return Vecteur(self.x + other.x,self.y + other.y,self.z + other.z)
    
    def __iadd__(self,other):
        # self = self + other
        self.x += other.x
        self.y += other.y
        self.z += other.z
        return self
    
    def mult_scalaire(self, k):
        return Vecteur(k*self.x,k*self.y,k*self.z)
    
    def norme(self):
        return sqrt(self.x**2 + self.y**2 + self.z**2)

    def normaliser(self):
        n = self.norme()
        self.x /=  n
        self.y /=  n
        self.z /=  n
        return self

    def produit_scalaire(self,other):
        return self.x*other.x+self.y*other.y+self.z*other.z

    def produit_vectoriel(self,other):
        return Vecteur(self.y*other.z-other.y*self.z, \
            self.z*other.x-self.x*other.z, \
            self.x*other.y-self.y*other.x)
    
    def est_orthogonal(self,other):
        return self.produit_scalaire(other)==0
    
    def est_colineaire(self,other):
        # Attention le 2eme vecteur ne doit pas contenir de vecteur nul
        return self.x/other.x==self.y/other.y==self.z/other.z





########## Test Area ##########

a = Vecteur(1,2,3)
b = Vecteur(2,3,4)
c = Vecteur(0,0,0)
d = Vecteur(1,1,1)

print(a,"+",b,"=",a+b)
print(3,"*",a,"=",a.mult_scalaire(3))
print(a,"+=",b,"=",a.__iadd__(b))
print("|",a,"|=",a.norme())
print("Nomaliser(",a,")=",a.normaliser())
a = Vecteur(1,2,3)
print(a,".",b,"=",a.produit_scalaire(b))
print(a,"^",b,"=",a.produit_vectoriel(b))
print(a.est_orthogonal(b))
print(a.est_orthogonal(c))
print(d.est_colineaire(d))


########## End Test Area #########

###############################################################################################
###############################################################################################

######################################### Fin du TP 4 #########################################
