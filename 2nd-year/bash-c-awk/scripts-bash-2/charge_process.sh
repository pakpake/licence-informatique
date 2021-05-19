#!/bin/bash

VAR='/proc'     # répertoire de travail
ID=$(id -u)     # Uid de l'utilisateur

# fonction d'usage du script
usage() {
    echo "  ./charge_process.sh X"
    echo "  X temps (en secondes) entre chaque mesure"
    exit 1
}

# fonction de lecture des process
lectureProc() {
    # on récupère le nombre total de processus en mode Run
    RUNNING=$(find ${VAR} -maxdepth 2 -type f -name "status" 2>/dev/null -exec grep "State" {} \; | cut -f2 | cut -d ' ' -f1 | grep ^R | wc -l)

    # on récupère le nombre total de processus de root (Uid=0)
    EROOT=$(find ${VAR} -maxdepth 2 -type f -name "status" 2>/dev/null -exec grep ^Uid {} \; | cut -f2 | grep ^0$ | wc -l)

    # on récupère le nombre total de processus de l'utilisateur (chez moi Uid=1001)
    USER=$(find ${VAR} -maxdepth 2 -type f -name "status" 2>/dev/null -exec grep ^Uid {} \; | cut -f2 | grep ${ID} | wc -l)

    # on récupère le nombre total de processus autre que root (il peut y avoir différents utilisateurs)
    NOTROOT=$(find ${VAR} -maxdepth 2 -type f -name "status" 2>/dev/null -exec grep ^Uid {} \; | cut -f2 | grep -v ^0$ | wc -l)

}

# on teste le nombre de paramètre
[[ ! $# -eq 1 ]] && usage || (
    DUREE=$1
    # on teste si DUREE est un entier
    if [ "$(echo ${DUREE} | grep "^[ [:digit:] ]*$")" ];then
        while true; do
            # on exécute une boucle infinie autour de la fonction lectureProc
            lectureProc
            # on affiche le résultat souhaité
            echo "process ${EROOT} root ${NOTROOT} user ${RUNNING} run"
            # on attend la durée souhaité entre chaque exécution
            # Attention cela ne prend pas en compte la durée d'exécution de la fonction lectureProc
            sleep ${DUREE}
        done
    else
        # si DUREE n'est pas un entier
        echo "Le paramètre doit être un entier positif"
        echo
        usage
    fi
)
