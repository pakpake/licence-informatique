#!/bin/bash

# on récupère toutes les interfaces présentes sur la machine
TOTAL=$(cat /proc/net/dev | sed "s/^\ *//g" | cut -s -d':' -f1 | tr '\n' ' ')

# fonction d'usage du script
usage() {
    echo "./lectureNet <interface>"
    echo " interface : lo, eth, eth0, wlan, wlp1s0, ..."
    exit 1
}

lecture() {
    # on récupère les bytes reçus de l'interface choisie en supprimant les espaces devant le nom de l'interface et en remplacant les blancs par 1 espace pour récupérer plus facilement le champs demandé
    RECU=$(cat /proc/net/dev | sed "s/^\ *//g" | grep -w "${INT}" |tr -s [:blank:] ' ' | cut -d' ' -f2)

    # on récupère les bytes émis de la même manière que la commande précédente
    EMI=$(cat /proc/net/dev | sed "s/^\ *//g" | grep -w "${INT}" |tr -s [:blank:] ' ' | cut -d' ' -f10)
}

# on teste le nombre d'arguments
if [ $# -ne 1 ];then
    # on renvoie l'usage du script si les arguments sont différents de 1
    usage
else
    # sinon on enregistre le nom de l'interface dans une variable
    INT=$1
    # si le nom de l'interface que l'on à écrite est (exactement) présente parmis toutes les interfaces disponibles
    if grep -q -w "${INT}" <<< "${TOTAL}"; then
        # on exécute la fonction
        lecture
        # on affiche les bytes reçus et émis
        echo "bytes reçus = ${RECU}"
        echo "bytes émis = ${EMI}"
    else
        # sinon on signale qu'elle n'est pas présente et on renvoie l'usage
        echo "Cette interface n'existe pas"
        echo "liste des interfaces présentes : ${TOTAL}"
        echo 
        usage
    fi
fi
