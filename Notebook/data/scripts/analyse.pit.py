import csv
import re
from glob import glob

# Static information
classes = ['models.Tile', 'models.Grid']
mutators = [
    'returns.BooleanFalseReturnValsMutator',
    'IncrementsMutator',
    'VoidMethodCallMutator',
    'returns.BooleanTrueReturnValsMutator',
    'ConditionalsBoundaryMutator',
    'returns.NullReturnValsMutator',
    'returns.EmptyObjectReturnValsMutator',
    'NegateConditionalsMutator',
    'MathMutator',
    'returns.PrimitiveReturnsMutator',
]
methods = [
    'models.Tile.getNearestPower2',
    'models.Tile.getValue',
    'models.Tile.isMerged',
    'models.Tile.canMergeWith',
    'models.Tile.mergeWith',
    'models.Tile.toString',
    'models.Tile.equals',
    'models.Grid.getTile',
    'models.Grid.getRow',
    'models.Grid.getCol',
    'models.Grid.setTile',
    'models.Grid.getLength',
    'models.Grid.clearMerged',
]
statuses = ['KILLED', 'NO_COVERAGE', 'SURVIVED', 'TIMED_OUT']

# Data
print(",".join(["id", "part", "killed"] + classes + mutators + methods))

for path in glob("results/*.csv"):
    match = re.search(r"/student(\d+)_part(\d)", path)
    name = match.group(1)
    part = match.group(2)

    with open(path, "r") as file:
        reader = csv.reader(file)

        n_mutants_per_class = {class_: 0 for class_ in classes}
        n_mutants_per_mutator = {mutator: 0 for mutator in mutators}
        n_mutants_per_method = {method: 0 for method in methods}

        n_killed = 0
        n_killed_per_class = {class_: 0 for class_ in classes}
        n_killed_per_mutator = {mutator: 0 for mutator in mutators}
        n_killed_per_method = {method: 0 for method in methods}

        for n_mutants, row in enumerate(reader):
            filename, class_, mutator, method, lineno, status, test = row
            class_ = class_.removeprefix("be.unamur.game2048.")
            mutator = mutator.removeprefix("org.pitest.mutationtest.engine.gregor.mutators.")
            method = f"{class_}.{method}"

            n_mutants_per_class[class_] += 1
            n_mutants_per_mutator[mutator] += 1
            n_mutants_per_method[method] += 1

            if status == "KILLED":
                n_killed += 1
                n_killed_per_class[class_] += 1
                n_killed_per_mutator[mutator] += 1
                n_killed_per_method[method] += 1

        # print(n_mutants)
        # print(n_mutants_per_class)
        # print(n_mutants_per_mutator)
        # print(n_mutants_per_method)

        print(f"{name},{part},{n_killed}", end=",")
        print(",".join(str(n_killed_per_class[class_]) for class_ in classes), end=",")
        print(
            ",".join(
                str(n_killed_per_mutator[mutator]) for mutator in mutators
            ),
            end=",",
        )

        print(",".join(str(n_killed_per_method[method]) for method in methods))