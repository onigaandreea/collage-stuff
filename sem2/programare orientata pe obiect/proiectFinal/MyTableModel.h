#pragma once
#include <QAbstractTableModel>
#include "med.h"
#include <vector>
#include <qdebug.h>

class MyTableModel :public QAbstractTableModel {
	std::vector<Meds> meds;
public:
	MyTableModel(const std::vector<Meds>& meds) :meds{ meds } {
	}

	int rowCount(const QModelIndex& parent = QModelIndex()) const override {
		return meds.size();
	}
	int columnCount(const QModelIndex& parent = QModelIndex()) const override {
		return 4;
	}

	QVariant data(const QModelIndex& index, int role = Qt::DisplayRole) const override {
		qDebug() << "row:" << index.row() << " col:" << index.column() << " role" << role;
		/*if (role == Qt::ForegroundRole) {
			Meds m = meds[index.row()];
			if (m.getPrice() > 0) {
				return QBrush{ Qt::red };
			}
		}*/
		if (role == Qt::DisplayRole) {

			Meds m = meds[index.row()];
			if (index.column() == 0) {
				return QString::fromStdString(m.getName());
			}
			else if (index.column() == 1) {
				return QString::fromStdString(m.getProducer());
			}
			else if (index.column() == 2) {
				return QString::fromStdString(m.getActSubst());
			}
			else if (index.column() == 3) {
				return QString::number(m.getPrice());
			}
		}
		return QVariant{};
	}

	QVariant headerData(int section, Qt::Orientation orientation, int role) const
	{
		if (role == Qt::DisplayRole)
		{
			if (orientation == Qt::Horizontal) {
				switch (section)
				{
				case 0:
					return tr("Denumire");
				case 1:
					return tr("Producator");
				case 2:
					return tr("SubstActiva");
				case 3:
					return tr("Pret");
				default:
					return QString("");
				}
			}
		}
		return QVariant{};
	}

	void setMeds(const std::vector<Meds>& meds) {
		this->meds = meds;
		auto topLeft = createIndex(0, 0);
		auto bottomR = createIndex(rowCount(), columnCount());
		emit dataChanged(topLeft, bottomR);
		emit layoutChanged();
	}
};
