class Gramatica:
    def __init__(self):
        self.reguli = []

    def citeste_gramatica_din_fisier(self, nume_fisier):
        with open(nume_fisier, 'r') as fisier:
            for linie in fisier:
                self.reguli.append(linie.strip())

    def afiseaza_reguli_cu_terminal(self, terminal_cautat):
        for regula in self.reguli:
            partea_stanga, partea_dreapta = regula.split('->')

            if terminal_cautat in partea_dreapta:
                print(regula)


gramatica = Gramatica()

nume_fisier = input("Introduceti numele fisierului cu regulile de productie ale gramaticii: ")
gramatica.citeste_gramatica_din_fisier(nume_fisier)

terminal_cautat = input("Introduceti terminalul cautat: ")
gramatica.afiseaza_reguli_cu_terminal(terminal_cautat)
