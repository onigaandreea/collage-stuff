#include <stdio.h>
#include <string.h>
#include "console.h"



void testAll() {
	testCreateDestroy();
	testCreateRepo();
	testIterateList();
	testCopyList();
	testAddOffer();
}
/*
  Read offer from standard input and add to list of offerts
*/
void readOffer(Repo* l) {
	printf("Type:");
	char type[30];
	scanf_s("%s", type, 30);
	printf("Surface:");
	int surface;
	scanf_s("%d", &surface);
	printf("Address:");
	char address[50];
	scanf_s("%s", address, 50);
	printf("Price:");
	float price;
	scanf_s("%f", &price);
	int error = Srvadd(l, type, surface, address, price);
	if (error != 0) {
		printf("Invalid offer.\n");
	}
	else {
		printf("Offer added.\n");
	}
}

void printAllOfferts(Repo* l) {
	printf("Offerts:\n");
	for (int i = 0; i < size(l); i++) {
		Offer of = get(l, i);
		printf("Type:%s Surface:%d Address:%s Price:%f\n", of.type, of.surface, of.address, of.price);
	}
}

void filterOfferts(Repo* l) {
	printf("Type filter substring:");
	char filterStr[30];
	scanf_s("%s", filterStr, 30);

	Repo filteredL = getAllOfferts(l, filterStr);
	printAllOfferts(&filteredL);
}

void update_el(Repo* repo)
{
	printf("Type:\n");
	char type[30];
	scanf_s("%s", type, 30);
	printf("Surface:\n");
	int surface;
	scanf_s("%d", &surface);
	printf("Address:\n");
	char address[50];
	scanf_s("%s", address, 50);
	printf("Price:\n");
	float price;
	scanf_s("%f", &price);
	printf("Type new:\n");
	char typen[30];
	scanf_s("%s", typen, 30);
	printf("Surface new:\n");
	int surfacen;
	scanf_s("%d", &surfacen);
	printf("Address new:\n");
	char addressn[50];
	scanf_s("%s", addressn, 50);
	printf("Price new:\n");
	float pricen;
	scanf_s("%f", &pricen);
	update_offer(repo, type, surface, address, price, typen, surfacen, addressn, pricen);
	printf("Offer updated.");
}

void run() {
	Repo allOfferts = createRepo();
	int ruleaza = 1;
	while (ruleaza) {
		printf("1 Add\n2 Filter\n3 All\n4 Update\n0 Exit\nCommand:");
		int cmd = 0;
		scanf_s("%d", &cmd);
		switch (cmd) {
		case 1:
			readOffer(&allOfferts);
			break;
		case 2:
			filterOfferts(&allOfferts);
			break;
		case 3:
			printAllOfferts(&allOfferts);
			break;
		case 4:
			update_el(&allOfferts);
			break;
		case 0:
			ruleaza = 0;
			break;
		default:
			printf("Comanda invalida!!!\n");
		}
	}
}