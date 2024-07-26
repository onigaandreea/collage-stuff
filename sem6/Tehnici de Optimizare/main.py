import numpy as np

from primalSimplex import solve as solve_primal_simplex
from auxiliary import solve as solve_auxiliary_lp


def verify_method(matrix):
    begin_C_columns = 0
    end_C_columns = matrix.shape[1] - 2

    # Positive vectors C and B - (0) Simplex Primal
    # Vector C Negative and B Non-positive - (1) Simplex Dual
    # Vectors C Non-negative and B Non-positive - (2) PL Auxiliar

    if (all(i >= 0 for i in matrix[0, begin_C_columns:end_C_columns])) and (all(i >= 0 for i in matrix[1:, -1])):
        return 0
    elif (all(i <= 0 for i in matrix[0, begin_C_columns:end_C_columns])) and \
            (not (all(i >= 0 for i in matrix[1:, -1]))):
        return 1
    elif (not all(i <= 0 for i in matrix[0, begin_C_columns:end_C_columns])) and \
            (not (all(i >= 0 for i in matrix[1:, -1]))):
        return 2


def main():
    f = open("test.txt", 'r')

    lines = int(f.readline()) + 1  # Wrong test input. Sum +1 was required
    columns = int(f.readline()) + 1

    matrix_str = f.readline()
    matrix = np.array(np.mat(matrix_str).reshape(lines, columns), dtype=float)
    matrix = matrix.astype('object')

    method = verify_method(matrix)

    if (method == 0):
        solve_primal_simplex(matrix)
    elif(method == 2):
        solve_auxiliary_lp(matrix)
    f.close()


main()