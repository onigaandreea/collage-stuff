from domain.entitati import Movie


class InMemoryRepository_movie:
    """
    Clasa creata cu responsabilitatea de a gestiona
    multimea de filme
    """

    def __init__(self):

        self.__movies = []

    def __exists_id(self, id):
        for movie in self.__movies:
            if movie.getId() == id:
                return True
        return False

    def find(self, id):
        """
        Cauta filmul cu id dat
        :param id: id dat
        :type id: str
        :return: filmul cu identificator id/None daca nu exista
        :rtype: Movie
        """
        for movie in self.__movies:
            if movie.getId() == id:
                return movie
        return None

    def store(self, movie):
        """
        Adauga un film in lista
        :param movie: filmul de adaugat
        :type movie: Movie
        :return: -; lista de filme se modifica prin adaugarea filmului
        :rtype:
        :raises:
        """
        if self.__exists_id(movie.getId()):
            raise ValueError('Exista deja un film cu acest id')

        self.__movies.append(movie)

    def get_all_movies(self):
        """
        Returneaza o lista cu toate filmele
        :rtype: list of Movie objects
        """
        return self.__movies

    def size(self):
        """
        Returneaza numarul de filme in lista
        :rtype: int
        """
        return len(self.__movies)

    def delete_by_id(self, id):
        """
        Sterge filmul cu id-ul dat din lista de filme
        :param id: id-ul dat
        :type id: str
        :return: obiectul film sters
        :rtype: Movie
        :raises: ValueError daca nu exista film cu id dat
        """
        if not self.__exists_id(id):
            raise ValueError('Nu exista film cu id-ul dat')

        movie = self.find(id)
        self.__movies.remove(movie)
        return movie

    def update(self, id, new_movie):
        """
        Modifica datele filmului cu identificator id
        :param id: id-ul dat
        :type id: str
        :param new_movie: filmul cu noile date
        :type new_movie: Movie
        :return: filmul modificat
        :rtype: Movie
        """
        movie = self.find(id)
        if movie is None:
            raise ValueError('Nu exista filmul cu acest id.')
        movie.setTitlu(new_movie.getTitlu())
        movie.setDescriere(new_movie.getDescriere())
        movie.setGen(new_movie.getGen())
        return movie

    def delete_all(self):
        """
        Sterge toate filmele din lista
        """
        self.__movies.clear()

    def get_movie_by_criteria(self, filter_function):
        """
        Selecteaza elementele din lista care indeplinesc un criteriu
        :param filter_function: functia dupa care se filtreaza
        :type filter_function: function
        :return: lista de filme care indeplinesc criteriul
        :rtype: list of Movies
        """
        return [movie for movie in self.__movies if filter_function(movie)]


def test_store():
    test_repo = InMemoryRepository_movie()
    m = Movie('1', 'Reminiscence', 'nice', 'SF, Romantic, Thriller')
    n = Movie('2', 'Yes day', 'funny', 'Family')
    test_repo.store(m)

    assert (test_repo.size() == 1)

    test_repo.store(n)
    assert (test_repo.size() == 2)
    assert (test_repo.find(m.getId()) == m)

    c = Movie('1', 'No Time to Die', 'very good', 'Actiune, Thriller')

    try:
        test_repo.store(c)
        assert False
    except ValueError:
        assert True


def test_delete_by_id():
    test_repo = InMemoryRepository_movie()
    m = Movie('1', 'Reminiscence', 'nice', 'SF, Romantic, Thriller')
    n = Movie('2', 'Yes day', 'funny', 'Family')
    test_repo.store(m)
    test_repo.store(n)

    deleted_movie = test_repo.delete_by_id('1')
    assert (test_repo.size() == 1)
    assert (deleted_movie.getId() == '1')
    assert (deleted_movie.getTitlu() == 'Reminiscence')
    assert (deleted_movie.getDescriere() == 'nice')
    assert (deleted_movie.getGen() == 'SF, Romantic, Thriller')

    try:
        deleted_movie = test_repo.delete_by_id('fjhs')
        assert False
    except ValueError:
        assert True


def test_update():
    test_repo = InMemoryRepository_movie()
    m = Movie('1', 'Reminiscence', 'nice', 'SF, Romantic, Thriller')
    test_repo.store(m)

    n = Movie('2', 'Yes day', 'funny', 'Family')
    updated_movie = test_repo.update('1', n)
    assert (updated_movie.getTitlu() == 'Yes day')
    assert (updated_movie.getDescriere() == 'funny')

    try:
        updated_movie = test_repo.update('7', m)
        assert False
    except ValueError:
        assert True


def test_get_all():
    test_repo = InMemoryRepository_movie()
    m = Movie('1', 'Reminiscence', 'nice', 'SF, Romantic, Thriller')
    n = Movie('2', 'Yes day', 'funny', 'Family')
    test_repo.store(m)
    assert (type(test_repo.get_all_movies()) == list)
    assert (len(test_repo.get_all_movies()) == 1)

    test_repo.store(n)
    assert (len(test_repo.get_all_movies()) == 2)
    assert (test_repo.get_all_movies()[0] == m)
    assert (test_repo.get_all_movies()[1] == n)

    test_repo.delete_by_id('2')
    assert (len(test_repo.get_all_movies()) == 1)


def test_get_size():
    test_repo = InMemoryRepository_movie()
    m = Movie('1', 'Reminiscence', 'nice', 'SF, Romantic, Thriller')
    n = Movie('2', 'Yes day', 'funny', 'Family')
    test_repo.store(m)
    assert (test_repo.size() == 1)

    test_repo.store(n)
    assert (test_repo.size() == 2)

    test_repo.delete_by_id('1')
    assert (test_repo.size() == 1)

    c = Movie('1', 'No Time to Die', 'very good', 'Actiune, Thriller')

    test_repo.update('2', c)
    assert (test_repo.size() == 1)


def test_delete_all():
    test_repo = InMemoryRepository_movie()
    m = Movie('1', 'Reminiscence', 'nice', 'SF, Romantic, Thriller')
    n = Movie('2', 'Yes day', 'funny', 'Family')
    test_repo.store(m)
    test_repo.store(n)
    assert (test_repo.size() == 2)
    test_repo.delete_all()
    assert (test_repo.size() == 0)


def setup_test_repo():

    a1 = Movie('1', 'Reminiscence', 'nice', 'SF, Romantic, Thriller')
    a2 = Movie('2', 'Yes day', 'funny', 'Family')
    a3 = Movie('3', 'No Time to Die', 'very good', 'Actiune, Thriller')
    a4 = Movie('4', 'Dune', 'very good', 'SF')
    a5 = Movie('5', 'Eternals', 'good', 'Actiune, SF')
    a6 = Movie('6', 'House of Gucci', 'well but not great', 'Thriller')
    a7 = Movie('7', 'Breathe', 'amazing', 'Romantic')
    a8 = Movie('8', 'Side effects', 'great', 'Drama')

    test_repo = InMemoryRepository_movie()
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

    filter_by_actiune = lambda x: 'Actiune' in x.getGen()
    filtered_list = test_repo.get_movie_by_criteria(filter_by_actiune)
    assert (len(filtered_list) == 2)
    assert (test_repo.size() == 8)

    filter_by_title = lambda x: x.getTitlu() == 'Reminiscence'
    filtered_list = test_repo.get_movie_by_criteria(filter_by_title)
    assert (len(filtered_list) == 1)
    assert (test_repo.size() == 8)

    filter_by_description = lambda x: x.getDescriere() == 'very good'
    filtered_list = test_repo.get_movie_by_criteria(filter_by_description)
    assert (len(filtered_list) == 2)
    assert (test_repo.size() == 8)


test_store()
test_get_all()
test_get_size()
test_delete_by_id()
test_delete_all()
test_update()
test_filter_by_criteria()
