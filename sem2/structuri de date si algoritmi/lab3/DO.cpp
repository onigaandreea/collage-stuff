#include "Iterator.h"
#include "DO.h"
#include <iostream>

#include <exception>
using namespace std;

Nod::Nod(TElem e, PNod urm) {
	//complexitate Theta(1)
	this->e = e;
	this->urm = urm;
}

TElem Nod::element() {
	//complexitate Theta(1)
	return this->e;
}

PNod Nod::urmator() {
	//complexitate Theta(1)
	return urm;
}


DO::DO(Relatie r) {
	//complexitate Theta(1)
	prim = nullptr;
	this->r = r;
}



//adauga o pereche (cheie, valoare) in dictionar
//daca exista deja cheia in dictionar, inlocuieste valoarea asociata cheii si returneaza vechea valoare
//daca nu exista cheia, adauga perechea si returneaza null
TValoare DO::adauga(TCheie c, TValoare v) {
	//complexitate O(n), n=nr elem
	PNod crt = prim;
	PNod pred = nullptr;
	TValoare val;
	TElem e;
	e.first = c;
	e.second = v;
	//pt lista vida
	if (crt == nullptr)
	{
		PNod nou = new Nod(e, nullptr);
		prim = nou;
		return NULL_TVALOARE;
	}

	while (crt != nullptr && r(crt->element().first, c))
	{
		//daca exista cheia
		if (crt->element().first == c)
		{
			//modifica val
			val = crt->element().second;
			crt->e.second = v;
			return val;
		}
		pred = crt;
		crt = crt->urmator();

	}

	//adauga pe prima poz
	if (pred == nullptr)
	{
		PNod nou = new Nod(e, nullptr);
		nou->urm = crt;
		prim = nou;
	}
	// adauga la mijloc sau la final
	else
	{
		PNod nou = new Nod(e, nullptr);
		nou->urm = crt;
		pred->urm = nou;
	}

	return NULL_TVALOARE;
}

//cauta o cheie si returneaza valoarea asociata (daca dictionarul contine cheia) sau null
TValoare DO::cauta(TCheie c) const {
	//complexitate O(n), n=nr elem
	PNod crt = prim;
	while (crt != nullptr && r(crt->element().first, c))
	{
		if (crt->element().first == c)
			return crt->element().second;
		crt = crt->urmator();
	}
	return NULL_TVALOARE;	
}

//sterge o cheie si returneaza valoarea asociata (daca exista) sau null
TValoare DO::sterge(TCheie c) {
	//complexitate O(n), n=nr elem
	PNod crt = prim;
	PNod pred = nullptr;
	TValoare val;
	if (crt == nullptr)
		return NULL_TVALOARE;

	while (crt != nullptr && r(crt->element().first, c))
	{
		
		if (crt->element().first == c)
		{
			val = crt->element().second;
			if (pred == nullptr)
			{
				prim = crt->urmator();
				delete crt;
				return val;
			}
			else
			{
				pred->urm = crt->urmator();
				delete crt;
				return val;
			}
		}
		pred = crt;
		crt = crt->urmator();
	}
	return NULL_TVALOARE;
}

//returneaza numarul de perechi (cheie, valoare) din dictionar
int DO::dim() const {
	//complexitate theta(n), n=nr elemente
	int k = 0;
	PNod nod=prim;
	while (nod != nullptr)
	{
		k++;
		nod = nod->urmator();
	}
	return k;
}

//verifica daca dictionarul e vid
bool DO::vid() const {
	//complexitate Theta(1)
	PNod nod = prim;
	if (nod == nullptr)
		return true;
	return false;
}
/*
void DO::filtreaza(Condiție cond)
{
	//complexitate O(n^2), n=nr elemente
	PNod crt = prim;
	
	if (vid())
	{
		throw exception("Dictionarul este vid.");
	}
	
	while (crt != nullptr)
	{
		TCheie ch = crt->element().first;
		if (!cond(ch))
		{
			//sterge elementele din dictionar ale caror chei nu respecta conditia
			PNod urm = crt->urmator();
			TValoare val = sterge(ch);
			crt = urm;
		}
		else
			crt = crt->urmator();
	}

}*/

void DO::goleste() {
	//complexitate Theta(n), n-nr de elemente
	PNod crt = prim;

	if (vid())
	{
		throw exception("Dictionarul este vid.");
	}

	while (crt != nullptr)
	{
		TCheie ch = crt->element().first;
		//sterge elementele din dictionar 
		PNod urm = crt->urmator();
		TValoare val = sterge(ch);
		crt = urm;
	}
}

Iterator DO::iterator() const {
	//complexitate Theta(1)
	return  Iterator(*this);
}

DO::~DO() {
	//complexitate theta(n), n=nr elemente
	while (prim != nullptr) {
		PNod p = prim;
		prim = prim->urm;
		delete p;
	}
}
