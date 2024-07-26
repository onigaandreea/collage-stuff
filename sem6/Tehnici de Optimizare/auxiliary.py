import numpy as np
from utils import pivoting, put_table_form, parse_to_fpi, put_canonical_form, add_identity
from primalSimplex import verify_state, find_c_neg, find_pivot
from primalSimplex import primal_simplex as solve_primal_simplex
from write_sol import non_viability_certificate


def transform_b_positive(matrix):
    matrix[1:, :] *= np.where(matrix[1:, -1] < 0, -1, 1)
    return matrix


def zero_vector_c(matrix):
    matrix[0, :] = 0
    return matrix


def prepare_for_primal_simplex(matrix, original_matrix, base_columns):
    original_matrix = parse_to_fpi(original_matrix)
    original_matrix[1:, :-1] = matrix[1:, :-1]
    original_matrix[1:, -1] = matrix[1:, -1]
    original_matrix = put_table_form(original_matrix)
    original_matrix = put_canonical_form(original_matrix, base_columns)
    solve_primal_simplex(original_matrix, base_columns)


def primal_simplex_auxiliar_pl(matrix, base_columns, original_matrix):
    c_index = find_c_neg(matrix)
    unlimited_control = 0

    if c_index is not None:
        line_index = find_pivot(matrix, c_index)

        if line_index is not None:
            pivoting(matrix, line_index, c_index)
            base_columns[line_index] = c_index
        elif matrix[0, c_index] < 0:
            unlimited_control = 1
        else:
            raise ValueError("Error - Choose c_index = 0, with an entire column less than or equal to zero")

    if unlimited_control != 1:
        simplex_state = verify_state(matrix)
        if simplex_state:
            if matrix[0, -1] == 0:
                prepare_for_primal_simplex(matrix, original_matrix, base_columns)
            else:
                non_viability_certificate(matrix, base_columns)
            return
        elif simplex_state is None:
            pass
        else:
            primal_simplex_auxiliar_pl(matrix, base_columns, original_matrix)


def solve(matrix):
    original_matrix = matrix
    base_columns = np.zeros(matrix.shape[0])
    matrix = parse_to_fpi(matrix)
    matrix = zero_vector_c(matrix)
    matrix = transform_b_positive(matrix)
    matrix = put_table_form(matrix)
    matrix = add_identity(matrix, matrix.shape[1]-1, matrix.shape[0]-1, 1)
    end_c = matrix.shape[1] - matrix.shape[0]
    base_columns[1:] = np.arange(end_c + 1, end_c + matrix.shape[0])
    matrix = put_canonical_form(matrix, base_columns)
    primal_simplex_auxiliar_pl(matrix, base_columns, original_matrix)
