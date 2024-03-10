#pragma once

#include "Service.h"
class ConsolUI {
	Pharmacy& srv;
	

public:
	ConsolUI(Pharmacy& srv) :srv{ srv } {
	}
	//nu permitem copierea obiectelor
	ConsolUI(const ConsolUI& ot) = delete;
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

	void modificaUI();

	void stergeUI();

	void adaugaRetetaUi();

	void adaugaRandomRetetaUi();

	void golesteRetetaUi();

	void exporta(const vector<Meds>& meds);

	void raportUi();

	/*
	Tiparim meniul
	*/
	void meniu();
	void start();
};

