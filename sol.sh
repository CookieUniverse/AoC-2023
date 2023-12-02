#!/bin/bash

INPUT=input.txt

if [ ! -f "$INPUT" ]
    then
        echo "$INPUT doesn't exist"
    exit
fi
maxNum=0
DAY_DIRS=`ls . | grep day`
for dir in $DAY_DIRS
do
    echo $dir
    maxNum=$((maxNum+1))
done

printf "Choose the day (1 - $maxNum): "

valid=false

while [ $valid = false ]
do
    read chosen_day
    if [ $chosen_day -lt 1 ] || [ $chosen_day -gt $maxNum ]
        then
            echo "Invalid day chosen"
            printf "Choose the day (1 - $maxNum): "
        else
            valid=true
    fi
done

day=day"$chosen_day"

printf "Choose the part (1 - 2): "

valid=false

while [ $valid = false ]
do
    read chosen_part
    if [ $chosen_part -lt 1 ] || [ $chosen_part -gt 2 ]
        then
            echo "Invalid part chosen"
            printf "Choose the part (1 - 2): "
        else
            valid=true
    fi
done

part=Part"$chosen_part".java

path="$day"/src

cp $INPUT "$path"/input.txt

cd "$path"
javac -d ../../out/ "$part"
cd ../..
java -cp ./out/ "$path"/"$part"
rm -rf ./out/