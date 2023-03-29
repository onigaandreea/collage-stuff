from domain.entitati import Inchiriere
from repository.inchirieri_repo import InchirieriInMemoryRepository


class InchireriService:
    """
    Responsabil de efectuarea operatiilor cerute de utilizator
    Coordoneaza operatiile necesare pentru a realiza actiunea declansata de utilizator

    """

    def __init__(self, repo_inchiriere, repo_movie, repo_client):

        self.__repo_inchiriere = repo_inchiriere
        self.__repo_movie = repo_movie
        self.__repo_client = repo_client

    def add_inchiriere(self, idclient, idfilm):
        """
        adauga o pereche client-film
        :param idclient: id ul clientului care inchiriaza filmul
        :param idfilm: id ul filmului inchiriat
        :return:
        """
        client = self.__repo_client.find(idclient)
        if client is None:
            raise ValueError('Nu exista acest client.')
        else:
            nume = client.getNume()

        film = self.__repo_movie.find(idfilm)
        if film is None:
            raise ValueError('Nu exista acest film.')
        else:
            titlu = film.getTitlu()

        a = Inchiriere(idclient, nume, idfilm, titlu)

        self.__repo_inchiriere.store(a)
        return a

    def toate_inchirierile(self):
        """
        Returneaza o lista cu toate inchirierile
        :return: lista de inchirieri
        :rtype: list of Inchiriere objects
        """
        return self.__repo_inchiriere.toate_inchirierile()

    def update_inchiriere(self, idclient, idfilm, idclientnou, idfilmnou):
        """
        actualizarea perechii idclient-idfilm
        :param idclient: id ul clientului de actualizat
        :param idfilm: id ul filmului de actualizat
        :param idclientnou: id ul nou al clientului
        :param idfilmnou: id ul nou al filmului
        :return:
        """
        client = self.__repo_client.find(idclient)
        nume = client.getNume()
        film = self.__repo_movie.find(idfilm)
        titlu = film.getTitlu()
        a = Inchiriere(idclient, nume, idfilm, titlu)
        client = self.__repo_client.find(idclientnou)
        nume_nou = client.getNume()
        film = self.__repo_movie.find(idfilmnou)
        titlu_nou = film.getTitlu()
        b = Inchiriere(idclientnou, nume_nou, idfilmnou, titlu_nou)
        return self.__repo_inchiriere.update(a, b)

    def find_by_id_client(self, idclient):
        """
        Cauta clientul cu id-ul dat
        :param idclient: id dat
        :type idclient: str
        :return: clientul cu identificatorul id/None daca nu exista clientul
        :rtype: Client
        """
        return self.__repo_inchiriere.find_client(idclient)

    def find_by_id_film(self, idfilm):
        """
        Cauta filmul cu id-ul dat
        :param idfilm: id dat
        :type idfilm: str
        :return:
        :rtype:
        """
        return self.__repo_inchiriere.find_movie(idfilm)

    def returnare(self, idclient, idfilm):

        return self.__repo_inchiriere.returnare(idclient, idfilm)

    def delete_all(self):
        return self.__repo_inchiriere.delete_all()



