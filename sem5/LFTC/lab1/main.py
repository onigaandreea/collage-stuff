from specificari import *
from fip import FormaInternaProgram
from analizator import *
from ts import TabelSimbol

if __name__ == '__main__':
    fileName = 'pr2.txt'

    with open(fileName, 'r') as file:
        for line in file:
            print([token for token in extrage_cuvinte(line, separatori)])

    tabelSimbol = TabelSimbol()
    fip = FormaInternaProgram()

    with open(fileName, 'r') as file:
        lineNo = 0
        for line in file:
            lineNo += 1
            for token in extrage_cuvinte(line[0:-1], separatori):
                if token in separatori + operatori + cuvinteRez:
                    fip.add(codificare[token], -1)
                elif isIdentifier(token):
                    id = tabelSimbol.add(token)
                    fip.add(codificare['identificator'], id)
                elif isConstant(token):
                    id = tabelSimbol.add(token)
                    fip.add(codificare['constanta'], id)
                else:
                    print('Forma Interna a Programului: \n', fip)
                    print('Tabel de Simbol: \n', tabelSimbol)
                    raise Exception('Unknown token ' + token + ' at line ' + str(lineNo))

    print('Forma Interna a Programului: \n', fip)
    print('Tabel de Simbol: \n', tabelSimbol)

    print('\n\nCodificarea tablului: ')
    for e in codificare:
        print(e, " -> ", codificare[e])
