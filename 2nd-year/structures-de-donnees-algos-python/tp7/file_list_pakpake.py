# -*- coding: utf-8 -*-
"""
@date: Mars 2020
@author: pakpake 
"""

######## Spécification ########

class File_List:

    def __init__(self):
        """
        :sortie self:
        :post-cond: self est une file vide
        """

    def est_vide(self):
        """
        :entrée self:
        :sortie v: bool
        :post-cond: v est True si et seulement si self est vide
        """

    def est_pleine(self):
        """
        :entrée self:
        :sortie p: bool
        :post-cond: v est True si et seulement si self est pleine
        """

    def ajoute_en_queue(self, e):
        """
        :entrée/sortie self:
        :entrée e: object
        :pré-cond: self n'est pas pleine
        :post-cond: e est ajouté en queue de self
        """

    def tete(self):
        """
        :entrée self:
        :sortie t: object
        :pré-cond: self n'est pas vide
        :post-cond: e est l'élément en tête de self
        """

    def retire_tete(self):
        """
        :entrée/sortie self:
        :pré-cond: self n'est pas vide
        :post-cond: l'élément stocké en tête de self est retiré
        """

######## Implémentation ########


class Cellule (): 
    def __init__(self):
        # initialisation de l'objet cellule
        self.valeur = object 
        self.suivant = None

class File_List(object):
    """ 
    :entree/sortie : self 
    :creation d’une file vide 
    """
    def __init__(self, tailleMax): # attributs prives
        self.mtete = None # element de tete
        self.mqueue = None # element de queue
        self.taille = 0 # initialisation de taille
        self.MAX = tailleMax # recuperation de tailleMax a la creation de la file

    def est_vide(self):
        # la file est vide si mtete est None
        return self.mtete == None

    def est_pleine(self):
        # la file est pleine si taille est Max
        return self.taille == self.MAX

    def ajoute_en_queue(self,e):
        # on ajoute en queue de file (on traite a part le cas file vide)
        if not self.est_pleine():
            m = Cell() # creation de l'objet cellule
            m.valeur = e    # remplissage de valeur
            self.taille += 1       # on augmente la taille
            if self.est_vide():
                # si la file est vide mqueue et mtete pointent vers m
                self.mqueue = self.mtete = m
            else:
                # sinon on ajoute l'objet a la fin de la file
                self.mqueue.suivant = m
                self.mqueue = m
        else:
            print("ajoute_en_queue : File Pleine")

    def tete(self):
        # retourne la valeur de la tete de la file
        if self.est_vide():
            print("tete : File Vide")
        else:
            return self.mtete.valeur
    
    def retire_tete(self):
        # retire l'objet en tete et renvoie sa valeur
        if self.est_vide():
            print("retire_tete : File Vide")
        else:
            retour = self.mtete.valeur      # on recupere sa valeur
            self.mtete = self.mtete.suivant # on retire l'objet en tete
            self.taille -= 1        # on ajuste la taille
            return retour       # on renvoit la valeur de l'objet retire

    def __str__(self):
        # fonction d'affichage de la file
        if self.est_vide():
            mafile = "[ ]"
        else:
            mafile = ""
            courant = self.mtete
            while courant != None:
                mafile += " " + str(courant.valeur)
                courant = courant.suivant
            mafile = '[' + mafile + ' ]'
        return mafile

################################ Zone de test ###############################

import random
f = File_List(10)

print(f)
i = 0
while not f.est_pleine():
    f.ajoute_en_queue(i)
    i += 1
    print(f)

while not f.est_vide():
    a = f.retire_tete()
    # print("J'enlève ",a)
    print(f)