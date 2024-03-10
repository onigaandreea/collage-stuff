#include "service.h"
#include <functional>
#include <algorithm>
#include <fstream>
#include <string>

using std::ofstream;
using std::string;

/*
Adauga un nou medicament
arunca exceptie daca: nu se poate salva, nu este valid
*/
void Pharmacy::addMed(string name, string producer, string active_subst, int price) {
	Meds m{ name, producer, active_subst, price };
	val.validate(m);
	rep.store(m);
	undoActions.push_back(std::make_unique<UndoAdauga>(rep, m));
}


const Meds& Pharmacy::findMed(string name) {

	return rep.find(name);
}

/*
	sterge un element
	returneaza elementul sters sau arunca exceptie daca nu exista
	*/
void Pharmacy::deleteMed(const Meds& m) {
	rep.del(m);
	undoActions.push_back(std::make_unique<UndoSterge>(rep, m));
}

/*
	modifica un element
	*/
void Pharmacy::updateMed(Meds& m, string newname, string newproducer, string newsubst, int newprice) {
	Meds newmed{ newname, newproducer, newsubst, newprice };
	rep.update(m, newmed);
	undoActions.push_back(std::make_unique<UndoModifica>(rep,m,newmed));
}

void Pharmacy::undo() {
	if (undoActions.empty()) {
		throw MedRepoException("Nu mai exista operatii");
	}

	undoActions.back()->doUndo();
	undoActions.pop_back();
}

/*
Sorteaza dupa denumire
*/
vector<Meds> Pharmacy::sortByName() {
	vector<Meds> sortedCopy{ rep.getAll() };
	sort(sortedCopy.begin(), sortedCopy.end(), cmpName);
	return sortedCopy;
}

/*
Sorteaza dupa producator
*/
vector<Meds> Pharmacy::sortByProducer() {
	vector<Meds> sortedCopy{ rep.getAll() };
	sort(sortedCopy.begin(), sortedCopy.end(), cmpProducer);
	return sortedCopy;
}


/*
Sorteaza dupa substanta activa+pret
*/
vector<Meds> Pharmacy::sortByActSubstPrice() {
	vector<Meds> sortedCopy{ rep.getAll() };
	sort(sortedCopy.begin(), sortedCopy.end(), cmpActSubstPrice);
	return sortedCopy;
}

vector<Meds> Pharmacy::filtrarePretMaiMic(int pret) {
	const vector<Meds>& allMeds = getAll();
	vector<Meds> filteredMeds;
	std::copy_if(allMeds.begin(), allMeds.end(), back_inserter(filteredMeds),
		[pret](const Meds& m) {
			return m.getPrice() < pret;
		});

	return filteredMeds;
}

vector<Meds> Pharmacy::filtrareSubstActv(string substanta) {
	const vector<Meds>& allMeds = getAll();
	vector<Meds> filteredMeds;
	std::copy_if(allMeds.begin(), allMeds.end(), back_inserter(filteredMeds),
		[substanta](const Meds& m) {
			return m.getActSubst() == substanta;
		});
	return filteredMeds;
}

vector<Meds> Pharmacy::filtrarePret(int pretMin, int pretMax) {
	const vector<Meds>& allMeds = getAll();
	vector<Meds> filteredMeds;
	std::copy_if(allMeds.begin(), allMeds.end(), back_inserter(filteredMeds),
		[=](const Meds& m) {
			return m.getPrice() >= pretMin && m.getPrice() <= pretMax;
		});
	return filteredMeds;
}

void Pharmacy::addToReteta(string name) {

	const auto& med = rep.find(name);
	RetetaCurenta.addMedToReteta(med);

}
int Pharmacy::addRandomToReteta(int howMany) {
	RetetaCurenta.addRandomMeds(this->getAll(), howMany);
	return RetetaCurenta.getAllFromReteta().size();
}
void Pharmacy::emptyReteta() {
	RetetaCurenta.emptyReteta();
}
const vector<Meds>& Pharmacy::getMedsFromReteta() {
	return RetetaCurenta.getAllFromReteta();
}

void Pharmacy::empty() {
	rep.empty();
	undoActions.clear();
}

unordered_map<string, Raport> Pharmacy::getProducer(string pr) {
	int crt = 0;
	unordered_map<string, Raport> set;
	for (const Meds& m : this->getAll()) {
		if (m.getProducer() == pr) {
			crt++;
		}
	}
	set[pr] = Raport(pr, crt);
	return set;
}

void Pharmacy::exporta(string file) {
	ofstream out(file);
	if (!out.is_open()) {
		throw MedRepoException("Fisierul nu se poate deschide!!");
	}
	out << "<!DOCTYPE html>\n";
	out << "<html>\n";
	out << "<body>\n";
	out << "<h1> Reteta de medicamente:\n";
	out << "</h1\n";
	for (auto& m : getMedsFromReteta()) {
		out << "<p>";
		out << m.getName();
		out << " ";
		out << m.getProducer();
		out << " ";
		out << m.getActSubst();
		out << " ";
		out << m.getPrice();
		out << "\n";
		out << "</p>\n";
	}
	out << "</body>\n";
	out << "</html>\n";

}