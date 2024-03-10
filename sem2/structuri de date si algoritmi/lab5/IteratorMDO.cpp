#include "IteratorMDO.h"
#include "MDO.h"

#define NIL pair<TCheie,TValoare> (-1,-1)

IteratorMDO::IteratorMDO(const MDO& d) : dict(d){
	curent = 0;
	/*vector<TElem> elemente;
	for (int j = 0; j < dict.m / 2; j++) {
		if (dict.e[j] != NIL) {
			elemente.push_back(dict.e[j]);
		}
	}
	TElem aux;
	for (auto i = elemente.begin(); i != elemente.end(); i++) {
		for (auto j = elemente.begin(); i != elemente.end(); i++) {
			if (!dict.rel((*i).first, (*j).first)) {
				aux = *i;
				*i = *j;
				*j = *i;
			}
		}
	}*/
	deplasare();
}

void IteratorMDO::deplasare() {
	while ((curent < dict.m) && dict.e[curent] == NIL)
		curent++;
}

void IteratorMDO::prim(){
	curent = 0;
	deplasare();
}

void IteratorMDO::urmator(){
	curent++;
	deplasare();
}

bool IteratorMDO::valid() const{
	return (curent < dict.m);
}

TElem IteratorMDO::element() const{
	return dict.e[curent];
}


