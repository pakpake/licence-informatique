# -*- coding: utf-8 -*-
"""
Created on Fri Mar 30 12:22:41 2018

@author: pakpake 
"""
#!/usr/bin/env python
# encoding: utf-8


class Noeud:
    def __init__(self, v):
        self.valeur = v

    def getValeur(self):
        """ valeur du noeud """
        return self.valeur
    
class Arbre:
    
    def __init__(self, n, g, d):
        #### attributs prives
        self.racine = n   #  racine de l'arbre
        self.gauche = g
        self.droit = d
    
    def getRacine(self):
        """ renvoie la racine de l'arbre """
        return self.racine

    def valRacine(self):
        """ renvoie la valeur de la racine de l'arbre """
        return self.racine.getValeur()

    def getGauche(self):
        """ renvoie le fils gauche de l'arbre """
        return self.gauche
    
    def getDroit(self):
        """ renvoie le fils droit de l'arbre """
        return self.droit

    def isVide(self):
        """ renvoie vrai si l'arbre est vide """
        return self.racine == None 
    
    def isFeuille(self):
        """ renvoie vrai si l'arbre est une feuille """
        return self.gauche == self.droit == None
    
    def hasGauche(self):
        """ renvoie vrai si l'arbre a une branche gauche """
        return self.gauche != None
 
    def hasDroit(self):
        """ renvoie vrai si l'arbre a une branche droite """
        return self.droit != None
    
    def hauteur(self):
        """ calcul de la profondeur par défaut  (càd en partant de 0 pour les feuilles) """
        if self.isVide():
            return 0
        elif self.isFeuille():
            return 0
        else:
            if self.hasGauche():
                if self.hasDroit():
                    return 1+max(self.getGauche().hauteur(), self.getDroit().hauteur())
                else:
                    return  1+self.getGauche().hauteur()
            else:
                return  1+self.getDroit().hauteur()

    def hauteur1(self):
        """ calcul la hauteur de l'arbre en partant de 1 pour les feuilles """
        if self.isVide():
            return 0
        elif self.isFeuille():
            return 1
        else:
            if self.hasGauche():
                if self.hasDroit():
                    return 1+max(self.getGauche().hauteur1(), self.getDroit().hauteur1())
                else:
                    return  1+self.getGauche().hauteur1()
            else:
                return  1+self.getDroit().hauteur1()
            
    def affiche_prefixe(self):
        """ affiche l'arbre dans le parcours préfixé : RacineGaucheDroite """
        if self.isVide():
            return ""
        else:
            ch = str(self.valRacine())
            if self.isFeuille():
                return ch
            else:
                if self.hasGauche():
                    if self.hasDroit():
                        return ch + self.getGauche().affiche_prefixe() + self.getDroit().affiche_prefixe()
                    else:
                        return ch + self.getGauche().affiche_prefixe()
                else:
                       return ch + self.getDroit().affiche_prefixe()

    def affiche_postfixe(self):
        """ affiche l'arbre dans le parcours préfixé : GDR """
        if self.isVide():
            return ""
        else:
            ch = str(self.valRacine())
            if self.isFeuille():
                return ch
            else:
                if self.hasGauche():
                    if self.hasDroit():
                        return self.getGauche().affiche_postfixe() + self.getDroit().affiche_postfixe() + ch
                    else:
                        return self.getGauche().affiche_postfixe() + ch
                else:
                       return self.getDroit().affiche_postfixe() + ch

    def affiche_infixe(self):
        """ affiche l'arbre dans le parcours infixé : GRD """
        if self.isVide():
            return ""
        else:
            ch = str(self.valRacine())
            if self.isFeuille():
                return ch
            else:
                if self.hasGauche():
                    if self.hasDroit():
                        return self.getGauche().affiche_infixe() + ch + self.getDroit().affiche_infixe()
                    else:
                        return self.getGauche().affiche_infixe() + ch
                else:
                       return ch + self.getDroit().affiche_infixe()

    def nb_noeuds(self):
        """ Calcule le nombre de noeuds d'un arbre """
        if self.isVide(): # si l'arbre est vide, nb = 0
            return 0
        elif self.isFeuille(): # si c'est une feuille, nb = 1
            return 1
        else:
            if self.hasGauche():
                if self.hasDroit(): # il a deux fils
                    return 1 + self.getGauche().nb_noeuds() + self.getDroit().nb_noeuds()
                else: # il n'a qu'un fils gauche
                    return 1 + self.getGauche().nb_noeuds()
            else: # il n'a qu'un fils droit
                return 1 + self.getDroit().nb_noeuds()

    def somme(self):
        """ Calcule la somme des noeuds d'un arbre """
        if self.isVide(): # si l'arbre est vide, somme = 0
            return 0
        elif self.isFeuille(): # si c'est une feuille, somme = la valeur de la feuille
            return self.valRacine()
        else:
            if self.hasGauche():
                if self.hasDroit(): # il a deux fils
                    return self.valRacine() + self.getGauche().somme() + self.getDroit().somme()
                else: # il n'a qu'un fils gauche
                    return self.valRacine() + self.getGauche().somme()
            else: # il n'a qu'un fils droit
                return self.valRacine() + self.getDroit().somme()

    def incremente(self):
        """ Incremente chaque valeur de 1 """
        if self.isVide(): # si l'arbre est vide, on ne fait rien
            pass
        elif self.isFeuille(): # si c'est une feuille, on augmente la valeur de 1
            self.racine.valeur += 1
        else:
            if self.hasGauche():
                if self.hasDroit(): # il a deux fils
                    self.racine.valeur += 1
                    self.getGauche().incremente()
                    self.getDroit().incremente()
                else: # il n'a qu'un fils gauche
                    self.racine.valeur += 1
                    self.getGauche().incremente()
            else: # il n'a qu'un fils droit
                self.racine.valeur += 1
                self.getDroit().incremente()
 
if __name__ == "__main__":     
    # execute only if run as a script
    
    ### 
    """ Exercice 1"""

    """ Question 1 """
    aVide = Arbre(None,None,None)
    print(aVide.isVide())

    """ Question 2 """

    # creation de feuilles
    a0 = Arbre(Noeud(0), None, None)
    a2 = Arbre(Noeud(2),None,None)
    a4 = Arbre(Noeud(4), None, None)
    # creation de sous arbres 
    a1 = Arbre(Noeud(1), a0,a2)     
    a5 = Arbre(Noeud(5), a4, None)     
    a3 = Arbre(Noeud(3),a1,a5)

    # Valeur de la racine
    print(a3.valRacine())
    print(a3.getRacine().getValeur())
    print(a3.getRacine().valeur)

    # Valeur a droite de la racine
    print(a5.valRacine())   
    print(a3.getDroit().valRacine())

    # Test du sous arbre a5
    print(a5.isVide())
    print(a5.hasDroit())

    # Test des feuilles
    print(a4.isFeuille())

    # Test Affiche Prefixe
    print(a3.affiche_prefixe())
    print(a1.affiche_prefixe())

    # Test hauteur
    print()
    print("Hauteur de a3",a3.hauteur())
    print("Hauteur de a4",a4.hauteur())
    print("Hauteur de a5",a5.hauteur())

    # Test hauteur
    print()
    print("Hauteur1 de a3",a3.hauteur1())
    print("Hauteur1 de a4",a4.hauteur1())
    print("Hauteur1 de a5",a5.hauteur1())

    # Test Nb Noeuds
    print()
    print("Nombre de noeuds de a3",a3.nb_noeuds())
    print("Nombre de noeuds de a2",a2.nb_noeuds())
    print("Nombre de noeuds de a5",a5.nb_noeuds())
    
    # Test de Somme
    print()
    print("Somme des noeuds de a3",a3.somme())
    print("Somme des noeuds de a2",a2.somme())
    print("Somme des noeuds de a5",a5.somme())
    
    # Test de incremente
    print()
    print("Affiche Prefixe de a3",a3.affiche_prefixe())
    print("On incremente a3")
    a3.incremente()
    print("Somme des noeuds de a3",a3.somme())
    print("Affiche Prefixe de a3",a3.affiche_prefixe())
    
    """ Question 3 """

    # Test de affiche postfixe
    print("Affiche Postfixe de a3",a3.affiche_postfixe())

    # Test de affiche infixe
    print("Affiche Infixe de a3",a3.affiche_infixe())