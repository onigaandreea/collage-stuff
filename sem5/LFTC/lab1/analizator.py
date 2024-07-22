import re

from specificari import *


def isOperator(token):
    for op in operatori:
        if token == op:
            return True
    return False


def isIdentifier(token):
    return re.match(r'[a-zA-Z]{,8}$', token) is not None


def isConstant(token):
    return re.match('^(0|[1-9][0-9]*)$|^(0|[1-9][0-9]*)\.([0-9])+$', token) is not None


def extrage_cuvinte(text, separatori):
    cuvinte = []
    cuvant_curent = ""
    in_ghilimele = False

    for caracter in text:
        if caracter == '"':
            in_ghilimele = not in_ghilimele  # Invertim starea la fiecare ghilimea
        elif not in_ghilimele and caracter not in separatori and caracter != '\n':
            cuvant_curent += caracter
        else:
            if cuvant_curent:
                cuvinte.append(cuvant_curent)
            cuvant_curent = ""

    if cuvant_curent:
        cuvinte.append(cuvant_curent)

    cuv_unice = []
    [cuv_unice.append(x) for x in cuvinte if x not in cuv_unice]

    return cuv_unice

