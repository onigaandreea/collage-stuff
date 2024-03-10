
#include "Coada.h"
#include <exception>
#include <iostream>

using namespace std;

void Coada::redim() {

	//alocam un spatiu de capacitate dubla
	TElem* eNou = new int[2 * cp];

	//copiem vechile valori in noua zona
	for (int i = 1; i <= this->spate; i++)
		eNou[i] = this->elem[i];

	//dublam capacitatea
	cp = 2 * cp;

	//eliberam vechea zona
	delete[] this->elem;

	//vectorul indica spre noua zona
	this->elem = eNou;
}

Coada::Coada() {

	this->cp = 2;
	elem = new TElem[cp];
	this->fata = 1;
	this->spate = 1;
}


void Coada::adauga(TElem e) {
	
	if (this->spate == this->cp) {
		redim();
	}
	elem[this->spate] = e;
	this->spate = this->spate + 1;
}

//arunca exceptie daca coada e vida
TElem Coada::element() const {

	if (vida())
		throw(exception("Coada este vida."));
	return elem[this->fata];
}

TElem Coada::sterge() {

	if (vida()) throw(exception("Coada este vida."));
	TElem el = elem[this->fata];
	if (this->fata == this->cp)
		this->fata = 1;
	else
		this->fata = this->fata + 1;

	return el;
}

bool Coada::vida() const {

	if (this->fata == this->spate) {
		return true;
	}
	else
		return false;
}


Coada::~Coada() {
	/* de adaugat */
	delete[] elem;
}

