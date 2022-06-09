#!/bin/bash

rm -r results
rm log.txt
mkdir results

for file in $(ls tests); do
    echo $file
    cp tests/$file src/test/java/be/unamur/game2048/Test2048.java
    rm -r target/pit-reports
    if mvn test; then
        if mvn test-compile org.pitest:pitest-maven:mutationCoverage; then
            report=$(ls target/pit-reports | sort | tail -1)
            cp target/pit-reports/$report/mutations.xml results/$file.xml
            cp target/pit-reports/$report/mutations.csv results/$file.csv
        else
            echo "$file: pitest failed" >> log.txt
        fi
    else
        echo "$file: test failed" >> log.txt
    fi
done
