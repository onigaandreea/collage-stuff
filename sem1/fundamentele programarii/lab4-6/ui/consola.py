from domain.tranzactie import creaza_tranzactie
from domain.cont import adauga_tranzactie, sume_mai_mari, tranzactie_dupa_zi_si_suma, selectie_dupa_tip, \
    sterge_dupa_tip, sterge_tranzactiile_din_ziua_data, sterge_tranzactie_din_perioada_data, setup_cont, \
    elimina_dupa_tip, filtreaza_dupa_tip_si_suma, undo, get_lista_tranzactii, suma_dupa_tip, sold_zi_data, \
    ordonare_dupa_suma, adauga_tranzactie1


def print_menu():
    print("1. Adauga tranzactie noua")
    print("2. Sterge tranzactii")
    print("3. Cauta tranzactii")
    print('4. Rapoarte')
    print("5. Filtreaza")
    print("6. Undo")
    print("P. Afiseaza lista curenta")
    print("I. Iesire din aplicatie")


def print_sterge():
    print("1. Sterge tranzactiile dintr-o zi specificata")
    print("2. sterge tranzactiile dintr-o perioada data")
    print("3. Sterge tranzactiile de un anumit tip")


def print_cauta():
    print("1. Tipareste tranzactiile cu sume mai mari decat o suma data")
    print("2. Tipareste ttranzactiile inainte de o zi si cu sume mai mari decat o suma data")
    print("3. Tipareste tranzactiile de un anumit tip")


def print_filtreaza():
    print('1. Elimina tranzactiile de un anumit tip')
    print('2. Elimina tranzactiile de un anumit tip si cu sume mai mici decat o suma data')


def print_rapoarte():
    print('1. Suma totală a tranzacțiilor de un anumit tip')
    print('2. Soldul contului la o dată specificată')
    print('3. Tipărește toate tranzacțiile de un anumit tip ordonat după sumă')


def print_list(lista):
    for i, tranzactie in enumerate(lista):
        print(i, 'Ziua: ', tranzactie['ziua'], 'Suma: ', tranzactie['suma'], 'Tip: ', tranzactie['tip'])


def adauga_tranzactii_ui(cont):
    # citim datele tranzactiei
    ziua = int(input("Ziua efectuarii tranzactiei este: "))
    suma = int(input("Suma tranzactiei este: "))
    tip = input("Tipul tranzactiei este: ")

    tranzactie = creaza_tranzactie(ziua, suma, tip)
    adauga_tranzactie(cont, tranzactie)


def sume_mai_mari_ui(cont):
    suma = float(input("Suma minima este: "))
    sume_mai_mari(cont, suma)


def tranzactii_dupa_zi_si_suma_ui(cont):
    zi = int(input("Ziua este:"))
    suma = float(input("Suma este:"))
    tranzactie_dupa_zi_si_suma(cont, zi, suma)


def selectie_dupa_tip_ui(cont):
    tip = input("Tipul este: ")
    selectie_dupa_tip(cont, tip)


def sterge_dupa_zi_ui(cont):
    zi = int(input("Ziua din care vreti sa stergeti este: "))
    sterge_tranzactiile_din_ziua_data(cont, zi)


def sterge_dupa_perioada_ui(cont):
    zi_inceput = int(input("Ziua in care incepe perioda este: "))
    zi_sfarsit = int(input("Ziua in care se termina perioada este: "))
    sterge_tranzactie_din_perioada_data(cont, zi_inceput, zi_sfarsit)


def sterge_dupa_tip_ui(cont):
    tip = input("Tipul pe care vreti sa-l stergeti este: ")
    sterge_dupa_tip(cont, tip)


def elimina_dupa_tip_ui(cont, i):
    tip = input('Tipul pe care vreti sa-l eliminati este: ')
    elimina_dupa_tip(cont, i, tip)


def filtreaza_dupa_tip_si_suma_ui(cont):
    tip = input("Tipul dupa care se filtreaza este: ")
    suma = int(input("Suma dupa care se face filtrarea este: "))
    filtreaza_dupa_tip_si_suma(cont, tip, suma)


def suma_dupa_tip_ui(cont):
    tip = input('Tipul pentru care se calculeaza suma este: ')
    suma = suma_dupa_tip(cont, tip)
    return suma


def sold_zi_data_ui(cont):
    ziua = int(input('Ziua pentru care se calculeaza soldul este: '))
    sold = sold_zi_data(cont, ziua)
    return sold


def ordonare_dupa_suma_ui(cont):
    tip = input('Tipul dorit este: ')
    ordonare_dupa_suma(cont, tip)


def start():
    cont = setup_cont(True)
    finished = False
    while not finished:
        print_menu()
        opt = input("Optiunea dumneavoastra este: ")
        if opt == "1":
            adauga_tranzactii_ui(cont)
            print("Tranzactia s-a adaugat cu succes.")
        elif opt == "2":
            print_sterge()
            opt2 = int(input("Optiunea dumneavoastra este:"))
            if opt2 == 1:
                sterge_dupa_zi_ui(cont)
                print("Tranzactia s-a sters cu succes")
            elif opt2 == 2:
                sterge_dupa_perioada_ui(cont)
                print("Tranzactia s-a sters cu succes")
            elif opt2 == 3:
                sterge_dupa_tip_ui(cont)
                print("Tranzactia s-a sters cu succes")
            else:
                print("Optiune invalida.")
        elif opt == "3":
            print_cauta()
            opt3 = int(input("Optiunea dumneavoastra este:"))
            if opt3 == 1:
                sume_mai_mari_ui(cont)
                print_list(get_lista_tranzactii(cont))
            elif opt3 == 2:
                tranzactii_dupa_zi_si_suma_ui(cont)
                print_list(get_lista_tranzactii(cont))
            elif opt3 == 3:
                selectie_dupa_tip_ui(cont)
                print_list(get_lista_tranzactii(cont))
            else:
                print("Optiune invalida.")
        elif opt == '4':
            print_rapoarte()
            opt4 = int(input('Optiunea dumneavostra este: '))
            if opt4 == 1:
                suma = suma_dupa_tip_ui(cont)
                print('Suma este: ', suma)
            elif opt4 == 2:
                sold = sold_zi_data_ui(cont)
                print('Soldul este: ', sold)
            elif opt4 == 3:
                ordonare_dupa_suma_ui(cont)
                print_list(get_lista_tranzactii(cont))
            else:
                print('Optiune invalida')
        elif opt == '5':
            print_filtreaza()
            opt5 = int(input('Optiunea dumneavostra este: '))
            if opt5 == 1:
                elimina_dupa_tip_ui(cont, 'tip')
                print('Eliminarea s-a realizat cu succes')
            elif opt5 == 2:
                filtreaza_dupa_tip_si_suma_ui(cont)
                print('Eliminarea s-a realizat cu succes')
        elif opt == '6':
            undo(cont)
            print('S-a efectuat un undo')
        elif opt == "P":
            print_list(get_lista_tranzactii(cont))
        elif opt == 'I':
            finished = True
        else:
            print("Optiune invalida.")


def adauga_tranzactii_ui_2(cont, optiune):
    ziua = int(optiune[1])
    suma = int(optiune[2])
    tip = optiune[3]

    tranzactie = creaza_tranzactie(ziua, suma, tip)
    adauga_tranzactie(cont, tranzactie)


def sterge_dupa_tip_ui_2(cont, optiune):
    tip = optiune[1]
    sterge_dupa_tip(cont, tip)


def sume_mai_mari_ui_2(cont, optiune):
    suma = int(optiune[1])
    sume_mai_mari(cont, suma)


def sold_zi_data_ui_2(cont, optiune):
    ziua = int(optiune[1])
    sold = sold_zi_data(cont, ziua)
    return sold


def elimina_dupa_tip_ui_2(cont, i, optiune):
    tip = optiune[1]
    elimina_dupa_tip(cont, i, tip)


def start2():
    cont = setup_cont(True)
    finished = False
    while not finished:
        print('Comenzi disponibile: adauga, sterge, cauta, raport, filtrare, undo, printeaza, iesire')
        optiune = input()
        optiune = optiune.split()
        if optiune[0].lower() == 'adauga':
            adauga_tranzactii_ui_2(cont, optiune)
            print('Tranzactia a fost adaugata cu succes.')
        elif optiune[0].lower() == 'sterge':
            sterge_dupa_tip_ui_2(cont, optiune)
            print('Stergerea s-a efectuat cu succes.')
        elif optiune[0].lower() == 'cauta':
            sume_mai_mari_ui_2(cont, optiune)
            print_list(get_lista_tranzactii(cont))
        elif optiune[0].lower() == 'raport':
            sold = sold_zi_data_ui_2(cont, optiune)
            print('Soldul este: ', sold)
        elif optiune[0].lower() == 'filtrare':
            elimina_dupa_tip_ui_2(cont, 'tip', optiune)
            print('Eliminarea s-a efectuat cu succes.')
        elif optiune[0].lower() == 'undo':
            undo(cont)
            print('S-a efectuat undo.')
        elif optiune[0].lower() == 'printeaza':
            print_list(get_lista_tranzactii(cont))
        elif optiune[0].lower() == 'iesire':
            finished = True
        else:
            print('Optiune invalida.')
