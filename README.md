# MuTEd: A Comparative Study of Classic and Extreme Mutation Testing for Teaching Software Testing

[![DOI](https://zenodo.org/badge/499075011.svg)](https://zenodo.org/badge/latestdoi/499075011)

## What is MuTEd?

MuTEd, which stands for *Mutation Testing Education*, is the code name for our master thesis. We conducted a comparative study of two reporting tools for teaching software testing to undergraduates. One, [PIT](https://github.com/hcoles/pitest), applies classical mutation testing and the other, [Reneri](https://github.com/STAMP-project/descartes-reneri), uses extreme mutation testing.

**Abstract**

> Although software testing is critical in software engineering, studies have shown a significant gap between students' knowledge of software testing and the industry's needs, hinting at the need to explore novel approaches to teach software testing. Among them, classical mutation testing has already proven to be effective in helping students. We hypothesise that extreme mutation testing could be more effective by introducing more obvious mutants to kill. In order to study this question, we organised an experiment with two undergraduate classes comparing the usage of two tools, one applying classical mutation testing, and the other one applying extreme mutation testing. The results contradicted our hypothesis. Indeed, students with access to the classic mutation testing tool obtained a better mutation score, while the others seem to have mostly covered more code. Finally, we have published and anonymised the students' test suites in adherence to best open-science practices, and we have developed guidance based on previous evaluations and our own results.

## What will you find in this repository?

In this GitHub repository, you will find in the [Notebook directory](./Notebook) the data collected during the experiment alongside the scripts to generate the tables and graphs shown in the master thesis document. You will also find in the [SUT directory](./SUT) the code for the SUT (an adapted version of the famous game 2048) that students tested and the adapted version of Reneri we used.

## Who is behind this project?

This study was conducted by two master's students, [Martin Balfroid](https://snail.info.unamur.be/author/martin-balfroid/) and [Pierre Luycx](https://snail.info.unamur.be/author/pierre-luycx/), from the University of Namur in Belgium. We were under the supervision of [Xavier Devroey](https://snail.info.unamur.be/author/xavier-devroey/) and [Benoît Vanderose](https://snail.info.unamur.be/author/benoit-vanderose/) during our internship inside the [SNAIL team](https://snail.info.unamur.be).

## Licensing

The dataset is licensed under CC-BY-SA 4.0. This repository contains adapted source codes of [Reneri](https://github.com/STAMP-project/descartes-reneri) (LGPL3, Oscar Luis Vera Pérez) and an implementation of 2048 found on [Rosetta Code](https://www.rosettacode.org/wiki/2048#Java) (GFDL1.2, anonymous author).
