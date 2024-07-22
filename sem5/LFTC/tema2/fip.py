class FormaInternaProgram:
    def __init__(self):
        self.__continut = []

    def add(self, cod, id):
        self.__continut.append((cod, id))

    def __str__(self):
        return str(self.__continut)
