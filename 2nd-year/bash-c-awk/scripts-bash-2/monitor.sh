#!/bin/bash

VAR='/proc'     # répertoire de travail

# Proc 168 r/u 203/1 - Recu 6513482 b (78924 b/s) - Emis 6513482 b (4496 b/s) 

# on récupère toutes les interfaces présentes sur la machine
TOTAL=$(cat /proc/net/dev | sed "s/^\ *//g" | cut -s -d':' -f1 | tr '\n' ' ')

# on définie les variables globales à 0
EMI=0
RECU=0
RUNNING=0
EROOT=0
NOTROOT=0

# fonction d'usage du script
usage() {
    echo "./monitor.sh <interface> X"
    echo "X : temps (en seconde) entre chaque mesure"
    exit 1
}


# fonction de lecture des process
lectureProc() {
    # on récupère le nombre total de processus en mode Run
    RUNNING=$(find ${VAR} -maxdepth 2 -type f -name "status" 2>/dev/null -exec grep "State" {} \; | cut -f2 | cut -d ' ' -f1 | grep ^R | wc -l)

    # on récupère le nombre total de processus de root (Uid=0)
    EROOT=$(find ${VAR} -maxdepth 2 -type f -name "status" 2>/dev/null -exec grep ^Uid {} \; | cut -f2 | grep ^0$ | wc -l)

    # on récupère le nombre total de processus autre que root (il peut y avoir différents utilisateurs)
    NOTROOT=$(find ${VAR} -maxdepth 2 -type f -name "status" 2>/dev/null -exec grep ^Uid {} \; | cut -f2 | grep -v ^0$ | wc -l)
}



lecture() {
    # on récupère les bytes reçus de l'interface choisie en supprimant les espaces devant le nom de l'interface et en remplacant les blancs par 1 espace pour récupérer plus facilement le champs demandé
    RECU=$(cat /proc/net/dev | sed "s/^\ *//g" | grep -w "${INT}" |tr -s [:blank:] ' ' | cut -d' ' -f2)

    # on récupère les bytes émis de la même manière que la commande précédente
    EMI=$(cat /proc/net/dev | sed "s/^\ *//g" | grep -w "${INT}" |tr -s [:blank:] ' ' | cut -d' ' -f10)
}


chargeNet() {
    # on fait appel à la fonction lecture
    lecture
    # on calcul la différence entre les 2 valeurs de bytes émis
    E=$(expr ${EMI} - ${EMI0})
    # de même pour les bytes recus
    R=$(expr ${RECU} - ${RECU0})
    # on peut ensuite calculer le nombres de bytes émis et reçus par secondes
    DEBITUP=$(expr $E / ${DUREE})
    DEBITDL=$(expr $R / ${DUREE})
    # emi0 et recu0 prennent pour valeurs emi et recu pour le prochain calcul
    EMI0=${EMI}
    RECU0=${RECU}
}



# on teste le nombre d'arguments
if [ $# -ne 2 ];then
    # on renvoie l'usage du script si les arguments sont différents de 2
    usage
else
    # sinon on enregistre le nom de l'interface dans une variable
    INT=$1
    # on enregistre la durée dans une variable
    DUREE=$2
    #
    EMI0=$(cat /proc/net/dev | sed "s/^\ *//g" | grep -w "${INT}" |tr -s [:blank:] ' ' | cut -d' ' -f10)
    RECU0=$(cat /proc/net/dev | sed "s/^\ *//g" | grep -w "${INT}" |tr -s [:blank:] ' ' | cut -d' ' -f2)
    # si le nom de l'interface que l'on à écrite est (exactement) présente parmis toutes les interfaces disponibles
    if grep -q -w "${INT}" <<< "${TOTAL}"; then
        # on vérifie que la durée est bien un nombre entier 
        if [ "$(echo ${DUREE} | grep "^[ [:digit:] ]*$")" ];then
            while true; do
                # on attend la durée souhaité entre chaque exécution
                sleep ${DUREE}
                # on exécute une boucle infinie autour de la fonction chargeNet et lectureProc
                chargeNet
                lectureProc
                echo -e "Proc ${RUNNING} r/u ${EROOT}/${NOTROOT}\tRecu ${RECU} b (${DEBITDL} b/s)\tEmis ${EMI} b (${DEBITUP} b/s)"
            done
        else
            # si DUREE n'est pas un entier
            echo "Le paramètre doit être un entier positif"
            echo
            usage
        fi
    else
        # l'interface n'est pas bonne
        echo "Cette interface n'existe pas"
        # on liste toutes les interfaces disponibles
        echo "liste des interfaces présentes : ${TOTAL}"
        echo 
        # on affiche l'usage de la fonction
        usage
    fi
fi
