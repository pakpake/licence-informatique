# scripts bash

## Traitement d’un répertoire

- Ecrire un programme nbDir en shell qui calcule et imprime sur la sortie standard le nombre de répertoires d’un répertoire passé en paramètre sans utiliser d’option particulière du `ls`.

## Moyenne

- Ecrire le script Shell moyennequi calcule la moyenne des nombres passés en paramètre.
- Ecrire le script Shell moyennequi calcule la moyenne des nombres contenu dans un fichier dont le nomest passé en paramètre. La moyenne doit être calculée en nombre réel.

## ZipFile

> Écrire un script Bash (zipFile) qui copie, dans un répertoire cible, un ensemble de fichiers en les compressant.

`zipFile repCible file1 file2 file ...`

Le répertoire cible doit être créé si il n’existe pas.
On utilisera la commande gzip pour la compression avec option –c qui écritsur la sortie standard le fichier compressé (-c pour ne pas perdre le fichier original).

`gzip -c file`

> Le script affiche le nombre de fichiers réellement copiés.

## maxSize

Ecrire en Bash un script maxSize qui affiche sur la sortie standard la liste des fichiers et uniquement les fichiers dont la taille est supérieure ou égale à une valeur donnée en octet (par défaut 1Mo). La commande travaille sur un répertoire dont le nom est passé en argument ; la commande n’est pas récursive.

> usage : maxSize [sizemax] dir

> Pour calculer la taille d’un fichier, vous pouvez utiliser la commande `ls -l`
Exemple d’utilisation

> maxSize.sh 2500000 /home/toto/name
/home/toto/name:gimp_pdf.zip 2961741
/home/toto/name:test.tiff 60991332

## Modifier maintenant le programme en ajoutant l’option unité

> usage : maxSize [sizemax unite] dir

Le paramètre unité peut prendre les valeurs suivantes o,k,m,g pourrespectivement octet, kilooctet 1024 , megaoctet 10242 , gigaoctet 10243. Par défaut, l’unité est le mégaoctet.

> maxSize.sh 2 /home/toto/name
/home/toto/name:gimp_pdf.zip 2961741
/home/toto/name:test.tiff 600991332
> maxSize.sh 2500 k /home/toto/name
/home/toto/name:gimp_pdf.zip 2961741
/home/toto/name:test.tiff 600991332
