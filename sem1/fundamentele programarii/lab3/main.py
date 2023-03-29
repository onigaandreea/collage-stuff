"""
Scrieti o aplicatie care are interfata utilizator tip consolă cu un meniu:
1 Citirea unei liste de numere intregi
2 Gasirea secventelor de lungime maxima care respectă proprietatea ca p=1 sau diferentele (x[j+1] - x[j]) si
(x[j+2] - x[j+1]) au semne contrare,
 pentru j=i..i+p-2.
3 Gasirea secventelor de lungime maxima care respectă proprietatea ca suma elementelor este egala cu 5
4 Iesire din aplicatie.

"""


def print_menu():

    print("1. Citeste o lista de numere intregi")
    print("2. Gaseste secventele de lungime maxima care au proprietatea ca p=1 sau diferentele"
          " (x[j+1] - x[j]) si (x[j+2] - x[j+1]) au semne contrare, pentru j=i. .i+p-2")
    print("3. Gaseste secventele de lungime maxima care au proprietatea ca suma elementelor este egala cu 5")
    print("4. Iesire din aplicatie")


def read_list():

    list_as_string = input("Introduceti lista ")
    the_list = list_as_string.split()
    new_list = []
    for elem in the_list:
        new_list.append(int(elem))
    return new_list


def populate_list(the_list):

    the_list.append(1)
    the_list.append(1)
    the_list.append(2)
    the_list.append(1)
    the_list.append(3)
    the_list.append(1)
    the_list.append(2)
    return the_list


def diferente(lista, i):

    if lista[i+1]-lista[i] < 0:
        d1 = -1
    else:
        d1 = 1
    if lista[i+2]-lista[i+1] < 0:
        d2 = -1
    else:
        d2 = 1
    if d1 != d2:
        return True
    return False


def test_diferente():
    assert diferente([1, 2, 1, 5, 4], 2)
    assert (diferente([1, 1, 5, 7, 9], 2) == False)


def semne_diferite(lista):

    l_secv = 1
    i_end = 0
    l_max = 0
    for i in range(0, len(lista) - 2):
        if diferente(lista, i):
            l_secv += 1
        else:
            l_secv = 1
        if l_max < l_secv:
            l_max = l_secv
            i_end = i+2
    if l_max == 1:
        the_list = [lista[0]]
    else:
        the_list = lista[(i_end-l_max):i_end+1]
    return the_list


def test_semne_diferite():

    new_list = semne_diferite([1, 2, 3, 4, 5])
    print(new_list)

    assert semne_diferite([0, 4, 5, -1, 2, 1]) == [4, 5, -1, 2, 1]
    assert new_list == [1]


def sum_5(the_list):

    l_max = 0
    i_end = -1
    for i in range(len(the_list)):
        for j in range(i, len(the_list)):
            if sum(the_list[i:j+1]) == 5:
                l_secv = j-i+1
                if l_max < l_secv:
                    l_max = l_secv
                    i_end = j
    if i_end == -1:
        the_list = []
    else:
        the_list = the_list[(i_end - l_max + 1):i_end+1]
    return the_list


def test_sum_5():

    assert sum_5([1, 2, 2, 3, 1, -3, 4]) == [2, 2, 3, 1, -3]
    assert sum_5([1, 1, 1, 1, 1, 4]) == [1, 1, 1, 1, 1]
    assert sum_5([1, 2, 4, 3, 1]) == []


def start():
    current_list = []
    populate_list(current_list)
    while True:
        print_menu()
        print('Lista curenta este', current_list)
        option = int(input("Optiunea dumneavoastra este:"))
        if option == 1:
            current_list = read_list()
        if option == 2:
            current_list = semne_diferite(current_list)
            print(current_list)
            print('Lista curenta este',  semne_diferite(current_list))
        if option == 3:
            current_list = sum_5(current_list)
            print('Lista curenta este', current_list)
        elif option == 4:
            return


def teste():
    test_diferente()
    test_semne_diferite()
    test_sum_5()


teste()
start()
