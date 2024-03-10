#include "medrepo.h"
#include "med.h"
#include <assert.h>
#include <iostream>
#include <vector>
#include <algorithm>

using std::ostream;

void MedRepo::store(const Meds& m)
{
	if (exist(m)) {
		throw MedRepoException("Exista deja un medicament cu denumirea:" + m.getName());
	}
	all.push_back(m);
}

bool MedRepo::exist(const Meds& m) const {
	try {
		find(m.getName());
		return true;
	}
	catch (MedRepoException&) {
		return false;
	}
}
/*
Cauta
arunca exceptie daca nu exista medicament
*/
const Meds& MedRepo::find(string name) const {
	for (const auto& m : all) {
		if (m.getName() == name) {
			return m;
		}
	}
	//daca nu exista arunc o exceptie
	throw MedRepoException("Nu exista medicament cu denumirea:" + name);
}

/*
	Cauta dupa denumire
	returneaza pozitia medicamentului
	arunca exceptie daca nu exista medicament
	*/
int MedRepo::cauta(string name) const {
	for (int i = 0; i < all.size(); i++) {
		if (all[i].getName() == name) {
			return i;
		}
	}
	//daca nu exista arunc o exceptie
	throw MedRepoException("Nu exista medicament cu denumirea:" + name);
	
}

/*
	sterge
	arunca exceptie daca nu exista medicament
	returneaza medicamentul sters
	
const Meds& MedRepo::del(const Meds& m) const {
	if(exist(m)) {
		for (auto i = all.begin(); i != all.end(); i++) {
			if ((*i).getName() == m.getName())
			{
				all.(i);
			}
		}
	}
	throw MedRepoException("Nu exista un medicament cu denumirea:" + m.getName());
	
}
/*
void MedRepo::setelem(const Meds& m, string newname, string newproducer, string newactsubst, string newprice) {
	m.getName() = newname;
	m.getProducer() = newproducer;
	m.getActSubst() = newactsubst;
	m.getPrice() = newprice;
}
/*
	modifica  medicament
	arunca exceptie daca nu exista un medicament cu aceea denumire
	
void MedRepo::update(const Meds& m1, const Meds& m2) {
	if (!exist(m1)) {
		throw MedRepoException("Nu exista un medicament cu denumirea:" + m1.getName());
	}
	setelem(m1, m2.getName(), m2.getProducer(), m2.getActSubst(), m2.getPrice());

}
*/
/*
returneaza toate medicamentele salvate
*/
const vector<Meds>& MedRepo::getAll() const noexcept {
	return all;
}


ostream& operator<<(ostream& out, const MedRepoException& ex) {
	out << ex.msg;
	return out;
}
