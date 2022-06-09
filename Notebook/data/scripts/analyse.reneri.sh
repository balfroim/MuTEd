#!/bin/bash

path='results'

for item in $(ls $path); do
    file="$path/$item"
    n_r=$(grep -c '"uncovered issue"' $file)
    n_ipr=$(grep -c '"hint issue"' $file)
    n_infection=$(grep -c '"execution diagnosis"' $file)
    n_propagation=$(grep -c '"infection diagnosis"' $file)
    n_revealability=$(grep -c '"observation diagnosis"' $file)
    echo "$file,$n_r,$n_ipr,$n_infection,$n_propagation,$n_revealability"
done
