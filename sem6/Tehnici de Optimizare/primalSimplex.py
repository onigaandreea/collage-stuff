import math
from utils import *
from write_sol import optimal_situation, unlimited_certificate


def find_c_neg(matrix):
    for index, value in enumerate(matrix[0, matrix.shape[0]-1:]):
        if value < 0:
            return index + matrix.shape[0] - 1
    return None


def find_pivot(matrix, c_index):
    min_index = None
    min_value = math.inf
    for index in range(1, matrix.shape[0]):
        if matrix[index, c_index] > 0:
            curr_value = matrix[index, -1] / matrix[index, c_index]
            if curr_value < min_value:
                min_value = curr_value
                min_index = index
    return min_index


def verify_state(matrix):
    c_positive = all(i >= 0 for i in matrix[0, matrix.shape[0]-1:])
    b_positive = all(i >= 0 for i in matrix[1:, -1])
    if c_positive and b_positive:
        return True
    elif c_positive:
        return None
    else:
        return False


def primal_simplex(matrix, base_columns):
    c_index = find_c_neg(matrix)
    if c_index is None:
        return
    line_index = find_pivot(matrix, c_index)
    if line_index is not None:
        pivoting(matrix, line_index, c_index)
        base_columns[line_index] = c_index
    elif matrix[0, c_index] < 0:
        unlimited_certificate(matrix, c_index, base_columns)
    else:
        raise ValueError("Error - Choose c_index = 0, with an entire column less than or equal to zero")
    simplex_state = verify_state(matrix)
    if simplex_state:
        optimal_situation(matrix, base_columns)
    elif simplex_state is None:
        pass
    else:
        primal_simplex(matrix, base_columns)


def solve(matrix):
    matrix = parse_to_fpi(matrix)
    matrix = put_table_form(matrix)
    base_columns = np.zeros(matrix.shape[0])
    canonical_form(matrix, base_columns)
    primal_simplex(matrix, base_columns)
