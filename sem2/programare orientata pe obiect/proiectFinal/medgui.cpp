#include "medgui.h"
#include "validator.h"
#include "med.h"
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QFormLayout>
#include <QtWidgets/QLabel>
#include <QtWidgets/QMessageBox>
#include <QtWidgets/QTableWidget>
#include <QtWidgets/QHeaderView>
#include <qstring.h>
#include <qpalette.h>
#include <qbrush.h>
#include <qdebug.h>
#include <string>
#include <algorithm>

void PharmacyGUI::initGUICmps() {

	setLayout(lyMain);

	//adaug lista si sub trei butoane de sort si sub inca doua butoane de filtrare
	QWidget* widDreapta = new QWidget;
	QVBoxLayout* vl = new QVBoxLayout;

	widDreapta->setLayout(vl);
	int noLines = 10;
	int noColumns = 4;
	this->tableMeds = new QTableWidget{ noLines, noColumns };

	//setam header-ul tabelului
	QStringList tblHeaderList;
	tblHeaderList << "Denumire" << "Producator" << "Substanta activa" << "Pret";
	this->tableMeds->setHorizontalHeaderLabels(tblHeaderList);

	//optiune pentru a se redimensiona celulele din tabel in functie de continut
	this->tableMeds->horizontalHeader()->setSectionResizeMode(QHeaderView::ResizeToContents);
	//lst = new QListWidget;
	vl->addWidget(tableMeds);

	QWidget* widBtnDreapta = new QWidget;
	QHBoxLayout* lyBtnsDr = new QHBoxLayout;
	widBtnDreapta->setLayout(lyBtnsDr);
	btnSortByName = new QPushButton("Sort by name");
	lyBtnsDr->addWidget(btnSortByName);
	btnSortBySubstPrice = new QPushButton("Sort by active substance and price");
	lyBtnsDr->addWidget(btnSortBySubstPrice);

	btnSortByProducer = new QPushButton("Sort by producer");
	lyBtnsDr->addWidget(btnSortByProducer);
	vl->addWidget(widBtnDreapta);

	QWidget* widFilter = new QWidget;
	QHBoxLayout* lyBtnsFl = new QHBoxLayout;
	widFilter->setLayout(lyBtnsFl);
	btnFilterPrice = new QPushButton("Filter by price");
	lyBtnsFl->addWidget(btnFilterPrice);
	btnFilterSubst = new QPushButton("Filter by active substance");
	lyBtnsFl->addWidget(btnFilterSubst);

	vl->addWidget(widFilter);
	lyMain->addWidget(widDreapta);


	//fac un form pentru detalii
	QWidget* widDetalii = new QWidget;
	QFormLayout* formLDetalii = new QFormLayout;
	widDetalii->setLayout(formLDetalii);
	txtName = new QLineEdit;
	formLDetalii->addRow(new QLabel("Name:"), txtName);
	txtProducer = new QLineEdit;
	formLDetalii->addRow(new QLabel("Producer:"), txtProducer);
	txtSubst = new QLineEdit;
	formLDetalii->addRow(new QLabel("Active Substance:"), txtSubst);
	txtPrice = new QLineEdit;
	formLDetalii->addRow(new QLabel("Price:"), txtPrice);

	btnAdd = new QPushButton("Add med");
	formLDetalii->addWidget(btnAdd);

	btnDelete = new QPushButton("Delete med");
	formLDetalii->addWidget(btnDelete);

	btnUpdate = new QPushButton("Update med");
	formLDetalii->addWidget(btnUpdate);

	btnSearch = new QPushButton("Search med");
	formLDetalii->addWidget(btnSearch);

	btnshowAll = new QPushButton("Show list of meds");
	formLDetalii->addWidget(btnshowAll);

	btnUndo = new QPushButton("Undo");
	formLDetalii->addWidget(btnUndo);

	btnReteta = new QPushButton("Reteta");
	formLDetalii->addWidget(btnReteta);

	lyMain->addWidget(widDetalii);
	
}

void PharmacyGUI::connectSignalsSlots() {
	//cand se emite semnalul clicked de pe buton reincarc lista
	QObject::connect(btnSortByName, &QPushButton::clicked, [&]() {
		qDebug() << "am ajuns aici!!!";
		reloadList(ctr.sortByName());
		});
	//cand se emite semnalul clicked de pe buton reincarc lista
	QObject::connect(btnSortByProducer, &QPushButton::clicked, [&]() {
		reloadList(ctr.sortByProducer());
		});

	QObject::connect(btnSortBySubstPrice, &QPushButton::clicked, [&]() {
		reloadList(ctr.sortByActSubstPrice());
		});

	QObject::connect(btnAdd, &QPushButton::clicked, this, &PharmacyGUI::addNewMed);

	QObject::connect(btnDelete, &QPushButton::clicked, this, &PharmacyGUI::deleteMed);

	QObject::connect(btnUpdate, &QPushButton::clicked, this, &PharmacyGUI::updateMed);

	QObject::connect(btnSearch, &QPushButton::clicked, this, &PharmacyGUI::searchMed);

	QObject::connect(btnFilterPrice, &QPushButton::clicked, this, &PharmacyGUI::filtreazaPret);

	QObject::connect(btnFilterSubst, &QPushButton::clicked, this, &PharmacyGUI::filtreazaSubst);

	QObject::connect(btnUndo, &QPushButton::clicked, this, &PharmacyGUI::undo);

	QObject::connect(btnshowAll, &QPushButton::clicked, [&]() {
		this->reloadList(ctr.getAll());
		});

	QObject::connect(tableMeds, &QTableWidget::itemClicked, [&]() {
		auto sel = tableMeds->selectedItems();
		if (sel.isEmpty()) {
			txtName->setText("");
			txtProducer->setText("");
			txtSubst->setText("");
			txtPrice->setText("");
		}
		else {
			auto selItem = sel.at(0);
			auto text = selItem->text();
			text.toStdString();
			text = text.split(" ")[0];
			txtName->setText(text);
			auto m = ctr.findMed(text.toStdString());
			txtProducer->setText(QString::fromStdString(m.getProducer()));
			txtSubst->setText(QString::fromStdString(m.getActSubst()));
			txtPrice->setText(QString::number(m.getPrice()));
		}
		});

	QObject::connect(btnReteta, &QPushButton::clicked, [&]() {

		retetaWindow.show();
		});
}

void PharmacyGUI::addNewMed() {
	try {
		ctr.addMed(txtName->text().toStdString(), txtProducer->text().toStdString(), txtSubst->text().toStdString(), txtPrice->text().toDouble());
		reloadList(ctr.getAll());
		QMessageBox::information(this, "Info", QString::fromStdString("Medicament adaugat cu succes!!"));
	}
	catch (MedRepoException& ex) {
		QMessageBox::warning(this, "Warning", QString::fromStdString(ex.getMsg()));
	}
	catch (ValidateException& vex) {
		QMessageBox::warning(this, "Warning", QString::fromStdString(vex.getErrorMessages()));
	}

	txtName->clear();
	txtProducer->clear();
	txtSubst->clear();
	txtPrice->clear();
}

void PharmacyGUI::deleteMed() {
	try {
		ctr.deleteMed(txtName->text().toStdString());
		reloadList(ctr.getAll());
		QMessageBox::information(this, "Info", QString::fromStdString("Medicament sters cu succes!!"));
	}
	catch (MedRepoException& ex) {
		QMessageBox::warning(this, "Warning", QString::fromStdString(ex.getMsg()));
	}
	
}

void PharmacyGUI::updateMed() {
	QWidget* widUpdate = new QWidget;

	QFormLayout* formUpdate = new QFormLayout;
	widUpdate->setLayout(formUpdate);

	txtNewName = new QLineEdit;
	formUpdate->addRow(new QLabel("New name:"), txtNewName);
	txtNewProducer = new QLineEdit;
	formUpdate->addRow(new QLabel("New producer:"), txtNewProducer);
	txtNewSubst = new QLineEdit;
	formUpdate->addRow(new QLabel("New active substance:"), txtNewSubst);
	txtNewPrice = new QLineEdit;
	formUpdate->addRow(new QLabel("New price:"), txtNewPrice);
	QPushButton* btnUpdateNow;
	btnUpdateNow = new QPushButton("Update med");
	formUpdate->addWidget(btnUpdateNow);

	widUpdate->show();

	connect(btnUpdateNow, &QPushButton::clicked, [=]() {
		try {
			ctr.updateMed(txtName->text().toStdString(), txtNewName->text().toStdString(), txtNewProducer->text().toStdString(), txtNewSubst->text().toStdString(), txtNewPrice->text().toInt());
			reloadList(ctr.getAll());
			QMessageBox::information(this, "Info", QString::fromStdString("Medicament modificat cu succes!!"));
		}
		catch (MedRepoException& ex) {
			QMessageBox::warning(this, "Warning", QString::fromStdString(ex.getMsg()));
		}

	});
}

void PharmacyGUI::searchMed() {
	QWidget* formSrc = new QWidget;
	QFormLayout* lyFormSrc = new QFormLayout;
	formSrc->setLayout(lyFormSrc);
	QLineEdit* txtNameSrch;
	txtNameSrch = new QLineEdit;
	lyFormSrc->addRow(new QLabel("Name of med to search:"), txtNameSrch);
	QPushButton* btnSrch;
	btnSrch = new QPushButton("Search med");
	lyFormSrc->addWidget(btnSrch);

	formSrc->show();
	connect(btnSrch, &QPushButton::clicked, [=] {
		try {
			ctr.findMed(txtNameSrch->text().toStdString());
			QMessageBox::information(this, "Info", QString::fromStdString("Medicamentul a fost gasit!!"));
		}
		catch (MedRepoException& ex) {
			QMessageBox::warning(this, "Warning", QString::fromStdString(ex.getMsg()));
		}
		txtNameSrch->clear();
		});
	
}

void PharmacyGUI::filtreazaPret() {
	QWidget* formFilter = new QWidget;
	QFormLayout* lyFormFilter = new QFormLayout;
	formFilter->setLayout(lyFormFilter);
	FilterPrice = new QLineEdit();
	lyFormFilter->addRow(lblFilterCriteria, FilterPrice);
	QPushButton* btnFilterNow;
	btnFilterNow = new QPushButton("Filter by price");
	lyFormFilter->addWidget(btnFilterNow);

	formFilter->show();

	connect(btnFilterNow, &QPushButton::clicked, [&]() {
		QString filterC = this->FilterPrice->text();
		int pretMaiMic = filterC.toInt();
		this->reloadList(ctr.filtrarePretMaiMic(pretMaiMic));
		FilterPrice->clear();
		});
}

void PharmacyGUI::filtreazaSubst() {
	QWidget* formFilter = new QWidget;
	QFormLayout* lyFormFilter = new QFormLayout;
	formFilter->setLayout(lyFormFilter);
	FilterSubst = new QLineEdit();
	lyFormFilter->addRow(lblFilterCriteria1, FilterSubst);
	QPushButton* btnFilterNow;
	btnFilterNow = new QPushButton("Filter by active substance");
	lyFormFilter->addWidget(btnFilterNow);

	formFilter->show();

	connect(btnFilterNow, &QPushButton::clicked, [&]() {
		string filterC = this->FilterSubst->text().toStdString();
		this->reloadList(ctr.filtrareSubstActv(filterC));
		FilterSubst->clear();
		});

}

void PharmacyGUI::undo() {
	try {
		ctr.undo();
		reloadList(ctr.getAll());
		QMessageBox::information(this, "Info", QString::fromStdString("Undo realizat cu succes!!"));
	}
	catch (MedRepoException& ex) {
		QMessageBox::warning(this, "Warning", QString::fromStdString(ex.getMsg()));
	}
}


void PharmacyGUI::adaugaButoane(const std::vector<Meds>& meds) {
	for (const auto& p : meds) {
		if (p.getProducer() == "a") {
			auto btn = new QPushButton{ QString::fromStdString(p.getProducer()) };
			lyBtnDy->addWidget(btn);
			QObject::connect(btn, &QPushButton::clicked, [this, btn, p]() {
				QMessageBox::information(nullptr, "Info", QString::number(p.getPrice()));
				delete btn;
				});
		}
	}
}

void PharmacyGUI::reloadList(const std::vector<Meds>& meds) {
	this->tableMeds->clearContents();
	this->tableMeds->setRowCount(meds.size());

	int lineNumber = 0;
	for (auto& med : meds) {
		this->tableMeds->setItem(lineNumber, 0, new QTableWidgetItem(QString::fromStdString(med.getName())));
		this->tableMeds->setItem(lineNumber, 1, new QTableWidgetItem(QString::fromStdString(med.getProducer())));
		this->tableMeds->setItem(lineNumber, 2, new QTableWidgetItem(QString::fromStdString(med.getActSubst())));
		this->tableMeds->setItem(lineNumber, 3, new QTableWidgetItem(QString::number(med.getPrice())));
		lineNumber++;
	}
	txtName->clear();
	txtProducer->clear();
	txtSubst->clear();
	txtPrice->clear();
}
