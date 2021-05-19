#!/bin/bash

test() {
    str=$2
    liste=$(ls $1)
    #echo $liste
    for f in ${liste}
    do
        #echo "f=$f"
        # echo "1=$1"
        if [[ -d $1/${f} ]]
        then
          #echo "${f} repertoire"
          bash ./toto-2.sh $1/${f} ${str}
        fi
        echo $1/${f} | grep ${str}$
    done
}


