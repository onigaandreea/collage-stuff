# Considerându-se o matrice cu n x m elemente întregi
# și o listă cu perechi formate din coordonatele a 2 căsuțe din matrice ((p,q) și (r,s)),
# să se calculeze suma elementelor din sub-matricile identificate de fieare pereche.


def suma_sub_matrici(a, b):
    """
    Calculeaza suma sub-matricelor identificate de fiecare pereche formata din coordonatele a 2 casute din matrice
    :param a: matricea data
    :param b: lista de perechi pentru care se formeaza sub-matricele
    :return: lista de sume ale sub-matricelor
    """
    sume = []
    n = len(b)
    for i in range(n):
        s = 0
        for j in range(b[i][0], b[i][2] + 1):
            for k in range(b[i][1], b[i][3] + 1):
                s += a[j][k]
        sume.append(s)
    return sume


def test9():
    assert (suma_sub_matrici([[0, 2, 5, 4, 1], [4, 8, 2, 3, 7],
                              [6, 3, 4, 6, 2], [7, 3, 1, 8, 3],
                              [1, 5, 7, 9, 4]], [[1, 1, 3, 3], [2, 2, 4, 4]]) == [38, 44])
    assert (suma_sub_matrici([[0, 1, 1, 2, 1], [4, 8, 2, 3, 7],
                              [1, 3, 2, 5, 2], [4, 8, 1, 3, 3]], [[0, 0, 2, 2]]) == [22])
    assert (suma_sub_matrici([[0, 2, 4, 6], [1, 3, 5, 7], [1, 2, 4, 5], [3, 4, 6, 7],
                              [1, 5, 7, 9]], [[0, 1, 2, 3], [0, 0, 3, 3], [2, 1, 4, 3]]) == [38, 60, 49])
