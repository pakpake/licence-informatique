#!/bin/bash

# on test si le nombre d'arguments est 2 sinon on renvoit "usage ..." et exit 1
if [ ! $# -eq 2 ]
then
	echo "usage : sauveC rep cible"
	exit 1
fi

# on test si le 1er arg n'existe pas
if [ ! -d "$1" ]
then
	echo "sauveC :  le repertoire $1 n'existe pas"
	exit 2
fi

# on test si le 2eme arg n'est pas un repertoire
if [ ! -d "$2" ]
then
	echo "sauveC : $2 existe deja mais n'est pas un repertoire"
	exit 2
fi

# on demande a l'utilisateur si il veut ecraser le 2eme arg si il existe deja
if [ -d "$2" ]
then
	echo "$2 existe déjà ! veuillez confirmer sa destruction y/n :"
	read reponse
	case $reponse in
		[nN])
			exit 1
			;;
		[yYoO])
			rm -rf $2
			mkdir $2
			;;
		*)
			echo "ce n'est pas une reponse autorisee"
			exit 1
			;;
	esac
fi

# cas ou tous les parametres sont bon
# on fait la sauvegarde
# on supprime les messages d'erreur et (exit error code avec || : )
cp -f $1/*.h $1/*.c $1/*.i $1/Makefile $1/makefile $2 2>/dev/null || :
exit 0
