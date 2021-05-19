class Noeud:
    def __init__(self,x):
        self.valeur = x
        self.vide = False
        self.suivant = None
    

class Liste:
    def __init__(self):
        self.premier = None
        self.taille = 0

    def supprime(self,x):
        # supprime de la liste toutes les occurences de x
        m = self.premier # on parcourt la liste en partant du 1er element
        while m != None:
            if m.valeur == x:
                m.vide = True
            m = m.suivant

    def longueur(self):
        # renvoie la longueur d'une liste a trous
        m = self.premier # on parcourt la liste en partant du 1er element
        m.taille = 0 # on redefini la taille de la liste a 0 (inutile)
        while m != None:
            if m.vide == False:
                m.taille += 1
            m = m.suivant
        return m.taille


    """
    # Question 3
    La complexité de la fonction longueur dépend du nombre total de noeud dans la liste, ceux qui doivent et ne doivent pas être ignorées y compris.
    Une situation dans laquelle il serait très long de calculer la longueur serait par exemple une très grosse liste, où il y aurait plein de noeuds marqués comme supprimés.
    """

    def insere(self,x):
        m = self.premier
        while m != None:
            if 