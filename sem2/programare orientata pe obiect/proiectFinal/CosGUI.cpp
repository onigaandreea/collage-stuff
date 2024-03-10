#include "cosGUI.h"

void RetetaGUI::initGUICmps() {
	lyMain = new QHBoxLayout;
	setLayout(lyMain);

	//adaug lista si sub trei butoane de sort si sub inca doua butoane de filtrare
	QWidget* widDreapta = new QWidget;
	QVBoxLayout* vl = new QVBoxLayout;
	widDreapta->setLayout(vl);

	lst = new QListWidget;
	vl->addWidget(lst);

	QWidget* widStanga = new QWidget;
	QVBoxLayout* lySt = new QVBoxLayout;
	widStanga->setLayout(lySt);

	QWidget* form = new QWidget;
	QFormLayout* formly = new QFormLayout;
	form->setLayout(formly);

	txtName= new QLineEdit;
	formly->addRow(new QLabel("Name:"), txtName);

	lySt->addWidget(form);

	btnAdd = new QPushButton("Add a med to prescription");
	lySt->addWidget(btnAdd);

	btnAddRandom = new QPushButton("Add random to prescription");
	lySt->addWidget(btnAddRandom);

	btnEmpty = new QPushButton("Empty the prescription");
	lySt->addWidget(btnEmpty);

	btnExport = new QPushButton("Export");
	lySt->addWidget(btnExport);

	btnListWindow = new QPushButton("Cos CRUD");
	btnListWindow->setStyleSheet("background - color: magenta");
	lySt->addWidget(btnListWindow);

	btnDrawWindow = new QPushButton("Reteta desene");
	btnDrawWindow->setStyleSheet("background - color: blue");
	lySt->addWidget(btnDrawWindow);

	lyMain->addWidget(widDreapta);
	lyMain->addWidget(widStanga);
}

void RetetaGUI::connectSignalsSlots() {
	srv.getReteta().addObserver(this);
	QObject::connect(btnAdd, &QPushButton::clicked, this, &RetetaGUI::addToReteta);

	QObject::connect(btnAddRandom, &QPushButton::clicked, this, &RetetaGUI::addRandom);

	QObject::connect(btnEmpty, &QPushButton::clicked, this, &RetetaGUI::empty);

	QObject::connect(btnExport, &QPushButton::clicked, this, &RetetaGUI::exportReteta);

	QObject::connect(btnListWindow, &QPushButton::clicked, this, [&]() {
		auto listWindow = new CosCRUDGUI { srv.getReteta()};
		listWindow->show();
		});

	QObject::connect(btnDrawWindow, &QPushButton::clicked, this, [&]() {
		auto drawWindow = new CosGUIDraw{ srv.getReteta() };
		drawWindow->show();
		});
}

void RetetaGUI::reloadReteta() {
	lst->clear();
	 const vector<Meds>& meds = srv.getMedsFromReteta();
	for (const auto& m : meds) {
		QListWidgetItem* item = new QListWidgetItem(QString::fromStdString(m.getName() + " | " + m.getProducer() + " | " + m.getActSubst() + " | " + std::to_string(m.getPrice()) + "\n"));
		lst->addItem(item);
	}
}


void RetetaGUI::addToReteta() {
	srv.addToReteta(txtName->text().toStdString());
	reloadReteta();
}

void RetetaGUI::addRandom() {
	QWidget* widget = new QWidget;

	QFormLayout* form = new QFormLayout;
	widget->setLayout(form);

	txtRandom = new QLineEdit;
	form->addRow(new QLabel("A number for generating random meds:"), txtRandom);

	QPushButton* btnGenerate;
	btnGenerate = new QPushButton("Generate");
	form->addWidget(btnGenerate);

	widget->show();
	connect(btnGenerate, &QPushButton::clicked, [=] {
		srv.addRandomToReteta(txtRandom->text().toInt());
		reloadReteta();
		});
}

void RetetaGUI::empty() {
	srv.emptyReteta();
	reloadReteta();
}

void RetetaGUI::exportReteta() {
	QWidget* widget = new QWidget;

	QFormLayout* form = new QFormLayout;
	widget->setLayout(form);

	txtFile = new QLineEdit;
	form->addRow(new QLabel("Enter the name of the html file for export:"), txtFile);

	QPushButton* btnsubmit = new QPushButton("Submit");
	form->addWidget(btnsubmit);

	widget->show();
	connect(btnsubmit, &QPushButton::clicked, [=] {
		srv.exporta(txtFile->text().toStdString());
		});
}