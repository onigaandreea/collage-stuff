#pragma once
#include "agentie.h"
#include "oferta.h"

/*
   Tipul functie de comparare pentru 2 elemente
   returneaza 0 daca sunt egale, 1 daca o1>o2, -1 altfel
*/
typedef int(*FunctieCompara)(oferta o1, oferta o2);

/*
	add a new element to the list
	ag- the list
	tip, suprafata, adresa, pret- parameters for the new element
*/
int AddElem(agentie_t* ag, char* tip, int suprafata, char* adresa, int pret);

/*
	delete an element from the list
	ag- the list
	tip, suprafata, adresa, pret- parameters for the element to delete
*/
int DelElem(agentie_t* ag, char* tip, int suprafata, char* adresa, int pret);

/*
	modify an element
	ag- the list
	of- the element to modify
	new_of- the new value for the given element
*/
int Update(agentie_t* ag, oferta of, oferta new_of);

/*
	filter the list by the type
	typeSubstring- the type we filter by
*/
agentie_t* getAllOfferts(agentie_t* ag, char* typeSubstring);

/*
* Sort in place
* cmpf - the relationship according to which it is sorted
*/
void sort_asc(agentie_t* ag, FunctieCompara cmpF);

/*
* Sort in place descendent
* cmpf - the relationship according to which it is sorted
*/
void sort_des(agentie_t* ag, FunctieCompara cmpF);

/*
	sort ascending by price
*/
agentie_t* sort_by_price(agentie_t* ag);

/*
	sort descending by type
*/
agentie_t* sort_by_type(agentie_t* ag);