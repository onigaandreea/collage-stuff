import random
import string

from domain.entitati import Movie
from domain.validatori import MovieValidator
from repository.movie_repo import InMemoryRepository_movie


def string_generator(size, char=string.ascii_letters):
    return ''.join(random.choice(char) for x in range(size))


def generate_title():
    return string_generator(1).upper() + string_generator(random.randint(3, 7)).lower()


def generate_description():
    return string_generator(1).upper() + string_generator(random.randint(3, 7)).lower()


class MovieService:
    """
    Responsabil de efectuarea operatiilor cerute de utilizator
    Coordoneaza operatiile necesare pentru a realiza actiunea declansata de utilizator

    """
    def __init__(self, repo, val):
        """
        Initializeaza service
        :param repo: obiectul de tip repo care ne ajuta sa gestionam lista de filme
        :type repo: InMemoryRepository_movie
        :param val: validator pentru verificarea filmelor
        :type val: MovieValidator
        """
        self.__repo = repo
        self.__validator = val

    def add_movie(self, id, titlu, descriere, gen):
        """
        Adaugare film in lista de filme
        :param id: id-ul filmului
        :type id: str
        :param titlu: titlul filmului
        :type titlu: str
        :param descriere: descrierea filmului
        :type descriere: str
        :param gen: genul filmului
        :type gen: str
        :return: obiectul de tip Movie creat
        :rtype: -;
        :raises: ValueError daca filmul nu este valid
        """

        a = Movie(id, titlu, descriere, gen)

        self.__validator.validate(a)
        self.__repo.store(a)

        return a

    def get_all_movies(self):
        """
        Returneaza o lista cu toate filmele
        :return: lista de filme
        :rtype: list of Movie objects
        """
        return self.__repo.get_all_movies()

    def update_movie(self, id, titlu, descriere, gen):
        """
        Modifica datele unui film
        :param id: id-ul filmului de modificat
        :type id:str
        :param titlu: titlul nou al filmului
        :type titlu: str
        :param descriere: descrierea noua a filmului
        :type descriere: str
        :param gen: genul filmului
        :type gen: str
        :return: filmul modificat
        :rtype: Movie
        :raises: ValueError daca nu exista filmul cu id-ul dat
        """
        a = Movie(id, titlu, descriere, gen)

        self.__validator.validate(a)
        return self.__repo.update(id, a)

    def find_by_id(self, id):
        """
        Cauta filmul cu id-ul dat
        :param id: id dat
        :type id: str
        :return: filmul cu identificatorul id/None daca nu exista filmul
        :rtype: Movie
        """
        return self.__repo.find(id)

    def delete_by_id(self, id):
        """
        Sterge filmele dupa id
        :param id: id-ul dat
        :type id: str
        :return:
        :rtype:
        """
        return self.__repo.delete_by_id(id)

    def filter_by_genre(self, gen):
        """
        Selecteaza filmele care au genul dat
        :param gen: genul cu care se compara
        :type gen: str
        :return:
        :rtype:
        """
        movies = self.__repo.get_all_movies()
        movies_with_good_genre = [movie for movie in movies if gen in movie.getGen()]
        return movies_with_good_genre


def test_add_movie():
    repo = InMemoryRepository_movie()
    validator = MovieValidator()
    test_srv = MovieService(repo, validator)

    added_movie = test_srv.add_movie('1', 'Reminiscence', 'nice', 'SF, Romantic, Thriller')
    assert (added_movie.getId() == '1')
    assert (len(test_srv.get_all_movies()) == 1)

    try:
        added_movie = test_srv.add_movie(0, '', '', '')
        assert False
    except ValueError:
        assert True


def test_get_all_movies():
    repo = InMemoryRepository_movie()
    validator = MovieValidator()
    test_srv = MovieService(repo, validator)

    added_movie = test_srv.add_movie('1', 'Reminiscence', 'nice', 'SF, Romantic, Thriller')
    assert (len(test_srv.get_all_movies()) == 1)

    added_movie2 = test_srv.add_movie('2', 'Yes day', 'funny', 'Family')
    assert (len(test_srv.get_all_movies()) == 2)
    assert (test_srv.get_all_movies()[0].getTitlu() == 'Reminiscence')
    assert (test_srv.get_all_movies()[1].getTitlu() == 'Yes day')

    updated_movie = test_srv.update_movie('1', 'Reminiscence', 'nice', 'SF')
    assert (len(test_srv.get_all_movies()) == 2)
    assert (test_srv.get_all_movies()[0].getGen() == 'SF')

    assert (test_srv.get_all_movies()[0].getTitlu() == 'Reminiscence')

    deleted_movie = test_srv.delete_by_id('2')
    assert (len(test_srv.get_all_movies()) == 1)
    assert (test_srv.get_all_movies()[0].getTitlu() == 'Reminiscence')


def test_delete_movie_by_id():
    repo = InMemoryRepository_movie()
    validator = MovieValidator()
    test_srv = MovieService(repo, validator)

    added_movie = test_srv.add_movie('1', 'Reminiscence', 'nice', 'SF, Romantic, Thriller')
    added_movie2 = test_srv.add_movie('2', 'Yes day', 'funny', 'Family')

    deleted_movie = test_srv.delete_by_id('1')
    assert (len(test_srv.get_all_movies()) == 1)

    assert (deleted_movie.getTitlu() == 'Reminiscence')
    assert (deleted_movie.getDescriere() == 'nice')
    assert (deleted_movie.getGen() == 'SF, Romantic, Thriller')

    assert (test_srv.get_all_movies()[0].getTitlu() == 'Yes day')

    try:
        deleted_movie = test_srv.delete_by_id('wrongid')
        assert False

    except ValueError:
        assert True


def test_update_movie():
    repo = InMemoryRepository_movie()
    validator = MovieValidator()
    test_srv = MovieService(repo, validator)

    added_movie = test_srv.add_movie('1', 'Reminiscence', 'nice', 'SF, Romantic, Thriller')
    added_movie2 = test_srv.add_movie('2', 'Yes day', 'funny', 'Family')

    updated_movie = test_srv.update_movie('1', 'Reminiscence', 'nice', 'SF')
    assert (len(test_srv.get_all_movies()) == 2)
    assert updated_movie.getTitlu() == 'Reminiscence'
    assert updated_movie.getDescriere() == 'nice'
    assert updated_movie.getGen() == 'SF'

    modified_movie_in_list = test_srv.find_by_id('1')
    unchanged_movie = test_srv.find_by_id('2')

    assert (modified_movie_in_list.getTitlu() == 'Reminiscence')
    assert (modified_movie_in_list.getGen() == 'SF')

    assert (unchanged_movie.getTitlu() == 'Yes day')
    assert (unchanged_movie.getGen() == 'Family')


def test_filter_by_genre():
    repo = InMemoryRepository_movie()
    validator = MovieValidator()
    test_srv = MovieService(repo, validator)

    added_movie = test_srv.add_movie('1', 'Reminiscence', 'nice', 'SF, Romantic, Thriller')
    added_movie2 = test_srv.add_movie('2', 'Yes day', 'funny', 'Family')
    added_movie3 = test_srv.add_movie('3', 'No Time to Die', 'very good', 'Actiune, Thriller')
    added_movie4 = test_srv.add_movie('4', 'Dune', 'very good', 'SF')

    filtered_list = test_srv.filter_by_genre('SF')
    assert (len(filtered_list) == 2)

    filtered_list = test_srv.filter_by_genre('Actiune')
    assert (len(filtered_list) == 1)

    filtered_list = test_srv.filter_by_genre('Romantic')
    assert (len(filtered_list) == 1)

    filtered_list = test_srv.filter_by_genre('Comedie')
    assert (len(filtered_list) == 0)


test_add_movie()
test_get_all_movies()
test_filter_by_genre()
test_update_movie()
test_delete_movie_by_id()
