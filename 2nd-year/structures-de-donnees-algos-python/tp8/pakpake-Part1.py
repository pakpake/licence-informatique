""" Table d'association
Implementation par une liste chainee
Herite des methodes des listes chainees simples
Une cellule contient une donnee (data) et un lien vers la cellule suivante
Data est une TableCell qui contient une valeur (value) et une cle (key) """


""" Partie specification """

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
        return str(self.key) + "=>" + str(self.value)

    def __eq__(self, k):
        """ Retourne v si k correspond, None sinon. Indispensable pour la fonction get_elt """
        if self.key == k:
            return self.value
        else:
            return None


class Table():

    def __init__(self):
        # self.mpremier = None
        # self.taille = 0
        self.liste = Liste()
      
    def table_vide(self):
        return self.liste.est_liste_vide()
    
    def print_values(self):
        """ retourne la liste des valeurs de la table """
        res = Liste()
        self.liste.filter(lambda x: res.insere_tete(x.value))
        return str(res)

    def print_values2(self):
        """ retourne la liste des valeurs de la table (version sans la fonction lambda() """
        res = Liste()
        for pos in range(self.liste.length()):
            res.insere_tete(self.liste.get_at(pos).value)
        return str(res)
    
    def print_keys(self):
        """ retourne la liste des clés de la table """
        res = Liste()
        self.liste.filter(lambda x: res.insere_tete(x.key))
        return str(res)
    
    def print_table(self):
        """ affiche le contenu de la table """
        if self.table_vide():
            return 'table vide'
        else:
            return str(self.liste)
    
    def get(self,k):
        "Recherche la clé `k` dans la table. Retourne la valeur associée ou bien `None` si absente."
        res = self.liste.filter(lambda x: x == k)
        if not res.est_liste_vide():
            return res.get_at(0).value
        else:
            return None
    
    def put(self,k,v):
        """ change la valeur de l'element de clé k par v """
        elt = self.liste.filter(lambda x: x == k)
        if elt != None:
            elt.value = v

    def add(self,k,v):
        """ insere en tete de la table l'élément TableCell de clé k et de valeur v """
        self.liste.insere_tete(TableCell(k, v))
        
    def get_elt(self,k):
        """ retourne element de la table de cle k """
        return self.liste.filter(lambda x: x == k)

    def remove(self,k):
        """ cette fonction ne peut pas etre implémentée avec l'implémentaion actuelle de liste.py. En effet, il n'est pas possible de récupérer un pointeur sur l'élément à supprimer sans parcourir la liste. Or, en faisant appel à liste.py, avec un from ... import ..., on ne peut pas transgresser la règle d'encapsulation. Si on veut implémenter cette fonction, il faudrait ré-implémenter liste.py à l'intérieur de Table """
        pass

##################################################################

# creation de notre table
t = Table()

# insertion des elements dans la table
t.add(0,"toto")

t.print_table()

for i in range(15):
    t.add(i,"toto"+str(i))

t.print_table()

# affichage des valeurs et des clés
t.print_keys()
t.print_values()

# recherche de la clé 10 dans la table
t.get(10)

# change la valeur de la clé 10 par titi10
t.put(10,"titi"+str(10))

