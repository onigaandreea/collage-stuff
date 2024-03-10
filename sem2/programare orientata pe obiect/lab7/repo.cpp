#include "repo.h"
#include <assert.h>
#include <algorithm>


/*
Salvare medicament
arunca exceptie daca mai exista un medicament cu aceeasi denumire
*/
void MedRepo:: store(const Meds& m) {
	if (exist(m)) {
		throw MedRepoException("Exista deja un medicament cu denumirea:" + m.getName());
	}
	allMeds.add(m);
}


/*
Cauta dupa denumire
arunca exceptie daca nu exista medicament
*/
Meds MedRepo::find(string name) {
	IteratorVectorT<Meds> it = allMeds.begin();
	while(it != allMeds.end()) {
		if (it.element().getName() == name) {
			return it.element();
		}
	}
	//daca nu exista arunc o exceptie
	throw MedRepoException("Nu exista medicament cu denumirea:" + name);
}

bool MedRepo::exist(const Meds& m) const{

	if (allMeds.find(m.getName()) == -1) {
		return false;
	}
	return true;
}
/*
Cauta dupa denumire
returneaza pozitia medicamentului
arunca exceptie daca nu exista medicament
*/
int MedRepo::cauta(string name) {
	return allMeds.find(name);
}
/*
modifica  medicament
arunca exceptie daca nu exista un medicament cu aceea denumire
*/

void MedRepo:: modify(const Meds &m1, const Meds &m2) {
	int poz = allMeds.find(m1.getName());
	if (poz == -1) {
		allMeds.set(poz, m2);
	}
}

/*
sterge
arunca exceptie daca nu exista medicament
returneaza medicamentul sters
*/
Meds MedRepo::del(const Meds& m){
	IteratorVectorT<Meds> it = allMeds.begin();
	while (it.valid()) {
		if (m.getName() != it.element().getName()) {
			it.next();
		}
		else {
			int poz = it.pozitie();
			return allMeds.deleted(m, poz);
		}
	}
	//daca nu exista aruncam exceptie
	throw MedRepoException("Nu exista medicament cu denumirea:" + m.getName());

}

int MedRepo:: size() {
	return allMeds.size();
}


Meds MedRepo::getelem(int poz){
	return allMeds.get(poz);
}

const VectDinNewDeleteT<Meds>& MedRepo::getAllMeds() const noexcept{
	return allMeds;
}

void testAdauga() {
	MedRepo rep;
	rep.store(Meds{ "a","a","a",4 });
	assert(rep.size() == 1);
	assert(rep.cauta("a") == 0);

	rep.store(Meds{ "b","b","b",4 });
	assert(rep.size() == 2);

}


void testCauta() {
	MedRepo rep;
	rep.store(Meds{ "a","a","a",4 });
	rep.store(Meds{ "b","b","b",7 });

	int poz = rep.cauta("a");
	Element m = rep.getelem(poz);
	assert(m.getName() == "a");
	assert(m.getProducer() == "a");
	assert(m.getActSubst() == "a");
	int i = rep.cauta("a");
	assert(i == 0);
	int j = rep.cauta("b");
	assert(j == 1);

}

void testModifica() {
	MedRepo rep;
	rep.store(Meds{ "a","a","a",4 });
	rep.store(Meds{ "b","b","b",7 });
	Element modified = rep.getelem(0), newelem= Meds{ "c","c","c",7 };

	rep.modify(modified, newelem);

	try
	{
		rep.find("a");
		assert(true);
	}
	catch(const MedRepoException&) {
		assert(false);

	}

}

void testDelete() {
	MedRepo rep;
	rep.store(Meds{ "a","a","a",4 });
	rep.store(Meds{ "b","b","b",7 });

	Element m = rep.getelem(0), m1=rep.getelem(1);

	Element deleted = rep.del(m);
	VectDinNewDeleteT<Meds> all = rep.getAllMeds();

	assert(all.size() == 1);
	assert(deleted.getName() == "a");

}

void testRepo() {
	testAdauga();
	testCauta();
	testModifica();
	testDelete();
}