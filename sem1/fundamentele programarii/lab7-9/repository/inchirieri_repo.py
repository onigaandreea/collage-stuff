from domain.entitati import Inchiriere, Client, Movie


class InchirieriInMemoryRepository:
    def __init__(self):
        self.__inchirieri = []

    def store(self, inchiriere):
        """
        adauga o pereche client-film in lista de inchirieri
        :param inchiriere: perechea client-film
        :return:
        """
        self.__inchirieri.append(inchiriere)

    def toate_inchirierile(self):
        """
        returneaza o lista cu toate perechile client-film
        :return:
        """
        return self.__inchirieri

    def size(self):
        """
        returneaza lungimea listei de inchirieri
        :return:
        """
        return len(self.__inchirieri)

    def update(self, inchiriere, inchiriere_noua):
        """
        actualizarea inchirierii cu inchirierea noua
        :param inchiriere: inchirierea de actualizat
        :param inchiriere_noua: inchirierea care va rezulta dupa actualizare
        :return:
        """
        inchiriere.setIdFilm(inchiriere_noua.getIdFilm())
        inchiriere.setIdClient(inchiriere_noua.getIdClient())
        inchiriere.setNumeClient(inchiriere_noua.getNumeClient())
        inchiriere.setTitlu(inchiriere_noua.getTitlu())
        return inchiriere

    def find_client(self, idclient):
        """
        Cauta inchirierile cu id clientului dat
        :param idclient: id dat
        :type idclient: str
        :return:
        :rtype:
        """
        inchirieri = []
        for inchiriere in self.__inchirieri:
            if inchiriere.getIdClient() == idclient:
                inchirieri.append(inchiriere)
        return inchirieri

    def find_movie(self, idfilm):
        """
        Cauta inchirierile cu id ul filmului dat
        :param idfilm: id dat
        :type idfilm: str
        :return:
        :rtype:
        """
        inchirieri = []
        for inchiriere in self.__inchirieri:
            if inchiriere.getIdFilm() == idfilm:
                inchirieri.append(inchiriere)
        return inchirieri

    def delete_all(self):
        """
        Sterge toate filmele din lista
        """
        self.__inchirieri.clear()

    def returnare(self, idclient, idfilm):
        returnari = []
        for inchiriere in self.__inchirieri:
            if inchiriere.getIdClient() == idclient and inchiriere.getIdFilm() == idfilm:
                self.__inchirieri.remove(inchiriere)
                return inchiriere


def test_store():
    inchirieri_repo = InchirieriInMemoryRepository()
    c = Client('1', 'Oniga Andreea', '0234524356345')
    f = Movie('1', 'Anne', 'nice', 'romantic')
    idC = c.getId()
    nume = c.getNume()
    idF = f.getId()
    titlu = f.getTitlu()
    i1 = Inchiriere(idC, nume, idF, titlu)
    c = Client('2', 'Oniga Maria', '0234524344345')
    f = Movie('1', 'Anne', 'nice', 'romantic')
    idC = c.getId()
    nume = c.getNume()
    idF = f.getId()
    titlu = f.getTitlu()
    i2 = Inchiriere(idC, nume, idF, titlu)
    inchirieri_repo.store(i1)
    assert (inchirieri_repo.size() == 1)
    inchirieri_repo.store(i2)
    assert (inchirieri_repo.size() == 2)


def test_toate_inchirierile():
    test_repo = InchirieriInMemoryRepository()
    c = Client('1', 'Oniga Andreea', '0234524356345')
    f = Movie('1', 'Anne', 'nice', 'romantic')
    idC = c.getId()
    nume = c.getNume()
    idF = f.getId()
    titlu = f.getTitlu()
    i1 = Inchiriere(idC, nume, idF, titlu)
    c = Client('2', 'Oniga Maria', '0234524344345')
    f = Movie('1', 'Anne', 'nice', 'romantic')
    idC = c.getId()
    nume = c.getNume()
    idF = f.getId()
    titlu = f.getTitlu()
    i2 = Inchiriere(idC, nume, idF, titlu)

    test_repo.store(i1)
    test_repo.store(i2)

    inchirieri = test_repo.toate_inchirierile()

    assert (type(inchirieri) == list)
    assert (len(inchirieri) == 2)


test_toate_inchirierile()
test_store()
