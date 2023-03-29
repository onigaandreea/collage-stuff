from domain.entitati import Client
from domain.validatori import ClientValidator
from repository.client_repo import InMemoryRepository_client
import random
import string


def string_generator(size, char=string.ascii_letters):
    return ''.join(random.choice(char) for x in range(size))


def nr_generator(size, char=string.digits):
    return ''.join(random.choice(char) for x in range(size))


def generate_nume():
    return string_generator(1).upper() + string_generator(random.randint(3, 7)).lower() + ' ' + \
           string_generator(1).upper() + string_generator(random.randint(3, 7)).lower()


def generate_CNP():
    return nr_generator(13)


class ClientService:
    """
    Responsabil de efectuarea operatiilor cerute de utilizator
    Coordoneaza operatiile necesare pentru a realiza actiunea declansata de utilizator

    """

    def __init__(self, repo, val):
        """
        Initializeaza service
        :param repo: obiectul de tip repo care ne ajuta sa gestionam lista de clienti
        :type repo: InMemoryRepository_client
        :param val: validator pentru verificarea clientilor
        :type val: ClientValidator
        """
        self.__repo = repo
        self.__validator = val

    def add_client(self, id, nume, CNP):
        """
        Adaugare client in lista de clienti
        :param id: id-ul clientului
        :type id: str
        :param nume: numele clintului
        :type nume: str
        :param CNP: CNP-ul clientului
        :type CNP: str
        :return: obiectul de tip Client creat
        :rtype: -;
        :raises: ValueError daca client nu este valid
        """

        a = Client(id, nume, CNP)

        self.__validator.validate(a)
        self.__repo.store(a)

        return a

    def get_all_clients(self):
        """
        Returneaza o lista cu toti clientii
        :return: lista de clienti
        :rtype: list of Client objects
        """
        return self.__repo.get_all_clients()

    def update_client(self, id, nume, CNP):
        """
        Modifica datele unui client
        :param id: id-ul cllientului de modificat
        :type id:str
        :param nume: numele nou al clientului
        :type nume: str
        :param CNP: descrierea noua a filmului
        :type CNP: str
        :return: clientul modificat
        :rtype: Client
        :raises: ValueError daca nu exista clientul cu id-ul dat
        """
        a = Client(id, nume, CNP)

        self.__validator.validate(a)
        return self.__repo.update(id, a)

    def find_by_id(self, id):
        """
        Cauta clientul cu id-ul dat
        :param id: id dat
        :type id: str
        :return: clientul cu identificatorul id/None daca nu exista clientul
        :rtype: Client
        """
        return self.__repo.find(id)

    def delete_by_id(self, id):
        """
        Sterge clientul dupa id
        :param id: id-ul dat
        :type id: str
        :return:
        :rtype:
        """
        return self.__repo.delete_by_id(id)

    def filter_by_CNP(self, cifra):
        """
        Selecteaza filmele care au genul dat
        :param cifra: cifra cu care sa inceapa CNP-ul
        :type cifra: str
        :return:
        :rtype:
        """
        clients = self.__repo.get_all_clients()
        clients_with_good_CNP = [client for client in clients if client.getCNP().startswith(cifra)]
        return clients_with_good_CNP


def test_add_client():
    repo = InMemoryRepository_client()
    validator = ClientValidator()
    test_srv = ClientService(repo, validator)

    added_client = test_srv.add_client('1', 'Oniga Andreea', '6935620584738')
    assert (added_client.getId() == '1')
    assert (len(test_srv.get_all_clients()) == 1)

    try:
        added_client = test_srv.add_client('0', '', '')
        assert False
    except ValueError:
        assert True


def test_get_all_clients():
    repo = InMemoryRepository_client()
    validator = ClientValidator()
    test_srv = ClientService(repo, validator)

    added_client = test_srv.add_client('1', 'Oniga Andreea', '6935620584738')
    assert (len(test_srv.get_all_clients()) == 1)

    added_client2 = test_srv.add_client('2', 'Marginean Sebastian', '5011017011825')
    assert (len(test_srv.get_all_clients()) == 2)
    assert (test_srv.get_all_clients()[0].getNume() == 'Oniga Andreea')
    assert (test_srv.get_all_clients()[1].getNume() == 'Marginean Sebastian')

    updated_client = test_srv.update_client('1', 'Oniga Andreea', '6935620584744')
    assert (len(test_srv.get_all_clients()) == 2)
    assert (test_srv.get_all_clients()[0].getCNP() == '6935620584744')

    assert (test_srv.get_all_clients()[0].getNume() == 'Oniga Andreea')

    deleted_client = test_srv.delete_by_id('2')
    assert (len(test_srv.get_all_clients()) == 1)
    assert (test_srv.get_all_clients()[0].getNume() == 'Oniga Andreea')


def test_delete_client_by_id():
    repo = InMemoryRepository_client()
    validator = ClientValidator()
    test_srv = ClientService(repo, validator)

    added_client = test_srv.add_client('1', 'Oniga Andreea', '6935620584738')
    added_client2 = test_srv.add_client('2', 'Marginean Sebastian', '5011017011825')

    deleted_client = test_srv.delete_by_id('1')
    assert (len(test_srv.get_all_clients()) == 1)

    assert (deleted_client.getNume() == 'Oniga Andreea')
    assert (deleted_client.getCNP() == '6935620584738')

    assert (test_srv.get_all_clients()[0].getNume() == 'Marginean Sebastian')

    try:
        deleted_client = test_srv.delete_by_id('wrongid')
        assert False

    except ValueError:
        assert True


def test_update_client():
    repo = InMemoryRepository_client()
    validator = ClientValidator()
    test_srv = ClientService(repo, validator)

    added_client = test_srv.add_client('1', 'Oniga Andreea', '6935620584738')
    added_client2 = test_srv.add_client('2', 'Marginean Sebastian', '5011017011825')

    updated_client = test_srv.update_client('1', 'Oniga Andreea', '6935620584744')
    assert (len(test_srv.get_all_clients()) == 2)
    assert updated_client.getNume() == 'Oniga Andreea'
    assert updated_client.getCNP() == '6935620584744'

    modified_client_in_list = test_srv.find_by_id('1')
    unchanged_client = test_srv.find_by_id('2')

    assert (modified_client_in_list.getNume() == 'Oniga Andreea')
    assert (modified_client_in_list.getCNP() == '6935620584744')

    assert (unchanged_client.getNume() == 'Marginean Sebastian')
    assert (unchanged_client.getCNP() == '5011017011825')


def test_filter_by_CNP():
    repo = InMemoryRepository_client()
    validator = ClientValidator()
    test_srv = ClientService(repo, validator)

    added_client = test_srv.add_client('1', 'Oniga Andreea', '6935620584738')
    added_client2 = test_srv.add_client('2', 'Marginean Sebastian', '5011017011825')
    added_client3 = test_srv.add_client('3', 'Solea Andra', '9235462453672')
    added_client4 = test_srv.add_client('4', 'Oniga Maria', '9224536245678')

    filtered_list = test_srv.filter_by_CNP('9')
    assert (len(filtered_list) == 2)

    filtered_list = test_srv.filter_by_CNP('6')
    assert (len(filtered_list) == 1)


test_add_client()
test_get_all_clients()
test_filter_by_CNP()
test_update_client()
test_delete_client_by_id()
