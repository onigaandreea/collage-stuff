# Considerându-se o matrice cu n x m elemente binare (0 sau 1) sortate crescător pe linii,
# să se identifice indexul liniei care conține cele mai multe elemente de 1.


def maxde1(mat):
    """
    Determina linia care contine cele mai multe valori de 1 dintr-o matrice
    :param mat: matricea data (contine elemente binare sortate crescător pe linii)
    :return: numarul liniei care contine cele mai multe valori de 1
    """
    n = len(mat)
    m = len(mat[0])
    maxi = [-1, m]
    for i in range(n):
        nr = 0
        for j in range(m):
            if mat[i][j] == 0:
                nr += 1
            else:
                break
        if nr < maxi[1]:
            maxi = [i, nr]
    return maxi[0]+1


def test10():
    assert (maxde1([[0, 0, 0, 1, 1], [0, 1, 1, 1, 1], [0, 0, 1, 1, 1]]) == 2)
    assert (maxde1([[1, 1, 1, 1], [0, 1, 1, 1], [0, 0, 1, 1], [0, 0, 0, 1]]) == 1)
    assert (maxde1([[0, 0, 0], [0, 1, 1], [0, 0, 1], [1, 1, 1]]) == 4)
