#include "med.h"

string Meds::getName() const {
	return this->name;
}

string Meds::getProducer() const {
	return this->producer;
}

string Meds::getActSubst() const {
	return this->active_subst;
}

int Meds::getPrice() const {
	return this->price;
}

void Meds::setName(string newname) {
	this->name = newname;
}

void Meds::setProducer(string newproducer) {
	this->producer = newproducer;
}

void Meds::setActSubst(string newsubst) {
	this->active_subst = newsubst;
}

void Meds::setPrice(int newprice) {
	this->price = newprice;
}

bool cmpName(const Meds& m1, const Meds& m2) {
	return m1.getName() < m2.getName();
}

bool cmpProducer(const Meds& m1, const Meds& m2) {
	return m1.getProducer() < m2.getProducer();
}


bool cmpActSubstPrice(const Meds& m1, const Meds& m2) {
	if (m1.getActSubst() == m2.getActSubst()) {
		return m1.getPrice() < m2.getPrice();
	}
	else
		return m1.getActSubst() < m2.getActSubst();
}
