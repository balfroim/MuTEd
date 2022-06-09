#!/usr/bin/env python3

import csv
import re
from glob import glob

# Static information
classes = {"Tile", "Grid"}

# Data
print("id,part,class,instr_missed,instr_covered,branch_missed,branch_covered,line_missed,line_covered,compl_missed,compl_covered,method_missed,method_covered")

for path in glob("results/*.csv"):
    match = re.search(r"/student(\d+)_part(\d)", path)
    name = match.group(1)
    part = match.group(2)

    with open(path, "r") as file:
        reader = csv.reader(file)

        for row in reader:
            _, _, class_, instr_missed, instr_covered, branch_missed, branch_covered, line_missed, line_covered, compl_missed, compl_covered, method_missed, method_covered = row
            if class_ in classes:
                print(",".join([name, part, class_, instr_missed, instr_covered, branch_missed, branch_covered, line_missed, line_covered, compl_missed, compl_covered, method_missed, method_covered]))

