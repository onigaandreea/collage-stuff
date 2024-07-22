# Pentru un șir cu n elemente care conține valori din mulțimea {1, 2, ..., n - 1} astfel încât
# o singură valoare se  repetă de două ori, să se identifice acea valoare care se repetă.
# De ex. în șirul [1,2,3,4,2] valoarea 2 apare de două ori.
import myfunctions


def dublura(arr):
    """
    Returneaza singurul element care se repetea in lista data
    :param arr: lista de elemente
    :return: elementul care se repeta
    """
    j = 0
    for i in arr:
        j += 1
        if myfunctions.exists(i, arr[j:]):
            return i


def test5():
    assert (dublura([1, 2, 3, 4, 2]) == 2)
    assert (dublura([3, 2, 3, 1]) == 3)
    assert (dublura([1, 4, 3, 7, 5, 8, 2, 6, 7]) == 7)
