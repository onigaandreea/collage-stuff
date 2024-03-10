#pragma once

#include "vectorDinamic.h"
#include "entitate.h"
#include "repo.h"
#include <string>

#include <functional>
#include "validator.h"

using std::function;


class Pharmacy {
	MedRepo& rep;
	MedValidator& val;

	
	 //Functie de sortare generala
	 //maiMicF- conditia dupa care se sorteaza
	 //returneaza o lista sortata dupa criteriu dat ca paramatru
	
	VectDinNewDeleteT<Meds> generalSort(bool (*maiMicF)(const Meds&, const Meds&));
	
	//Functie generala de filtrare
	//fct-o functie dupa care se face filtrarea
	//returneaza doar medicamentele care trec de filtru (fct(med)==true)
	
	VectDinNewDeleteT<Meds> filtreaza(function<bool(const Meds&)> fct);
public:
	Pharmacy(MedRepo& rep, MedValidator& val) :rep{ rep }, val{ val } {
	}
	//nu permitem copierea de obiecte Pharmacy
	Pharmacy(const Pharmacy& ot) = delete;
	
	//returneaza toate medicamentele in ordinea in care au fost adaugate
	
	const VectDinNewDeleteT<Meds>& getAll() const noexcept {
		return rep.getAllMeds();
	}
	
	//Adauga un nou medicament
	//arunca exceptie daca: nu se poate salva, nu este valid
	
	void addMed(const string& name, const string& producer, const string& active_subst, int price);

	Element deleteMed(const string& name, const string& producer, const string& active_subst, int price);

	void modifyMed(Element m1, Element m2);

	const Element findMed(const string& name);

	
	//Sorteaza dupa denumire
	
	VectDinNewDeleteT<Meds> sortByName();

	
	//Sorteaza dupa producer
	
	VectDinNewDeleteT<Meds> sortByProducer();


	
	//Sorteaza dupa substanta activa+pret
	
	VectDinNewDeleteT<Meds> sortByActSubstPrice();

	
	//returneaza doar medicamentele cu pret mai mic decat cel dat
	
	VectDinNewDeleteT<Meds> filtrarePretMaiMic(int pret);
	
	//returneaza doar acele medicamente care au substanta activa data
	
	VectDinNewDeleteT<Meds> filtrareSubstActv(string substanta);

	
	//returneaza doar medicamentele cu pret intre cele doua preturi
	
	VectDinNewDeleteT<Meds> filtrarePret(int pretMin, int pretMax);

};

void testAdaugaSrv();
void testFiltrare();
void testSortare();
void testSrv();
