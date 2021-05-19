#!/bin/bash

VAR=$(ls -a $1)
#VAR=$(ls -l $1 | egrep '^d' | cut -d ':' -f2 | cut -d ' ' -f 2)
#VAR=$(find $1 -mindepth 1 -maxdepth 1 -type d)
echo $VAR
echo -------------------

for i in $(echo ${VAR});do
    if [[ $i != '.' && $i != '..' ]];then
        echo "Les resultats sont => $i"
        if [ -d "$1/$i" ];then
            echo "Un rep : $1/$i"
            $(./script.sh $1/$i)
        fi
    fi
done
