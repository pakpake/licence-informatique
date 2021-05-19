# coding: utf-8
from liste import Liste




class TabCell():
    """Élément d'une table d'asso de la classe `Table`."""
    
    def __init__(self, key, val):
        """Création d'une instance de cellule de table d'asso.
         `key` doit être exister (et de type "hachable" en théorie)."""
        if not key:
            # on pourrait aussi tester si le type de la clé est utilisable (hachable) comme entrée.
            print("ERREUR : `key` doit être valide.")
            raise ValueError()
        else:
            self.key = key
            self.val = val
    
    def __str__(self):
        return str(self.key) + "=>" + str(self.val)
    
    def __eq__(self, key):
        "Retourne val si key correspond, None sinon."
        if self.key == key:
            return self.val
        else:
            return None
        
    def supprime(self):
        """En Python, si un objet n'est plus référencé alors le ramasse miètes va le supprimer de la mémoire.
        Dans d'autres langages, il faut supprimer explicitement l'objet (libérer la mémoire occupée)."""
        self.key = None
        self.val = None



c = TabCell(1, 'a')
print(c)
print(c == 1)
print(c == 0)


c.supprime()

class Table():
    """Table d'association simulée avec une liste."""
    
    def __init__(self, autre=None):
        """Création d'une instance de table d'asso.
        Possibilités pour `arg` :
         - None : crée une table vide (défaut)
         - TabCell : crée une table avec une cellule déja "peuplée"
         - Table : crée une copie de la table
        """
        self.liste = Liste()  # dans tous les cas on crée la liste
        if isinstance(autre, TabCell):
            self.liste.insere_tete(autre)
        elif isinstance(autre, Table):
            autre.liste.filter(lambda x: self.liste.insere_tete(x))

    def taille(self):
        "Retourne le nombre d'éléments de la table."
        return self.liste.length()
    
    def est_vide(self):
        "Retourne un booléen indiquant si la table est vide ou pas."
        return self.liste.est_liste_vide()
    
    def cles(self):
        "Retourne la liste des clés de la table."
        res = Liste()
        for pos in range(self.liste.length()):
            res.insere_tete(self.liste.get_at(pos).key)
        return res
    
    def valeurs(self):
        "Retourne la liste des valeurs de la table."
        res = Liste()
        self.liste.filter(lambda x: res.insere_tete(x.val))
        return res
        
    def __str__(self):
        "Affichage du contenu de la table"
        if self.est_vide():
            return 'table vide'
        else:
            return str(self.liste)
    
    def ajout(self, key, val):
        "Ajout une cellule."
        
        # FIXME ajout d'une clé déjà présente ?
        
        self.liste.insere_tete(TabCell(key, val))
        
    def present(self, key):
        "Recherche la clé `key` dans la table. Retourne la valeur associée ou bien `None` si absente."
        res = self.liste.filter(lambda x: x == key)
        if not res.est_liste_vide():
            return res.get_at(0).val
        else:
            return None
    
    def supprime(self, key):
        """Supprime l'entrée correspondant à key en retournant sa valeur. Retourne `None` si absente.
        Pourrait être trivial si Liste avait une fonction de recherche d'indice (qui retourne la 
        position/indice de la donnée recherchée ou None si absente).
        Mais sans cela, il faut manipuler la structure interne de self.liste depuis Table ce qui
        n'est pas une bonne chose."""
        pass




t = Table()
print(t, '  ', t.est_vide(), '  ', t.taille())




t.ajout('a', 100)
t.ajout('b', 300)
print(t, '  ', t.est_vide(), '  ', t.taille())




res = t.present('a')
print(res)




res = t.present('toto')
print(res)




print(t.cles())




print(t.valeurs())









t1 = Table(TabCell('z', 1000))
print(t1, '  ', t1.est_vide(), '  ', t1.taille())









t2 = Table(t1)
print(t2, '  ', t2.est_vide(), '  ', t2.taille())




t2 is t1






