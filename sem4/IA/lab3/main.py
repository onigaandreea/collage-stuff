import networkx as nx
from GA import GA
import numpy as np
import matplotlib.pyplot as plt


def readGraphGML(path):
    """
    Reads the graph from GML file and returns dictionary containing
    noNodes, noEdges, degrees vector and adjacency matrix of graph
    :param path: path of file
    :return: dictionary containing information about the graph
    """
    G = nx.read_gml(path, label='id')
    net = {'noNodes': len(G.nodes), 'noEdges': len(G.edges)}
    # initializing the adjacency matrix
    mat = [[0] * len(G.nodes) for i in range(len(G.nodes))]
    # marking all the edges in the adjacency matrix
    for el in G.edges:
        mat[el[0]-1][el[1]-1] = 1
        mat[el[1] - 1][el[0] - 1] = 1
    net['mat'] = mat
    degrees = []
    for i in range(net['noNodes']):
        d = 0
        for j in range(net['noNodes']):
            if mat[i][j] == 1:
                d += 1
        degrees.append(d)
    net['degrees'] = degrees
    return net


def modularity(chromosome):
    """
    Calculates the modularity for a given list of communities
    :param communities: a list of communities
    :return: the modularity for the given list of communities
    """
    communities = chromosome.representation
    param = chromosome.graph
    noNodes = param['noNodes']
    mat = param['mat']
    degrees = param['degrees']
    noEdges = param['noEdges']
    M = 2 * noEdges
    Q = 0.0
    for i in range(0, noNodes):
        for j in range(0, noNodes):
            if communities[i] == communities[j]:
                Q += (mat[i][j] - degrees[i] * degrees[j] / M)
    return Q * 1 / M


def numberOfCommunities(bestChr):
    """
    Calculates the number of communities based on a chromosome
    :param bestChr: the best choice for a chromosome
    :return: the number of communities
    """
    com = []
    for gene in bestChr.representation:
        if gene not in com:
            com.append(gene)
    return len(com)



def plotNetwork(network, communities=[1, 1, 1, 1, 1, 1]):
    """
    Plots a network with colored vertexes by community and numbers assigned to each of them
    :param network: the network we want to plot
    :param communities: the communities that exist in the network
    """
    np.random.seed(123)
    A = np.matrix(network["mat"])
    G = nx.from_numpy_array(A)
    pos = nx.spring_layout(G)  # compute graph layout
    plt.figure(figsize=(10, 6))  # image is 8 x 8 inches
    nx.draw_networkx_nodes(G, pos, node_size=200, cmap=plt.cm.RdYlBu, node_color=communities)
    nx.draw_networkx_edges(G, pos, alpha=0.3)
    plt.show()


def main():
    # initializing the network we are using
    network = readGraphGML('data/lesmiserables.gml')
    gaParam = {'graph': network, 'popSize': 200, 'modularity': modularity, 'noGenerations': 800,
               'mutationProbability': 0.2}
    problParam = {'graph': network}
    ga = GA(gaParam, problParam)
    ga.initialisation()
    ga.evaluation()

    bestChromosomeMax = ga.bestChromosome()
    for g in range(gaParam['noGenerations']):
        ga.oneGenerationElitism()
        bestChromo = ga.bestChromosome()
        if bestChromo.fitness > bestChromosomeMax.fitness:
            bestChromosomeMax = bestChromo
            if bestChromosomeMax.fitness > 0:
                print('Best solution in generation ' + str(g) + ' has fitness= ' + str(bestChromo.fitness))
    print('Se formeaza ', numberOfCommunities(bestChromo), ' comunitati')
    plotNetwork(network, bestChromosomeMax.representation)


if __name__ == "__main__":
    main()
