#include "teste.h"
#include "console.h"
#include <iostream>

using std::cout;

void adaugaCateva(Pharmacy& srv) {
	srv.addMed("a", "aa", "a", 6);
	srv.addMed("b", "bb", "ab", 30);
	srv.addMed("d", "bc", "b", 10);
	srv.addMed("c", "bb", "a", 20);
}


void testAll() {
	testValidator();
	testeRepo();
	testeService();
	cout << "Testele au trecut!!!\n";
}

int main()
{
	testAll();

	MedRepo rep;
	validator val;
	Pharmacy ctr{ rep,val };
	adaugaCateva(ctr);
	ConsolUI ui{ ctr };
	ui.start();

	_CrtDumpMemoryLeaks(); 
}