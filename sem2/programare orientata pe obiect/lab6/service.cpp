#include "service.h"
#include <functional>
#include <algorithm>

vector<Meds> Pharmacy::generalSort(bool(*maiMicF)(const Meds&, const Meds&)) {
	vector<Meds> v{ rep.getAll() };//fac o copie	
	for (size_t i = 0; i < v.size(); i++) {
		for (size_t j = i + 1; j < v.size(); j++) {
			if (!maiMicF(v[i], v[j])) {
				//interschimbam
				Meds aux = v[i];
				v[i] = v[j];
				v[j] = aux;
			}
		}
	}
	return v;
}

/*
Adauga un nou medicament
arunca exceptie daca: nu se poate salva, nu este valid
*/
void Pharmacy::addMed(const string& name, const string& producer, const string& active_subst, int price) {
	Meds m{ name, producer, active_subst, price };
	val.validate(m);
	rep.store(m);
}


const Meds& Pharmacy::findMed(const string& name) {

	return rep.find(name);
}
/*
Sorteaza dupa denumire
*/
vector<Meds> Pharmacy::sortByName() {
	auto copyAll = rep.getAll();
	std::sort(copyAll.begin(), copyAll.end(), cmpName);
	return copyAll;
	//return generalSort(cmpType);
}

/*
Sorteaza dupa producator
*/
vector<Meds> Pharmacy::sortByProducer() {
	return generalSort(cmpProducer);
}


/*
Sorteaza dupa substanta activa+pret
*/
vector<Meds> Pharmacy::sortByActSubstPrice() {
	return generalSort([](const Meds& m1, const Meds& m2) {
		if (m1.getActSubst() == m2.getActSubst()) {
			return cmpPrice(m1, m2);
		}
		return cmpActSubst(m1, m2);
		});
}

vector<Meds> Pharmacy::filtreaza(function<bool(const Meds&)> fct) {
	vector<Meds> rez;
	for (const auto& med : rep.getAll()) {
		if (fct(med)) {
			rez.push_back(med);
		}
	}
	return rez;
}

vector<Meds> Pharmacy::filtrarePretMaiMic(int pret) {
	return filtreaza([pret](const Meds& m) {
		return m.getPrice() < pret;
		});
}

vector<Meds> Pharmacy::filtrareSubstActv(string substanta) {
	return filtreaza([substanta](const Meds& m) {
		return m.getActSubst() == substanta;
		});
}

vector<Meds> Pharmacy::filtrarePret(int pretMin, int pretMax) {
	return filtreaza([=](const Meds& m) {
		return m.getPrice() >= pretMin && m.getPrice() <= pretMax;
		});
}
