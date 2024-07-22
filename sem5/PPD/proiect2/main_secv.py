import time
import sys

from constants import *
from utils import *


def convolution_row_sequential(i, N, M, n, m, matrix, convolution_matrix):
    result_row = []

    for j in range(M):
        accumulator = 0
        for k in range(n):
            for l in range(m):
                ii = i + k - (n // 2)
                ii = 0 if ii <= -1 else (N - 1) if ii >= N else ii
                jj = j + l - (m // 2)
                jj = 0 if jj <= -1 else (M - 1) if jj >= M else jj
                accumulator += matrix[ii][jj] * convolution_matrix[k][l]
        result_row.append(accumulator)

    return result_row


def convolution_column_sequential(j, N, M, n, m, matrix, convolution_matrix):
    result_column = []

    for i in range(N):
        accumulator = 0
        for k in range(n):
            for l in range(m):
                ii = i + k - (n // 2)
                ii = 0 if ii <= -1 else (N - 1) if ii >= N else ii
                jj = j + l - (m // 2)
                jj = 0 if jj <= -1 else (M - 1) if jj >= M else jj
                accumulator += matrix[ii][jj] * convolution_matrix[k][l]
        result_column.append(accumulator)

    return result_column


def main():
    convolution_matrix = read_matrix(FILE_PATH)
    input_matrix = read_matrix(INPUT_FILE_NAME)

    # Salvează matricea rezultat
    if len(sys.argv) < 2:
        print("Introdu o litera ca argument de la linia de comanda.")
    else:
        argument = sys.argv[1][0]
        start = time.time()

        if argument == 'r':
            result = []
            for i in range(N):
                result.append(convolution_row_sequential(i, N, M, n, m, input_matrix, convolution_matrix))

            np.savetxt(OUTPUT_FILE_NAME_SECV, np.asmatrix(result), fmt='%d')
        elif argument == 'c':
            start = time.time()
            input_matrix = [convolution_column_sequential(j, N, M, n, m, input_matrix, convolution_matrix) for j in
                            range(M)]
            input_matrix = np.transpose(input_matrix)
            np.savetxt(OUTPUT_FILE_NAME_SECV, input_matrix, fmt='%d')

        end = time.time()
        print((end - start)*1000, "ms")


if __name__ == "__main__":
    main()
