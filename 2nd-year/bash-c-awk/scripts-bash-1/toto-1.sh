#!/bin/bash

str="r2"
for f in $(ls $1)
do
    if test -d ${f}
    then
      #echo "${f} repertoire"
      bash /tmp/toto.sh $1/${f}
    else
      echo $1/${f} | grep ${str}
    fi
done
