from automatFinit import AutomatFinit


# functie de citit din fisier
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


def citeste_tastatura():
    automat = AutomatFinit()
    stari = input("Stari: (de forma stare, stare, ..)")
    stari = stari.strip().split(",")
    for stare in stari:
        automat.adauga_stare(stare.strip())
    alfabet = input("Alfabet: (de forma caracter, caracter, ...)")
    alfabet = alfabet.strip().split(",")
    for simbol in alfabet:
        automat.adauga_simbol(simbol.strip())
    tranzitii = input("Tranzitii: (de forma stare, simbol -> stare ; stare, simbol -> stare; ..)")
    tranzitii = tranzitii.strip().split(";")
    for tranzitie in tranzitii:
        parts = tranzitie.strip().split("->")
        if len(parts) == 2:
            stare, simbol = parts[0].split(",")
            stare_urm = parts[1]
            simb = simbol.strip().split(" ")
            for s in simb:
                automat.adauga_tranzitie(stare.strip(), s.strip(), stare_urm.strip())
    line = input("Stari finale: (de forma stare, stare, ...)")
    stare_finala = line.strip().split(",")
    automat.set_stare_finala(stare_finala)
    return automat


# functia de meniu si interactiune cu utilizatorul
def menu(automat):
    while True:
        print("\n1. Afiseaza multimea starilor")
        print("2. Afiseaza alfabetul")
        print("3. Afiseaza tranzitiile")
        print("4. Afiseaza multimea starilor finale")
        print("5. Verifica daca o secventa este acceptata")
        print("6. Determina cel mai lung prefix acceptat")
        print("0. Iesire")
        choice = input("Alegeti o optiune: ")

        if choice == "1":
            print("Multimea starilor:", automat.stari)
        elif choice == "2":
            print("Alfabetul:", automat.alfabet)
        elif choice == "3":
            print("Tranzitiile:")
            for (stare, simbol), stare_urm in automat.tranzitii.items():
                print(f"{stare}, {simbol} -> {stare_urm}")
        elif choice == "4":
            print("Multimea starilor finale:", automat.stari_finale)
        elif choice == "5":
            secventa = input("Introduceti secventa de verificat: ")
            automat.set_stare_curenta(list(automat.stari)[0])
            if len(secventa) > 10:
                print("Secventa nu este acceptata.")
            else:
                for simbol in secventa:
                    if not automat.verifica_simbol(simbol):
                        print("Secventa nu este acceptata.")
                        break

                else:
                    if automat.stare_acceptata():
                        print("Secventa este acceptata.")
                    else:
                        print("Secventa nu este acceptata.")
        elif choice == "6":
            secventa = input("Introduceti secventa pentru determinarea prefixului acceptat: ")
            automat.set_stare_curenta(list(automat.stari)[0])
            prefix_acceptat = ""
            for simbol in secventa:
                if not automat.verifica_simbol(simbol):
                    break
                prefix_acceptat += simbol
            if automat.stare_acceptata():
                print("Cel mai lung prefix acceptat:", prefix_acceptat)
            else:
                print("Nu avem un cel mai lung prefix acceptat(nu se ajunge in stare finala)")
        elif choice == "0":
            break
        else:
            print("Optiune invalida. Incercati din nou.")


if __name__ == "__main__":
    #automat = citeste_tastatura()
    filename = input("Introduceti numele fisierului cu definitia automatului: ")
    automat = citeste_fisier(filename)
    menu(automat)
