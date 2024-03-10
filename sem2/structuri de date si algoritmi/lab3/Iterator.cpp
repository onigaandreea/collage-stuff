#include "Iterator.h"
#include "DO.h"
#include <exception>

using namespace std;

Iterator::Iterator(const DO& d) : dict(d){
	//complexitate Theta(1)
	curent = dict.prim;
}

void Iterator::prim(){
	//complexitate Theta(1)
	curent = dict.prim;
}

void Iterator::urmator(){
	//complexitate Theta(1)
	curent=curent->urmator();
}

bool Iterator::valid() const{
	//complexitate Theta(1)
	return curent != nullptr;
}

TElem Iterator::element() const{
	//complexitate Theta(1)
	if (!valid())
		throw exception("Iterator invalid.");
	return curent->element();
}


