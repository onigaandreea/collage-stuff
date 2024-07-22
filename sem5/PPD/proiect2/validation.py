from constants import *
import sys

def validate_files(file1_path, file2_path):
    try:
        with open(file1_path, 'r') as file1, open(file2_path, 'r') as file2:
            content1 = file1.read()
            content2 = file2.read()

            if content1 == content2:
                print(f"Fișierele {file1_path} și {file2_path} sunt identice.")
            else:
                print(f"Fișierele {file1_path} și {file2_path} sunt diferite.")
    except FileNotFoundError as e:
        print(f"Eroare: {e}")
    except Exception as e:
        print(f"Eroare necunoscută: {e}")


if __name__ == "__main__":
    file1_path = OUTPUT_FILE_NAME_SECV
    file2_path = OUTPUT_FILE_NAME_ROW
    file3_path = OUTPUT_FILE_NAME_COLUMN

    if len(sys.argv) < 2:
        print("Introdu o litera ca argument de la linia de comanda.")
    else:
        argument = sys.argv[1][0]
        if argument == 'r':
            validate_files(file1_path, file2_path)
        elif argument == 'c':
            validate_files(file1_path, file2_path)
