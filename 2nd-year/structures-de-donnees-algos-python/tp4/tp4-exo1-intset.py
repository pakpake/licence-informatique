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
    
    
    
    


""" Exercice 1 """
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

