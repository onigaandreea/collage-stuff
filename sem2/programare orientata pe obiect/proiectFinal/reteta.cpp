#include "reteta.h"

#include <assert.h>
using std::shuffle;

void Reteta::addMedToReteta(const Meds& m) {
	this->retetaMed.push_back(m);
	notify();
}
void Reteta::emptyReteta() {
	this->retetaMed.clear();
	notify();
}

void Reteta::addRandomMeds(int howMany) {
	vector<Meds> originalMeds = repo.getAll();
	shuffle(originalMeds.begin(), originalMeds.end(), std::default_random_engine(std::random_device{}())); //amesteca vectorul v
	int lg = retetaMed.size();
	while (retetaMed.size() < (howMany+lg) && originalMeds.size() > 0) {
		retetaMed.push_back(originalMeds.back());
		originalMeds.pop_back();
	}
	notify();
}
const vector<Meds>& Reteta::getAllFromReteta() {
	return this->retetaMed;
}

void testReteta() {
	MedRepo repo;
	Reteta ret(repo);
	assert(ret.getAllFromReteta().size() == 0);
	Meds m1{ "a","a","a",6 };
	ret.addMedToReteta(m1);
	assert(ret.getAllFromReteta().size() == 1);

	Meds m2{ "b","b","b",10 };
	ret.addMedToReteta(m2);
	assert(ret.getAllFromReteta().size() == 2);
	Meds m3{ "c","bc","eb",17 };
	Meds m4{ "d","bb","gb",13 };
	Meds m5{ "e","ba","jb",20 };
	ret.addMedToReteta(m3);
	ret.addMedToReteta(m4);
	ret.addMedToReteta(m5);

	MedRepo reporandom;
	Reteta ret_random(reporandom);
	ret_random.addRandomMeds(3);
	assert(ret_random.getAllFromReteta().size() == 3);

	ret_random.emptyReteta();
	assert(ret_random.getAllFromReteta().size() == 0);
	ret.emptyReteta();
	assert(ret.getAllFromReteta().size() == 0);

}
