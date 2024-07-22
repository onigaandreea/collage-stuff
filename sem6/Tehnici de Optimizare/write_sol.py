import numpy as np


def write_to_file(content):
    with open('final.txt', 'w') as f:
        f.writelines(content)


def unlimited_certificate(matrix, c_index, base_columns):
    begin_A_columns = matrix.shape[0] - 1
    coefficients = np.zeros(matrix.shape[1] - begin_A_columns)
    for index in range(1, len(base_columns)):
        coefficients[(int(base_columns[index]) - begin_A_columns)] = -matrix[index, c_index]
    coefficients[c_index - begin_A_columns] = 1

    write_to_file(["1\n", str(np.around(np.array(coefficients, dtype=float), decimals=6).tolist())])


def optimal_situation(matrix, base_columns):
    begin_A_columns = matrix.shape[0]-1
    solution = (np.zeros(matrix.shape[1] - (begin_A_columns))).astype('object')
    for index in range(1,len(base_columns)):
        solution[(int(base_columns[index])-begin_A_columns)] = matrix[index,matrix.shape[1]-1]

    conteudo = []
    conteudo.append("2"+'\n')
    conteudo.append(str(np.around(np.array(solution[0:-(matrix.shape[0])],dtype=float), decimals=5).tolist())+'\n')
    conteudo.append(str(np.around(float(matrix[0,-1]) , decimals=5))+'\n')
    conteudo.append(str(np.around( np.array(matrix[0,0:(matrix.shape[0]-1)],dtype=float), decimals=6).tolist()))
    write_to_file(conteudo)


def non_viability_certificate(matrix,base_columns):
    conteudo = []
    conteudo.append("0"+'\n')
    conteudo.append(str((np.around(np.array((matrix[0,0:(matrix.shape[0]-1)]) ,dtype=float), decimals=5)).tolist()))
    write_to_file(conteudo)
