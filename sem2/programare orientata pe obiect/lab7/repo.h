#pragma once


#include "entitate.h"
#include "vectorDinamic.h"
#define INITIAL_CAPACITY 5

#include <string>
#include <ostream>

using std::string;
using std::ostream;


class MedRepo {
private:
	VectDinNewDeleteT<Meds> allMeds;
	/*
	metoda privata verifica daca exista deja m in repository
	*/
	bool exist(const Meds& m) const;
public:
	MedRepo() = default;

	//nu permitem copierea de obiecte
	MedRepo(const MedRepo& ot) = delete;

	/*
	Salvare medicament
	arunca exceptie daca mai exista un medicament cu aceeasi denumire
	*/
	void store(const Meds& m);

	/*
	Cauta dupa denumire
	arunca exceptie daca nu exista medicament
	*/
	Meds find(string name);

	/*
	Cauta dupa denumire
	returneaza pozitia medicamentului
	arunca exceptie daca nu exista medicament
	*/
	int cauta(string name);

	/*
	sterge
	arunca exceptie daca nu exista medicament
	returneaza medicamentul sters
	*/
	Meds del(const Meds& m) ;
	/*
	modifica  medicament
	arunca exceptie daca nu exista un medicament cu aceea denumire
	*/
	void modify(const Meds& m1, const Meds& m2);

	/*
	returneaza toate medicamentele salvate
	*/
	const VectDinNewDeleteT<Meds>& getAllMeds() const noexcept;

	int size();

	Meds getelem(int poz);

};

/*
Folosit pentru a semnala situatii exceptionale care pot aparea in repo
*/
class MedRepoException {
	string msg;
public:
	MedRepoException(string m) :msg{ m } {}
	//functie friend (vreau sa folosesc membru privat msg)
	friend ostream& operator<<(ostream& out, const MedRepoException& ex);
};

ostream& operator<<(ostream& out, const MedRepoException& ex);

void testAdauga();
void testCauta();
void testRepo();
