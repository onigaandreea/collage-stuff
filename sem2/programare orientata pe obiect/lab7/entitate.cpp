#include "entitate.h"

bool cmpName(const Meds& m1, const Meds& m2) {
	return m1.getName() < m2.getName();
}

bool cmpProducer(const Meds& m1, const Meds& m2) {
	return m1.getProducer() < m2.getProducer();
}


bool cmpActSubst(const Meds& m1, const Meds& m2) {
	return m1.getActSubst() < m2.getActSubst();
}


bool cmpPrice(const Meds& m1, const Meds& m2) {
	return m1.getPrice() < m2.getPrice();

}