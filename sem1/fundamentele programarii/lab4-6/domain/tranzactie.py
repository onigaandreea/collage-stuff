def creaza_tranzactie(ziua, suma, tip):
    """
    creaza o  tranzactie, un dictionar
    :param ziua: ziua in care se face tranzactia
    :type: int
    :param suma: suma
    :type: float
    :param tip: tipul tranzactiei
    :type: string
    :return: o noua tranzactie
    :rtype: dict
    """
    return {'ziua': ziua, 'suma': suma, 'tip': tip}


def validare_tranzactie(tranzactie):
    """
    Valideaza o tranzactie data
    :param tranzactie: tranzactia pentru care se verifica
    :type tranzactie: dict
    :return:-
    :rtype:
    :raises: ValueError daca tranzactia are date invalide
    """
    # ziua tranzactie: intre 1 si 31, numar intreg
    # suma: un numar pozitiv
    # tipul: intrare, iesire
    errors = []
    if (get_zi(tranzactie) < 1 or get_zi(tranzactie) > 31) and type(get_zi(tranzactie) == int):
        errors.append('Ziua tranzactiei trebuie sa fie un numar intreg intre 1 si 31.')
    if get_suma(tranzactie) < 0:
        errors.append('Suma data trebuie sa fie un numar pozitiv.')
    if get_tip(tranzactie) not in ['intrare', 'iesire']:
        errors.append('Tipul nu este din lista de tipuri acceptate.')

    if len(errors) > 0:
        error_string = '\n'.join(errors)
        raise ValueError(error_string)


def get_zi(tranzactie):
    return tranzactie['ziua']


def get_suma(tranzactie):
    return tranzactie['suma']


def get_tip(tranzactie):
    return tranzactie['tip']


def set_zi(tranz, val_noua):
    tranz['ziua'] = val_noua


def set_suma(tranz, val_noua):
    tranz['suma'] = val_noua


def set_tip(tranz, val_noua):
    tranz['tip'] = val_noua


def test_creaza_tranzactie():

    a1 = creaza_tranzactie(12, 200, "intrare")
    assert type(a1) == dict
    assert (a1['ziua'] == 12)
    assert (a1['suma'] == 200)
    assert (a1['tip'] == "intrare")


def test_validare_tranzactie():
    tranzactie1 = creaza_tranzactie(-12, 200, 'intrare')
    try:
        validare_tranzactie(tranzactie1)
    except ValueError as ve:
        assert (str(ve) == 'Ziua tranzactiei trebuie sa fie un numar intreg intre 1 si 31.')

    tranzactie2 = creaza_tranzactie(12, -20, 'iesire')
    try:
        validare_tranzactie(tranzactie2)
    except ValueError as ve:
        assert (str(ve) == 'Suma data trebuie sa fie un numar pozitiv.')

    tranzactie3 = creaza_tranzactie(13, 200, 'safdyu')
    try:
        validare_tranzactie(tranzactie3)
    except ValueError as ve:
        assert (str(ve) == 'Tipul nu este din lista de tipuri acceptate.')

    tranzactie4 = creaza_tranzactie(34, -15, 'intrare')
    try:
        validare_tranzactie(tranzactie4)
    except ValueError as ve:
        assert (str(ve) == 'Ziua tranzactiei trebuie sa fie un numar intreg intre 1 si 31.\n'
                           'Suma data trebuie sa fie un numar pozitiv.')


test_creaza_tranzactie()
test_validare_tranzactie()
