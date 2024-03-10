#include "IteratorColectie.h"
#include "Colectie.h"


IteratorColectie::IteratorColectie(const Colectie& c): col(c) {
	curent = 0;
	for (int i = 0; i < col.lg; i++) {
		all.push_back(col.elems[i]);
	}
	sort(all.begin(), all.end());
}

void IteratorColectie::prim() {
	curent = 0;
}


void IteratorColectie::urmator() {
	curent++;
}


bool IteratorColectie::valid() const {
	if (curent < col.lg) {
		return true;
	}
	return false;
}



TElem IteratorColectie::element() const {
	return all[curent];
}
