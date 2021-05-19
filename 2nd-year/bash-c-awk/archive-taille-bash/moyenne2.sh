#!/usr/bin/bash
# Calcule la moyenne des nombres contenu dans un fichier dont le nom est passe en parametre.
# La moyenne doit etre calculee en nombre reel

# test si fichier existe 

som=0
compteur=`cat $1 | wc -w`
for i in `cat $1`;do
	let som+=$i
done
res=`echo $som / $compteur | bc -l`
echo $res
