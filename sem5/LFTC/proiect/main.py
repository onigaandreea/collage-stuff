class LL1Parser:
    def __init__(self):
        self.neterminale = set()
        self.terminale = set()
        self.reguli = {}
        self.start = 'S'
        self.follow = self.calculate_follow_sets()
        self.first = self.calculate_first_sets()
        self.table = self.create_table()

    def citeste_gramatica(self, nume_fisier):
        with open(nume_fisier, 'r') as fisier:
            for linie in fisier:
                linie = linie.strip()

                if linie:
                    partitii = linie.split('->')
                    neterminal = partitii[0].strip()
                    self.neterminale.add(neterminal)

                    if neterminal not in self.reguli:
                        self.reguli[neterminal] = []
                    self.reguli[neterminal].append(list(partitii[1].split(' ')))

                    productii = [prod.strip() for prod in partitii[1].split(' ')]
                    for prod in productii:
                        for simbol in prod.split():
                            if not simbol.istitle() and simbol != "eps":
                                self.terminale.add(simbol)

    def calculate_first_sets(self):
        first_sets = {}
        for non_terminal in self.reguli:
            first_sets[non_terminal] = set()

        contor = 0
        while contor < 3:
            for non_terminal in self.reguli:
                for production in self.reguli[non_terminal]:
                    if production[0] in self.terminale or production[0] == "eps":
                        first_sets[non_terminal].add(production[0])
                    elif production[0] in self.neterminale:
                        for p in first_sets[production[0]]:
                            first_sets[non_terminal].add(p)
            contor += 1

        return first_sets

    def calculate_follow_sets(self):
        follow_sets = {}
        for non_terminal in self.reguli:
            follow_sets[non_terminal] = set()

        contor = 0
        while contor < 3:
            for neterminal in self.reguli:
                if neterminal == self.start:
                    follow_sets[neterminal].add('$')
                for productie in self.reguli[neterminal]:
                    for i in range(len(productie)-1):
                        if productie[i] in self.neterminale:
                            if productie[i+1] in self.terminale:
                                follow_sets[productie[i]].add(productie[i+1])
                            elif productie[i+1] in self.neterminale:
                                firsts = self.calculate_first_sets()
                                for elem in firsts[productie[i + 1]]:
                                    if not elem == "eps":
                                        follow_sets[productie[i]].add(elem)
                                    else:
                                        for el in follow_sets[productie[i+1]]:
                                            follow_sets[productie[i]].add(el)
                    if productie[len(productie) - 1] in self.neterminale:
                        for elem in follow_sets[neterminal]:
                            follow_sets[productie[len(productie) - 1]].add(elem)
            contor += 1
        return follow_sets

    def create_table(self):
        table = {}
        # initializing the table
        for neterminal in self.reguli:
            for terminal in self.terminale:
                    table[(neterminal, terminal)] = None
        i = 1 # to know the production we are at
        # go through each non-terminal to complete the lines in the table
        for neterminal in self.reguli:
            for productie in self.reguli[neterminal]:
                prod = ""
                for j in range(len(productie)):
                    prod += productie[j]
                if productie[0] in self.terminale:
                    if table[(neterminal, productie[0])] is None:
                        table[(neterminal, productie[0])] = (prod, i)
                    else:
                        print(productie[0], table[((neterminal, productie[0]))])
                        print("Eroare: gramatica nu poate fi analizata!")
                        return
                elif productie[0] in self.neterminale:
                    for firsts in self.calculate_first_sets()[productie[0]]:
                        if table[(neterminal, firsts)] is None:
                            table[(neterminal, firsts)] = (prod, i)
                        else:
                            print(productie[0], table[((neterminal, productie[0]))])
                            print("Eroare: gramatica nu poate fi analizata!")
                            return
                elif productie[0] == "eps":
                    for follows in self.calculate_follow_sets()[neterminal]:
                        if table[(neterminal, follows)] is None:
                            table[(neterminal, follows)] = (prod, i)
                        else:
                            print("Eroare: gramatica nu poate fi analizata!")
                            return
                i += 1
        return table

    def validare_secventa(self, secventa_intrare):
        secventa_intrare = list(secventa_intrare)
        stiva = [self.start]
        productii = []
        table = self.create_table()
        while secventa_intrare:
            if stiva[0] == secventa_intrare[0]:
                stiva.pop(0)
                secventa_intrare.pop(0)
            elif stiva[0] in self.neterminale and table[(stiva[0], secventa_intrare[0])]:
                (productie, numar) = table[(stiva[0], secventa_intrare[0])]
                stiva.pop(0)
                if productie != "eps":
                    productie = list(productie)
                    productie.reverse()
                    stiva.reverse()
                    for j in range(len(productie)):
                        stiva.append(productie[j])
                    stiva.reverse()
                productii.append(numar)
            else:
                print("Eroare")
                return
        if not stiva:
            print("Secventa valida")
            print(productii)
        else:
            print("Eroare")


if __name__ == "__main__":
    parser = LL1Parser()
    parser.citeste_gramatica("gram1.txt")
    print(parser.reguli)
    # print("Neterminale",parser.neterminale)
    # print("Termminale",parser.terminale)
    print("First: ",parser.calculate_first_sets())
    print("Follow: ",parser.calculate_follow_sets())
    # print(parser.create_table())

    parser.validare_secventa("b;uns;im(){ia,a,a;ca;oa;a=a;a=a+a;rz;}")