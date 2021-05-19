#!/bin/bash
# affiche la liste des fichiers dont la taille est supérieure ou égale à une valeur donnée en octet


# usage de la commande
if [ $# -eq 0 ];then
	echo "usage : maxsize [sizemax unite] dir"
	exit 1
fi
TAILLE=$1
if [ $# -eq 2 ];then
	OPT="M"
else
	# calcul de la taille des fichiers
	case $2 in
		o) OPT="c" ;;
		k) OPT="k" ;;
		m) OPT="M" ;;
		g) OPT="G" ;;
	esac
	shift
fi
# on test si le repertoire existe
if [ ! -d $2 ];then
	echo "maxSize : le repertoire $2 n'existe pas"
	exit 1
fi
find $2 -maxdepth 1 -size +$TAILLE$OPT -printf "%h:%-50f %s \n"

