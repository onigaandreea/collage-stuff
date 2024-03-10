#pragma once

#include "vectorDinamic.h"
#include "medicament.h"
#include "repository.h"
#include <string>

#include <functional>
#include "validator.h"

using std::function;


class Pharmacy {
	MedRepo& rep;
	validator& val;

	//Functie de sortare generala
	//maiMicF- conditia dupa care se sorteaza
	//returneaza o lista sortata dupa criteriu dat ca paramatru

	VectDinNewDeleteT<medicament> generalSort(bool (*maiMicF)(const medicament&, const medicament&));

	//Functie generala de filtrare
	//fct-o functie dupa care se face filtrarea
	//returneaza doar medicamentele care trec de filtru (fct(med)==true)

	VectDinNewDeleteT<medicament> filtreaza(function<bool(const medicament&)> fct);
public:
	Pharmacy(MedRepo& rep, validator& val) :rep{ rep }, val{ val } {
	}
	//nu permitem copierea de obiecte Pharmacy
	Pharmacy(const Pharmacy& ot) = delete;

	//returneaza toate medicamentele in ordinea in care au fost adaugate

	VectDinNewDeleteT<medicament> getAll(){
		return rep.getAllMeds();
	}

	//Adauga un nou medicament
	//arunca exceptie daca: nu se poate salva, nu este valid

	void addMed(const string& name, const string& producer, const string& active_subst, int price);

	//sterge un medicament
	//arunca exceptie daca elementul nu exista
	medicament deleteMed(const string& name, const string& producer, const string& active_subst, int price);

	//modifica un element
	//arunca exceptie daca: nu exista elementul, noile valori nu sunt valide
	void modifyMed(const medicament& m1, const medicament& m2);

	//cauta un element dupa denumire
	//returneaza elementul cautat
	//arunca exceptie daca elementul nu exista
	medicament findMed(const string& name);


	//Sorteaza dupa denumire

	VectDinNewDeleteT<medicament> sortByName();


	//Sorteaza dupa producer

	VectDinNewDeleteT<medicament> sortByProducer();



	//Sorteaza dupa substanta activa+pret

	VectDinNewDeleteT<medicament> sortByActSubstPrice();


	//returneaza doar medicamentele cu pret mai mic decat cel dat

	VectDinNewDeleteT<medicament> filtrarePretMaiMic(int pret);

	//returneaza doar acele medicamente care au substanta activa data

	VectDinNewDeleteT<medicament> filtrareSubstActv(string substanta);


	//returneaza doar medicamentele cu pret intre cele doua preturi

	VectDinNewDeleteT<medicament> filtrarePret(int pretMin, int pretMax);

};

