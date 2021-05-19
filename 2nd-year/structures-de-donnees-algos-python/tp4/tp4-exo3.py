from math import sqrt
"""Coordinates of a point in a three-dimensional space x,y,z"""


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


########## End Test Area ##########