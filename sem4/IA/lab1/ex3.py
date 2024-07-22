# Să se determine produsul scalar a doi vectori rari care conțin numere reale.
# Un vector este rar atunci când conține multe elemente nule. Vectorii pot avea oricâte dimensiuni.
# De ex. produsul scalar a 2 vectori unisimensionali [1,0,2,0,3] și [1,2,0,3,1] este 4.
from numpy import dot


def prod(a, b):
    """
    Calculeaza produsul scalar a 2 vectori
    :param a: primul vector
    :param b: al doilea vector
    :return: produsul celor 2 vectori
    """
    ps = 0
    for i in range(len(a)):
        ps += a[i] * b[i]
    return ps


def prod2(a, b):
    """
    Calculeaza produsul scalar a 2 vectori
    Varianta 2 de calcul
    :param a: primul vector
    :param b: al doilea vector
    :return: produsul celor 2 vectori
    """
    return dot(a, b)


def test3():
    assert (prod([1, 0, 2, 0, 3], [1, 2, 0, 3, 1]) == 4)
    assert (prod([1, 3, 5, 0, 3, 0], [0, 2, 0, 7, 1, 0]) == 9)
    assert (prod([1, 2, 1, 2, 0], [0, 1, 2, 0, 7]) == 4)

    assert (prod2([1, 0, 2, 0, 3], [1, 2, 0, 3, 1]) == 4)
    assert (prod2([1, 3, 5, 0, 3, 0], [0, 2, 0, 7, 1, 0]) == 9)
    assert (prod2([1, 2, 1, 2, 0], [0, 1, 2, 0, 7]) == 4)
