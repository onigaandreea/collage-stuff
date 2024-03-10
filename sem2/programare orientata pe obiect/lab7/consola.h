#pragma once

#include "service.h"
#include "entitate.h"
class ConsolUI {
	Pharmacy& srv;
	
	//Citeste datele de la tastatura si adauga medicament
	//arunca exceptie daca: nu se poate salva, nu e valid
	
	void adaugaUI();
	
	//Tipareste o lista de medicamente la consola
	
	void tipareste(const VectDinNewDeleteT<Meds> meds);

	void modificaUI();

	void stergeUI();

	void cautaUI();

	
	//Tiparim meniul
	
	void meniu();

public:
	ConsolUI(Pharmacy& srv) :srv{ srv } {
	}
	//nu permitem copierea obiectelor
	ConsolUI(const ConsolUI& ot) = delete;

	void start();
};
