from arbre import Arbre

class ABR(Arbre):
    """ classe Arbre Binaire de Recherche """
    
    def ajoute(self, n):
        """ permet d'ajouter un noeud n dans un ABR """
        if self.isVide():
            # cas où l'arbre est vide
            return self.__init__(n,None,None)
        else:
            if n.getValeur() < self.valRacine():
                # on insère à gauche
                if self.hasGauche():
                    # on récupère le membre de gauche et on appel en récursif ajoute()
                    self.getGauche().ajoute(n)
                else:
                    # on créer le nouveau ABR
                    tmp = ABR(n,None,None)
                    # on le raccroche a l'ABR existant
                    self.gauche = tmp
            else:
                if self.hasDroit():
                    self.getDroit().ajoute(n)
                else:
                    tmp = ABR(n,None,None)
                    self.droit = tmp

    def affiche_infixe(self):
        """ affiche l'ABR dans le parcours infixé : GRD """
        if self.isVide():
            return ""
        else:
            ch = str(self.valRacine())
            if self.isFeuille():
                return ch + " "
            else:
                if self.hasGauche():
                    if self.hasDroit():
                        return self.getGauche().affiche_infixe() + ch + " " + self.getDroit().affiche_infixe()
                    else:
                        return self.getGauche().affiche_infixe() + ch+ " "
                else:
                       return ch + " " + self.getDroit().affiche_infixe()
    
    def construit_abr(self, l):
        if not self.isVide():
            print("construit_abr : l'arbre n'est pas vide ")
        else:
            for i in l:
                self.ajoute(Noeud(i))
            
    def inf_abr(self,x):
        """ teste si l'ensemble des noeuds de l'arbre est inférieur à x """
        if self.isVide():
            print("L'arbre est vide !")
        else:
            # le plus grand nombre se trouve sur la feuille la plus à droite
            tmp = self
            while tmp.hasDroit():
                tmp = tmp.getDroit()
            return tmp.valRacine() <= x

    def sup_abr(self,x):
        """ teste si l'ensemble des noeuds de l'arbre sont supérieur à x """
        if self.isVide():
            print("L'arbre est vide !")
        else:
            tmp = self
            # le plus petit nombre se trouve sur la feuille la plus à gauche
            while tmp.hasGauche():
                tmp = tmp.getGauche()
            return tmp.valRacine()

    def test_abr(self):
        """ Teste si l'arbre est un ABR """
        if self.isVide():
            print("L'arbre est vide !")
        else:
            if self.hasGauche():
                if self.getGauche().valRacine() < self.valRacine():
                    return self.getGauche().test_abr()
                else:
                    return False        # ce n'est pas un ABR
            elif self.hasDroit():
                if self.getDroit().valRacine() >= self.valRacine():
                    return self.getDroit().test_abr()
                else:
                    return False
            else:
                return True     # c'est un ABR
    
    def recherche_abr(self,v):
        """ recherche la valeur v dans l'arbre et renvoie le noeud-racine contenant cette valeur """
        if self.isVide():
            print("L'arbre est vide !")
        else:
            if self.isFeuille():
                if self.valRacine() == v:
                    return self
                else:
                    return None
            else:
                if self.valRacine() == v:
                    return self
                else:
                    if v < self.valRacine():
                        if self.hasGauche():
                            return self.getGauche().recherche_abr(v)
                        else:
                            return None
                    else:
                        if self.hasDroit():
                            return self.getDroit().recherche_abr(v)
                        else:
                            return None
                            
    def supprime_abr(self,v):
        """ supprime le noeud contenant la valeur v de l'arbre """
        if self.isVide():
            print("L'arbre est vide !")
        else:
            # si la valeur rechrchée est < à la racine et qu'on a une branche gauche
            if v < self.valRacine() and self.hasGauche():
                # on renvoie l'arbre résultant de la suppression de v dans la branche gauche
                return self.getGauche().supprime_abr(v)
                # sinon si v > à racine et branche droite, 
            elif v > self.valRacine() and self.hasDroit():
                # on renvoie l'arbre résultant de la suppression de v dans la branche droite
                return self.getDroit().supprime_abr(v)
            else:
                # on est dans le cas où v est égal à la racine
                if self.isFeuille():
                    # si c'est une feuille, on supprime la racine
                    return None
                if not self.hasGauche():
                    # si elle n'a pas de branche gauche
                    # on renvoie la branche droite
                    return self.getDroit()
                if not self.hasDroit():
                    # et inversement
                    return self.getGauche()
                # dans les autres cas, il faut trouver la valeur la plus proche de v
                # dans les sous arbres, la supprimer et la remplacer à la racine
                pass


    def meme_valeurs(self,a):
        """ teste si deux arbres binaires de recherche contiennent exactement les mêmes valeurs (mais pas forcément organisées de la même manière)  """
        if self.isVide() and a.isVide():
            return True
        elif self.nb_noeuds() != a.nb_noeuds():
            return False
        else:
            ch1 = self.affiche_infixe()
            ch2 = a.affiche_infixe()
            return ch2==ch1

if __name__ == "__main__":
    
    """ Question 1 : test de ajoute """
    print()
    print("---------------------------------------------------------------------")
    print("Question 1")
    print()

    import random

    a = ABR(None,None,None)
    print("affichage de l'arbre a vide :",a.affiche_infixe())

    a.ajoute(Noeud(10))
    print("on a ajouté le Noeud(10) : ",a.affiche_infixe())
    print("Le nombre de noeuds est : ",a.nb_noeuds())

    # test de la fonction ajoute
    a.ajoute(Noeud(5))
    print("on a ajouté le noeud(5) : ",a.affiche_infixe())

    for i in range(10):
        a.ajoute(Noeud(random.randint(0,20)))

    print("on a ajouté 10 noeuds aléatoire supplémentaires : ",a.affiche_infixe())

    """ Question 2 """
    print()
    print("---------------------------------------------------------------------")
    print("Question 2")
    print()
    b = ABR(None,None,None)

    l = []
    for i in range(10):
        l.append(random.randint(50,100))
    print("notre liste est : ",l)
    
    print("création de l'arbre b vide : ",b.isVide())
    b.construit_abr(l)
    print("affichage de l'arbre b : ",b.affiche_infixe())

    # ce nouvel appel doit declencher une exception
    b.construit_abr(l)

    """ Question 3 """
    print()
    print("---------------------------------------------------------------------")
    print("Question 3")
    print()

    print("Si la liste est triée par ordre croissant (resp. décroissant), la construction de l'arbre ne sera pas équilibrée, et tous les noeuds seront dans la branche droite (resp. gauche).")
    print()

    """ Question 4 """
    print()
    print("---------------------------------------------------------------------")
    print("Question 4")
    print()

    x = 98
    print("l'arbre contient : ",b.affiche_infixe())
    print("Est ce que l'ensemble des noeuds de l'arbre est inférieur à ",x,": ",b.inf_abr(x))

    x = 52
    print("Est ce que l'ensemble des noeuds de l'arbre est supérieur à ",x,": ",b.inf_abr(x))

    """ Question 5 """
    print()
    print("---------------------------------------------------------------------")
    print("Question 5")
    print()

    print("l'arbre contient : ",b.affiche_infixe())
    print("l'arbre est un ABR ? ",b.test_abr())

    # avec un arbre non-ABR
    c0 = ABR(Noeud(0), None, None)
    c2 = ABR(Noeud(2),None,None)
    c4 = ABR(Noeud(4), None, None)
    c1 = ABR(Noeud(1), c2,c0)     
    c5 = ABR(Noeud(5), c4, None)     
    c = ABR(Noeud(3),c5,c1)

    print("l'arbre contient : ",c.affiche_infixe())
    print("l'arbre est un ABR ? ",c.test_abr())
    print()

    """ Question 6 """
    print("---------------------------------------------------------------------")
    print()
    print("Question 6")
    print()
    d = ABR(None,None,None)

    l = []
    for i in range(10):
        l.append(random.randint(50,60))
    print()
    print("notre liste est : ",l)
    
    print("création de l'arbre d vide : ",d.isVide())
    d.construit_abr(l)
    print("affichage de l'arbre d : ",d.affiche_infixe())

    v = 55
    n = d.recherche_abr(v)
    print("recherche de la valeur",v,"dans l'arbre d : ",n!=None)
    print()

    e0 = ABR(Noeud(5),None,None)
    e1 = ABR(Noeud(13),None,None)
    e = ABR(Noeud(10),e0,e1)

    print("arbre e0 = ",e0.affiche_infixe())
    print("on supprime 5 dans l'arbe e0")
    e0.supprime_abr(5)


    """ Question 7 """
    print("---------------------------------------------------------------------")
    print()
    print("Question 7")
    print()
    l =[]

    for i in range(10):
        l.append(i)

    # on remplit le 1er arbre avec une liste de nombres entre 0 et 9 mélangés aléatoirement
    random.shuffle(l)
    print("liste :",l)
    e1 = ABR(None,None,None)
    e1.construit_abr(l)
    print("1er ABR :",e1.affiche_infixe())
    # on construit un 2eme arbre avec la meme liste mais dans un ordre différent
    random.shuffle(l)
    print("liste :",l)
    e2 = ABR(None,None,None)
    e2.construit_abr(l)
    print("2eme ABR :",e2.affiche_infixe())
    print("Les 2 ABR sont identiques ? ",e1.meme_valeurs(e2))
