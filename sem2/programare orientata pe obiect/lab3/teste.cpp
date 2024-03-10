#include "teste.h"

#include <assert.h>
#include <sstream>


void testValidator() {
	validator v;
	medicament m{ "","","", -1 };
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

	//test adaugare

	rep.store(medicament{ "a","a","a",4 });
	assert(rep.size() == 1);
	assert(rep.cauta("a") == 0);

	try {
		rep.store(medicament{ "a", "b","c",7 });
		//assert(false);
	}
	catch (const MedRepoException&) {
		assert(true);

	}
	
	//test cauta
	medicament m = rep.find("a");
	rep.store(medicament{ "b","b","b",7 });
	assert(m.getName() == "a");
	assert(m.getProducer() == "a");
	assert(m.getActSubst() == "a");
	int i = rep.cauta("a");
	assert(i == 0);
	int j = rep.cauta("b");
	assert(j == 1);

	try
	{
		rep.find("c");
		//assert(false);
	}
	catch (const MedRepoException&) {
		assert(true);

	}

	try
	{
		rep.cauta("z");
		//assert(false);
	}
	catch (const MedRepoException&) {
		assert(true);

	}


	//test modificare
	
	medicament modified = rep.getelem(0), newelem = medicament{ "c","c","c",7 };

	rep.modify(modified, newelem);

	assert(rep.getelem(0).getName() == "c");
	assert(rep.getelem(0).getProducer() == "c");
	assert(rep.getelem(0).getActSubst() == "c");
	assert(rep.getelem(0).getPrice() == 7);

	try
	{
		rep.find("a");
		//assert(false);
	}
	catch (const MedRepoException&) {
		assert(true);

	}

	//test stergere
	medicament deleted = rep.del(rep.getelem(0));

	assert(rep.getAllMeds().size() == 1);
	assert(deleted.getName() == "c");

	try {
		rep.del(medicament{ "a", "b","c",7 });
		//assert(false);
	}
	catch (const MedRepoException&) {
		assert(true);
	}
}

void testeService() {
	MedRepo rep;
	validator val;
	Pharmacy srv{ rep,val };

	//teste agaugare
	srv.addMed("a", "a", "a", 6);
	assert(srv.getAll().size() == 1);

	//adaug ceva invalid
	try {
		srv.addMed("", "", "", 6);
		//assert(false);
	}
	catch (ValidateException&) {
		assert(true);
	}

	//adaug ceva existent
	try {
		srv.addMed("a", "b", "c", 6);
		//assert(false);
	}
	catch (MedRepoException&) {
		assert(true);
	}

	//test cauta
	medicament found = srv.findMed("a");

	assert(found.getProducer() == "a");
	assert(found.getName() == "a");
	assert(found.getActSubst() == "a");
	assert(found.getPrice() == 6);

	//cauta inexistent
	try {
		srv.findMed("b");
		//assert(false);
	}
	catch (MedRepoException&) {
		assert(true);
	}

	//modificare

	medicament m3 = medicament{ "c", "c", "c", 12 };

	srv.modifyMed(rep.getelem(0), m3);
	assert(rep.getelem(0).getName() == "c");

	//modifica inexistent
	try {
		srv.modifyMed(medicament{"z","z","z",12}, medicament{"a","b","c",10});
		//assert(false);
	}
	catch (MedRepoException&) {
		assert(true);
	}

	//modifica invalid
	try {
		srv.modifyMed(rep.getelem(0), medicament{"","","",-10});
		//assert(false);
	}
	catch (ValidateException&) {
		assert(true);
	}

	//stergere
	medicament m1 = rep.getelem(0);
	medicament deleted = srv.deleteMed(m1.getName(), m1.getProducer(), m1.getActSubst(), m1.getPrice());
	assert(deleted.getName() == "c");
	assert(srv.getAll().size() == 0);

	//sterge inexistent
	try {
		srv.deleteMed("z","z","z",12);
		//assert(false);
	}
	catch (MedRepoException&) {
		assert(true);
	}

	srv.addMed("z", "a", "a", 6);
	srv.addMed("g", "b", "b", 60);
	srv.addMed("f", "b", "a", 80);
	srv.addMed("d", "a", "c", 10);
	srv.addMed("b", "b", "b", 20);
	srv.addMed("c", "b", "d", 15);
	assert(srv.getAll().size() == 6);

	//filtrare
	assert(srv.filtrarePret(20, 70).size() == 2);
	assert(srv.filtrarePret(10, 60).size() == 4);
	assert(srv.filtrareSubstActv("a").size() == 2);
	assert(srv.filtrareSubstActv("b").size() == 2);
	assert(srv.filtrarePretMaiMic(60).size() == 4);
	assert(srv.filtrarePretMaiMic(7).size() == 1);
	assert(srv.filtrarePretMaiMic(20).size() == 3);

	//sortare
	auto firstP = srv.sortByName().get(0);
	assert(firstP.getName() == "b");

	firstP = srv.sortByProducer().get(0);
	assert(firstP.getProducer() == "a");

	firstP = srv.sortByActSubstPrice().get(0);
	assert(firstP.getPrice() == 6);
}
