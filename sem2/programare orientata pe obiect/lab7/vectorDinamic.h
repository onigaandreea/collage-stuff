#pragma once
#define INITIAL_CAPACITY 5
#include "repo.h"
typedef Meds Element;
template <typename ElementT>
class IteratorVectorT;

template <typename ElementT>
class VectDinNewDeleteT
{
public:
	/*
	Constructor default
	Alocam loc pentru 5 elemente
	*/
	VectDinNewDeleteT();

	/*
	Constructor de copiere
	*/
	VectDinNewDeleteT(const VectDinNewDeleteT& ot); //rule of 3

	/*
	Eliberam memoria
	*/
	~VectDinNewDeleteT();//rule of 3

	void add(const ElementT& el);

	ElementT& get(int poz) const;

	int find(string name) const;

	ElementT deleted(const ElementT& el, int poz);

	void set(int poz, const ElementT& el);

	int size() const noexcept;

	friend class IteratorVectorT<ElementT>;
	//functii care creaza iteratori
	IteratorVectorT<ElementT> begin() const;
	IteratorVectorT<ElementT> end() const;


private:
	int lg;//numar elemente
	int cap;//capacitate
	ElementT* elems;//elemente

	void ensureCapacity();
};

/*
Constructor default
Alocam loc pentru 5 elemente
*/
template<typename ElementT>
VectDinNewDeleteT<ElementT>::VectDinNewDeleteT() :elems{ new Element[INITIAL_CAPACITY] }, cap{ INITIAL_CAPACITY }, lg{ 0 } {}

/*
Constructor de copiere
Obiectul current (this) acum se creaza
aloca spatiu pentru elemente
copieaza elementele din ot in this
*/
template<typename ElementT>
VectDinNewDeleteT<ElementT>::VectDinNewDeleteT(const VectDinNewDeleteT<ElementT>& ot) {
	elems = new ElementT[ot.cap];
	//copiez elementele
	for (int i = 0; i < ot.lg; i++) {
		elems[i] = ot.elems[i];  //assignment din Meds
	}
	lg = ot.lg;
	cap = ot.cap;
}

/*
Eliberam memoria
*/
template<typename ElementT>
VectDinNewDeleteT<ElementT>::~VectDinNewDeleteT() {
	delete[] elems;
}

template<typename ElementT>
void VectDinNewDeleteT<ElementT>::add(const ElementT& el) {
	ensureCapacity();//daca e nevoie mai alocam memorie
	elems[lg++] = el;
}

template<typename ElementT>
ElementT& VectDinNewDeleteT<ElementT>::get(int poz) const {
	return elems[poz];
}

template<typename ElementT>
int VectDinNewDeleteT<ElementT>::find(string name) const {
	for (int i = 0; i < lg; i++) {
		if (elems[i].getName() == name) {
			return i;
		}
	}
	return -1;
}

template<typename ElementT>
ElementT VectDinNewDeleteT<ElementT>::deleted(const ElementT& el, int poz) {
	for (int i = poz; i < lg; i++) {
		set(i, elems[i + 1]);
	}
	lg--;
	return el;
	
}

template<typename ElementT>
void VectDinNewDeleteT<ElementT>::set(int poz, const ElementT& el) {
	elems[poz] = el;
}

template<typename ElementT>
int VectDinNewDeleteT<ElementT>::size() const noexcept {
	return lg;
}

template<typename ElementT>
void VectDinNewDeleteT<ElementT>::ensureCapacity() {
	if (lg < cap) {
		return; //mai avem loc
	}
	cap *= 2;
	ElementT* aux = new ElementT[cap];
	for (int i = 0; i < lg; i++) {
		aux[i] = elems[i];
	}
	delete[] elems;
	elems = aux;
}

template<typename ElementT>
IteratorVectorT<ElementT> VectDinNewDeleteT<ElementT>::begin() const 
{
	return IteratorVectorT<ElementT>(*this);
}

template<typename ElementT>
IteratorVectorT<ElementT> VectDinNewDeleteT<ElementT>::end() const 
{
	return IteratorVectorT<ElementT>(*this, lg);
}

template<typename ElementT>
class IteratorVectorT {
private:
	const VectDinNewDeleteT<ElementT>& v;
	int poz = 0;
public:
	IteratorVectorT(const VectDinNewDeleteT<ElementT>& v) noexcept;
	IteratorVectorT(const VectDinNewDeleteT<ElementT>& v, int poz)noexcept;
	bool valid()const;
	ElementT& element() const;
	void next();
	int pozitie();
	ElementT& operator*();
	IteratorVectorT& operator++();
	bool operator==(const IteratorVectorT& ot)noexcept;
	bool operator!=(const IteratorVectorT& ot)noexcept;
};

template<typename ElementT>
IteratorVectorT<ElementT>::IteratorVectorT(const VectDinNewDeleteT<ElementT>& v) noexcept :v{ v } {}

template<typename ElementT>
IteratorVectorT<ElementT>::IteratorVectorT(const VectDinNewDeleteT<ElementT>& v, int poz)noexcept : v{ v }, poz{ poz } {}

template<typename ElementT>
bool IteratorVectorT<ElementT>::valid()const {
	return poz < v.lg;
}

template<typename ElementT>
ElementT& IteratorVectorT<ElementT>::element() const {
	return v.elems[poz];
}

template<typename ElementT>
int IteratorVectorT<ElementT>::pozitie() {
	return poz;
}

template<typename ElementT>
void IteratorVectorT<ElementT>::next() {
	poz++;
}

template<typename ElementT>
ElementT& IteratorVectorT<ElementT>::operator*() {
	return element();
}

template<typename ElementT>
IteratorVectorT<ElementT>& IteratorVectorT<ElementT>::operator++() {
	next();
	return *this;
}

template<typename ElementT>
bool IteratorVectorT<ElementT>::operator==(const IteratorVectorT<ElementT>& ot) noexcept {
	return poz == ot.poz;
}

template<typename ElementT>
bool IteratorVectorT<ElementT>::operator!=(const IteratorVectorT<ElementT>& ot)noexcept {
	return !(*this == ot);
}

