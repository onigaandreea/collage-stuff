from numba import cuda
import time
import sys
from constants import *
from utils import *


@cuda.jit
def convolution_kernel(matrix, convolution_matrix, result):
    i, j = cuda.grid(2)
    if i < matrix.shape[0] and j < matrix.shape[1]:
        accumulator = 0
        for k in range(-(n // 2), n // 2 + 1):
            for l in range(-(m // 2), m // 2 + 1):
                ii = i + k
                jj = j + l

                accumulator += matrix[max(min(ii, matrix.shape[0] - 1), 0), max(min(jj, matrix.shape[1] - 1), 0)] * \
                               convolution_matrix[k + n // 2, l + m // 2]
        result[i, j] = accumulator


def main():
    convolution_matrix = read_matrix(FILE_PATH)
    input_matrix = read_matrix(INPUT_FILE_NAME)

    # Creează matricea rezultat pe GPU
    result_matrix = cuda.device_array_like(input_matrix)

    # Stabilește dimensiunile blocului și grilei CUDA
    threads_per_block = (16, 16)
    blocks_per_grid_x = (input_matrix.shape[0] + threads_per_block[0] - 1) // threads_per_block[0]
    blocks_per_grid_y = (input_matrix.shape[1] + threads_per_block[1] - 1) // threads_per_block[1]
    blocks_per_grid = (blocks_per_grid_x, blocks_per_grid_y)

    if len(sys.argv) < 2:
        print("Introdu o litera ca argument de la linia de comanda.")
    else:
        argument = sys.argv[1][0]
        start = time.time()

        if argument == 'r':
            # Aplică convoluția pe linii
            convolution_kernel[blocks_per_grid, threads_per_block](input_matrix, convolution_matrix, result_matrix)
            np.savetxt(OUTPUT_FILE_NAME_ROW, result_matrix.copy_to_host(), fmt='%d')
        elif argument == 'c':
            # Transpune matricea pentru a efectua convoluția pe coloane
            input_matrix = np.transpose(input_matrix)
            result_matrix = cuda.device_array_like(input_matrix)

            # Aplică convoluția pe coloane
            convolution_kernel[blocks_per_grid, threads_per_block](input_matrix, convolution_matrix, result_matrix)
            np.savetxt(OUTPUT_FILE_NAME_COLUMN, result_matrix.copy_to_host(), fmt='%d')

        end = time.time()
        print((end - start)*1000, "ms")


if __name__ == "__main__":
    main()
