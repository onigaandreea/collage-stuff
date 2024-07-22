import numpy as np
from fractions import Fraction


def add_identity(matrix, position, size, value):
    adicional_row = np.full(size, value)
    identity = np.identity(size)
    sub_matrix = np.insert(identity, 0, adicional_row, axis=0)

    arrays_list = []
    for line_index in range(matrix.shape[0]):
        extended_row = np.insert(matrix[line_index], position, sub_matrix[line_index])
        arrays_list.append(extended_row)
    return np.array(arrays_list)


def parse_to_fpi(matrix):
    position = matrix.shape[1] - 1
    size = matrix.shape[0] - 1
    return add_identity(matrix, position, size, 0)


def put_table_form(matrix):
    matrix_A_lines = matrix.shape[0] - 1
    matrix = add_identity(matrix, 0, matrix_A_lines, 0)
    matrix[0, :] *= -1
    return matrix


def pivoting(matrix, line_index, column_index):
    factor_line = matrix[line_index, :].copy()
    for index in range(matrix.shape[0]):
        if index == line_index:
            matrix[index, :] /= factor_line[column_index]
        else:
            factor = -matrix[index, column_index] / factor_line[column_index]
            matrix[index, :] += factor * factor_line

    with open('prim.txt', 'a') as f:
        for row in matrix:
            f.write(str(row.tolist()) + '\n')
        f.write('\n\n')


def canonical_form(matrix, base_columns):
    if not verify_canonical_form(matrix, base_columns):
        put_canonical_form(matrix, base_columns)


def verify_canonical_form(matrix, base_columns):
    for index in range(matrix.shape[0] - 1, matrix.shape[1]):
        count_ones = 0
        base = None
        if matrix[0, index] == 0:
            for line in range(1, matrix.shape[0]):
                if matrix[line, index] == 1:
                    count_ones += 1
                    base = line
                elif matrix[line, index] == 0:
                    continue
                else:
                    base = None
                    break
        if base is not None and count_ones == 1:
            base_columns[base] = index

    return all(i > 0 for i in base_columns)


def put_canonical_form(matrix, base_columns):
    for linha in range(1, base_columns.shape[0]):
        matrix[0, :] += matrix[linha, :] * (
                    (-matrix[0, int(base_columns[linha])]) / matrix[linha, int(base_columns[linha])])
    return matrix
