#!/bin/bash
# Copie dans un repertoire cible un ensemble de fichier en les compressants

# usage de la commande
if [ $# -eq 0 ];then
	echo "usage : zipFile repcible file1 file2 ..."
	exit 1
fi 
DIR=$1
# on test si le repertoire cible existe, sinon on le cree
if [ ! -d ${DIR} ];then
	mkdir ${DIR}
fi
shift	# on passe le 1er argument
i=0
for f in $*
do
    gzip -c $f > $DIR/$f.gz 2>/dev/null
    ((i++))
done
echo "$i fichiers commpressés et transférés dans $DIR"


