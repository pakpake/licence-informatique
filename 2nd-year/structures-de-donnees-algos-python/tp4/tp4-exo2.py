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