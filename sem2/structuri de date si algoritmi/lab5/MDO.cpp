#include "IteratorMDO.h"
#include "MDO.h"
#include <iostream>
#include <vector>
#include <algorithm>
#include <exception>
using namespace std;
pair<TCheie, TValoare> NIL (-1, -1);

MDO::MDO(Relatie r) {
	//complexitate theta(m)
	rel = r;
	m = MAX;
	e = new TElem[m];
	for (int i = 0; i < m; i++) {
		e[i] = NIL;
	}
	lg = 0;
}

int MDO::d(TCheie c, int i)  const{
	//theta(1)
	int j = c % m + 0.5 * i + 0.5 * i * i;
	return j % m;
}

void MDO::resize() {
	//complexitate O(m), m-nr de locatii din tabela
	m = m * 2;
	TElem* elems=new TElem[m];
	for (int i = 0; i < m; i++) {
		elems[i] = NIL;
	}
	vector<TElem> elemente;
	for (int j = 0; j < m / 2; j++) {
		if (e[j] != NIL) {
			elemente.push_back(e[j]);
		}
	}
	TElem aux;
	for (auto i = elemente.begin(); i != elemente.end(); i++) {
		for (auto j = elemente.begin(); i != elemente.end(); i++) {
			if (!rel((*i).first, (*j).first)) {
				aux = *i;
				*i = *j;
				*j = *i;
			}
		}
	}
	delete[] e;
	e = elems;
	lg = 0;
	for (TElem el : elemente) {
		this->adauga(el.first,el.second);
	}
}


void MDO::adauga(TCheie c, TValoare v) {
	//O(m)

	int i = 0; //numarul de verificare
	TElem elem;
	elem.first = c;
	elem.second = v;
	bool gasit = false;
	do {
		int j = d(c, i);
		if (e[j] == NIL) {
			e[j] = elem;
			gasit = true;
			lg++;
		}
		else i++;
	} while (i < m && !gasit);
	if (i == m) {
		resize();
		adauga(c, v);
	}
}

vector<TValoare> MDO::cauta(TCheie c) const {
	//O(m)

	vector<TValoare> valori;
	int i = 0;
	int j = d(c, i);
	int k = 0;
	while (e[j] != NIL&&k!=m) {
		if (e[j].first == c) {
			valori.push_back(e[j].second);
		}
		i++;
		j = d(c, i);
		k++;
	}
	return valori;
}

bool MDO::sterge(TCheie c, TValoare v) {
	//O(m*m)
	int i = 0;
	int ant = 0;
	int j = d(c, i);
	int k = 0;
	while (k!=m) {
		if (e[j].first == c && e[j].second == v) {
			ant = j;
			i++;
			j = d(c, i);
			while (e[j]!=NIL&&k!=m)
			{
				if (e[j].first % m == c % m) {
					e[ant] = e[j];
					ant = j;
				}
				//ant = j;
				i++;
				j = d(c, i);
				k++;
			}
			e[ant] = NIL;
			lg--;
			return true;
		}
		i++;
		j = d(c, i);
		k++;
	}
	return false;
}

vector<TValoare> MDO::colectiaValorilor() const {
	//Theta(m)
	vector<TValoare> valori;
	for (int i = 0; i < m; i++) {
		if (e[i] != NIL) {
			valori.push_back(e[i].second);
		}
	}
	return valori;
}

int MDO::dim() const {
	//Theta(1)
	return lg;
}

bool MDO::vid() const {
	//Theta(1)
	if (lg != 0) {
		return false;
	}
	return true;
}

IteratorMDO MDO::iterator() const {
	//Theta(1)
	return IteratorMDO(*this);
}

MDO::~MDO() {
	//Theta(1)
	delete[] e;
}
