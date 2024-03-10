#include "repository.h"
#include "med.h"
#include <iostream>
#include <vector>
#include <algorithm>

using std::ostream;

void MedRepo::store(const Meds& m)
{
	if (exist(m)) {
		throw MedRepoException("Exista deja un medicament cu denumirea:" + m.getName() + "\n");
	}
	all.push_back(m);
}

bool MedRepo::exist(const Meds& m) {
	try {
		find(m.getName());
		return true;
	}
	catch (MedRepoException&) {
		return false;
	}
}

//Cauta
// returneaza medicamentul gasit
//arunca exceptie daca nu exista medicament

const Meds& MedRepo::find(string name) {
	/*for (const auto& m : all) {
		if (m.getName() == name) {
			return m;
		}
	}*/

	vector<Meds>::iterator f = std::find_if(this->all.begin(), this->all.end(),
		[=](const Meds& m) {
			return m.getName() == name;
		});

	if (f != this->all.end())
		return (*f);
	else
		throw MedRepoException("Nu exista medicament cu denumirea:" + name + "\n");
}


//Cauta dupa denumire
//returneaza pozitia medicamentului
//arunca exceptie daca nu exista medicament

int MedRepo::cauta(string name) const {
	for (int i = 0; i < all.size(); i++) {
		if (all[i].getName() == name) {
			return i;
		}
	}
	//daca nu exista arunc o exceptie
	throw MedRepoException("Nu exista medicament cu denumirea:" + name + "\n");

}

/*
	sterge
	arunca exceptie daca nu exista medicament
	*/
void MedRepo::del(const Meds& m) {
	if (exist(m)) {
		string name = m.getName();
		vector<Meds>::iterator f = std::find_if(this->all.begin(), this->all.end(),
			[=](const Meds& m) {
				return m.getName() == name;
			});
		if (f != this->all.end()) {
			all.erase(f);
		}

	}
	else {
		throw MedRepoException("Nu exista medicament cu denumirea:" + m.getName() + "\n");
	}
}

/*
	modifica  medicament
	arunca exceptie daca nu exista un medicament cu aceea denumire
	*/
void MedRepo::update(Meds& m1, Meds& m2) {
	if (exist(m1)) {
		int i=cauta(m1.getName());
		all[i] = m2;
		//m1 = m2;
		/*m1.setName(m2.getName());
		m1.setProducer(m2.getProducer());
		m1.setActSubst(m2.getActSubst());
		m1.setPrice(m2.getPrice());*/
	}
	else
		throw MedRepoException("Nu exista medicament cu denumirea:" + m1.getName() + "\n");

	
}

//returneaza toate medicamentele salvate

const vector<Meds>& MedRepo::getAll() const noexcept {
	return all;
}

void MedRepo::empty() {
	this->all.clear();
}

ostream& operator<<(ostream& out, const MedRepoException& ex) {
	out << ex.msg;
	return out;
}