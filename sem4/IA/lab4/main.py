import math
import random

# Set the parameters of the genetic algorithm
POPULATION_SIZE = 50
ELITE_SIZE = 5
MUTATION_RATE = 0.1
NUM_GENERATIONS = 1000


def readFile(fileName):
    f = open(fileName, "r")
    net = {}
    n = int(f.readline())
    net['nrNodes'] = n
    mat = []
    for i in range(n):
        mat.append([])
        line = f.readline()
        elems = line.split(",")
        for j in range(n):
            mat[-1].append(int(elems[j]))
    f.close()
    net['mat'] = mat
    return net


def readCoord(fileName):
    f = open(fileName, "r")
    net = {}
    n = int(f.readline())
    net['nrNodes'] = n
    mat = []
    for i in range(n):
        mat.append([])
        line = f.readline()
        elems = line.split(" ")
        for j in range(1,3):
            mat[-1].append(float(elems[j]))
    f.close()
    net['mat'] = mat
    return net

net = readFile('fricker.txt')
mat = net['mat']
# net = readCoord('berlin.txt')
# mat = net1['mat']

# Define the fitness function
def fitness(individual):
    total_distance = 0
    for i in range(len(individual)):
        if i == len(individual) - 1:
            distance = mat[i][0]
        else:
            distance = mat[i][i+1]
        total_distance += distance
    return 1 / total_distance


# def fitness(individual):
#     total_distance = 0
#     for i in range(len(individual)):
#         from_city = individual[i]
#         to_city = individual[(i + 1) % len(individual)]
#         total_distance += ((mat[from_city][0] - mat[to_city][0]) ** 2 + (
#                     mat[from_city][1] - mat[to_city][1]) ** 2) ** 0.5
#     return 1 / total_distance

# Define the crossover function
def crossover(parent1, parent2):
    # Choose two random indices
    i, j = sorted(random.sample(range(len(parent1)), 2))
    # Extract the subsequence between the two indices from the first parent
    segment = parent1[i:j + 1]
    # Create a new child by filling in the missing values from the second parent
    child = [x for x in parent2 if x not in segment]
    return child[:i] + segment + child[i:]


# Define the mutation function
def mutate(individual):
    if random.random() < MUTATION_RATE:
        # Choose two random indices
        i, j = random.sample(range(len(individual)), 2)
        # Swap the values at the two indices
        individual[i], individual[j] = individual[j], individual[i]
    return individual


# Initialize a population of potential solutions
population = []
for i in range(POPULATION_SIZE):
    individual = list(range(net['nrNodes']))
    random.shuffle(individual)
    population.append(individual)

# Evaluate the fitness of each individual in the population
fitness_scores = [fitness(individual) for individual in population]


# Run the genetic algorithm
best_fitness_scores = []
best_individuals = []
for generation in range(NUM_GENERATIONS):
    # Select parents from the population
    parent_indices = sorted(random.sample(range(len(population)), 2))
    parent1 = population[parent_indices[0]]
    parent2 = population[parent_indices[1]]

    # Create offspring by applying crossover and mutation operators to the selected parents
    offspring = crossover(parent1, parent2)
    offspring = mutate(offspring)

    # Evaluate the fitness of the offspring
    offspring_fitness = fitness(offspring)

    # Replace the least fit individuals in the population with the offspring
    indices_to_replace = sorted(range(len(fitness_scores)), key=lambda i: fitness_scores[i])[:len(offspring)]

    # Sort the population by fitness score
    sorted_indices = sorted(range(len(fitness_scores)), key=lambda i: fitness_scores[i], reverse=True)
    population = [population[i] for i in sorted_indices]
    fitness_scores = [fitness_scores[i] for i in sorted_indices]

    # Store the best fitness score and best individual in the population
    best_fitness_score = fitness_scores[0]
    best_individual = population[0]
    best_fitness_scores.append(best_fitness_score)
    best_individuals.append(best_individual)

    # Check if we have found the optimal solution
    if best_fitness_score == 1:
        print("Optimal solution found!")
        break

unique_individuals = set(tuple(individual) for individual in best_individuals)
for i, individual in enumerate(unique_individuals):
    print("Unique solution {}: Fitness score = {}".format(i + 1, fitness(individual)))
    print("  {}".format(list(individual)))
