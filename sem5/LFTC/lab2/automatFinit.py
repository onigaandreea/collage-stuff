# definim clasa pentru automatul finit
class AutomatFinit:
    def __init__(self):
        self.stari = list()
        self.alfabet = list()
        self.tranzitii = {}
        self.stari_finale = list()
        self.stare_crt = None

    def adauga_stare(self, stare):
        self.stari.append(stare)

    def adauga_simbol(self, simbol):
        self.alfabet.append(simbol)

    def adauga_tranzitie(self, stare, simbol, stare_urm):
        self.tranzitii[(stare, simbol)] = stare_urm

    def set_stare_finala(self, stare_finala):
        self.stari_finale = set(stare_finala)

    def set_stare_curenta(self, stare):
        self.stare_crt = stare

    def verifica_simbol(self, simbol):
        if (self.stare_crt, simbol) in self.tranzitii:
            self.stare_crt = self.tranzitii[(self.stare_crt, simbol)]
            return True
        else:
            return False

    def stare_acceptata(self):
        return self.stare_crt in self.stari_finale
