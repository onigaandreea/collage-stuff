import re
from automatFinit import AutomatFinit


def citeste_fisier(filename):
    automat = AutomatFinit()
    with open(filename, 'r') as file:
        for line in file:
            line = line.strip()
            if line.startswith("Stari:"):
                stari = line.split(":")[1].split(",")
                for stare in stari:
                    automat.adauga_stare(stare.strip())
            elif line.startswith("Alfabet:"):
                alfabet = line.split(":")[1].split(",")
                for simbol in alfabet:
                    automat.adauga_simbol(simbol.strip())
            elif line.startswith("Tranzitii:"):
                tranzitii = line.split(":")[1].split(";")
                for tranzitie in tranzitii:
                    parts = tranzitie.strip().split("->")
                    if len(parts) == 2:
                        stare, simbol = parts[0].split(",")
                        stare_urm = parts[1]
                        simb = simbol.strip().split(" ")
                        for s in simb:
                            automat.adauga_tranzitie(stare.strip(), s.strip(), stare_urm.strip())
            elif line.startswith("StariFinale:"):
                stare_finala = line.split(": ")[1].split(",")
                automat.set_stare_finala(stare_finala)
    return automat
