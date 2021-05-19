# test du nombre d'arguments
# [[ ${ARGS} -ne 2 && ${ARGS} -ne 3 ]] && usage || [[ ${ARGS} -eq 3 ]] && \
# ( REPCIBLE=$2 ; NOM=$3 ) \
# && recherche || ( REPCIBLE=$1 ; NOM=$2 ) && recherche
# autre syntaxe pour les conditions mais ne marche pas (pb au niveau de l'affectation des variables REPCIBLE et NOM)

# ressemble a ça :

if [[ ${ARGS} -ne 2 && ${ARGS} -ne 3 ]];then
    usage
else
    if [ ${ARGS} -eq 3 ];then
        TYPE=$1
        REPCIBLE=$2
        NOM=$3
        if [[ ${TYPE} != 'f' && ${TYPE} != 'd' ]]; then
            echo "ERROR : type non valide"
            usage
        fi
    else
        REPCIBLE=$1
        NOM=$2
    fi
    recherche
fi

# Exemples :
# cmd1 && cmd2 <=> cmd2 est executee ssi cmd1 est True (renvoie 0)
# cmd1 || cmd2 <=> cmd2 est executee ssi cmd1 est False (renvoie != 0)
