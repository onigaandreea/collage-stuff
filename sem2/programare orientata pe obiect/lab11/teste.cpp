#include "teste.h"
#include <assert.h>
#include <iostream>
#include <sstream>

using std::ostream;
using std::stringstream;

void testeDomeniu() {
	Meds med1{ "a", "aa", "a", 6};
	assert(med1.getName()=="a");
	assert(med1.getProducer() == "aa");
	assert(med1.getActSubst() == "a");
	assert(med1.getPrice() == 6);

	Meds med2 { "b", "bb", "b", 12 };
	assert(med2.getName() == "b");
	assert(med2.getProducer() == "bb");
	assert(med2.getActSubst() == "b");
	assert(med2.getPrice() == 12);

	med2.setName("c");
	med2.setProducer("cc");
	med2.setActSubst("c");
	med2.setPrice(20);

	assert(med2.getName() == "c");
	assert(med2.getProducer() == "cc");
	assert(med2.getActSubst() == "c");
	assert(med2.getPrice() == 20);
}

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
	try {
		rep.cauta("c");
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
	int i1 = rep.cauta("a");
	vector<Meds> all = rep.getAll();
	Meds m3 = all[i1];
	assert(m3.getName() == "a");
	assert(m3.getProducer() == "b");
	assert(m3.getActSubst() == "c");
	assert(m3.getPrice() == 7);

	rep.empty();
	assert(rep.getAll().size() == 0);
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

	srv.deleteMed("b");
	assert(srv.getAll().size() == 2);

	try {
		srv.deleteMed("z");
		assert(false);
	}
	catch (MedRepoException&) {
		assert(true);
	}

	//update
	Meds m1{ "a", "a", "a", 6 };
	srv.updateMed("a", "a", "b", "c", 10);
	int i1 = rep.cauta("a");
	vector<Meds> all = rep.getAll();
	Meds m3 = all[i1];
	assert(rep.exist(m3));

	Meds m2{ "j", "k", "c", 10 };
	try {
		srv.updateMed("j", "a", "b", "c", 10);
		assert(false);
	}
	catch (MedRepoException&) {
		assert(true);
	}
}

void testUndo() {
	MedRepo rep;
	MedValidator val;
	Pharmacy srv{ rep,val };
	try {
		srv.undo();
		assert(false);
	}
	catch (MedRepoException&) {
		assert(true);
	}
	srv.addMed("a", "a", "a", 6);
	srv.addMed("b", "b", "b", 60);

	srv.undo();
	assert(srv.getAll().size() == 1);
	Meds m = { "a", "a", "a", 6 };
	srv.deleteMed("a");
	assert(srv.getAll().size() == 0);
	srv.undo();
	assert(srv.getAll().size() == 1);
	assert(rep.exist(m));
	Meds m1 = { "a", "a", "a", 6 };
	srv.updateMed("a", "b", "cc", "b", 12);
	Meds updated = { "b", "cc", "b", 12 };
	assert(rep.exist(updated));
	srv.undo();
	assert(rep.exist(m));

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
	srv.addToReteta("a");
	assert(srv.getMedsFromReteta().size() == 1);

	srv.addToReteta("b");
	assert(srv.getMedsFromReteta().size() == 2);

	srv.addMed("cc", "bc", "eb", 17);
	srv.addMed("d", "bb", "gb", 13);
	srv.addMed("e", "ba", "jb", 20);

	srv.emptyReteta();
	assert(srv.getMedsFromReteta().size() == 0);
	srv.addRandomToReteta(3);
	assert(srv.getMedsFromReteta().size() == 3);
}

void testdict() {
	MedRepo rep;
	MedValidator val;
	Pharmacy srv{ rep,val };
	srv.addMed("a", "a", "a", 6);
	srv.addMed("b", "b", "b", 60);
	srv.addMed("c", "b", "a", 80);
	unordered_map<string, Raport> set = srv.getProducer("a");
	assert(set["a"].getNumar() == 1);
	set = srv.getProducer("b");
	assert(set["b"].getNumar() == 2);
	set = srv.getProducer("c");
	assert(set["c"].getNumar() == 0);
}
