class Movie:
    def __init__(self, id, titlu, descriere, gen):
        """
        Creeaza un nou film cu numarul de identificare id, denumirea titlu si genul in care se incadreaza gen
        :param id: numar de identificare
        :type id: str
        :param titlu: denumirea filmului
        :type titlu: str
        :param descriere: descrierea succinta a filmului
        :type descriere: str
        :param gen: genul filmului
        :type gen: str
        """
        self.__id = id
        self.__titlu = titlu
        self.__descriere = descriere
        self.__gen = gen

    def getId(self):
        return self.__id

    def getTitlu(self):
        return self.__titlu

    def getDescriere(self):
        return self.__descriere

    def getGen(self):
        return self.__gen

    def setId(self, value):
        self.__id = value

    def setTitlu(self, value):
        self.__titlu = value

    def setDescriere(self, value):
        self.__descriere = value

    def setGen(self, value):
        self.__gen = value

    def __eq__(self, other):
        """
        Verifica egalitatea
        :param other: filmul cu care se compara
        :type other: Movie
        :return: True daca filmul curent este egal cu filmul other
                 False altfel
        :rtype: bool
        """
        if self.__id == other.getId():
            return True
        return False


def test_create_movie():
    m = Movie('1', 'Reminiscence', 'nice', 'SF, Romantic, Thriller')
    assert (m.getId() == '1')
    assert (m.getTitlu() == 'Reminiscence')
    assert (m.getDescriere() == 'nice')
    assert (m.getGen() == 'SF, Romantic, Thriller')

    m.setId('1')
    m.setTitlu('Reminiscence')
    m.setDescriere('nice')
    m.setGen('SF, Romantic, Thriller')

    assert (m.getId() == '1')
    assert (m.getTitlu() == 'Reminiscence')
    assert (m.getDescriere() == 'nice')
    assert (m.getGen() == 'SF, Romantic, Thriller')


def test_equal_movie():
    a = Movie('1', 'Reminiscence ', 'nice', 'SF, Romantic, Thriller')
    b = Movie('1', 'No Time to Die', 'very good', 'Actiune, Thriller')

    assert (a == b)

    c = Movie('2', 'No Time to Die', 'very good', 'Actiune, Thriller')
    assert (a != c)


test_create_movie()
test_equal_movie()


class Client:
    def __init__(self, id, nume, CNP):
        """
        Creeaza un nou client, cu numarul de ifentificare id, numele nume(nume si prenume) si CNP-ul CNP
        :param id: numar de identificare
        :param nume: numele si prenumele clientului
        :param CNP: CNP-ul clientului
        """
        self.__id = id
        self.__nume = nume
        self.__CNP = CNP

    def getId(self):
        return self.__id

    def getNume(self):
        return self.__nume

    def getCNP(self):
        return self.__CNP

    def setId(self, value):
        self.__id = value

    def setNume(self, value):
        self.__nume = value

    def setCNP(self, value):
        self.__CNP = value

    def __eq__(self, other):
        """
        Verifica egalitatea
        :param other: filmul cu care se compara
        :type other: Movie
        :return: True daca filmul curent este egal cu filmul other
                False altfel
        :rtype: bool
        """
        if self.__id == other.getId():
            return True
        return False


def test_create_client():
    c = Client('1', 'Oniga Andreea', '9206473849647')

    assert (c.getId() == '1')
    assert (c.getNume() == 'Oniga Andreea')
    assert (c.getCNP() == '9206473849647')

    c.setId('1')
    c.setNume('Oniga Andreea')
    c.setCNP('9206473849647')

    assert (c.getId() == '1')
    assert (c.getNume() == 'Oniga Andreea')
    assert (c.getCNP() == '9206473849647')


def test_equal_client():
    a = Client('1', 'Oniga Andreea', '9206473849647')
    b = Client('1', 'Marginean Sebastian', '5011017011825')

    assert (a == b)

    c = Client('2', 'Marginean Sebastian', '5011017011825')

    assert (a != c)


test_create_client()
test_equal_client()


class Inchiriere:
    def __init__(self, id_client,nume_client, id_film, titlu):
        self.__IdClient = id_client
        self.__numeClient = nume_client
        self.__IdFilm = id_film
        self.__Titlu = titlu

    def getIdClient(self):
        return self.__IdClient

    def getNumeClient(self):
        return self.__numeClient

    def getIdFilm(self):
        return self.__IdFilm

    def getTitlu(self):
        return self.__Titlu

    def setIdClient(self, value):
        self.__IdClient = value

    def setNumeClient(self, value):
        self.__numeClient = value

    def setIdFilm(self, value):
        self.__IdFilm = value

    def setTitlu(self, value):
        self.__Titlu = value


def test_create_inchiriere():
    c = Client('1', 'Oniga Andreea', '0234524356345')
    f = Movie('1', 'Anne', 'nice', 'romantic')
    idC = c.getId()
    nume = c.getNume()
    idF = f.getId()
    titlu = f.getTitlu()
    inchiriere_test = Inchiriere(idC,nume, idF, titlu)

    assert (inchiriere_test.getIdClient() == idC)
    assert (inchiriere_test.getIdFilm() == idF)


test_create_inchiriere()
