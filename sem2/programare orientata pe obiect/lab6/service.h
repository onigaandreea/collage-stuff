#pragma once

#include "med.h"
#include "medrepo.h"
#include <string>
#include <vector>

#include <functional>
#include "validator.h"

using std::vector;
using std::function;

class Pharmacy {
	MedRepo& rep;
	MedValidator& val;

	/*
	 Functie de sortare generala
	 maiMicF- conditia dupa care se sorteaza
	 returneaza o lista sortata dupa criteriu dat ca paramatru
	*/
	vector<Meds> generalSort(bool (*maiMicF)(const Meds&, const Meds&));
	/*
	Functie generala de filtrare
	fct-o functie dupa care se face filtrarea
	returneaza doar medicamentele care trec de filtru (fct(med)==true)
	*/
	vector<Meds> filtreaza(function<bool(const Meds&)> fct);
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
	void addMed(const string& name, const string& producer, const string& active_subst, int price);


	const Meds& findMed(const string& name);

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

};
void testSrv();

