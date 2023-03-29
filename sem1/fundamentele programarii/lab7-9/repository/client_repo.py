from domain.entitati import Client


class InMemoryRepository_client:
    """
    Clasa creata cu responsabilitatea de a gestiona
    multimea de clienti
    """

    def __init__(self):

        self.__clients = []

    def __exists_id(self, id):
        for client in self.__clients:
            if client.getId() == id:
                return True
        return False

    def find(self, id):
        """
        Cauta clientul cu id dat
        :param id: id dat
        :type id: str
        :return: clientul cu identificator id/None daca nu exista
        :rtype: Client
        """
        for client in self.__clients:
            if client.getId() == id:
                return client
        return None

    def store(self, client):
        """
        Adauga un client in lista
        :param client: clientul de adaugat
        :type client: Client
        :return: -; lista de filme se modifica prin adaugarea filmului
        :rtype:
        :raises:
        """
        if client.getId() in map(lambda x: x.getId(), self.__clients):
            raise ValueError('Exista deja un client cu acest id')
        self.__clients.append(client)

    def get_all_clients(self):
        """
        Returneaza o lista cu toti clientii
        :rtype: list of Client objects
        """
        return self.__clients

    def size(self):
        """
        Returneaza numarul de clienti din lista
        :rtype: int
        """
        return len(self.__clients)

    def delete_by_id(self, id):
        """
        Sterge clientul cu id-ul dat din lista de clienti
        :param id: id-ul dat
        :type id: str
        :return: obiectul client sters
        :rtype: Client
        :raises: ValueError daca nu exista clientul cu id dat
        """
        if not self.__exists_id(id):
            raise ValueError('Nu exista client cu id-ul dat')

        client = self.find(id)
        self.__clients.remove(client)
        return client

    def update(self, id, new_client):
        """
        Modifica datele clientului cu identificator id
        :param id: id-ul dat
        :type id: str
        :param new_client: clientul cu noile date
        :type new_client: Client
        :return: clientul modificat
        :rtype: Client
        """
        client = self.find(id)
        if client is None:
            raise ValueError('Nu exista client cu acest id.')
        client.setNume(new_client.getNume())
        client.setCNP(new_client.getCNP())
        return client

    def delete_all(self):
        """
        Sterge toti clientii din lista
        """
        self.__clients.clear()

    def get_clients_by_criteria(self, filter_function):
        """
        Selecteaza elementele din lista care indeplinesc un criteriu
        :param filter_function: functia dupa care se filtreaza
        :type filter_function: function
        :return: lista de clienti care indeplinesc criteriul
        :rtype: list of Clients
        """
        return [client for client in self.__clients if filter_function(client)]


def test_store():
    test_repo = InMemoryRepository_client()
    a = Client('1', 'Oniga Andreea', '9206473849647')
    b = Client('2', 'Marginean Sebastian', '5011017011825')
    test_repo.store(a)

    assert (test_repo.size() == 1)

    test_repo.store(b)
    assert (test_repo.size() == 2)
    assert (test_repo.find(a.getId()) == a)

    c = Client('1', 'Solea Andra', '9235462453672')

    try:
        test_repo.store(c)
        assert False
    except ValueError:
        assert True


def test_delete_by_id():
    test_repo = InMemoryRepository_client()
    a = Client('1', 'Oniga Andreea', '9206473849647')
    b = Client('2', 'Marginean Sebastian', '5011017011825')
    test_repo.store(a)
    test_repo.store(b)

    deleted_client = test_repo.delete_by_id('1')
    assert (test_repo.size() == 1)
    assert (deleted_client.getId() == '1')
    assert (deleted_client.getNume() == 'Oniga Andreea')
    assert (deleted_client.getCNP() == '9206473849647')

    try:
        deleted_client = test_repo.delete_by_id('fjhs')
        assert False
    except ValueError:
        assert True


def test_update():
    test_repo = InMemoryRepository_client()
    a = Client('1', 'Oniga Andreea', '9206473849647')
    test_repo.store(a)
    b = Client('1', 'Marginean Sebastian', '5011017011825')

    updated_client = test_repo.update('1', b)
    assert (updated_client.getNume() == 'Marginean Sebastian')
    assert (updated_client.getCNP() == '5011017011825')

    try:
        updated_client = test_repo.update('7', a)
        assert False
    except ValueError:
        assert True


def test_get_all():
    test_repo = InMemoryRepository_client()
    a = Client('1', 'Oniga Andreea', '9206473849647')
    b = Client('2', 'Marginean Sebastian', '5011017011825')
    test_repo.store(a)
    assert (type(test_repo.get_all_clients()) == list)
    assert (len(test_repo.get_all_clients()) == 1)

    test_repo.store(b)
    assert (len(test_repo.get_all_clients()) == 2)
    assert (test_repo.get_all_clients()[0] == a)
    assert (test_repo.get_all_clients()[1] == b)

    test_repo.delete_by_id('2')
    assert (len(test_repo.get_all_clients()) == 1)


def test_get_size():
    test_repo = InMemoryRepository_client()
    a = Client('1', 'Oniga Andreea', '9206473849647')
    b = Client('2', 'Marginean Sebastian', '5011017011825')
    test_repo.store(a)
    assert (test_repo.size() == 1)

    test_repo.store(b)
    assert (test_repo.size() == 2)

    test_repo.delete_by_id('1')
    assert (test_repo.size() == 1)

    c = Client('2', 'Solea Andra', '9235462453672')

    test_repo.update('2', c)
    assert (test_repo.size() == 1)


def test_delete_all():
    test_repo = InMemoryRepository_client()
    a = Client('1', 'Oniga Andreea', '9206473849647')
    b = Client('2', 'Marginean Sebastian', '5011017011825')
    test_repo.store(a)
    test_repo.store(b)
    assert (test_repo.size() == 2)
    test_repo.delete_all()
    assert (test_repo.size() == 0)


def setup_test_repo():
    a1 = Client('1', 'Oniga Andreea', '9206473849647')
    a2 = Client('2', 'Marginean Sebastian', '5011017011825')
    a3 = Client('3', 'Solea Andra', '9235462453672')
    a4 = Client('4', 'Oniga Maria', '9224536245678')
    a5 = Client('5', 'Aloman Dumitru', '9354245682935')
    a6 = Client('6', 'Avram Daniel', '9134253679866')
    a7 = Client('7', 'Oltean Adina', '2341567234789')
    a8 = Client('8', 'Cristea Adrian', '2451367778923')

    test_repo = InMemoryRepository_client()
    test_repo.store(a1)
    test_repo.store(a2)
    test_repo.store(a3)
    test_repo.store(a4)
    test_repo.store(a5)
    test_repo.store(a6)
    test_repo.store(a7)
    test_repo.store(a8)
    return test_repo


def test_filter_by_criteria():
    test_repo = setup_test_repo()

    filter_by_name = lambda x: 'Oniga' in x.getNume()
    filtered_list = test_repo.get_clients_by_criteria(filter_by_name)
    assert (len(filtered_list) == 2)
    assert (test_repo.size() == 8)

    filter_by_CNP = lambda x: x.getCNP().startswith('9')
    filtered_list = test_repo.get_clients_by_criteria(filter_by_CNP)
    assert (len(filtered_list) == 5)
    assert (test_repo.size() == 8)


test_store()
test_get_all()
test_get_size()
test_delete_by_id()
test_delete_all()
test_update()
test_filter_by_criteria()
