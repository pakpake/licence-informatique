""" Exercice 2 """
from math import *


class Complex(object):
    """ Create Complex class"""

    #q°1)
    def __init__(self, real, imag):
        """ Instanciate complex number """
        self.real = real
        self.imag = imag

    def __add__(self, self2):
        """ Add 2 complex numbers """
        return Complex(self.real + self2.real,self.imag + self2.imag)

    def __sub__(self, self2):
        """ Substract 2 complex numbers """
        return Complex(self.real - self2.real,self.imag - self2.imag)

    def __mul__(self, self2):
        """ Multiply 2 complex numbers """
        return Complex(self.real*self2.real - self.imag*self2.imag,self.imag*self2.real + self.real*self2.imag)

    def __div__(self, self2):
        """ Divide 2 complex numbers """
        den = self2.real**2+self2.imag**2       # computes the denominator
        return Complex((self.real*self2.real + self.imag*self2.imag)/den,(self2.real*self.imag - self.real*self2.imag)/den)

    def __module__(self):
        """ Compute the module """
        return '%.7g' % sqrt(self.real**2 + self.imag**2)

    def __str__(self):
        return str(self.real) + ' + i(' + str(self.imag) + ')'

#######################
# Test Area
a = Complex(1,1)
print(a)

b = Complex(2,-1)
print(b)

print(a.__add__(b))
print(a.__sub__(b))
print(a.__mul__(b))
print(a.__div__(b))
print(a.__module__())

#######################

"""
# q° 2)

    def __init__(self, n1, n2, bcart):
        #Create complex number either in cartesian or polar notaion
        if bcart:
            self.real = n1
            self.imag = n2
        else:
            self.rho = n1
            self.theta = n2

#######


"""