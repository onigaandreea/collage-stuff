#include "Multime.h"
#include "IteratorMultime.h"
#include <iostream>

using namespace std;

//o posibila relatie
bool rel(TElem e1, TElem e2) {
	if (e1 <= e2) {
		return true;
	}
	else {
		return false;
	}
}

void Multime::redim() {
	//initializam noii parametrii
	int* newUrm = new int[cp * 2];
	int* newPrec = new int[cp * 2];
	TElem* newElems = new TElem[cp * 2];
	for (int i = 0; i < cp ; i++) {
		newUrm[i] = urm[i];
		newPrec[i] = prec[i];
		newElems[i] = e[i];
	}
	//dublam capacitatea

	cp = cp * 2;

	//pregatirea parametrilor pentru reinitializare
	delete[] e;
	delete[] prec;
	delete[] urm;

	//reinitializarea vectorilor
	e = newElems;
	urm = newUrm;
	prec = newPrec;

	for (int i = cp / 2; i < cp; i++) {
		dealoca(i);
	}
}
void Multime::afisare() {
	int crt = prim;
	cout << "\nAfisare: ";
	while (crt != -1) {
		cout << e[crt] << " ";
		crt = urm[crt];
	}
	cout << endl;

}
Multime::Multime() {
	//lista e vida
	cp = CAPACITATE;
	lg = 0;
	e = new TElem[cp];
	urm = new int[cp];
	prec = new int[cp];
	prim = -1;
	ultim = -1;
	//se initializeaza lista spatiului liber - toate pozitiile din vecto sunt marcate ca fiind libere
	for (int i = 0; i < cp - 1; i++) {
		urm[i] = i + 1;
		prec[i] = i - 1;
	}
	prec[cp - 1] = cp - 2;
	urm[cp - 1] = -1;
	//referinta spre prima pozitie libera din lista
	primLiber = 0;
	
}

int Multime::aloca(){
	//se sterge primul element din lista spatiului liber
	int i = primLiber;
	primLiber = urm[primLiber];
	return i;
}

void Multime::dealoca(int i) {
	//se trece pozitia i in lista spatiului liber
	urm[i] = primLiber;
	primLiber = i;
}

//creeaza un nod in lista inlantuita unde se memoreaza informatia utila e
int Multime::creeazaNod(TElem e) {
	if (primLiber == -1) {
		redim();
	}
	int i = aloca();
	if (i != -1) {//exista spatiu liber in lista
		this->e[i] = e;
		urm[i] = -1;
		prec[i] = -1;
	}
	return i;
}


bool Multime::adauga(TElem elem) {
	int i = creeazaNod(elem);
	int curent = prim;
	//introducem un prim element

	if (vida()) {
		prim = i;
		ultim = i;
		lg++;
		return true;
	}
	while (curent != -1 && rel(e[curent], e[i])) {
		//elem exista
		if (e[curent] == elem)
		{
			dealoca(i);
			return false;
		}
		curent = urm[curent];
	}

	//adaugam la sfarsit
	if (curent == -1) {
		prec[i] = ultim;
		urm[ultim] = i;
		ultim = i;
		lg++;
		return true;
	}
	
	//adauga la inceput
	if (curent == prim) {
		urm[i] = curent;
		prec[curent] = i;
		prim = i;
		lg++;
		return true;
	}
	//adauga la mijloc
	urm[prec[curent]] = i;
	prec[i] = prec[curent];
	urm[i] = curent;
	prec[curent] = i;
	lg++;
	return true;
}


bool Multime::sterge(TElem elem) {
	int curent = prim;
	if (vida()) {
		return false;
	}
	while (curent != -1 && rel(e[curent], elem)) {
		//elem exista
		if (e[curent] == elem) {
			//stergem singurul element
			if (prim == ultim) {
				dealoca(curent);
				prim = -1;
				ultim = -1;
				lg--;
				return true;
			}

			//stergere de la inceput
			if (curent == prim) {
				prec[urm[curent]] = -1;
				prim = urm[curent];
				dealoca(curent);
				lg--;
				return true;
			}

			//stergere de la final
			if (curent == ultim) {
				urm[prec[curent]] = -1;
				ultim = prec[curent];
				dealoca(curent);
				lg--;
				return true;
			}

			//stergere la mijloc
			urm[prec[curent]] = urm[curent];
			prec[urm[curent]] = prec[curent];
			dealoca(curent);
			lg--;
			return true;
		}
		curent = urm[curent];
	}
	return false;
}


bool Multime::cauta(TElem elem) const {
	int curent = prim;
	//nu avem unde sa cautam
	if (vida()) {
		return false;
	}
	while (curent != -1 && rel(e[curent], elem)) {
		//elem exista
		if (e[curent] == elem)
			return true;
		curent = urm[curent];
	}
	return false;
}


int Multime::dim() const {
	return lg;
}



bool Multime::vida() const {
	if (prim == -1 && ultim == -1) {
		return true;
	}
	return false;
}

IteratorMultime Multime::iterator() {
	return IteratorMultime(*this);
}


Multime::~Multime() {
	delete[] e;
	delete[] urm;
	delete[] prec;
}






