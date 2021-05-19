#!/bin/bash

# variables
CPDATE=$(date +"%Y.%m")
DER=$#          # =2 ou =4          
REPSOURCE='/tmp/data/'
CODE=$1

usage() {
    echo "usage : selectFileMeteo CODESTATION [AAAA MM] repCible"
    echo "      :"
    echo "      : selectFileMeteo LFRH 2015 06 /tmp/M2015 # mois 06 annee 2015"
    echo "      : selectFileMeteo LFRH /tmp/MMoisCourant #annee et mois courant"
    exit 1;
}

repCible() {
    if [ -d ${REPCIBLE} ];then
        rm -rf ${REPCIBLE}/*
    else
        mkdir ${REPCIBLE} 2>/dev/null
    fi
}

codeStation() {
    # on verifie que  CODESTATION = longueur 4
    if [ $(expr length ${CODE}) -ne 4 ];then
	echo "--------------------------------------"
	echo "Le CODESTATION doit être de longueur 4"
	echo "--------------------------------------"
        usage
    else
	# on verifie que CODESTATION est bien present dans les fichiers
	GREP='ls'
	GREP="${GREP} ${REPSOURCE}${CODE}*"
	# $? recupere le code d'erreur (exit status) de la commande precedente
	ERRCODE=$(${GREP} >/dev/null 2>&1 ; echo $?) 
       	if [ ${ERRCODE} -ne 0 ];then
	    echo "-------------------------------------------"
	    echo "Le CODESTATION n'existe pas dans ${REPSOURCE}"
	    echo "-------------------------------------------"
            usage
	fi    	
    fi
}

selectFileMeteo() {
    if [ ${DER} -eq 2 ];then
	codeStation
	repCible
	# copie des fichiers avec la date courante
	echo "Répertoire source  = ${REPSOURCE}"
	echo "Code de la station = ${CODE}"
	echo "Date courante      = ${CPDATE}"
        echo "Répertoire cible   = ${REPCIBLE}"
	cp -f ${REPSOURCE}${CODE}-${CPDATE}* ${REPCIBLE} 2>/dev/null
    else
	DATE2="${ANNEE}.${MOIS}"
        codeStation
	repCible
	echo "Répertoire source  = ${REPSOURCE}"
	echo "Code de la station = ${CODE}"
	echo "Date choisie       = ${DATE2}"
        echo "Répertoire cible   = ${REPCIBLE}"
	cp -f ${REPSOURCE}${CODE}-${DATE2}* ${REPCIBLE} 2>/dev/null
    fi
}

# On test si il n'y a que 2 ou 4 arguments
if [[ ${DER} -ne 2 && ${DER} -ne 4 ]];then
    usage
else
    if [ ${DER} -eq 4 ];then
        ANNEE=$2
        MOIS=$3
        REPCIBLE=$4
    else
        REPCIBLE=$2
    fi
    selectFileMeteo
fi
