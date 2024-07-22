# Să se determine al k-lea cel mai mare element al unui șir de numere cu n elemente (k < n).
# De ex. al 2-lea cel mai mare element din șirul [7,4,6,3,9,1] este 7.
import myfunctions


def k_mai_mare(a, k):
    """
    Returneaza al k-lea cel mai mare element al unui șir de numere cu n elemente
    :param a: sirul de numere dat
    :param k: al catelea element mai mare este cautat
    :return: al k-lea cel mai mare element
    """
    myfunctions.quickSort(a, 0, len(a) - 1)
    return a[k-1]


def test7():
    assert (k_mai_mare([7, 4, 6, 3, 9, 1], 2) == 7)
    assert (k_mai_mare([61, 75, 80, 42, 18, 55], 3) == 61)
    assert (k_mai_mare([8, 16, 56, 23, 68, 33, 47, 99], 7) == 16)
