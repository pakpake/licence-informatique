#!/usr/bin/bash
#Calcule la moyenne des nombres passes en parametres

som=0
for i in $*;do
	let som+=$i
done
res=`echo $som / $# | bc -l`
let res=$som/$# #entier
echo $res
