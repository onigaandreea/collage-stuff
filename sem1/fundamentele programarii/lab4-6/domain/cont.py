from domain.tranzactie import creaza_tranzactie, validare_tranzactie
from utils.operatii import make_list_copy, add_to_list, remove_from_list, filter_list


def genereaza_tranzactii():

    return [{'ziua': 12, 'suma': 200, 'tip': "intrare"},
            {'ziua': 7, 'suma': 300, 'tip': "intrare"},
            {'ziua': 15, 'suma': 70, 'tip': "iesire"},
            {'ziua': 25, 'suma': 105, 'tip': "iesire"},
            {'ziua': 7, 'suma': 100, 'tip': "iesire"},
            {'ziua': 12, 'suma': 80, 'tip': "intrare"}]


def setup_cont(predefinit):

    if predefinit:
        lista_tranzactii = genereaza_tranzactii()
    else:
        lista_tranzactii = []
    undo_list = []
    return [lista_tranzactii, undo_list]


def get_lista_tranzactii(cont):

    return cont[0]


def get_undo(cont):
    """
    Returneaza lista de undo corespunzatoare contului dat
    :param cont: obiect de tip cont
    :return: lista de undo aferenta contului
    """
    return cont[1]


def set_lista_tranzactii(cont, val_noua):

    cont[0] = val_noua


def set_undo(cont, val_noua):

    cont[1] = val_noua


def adauga_tranzactie(cont, tranzactie):
    validare_tranzactie(tranzactie)
    lista_curenta = get_lista_tranzactii(cont)
    undo_list = get_undo(cont)
    undo_list.append(make_list_copy(lista_curenta))
    add_to_list(lista_curenta, tranzactie)


def adauga_tranzactie1(cont, tranzactie):
    lista_curenta = get_lista_tranzactii(cont)
    undo_list = get_undo(cont)
    undo_list.append(make_list_copy(lista_curenta))
    add_to_list(lista_curenta, tranzactie)


def sterge_tranzactiile_din_ziua_data(cont, ziua):

    lista_curenta = get_lista_tranzactii(cont)
    undo_list = get_undo(cont)
    undo_list.append(make_list_copy(lista_curenta))
    set_lista_tranzactii(cont, remove_from_list(lista_curenta, 'ziua', ziua))


def sterge_tranzactie_din_perioada_data(cont, start, stop):

    crt_tranzactii = get_lista_tranzactii(cont)
    undo_list = get_undo(cont)

    undo_list.append(make_list_copy(crt_tranzactii))
    modific_list = [elem for elem in crt_tranzactii if (elem['ziua'] < start or elem['ziua'] > stop)]
    set_lista_tranzactii(cont, modific_list)


def sterge_dupa_tip(cont, tip):

    lista_curenta = get_lista_tranzactii(cont)
    undo_list = get_undo(cont)
    undo_list.append(make_list_copy(lista_curenta))
    set_lista_tranzactii(cont, remove_from_list(lista_curenta, 'tip', tip))


def sume_mai_mari(cont, suma):

    lista_curenta = get_lista_tranzactii(cont)
    undo_list = get_undo(cont)
    undo_list.append(make_list_copy(lista_curenta))

    lista_noua = [elem for elem in lista_curenta if elem['suma'] > suma]

    set_lista_tranzactii(cont, lista_noua)


def sume_mai_mari1(cont, suma):

    lista_curenta = get_lista_tranzactii(cont)
    undo_list = get_undo(cont)
    undo_list.append(make_list_copy(lista_curenta))

    lista_noua = [elem for elem in lista_curenta if elem['suma'] > int(suma)]

    set_lista_tranzactii(cont, lista_noua)


def tranzactie_dupa_zi_si_suma(cont, ziua, suma):

    lista_curenta = get_lista_tranzactii(cont)
    undo_list = get_undo(cont)
    undo_list.append(make_list_copy(lista_curenta))
    sume_mai_mari(cont, suma)
    lista_curenta = get_lista_tranzactii(cont)
    new_list = [elem for elem in lista_curenta if elem['ziua'] < ziua]
    set_lista_tranzactii(cont, new_list)


def selectie_dupa_tip(cont, tip):

    lista_curenta = get_lista_tranzactii(cont)
    lista_noua = []
    for i in range(len(lista_curenta)):
        if lista_curenta[i]['tip'] == tip:
            lista_noua.append(lista_curenta[i])
    set_lista_tranzactii(cont, lista_noua)


def elimina_dupa_tip(cont, key,  tip):
    lista_curenta = get_lista_tranzactii(cont)
    undo_list = get_undo(cont)
    undo_list.append(make_list_copy(lista_curenta))
    new_list = filter_list(lista_curenta, key, tip)
    set_lista_tranzactii(cont, new_list)


def filtreaza_dupa_tip_si_suma(cont, tip, suma):
    lista_curenta = get_lista_tranzactii(cont)
    undo_list = get_undo(cont)
    undo_list.append(make_list_copy(lista_curenta))
    new_list = [elem for elem in get_lista_tranzactii(cont) if elem['suma'] >= suma or elem['tip'] != tip]
    set_lista_tranzactii(cont, new_list)


def undo(cont):

    if len(get_undo(cont)) != 0:
        undo_list = get_undo(cont)
        previous_list_of_transactions = undo_list[-1]

        cont[0] = previous_list_of_transactions
        cont[1] = undo_list[:-1]

    else:
        raise ValueError("Nu se mai poate face undo.")


def suma_dupa_tip(cont, tip):
    lista_curenta = get_lista_tranzactii(cont)
    undo_list = get_undo(cont)
    undo_list.append(make_list_copy(lista_curenta))
    suma = 0
    for elem in lista_curenta:
        if elem['tip'] == tip:
            suma = suma + elem['suma']
    return suma


def sold_zi_data(cont, zi):
    lista_curenta = get_lista_tranzactii(cont)
    undo_list = get_undo(cont)
    undo_list.append(make_list_copy(lista_curenta))
    suma1 = 0
    suma2 = 0
    for elem in lista_curenta:
        if elem ['ziua'] == zi:
            if elem['tip'] == 'intrare':
                suma1 = suma1 + elem['suma']
            else:
                suma2 = suma2 + elem['suma']
    sold = suma1 - suma2
    return sold


def ordonare_dupa_suma(cont, tip):
    lista_curenta = get_lista_tranzactii(cont)
    undo_list = get_undo(cont)
    undo_list.append(make_list_copy(lista_curenta))
    selectie_dupa_tip(cont, tip)
    lista_curenta = get_lista_tranzactii(cont)
    lista_curenta = sorted(lista_curenta, key=lambda elem : elem['suma'])
    set_lista_tranzactii(cont, lista_curenta)


def test_sold_zi_data():
    test_cont = setup_cont(True)
    sold1 = sold_zi_data(test_cont, 7)
    sold2 = sold_zi_data(test_cont, 12)
    assert (sold1 == 200)
    assert (sold2 == 280)


def test_suma_dupa_tip():
    test_cont = setup_cont(True)
    suma = suma_dupa_tip(test_cont, 'intrare')
    assert (suma == 580)
    test_cont1 = setup_cont(True)
    suma1 = suma_dupa_tip(test_cont1, 'iesire')
    assert (suma1 == 275)


def test_ordonare_dupa_suma():
    test_cont = setup_cont(True)
    ordonare_dupa_suma(test_cont, 'intrare')
    test_list = get_lista_tranzactii(test_cont)
    assert (len(test_list) == 3)
    assert (test_list[0]['suma'] == 80)
    assert (test_list[1]['suma'] == 200)
    assert (test_list[2]['suma'] == 300)


def test_elimina_dupa_tip():
    test_cont = setup_cont(True)
    elimina_dupa_tip(test_cont, 'tip', "intrare")
    new_list = get_lista_tranzactii(test_cont)
    assert (len(new_list) == 3)
    assert (new_list[0] == {'ziua': 15, 'suma': 70, 'tip': 'iesire'})


def test_filtreaza_dupa_tip_si_suma():
    test_cont = setup_cont(True)
    filtreaza_dupa_tip_si_suma(test_cont, 'intrare', 210)
    new_list = get_lista_tranzactii(test_cont)
    assert (len(new_list) == 4)
    assert (new_list[0] == {'ziua': 7, 'suma': 300, 'tip': 'intrare'})
    assert (new_list[1] == {'ziua': 15, 'suma': 70, 'tip': 'iesire'})


def test_genereaza_tranzactii():

    a1 = genereaza_tranzactii()
    assert (type(a1) == list)
    assert (a1[0] == {'ziua': 12,'suma': 200,'tip': "intrare"})


def test_adauga_tranzactie():
    test_cont = setup_cont(False)
    a1 = creaza_tranzactie(12, 200, "intrare")
    adauga_tranzactie(test_cont, a1)
    the_list = get_lista_tranzactii(test_cont)
    assert (len(the_list) == 1)
    assert (the_list[0]['ziua'] == 12)
    assert (the_list[0]['suma'] == 200)
    assert (the_list[0]['tip'] == "intrare")

    a2 = creaza_tranzactie(11, 100, "iesire")
    adauga_tranzactie(test_cont, a2)
    the_list = get_lista_tranzactii(test_cont)
    assert (len(the_list) == 2)
    assert (the_list[1]['ziua'] == 11)
    assert (the_list[1]['suma'] == 100)
    assert (the_list[1]['tip'] == "iesire")


def test_sterge_tranzactie_din_perioada_data():
    test_cont = setup_cont(False)
    a1 = creaza_tranzactie(12, 200, "intrare")
    a2 = creaza_tranzactie(12, 250, "intrare")
    a3 = creaza_tranzactie(13, 100, "iesire")
    a4 = creaza_tranzactie(10, 70, "intrare")
    a5 = creaza_tranzactie(13, 300, "intrare")
    a6 = creaza_tranzactie(8, 150, "iesire")
    a7 = creaza_tranzactie(15, 10, "iesire")
    a8 = creaza_tranzactie(10, 70, "intrare")
    adauga_tranzactie(test_cont, a1)
    adauga_tranzactie(test_cont, a2)
    adauga_tranzactie(test_cont, a3)
    adauga_tranzactie(test_cont, a4)
    adauga_tranzactie(test_cont, a5)
    adauga_tranzactie(test_cont, a6)
    adauga_tranzactie(test_cont, a7)
    adauga_tranzactie(test_cont, a8)

    sterge_tranzactie_din_perioada_data(test_cont, 7, 9)
    assert (len(get_lista_tranzactii(test_cont)) == 7)
    sterge_tranzactie_din_perioada_data(test_cont, 11, 13)
    assert (len(get_lista_tranzactii(test_cont)) == 3)


def test_sterge_dupa_tip():
    test_cont = setup_cont(False)

    a1 = creaza_tranzactie(12, 200, "intrare")
    a2 = creaza_tranzactie(12, 250, "intrare")
    a3 = creaza_tranzactie(13, 100, "iesire")
    a4 = creaza_tranzactie(10, 70, "intrare")
    adauga_tranzactie(test_cont, a1)
    adauga_tranzactie(test_cont, a2)
    adauga_tranzactie(test_cont, a3)
    adauga_tranzactie(test_cont, a4)

    sterge_dupa_tip(test_cont, "intrare")
    assert (len(get_lista_tranzactii(test_cont)) == 1)


def test_sume_mai_mari():
    test_cont = setup_cont(False)
    a1 = creaza_tranzactie(12, 200, "intrare")
    a2 = creaza_tranzactie(12, 250, "intrare")
    a3 = creaza_tranzactie(13, 100, "iesire")
    a4 = creaza_tranzactie(10, 70, "intrare")
    adauga_tranzactie(test_cont, a1)
    adauga_tranzactie(test_cont, a2)
    adauga_tranzactie(test_cont, a3)
    adauga_tranzactie(test_cont, a4)
    sume_mai_mari(test_cont, 50)
    assert (len(get_lista_tranzactii(test_cont)) == 4)

    sume_mai_mari(test_cont, 100)
    assert (len(get_lista_tranzactii(test_cont)) == 2)


def test_sterge_tranzactiile_din_ziua_data():

    test_cont = setup_cont(False)
    a1 = creaza_tranzactie(12, 200, "intrare")
    a2 = creaza_tranzactie(12, 250, "intrare")
    a3 = creaza_tranzactie(13, 100, "iesire")
    a4 = creaza_tranzactie(10, 70, "intrare")
    adauga_tranzactie(test_cont, a1)
    adauga_tranzactie(test_cont, a2)
    adauga_tranzactie(test_cont, a3)
    adauga_tranzactie(test_cont, a4)

    sterge_tranzactiile_din_ziua_data(test_cont, 10)
    assert (len(get_lista_tranzactii(test_cont)) == 3)

    sterge_tranzactiile_din_ziua_data(test_cont, 12)
    assert (len(get_lista_tranzactii(test_cont)) == 1)


def test_tranzactie_dupa_zi_si_suma():
    test_cont = setup_cont(False)
    a1 = creaza_tranzactie(12, 200, "intrare")
    a2 = creaza_tranzactie(12, 250, "intrare")
    a3 = creaza_tranzactie(13, 100, "iesire")
    a4 = creaza_tranzactie(10, 70, "intrare")
    adauga_tranzactie(test_cont, a1)
    adauga_tranzactie(test_cont, a2)
    adauga_tranzactie(test_cont, a3)
    adauga_tranzactie(test_cont, a4)
    tranzactie_dupa_zi_si_suma(test_cont, 13, 75)
    assert (len(get_lista_tranzactii(test_cont)) == 2)
    assert (get_lista_tranzactii(test_cont)[0]['ziua'] == 12)
    assert (get_lista_tranzactii(test_cont)[0]['suma'] == 200)
    assert (get_lista_tranzactii(test_cont)[0]['tip'] == "intrare")


def test_selectie_dupa_tip():
    test_cont = setup_cont(False)
    a1 = creaza_tranzactie(12, 200, "intrare")
    a2 = creaza_tranzactie(12, 250, "intrare")
    a3 = creaza_tranzactie(13, 100, "iesire")
    a4 = creaza_tranzactie(10, 70, "intrare")
    adauga_tranzactie(test_cont, a1)
    adauga_tranzactie(test_cont, a2)
    adauga_tranzactie(test_cont, a3)
    adauga_tranzactie(test_cont, a4)
    selectie_dupa_tip(test_cont, "intrare")
    test_list = get_lista_tranzactii(test_cont)
    assert (len(test_list) == 3)
    assert (test_list[0] == {'ziua': 12, 'suma': 200, 'tip': "intrare"})
    assert (test_list[1] == {'ziua': 12, 'suma': 250, 'tip': "intrare"})
    assert (test_list[2] == {'ziua': 10, 'suma': 70, 'tip': "intrare"})


def test_undo():
    # test undo adaugare
    test_cont = setup_cont(False)
    t1 = creaza_tranzactie(12, 240, 'intrare')
    adauga_tranzactie(test_cont, t1)

    assert (len(get_lista_tranzactii(test_cont)) == 1)

    undo(test_cont)
    assert (len(get_lista_tranzactii(test_cont)) == 0)

    # test undo stergere
    test_cont2 = setup_cont(True)
    sterge_tranzactiile_din_ziua_data(test_cont2, 12)
    assert (len(get_lista_tranzactii(test_cont2)) == 4)

    undo(test_cont2)
    assert (len(get_lista_tranzactii(test_cont2)) == 6)


test_ordonare_dupa_suma()
test_sold_zi_data()
test_suma_dupa_tip()
test_undo()
test_selectie_dupa_tip()
test_elimina_dupa_tip()
test_sume_mai_mari()
test_adauga_tranzactie()
test_genereaza_tranzactii()
test_tranzactie_dupa_zi_si_suma()
test_sterge_dupa_tip()
test_sterge_tranzactie_din_perioada_data()
test_sterge_tranzactiile_din_ziua_data()
test_filtreaza_dupa_tip_si_suma()
