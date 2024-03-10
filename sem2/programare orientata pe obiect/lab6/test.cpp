#include "test.h"
#include <assert.h>
#include <iostream>
#include <sstream>

using std::ostream;
using std::stringstream;


void testValidator() {
	MedValidator v;
	Meds m{ "","","", -1 };
	try {
		v.validate(m);
	}
	catch (const ValidateException& ex) {
		std::stringstream sout;
		sout << ex;
		auto mesaj = sout.str();
		assert(mesaj.find("negativ") >= 0);
		assert(mesaj.find("vid") >= 0);
		assert(mesaj.find("vida") >= 0);
	}

}

void testAdauga() {
	MedRepo rep;
	rep.store(Meds{ "a","a","a",4 });
	assert(rep.getAll().size() == 1);
	assert(rep.find("a").getProducer() == "a");

	rep.store(Meds{ "b","b","b",4 });
	assert(rep.getAll().size() == 2);

	//nu pot adauga 2 cu aceeasi denumire
	try {
		rep.store(Meds{ "a","b","c",7 });
		assert(false);
	}
	catch (const MedRepoException&) {
		assert(true);
	}
	//cauta inexistent
	try {
		rep.find("c");
		assert(false);
	}
	catch (const MedRepoException& e) {
		stringstream os;
		os << e;
		assert(os.str().find("exista") >= 0);
	}
}

void testCauta() {
	MedRepo rep;
	rep.store(Meds{ "a","a","a",4 });
	rep.store(Meds{ "b","b","b",7 });

	auto m = rep.find("a");
	assert(m.getName() == "a");
	assert(m.getProducer() == "a");
	assert(m.getActSubst() == "a");
	int i = rep.cauta("a");
	assert(i == 0);
	int j = rep.cauta("b");
	assert(j == 1);
	//arunca exceptie daca nu gaseste
	try {
		rep.find("c");
		assert(false);
	}
	catch (MedRepoException&) {
		assert(true);
	}

}

void testeRepo() {
	testAdauga();
	testCauta();
}


void testAdaugaSrv() {
	MedRepo rep;
	MedValidator val;
	Pharmacy srv{ rep,val };
	srv.addMed("a", "a", "a", 6);
	assert(srv.getAll().size() == 1);

	//adaug ceva invalid
	try {
		srv.addMed("", "", "", 6);
		assert(false);
	}
	catch (ValidateException&) {
		assert(true);
	}
	//incerc sa adaug ceva ce existadeja
	try {
		srv.addMed("a", "b", "b", 5);
		assert(false);
	}
	catch (MedRepoException&) {
		assert(true);
	}
}

void testFiltrare() {
	MedRepo rep;
	MedValidator val;
	Pharmacy srv{ rep,val };
	srv.addMed("a", "a", "a", 6);
	srv.addMed("b", "b", "b", 60);
	srv.addMed("c", "b", "a", 80);
	assert(srv.filtrarePret(6, 70).size() == 2);
	assert(srv.filtrarePret(6, 60).size() == 2);
	assert(srv.filtrareSubstActv("a").size() == 2);
	assert(srv.filtrareSubstActv("b").size() == 1);
	assert(srv.filtrarePretMaiMic(60).size() == 1);
	assert(srv.filtrarePretMaiMic(7).size() == 1);
	assert(srv.filtrarePretMaiMic(6).size() == 0);
}

void testSortare() {
	MedRepo rep;
	MedValidator val;
	Pharmacy srv{ rep,val };
	srv.addMed("d", "a", "b", 6);
	srv.addMed("b", "b", "a", 60);
	srv.addMed("c", "b", "a", 80);

	auto firstP = srv.sortByName()[0];
	assert(firstP.getName() == "b");

	firstP = srv.sortByProducer()[0];
	assert(firstP.getProducer() == "a");

	firstP = srv.sortByActSubstPrice()[0];
	assert(firstP.getPrice() == 60);

}

void testSrv() {
	testAdaugaSrv();
	testFiltrare();
	testSortare();
}