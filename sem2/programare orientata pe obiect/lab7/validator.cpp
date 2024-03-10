#include "validator.h"
#include <sstream>
#include <assert.h>

void MedValidator::validate(const Meds& m) {
	vector<string> msgs;
	if (m.getPrice() < 0) msgs.push_back("Pret negativ!!!");
	if (m.getName().size() == 0) msgs.push_back("Denumire vid!!!");
	if (m.getProducer().size() == 0) msgs.push_back("Producator vid!!!");
	if (m.getActSubst().size() == 0)msgs.push_back("Substanta activa vida!!!");
	if (msgs.size() > 0) {
		throw ValidateException(msgs);
	}
}

ostream& operator<<(ostream& out, const ValidateException& ex) {
	for (const auto& msg : ex.msgs) {
		out << msg << " ";
	}
	return out;
}

void testValidator() {
	MedValidator v;
	Meds m{ "","","", -1 };
	try {
		v.validate(m);
	}
	catch (const ValidateException& ex) {
		std::stringstream sout;
		sout << ex;
		auto mesaj = sout.str();
		assert(mesaj.find("negativ") >= 0);
		assert(mesaj.find("vid") >= 0);
		assert(mesaj.find("vida") >= 0);
	}

}