# Să se determine cuvintele unui text care apar exact o singură dată în acel text.
# De ex. cuvintele care apar o singură dată în ”ana are ana are mere rosii ana" sunt: 'mere' și 'rosii'.
import myfunctions


def once(arr):
    """
    Returneaza o lista de cuvinte care apar o singura data in textul dat
    :param arr: textul in care se cauta cuvintele
    :return: lista de cuvinte care apar o singura data
    """
    cuv = myfunctions.splited(arr)
    lst = []
    dct = {}
    for c in cuv:
        if c not in dct.keys():
            dct[c] = 1
        else:
            dct[c] += 1
    for c in dct.keys():
        if dct[c] == 1:
            lst.append(c)
    return lst


def test4():
    assert (once("ana are ana are mere rosii ana") == ["mere", "rosii"])
    assert (once("vaza lalea vaza trandafir floare frunza trandafir crin") == ["lalea", "floare", "frunza", "crin"])
    assert (once("volei par mar volei mar par") == [])
    assert (once("am chef sa joc paintball") == ["am", "chef", "sa", "joc", "paintball"])
