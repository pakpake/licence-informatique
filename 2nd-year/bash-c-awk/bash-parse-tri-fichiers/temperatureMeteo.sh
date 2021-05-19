#!/bin/bash
# @author: pakpake 

# Empecher bash de faire les expend des wildcards
set -f

# variables
DATE1=$(date +"%Y.%m")
DER=$#      # =1 ou =3          
CODE=$1
REPSOURCE='/tmp/data/'

usage() {
    echo "usage : temperatureMeteo CODESTATION [AAAA MM]"
    echo "      : exemple"
    echo "      : temperatureMeteo LFRH 2015 06 # mois 06 annee 2015"
    echo "      : temperatureMeteo LFRH         # mois courant"
    exit 1;
}

Temperature() {
    GREP="grep Temperature ${REPSOURCE}${CODE}-${DATE1}* "
    CMD="${GREP} | awk -F\":\" 'BEGIN{SOM=0;I=0}{SOM=SOM+\$3;I++}END{if (I==0) print \"Aucune valeur trouvée\"; else print SOM/I}'"
    echo ${CMD} | bash
}


# On test si il n'y a que 1 ou 3 arguments
if [[ ${DER} -ne 1 && ${DER} -ne 3 ]];then
    usage
else
    # Test de l'existence du repertoire
    if [ ! -d ${REPSOURCE} ];then
        echo "Répertoire $REPSOURCE inexistant"
        usage
    fi
    if [ ${DER} -eq 3 ];then
        ANNEE=$2
        MOIS=$3
        DATE1=${ANNEE}.${MOIS}
    fi
    # on verifie que  CODESTATION = longueur 4
    if [ $(expr length ${CODE}) -ne 4 ];then
	    echo "--------------------------------------"
	    echo "Le CODESTATION doit être de longueur 4"
	    echo "--------------------------------------"
        usage
    fi
    Temperature
fi
