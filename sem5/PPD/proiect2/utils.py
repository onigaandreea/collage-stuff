import numpy as np


def read_matrix(file_name):
    try:
        matrix = np.loadtxt(file_name, delimiter=' ')
        return matrix
    except Exception as e:
        print(f"Nu s-a putut citi matricea din fisier. Eroare: {e}")
