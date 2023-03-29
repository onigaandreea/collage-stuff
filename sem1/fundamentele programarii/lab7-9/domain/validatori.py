from domain.entitati import Movie, Client


class MovieValidator:
    """
    clasa pentru incapsularea algoritmului de validare
    """
    def validate(self, movie):
        errors = []
        if int(movie.getId()) < 1:
            errors.append('Id-ul trebuie sa fie un numar mai mare decat 0')
        if len(movie.getTitlu().split()) < 1:
            errors.append('Titlul are cel putin un cuvant')
        if len(movie.getGen().split(', ')) > 1:
            g = movie.getGen().split(', ')
            for elem in g:
                if elem.lower() not in ['actiune', 'comedie', 'sf', 'romantic', 'thriller', 'family', 'drama']:
                    errors.append('Nu toate genurile se afla in lista acceptata de genuri')
        elif movie.getGen().lower() not in ['actiune', 'comedie', 'sf', 'romantic', 'thriller', 'family', 'drama']:
            errors.append('Genul nu se afla in lista acceptata de genuri')

        if len(errors) > 0:
            error_string = '\n'.join(errors)
            raise ValueError(error_string)


def test_validate_movie():
    validator = MovieValidator()
    a = Movie('1', 'Reminiscence', 'nice', 'SF, Romantic, Thriller')
    c = Movie('2', 'No Time to Die', 'very good', 'Actiune, Thriller')
    validator.validate(a)
    validator.validate(c)

    try:
        b = Movie('0', '', 'nice', 'Justitiar')
        validator.validate(b)
        assert False
    except ValueError:
        assert True

    try:
        b1 = Movie('-16', '', '', '')
        validator.validate(b1)
        assert False
    except ValueError:
        assert True


test_validate_movie()


class ClientValidator:
    """
    clasa pentru incapsularea algoritmului de validare
    """
    def validate(self, client):
        errors = []
        if int(client.getId()) < 1:
            errors.append('Id-ul trebuie sa fie un numar mai mare decat 0')
        if len(client.getNume().split()) < 2:
            errors.append('Numele trebuie sa aiba cel putin 2 cuvinte')
        if len(client.getCNP()) != 13:
            errors.append('CNP-ul trebuie sa aiba 13 caractere')
        if len(errors) > 0:
            error_string = '\n'.join(errors)
            raise ValueError(error_string)


def test_validate_client():
    validator = ClientValidator()
    a = Client('1', 'Oniga Andreea', '9206473849647')
    b = Client('2', 'Marginean Sebastian', '5011017011825')
    validator.validate(a)
    validator.validate(b)

    try:
        c = Client('0', 'Marginean', '50110170118')
        validator.validate(c)
        assert False
    except ValueError:
        assert True

    try:
        b1 = Client('-16', '', '')
        validator.validate(b1)
        assert False
    except ValueError:
        assert True


test_validate_client()
