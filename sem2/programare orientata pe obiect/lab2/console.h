#ifndef CONSOLE_H_
#define CONSOLE_H_
#include "Oferta.h"
#include "repo.h"
#include "service.h"

void testAll();

/*
  Read offer from standard input and add to list of offerts
*/
void readOffer(Repo* l);

void printAllOfferts(Repo* l);

void filterOfferts(Repo* l);

void run();

#endif /* CONSOLE_H_ */