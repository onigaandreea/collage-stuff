#include "console.h"


#include <iostream>
#include <string>

using std::cout;
using std::cin;
using std::string;

void ConsolUI::tipareste(const VectDinNewDeleteT<medicament> meds) {
	cout << "Medicamentele:\n";
	for (int i = 0; i < meds.size(); i++) {
		cout << ' ' << meds.get(i).getName() << ' ' << meds.get(i).getProducer() << ' ' << meds.get(i).getActSubst() << ' ' << meds.get(i).getPrice() << '\n';
	}
	cout << "Sfarsit lista medicamente\n";
}

void ConsolUI::cautaUI() {
	string name, producer, actsubst;
	int price;
	cout << "Dati denumirea: ";
	cin >> name;
	const auto& med = srv.findMed(name);
	cout << ' ' << med.getName() << ' ' << med.getProducer() << ' ' << med.getActSubst() << ' ' << med.getPrice() << '\n';
}


void ConsolUI::adaugaUI() {
	string name, producer, actsubst;
	int price;
	cout << "Dati denumirea: ";
	cin >> name;
	cout << "Dati producatorul: ";
	cin >> producer;
	cout << "Dati substanta activa: ";
	cin >> actsubst;
	cout << "Dati pret:";
	cin >> price;
	srv.addMed(name, producer, actsubst, price);
	cout << "Adaugat cu succes\n";
}

void ConsolUI::modificaUI() {
	string name, producer, actsubst;
	int price;
	cout << "Dati denumirea: ";
	cin >> name;
	cout << "Dati producatorul: ";
	cin >> producer;
	cout << "Dati substanta activa: ";
	cin >> actsubst;
	cout << "Dati pret:";
	cin >> price;
	medicament m1 = medicament{ name, producer, actsubst, price };
	string newname, newproducer, newactsubst;
	int newprice;
	cout << "Dati noua denumirea: ";
	cin >> newname;
	cout << "Dati noul producatorul: ";
	cin >> newproducer;
	cout << "Dati noua substanta activa: ";
	cin >> newactsubst;
	cout << "Dati noul pret:";
	cin >> newprice;
	medicament m2 = { newname, newproducer, newactsubst, newprice };
	srv.modifyMed(m1, m2);
	cout << "Modificat cu succes\n";
}

void ConsolUI::stergeUI() {
	string name, producer, actsubst;
	int price;
	cout << "Dati denumirea: ";
	cin >> name;
	cout << "Dati producatorul: ";
	cin >> producer;
	cout << "Dati substanta activa: ";
	cin >> actsubst;
	cout << "Dati pret:";
	cin >> price;
	srv.deleteMed(name, producer, actsubst, price);
	cout << "Sters cu succes\n";
}


void ConsolUI::meniu() {
	cout << "Meniu:\n";
	cout << "1 adauga\n";
	cout << "2 tipareste\n";
	cout << "3 sterge\n";
	cout << "4 modifica\n";
	cout << "5 sortare dupa denumire\n";
	cout << "6 sortare dupa producator\n";
	cout << "7 sortare dupa substanta activa si pret\n";
	cout << "8 filtrare pret\n";
	cout << "9 filtrare dupa substanta activa\n";
	cout << "10 filtrare pret min max\n";
	cout << "11 cauta dupa denumire\n";
	cout << "0 Iesire\n";
	cout << "Dati comanda : ";

}

void ConsolUI::start() {
	while (true) {
		meniu();
		int cmd;
		string subst;
		cin >> cmd;
		try {
			switch (cmd) {
			case 1:
				adaugaUI();
				break;
			case 2:
				tipareste(srv.getAll());
				break;
			case 5:
				tipareste(srv.sortByName());
				break;
			case 6:
				tipareste(srv.sortByProducer());
				break;
			case 7:
				tipareste(srv.sortByActSubstPrice());
				break;
			case 8:
				cout << "Dati pret maxim:";
				int pret;
				cin >> pret;
				tipareste(srv.filtrarePretMaiMic(pret));
				break;
			case 9:
				cout << "Dati substanta activa:";
				cin >> subst;
				tipareste(srv.filtrareSubstActv(subst));
				break;
			case 10:
				cout << "Dati pret minim:";
				int pretMin;
				cin >> pretMin;
				cout << "Dati pret maxim:";
				int pretMax;
				cin >> pretMax;
				tipareste(srv.filtrarePret(pretMin, pretMax));
				break;
			case 3:
				stergeUI();
				break;
			case 4:
				modificaUI();
				break;
			case 11:
				cautaUI();
				break;
			case 0:
				return;
			default:
				cout << "Comanda invalida\n";
			}

		}
		catch (const ValidateException& ex) {
			cout << ex << '\n';
		}
	}
}
