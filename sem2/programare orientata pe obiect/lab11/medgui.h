#pragma once
#include <qwidget.h>
#include <qlistwidget.h>
#include <qpushbutton.h>
#include <qlineedit.h>
#include <qboxlayout.h>
#include <qdebug.h>
#include <qtablewidget.h>
#include "service.h"
#include "med.h"
#include <vector>
#include <qlabel.h>
//class SmallGUI : public QWidget {
//private:
//	QLabel* lbl = new QLabel("Nimic");
//public:
//	SmallGUI() {
//		QHBoxLayout* ly = new QHBoxLayout;
//		setLayout(ly);
//		ly->addWidget(lbl);
//	}
//
//	void setPet(const Meds& p) {
//		lbl->setText(QString::fromStdString(p.getName()));
//	}
//};


class PharmacyGUI : public QWidget {
private:
	Pharmacy& ctr;
	//avem layout orizontal pentru fereastra principala
	QHBoxLayout* lyMain = new QHBoxLayout;
	//alegem un un layout vertical pentru form si butoanele de gestiune a listei
	QVBoxLayout* lyBtnDy = new QVBoxLayout;

	QWidget* btnDyn = new QWidget;
	QListWidget* lst;
	
	QPushButton* btnSortByName;
	QPushButton* btnSortByProducer;
	QPushButton* btnSortBySubstPrice;
	QPushButton* btnFilterPrice;
	QPushButton* btnFilterSubst;
	QPushButton* btnAdd;
	QPushButton* btnshowAll;
	QPushButton* btnDelete;
	QPushButton* btnUpdate;
	QPushButton* btnSearch;
	QPushButton* btnUndo;
	QPushButton* btnAddtoReteta;
	QPushButton* btnAddRandom;
	QPushButton* btnEmpty;
	QPushButton* btnExport;

	QLineEdit* txtName;
	QLineEdit* txtProducer;
	QLineEdit* txtSubst;
	QLineEdit* txtPrice;
	QLineEdit* txtNewName;
	QLineEdit* txtNewProducer;
	QLineEdit* txtNewSubst;
	QLineEdit* txtNewPrice;
	QLineEdit* txtFile;
	QLineEdit* txtRandom;

	QLineEdit* FilterPrice;
	QLineEdit* FilterSubst;

	QLabel* lblFilterCriteria = new QLabel{ "Pret dupa care se filtreaza:" };
	QLabel* lblFilterCriteria1 = new QLabel{ "Substanta activa dupa care se filtreaza:" };

	QTableWidget* tableMeds;

	void initGUICmps();
	void connectSignalsSlots();
	void reloadList(const std::vector<Meds>& pets);
	void addNewMed();
	void deleteMed();
	void updateMed();
	void searchMed();
	void filtreazaPret();
	void filtreazaSubst();
	void undo();
	void addToReteta();
	void addRandom();
	void empty();
	void exportReteta();
	void reloadReteta(const std::vector<Meds> meds);
	void adaugaButoane(const std::vector<Meds>& meds);

public:
	PharmacyGUI(Pharmacy& ctr) :ctr{ ctr } {
		initGUICmps();
		connectSignalsSlots();
		reloadList(ctr.getAll());
		adaugaButoane(ctr.getAll());
	}
};