#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_lab11.h"

class lab11 : public QMainWindow
{
    Q_OBJECT

public:
    lab11(QWidget *parent = Q_NULLPTR);

private:
    Ui::lab11Class ui;
};
