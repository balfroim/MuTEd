#!/usr/bin/env python3

import csv

QA = [
    "What was your knowledge of Java before you started this experiment?",
    [
        "No knowledge",
        "Basic knowledge",
        "Intermediate knowledge",
        "Advanced knowledge",
    ],
    "What was your knowledge of software testing before you started this experiment?",
    [
        "No knowledge",
        "Basic knowledge",
        "Intermediate knowledge",
        "Advanced knowledge",
    ],
    "Do you think it is interesting to present the concepts of mutation testing together with the basics of programming?",
    [
        "Yes",
        "Yes but superficially",
        "No",
    ],
    "What could be the consequences of the use of mutation testing by novice programmers?",
    [
        "Better programs",
        "More competent programmers",
        "Better programs and more competent programmers",
        "Neither",
    ],
    "Do you consider classical testing tools (JUnit) to be useful for teaching programming fundamentals?",
    [
        "Yes",
        "Yes, but only with basic functionality",
        "No",
    ],
    "Do you consider mutation testing tools to be useful for teaching the fundamentals of programming?",
    [
        "Yes",
        "Yes, but only with basic functionality",
        "No",
    ],
    "Considering your background so far (without taking this presentation into account), you feel that the concepts of software testing have been:",
    [
        "Fairly well presented",
        "Insufficiently presented",
        "Not presented",
    ],
    "Do you think that using software testing tools for learning purposes could be useful for creating good programming habits?",
    [
        "Yes",
        "No",
    ],
    "Do you think that creating test cases through mutation testing is useful for improving the learning ability of novice programmers?",
    [
        "Yes",
        "No",
    ],
    "How did you find creating tests manually, without the help of a tool?",
    [
        "Easy in general",
        "Difficult, especially with regard to the completeness of my tests (sufficient code coverage)",
        "Difficult, especially to follow a logical order in the design of test cases",
    ],
    "What is your perception of software testing after applying mutation testing to your tests?",
    [
        "It has changed the way I design tests",
        "This allowed me to discover parts of the code that were not sufficiently tested",
        "The mutants do not seem to me to be particularly useful for improving the quality of my tests",
    ],
    "The reports generated by the tool used in the second session:",
    [
        "Were sufficiently understandable",
        "Lacked comprehensibility but were still usable",
        "Were not understandable enough to be usable",
    ],
    "Compared to your original self-assessment, you feel:",
    [
        "You have assessed yourself correctly",
        "You have overestimated yourself",
        "You have undervalued yourself",
    ],
    "From a practical point of view, mutation testing:",
    [
        "Is very useful",
        "Is very useful but not comfortable to use",
        "Does not compensate for the effort required to use it",
    ],
]

QUESTIONS = QA[::2]
ANSWERS = QA[1::2]

GROUPS = ["pit", "reneri"]

HEADER_LATEX = r"""% Generated from script
    {\footnotesize\begin{longtable}{m{0.55\linewidth}rrr}
        \caption{Questions and Answers by Group}
        \label{tab:questionnaire} \\
        \hline
         & \textbf{Both} & \textbf{PIT} & \textbf{Reneri} \\
        \hline
        \endfirsthead
    
        \hline
         & \textbf{Both} & \textbf{PIT} & \textbf{Reneri} \\
        \hline
        \endhead
    
        \hline
        \multicolumn{4}{r}{\textit{Continued on next page}} \\ \hline
        \endfoot
    
        \hline
        \endlastfoot"""


def write_table_questionaire():
    with open("data/questions.csv", "r") as file:
        reader = csv.reader(file)
        answers_both, answers_per_group = count_answers(reader)
    # Format
    buffer = HEADER_LATEX
    for question_i, question in enumerate(QUESTIONS):
        buffer += "\n"
        buffer += r"""    \multicolumn{4}{m{0.9\linewidth}}{\textit{Q%d.~%s}} \\*""" % (question_i + 1, question)
        for answer_i, answer in enumerate(ANSWERS[question_i]):
            sum_both = sum(answers_both[question_i])
            sum_per_group = [sum(answers_per_group[group_i][question_i]) for group_i in range(len(GROUPS))]
            both = answers_both[question_i][answer_i]
            per_group = [answers_per_group[group_i][question_i][answer_i] for group_i in range(len(GROUPS))]
            percentage_both = round(both / sum_both * 100)
            percentage_per_group = [round(per_group[0] / sum_per_group[0] * 100),
                                    round(per_group[1] / sum_per_group[1] * 100)]
            last = answer_i == len(ANSWERS[question_i]) - 1
            buffer += r"""    %s & %d (%d\%%) & %d (%d\%%) & %d (%d\%%) """ % (
                answer, both, percentage_both, per_group[0], percentage_per_group[0], per_group[1],
                percentage_per_group[1])
            buffer += r"\\ \hline" if last else r"\\*"
            buffer += "\n"
    buffer += "\n" + r"""\end{longtable}}""" + "\n"
    with open("output/tables/questions.tex", "w") as f:
        f.write(buffer)


def count_answers(reader: '_csv.reader'):
    answers_both = [[0 for _ in q] for q in ANSWERS]
    answers_per_group = [[[0 for _ in q] for q in ANSWERS] for _ in GROUPS]
    next(reader)  # Skip header
    for row in reader:
        name, group, *answers = row
        group_i = GROUPS.index(group)
        for question_i, answer in enumerate(answers):
            try:
                answer_i = int(answer) - 1
                answers_both[question_i][answer_i] += 1
                answers_per_group[group_i][question_i][answer_i] += 1
            except ValueError:
                pass
    return answers_both, answers_per_group
