#include "medicament.h"


bool cmpName(const medicament& m1, const medicament& m2) {
	return m1.getName() < m2.getName();
}

bool cmpProducer(const medicament& m1, const medicament& m2) {
	return m1.getProducer() < m2.getProducer();
}


bool cmpActSubst(const medicament& m1, const medicament& m2) {
	return m1.getActSubst() < m2.getActSubst();
}


bool cmpPrice(const medicament& m1, const medicament& m2) {
	return m1.getPrice() < m2.getPrice();

}
