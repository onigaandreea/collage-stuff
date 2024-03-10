#include "consola.h"
#include <iostream>
#include <string>
#include <fstream>

using std::cout;
using std::cin;
using std::string;
using std::ofstream;

void ConsolUI::tipareste(const vector<Meds>& meds) {
	cout << "Medicamentele:\n";
	for (const auto& med : meds) {
		cout << ' ' << med.getName() << ' ' << med.getProducer() << ' ' << med.getActSubst() << ' ' << med.getPrice() << '\n';
	}
	cout << "Sfarsit lista medicamente\n";
}

void ConsolUI::cautaUI() {
	string name;
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
	Meds m{ name, producer,actsubst,price };
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
	srv.updateMed(m, newname, newproducer, newactsubst, newprice);
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
	Meds m{ name, producer,actsubst,price };
	srv.deleteMed(m);
	cout << "Sters cu succes\n";
}

void ConsolUI::adaugaRetetaUi() {
	string name;
	cout << "Dati denumirea: ";
	cin >> name;
	srv.addToReteta(name);
	cout << "adugat cu succes\n";
}

void ConsolUI::adaugaRandomRetetaUi() {
	int nr;
	cout << "Dati numarul: ";
	cin >> nr;
	srv.addRandomToReteta(nr);
	cout << "adugate cu succes\n";
}

void ConsolUI::golesteRetetaUi() {
	srv.emptyReteta();
}

void ConsolUI::exporta() {
	string file;
	cout << "Dati numele fisierului: ";
	cin >> file;
	file = file + ".html";
	srv.exporta(file);
}

void ConsolUI::raportUi() {
	cout << "Dati producatorul: ";
	string pr;
	cin >> pr;
	unordered_map<string, Raport> rez = srv.getProducer(pr);
	cout << rez[pr].getNumar() << "\n";
}

void ConsolUI::meniu() {
	cout << "Meniu:\n";
	cout << "1 adauga\n";
	cout << "2 sterge\n";
	cout << "3 modifica\n";
	cout << "4 cauta dupa denumire\n";
	cout << "5 tipareste\n";
	cout << "6 sortare dupa denumire\n";
	cout << "7 sortare dupa producator\n";
	cout << "8 sortare dupa substanta activa si pret\n";
	cout << "9 filtrare pret\n";
	cout << "10 filtrare dupa substanta activa\n";
	cout << "11 filtrare pret min max\n";
	cout << "12 adauga in reteta\n";
	cout << "13 adauga un numar random de medicamente in reteta\n";
	cout << "14 goleste reteta\n";
	cout << "15 afisare reteta\n";
	cout << "16 export reteta\n";
	cout << "17 undo\n";

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
				stergeUI();
				break;
			case 3:
				modificaUI();
				break;
			case 4:
				cautaUI();
				break;
			case 5:
				tipareste(srv.getAll());
				break;
			case 6:
				tipareste(srv.sortByName());
				break;
			case 7:
				tipareste(srv.sortByProducer());
				break;
			case 8:
				tipareste(srv.sortByActSubstPrice());
				break;
			case 9:
				cout << "Dati pret maxim:";
				int pret;
				cin >> pret;
				tipareste(srv.filtrarePretMaiMic(pret));
				break;
			case 10:
				cout << "Dati substanta activa:";
				cin >> subst;
				tipareste(srv.filtrareSubstActv(subst));
				break;
			case 11:
				cout << "Dati pret minim:";
				int pretMin;
				cin >> pretMin;
				cout << "Dati pret maxim:";
				int pretMax;
				cin >> pretMax;
				tipareste(srv.filtrarePret(pretMin, pretMax));
				break;
			case 12:
				adaugaRetetaUi();
				tipareste(srv.getMedsFromReteta());
				break;
			case 13:
				adaugaRandomRetetaUi();
				tipareste(srv.getMedsFromReteta());
				break;
			case 14:
				golesteRetetaUi();
				break;
			case 15:
				tipareste(srv.getMedsFromReteta());
				break;
			case 16:
				exporta();
				break;
			case 17:
				srv.undo();
				break;
			case 0:
				srv.empty();
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