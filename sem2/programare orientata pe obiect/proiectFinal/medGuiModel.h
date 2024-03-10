#pragma once
#include <qpainter.h>
#include <qformlayout.h>
#include <qwidget.h>
#include <qtimer.h>
#include <QtWidgets/QHBoxLayout>
#include <qpushbutton.h>
#include <qlistwidget.h>
#include <qtablewidget.h>
#include <qstring.h>
#include <qlabel.h>
#include <qlineedit.h>
#include <vector>
#include "reteta.h"
#include "observer.h"
#include "service.h"
#include "MyTableModel.h"
#include "cosGUI.h"

class PharmacyGUIModel : public QWidget {
private:
	Pharmacy& ctr;
	RetetaGUI retetaWindow{ ctr };
	MyTableModel* model;

	QLabel* lblCate = new QLabel{ "Cate:" };

	//avem layout orizontal pentru fereastra principala
	QHBoxLayout* lyMain = new QHBoxLayout;
	//alegem un un layout vertical pentru form si butoanele de gestiune a listei
	QVBoxLayout* lyBtnDy = new QVBoxLayout;

	QWidget* btnDyn = new QWidget;

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
	QPushButton* btnReteta;

	QLineEdit* txtName;
	QLineEdit* txtProducer;
	QLineEdit* txtSubst;
	QLineEdit* txtPrice;
	QLineEdit* txtNewName;
	QLineEdit* txtNewProducer;
	QLineEdit* txtNewSubst;
	QLineEdit* txtNewPrice;

	QLineEdit* FilterPrice;
	QLineEdit* FilterSubst;

	QLabel* lblFilterCriteria = new QLabel{ "Pret dupa care se filtreaza:" };
	QLabel* lblFilterCriteria1 = new QLabel{ "Substanta activa dupa care se filtreaza:" };

	QTableView* tableMeds =  new QTableView;

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

	void adaugaButoane(const std::vector<Meds>& meds);

public:
	PharmacyGUIModel(Pharmacy& ctr) :ctr{ ctr } {
		initGUICmps();
		this->model = new MyTableModel{ ctr.getAll() };
		this->tableMeds->setModel(model);
		connectSignalsSlots();
		reloadList(ctr.getAll());
		adaugaButoane(ctr.getAll());
	}
};
