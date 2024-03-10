#pragma once
#include "service.h"
#include "agentie.h"
#include "oferta.h"

/*
	add an element to the list
	ag- the list
	the parameters are given by the user
*/
void adauga(agentie_t* ag);

/*
	delete an element from the list
	ag- the list
	the parameters are given by the user
*/
void deleteEl(agentie_t* ag);

/*
	modify an element from the list
	ag- the list
	the parameters of the element to modify and the new values for the element are given by the user
*/
void update_of(agentie_t* ag);

/*
	print the element of the list
	ag- the list
*/
void printAllOfferts(agentie_t* ag);

/*
	print the list filtred by the type
	ag- the list
	the typeSubstring is given by the user
*/
void filterOfferts(agentie_t* ag);

/*
	sort ascending by price
*/
void sort_price(agentie_t* ag);

/*
	sort descending by type
*/
void sort_type(agentie_t* ag);

/*
	prints the list of comands
*/
void print_menu();

/*
	runs the application
*/
void run();