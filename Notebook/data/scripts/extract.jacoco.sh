#!/bin/bash

mvn clean
rm -r results
rm log.txt
mkdir results

for file in $(ls tests); do
    echo $file
    cp tests/$file src/test/java/be/unamur/game2048/Test2048.java
    if mvn clean test jacoco:report; then
        cp target/site/jacoco/jacoco.csv results/$file.csv
    else
        echo "$file: failed" >> log.txt
    fi
done
