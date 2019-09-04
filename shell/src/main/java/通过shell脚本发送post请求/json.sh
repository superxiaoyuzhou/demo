#!/bin/env bash

echo "请输入请求JSON文件名:"
read fileName
echo "请输入请求url:"
read url
jsonString=""
for line in $(cat ./${fileName})
do
 jsonString=${jsonString}${line}
done
echo ${jsonString}
curl -i -X POST -H 'Content-type':'application/json' -d ${jsonString} ${url}
