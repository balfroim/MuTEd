#!/bin/bash

rm -r results
rm log.txt
mkdir results

for file in $(ls tests); do
    echo $file
    cp tests/$file src/test/java/be/unamur/game2048/Test2048.java
    mvn clean
    rm report.html
    if ../descartes-reneri/scripts/venv/bin/python /home/pierre/Projets/muted/descartes-reneri/scripts/generate_report.py; then
        cp report.html results/$file.html
    else
        echo "$file: failed" >> log.txt
    fi
done
