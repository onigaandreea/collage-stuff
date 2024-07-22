from listaSortata import ListaSortata


class TabelSimbol:
    def __init__(self):
        self.__listaSorted = ListaSortata()

    def add(self, value):
        return self.__listaSorted.add(value)

    def get(self, value):
        return self.__listaSorted.getId(value)

    def __str__(self):
        return str(self.__listaSorted)
