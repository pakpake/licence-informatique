#!/bin/bash
# affiche la liste des fichiers dont la taille est supérieure ou égale à une valeur donnée en octet


# usage de la commande
if [ $# -eq 0 ];then
	echo "usage : maxSize [sizemax] dir"
	exit 1
fi
# on test si le repertoire existe
if [ ! -d $2 ];then
	echo "maxSize : le repertoire $2 n'existe pas"
	exit 1
fi
# calcul de la taille des fichiers
find $2 -maxdepth 1 -size +$1c -printf "%h:%-50f %s \n"

