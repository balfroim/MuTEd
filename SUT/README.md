# MuTEd: A Comparative Study of Classic and Extreme Mutation Testing for Teaching Software Testing

## How to use the software under test

There are several bash scripts in this directory to install or remove dependencies, to switch between control and treatment groups, and to generate PIT or Reneri reports.

1. First, run "install.sh" to install Java and Maven, to build and install Reneri, and to automatically start Visual Studio Code. Inside Visual Studio Code, students can run the mutation testing tool by selecting "Terminal > Run Task > Générer un rapport" (see [tasks.json](./2048/.vscode/tasks.json)).
2. At first, the project will be setup to run PIT. To switch to Reneri (and back to PIT), one can run "group.reneri.sh" (or "group.pit.sh", respectively).
3. Finally, once the experiment is complete, students can run "uninstall.sh" to remove any dependencies that were previously installed.
