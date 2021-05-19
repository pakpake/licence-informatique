"""
title: TP5 - Liste Chainee
author: pakpake 
date: Fevrier 2020
"""


class Cellule():
    def __init__(self):
        self.data = object
        self.suivant = None


    def remove(self):
        self.data = None
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

    def __str__(self):
        if self.est_liste_vide():
            chaine = ""
        else:
            chaine = ""
            courant = self.mpremier
            while courant != None:
                chaine += " " + str(courant.data)
                courant = courant.suivant
        chaine = '[' + chaine + ']'
        return chaine

    def length(self):
        # self.taille = 0
        # courant = self.tete()
        # while courant != None:
        #     courant = courant.suivant
        #     self.taille += 1
        return self.taille
    
    def get_at(self,i):
        # renvoie le i-eme element de la liste
        if i >= self.length() or i < 0:
            return None
        else:
            # on definit le premier element a 0
            courant = self.mpremier
            while i>0:
                courant = courant.suivant
                i-=1
            return courant.data
    
    def remplace_at(self,e,i):
        if i >= self.length() or i < 0:
            return ("ERROR : indice en dehors de la liste dans la fonction remplace_at")
        else:
            # on definit le premier element a 0
            courant = self.mpremier
            while i>0:
                courant = courant.suivant
                i-=1
            courant.data = e
    
    def insere_at(self,e,i):
        # renvoie la liste a laquelle on a insere l'element e en position i
        m = Cellule()
        m.data = e
        if i > self.length() or i < 0:
            return ("ERROR : indice en dehors de la liste dans la fonction insere_at")
        else:
            # cas où i=0
            if i==0:
                self.insere_tete(e)
            else:
                # on definit le premier element a 0
                courant = self.mpremier
                # on s'arrete sur l'element precedant l'endroit ou on veut inserer e
                i -= 1
                while i>0:
                    courant = courant.suivant
                    i-=1
                m.suivant = courant.suivant     # on raccroche les elements suivant de la liste a la nouvelle cellule
                courant.suivant = m     # on rattache le nouvel element au bon endroit
                self.taille += 1        # on met a jour la taille de la liste

    def supprime_at(self,i):
        # renvoie la liste a laquelle on a enlever le i-eme element 
        if i >= self.length() or i < 0:
            return ("ERROR : indice en dehors de la liste dans la fonction supprime_at")
        else:
            if i==0:
                # m = self.mpremier
                self.mpremier = self.mpremier.suivant
            else:
                courant = self.mpremier
                # on s'arrete sur l'element precedant l'endroit ou on veut supprimer e
                i -= 1
                while i>0:
                    courant = courant.suivant
                    i-=1
                m = courant.suivant     # on raccroche l'element a supprimer a un pointeur temporaire m
                courant.suivant = m.suivant     # on dit que le suivant de courant c'est le suivant de m
            self.taille -= 1        # on met a jour la taille de la liste
            # m.remove()      # on libere la memoire occupee par le pointeur qu'on supprime

    def map(self,f,inplace=False):
        # applique a chaque element de la liste la fonction f
        courant = self.mpremier
        while courant != None:
            if inplace:
                courant.data = f(courant.data)
            else:
                f(courant.data)
            courant = courant.suivant
    
    def count(self,e):
        courant = self.mpremier
        counter = 0
        # for i in range(len(self)):
        #     if courant.data == e:
        #         counter += 1
        #     courant = courant.suivant
        # return counter
        while courant != None:
            if courant.data == e:
                counter += 1
            courant = courant.suivant
        return counter

    def filter(self,f):
        # renvoie la liste des elements pour lesquels la fonction f renvoie True
        courant = self.mpremier
        # creation d'une liste vide
        l = Liste()
        while courant != None:
            if f(courant.data):
                # l.insere_tete(courant.data)
                l.insere_at(courant.data,l.length())
            courant = courant.suivant
        return l
        # les elements sont ajoutes en ordre relatif inverse

    def reduce(self,f,x):
        courant = self.mpremier
        while courant != None:
            x = f(x,courant.data)
            courant = courant.suivant
        return x
