#pragma once
#include "med.h"
#include <vector>

using std::vector;
using std::string;
using std::ostream;
using std::stringstream;

class ValidateException {
	vector<string> msgs;
public:
	ValidateException(const vector<string>& errors) :msgs{ errors } {}
	//functie friend (vreau sa folosesc membru privat msg)
	friend ostream& operator<<(ostream& out, const ValidateException& ex);
	string getErrorMessages() {
		string fullMsg = "";
		for (const string e : msgs) {
			fullMsg += e + "\n";
		}
		return fullMsg;
	}
};

ostream& operator<<(ostream& out, const ValidateException& ex);

class MedValidator {
public:
	void validate(const Meds& m);
};

