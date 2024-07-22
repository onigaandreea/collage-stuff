# Să se determine distanța Euclideană între două locații identificate prin perechi de numere.
# De ex. distanța între (1,5) și (4,1) este 5.0
from math import sqrt
from math import dist


def distanta(x1, y1, x2, y2):
    """
    Calculeaza distanta euclidiana dintre 2 puncte, date prin coordonatele lor
    :param x1: prima coordonata a primului punct
    :param y1: a doua coordonata a primului punct
    :param x2: prima coordonata a celui de al doilea punct
    :param y2: a doua coordonata a celui de al doilea punct
    :return: distanta dintre cele 2 puncte
    """
    dist = sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2))
    return dist


def distanta2(coord):
    """
    Calculeaza distanta euclidiana dintre 2 puncte, date prin coordonatele lor
    Varianta 2 de calcul
    :param coord: lista de coordonate
    :return: distanta dintre punctele reprezentate prin coordonate
    """
    p1 = [coord[0], coord[1]]
    p2 = [coord[2], coord[3]]
    return dist(p1, p2)


def test2():
    assert (distanta(1, 5, 4, 1) == 5.0)
    assert (distanta(1, 13, 7, 5) == 10.0)
    assert (distanta(7, 9, 15, 3) == 10.0)

    assert (distanta2([1, 5, 4, 1]) == 5.0)
    assert (distanta2([1, 13, 7, 5]) == 10.0)
    assert (distanta2([7, 9, 15, 3]) == 10.0)
