# Pentru un șir cu n numere întregi care conține și duplicate, să se determine elementul majoritar
# (care apare de mai mult de n / 2 ori). De ex. 2 este elementul majoritar în șirul [2,8,7,2,2,5,2,3,1,2,2].


def majorar(a):
    c = {}
    for i in a:
        if i not in c.keys():
            c[i] = 1
        else:
            c[i] += 1
    for i in c.keys():
        if c[i] >= len(a) // 2:
            return i


def test6():
    assert (majorar([2, 8, 7, 2, 2, 5, 2, 3, 1, 2, 2]) == 2)
    assert (majorar([11, 12, 17, 21, 12, 12, 21, 11, 12, 12]) == 12)
    assert (majorar([62, 55, 35, 62, 35, 35, 55, 35, 62, 35, 35]) == 35)
