#include "Colectie.h"
#include "IteratorColectie.h"
#include <exception>
#include <iostream>
#include <stack>

using namespace std;

bool rel(TElem e1, TElem e2) {
	//theta(1)
	if (e1 <= e2)
		return true;
	return false;
}

int Colectie::minim(int p) {
	while (st[p] != -1) {
		p = st[p];
	}
	return p;
}

void Colectie::actualizarePrimLiber() {
	//O(cp)
	while (primLiber < cp && elems[primLiber] != liber )
		primLiber++;
}


Colectie::Colectie() {
	//Theta(1)
	rad = -1;
	primLiber = 0;
	lg = 0;
	for (int i = 0; i < cp; i++) {
		elems[i] = NIL;
		st[i] = -1;
		dr[i] = -1;
	}
}


void Colectie::adauga(TElem elem) {
	rad = adauga_rec(rad, elem);
}

int Colectie::adauga_rec(int crt, TElem elem) {
	if (crt == -1) {
		elems[primLiber] = elem;
		st[primLiber] = -1;
		dr[primLiber] = -1;
		crt = primLiber;
		actualizarePrimLiber();
		lg++;
	}
	else {

		if (rel(elem, elems[crt])) {
			st[crt] = adauga_rec(st[crt], elem);
		}
		else {
			dr[crt] = adauga_rec(dr[crt], elem);
		}
	}
	return crt;
}


bool Colectie::sterge(TElem elem) {
	//O(n) n-nr de elem
	int gasit = 0;
	rad = sterge_rec(rad, elem, gasit);
	if (gasit) {
		lg--;
		return true;
	}
	return false;
}

int Colectie::sterge_rec(int p, TElem e, int& gasit) {
	if (p == -1) return -1;
	else if (e < elems[p]) {
		st[p] = sterge_rec(st[p], e, gasit);
		return p;
	}
	else if (e > elems[p]) {
		dr[p] = sterge_rec(dr[p], e, gasit);
		return p;
	}
	else if (e == elems[p]) {// am gasit elementul in arbore
		gasit = 1;
		if (st[p] != -1 && dr[p] != -1) { //cazul 3 de la stergere
			int temp = minim(dr[p]);
			elems[p] = elems[temp];
			dr[p] = sterge_rec(dr[p], elems[p], gasit);
			return p;
		}
		else {
			int temp = p, repl;
			if (st[p] == -1) repl = dr[p];
			else repl = st[p];
			return repl;
		}
	}
}

bool Colectie::cauta(TElem elem) {
	int p = rad;
	while (p != -1 && elems[p] != elem) {
		if (elem < elems[p]) {
			p = st[p];
		}
		else if (elem > elems[p]) {
			p = dr[p];
		}
	}
	if (p != -1) {
		return true;
	}
	return false;
}

int Colectie::nrAparitii(TElem elem) const {
	//O(h)
	int nr = 0;
	IteratorColectie ic = iterator();
	while (ic.valid()) {
		if (ic.element() == elem) {
			nr++;
		}
		ic.urmator();
	}
	return nr;
}


int Colectie::dim() const {
	return lg;
}


bool Colectie::vida() const {
	if (rad != -1) {
		return false;
	}
	return true;
}

int Colectie:: ștergeToateElementeleRepetitive() {
	//caz favorabil theta(n) n-nr de elemente
	// caz mediu O(n^2)
	//caz defavorabil O(n^2) n-nr de elemente

	/*subalgoritm stergeToateElementeleRepetitive(c, nr)
	* prec: c o colectie ordonata
	* post: nr numarul de elemente sterse
	* 
	* nr<-0
	* k<-0
	* ic<-iterator(c)
	* cat timp valid(ic)=true executa
	*	k<-nrAparitii(c,element(ic))
	*	daca k>1 atunci
	*		nr<-nr+k
	*		cat timp k>0 executa
	*			sterge(c,element(ic))
	*			urmator(ic)
	*			k--
	*		sf cat timp
	*	  altfel
	*		urmator(ic)
	*	sf daca
	* sf cat timp
	*			
	
	*/
	int k = 0;
	IteratorColectie ic = iterator();
	while (ic.valid()) {
		int nr = nrAparitii(ic.element());
		if (nr > 1) {
			k = k + nr;
			while (nr)
			{
				sterge(ic.element());
				ic.urmator();
				nr--;
			}
		}
		else
			ic.urmator();
	}
	return k;
}

IteratorColectie Colectie::iterator() const {
	return  IteratorColectie(*this);
}


Colectie::~Colectie() {

}


