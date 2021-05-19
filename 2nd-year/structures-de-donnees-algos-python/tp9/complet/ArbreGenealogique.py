from arbre import Arbre

""" Exercice 2 """

class ArbreGenealogique(Arbre):
    """ classe ArbreGenealogique qui hérite de la classe Arbre """
    def affiche_petits_enfants(self):
        if not self.isVide():
            ch = ""
            if self.hasGauche():
                if self.getGauche().hasGauche():
                    ch += self.getGauche().getGauche().valRacine()
                if self.getGauche().hasDroit():
                    ch += self.getGauche().getDroit().valRacine()
            if self.hasDroit():
                if self.getDroit().hasGauche():
                    ch += self.getDroit().getGauche().valRacine()
                if self.getDroit().hasDroit():
                    ch += self.getDroit().getDroit().valRacine()
        return ch


if __name__ == "__main__":
    
    """ Question 1 """
    # création de l'arbre généalogique
    # j'ai rajouté un espace aux noms des noeuds pour l'affichage

    #### ne pas oublier de compiler arbre.py avant d'executer ces exemples ####

    A0 = ArbreGenealogique(Noeud("Pierre "),None,None)
    A1 = ArbreGenealogique(Noeud("Romain "),None,None)
    A2 = ArbreGenealogique(Noeud("Philippe "),A0,A1)
    A3 = ArbreGenealogique(Noeud("Xavier "),None,None)
    A4 = ArbreGenealogique(Noeud("Michel "),A2,A3)
    A5 = ArbreGenealogique(Noeud("Vincent "),None,None)
    A6 = ArbreGenealogique(Noeud("Olivier "),None,None)
    A7 = ArbreGenealogique(Noeud("Claude "),A5,A6)
    A8 = ArbreGenealogique(Noeud("Louis "),A4,A7)

    print("Les petits enfants de ",A8.valRacine(),"sont : ")
    print("=> ",A8.affiche_petits_enfants())

    print()
    print("L'arbre généalogique contient en ordre préfixé")
    print(A8.affiche_prefixe())
