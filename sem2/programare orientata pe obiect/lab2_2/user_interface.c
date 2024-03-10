#include <string.h>
#include <stdlib.h>
#include <stdio.h>

#include "user_interface.h"

void adauga(agentie_t* ag) {
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
	int error = AddElem(ag, type, surface, address, price);
	if (error != 0) {
		printf("Invalid offer.\n");
	}
	else {
		printf("Offer added.\n");
	}
}

void update_of(agentie_t* ag)
{
	printf("The offer to modify has:\n");
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
	oferta of = init_oferta(type, surface, address, price);
	printf("The new parameters are:\n");
	printf("Type:");
	char tip[30];
	scanf_s("%s", tip, 30);
	printf("Surface:");
	int suprafata;
	scanf_s("%d", &suprafata);
	printf("Address:");
	char adresa[50];
	scanf_s("%s", adresa, 50);
	printf("Price:");
	float pret;
	scanf_s("%f", &pret);
	oferta new_of = init_oferta(tip, suprafata, adresa, pret);
	printf("%s", new_of->tip);
	int errors = Update(ag, of, new_of);
	if (errors == -2) printf("The new offer is not valid.\n");
	else if (errors == -1) printf("Offer not found.\n");
	else printf("Offer modified.\n");
}

void deleteEl(agentie_t* ag) {
	printf("The offer to delete has:\n");
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
	int errors = DelElem(ag, type, surface, address, price);
	if (errors != 0) {
		printf("Offer not found.\n");
	}
	else {
		printf("Offer deleted.\n");
	}
}

void printAllOfferts(agentie_t* ag) {
	printf("Offerts:\n");
	for (int i = 0; i < get_len(ag); i++) {
		oferta_t* of = get_elem_at(ag,i);
		printf("Type:%s Surface:%d Address:%s Price:%d\n", of->tip, of->suprafata, of->adresa, of->pret);
	}
}

void filterOfferts(agentie_t* ag) {
	printf("Type filter substring:");
	char filterStr[30];
	scanf_s("%s", filterStr, 30);

	agentie_t* filteredL = getAllOfferts(ag,filterStr);
	printAllOfferts(filteredL);
	if (get_len(filteredL) == 0)
		printf("There are none of this type.");
}

void print_menu() {
	printf("1. Add\n");
	printf("2. Update\n");
	printf("3. Delete\n");
	printf("4. Filter by type\n");
	printf("5. Sort ascending by price\n");
	printf("6. Sort descending by type\n");
	printf("7. Show all\n");
	printf("8. Exit\n");
}

void sort_price(agentie_t* ag) {
	agentie_t* sortedL = sort_by_price(ag);
	printAllOfferts(sortedL);
}

void sort_type(agentie_t* ag) {
	agentie_t* sortedL = sort_by_type(ag);
	printAllOfferts(sortedL);
}

void run() {
	agentie_t* allOfferts = init_agentie();
	int ruleaza = 1;
	while (ruleaza) {
		print_menu();
		int cmd = 0;
		scanf_s("%d", &cmd);
		switch (cmd) {
		case 1:
			adauga(allOfferts);
			break;
		case 2:
			update_of(allOfferts);
			break;
		case 3:
			deleteEl(allOfferts);
			break;
		case 4:
			filterOfferts(allOfferts);
			break;
		case 5:
			sort_price(allOfferts);
			break;
		case 6:
			sort_type(allOfferts);
			break;
		case 7:
			printAllOfferts(allOfferts);
			break;
		case 8:
			ruleaza = 0;
			break;
		default:
			printf("Comanda invalida!!!\n");
		}
	}
}