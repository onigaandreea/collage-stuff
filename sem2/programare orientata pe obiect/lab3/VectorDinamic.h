#pragma once

#define INITIAL_CAPACITY 5

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

	/*
	Operator assgnment
	elibereaza ce era in obiectul curent (this)
	aloca spatiu pentru elemente
	copieaza elementele din ot in this
	*/
	VectDinNewDeleteT& operator=(const VectDinNewDeleteT& ot);//rule of 3

	void add(const ElementT& el);

	ElementT& get(int poz) const;

	void set(int poz, const ElementT& el);

	int size() const noexcept;

	void erase(int poz);

	friend class IteratorVectorT<ElementT>;
	//functii care creaza iteratori
	IteratorVectorT<ElementT> begin();
	IteratorVectorT<ElementT> end();


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
VectDinNewDeleteT<ElementT>::VectDinNewDeleteT() :elems{ new ElementT[INITIAL_CAPACITY] }, cap{ INITIAL_CAPACITY }, lg{ 0 } {}

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
		elems[i] = ot.elems[i];  //assignment din Pet
	}
	lg = ot.lg;
	cap = ot.cap;
}

/*
Operator assgnment
elibereaza ce era in obiectul curent (this)
aloca spatiu pentru elemente
copieaza elementele din ot in this
*/
template<typename ElementT>
VectDinNewDeleteT<ElementT>& VectDinNewDeleteT<ElementT>::operator=(const VectDinNewDeleteT<ElementT>& ot) {
	if (this == &ot) {
		return *this;//s-a facut l=l;
	}
	delete[] elems;
	elems = new ElementT[ot.cap];
	//copiez elementele
	for (int i = 0; i < ot.lg; i++) {
		elems[i] = ot.elems[i];  //assignment din Pet
	}
	lg = ot.lg;
	cap = ot.cap;
	return *this;
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
void VectDinNewDeleteT<ElementT>::set(int poz, const ElementT& el) {
	elems[poz] = el;
}

template<typename ElementT>
int VectDinNewDeleteT<ElementT>::size() const noexcept {
	return lg;
}

template<typename ElementT>
void VectDinNewDeleteT<ElementT>:: erase(int poz) {
	for (int i = poz; i < lg; i++) {
		set(i, elems[i + 1]);
	}
	lg--;
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
IteratorVectorT<ElementT> VectDinNewDeleteT<ElementT>::begin()
{
	return IteratorVectorT<ElementT>(*this);
}

template<typename ElementT>
IteratorVectorT<ElementT> VectDinNewDeleteT<ElementT>::end()
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


