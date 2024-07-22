# Considerându-se o matrice cu n x m elemente binare (0 sau 1),
# să se înlocuiască cu 1 toate aparițiile elementelor egale cu 0 care sunt complet înconjurate de 1.


def inconjurate(mat):
    """
    Inlocuieste toate valorile de 0 dintr-o matrice cu valoarea 1, daca acel 0 este inconjurat complet de 1
    :param mat:
    :return:
    """
    n = len(mat)
    m = len(mat[0])
    # marginea matricei ramane neschimbata, deoarece acele elemente nu pot fi complet inconjurate de valori de 1

    for i in range(1, n-1):
        for j in range(1, m-1):
            if mat[i][j] == 0:
                if mat[i-1][j] == 1 & mat[i][j-1] == 1 & mat[i][j+1] == 1 & mat[i+1][j] == 1:
                    mat[i][j] = 1
    return mat


def test11():
    assert (inconjurate([[0, 1, 1, 0], [1, 1, 1, 1], [1, 0, 1, 1], [1, 1, 1, 1]]) ==
            [[0, 1, 1, 0], [1, 1, 1, 1], [1, 1, 1, 1], [1, 1, 1, 1]])
    assert (inconjurate([[0, 0, 1, 1, 0], [1, 1, 1, 1, 1], [1, 0, 1, 0, 1], [1, 1, 1, 1, 1]]) ==
            [[0, 0, 1, 1, 0], [1, 1, 1, 1, 1], [1, 1, 1, 1, 1], [1, 1, 1, 1, 1]])
