#pragma once
#include <string>
#include <vector>
#include <unordered_map>
#include <functional>
#include "validator.h"
#include "repository.h"
#include "reteta.h"
#include "undo.h"
#include "raport.h"
using std::vector;
using std::function;
using std::unique_ptr;
using std::unordered_map;

class Pharmacy {
	MedRepo& rep;
	MedValidator& val;

	Reteta RetetaCurenta;

	vector<unique_ptr<ActiuneUndo>> undoActions;

public:
	Pharmacy(MedRepo& rep, MedValidator& val) :rep{ rep }, val{ val } {
	}
	//nu permitem copierea de obiecte Pharmacy
	Pharmacy(const Pharmacy& ot) = delete;
	/*
	 returneaza toate medicamentele in ordinea in care au fost adaugate
	*/
	const vector<Meds>& getAll() noexcept {
		return rep.getAll();
	}
	/*
	Adauga un nou medicament
	arunca exceptie daca: nu se poate salva, nu este valid
	*/
	void addMed(string name, string producer, string active_subst, int price);

	/*
	* cauta un medicament dupa nume
	* returneaza medicamentul daca exista sau arunca exceptie altfel
	*/
	const Meds& findMed(string name);

	/*
	sterge un element
	returneaza elementul sters sau arunca exceptie daca nu exista
	*/
	void deleteMed(const Meds& m);

	/*
	modifica un element
	*/
	void updateMed(Meds& m, string newname, string newproducer, string newsubst, int newprice);

	/*
	Sorteaza dupa denumire
	*/
	vector<Meds> sortByName();

	/*
	Sorteaza dupa producer
	*/
	vector<Meds> sortByProducer();


	/*
	Sorteaza dupa substanta activa+pret
	*/
	vector<Meds> sortByActSubstPrice();

	/*
	returneaza doar medicamentele cu pret mai mic decat cel dat
	*/
	vector<Meds> filtrarePretMaiMic(int pret);
	/*
	returneaza doar acele medicamente care au substanta activa data
	*/
	vector<Meds> filtrareSubstActv(string substanta);

	/*
	returneaza doar medicamentele cu pret intre cele doua preturi
	*/
	vector<Meds> filtrarePret(int pretMin, int pretMax);

	/*
	* Adauga un medicament in reteta
	*
	* @throws: RepoException daca nu exista medicament
	*/
	void addToReteta(string name);
	/*
	* Adauga un numar random de medicamente in reteta
	* @return: numarul de melodii adaugate in playlist
	* post: se adauga un numar random de medicamente in reteta
	*/
	int addRandomToReteta(int howMany);
	/*
	* Elimina toate medicamentele din reteta
	*/
	void emptyReteta();
	/*
	* Returneaza un vector cu toate medicamentele din reteta
	*/
	const vector<Meds>& getMedsFromReteta();

	void empty();

	unordered_map<string, Raport> getProducer(string pr);

	void undo();
	void exporta(string file);
};


