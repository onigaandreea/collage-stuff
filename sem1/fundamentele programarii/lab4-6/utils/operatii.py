def make_list_copy(the_list):
    """
    Face o copie a unei liste de dictionare
    :param the_list: lista care trebuie copiata
    :type the_list: list (of dicts)
    :return: copie a listei date
    :rtype: list (of dicts)
    """
    tranzactii_list_copy = []
    for a in the_list:
        a_copy = {}
        for key, value in a.items():
            a_copy[key] = value
        tranzactii_list_copy.append(a_copy)
    return tranzactii_list_copy


def add_to_list(the_list, element):
    """
     Adauga un element intr-o lista
    :param the_list: lista la care se adauga
    :type the_list: list
    :param element: elementul care se adauga
    :type element: any
    :return: lista se modifica prin adaugarea elementului
    :rtype: -
    """
    the_list.append(element)
    # return athlete_list + [athlete]


def remove_from_list(the_list, del_key, value_to_del):
    """
    Sterge dintr-o lista de dictionare elementele care au valoarea value_to_del pentru cheia del_key

    :param the_list: lista cu elemente
    :type the_list: list (of dicts)
    :param del_key: cheia pentru care se verifica (e.g. nume, sport, etc - atributul de interes al entitatii)
    :type del_key: string
    :param value_to_del: valoarea dupa care eliminam (e.g. sportul specific pentru care stergem in cazul atletilor)
    :type value_to_del: string (dar poate fi si int, float, etc)
    :return: lista cu elemente care au valoarea pentru cheia del_key diferita de value_to_del
    :rtype: list (of dicts)
    """
    new_list = [elem for elem in the_list if elem[del_key] != value_to_del]
    return new_list


def filter_list(the_list, compare_key, value):
    """
    Returneaza o lista cu elementele care indeplinesc conditia ceruta
    :param the_list: lista data, elemente reprezentate prin dictionare
    :type the_list: list (of dicts)
    :param compare_key: cheia din dictionar dupa care se face comparatia
    :type compare_key: string
    :param value: valoarea cu care se compara
    :type value: str
    :return: lista cu elementele care indeplinesc conditia: elem[compare_key] op value
    :rtype: list (of dicts)
    :raises: KeyError daca, cheia dupa care se compara nu exista in dictionar
    """

    new_list = [elem for elem in the_list if (elem[compare_key] != value)]

    return new_list


def test_filter_list():
    test_list = []
    test_list.append({'a': 10, 'b': 15, 'c': 20})
    test_list.append({'a': 1, 'b': 2, 'c': 3})
    test_list.append({'a': 0, 'b': 200, 'c': 14})
    test_list.append({'a': 12, 'b': 10, 'c': 25})

    filtered_list1 = filter_list(test_list, 'a', 20)
    assert (len(filtered_list1) == 4)

    filtered_list2 = filter_list(test_list, 'a', 12)
    assert (len(filtered_list2) == 3)

    try:
        filtered_list3 = filter_list(test_list, 'x', 14)
        assert False
    except KeyError:
        assert True


def test_add_to_list():
    test_list = []
    add_to_list(test_list, 1)
    assert (len(test_list) == 1)
    add_to_list(test_list, 'second_element')
    assert (len(test_list) == 2)
    add_to_list(test_list, {'key1': 0, 'key2': 1})
    assert (len(test_list) == 3)


def test_remove_from_list():
    test_list1 = []
    test_list1.append({'a': 10, 'b': 15, 'c': 20})
    test_list1.append({'a': 1, 'b': 2, 'c': 3})
    test_list1.append({'a': 0, 'b': 200, 'c': 14})
    test_list1.append({'a': 10, 'b': 10, 'c': 25})

    new_list1 = remove_from_list(test_list1, 'a', 10)
    assert (len(new_list1) == 2)
    new_list2 = remove_from_list(test_list1, 'a', 20)
    assert (len(new_list2) == 4)

    try:
        new_list3 = remove_from_list(test_list1, 'varsta', 11)
        assert False
    except KeyError:
        assert True


def test_copy_list():
    l1 = [{'a': 10, 'b': 10}, {'a': 15, 'b': 20}]
    l2 = make_list_copy(l1)
    assert (len(l1) == len(l2))
    assert (l1 == l2)
