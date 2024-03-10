#pragma once
#include "Meds.h"
#include <vector>
#include <algorithm>
#include <random>

using std::vector;
class Reteta
{
private:
	vector<Meds> retetaMed;
public:
	Reteta() = default;

	/*
	* Adauga un medicament in reteta
	*/
	void addMedToReteta(const Meds& m);
	/*
	* Elimina toate medicamentele din reteta
	*/
	void emptyReteta();
	/*
	* Adauga un numar random de medicamente in reteta
	*
	* @param originalMeds: medicamentele din care se alege (vector<Meds>)
	* @param howMany: cate medicamente se adauga (int)
	*
	* post: medicamentele sunt adaugate in reteta curenta
	*/
	void addRandomMeds(vector<Meds> originalMeds, int howMany);
	/*
	* Returneaza un vector care contine toate melodiile din playlist
	*/
	const vector<Meds>& getAllFromReteta();
};

void testReteta();

