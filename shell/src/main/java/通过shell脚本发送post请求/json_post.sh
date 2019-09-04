#!/bin/env bash

jsonString=""
for line in $(cat ./${1})
do
 jsonString=${jsonString}${line}
done
echo ${jsonString}
curl -i -X POST -H 'Content-type':'application/json' -d @${1} ${2}
