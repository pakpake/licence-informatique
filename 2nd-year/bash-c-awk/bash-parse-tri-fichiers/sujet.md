# sujet 

## grâce aux fichiers de données météorologique : 

* calculez la moyenne des températures d'un mois ou d'une année
* tracez le courbe des températures

vous avez à votre disposition de le dossier *data-v2* qui contient les relevés météos 
le nom de ces fichiers sont toujours sous le même format :
CCCC AAAA MM JJ
CCCC étant le code de la station météo

## Sélection météo

Écrire une fonction bash qui copie en sélectionnant de ces fichiers vers un second répertoire.

l'utilisation de cette commande est dans le fichier *selectFileMeteo.sh*

Cela copie tous les fichiers du mmois MM de l'année AAAA de la station CCCC dans le repertoire repCible
le fonction créé ou vide le répertoire de destination
Si l'année et le mois ne sont pas spécifiés, le programme utilisate la date courante (mois et année d'aujourd'hui - via la commande bash *date*).

## Température

Écrire une fonction *temperatureMeteo.sh* qui affiche la moyenne des températures à partir de fichiers de relevé méteo.

les fichiers météos sont toujours dans le même format.

Si le fichier possède la ligne Temperature avec une valeur, alors le jour est pris en compte.
L'utilisation de la commande est dans le fichier *temperatureMeteo.sh*.
