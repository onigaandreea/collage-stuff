#pragma once
#include <iostream>
#include <string>

using std::string;
using std::cout;
using std::endl;

class Meds
{
	string name;
	string producer;
	string active_subst;
	int price;
public:
	Meds() = delete;
	Meds(string n, string p, string a, int pr) :name{ n }, producer{ p }, active_subst{ a }, price{ pr }{}

	Meds(const Meds& md) :name{ md.name }, producer{ md.producer }, active_subst{ md.active_subst }, price{ md.price } {
		//cout << "!!!!!!!!!!!!!!!!!!!!\n";
	}

	Meds& operator=(const Meds& ot) {
		this->name = ot.name;
		this->producer = ot.producer;
		this->active_subst = ot.active_subst;
		this->price = ot.price;
		return (*this);
	};

	string getName() const;
	string getProducer() const;
	string getActSubst() const;
	int getPrice() const;

	void setName(string newname);
	void setProducer(string newproducer);
	void setActSubst(string newactsubst);
	void setPrice(int newprice);

};

/*
Compara dupa denumire
returneaza true daca m1.name e mai mic decat m2.name
*/
bool cmpName(const Meds& m1, const Meds& m2);

/*
Compara dupa producator
returneaza true daca m1.producer e mai mic decat m2.producer
*/
bool cmpProducer(const Meds& m1, const Meds& m2);

/*
Compara dupa substanta activa
returneaza true daca m1.active_subst e mai mic decat m2.active_subst
*/
bool cmpActSubstPrice(const Meds& m1, const Meds& m2);

