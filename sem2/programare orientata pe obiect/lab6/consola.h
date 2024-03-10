#pragma once

#include "service.h"
#include "med.h"
class ConsolUI {
	Pharmacy& srv;
	/*
	Citeste datele de la tastatura si adauga medicament
	arunca exceptie daca: nu se poate salva, nu e valid
	*/
	void adaugaUI();
	/*
	Tipareste o lista de medicamente la consola
	*/
	void tipareste(const vector<Meds>& meds);

	void cautaUI();

	/*
	Tiparim meniul
	*/
	void meniu();

public:
	ConsolUI(Pharmacy& srv) :srv{ srv } {
	}
	//nu permitem copierea obiectelor
	ConsolUI(const ConsolUI& ot) = delete;

	void start();
};
