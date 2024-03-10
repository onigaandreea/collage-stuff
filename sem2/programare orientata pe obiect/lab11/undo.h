#pragma once
#include "repository.h"

class ActiuneUndo {
public:
	virtual void doUndo() = 0;
	//destructorul e virtual pentru a ne asigura ca daca dau delete pe un 
	 // pointer se apeleaza destructorul din clasa care trebuie
	virtual ~ActiuneUndo() {};
};
class UndoAdauga : public ActiuneUndo {
	Meds medAdaugat;
	MedRepo& rep;
public:
	UndoAdauga(MedRepo& rep, const Meds& m) :rep{ rep }, medAdaugat{ m } {}
	void doUndo() override {
		rep.del(medAdaugat);
	}
};

class UndoSterge : public ActiuneUndo {
	Meds medSters;
	MedRepo& rep;
public:
	UndoSterge(MedRepo& rep, const Meds& m) :rep{ rep }, medSters{ m } {}
	void doUndo() override {
		rep.store(medSters);
	}
};

class UndoModifica : public ActiuneUndo {
	Meds medDeModificat, medModificat;
	MedRepo& rep;
public:
	UndoModifica(MedRepo& rep, const Meds& m1, const Meds& m2) :rep{ rep }, medDeModificat{ m1 }, medModificat{ m2 } {}
	void doUndo() override {
		rep.update(medModificat, medDeModificat);
	}
};

