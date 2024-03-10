#pragma once

#include "med.h"

#include <vector>
#include <string>
#include <ostream>

using std::vector;
using std::string;
using std::ostream;

class MedRepo {
	vector<Meds> all;
	/*
	metoda privata verifica daca exista deja m in repository
	*/
	bool exist(const Meds& m) const;
public:
	MedRepo() = default;

	//nu permitem copierea de obiecte
	MedRepo(const MedRepo& ot) = delete;

	void setelem(const Meds& m, string newname, string newproducer, string newactsubst, string newprice);
	/*
	Salvare medicament
	arunca exceptie daca mai exista un medicament cu aceeasi denumire
	*/
	void store(const Meds& m);

	/*
	Cauta dupa denumire
	arunca exceptie daca nu exista medicament
	*/
	const Meds& find(string name) const;

	/*
	Cauta dupa denumire
	returneaza pozitia medicamentului
	arunca exceptie daca nu exista medicament
	*/
	int cauta(string name) const;

	/*
	sterge
	arunca exceptie daca nu exista medicament
	returneaza medicamentul sters
	*/
	const Meds& del(const Meds& m) const;
	/*
	modifica  medicament
	arunca exceptie daca nu exista un medicament cu aceea denumire
	*/
	void update(const Meds& m1, const Meds& m2);

	/*
	returneaza toate medicamentele salvate
	*/
	const vector<Meds>& getAll() const noexcept;

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

void testeRepo();
