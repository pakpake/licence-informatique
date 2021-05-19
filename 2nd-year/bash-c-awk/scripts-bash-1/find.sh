#!/bin/bash

# script qui recherche les occurences d'un fichier ou d'un répertoire à partir d'un répertoire donné en argument.
# Parcourt récursivement les sous-répertoires
# Affiche sur la sortie standard les chemins ou l'entrée est trouvée
# retourne 0 (exit) en cas de succès, 1 sinon

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
    # débugage :
    # echo "Nombre args = ${ARGS}"
    # echo "TYPE        = ${TYPE}"
    # echo "Recpible    = ${REPCIBLE}" 
    # echo "NOM         = ${NOM}"
    ##################################
    # on récupère la liste du résultat de `ls` de REPCIBLE
    LISTE=$(ls -a ${REPCIBLE})
    # pour chacun des résultats trouvés
    for f in ${LISTE};do
        # on ne tient pas compte de '.' et '..'
        if [[ ${f} != '.' && ${f} != '..' ]];then
            # si c'est un répertoire on fait un appel récursif
            if [[ -d ${REPCIBLE}/${f} ]]; then
                bash ./find.sh ${TYPE} ${REPCIBLE}/${f} ${NOM}
            fi
            # sinon, si c'est un fichier qu'on recherche, on l'affiche
            if [[ ${TYPE} == 'f' ]]; then
                if [[ -f ${REPCIBLE}/${f} ]]; then
                    echo ${REPCIBLE}/${f} | grep -w ${NOM}$
                fi
            # si c'est un répertoire, on l'affiche
            elif [[ ${TYPE} == 'd' ]]; then
                 if [[ -d ${REPCIBLE}/${f} ]]; then
                    echo ${REPCIBLE}/${f} | grep -w ${NOM}$
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
    recherche
fi

