#include "teste.h"
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

void testeRepo() {
	MedRepo rep;

	//adaugare
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

	//cautari
	//cauta existent
	auto m = rep.find("a");
	assert(m.getName() == "a");
	assert(m.getProducer() == "a");
	assert(m.getActSubst() == "a");

	//cautare ce returneaza index
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

	//stergere
	assert(rep.getAll().size() == 2);
	Meds m4 = Meds("b", "b", "b", 4);
	assert(rep.exist(m4));
	rep.del(m4);
	assert(rep.getAll().size() == 1);
	

	//arunca exceptie
	try {
		rep.del(Meds{ "z","x","y",10 });
		assert(false);
	}
	catch (MedRepoException&) {
		assert(true);
	}

	//modificari
	Meds m2{ "a","a","a",4 };
	Meds m1{ "a","b","c",7 };
	rep.update(m2, m1);
	assert(m2.getName() == "a");
	assert(m2.getProducer() == "b");
	assert(m2.getActSubst() == "c");
	assert(m2.getPrice() == 7);
}


void testeSrv() {
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
	//incerc sa adaug ceva ce exista deja
	try {
		srv.addMed("a", "b", "b", 5);
		assert(false);
	}
	catch (MedRepoException&) {
		assert(true);
	}

	//cautari

	auto m = srv.findMed("a");
	assert(m.getName() == "a");
	assert(m.getProducer() == "a");
	assert(m.getActSubst() == "a");

	//arunca exceptie daca nu gaseste
	try {
		srv.findMed("c");
		assert(false);
	}
	catch (MedRepoException&) {
		assert(true);
	}

	//stergere
	srv.addMed("b", "c", "d", 16);
	srv.addMed("g", "f", "f", 9);

	srv.deleteMed(Meds{ "b", "c", "d", 16 });
	assert(srv.getAll().size() == 2);

	try {
		srv.deleteMed(Meds{ "z","x","y",20 });
		assert(false);
	}
	catch (MedRepoException&) {
		assert(true);
	}

	//update
	Meds m1{ "a", "a", "a", 6 };
	srv.updateMed(m1, "a", "b", "c", 10);
	Meds updated = { "a", "b", "c", 10 };
	assert(rep.exist(updated));

	Meds m3{ "j", "k", "c", 10 };
	try {
		srv.updateMed(m3, "a", "b", "c", 10);
		assert(false);
	}
	catch (MedRepoException&) {
		assert(true);
	}
	 
	/*srv.deleteMed(Meds{ "a", "a", "a", 6 });
	srv.deleteMed(Meds{ "g", "f", "f", 9 });*/


	////filtrari
	//srv.addMed("a", "a", "a", 6);
	//srv.addMed("b", "b", "b", 60);
	//srv.addMed("c", "b", "a", 80);
	//assert(srv.filtrarePret(6, 70).size() == 2);
	//assert(srv.filtrarePret(6, 60).size() == 2);
	//assert(srv.filtrareSubstActv("a").size() == 2);
	//assert(srv.filtrareSubstActv("b").size() == 1);
	//assert(srv.filtrarePretMaiMic(60).size() == 1);
	//assert(srv.filtrarePretMaiMic(7).size() == 1);
	//assert(srv.filtrarePretMaiMic(6).size() == 0);


	////sortari
	//srv.deleteMed(Meds{ "a", "a", "a", 6 });
	//srv.deleteMed(Meds{ "b", "b", "b", 60 });
	//srv.deleteMed(Meds{ "c", "b", "a", 80 });

	//srv.addMed("d", "a", "b", 6);
	//srv.addMed("b", "b", "a", 60);
	//srv.addMed("c", "b", "a", 80);

	//auto firstP = srv.sortByName()[0];
	//assert(firstP.getName() == "b");

	//firstP = srv.sortByProducer()[0];
	//assert(firstP.getProducer() == "a");

	//firstP = srv.sortByActSubstPrice()[0];
	//assert(firstP.getPrice() == 60);

}

void testfiltrare() {

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

void testsortare() {
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

void testRetetaSrv() {
	Reteta ret;
	MedRepo rep;
	MedValidator val;
	Pharmacy srv{ rep,val };
	srv.addMed("a", "a", "a", 6);
	srv.addMed("b", "b", "b", 60);
	srv.addMed("c", "b", "a", 80);
	assert(srv.getMedsFromReteta().size() == 0);
	Meds m1{ "a","a","a",6 };
	srv.addToReteta(m1.getName());
	assert(srv.getMedsFromReteta().size() == 1);

	Meds m2{ "b","b","b",60 };
	srv.addToReteta(m2.getName());
	assert(ret.getAllFromReteta().size() == 2);
	
	srv.addMed("c", "bc", "eb", 17);
	srv.addMed("d", "bb", "gb", 13);
	srv.addMed("e", "ba", "jb", 20);

	srv.emptyReteta();
	assert(srv.getMedsFromReteta().size() == 0);
	srv.addRandomToReteta(3);
	assert(srv.getMedsFromReteta().size() == 3);
	

}
