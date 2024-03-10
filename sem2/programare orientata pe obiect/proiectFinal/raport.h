#pragma once
#include "service.h"
#include <string>
#include <utility>

using std::string;

class Raport {
	string producator;
	int nr;

public:
	Raport() = default;

	Raport(string pr, int nr) :producator{ pr }, nr{ nr }{}

	Raport(const Raport& ot) :producator{ ot.producator }, nr{ ot.nr }{
		//cout << "!!!\n";
	}

	string getProducer() const {
		return this->producator;
	}

	int getNumar() {
		return this->nr;
	}
};
