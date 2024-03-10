#include "lab11.h"
#include "service.h"
#include "medgui.h"
#include "teste.h"
#include <QtWidgets/QApplication>

void adaugaCateva(Pharmacy& srv) {
	srv.addMed("a", "aa", "a", 6);
	srv.addMed("b", "bb", "ab", 30);
	srv.addMed("d", "bc", "b", 10);
	srv.addMed("c", "bb", "a", 20);
}

int runGUI(int argc, char* argv[])
{
	QApplication a(argc, argv);
	MedRepo rep;
	MedValidator val;
	Pharmacy ctr{ rep, val };
	Reteta reteta;
	adaugaCateva(ctr);
	PharmacyGUI gui{ ctr };
	gui.show();
	return a.exec();
}

int main(int argc, char* argv[])
{
	testValidator();
	testeDomeniu();
	testeRepo();
	testeSrv();
	testfiltrare();
	testsortare();
	testUndo();
	testdict();
	testRetetaSrv();

	runGUI(argc, argv);

	return 0;
}

