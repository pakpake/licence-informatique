""" Table d'association

Implementation par une liste chainee
Herite des methodes des listes chainees simples
Une cellule contient une donnee (data) et un lien vers la cellule suivante
Data est une TableCell qui contient une valeur (value) et une cle (key) """


""" Partie specification """

""" Dans cette version, on se sert de la structure de liste.py par heritage """
from liste import Liste


class Table(Liste):
  
    def __init__(self):
        """
        :sortie : self
        :post-cond: self est une table vide
        """
    def table_vide(self):
        """:entree : self
        :sortie b : bool
        :post-cond : b est True ssi si self est vide
        """ 
    def print_values(self):
        """:entree : self
        :sortie : string
        :retourne la chaine des valeurs des elements de la table self
        """      
    def print_keys(self):
        """:entree : self
        :sortie : string
        :retourne la chaine des cles des elements de la table self
        """      
    def print_table(self):
        """
        :entree : self
        :sortie : string
        :retourne la liste des elements (cle, valeur) de la table self
        """        
    def get(self,k):
        """entrees : table self, cle k
        sortie : valeur de la cle k par v"""
    def put(self,k, v):
        """ entrees : table self, cle k, valeur v
        sortie : self dans laquelle on a remplace 
        la valeur de la cle k par v"""
    def add(self,k,v):
        """ entrees : table self, cle k, valeur v
        sortie : self dans laquelle on a insere en tete de la table
        l'élément TableCell de clé k et de valeur v"""
    def remove(self,k):
        """ entrees : table self, cle k
        sortie : self dans laquelle on a retire 
        l'element de cle k"""
    def get_elt(self,k):
        """
        :entrees : table self, cle k
        :sortie : element de la table de cle k"""


class TableCell():
    def __init__(self, k, v):
        """ Création d'une instance de cellule de table d'association """
        self.value = v
        self.key = k
    
    def __str__(self):
        """ fonction d'affichage de la cellule """
        return str(self.key) + " => " + str(self.value)

    def __eq__(self, k):
        """ Retourne v si k correspond, None sinon. Indispensable pour la fonction get_elt """
        if self.key == k:
            return self.value
        else:
            return None


class Table(Liste):
    """
        def __init__(self):
            " On appelle le constructeur de Liste directement, 
            Table a la meme structure que Liste "
            self.liste = Liste()
    """      
    def table_vide(self):
        return self.est_liste_vide()
    
    def print_values(self):
        """ retourne la liste des valeurs de la table """
        res = Liste()
        self.filter(lambda x: res.insere_tete(x.value))
        return str(res)
    
    def print_keys(self):
        """ retourne la liste des clés de la table """
        res = Liste()
        self.filter(lambda x: res.insere_tete(x.key))
        return str(res)
    
    def print_table(self):
        """ affiche le contenu de la table """
        if self.table_vide():
            return 'table vide'
        else:
            return str(self)
    
    def get(self,k):
        "Recherche la clé `k` dans la table. Retourne la valeur associée ou bien `None` si absente."
        res = self.filter(lambda x: x == k)
        if not res.est_liste_vide():
            return res.get_at(0).value
        else:
            return None
    
    def add(self,k,v):
        """ insere en tete de la table l'élément TableCell de clé k et de valeur v """
        self.insere_tete(TableCell(k, v))
        
    def put(self,k,v):
        """ change la valeur de l'element de clé k par v """
        courant = self.mpremier
        print(courant.data.value)
        while courant != None and courant.data.key != k: # on cherche k dans la liste
            courant = courant.suivant
        if courant != None: # on a trouve k
            courant.data.value = v

    def get_elt(self,k):
        """ retourne element de la table de cle k """
        return self.filter(lambda x: x == k)

    def remove(self,k):
        """ supprime l'élément de clé k """
        courant = self.mpremier
        if courant != None:     # on teste si la liste est vide
            # on traite à part le 1er element
            if courant.data.key == k:
                self.supprime_at(0)
            else:
                # si l'element a supprimer n'est pas en 1ere position
                # on avance dans la liste chainee en gardant le pointeur sur l'element precedent
                while courant.suivant != None and courant.suivant.data.key != k:
                    courant = courant.suivant
                if courant != None:
                    # on a trouve l'element a supprimer
                    courant.suivant = courant.suivant.suivant
        else:
            print("remove : Error empty list")            

##################################################################

# creation de notre table
t = Table()

# insertion des elements dans la table
t.add(0,"toto")

print(t.print_table())

for i in range(1,6):
    t.add(i,"toto"+str(i))

print(t.print_table())

# affichage des valeurs et des clés
print(t.print_keys())
print(t.print_values())

# recherche de la clé 10 dans la table
print(t.get(3))

# change la valeur de la clé 10 par titi10
t.put(3,"titi")

print(t.print_table())

# supprime l'element de clé 3 par exemple
t.remove(3)

print(t.print_table())

#############################################################################
#############################################################################

""" Exercice 2 """
import random

class Person():
    """ classe Person avec les attributs nom, prenom et numero de telephone """
    def __init__(self, n, p, t):
        self.nom = n
        self.prenom = p
        self.tel = t

# Création du répertoire
repertoire = Table()

# Création d'une instance de la classe Person
p1 = Person("","pakpake",1234567890)


# insertion des elements dans la table
repertoire.add(p1.nom+" "+p1.prenom,p1.tel)

# affichage du répertoire
print(repertoire.print_table())

# Création d'une sélection de numéros aléatoires
l = []
for i in range(9):
    l.append(random.randint(1,9))

# Remplissage du répertoire
for i in range(1,5):
    # création d'un numéro aléatoire
    random.shuffle(l)
    num = '0'+''.join([str(e) for e in l])
    p1 = Person("Nom["+str(i)+"]","Prenom["+str(i)+"]",num)
    repertoire.add(p1.nom+" "+p1.prenom,p1.tel)

print(repertoire.print_table())

# affichage des valeurs et des clés
print(repertoire.print_keys())
print(repertoire.print_values())

# recherche de la clé  pakpake dans la table
print(repertoire.get(" pakpake"))

# change la valeur de la clé  pakpake par 0567890123
repertoire.put(" pakpake","0567890123")

print(repertoire.print_table())

# supprime l'element de clé 3 par exemple
repertoire.remove("Nom[3] Prenom[3]")

print(repertoire.print_table())