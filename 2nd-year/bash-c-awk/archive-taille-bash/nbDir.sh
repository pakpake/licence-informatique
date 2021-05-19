#!/bin/bash
#Calcule et affiche le nombre de repertoires d'un repertoire passe en parametre (sans `ls`)

# test le nombre d'arguments
if [ ! $# -eq 1 ]
then
	echo "usage : nbDir rep"
	exit 1
fi

# on test si l'argument n'existe pas
if [ ! -d "$1" ]
then
	echo "nbDir : le repertoire $1 n'existe pas"
	exit 2
fi

# si toutes les conditions sont respectees
i=0
lFS=$'\n'
nb=0
for i in `ls -1 $1`
do
	if [ -d "$1/$i" ]
	then
		#if [ -d "Mes documents" ]
		let nb++
	fi
done
echo $nb
