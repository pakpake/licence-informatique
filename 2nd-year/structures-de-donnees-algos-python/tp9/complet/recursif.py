from arbre import Arbre

""" Exercice 2 : Question 3 """

class Recursif(Arbre):
    """ conception de fonctions récursives """
    def applique(self, f):
        """ applique la fonction f à toutes les valeurs de l'arbre """
        if not self.isVide():
            if self.isFeuille():
                self.getRacine().valeur = f(self.valRacine())
            else:
                self.getRacine().valeur = f(self.valRacine())
                if self.hasGauche():
                    self.getGauche().applique(f)
                if self.hasDroit():
                    self.getDroit().applique(f)
    
    def grand_chemin(self):
        """ renvoie la plus grande somme possible  """
        if not self.isVide():
            if self.isFeuille():
                return self.valRacine()
            else:
                if self.hasGauche():
                    if self.hasDroit():
                        return self.valRacine()+max(self.getGauche().grand_chemin(),self.getDroit().grand_chemin())
                    else:
                        return self.valRacine()+self.getGauche().grand_chemin()
                else:
                    return self.valRacine()+self.getDroit().grand_chemin()


if __name__ == "__main__":
    # creation de l'arbre
    a0 = Recursif(Noeud(0), None, None)
    a2 = Recursif(Noeud(2),None,None)
    a4 = Recursif(Noeud(4), None, None)

    a1 = Recursif(Noeud(1), a0,a2)     
    a5 = Recursif(Noeud(5), a4, None)     
    a3 = Recursif(Noeud(3),a1,a5)

    print(a3.affiche_prefixe())

    def f(x):
        return x+1

    print("applique la fonction f(x) à l'arbre = ")
    a3.applique(f)
    print(a3.affiche_prefixe())

    print("Grand chemin (avec la fonction f(x) appliquée) de a3 = ",a3.grand_chemin())