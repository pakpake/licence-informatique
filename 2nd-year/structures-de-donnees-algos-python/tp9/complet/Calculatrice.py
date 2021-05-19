from arbre import Arbre

""" Exercice 2 : Question 2 """

class Calculatrice(Arbre):
    """ Permet de créer un arbre représentant l'expression (1+2)×3+(4×5). On peut remarquer que les nombres doivent être contenus dans les feuilles et les opérateurs dans les noeuds/racines non-feuilles """

    def affiche_infixe_p(self):
        """ Surchage de la méthode pour ajouter des parenthèses """
        if self.isVide():
            return ""
        else:
            ch = str(self.valRacine())
            if self.isFeuille():
                return ch
            else:
                if self.hasGauche():
                    if self.hasDroit():
                        return '(' + self.getGauche().affiche_infixe_p() + ch + self.getDroit().affiche_infixe_p() + ')'
                    else:
                        return self.getGauche().affiche_infixe_p() + ch
                else:
                       return ch + self.getDroit().affiche_infixe_p()

    def hauteur_nb(self):
        """ retourne la hauteur et le nombre de noeuds sous forme de tuple """
        return (self.hauteur(),self.nb_noeuds())

    def evaluation(self):
        """ évalue l'expression donnée en paramètre en récursif """
        if self.hasDroit() and self.hasGauche() and self.valRacine() == '+':
            return self.getDroit().evaluation() + self.getGauche().evaluation()
        elif self.hasDroit() and self.hasGauche() and self.valRacine() == '-':
            return self.getDroit().evaluation() - self.getGauche().evaluation()
        elif self.hasDroit() and self.hasGauche() and self.valRacine() == '*':
            return self.getDroit().evaluation() * self.getGauche().evaluation()
        elif self.hasDroit() and self.hasGauche() and self.valRacine() == '/':
            return self.getDroit().evaluation() / self.getGauche().evaluation()
        else:
            return self.valRacine()
    

if __name__ == "__main__":
    """ Zone de test pour la calculatrice """
    e1 = Calculatrice(Noeud(1),None,None)
    e2 = Calculatrice(Noeud(2),None,None)
    e3 = Calculatrice(Noeud(3),None,None)
    e4 = Calculatrice(Noeud(4),None,None)
    e5 = Calculatrice(Noeud(5),None,None)

    e6 = Calculatrice(Noeud('+'),e1,e2)
    e7 = Calculatrice(Noeud('*'),e6,e3)
    e8 = Calculatrice(Noeud('*'),e4,e5)
    e9 = Calculatrice(Noeud('+'),e7,e8)
    
    print("Notation polonaise inverse = ",e9.affiche_postfixe())
    print("Représentation de l'expression demandée = ",e9.affiche_infixe_p())

    print("Représentation de l'expression demandée (sans parenthèses) = ",e9.affiche_infixe())

    print("hauteur et nombre de noeuds = ",e9.hauteur_nb())

    print("Evaluation de l'expression ",e9.affiche_infixe_p()," = ",e9.evaluation())