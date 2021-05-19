# réécriture de la fonction find en bash

# find version processus

Écrire un script find.sh qui recherche les occurrences d’un fichier ou d’un répertoire à partir d’un
répertoire donné en argument.
Cette commande doit parcourir récursivement les sous-répertoire par appel au programme lui-même pour y effectuer une recherche complète.
Elle affiche sur la sortie standard les chemins ou l’entrée est trouvée.

Le programme retourne 0 (exit) en cas de succès 1 sinon

`find.sh [type] rep nom`

type : spécifie le type de la recherche (f ou d) fichier ou directory (f par défaut)
rep  : le chemin du répertoire du début de la recherche
nom  : nom exact de l’entrée recherchée

```bash
$ find.sh d /tmp r2
/tmp/src/r2
/tmp/src2/sr2/r2
```

```bash
$ find.sh . f1
./f1
./src/f1
./tmp/f1
```

```bash
$ echo $?
0
````

```bash
$ find.sh . fichierInexistant
$ echo $?
1
```

# find version fonction

Même exo avec une fonction bash récursive

