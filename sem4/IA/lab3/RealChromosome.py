from random import randint


class Chromosome:
    def __init__(self, problParam=None):
        self.__problParam = problParam  # graful
        self.__representation = []  # cum decid sa encodez comunitatile
        self.__fitness = 0.0
        for _ in range(self.graph['noNodes']):
            self.__representation.append(randint(0, self.graph['noNodes']))
        self.normalization()

    @property
    def graph(self):
        return self.__problParam["graph"]

    @property
    def representation(self):
        return self.__representation.copy()

    @property
    def fitness(self):
        return self.__fitness

    @representation.setter
    def representation(self, lista):
        self.__representation = lista
        self.normalization()

    @fitness.setter
    def fitness(self, fit=0.0):
        self.__fitness = fit

    def crossover(self, c):  # c e destination, self e sursa
        r = randint(0, len(self.__representation) - 1)
        destination = c.representation
        com = self.__representation[r]

        for i in range(len(self.__representation)):
            if self.__representation[i] == com:
                destination[i] = com

        child = Chromosome(self.__problParam)
        child.representation = destination
        return child

    def mutation(self):
        gene = randint(0, len(self.__representation) - 1)
        self.__representation[gene] = randint(0, self.graph['noNodes'])
        self.normalization()

    def normalization(self):
        dicti = {}
        repres = self.representation
        k=0
        for i in range(len(repres)):
            if repres[i] not in dicti:
                dicti[repres[i]] = k
                k += 1
        for i in range(len(repres)):
            self.__representation[i] = dicti[repres[i]]

    def __str__(self):
        return '\nChromo: ' + str(self.__representation) + ' has fit: ' + str(self.__fitness)

    def __repr__(self):
        return self.__str__()

    def __eq__(self, c):
        return self.__representation == c.__representation and self.__fitness == c.__fitness
