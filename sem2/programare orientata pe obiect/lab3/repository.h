#pragma once

#include "medicament.h"
#include "vectorDinamic.h"

#include <string>
#include <ostream>

using std::string;
using std::ostream;


class MedRepo {
private:
	VectDinNewDeleteT<medicament> allMeds;
	
public:
	/*MedRepo() = default;

	//nu permitem copierea de obiecte
	MedRepo(const MedRepo& ot) = delete;*/

	
	//Salvare medicament
	//arunca exceptie daca mai exista un medicament cu aceeasi denumire
	
	void store(const medicament& m);

	
	//Cauta dupa denumire
	//arunca exceptie daca nu exista medicament
	
	medicament find(string name);

	
	//Cauta dupa denumire
	//returneaza pozitia medicamentului
	//arunca exceptie daca nu exista medicament
	
	int cauta(string name);

	
	//sterge
	//arunca exceptie daca nu exista medicament
	//returneaza medicamentul sters
	
	medicament del(const medicament& m);
	
	//modifica  medicament
	//arunca exceptie daca nu exista un medicament cu aceea denumire
	
	void modify(const medicament& m1, const medicament& m2);

	
	//returneaza toate medicamentele salvate
	
	VectDinNewDeleteT<medicament> getAllMeds()
	{
		return allMeds;
	};

	//returneaza numarul de elemente din lista
	int size();

	//returneaza elementul de pe pozitia poz
	medicament getelem(int poz);

};


//Folosit pentru a semnala situatii exceptionale care pot aparea in repo

class MedRepoException {
	string msg;
public:
	MedRepoException(string m) :msg{ m } {}
	friend ostream& operator<<(ostream& out, const MedRepoException& ex);
};

ostream& operator<<(ostream& out, const MedRepoException& ex);
