#include "consola.h"

#include <iostream>
#include <string>

using std::cout;
using std::cin;
using std::string;

void ConsolUI::tipareste(const vector<Meds>& meds) {
	cout << "Medicamentele:\n";
	for (const auto& med : meds) {
		cout << ' ' << med.getName() << ' ' << med.getProducer() << ' ' << med.getActSubst() << ' ' << med.getPrice() << '\n';
	}
	cout << "Sfarsit lista medicamente\n";
}

void ConsolUI::cautaUI() {
	string name, producer, actsubst;
	int price;
	cout << "Dati denumirea: ";
	cin >> name;
	const auto& med=srv.findMed(name);
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

void ConsolUI::meniu() {
	cout << "Meniu:\n";
	cout << "1 adauga\n";
	cout << "2 tipareste\n";
	cout << "3 sortare dupa denumire\n";
	cout << "4 sortare dupa producator\n";
	cout << "5 sortare dupa substanta activa si pret\n";
	cout << "6 filtrare pret\n";
	cout << "7 filtrare dupa substanta activa\n";
	cout << "8 filtrare pret min max\n";
	cout << "9 cauta dupa denumire\n";
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
			case 3:
				tipareste(srv.sortByName());
				break;
			case 4:
				tipareste(srv.sortByProducer());
				break;
			case 5:
				tipareste(srv.sortByActSubstPrice());
				break;
			case 6:
				cout << "Dati pret maxim:";
				int pret;
				cin >> pret;
				tipareste(srv.filtrarePretMaiMic(pret));
				break;
			case 7:
				cout << "Dati substanta activa:";
				cin >> subst;
				tipareste(srv.filtrareSubstActv(subst));
				break;
			case 8:
				cout << "Dati pret minim:";
				int pretMin;
				cin >> pretMin;
				cout << "Dati pret maxim:";
				int pretMax;
				cin >> pretMax;
				tipareste(srv.filtrarePret(pretMin, pretMax));
				break;
			case 9:
				cautaUI();
				break;
			case 0:
				return;
			default:
				cout << "Comanda invalida\n";
			}
		}
		catch (const MedRepoException& ex) {
			cout << ex << '\n';
		}
		catch (const ValidateException& ex) {
			cout << ex << '\n';
		}
	}
}