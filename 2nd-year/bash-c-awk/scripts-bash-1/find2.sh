#!/bin/bash

#variables
ARGS=$#
TYPE='f' # par défaut

# fonction d'usage du script
usage() {
    echo
    echo "find.sh [type] rep nom"
    echo
    echo "    - type : spécifie le type de la recherche (f ou d) fichier ou directory (f par défaut)"
    echo "    - rep : le chemin du repertoire de début de la recherche"
    echo "    - nom : nom exact de l'entrée recherchée"
    exit 1
}

recherche() {
    TYPE=$1
    local REPCIBLE=$2
    NOM=$3
    # on récupère la liste du résultat de `ls` de REPCIBLE
    LISTE=$(ls ${REPCIBLE} | tr "\n" " ")
    # pour chacun des résultats trouvés
    for f in ${LISTE};do
        # on ne tient pas compte de '.' et '..'
        if [[ ${f} != '.' && ${f} != '..' ]];then
            # si c'est un répertoire on fait un appel récursif
            if [[ -d ${REPCIBLE}/${f} ]]; then
                recherche ${TYPE} ${REPCIBLE}/${f} ${NOM}
            fi
            # si c'est un fichier qu'on recherche, on l'affiche
            if [[ ${TYPE} == 'f' ]]; then
                if [[ -f ${REPCIBLE}/${f} ]]; then
                    echo ${REPCIBLE}/${f} | grep -w ${NOM}$
                    return 0
                fi
            # si c'est un répertoire, on l'affiche
            elif [[ ${TYPE} == 'd' ]]; then
                if [[ -d ${REPCIBLE}/${f} ]]; then
                   echo ${REPCIBLE}/${f} | grep -w ${NOM}$
                   return 0
                fi
            fi
        fi
    done
}


# on teste le nombre d'arguments
if [[ ${ARGS} -ne 2 && ${ARGS} -ne 3 ]];then
    usage
else
    # on récupère les variables pour 3 arguments
    if [ ${ARGS} -eq 3 ];then
        TYPE=$1
        REPCIBLE=$2
        NOM=$3
        # on teste si TYPE est f ou d
        if [[ ${TYPE} != 'f' && ${TYPE} != 'd' ]]; then
            echo "ERROR : type non valide"
            usage
        fi
    else
        # pour 2 arguments
        REPCIBLE=$1
        NOM=$2
    fi
    # on teste si REPCIBLE existe
    if [[ ! -d ${REPCIBLE} ]]; then
        echo "ERROR : le répertoire n'existe pas"
        usage
    fi
    # on teste si le dernier caractère de REPCIBLE est un'/'
    if [[ $(echo ${REPCIBLE:$((${#REPCIBLE}-1))})  = '/' ]]; then
        REPCIBLE=${REPCIBLE::-1}        # suppression du dernier caractère
    fi
    recherche $TYPE $REPCIBLE $NOM
fi

