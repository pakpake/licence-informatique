# -*- coding: utf-8 -*-
"""
@date: Avril 2020
@author: pakpake 
@comment: Génère automatiquement des fichiers séparés à partir d'un texte. La coupure se fait aux fins de phrases (i.e. aux points)
"""

# ouverture et lecture du fichier
fichier = open('textes.dat','r')
texte_complet = fichier.read()
fichier.close()

textes = texte_complet.split('\n')
i = 1 # indice du fichier
for t in textes:
    fname = "files/text"+str(i)+".txt"
    i += 1
    fichier = open(fname,'w')
    fichier.write(textes[i])
    print(fname) 
    fichier.close()