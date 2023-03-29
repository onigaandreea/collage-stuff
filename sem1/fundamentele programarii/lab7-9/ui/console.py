import random
from service.movie_service import generate_title, generate_description
from service.client_service import generate_nume, generate_CNP


class Console:
    def __init__(self, srv_movie, srv_client, srv_inchiriere):
        self.__srv_movie = srv_movie
        self.__srv_client = srv_client
        self.__srv_inchiriere = srv_inchiriere

    def print_menu(self):
        print('Comenzi disponibile pentru filme, clienti. Adaugati sufixul film sau client'
              'pentru a accesa optiunea dorita.')
        print('ENTITY COMMANDS: add, delete, update, show_all, genereaza random')
        print('Comenzi disponibile pentru inchirieri. Se folosesc fara adaugare string.')
        print('COMMANDS: inchiriere, returnare, clienti ordonati dupa nume, clienti ordonati dupa numarul '
              'de filme inchiriate, cele mai inchiriate filme, primii 30% clienti cu filme inchiriate, '
              'primii 3 clienti cu filme inchiriate, show_list')

    def __show_list_of_movies(self, movie_list):
        """
        Afiseaza toate filmele din lista movie_list
        """
        if len(movie_list) == 0:
            print('Nu exista filme in lista.')
        else:
            print('Lista de filme:')
            for film in movie_list:
                print('Id: ', film.getId(), 'Titlu: ', film.getTitlu(), 'Descriere: ', film.getDescriere(), 'Gen: ',
                      film.getGen())

    def __show_list(self):
        lista = self.__srv_inchiriere.toate_inchirierile()
        if len(lista) == 0:
            print('Nu exista elemente in lista')
        else:
            for elem in lista:
                print('Clientul', elem.getNumeClient(), '(cu id-ul:', elem.getIdClient(), ') a inchiriat filmul',
                      elem.getTitlu(), '(cu id-ul: ', elem.getIdFilm(), ')')

    def __show_list_of_clients(self, client_list):
        """
        Afiseaza toti clientii
        """
        if len(client_list) == 0:
            print('Nu exista clienti in lista.')
        else:
            print('Lista de clienti:')
            for client in client_list:
                print('Id:', client.getId(), 'Nume:', client.getNume(), 'CNP:', client.getCNP())

    def __add_movie(self):
        """
        Adauga un film cu datele citite de la tastatura
        """
        id = input('Id-ul este:')
        titlu = input('Titlul este: ')
        descriere = input('Descrierea este: ')
        gen = input('Genul este: ')

        try:
            added_movie = self.__srv_movie.add_movie(id, titlu, descriere, gen)
            print('Filmul ', added_movie.getTitlu(), ', cu genul', added_movie.getGen(), 's-a adaugat cu succes.')
        except ValueError as ve:
            print(str(ve))

    def __add_client(self):
        """
        Adauga un client cu datele citite de la tastatura
        """
        id = input('Id: ')
        nume = input('Numele este: ')
        CNP = input('CNP-ul este: ')

        try:
            added_client = self.__srv_client.add_client(id, nume, CNP)
            print('Clientul ', added_client.getNume(), 's-a adaugat cu succes.')
        except ValueError as ve:
            print(str(ve))

    def inchiriere(self):
        """
        se realizeaza o inchiriere
        :return:
        """
        idclient = input('Id-ul clientului:')
        idfilm = input('Id-ul filmului este:')
        inchiriere = self.__srv_inchiriere.add_inchiriere(idclient, idfilm)
        print('Clientul ', inchiriere.getNumeClient(), 'a inchiriat filmul ', inchiriere.getTitlu())

    def returnare(self):
        """
        se realizeaza o returnare
        :return:
        """
        idclient = input('Id-ul clentului:')
        idfilm = input('Id-ul filmului este:')
        returnare = self.__srv_inchiriere.returnare(idclient, idfilm)
        print('Returnarea s-a efectuat cu succes.')

    def __delete_movie_by_id(self):
        """
        Sterge un film cu id-ul citit de la tastatura

        """
        id = input('Id-ul este:')
        try:
            deleted_movie = self.__srv_movie.delete_by_id(id)
            print('Filmul ', deleted_movie.getTitlu(), 's-a sters cu succes (ID = ', deleted_movie.getId(), ').')
        except ValueError as ve:
            print(str(ve))

    def __delete_client_by_id(self):
        """
        Sterge un client cu id-ul citit de la tastatura

        """
        id = input('Id-ul este:')
        try:
            deleted_client = self.__srv_client.delete_by_id(id)
            print('Clientul ', deleted_client.getNume(), 's-a sters cu succes (ID = ', deleted_client.getId(), ').')
        except ValueError as ve:
            print(str(ve))

    def __update_movie(self):
        """
        Modifica un film cu datele citite de la tastatura
        """

        id = input('Id-ul este:')
        titlu_nou = input('Titlul este:')
        descriere_noua = input('Descrierea este:')
        gen_nou = input('Genul este:')

        try:
            updated_movie = self.__srv_movie.update_movie(id, titlu_nou, descriere_noua, gen_nou)
            print('Filmul', updated_movie.getTitlu(), 's-a modificat cu succes.')
        except ValueError as ve:
            print(str(ve))

    def __update_client(self):
        """
        Modifica un client cu datele citite de la tastatura
        """

        id = input('Id-ul este:')
        nume_nou = input('Numele este:')
        CNP_nou = input('CNP-ul este:')

        try:
            updated_client = self.__srv_client.update_client(id, nume_nou, CNP_nou)
            print('Clientu ', updated_client.getNume(), 's-a modificat cu succes.')
        except ValueError as ve:
            print(str(ve))

    def __filter_movie_by_gen(self):
        """
        Afiseaza toate filmele care au un gen dat
        """
        try:
            gen = input('Genul este:')
            filtered_list = self.__srv_movie.filter_by_genre(gen)
            self.__show_list_of_movies(filtered_list)
        except ValueError:
            print('Genul nu se afla in lista acceptata de genuri.')

    def add_random_movie(self):
        n = int(input('Cate filme vom adauga? '))
        for i in range(1, n+1):
            id = str(i)
            titlu = generate_title()
            gen = random.choice(('actiune', 'comedie', 'sf', 'romantic', 'thriller', 'family', 'drama'))
            descriere = generate_description()
            try:
                added_movie = self.__srv_movie.add_movie(id, titlu, descriere, gen)
                print('Filmul ', added_movie.getTitlu(), ', cu genul', added_movie.getGen(), 's-a adaugat cu succes.')
            except ValueError as ve:
                print(str(ve))

    def add_random_client(self):
        n = int(input('Cati clienti vom adauga? '))
        for i in range(1, n+1):
            id = str(i)
            nume = generate_nume()
            CNP = generate_CNP()
            try:
                added_client = self.__srv_client.add_client(id, nume, CNP)
                print('Clientul ', added_client.getNume(), 's-a adaugat cu succes.')
            except ValueError as ve:
                print(str(ve))

    def __filter_client_by_CNP(self):
        """
        Afiseaza toti clientii al caror CNP incepe cu o cifra data
        """
        try:
            cifra = int(input('Cifra este:'))
            filtered_list = self.__srv_client.filter_by_CNP(cifra)
            self.__show_list_of_clients(filtered_list)
        except ValueError:
            print('Cifra trebuie sa fie un numar.')

    def clienti_si_numar_filme(self):
        lista_clienti = []
        for inchiriere in self.__srv_inchiriere.toate_inchirierile():
            id_client = inchiriere.getIdClient()
            lista_filme = self.__srv_inchiriere.find_by_id_client(id_client)
            nr_filme = len(lista_filme)
            nume = inchiriere.getNumeClient()
            element = [id_client, nume, nr_filme]
            if len(lista_clienti) == 0:
                lista_clienti.append(element)
            else:
                k = 0
                for elem in lista_clienti:
                    if elem[0] == element[0]:
                        k = 1
                if k == 0:
                    lista_clienti.append(element)

        return lista_clienti

    def clienti_ord_dupa_nume(self):
        lista_clienti = self.clienti_si_numar_filme()
        lista_clienti = sorted(lista_clienti, key=lambda x: x[1])
        return lista_clienti

    def clienti_ord_dupa_nrfilme(self):
        lista_clienti = self.clienti_si_numar_filme()
        lista_clienti = sorted(lista_clienti, key=lambda x: x[2], reverse=True)
        return lista_clienti

    def numar_filme(self):
        lista_filme = []
        for inchiriere in self.__srv_inchiriere.toate_inchirierile():
            id_film = inchiriere.getIdFilm()
            lista = self.__srv_inchiriere.find_by_id_film(id_film)
            nr_filme = len(lista)
            element = [id_film, nr_filme]
            if len(lista_filme) == 0:
                lista_filme.append(element)
            else:
                k = 0
                for elem in lista_filme:
                    if elem[0] == element[0]:
                        k = 1
                if k == 0:
                    lista_filme.append(element)
        lista_filme = sorted(lista_filme, key=lambda x: x[1], reverse=True)
        return lista_filme

    def primi_3(self):
        lista_clienti = self.clienti_ord_dupa_nrfilme()
        primii = lista_clienti[:3]
        return [[elem[1],elem[2]] for elem in primii]

    def primi_30_la_suta(self):
        lista_clienti = self.clienti_ord_dupa_nrfilme()
        n = (30 * len(lista_clienti)) / 100
        nr = int(n+1)
        primii = lista_clienti[:nr]
        return [[elem[1],elem[2]] for elem in primii]

    def show_ui(self):
        while True:
            self.print_menu()
            cmd1 = input('Dati comanda:')
            cmd = cmd1.lower().strip()
            if cmd == 'add_film':
                self.__add_movie()
            elif cmd == 'delete_film':
                self.__delete_movie_by_id()
            elif cmd == 'update_film':
                self.__update_movie()
            elif cmd == 'filter_film':
                self.__filter_movie_by_gen()
            elif cmd == 'show_all_film':
                self.__show_list_of_movies(self.__srv_movie.get_all_movies())
            elif cmd == 'genereaza random_film':
                self.add_random_movie()
            elif cmd == 'add_client':
                self.__add_client()
            elif cmd == 'genereaza random_client':
                self.add_random_client()
            elif cmd == 'delete_client':
                self.__delete_client_by_id()
            elif cmd == 'update_client':
                self.__update_client()
            elif cmd == 'filter_client':
                self.__filter_client_by_CNP()
            elif cmd == 'show_all_client':
                self.__show_list_of_clients(self.__srv_client.get_all_clients())
            elif cmd == 'inchiriere':
                self.inchiriere()
            elif cmd == 'returnare':
                self.returnare()
            elif cmd == 'show_list':
                self.__show_list()
            elif cmd == 'clienti ordonati dupa nume':
                self.__show_list(self.clienti_ord_dupa_nume())
            elif cmd == 'clienti ordonati dupa numarul de filme inchiriate':
                self.__show_list(self.clienti_ord_dupa_nrfilme())
            elif cmd == 'cele mai inchiriate filme':
                self.__show_list(self.numar_filme())
            elif cmd == 'primii 30% clienti cu filme inchiriate':
                self.__show_list(self.primi_30_la_suta())
            elif cmd == 'primii 3 clienti cu filme inchiriate':
                self.__show_list(self.primi_3())
            elif cmd == 'exit':
                return
            else:
                print('Comanda invalida')
