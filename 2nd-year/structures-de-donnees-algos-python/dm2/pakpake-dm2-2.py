# -*- coding: utf-8 -*-
"""
@date: Avril 2020
@author: pakpake 
@comment: Exercice 2 - DM2
          Attention, j'ai dupliqué certaines fonction pour qu'elles rendent des documents comme initialement indiqué dans le sujet.
"""

import glob  # pour la lecture des fichiers avec les *
import re    # pour ameliorer tokenize en tokenize2

""" Exercice 2 """

class Moteur_files:
    def __init__(self):
        self.inv_indx = dict()
        self.docs_links = dict()
    
    def __str__(self):
        """ Pour afficher la classe complète """
        # On trie les mots de inv_indx par ordre alphabetique
        return "**********DUMPING CLASS**********\n  DOCS\n  "+"  "+'\n    '.join([str((k,v)) for k,v in self.docs_links.items()])+'\n  INDX\n  '+"  "+'\n    '.join([str((k,v)) for k,v in sorted(self.inv_indx.items())]) + '\n*********************************'
        
    def tokenize(self, s):
        """ permet de mettre en minuscule le contenu s et de placer chacun des mots séparés par des espaces dans une liste """
        return s.lower().split(" ")
    
    def tokenize2(self, s):
        """ permet de mettre en minuscule le contenu s et de placer chacun des mots séparés par des espaces, virgules, point, 2 points et quotes dans une liste """
        # on fait appel a re pour les expressions regulieres
        return re.split(r'[\.;,\s:\']\s*',s.lower())

    def add_to_index_file(self, fname):
        """:entree : self, chemin fname
        :sortie : None
        """
        # fname est le nom du fichier a integrer dans le moteur
        ni = len(self.docs_links)+1 # on recupere le numero du nouvel index
        self.docs_links.update({ni : fname})  # on rajoute au dictionnaire doc_links le nouveau document avec son index
        fichier = open(fname,'r') # ouverture du fichier
        c = fichier.read() # lecture du fichier
        fichier.close() # fermeture du fichier
        doc = self.tokenize2(c) # on separe les mots dans une variable doc
        for i in doc:   # pour tous les mots du document
            if i != '': # pour eviter les mots vides 
                if i in self.inv_indx:  # si le mot existe deja dans le dictionnaire inv_index
                    self.inv_indx[i].add(ni) # on rajoute la nouvelle reference a ce mot
                else:   # sinon
                    self.inv_indx.update({i : {ni}})  # on cree cette nouvelle entree dans le dictionnaire inverse    


    def add_bulk_files(self, filenames):
        """:entree : self, list de chemins filenames
        :sortie : None
        permet d’indexer une liste de documents représentés par une liste de noms de fichiers filenames """
        filesList = glob.glob(filenames)    # liste des fichiers
        for i in filesList: # pour chaque fichier
            self.add_to_index_file(i) # on le rajoute dans la base
    
    def intersection_sets_terms(self,terms):
        """ renvoie les INDICES des documents qui résultent de l’intersection d’une liste de  termes 
        On part de la liste complete et on fait les intersections successives avec les ensembles trouves avant de renvoyer l'ensemble final """
        ens = {e for e in self.docs_links} # ensemble de tous les index des doc
        for w in terms:                    # pour chaque mot de terms
            if w in self.inv_indx: # si le mot est dans la base
                we = self.inv_indx[w] # renvoie les valeurs de la cle w dans le dictionnaire inv_indx
            else:
                we = set() # ou renvoie un ensemble vide si la cle n'est pas dans inv_indx
            ens = ens & we # intersection de ens et we
        return ens

    def intersection_sets_terms_doc(self,terms):
        """ renvoie les documents qui résultent de l’intersection d’une liste de  termes 
        On part de la liste complete et on fait les intersections successives avec les ensembles trouves avant de renvoyer l'ensemble final, ici on retourne la liste des documents et pas la liste des index """
        ens = {e for e in self.docs_links} # ensemble de tous les index des doc
        for w in terms:     # pour chaque mot de terms
            if w in self.inv_indx: # si le mot est dans la base
                we = self.inv_indx[w] # renvoie les valeurs de la cle w dans le dictionnaire inv_indx
            else:
                we = set() # ou renvoie un ensemble vide si la cle n'est pas dans inv_indx
            ens = ens & we # intersection de ens et we
        l = []      # liste qui contiendra les noms des fichiers d'indice dans ens
        for i in ens:
            l.append(self.docs_links[i])
        return l

    
    def union_sets_terms(self,terms):
        """ renvoie les INDICES des documents qui résultent de l’union d’une liste de termes 
        On part de la liste vide et on fait les unions successives avec les ensembles trouves avant de renvoyer l'ensemble final """
        ens = set()     # cree un ensemble vide
        for w in terms:     # pour chaque mot de terms
            if w in self.inv_indx:
                we = self.inv_indx[w] # renvoie les valeurs de la cle w dans le dictionnaire inv_indx
            else:
                we = set() # ou renvoie un ensemble vide si la cle n'est pas dans inv_indx
            ens = ens | we # union de ens et we
        return ens

    def union_sets_terms_doc(self,terms):
        """ renvoie les documents qui résultent de l’union d’une liste de termes 
        On part de la liste vide et on fait les unions successives avec les ensembles trouves avant de renvoyer l'ensemble final, ici on renvoie la liste des documents et pas la liste des index """
        ens = set()     # cree un ensemble vide
        for w in terms:     # pour chaque mot de terms
            if w in self.inv_indx:
                we = self.inv_indx[w] # renvoie les valeurs de la cle w dans le dictionnaire inv_indx
            else:
                we = set() # ou renvoie un ensemble vide si la cle n'est pas dans inv_indx
            ens = ens | we # union de ens et we
        l = []      # liste qui contiendra les noms des fichiers d'indice dans ens
        for i in ens:
            l.append(self.docs_links[i])
        return l    

    def intersection_inv_sets_terms_doc(self,terms):
        """ renvoie les documents qui ne contiennent pas une liste de termes """
        # les documents exclus, c'est tout moins ceux trouves
        return [e for e in self.docs_links.values() if e not in self.intersection_sets_terms(terms)]

    def intersection_inv_sets_terms(self,terms):
        """ renvoie les INDICES des documents qui ne contiennent pas une liste de termes """
        # les termes exclus, c'est tout moins ceux trouves
        return {e for e in self.docs_links if e not in self.intersection_sets_terms(terms)}

    def intersection_sets(self,sets):
        """ renvoie les INDICES des documents qui résultent de l’intersection d’une liste de set 
        On part de la liste complète et on fait l'intersection successive avec les ensembles donnés en paramètre """
        ens = {e for e in self.docs_links} # ensemble de tous les index des doc
        for s in sets:
            ens = ens & s   # intersection des ensembles
        return ens
    
    def intersection_sets_doc(self,sets):
        """ renvoie les documents qui résultent de l’intersection d’une liste de set
        Idem mais renvoie la liste de documents """
        ens = {e for e in self.docs_links}      # ensemble de tous les index des doc
        for s in sets:
            ens = ens & s   # intersection des ensembles
        l = []      # liste qui contiendra les noms des fichiers d'indice dans ens
        for i in ens:
            l.append(self.docs_links[i])
        return l

    def search(self, must_include=None, at_least_one=None, exclude=None):
        """:entree : self, liste de mots must_include, liste de mots at_least_one, liste de mots exclude 
        :sortie : set 
        On créé une liste vide et on y ajoute les ensembles venus des extractions et on en fait l'intersection finale """
        sets = [] # on cree une liste vide
        if must_include is not None: # on ajoute les must include
            sets.append(self.intersection_sets_terms(must_include))
        if at_least_one is not None: # on ajoute les at least
            sets.append(self.union_sets_terms(at_least_one))
        if exclude is not None: # on ajoute les exclude
            sets.append(self.intersection_inv_sets_terms(exclude))
        # on renvoie l'intersection de tout
        return self.intersection_sets(sets)
        

    def print_result(self, search_result, max_results = 1):            
        """:entree : self, ensemble de documents search_result, nombre de résultats max_results
        :sortie : None """
        nb = 1 # compteur pour verifier le nombre de resulats max
        for i in search_result: # on parcourt la liste des index 
            if nb <= max_results: # si on n'a pas atteint le nb de resulats max
                nb += 1 # on incremente nb
                print(i,":",self.docs_links[i]) # on affiche l'indice et le nom de fichier
                fichier = open(self.docs_links[i],'r')
                print(fichier.read()) # et son contenu
                fichier.close()
                print("---------------------------------------------------------")


if __name__ == "__main__":
    # zone de test #
    print("Exercice 2 :")
    print("** Question 1 **")
    M = Moteur_files()
    T = "Ex conseQuat ExercItation dUis cuLpa NULLA ameT"
    print(T)
    print(M.tokenize(T))
    print()
    print("** Question 2 **")
    M.add_to_index_file('files/Droit_civil.txt')
    print(M)
    print()
    M = Moteur_files()
    M.add_bulk_files('files/Droit*.txt')
    print(M)
    print()
    print("** Question 3 **")
    print("Intersection de \"le\" et \"numérique\"",M.intersection_sets_terms(['le','numérique']))
    print("Union de \"rapports\" et \"règles\"",M.union_sets_terms(['rapports','règles']))
    print("Intersection inverse de \"le\" et \"numérique\"",M.intersection_inv_sets_terms(['le','numérique']))
    print("Intersection de {1,2,3} et {1}",M.intersection_sets([{1,2,3},{1}]))
    print()
    print("** Question 4 **")
    print("Recherche des indices des documents de :")
    print("  - must include (['et','régit']) = ",M.intersection_sets_terms(['et','régit']))
    print("  - at least (['l','toto']) = ",M.union_sets_terms(['l','toto']))
    print("  - exclude (['pénal']) = ",M.intersection_inv_sets_terms(['pénal']))
    print("search(['et','régit'],['l','toto'],['pénal']) =",M.search(['et','régit'],['l'],['pénal']))

    print()
    print("** Question 5 **")
    print("Affichage des documents pour {1,2,3} avec max_results = 2 :")
    M.print_result({1,2,3},2)
    print("***********************************************************")
    print("*****************  Tests avec mes fichiers ****************")
    ME = Moteur_files()
    ME.add_bulk_files('files/text*.txt')
    # print(ME)
    print("Mots obligatoires")
    mobl = ['homme','lui']
    print("  ",mobl,ME.intersection_sets_terms(mot))
    print("Au moint un mot parmi")
    m1 = ['amoureux', 'aime', 'amour','bonheur']
    print("  ",m1,ME.intersection_sets_terms(mot))
    print('Mots à exclure')
    mexc = ['sexe', 'mort', 'vin']
    print("  ",mexc,ME.intersection_sets_terms(mot))
    print()
    print("Résultat")
    print("  ",ME.search(mobl,m1,mexc))
    print("***********************************************************")