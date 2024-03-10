
#include "service.h"
#include <functional>
#include <algorithm>
#include <assert.h>
#include <utility>

VectDinNewDeleteT<Meds> Pharmacy::generalSort(bool(*maiMicF)(const Meds&, const Meds&)) {
	const VectDinNewDeleteT<Meds>& v = { rep.getAllMeds() };//fac o copie
	for (size_t i = 0; i < v.size(); i++) {
		for (size_t j = i + 1; j < v.size(); j++) {
			if (!maiMicF(v.get(i), v.get(j))) {
				//interschimbam
				Meds aux = v.get(i);
				v.get(i) = v.get(j);
				v.get(j) = aux;
			}
		}
	}
	return v;
}


//Adauga un nou medicament
//arunca exceptie daca: nu se poate salva, nu este valid

void Pharmacy::addMed(const string& name, const string& producer, const string& active_subst, int price) {
	Meds m{ name, producer, active_subst, price };
	val.validate(m);
	rep.store(m);
}


const Element Pharmacy::findMed(const string& name) {

	return rep.find(name);
}

Element Pharmacy::deleteMed(const string& name, const string& producer, const string& active_subst, int price) {
	Meds m{ name, producer, active_subst, price };
	Element deleted=rep.del(m);
	return deleted;
}

void Pharmacy::modifyMed(Element m1, Element m2)
{
	rep.modify(m1, m2);
}

//Sorteaza dupa denumire

VectDinNewDeleteT<Meds> Pharmacy::sortByName() {
	
	return generalSort(cmpName);
}

//Sorteaza dupa producator

VectDinNewDeleteT<Meds> Pharmacy::sortByProducer() {
	return generalSort(cmpProducer);
}

//Sorteaza dupa substanta activa+pret

VectDinNewDeleteT<Meds> Pharmacy::sortByActSubstPrice() {
	return generalSort([](const Meds& m1, const Meds& m2) {
		if (m1.getActSubst() == m2.getActSubst()) {
			return cmpPrice(m1, m2);
		}
		return cmpActSubst(m1, m2);
		});
}

VectDinNewDeleteT<Meds> Pharmacy::filtreaza(function<bool(const Meds&)> fct) {
	VectDinNewDeleteT<Meds> rez;
	for (int i = 0; i<rep.size(); i++) {
		if (fct(rep.getelem(i))) {
			rez.add(rep.getelem(i));
		}
	}
	return rez;
}

VectDinNewDeleteT<Meds> Pharmacy::filtrarePretMaiMic(int pret) {
	return filtreaza([pret](const Meds& m) {
		return m.getPrice() < pret;
		});
}

VectDinNewDeleteT<Meds> Pharmacy::filtrareSubstActv(string substanta) {
	return filtreaza([substanta](const Meds& m) {
		return m.getActSubst() == substanta;
		});
}

VectDinNewDeleteT<Meds> Pharmacy::filtrarePret(int pretMin, int pretMax) {
	return filtreaza([=](const Meds& m) {
		return m.getPrice() >= pretMin && m.getPrice() <= pretMax;
		});
}


void testAdaugaSrv() {
	MedRepo rep;
	MedValidator val;
	Pharmacy srv{ rep,val };
	srv.addMed("a", "a", "a", 6);
	VectDinNewDeleteT<Meds> all = srv.getAll();
	assert(all.size() == 1);

	Element m4 = srv.findMed("a");

	assert(m4.getProducer() == "a");

	//adaug ceva invalid
	try {
		srv.addMed("", "", "", 6);
		assert(false);
	}
	catch (ValidateException&) {
		assert(true);
	}
	srv.addMed("b", "b", "b", 8);

	Element m1 = srv.getAll().get(0), m2 = srv.getAll().get(1);
	assert(m1.getActSubst() == "a");
	assert(m2.getName() == "b");
	Element m3 = Meds{ "c", "c", "c", 12 };
	
	srv.modifyMed(m1, m3);
	//m1 = rep.get(0);
	assert(m1.getName() == "c");

	Element deleted = srv.deleteMed(m1.getName(), m1.getProducer(), m1.getActSubst(), m1.getPrice());
	assert(deleted.getName() == "a");
	assert(srv.getAll().size() == 1);
}

void testFiltrare() {
	MedRepo rep;
	MedValidator val;
	Pharmacy srv{ rep,val };
	srv.addMed("a", "a", "a", 6);
	srv.addMed("b", "b", "b", 60);
	srv.addMed("c", "b", "a", 80);
	assert(srv.getAll().size() == 3);

	
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

	auto firstP = srv.sortByName().get(0);
	assert(firstP.getName() == "b");
	
	firstP = srv.sortByProducer().get(0);
	assert(firstP.getProducer() == "a");

	firstP = srv.sortByActSubstPrice().get(0);
	assert(firstP.getPrice() == 60);
	
}
void testSrv() {
	testAdaugaSrv();
	testFiltrare();
	testSortare();
}
