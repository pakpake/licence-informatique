#!/bin/bash

# on récupère toutes les interfaces présentes sur la machine
TOTAL=$(cat /proc/net/dev | sed "s/^\ *//g" | cut -s -d':' -f1 | tr '\n' ' ')

# on définie les variables globales à 0
EMI=0
RECU=0

# fonction d'usage du script
usage(){
    echo "./chargeNet <interface> X"
    echo "X le temps (en seconde) entre 2 mesures"
    exit 1
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
                # on exécute une boucle infinie autour de la fonction chargeNet
                chargeNet
                # on affiche les résultats
                echo "Net debit ${DEBITDL} b/s entrant ${DEBITUP} b/s sortant"
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


