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

class RetetaGUI :public QWidget, public Observer {
private:
	Pharmacy& srv;
	QHBoxLayout* lyMain;

	QListWidget* lst;

	QPushButton* btnAdd;
	QPushButton* btnAddRandom;
	QPushButton* btnEmpty;
	QPushButton* btnExport;
	QPushButton* btnListWindow;
	QPushButton* btnDrawWindow;

	QLineEdit* txtName;
	QLineEdit* txtFile;
	QLineEdit* txtRandom;

	void initGUICmps();
	void connectSignalsSlots();
	void addToReteta();
	void addRandom();
	void empty();
	void exportReteta();
	void reloadReteta();
	

public:
	RetetaGUI(Pharmacy& ctr) : srv{ ctr } {
		initGUICmps();
		connectSignalsSlots();
	}
	void update() override {
		reloadReteta();
	}
};

class CosCRUDGUI : public QWidget, public Observer {
private:
	Reteta& cos;
	QListWidget* lst;
	QPushButton* btnclear;
	QPushButton* btnRandom;
	void loadList(const std::vector<Meds>& meds) {
		lst->clear();
		for (auto& p : meds) {
			lst->addItem(QString::fromStdString(p.getName() + " | " + p.getProducer()));
		}
	}
	void initGUI() {
		QHBoxLayout* ly = new QHBoxLayout;
		lst = new QListWidget;
		ly->addWidget(lst);
		btnclear = new QPushButton("Clear cos");
		ly->addWidget(btnclear);

		btnRandom = new QPushButton("Add random 3");
		ly->addWidget(btnRandom);
		setLayout(ly);
		setAttribute(Qt::WA_DeleteOnClose);
		cos.addObserver(this);
	}
	void connectSignals() {
		cos.addObserver(this);
		QObject::connect(btnclear, &QPushButton::clicked, [&]() {
			cos.emptyReteta();
			loadList(cos.getAllFromReteta());
			});
		QObject::connect(btnRandom, &QPushButton::clicked, [&]() {
			cos.addRandomMeds(3);
			loadList(cos.getAllFromReteta());
			});
	}
public:
	CosCRUDGUI(Reteta& cos) :cos{ cos } {
		initGUI();
		connectSignals();
		loadList(cos.getAllFromReteta());
	}

	void update() override {
		loadList(cos.getAllFromReteta());
	}
	~CosCRUDGUI() {
		cos.removeObserver(this);
	}

};


class CosGUIDraw :public QWidget, public Observer {
private:
	Reteta& cos;
	void paintEvent(QPaintEvent*) override {
		QPainter p{ this };
		int x = 0;
		int y = 0;
		for (auto med : cos.getAllFromReteta()) {
			x = rand() % 400 + 1;
			y = rand() % 400 + 1;
			qDebug() << x << " " << y;
			QRectF target(x, y, 100, 94);
			QRectF source(0, 0, 732, 720);
			QImage image("med.jpg");

			p.drawImage(target, image, source);

			x += 40;

		}
	}


public:
	CosGUIDraw(Reteta& cos) :cos{ cos } {
		cos.addObserver(this);
	};

	void update() override {
		repaint();
	};
	~CosGUIDraw() {
		cos.removeObserver(this);
	}

};


