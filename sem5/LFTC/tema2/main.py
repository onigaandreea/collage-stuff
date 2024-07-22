from specificari import *
from fip import FormaInternaProgram
from analizator import *
from ts import TabelSimbol

if __name__ == '__main__':
    fileName = 'pr1.txt'

    tabelSimbol = TabelSimbol()
    fip = FormaInternaProgram()
    automatConst = citeste_fisier("const.txt")
    automatID = citeste_fisier("identificatori.txt")
    automatConst.set_stare_curenta(automatConst.stari[0])
    automatID.set_stare_curenta(automatID.stari[0])
    in_ghilimele = False

    with open(fileName, 'r') as file:
        lineNo = 0
        for line in file:
            lineNo += 1
            const=""
            identif=""
            for token in line:
                if token == '"':
                    in_ghilimele = not in_ghilimele
                if not in_ghilimele:
                    if automatConst.verifica_simbol(token):
                        # verif daca avem constante
                        const = const + token
                        continue
                    else:
                        # simbolurile nu mai apartin unei constante
                        if automatConst.stare_acceptata() and const:
                            id = tabelSimbol.add(const)
                            fip.add(codificare['constanta'], id)
                        const = ""
                        automatConst.set_stare_curenta(automatConst.stari[0])
                    if automatID.verifica_simbol(token) and not in_ghilimele:
                        # verif daca avem identificatori
                        identif = identif + token
                        continue
                    else:
                        # simbolurile nu mai apartin unui identificator
                        if automatID.stare_acceptata() and identif:
                            if identif not in cuvinteRez:
                                if len(identif) < 9:
                                    id = tabelSimbol.add(identif)
                                    fip.add(codificare['identificator'], id)
                                else:
                                    print('Forma Interna a Programului: \n', fip)
                                    print('Tabel de Simbol: \n', tabelSimbol)
                                    raise Exception('Unknown token ' + identif + ' at line ' + str(lineNo))
                            else:
                                fip.add(codificare[identif], -1)
                        identif = ""
                        automatID.set_stare_curenta(automatID.stari[0])
                    if token in separatori+operatori:
                        fip.add(codificare[token], -1)

    print('Forma Interna a Programului: \n', fip)
    print('Tabel de Simbol: \n', tabelSimbol)

    print('\n\nCodificarea tablului: ')
    for e in codificare:
        print(e, " -> ", codificare[e])
