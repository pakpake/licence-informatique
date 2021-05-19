class Liste:
    def __init__(self):
        """
        :sortie : self
        :post-cond: self est une liste vide
        """
    def est_liste_vide(self):
        """
        :entree : self
        :sortie v: bool
        :renvoie True si et seulement si self est vide
        """
    def tete(self):
        """
        :entree : self
        :sortie : object
        :pre-cond: self n'est pas vide
        :renvoie le premier element de la liste
        """
    def premiere_valeur(self):
        """
        :entree : self
        :sortie : object
        :pre-cond: self n'est pas vide
        :renvoie la valeur du premier element de la liste
        """
    def insere_tete(self, v):
        """
        :entree/sortie : self
        :entree v : object
        :insere v en tete de liste
        """



class Cellule():
    def __init__(self):
        self.data = object
        self.suivant = None


class Liste:

    def __init__(self):
        self.mpremier = None   # Premier element de la liste
        self.taille = 0         #taille de la liste

    def est_liste_vide(self):
        v = (self.mpremier == None)
        return v
        
    def tete(self):
        if self.mpremier == None:
            print("liste vide")
        else:
            p = self.mpremier
            return p

    def premiere_valeur(self):
        if self.mpremier == None:
            print("liste vide")
        else:
            s = self.mpremier.data
            return s
    
    def insere_tete(self, v):
        m = Cellule()
        m.data=v
        if self.mpremier == None:
            m.suivant = None
        else:
            m.suivant=self.mpremier
        self.mpremier = m
        self.taille += 1


