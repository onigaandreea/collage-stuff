#pragma once

#include <string>
#include <iostream>

using std::string;
using std::cout;

class medicament
{std::string name;
	std::string producer;
	std::string active_subst;
	int price;
public:

	medicament() = default;
	medicament(const string n, const string p, const string a, int pr) :name{ n }, producer{ p }, active_subst{ a }, price{ pr }{}

	/*Meds(const Meds& md) :name{md.name}, producer{md.producer}, active_subst{md.active_subst}, price{md.price} {
		cout << "!!!!!!!!!!!!!!!!!!!!\n";
	}*/


	string getName() const {
		return name;
	}
	string getProducer() const {
		return producer;
	}

	string getActSubst() const {
		return active_subst;
	}
	int getPrice() const noexcept {
		return price;
	}

	void setName(string new_value) {
		this->name = new_value;
	}

};

/*
Compara dupa denumire
returneaza true daca m1.name e mai mic decat m2.name
*/
bool cmpName(const medicament& m1, const medicament& m2);

/*
Compara dupa producator
returneaza true daca m1.producer e mai mic decat m2.producer
*/
bool cmpProducer(const medicament& m1, const medicament& m2);

/*
Compara dupa substanta activa
returneaza true daca m1.active_subst e mai mic decat m2.active_subst
*/
bool cmpActSubst(const medicament& m1, const medicament& m2);

/*
Compara dupa pret
returneaza true daca m1.price e mai mic decat m2.price
*/
bool cmpPrice(const medicament& m1, const medicament& m2);


