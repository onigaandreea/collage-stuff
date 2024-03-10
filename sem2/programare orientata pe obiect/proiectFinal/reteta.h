#pragma once
#include "med.h"
#include <vector>
#include <algorithm>
#include <random>
#include "observer.h"
#include "repository.h"

using std::vector;
class Reteta:public Observable
{
private:
	vector<Meds> retetaMed;
	const MedRepo& repo;
public:
	Reteta(const MedRepo& rep) :repo{ rep } {};

	/*
	* Adauga un medicament in reteta
	*/
	void addMedToReteta(const Meds& m);
	/*
	* Elimina toate medicamentele din reteta
	*/
	void emptyReteta();
	

	void addRandomMeds(int howMany);
	/*
	* Returneaza un vector care contine toate melodiile din playlist
	*/
	const vector<Meds>& getAllFromReteta();
};

void testReteta();


