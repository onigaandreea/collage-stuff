from random import randint, random
from RealChromosome import Chromosome


class GA:
    def __init__(self, param=None, problParam=None):
        self.__param = param # parametrii populatie
        self.__problParam = problParam # parametrii cromozom
        self.__population = []

    @property
    def population(self):
        return self.__population

    def initialisation(self):
        for _ in range(0, self.__param['popSize']):
            c = Chromosome(self.__problParam)
            self.__population.append(c)

    def evaluation(self):
        for c in self.__population:
            c.fitness = self.__param['modularity'](c)

    def bestChromosome(self):
        best = self.__population[0]
        for c in self.__population:
            if c.fitness > best.fitness:
                best = c
        return best

    def worstChromosome(self):
        worst = self.__population[0]
        for c in self.__population:
            if c.fitness < worst.fitness:
                worst = c
        return worst

    def selection(self):
        pos1 = randint(0, self.__param['popSize'] - 1)
        pos2 = randint(0, self.__param['popSize'] - 1)
        if self.__population[pos1].fitness > self.__population[pos2].fitness:
            return pos1
        else:
            return pos2

    def oneGeneration(self):
        newPop = []
        for _ in range(self.__param['popSize']):
            p1 = self.__population[self.selection()]
            p2 = self.__population[self.selection()]
            off = p1.crossover(p2)
            if random() < self.__param["mutationProbability"]:
                off.mutation()
            newPop.append(off)
        self.__population += newPop
        if random() < 0.3 and len(self.__population) > 20:
            dim = int(len(self.__population) - 4/100 * len(self.__population))
            for j in range(dim):
                self.__population.remove(self.worstChromosome())
                self.__param['popSize'] -= 1
        self.evaluation()

    def oneGenerationElitism(self):
        newPop = [self.bestChromosome()]
        for _ in range(self.__param['popSize'] - 1):
            p1 = self.__population[self.selection()]
            p2 = self.__population[self.selection()]
            off = p1.crossover(p2)
            if random() < self.__param["mutationProbability"]:
                off.mutation()
            newPop.append(off)
        self.__population = newPop
        self.evaluation()
