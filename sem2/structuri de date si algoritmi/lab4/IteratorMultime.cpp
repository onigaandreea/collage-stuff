#include "IteratorMultime.h"
#include "Multime.h"
#include <exception>

using namespace std;

IteratorMultime::IteratorMultime(Multime& m): mult(m) {
	curent = m.prim;
}

TElem IteratorMultime::element() const {
	if (!valid()) {
		throw exception("Iterator invalid.");
	}
	return mult.e[curent];
}

bool IteratorMultime::valid() const {
	return curent != -1;
}

void IteratorMultime::urmator() {
	curent = mult.urm[curent];
}

void IteratorMultime::prim() {
	curent = mult.prim;
}

TElem IteratorMultime::elimina() {
	//complexitate medie=complexitate favorabila=complexitate defavorabila este Theta(1)
	if (!valid()) {
		throw exception("Iterator invalid.");
	}
	if (curent == mult.ultim) {
		int i = curent;
		urmator();
		return mult.e[i];
	}
	int i = curent;
	urmator();
	return mult.e[i];
}
