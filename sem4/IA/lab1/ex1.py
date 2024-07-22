# Să se determine ultimul (din punct de vedere alfabetic) cuvânt care poate apărea într-un text care conține mai multe
# cuvinte separate prin ” ” (spațiu).
# De ex. ultimul (dpdv alfabetic) cuvânt din ”Ana are mere rosii si galbene” este cuvântul "si".
import myfunctions


def ultimul_cuv(prop):
    """
    Afla ultimul cuvant, din punct de vedere alfabetic, dintr-un text dat
    :param prop: textul din care se afiseza cuvantul cel mai mare
    :return: cuvantul care din punct de vedere alfabetic este ultimul din text
    """
    cuv = myfunctions.splited(prop)
    myfunctions.quickSort(cuv, 0, len(cuv) - 1)
    return cuv[0]


def ultim(arr):
    """
    Afla ultimul cuvant, din punct de vedere alfabetic, dintr-un text dat
    Construieste cuvinte pe care le compara pe rand cu un maxim, care se actualizeaza, daca este cazul
    :param arr: textul din care se afiseza cuvantul cel mai mare
    :return: cuvantul care din punct de vedere alfabetic este ultimul din text
    """
    arr += " "
    cuv = ""
    maxi = ""
    for i in range(len(arr)):
        if arr[i] != " ":
            cuv += arr[i]
        else:
            if maxi.lower() <= cuv.lower():
                maxi = cuv
            cuv = ""
    return maxi


def test1():
    assert (ultimul_cuv("Ana are mere galbene si rosii") == "si")
    assert (ultimul_cuv("ala bala portocala") == "portocala")
    assert (ultimul_cuv("Ana anna anne") == "anne")

    assert (ultim("Ana are mere galbene si rosii") == "si")
    assert (ultim("ala bala portocala") == "portocala")
    assert (ultim("Ana anna anne") == "anne")
