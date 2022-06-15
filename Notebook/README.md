# MuTEd: A Comparative Study of Classic and Extreme Mutation Testing for Teaching Software Testing

## How to run the project

### First time

First, install poetry by following the instructions on this [page](https://python-poetry.org/docs/#installation). This tool allows you to manage the dependencies of a project effectively.

Once poetry is installed, you need to start a command/shell prompt (On Windows, `⊞`+`R`, then enter `cmd`) and go to the project's root folder.

The first time run [`poetry install`](https://python-poetry.org/docs/cli/#install) to install the dependencies.

### Every time

Every time you want to start the notebook, you should run the command [`poetry shell`](https://python-poetry.org/docs/cli/#shell), which generates a new command prompt using a virtual python environment with all the project dependencies. Then run `jupyter notebook`, which should start a notebook on your default browser.
