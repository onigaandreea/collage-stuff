#include "service.h"

#include <functional>
#include <algorithm>
#include <utility>

	//Functie de sortare generala
	//maiMicF- conditia dupa care se sorteaza
	//returneaza o lista sortata dupa criteriu dat ca paramatru

VectDinNewDeleteT<medicament> Pharmacy::generalSort(bool (*maiMicF)(const medicament&, const medicament&)) {
	VectDinNewDeleteT<medicament> v = { rep.getAllMeds() };//fac o copie
	for (size_t i = 0; i < v.size(); i++) {
		for (size_t j = i + 1; j < v.size(); j++) {
			if (!maiMicF(v.get(i), v.get(j))) {
				//interschimbam
				medicament aux = v.get(i);
				v.get(i) = v.get(j);
				v.get(j) = aux;
			}
		}
	}
	return v;
}

//Functie generala de filtrare
//fct-o functie dupa care se face filtrarea
//returneaza doar medicamentele care trec de filtru (fct(med)==true)

VectDinNewDeleteT<medicament> Pharmacy::filtreaza(function<bool(const medicament&)> fct) {
	VectDinNewDeleteT<medicament> rez;
	for (int i = 0; i < rep.size(); i++) {
		if (fct(rep.getelem(i))) {
			rez.add(rep.getelem(i));
		}
	}
	return rez;
}

	
	//Adauga un nou medicament
	//arunca exceptie daca: nu se poate salva, nu este valid

void Pharmacy::addMed(const string& name, const string& producer, const string& active_subst, int price) {
	medicament m{ name, producer, active_subst, price };
	val.validate(m);
	rep.store(m);
}

	//sterge un medicament
	//arunca exceptie daca elementul nu exista
medicament Pharmacy::deleteMed(const string& name, const string& producer, const string& active_subst, int price) {
	medicament m{ name, producer, active_subst, price };
	medicament deleted = rep.del(m);
	return deleted;
}

	//modifica un element
	//arunca exceptie daca: nu exista elementul, noile valori nu sunt valide
void Pharmacy::modifyMed(const medicament& m1, const medicament& m2) {
	val.validate(m2);
	rep.modify(m1, m2);
}

	//cauta un element dupa denumire
	//returneaza elementul cautat
	//arunca exceptie daca elementul nu exista
medicament Pharmacy::findMed(const string& name) {
	medicament found = rep.find(name);
	return found;
}


	//Sorteaza dupa denumire

VectDinNewDeleteT<medicament> Pharmacy::sortByName() {
	return generalSort(cmpName);
}


	//Sorteaza dupa producer

VectDinNewDeleteT<medicament> Pharmacy::sortByProducer()
{
	return generalSort(cmpProducer);
}



	//Sorteaza dupa substanta activa+pret

VectDinNewDeleteT<medicament> Pharmacy::sortByActSubstPrice() {
	return generalSort([](const medicament& m1, const medicament& m2) {
		if (m1.getActSubst() == m2.getActSubst()) {
			return cmpPrice(m1, m2);
		}
		return cmpActSubst(m1, m2);
		});
}


	//returneaza doar medicamentele cu pret mai mic decat cel dat

VectDinNewDeleteT<medicament> Pharmacy::filtrarePretMaiMic(int pret) {
	return filtreaza([pret](const medicament& m) {
		return m.getPrice() < pret;
		});
}

	//returneaza doar acele medicamente care au substanta activa data

VectDinNewDeleteT<medicament> Pharmacy::filtrareSubstActv(string substanta) {
	return filtreaza([substanta](const medicament& m) {
		return m.getActSubst()==substanta;
		});
}


	//returneaza doar medicamentele cu pret intre cele doua preturi

VectDinNewDeleteT<medicament> Pharmacy::filtrarePret(int pretMin, int pretMax) {
	return filtreaza([=](const medicament& m) {
		return m.getPrice() >= pretMin && m.getPrice() <= pretMax;
		});
}
