import os
import sys

import numpy as np
import networkx as nx
import matplotlib.pyplot as plt
import warnings
warnings.simplefilter('ignore')
"""
def readNet(fileName):
    f = open(fileName, "r")
    net = {}
    n = int(f.readline())
    net['noNodes'] = n
    mat = []
    for i in range(n):
        mat.append([])
        line = f.readline()
        elems = line.split(" ")
        for j in range(n):
            mat[-1].append(int(elems[j]))
    net["mat"] = mat
    degrees = []
    noEdges = 0
    for i in range(n):
        d = 0
        for j in range(n):
            if (mat[i][j] == 1):
                d += 1
            if (j > i):
                noEdges += mat[i][j]
        degrees.append(d)
    net["noEdges"] = noEdges
    net["degrees"] = degrees
    f.close()
    return net


def plotNetwork(network, communities=[1, 1, 1, 1, 1, 1]):
    np.random.seed(123)  # to freeze the graph's view (networks uses a random view)
    A = np.matrix(network["mat"])
    G = nx.from_numpy_matrix(A)
    pos = nx.spring_layout(G)  # compute graph layout
    plt.figure(figsize=(15, 15))  # image is 8 x 8 inches
    nx.draw_networkx_nodes(G, pos, node_size=200, cmap=plt.cm.RdYlBu, node_color=communities)
    nx.draw_networkx_labels(G, pos, labels={n: str(n) for n in G.nodes()})
    nx.draw_networkx_edges(G, pos, alpha=0.3)
    plt.show()

# load a network
crtDir = os.getcwd()
filePath = os.path.join(crtDir,  'data', 'net.in')
network = readNet(filePath)

# plot the network
plotNetwork(network)

# plot a particular (mock) division in communities
mockCommunities = [1, 2, 1, 2, 1, 1]
plotNetwork(network, mockCommunities)

# identify the communities (by a tool) and plot them
def greedyCommunitiesDetectionByTool(network):
    # Input: a graph
    # Output: list of comunity index (for every node)

    from networkx.algorithms import community

    A = np.matrix(network["mat"])
    G = nx.from_numpy_matrix(A)
    communities_generator = community.girvan_newman(G)
    top_level_communities = next(communities_generator)
    sorted(map(sorted, top_level_communities))
    communities = [0 for node in range(network['noNodes'])]
    index = 1
    for community in sorted(map(sorted, top_level_communities)):
        for node in community:
            communities[node] = index
        index += 1
    return communities


#plotNetwork(network, greedyCommunitiesDetectionByTool(network))
"""

def readGraphGML(path):
    """
    Reads the graph from GML file and returns dictionary containing
    noNodes, noEdges, degrees vector and adjacency matrix of graph
    :param path: path of file
    :return: net, dictionary containing info about the graph
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
    plt.figure(figsize=(10, 8))  # image is 8 x 8 inches
    nx.draw_networkx_nodes(G, pos, node_size=200, cmap=plt.cm.RdYlBu, node_color=communities)
    nx.draw_networkx_labels(G, pos, labels={n: str(n) for n in G.nodes()})
    nx.draw_networkx_edges(G, pos, alpha=0.3)
    plt.show()


def copy_mat(m):
    """
    Copies a matrix
    :param m: the matrix we want to copy
    :return: the copied matrix
    """
    return [x.copy() for x in m]


class Configuration:
    """
    Defines the configuration of the algorithm.
    e- the matrix of connections between different communities
    a- the degrees vector
    Q0- the modularity metric
    communities- the community assignments
    """
    def __init__(self, e, a, Q0, communities):
        self.e = e
        self.a = a
        self.Q0 = Q0
        self.communities = communities

    def join(self, e, i, j):
        """
        Joins two communities
        :param e: the matrix of connections between different communities
        :param i: one end (a community) of the edge that connects two communities
        :param j: the end (a community) of the edge that connects two communities
        :return: the matrix of connections after join was made
        """
        e[i][i] += e[j][j] + 2 * e[i][j]
        e[i][j] = 0
        e[j][i] = 0
        e[j][j] = 0
        for k in range(network["noNodes"]):
            if k == i or k == j:
                continue
            e[i][k] += e[j][k]
            e[k][i] += e[k][j]
            e[j][k] = 0
            e[k][j] = 0
        return e

    def joinCommunities(self):
        """
        Finds the edge that maximizes the difference between the modularity of the old
        matrix of connections and the new one, obtained after simulating the morphing
        of the communities that correspond to the ends of that edge, and applies it,
        modifying e, a and Q0
        """
        maxDeltaQ = -sys.maxsize - 1
        e_maxSimulation = []
        a_maxSimulation = []
        i = network["noNodes"]-1
        (u, v) = (0, 0)
        while i > -1:
            j = network["noNodes"]-1
            while j > -1:
                if self.e[i][j] != 0 and i != j:
                    # calculates the modularity
                    deltaQ = 2 * (2*network["noEdges"]*self.e[i][j] - self.a[i] * self.a[j])
                    if deltaQ > maxDeltaQ:
                        maxDeltaQ = deltaQ
                        e_maxSimulation = copy_mat(self.join(copy_mat(self.e), i, j))
                        a_maxSimulation = []
                        for k in range(network["noNodes"]):
                            a_maxSimulation.append(sum(e_maxSimulation[k]))
                        (u, v) = (i, j)
                j -= 1
            i -= 1
        for c in range(network["noNodes"]):
            if self.communities[c] == v:
                self.communities[c] = u
        self.Q0 = self.Q0 + maxDeltaQ
        self.e = e_maxSimulation
        self.a = a_maxSimulation


def reachedFinalForm(e):
    """
    Helps to find out if the graph has reached finality, meaning the point where it contains only one community
    :param e: the matrix of connections between different communities
    :return: 0 if finality was not reached, 1 if it was
    """
    nr = 0
    for i in range(network["noNodes"]):
        for j in range(network["noNodes"]):
            if e[i][j] != 0:
                nr += 1
    if nr == 1:
        return 1
    else:
        return 0


def greedyCommunitiesDetection(network, nr):
    """
    Detects the communities and their vertexes
    :param network: the network in which we try to form nr communities
    :param nr: the number of communities we want to form
    :return: dictionary of communities and the vertexes they contain
    """
    # creating the first comunity which contains all the nodes
    e = copy_mat(network["mat"])
    a = network["degrees"].copy()
    Q0 = 0
    communities = []
    for i in range(network["noNodes"]):
        Q0 = Q0 + network["noEdges"]*e[i][i] - a[i]*a[i]
        communities.append(i)
    c = Configuration(e, a, Q0, communities)
    #
    c.joinCommunities()
    result = {}
    while reachedFinalForm(e) == 0 and len(np.unique(communities)) != nr:
        e = copy_mat(c.e)
        a = c.a.copy()
        Q0 = c.Q0
        communities = c.communities.copy()
        dicti = {}
        for i in range(len(communities)):
            if not (communities[i] in dicti):
                dicti[communities[i]] = []
            dicti[communities[i]] += [i]
        if len(dicti.keys()) == nr:
            result = communities
            print(f"Comunitati:...{dicti}")
            print(f"Nr comunitati = {len(dicti.keys())}")
            break
        c.joinCommunities()
    return result


if __name__ == '__main__':
    crtDir = os.getcwd()
    filePath = os.path.join(crtDir, 'data', 'lesmis.gml')
    network = readGraphGML(filePath)
    nr = int(input("Dati un nr: "))
    plotNetwork(network, greedyCommunitiesDetection(network, nr))
