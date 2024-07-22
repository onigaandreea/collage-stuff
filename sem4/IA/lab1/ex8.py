# Să se genereze toate numerele (în reprezentare binară) cuprinse între 1 și n.
# De ex. dacă n = 4, numerele sunt: 1, 10, 11, 100.
import myfunctions


def binare(n):
    """
    Creaza o lista cu toate numerele de la 1 la n convertite in baza 2
    :param n: ultimul numar pentru care facem conversie
    :return: lista care contine numerele convertite in baza 2
    """
    nr = []
    i = 1
    while i <= n:
        a = myfunctions.binar(i)
        nr.append(a)
        i += 1
    return nr


def test8():
    assert (binare(4) == [1, 10, 11, 100])
    assert (binare(8) == [1, 10, 11, 100, 101, 110, 111, 1000])
    assert (binare(11) == [1, 10, 11, 100, 101, 110, 111, 1000, 1001, 1010, 1011])
