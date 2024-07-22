class ListaSortata:
    def __init__(self):
        self.__lista = []
        self.__count = 0

    def add(self, value):
        id = self.getId(value)
        if id != -1:
            return id
        self.__lista.append((value, self.__count))
        self.__count += 1
        self.__lista = sorted(self.__lista, key=lambda x: x[0])
        return self.__count - 1

    def getId(self, valoare):
        for i in self.__lista:
            if i[0] == valoare:
                return i[1]
        return -1

    def __str__(self):
        return str(self.__lista)
