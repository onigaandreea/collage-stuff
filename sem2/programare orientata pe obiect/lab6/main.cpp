#include "consola.h"
#include "test.h"
#include <iostream>
using std::cout;

void adaugaCateva(Pharmacy& srv) {
	srv.addMed("a", "aa", "a", 6);
	srv.addMed("b", "bb", "ab", 30);
	srv.addMed("d", "bc", "b", 10);
	srv.addMed("c", "bb", "a", 20);
}

void testAll() {
	testeRepo();
	testSrv();
	testValidator();
	cout << "Testele au trecut!!";
}

int main() {
	testAll();

	MedRepo rep;
	MedValidator val;
	Pharmacy ctr{ rep,val };
	adaugaCateva(ctr);//adaug cateva medicamente
	ConsolUI ui{ ctr };
	ui.start();
}