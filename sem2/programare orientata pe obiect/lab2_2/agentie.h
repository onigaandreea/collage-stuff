#pragma once
#include "oferta.h"

//define the list
typedef struct {
	oferta* elem;
	int len;
	int cap;
} agentie_t;

typedef agentie_t* agentie;

/*
	create a list of offerts
*/
agentie_t* init_agentie();

/*
	increse the capacity to make room for more elements
	ag- the list to resize
*/
void resize(agentie_t* ag);

/*
	return the size of the list
	ag- the list of elements
*/
int get_len(agentie_t* ag);

/*
	return the element located at the given position
	ag- the list of elements
	poz- the given position
*/
oferta get_elem_at(agentie_t* ag, int poz);

/*
	sets the length of the list at the given value
	ag- the list of elements
	len- the given length
*/
void set_len(agentie_t* ag, int len);

/*
	modify an element located at the given position 
	ag- the list of elements
	off- the new value for the element
	poz- the positin of the element we want to modify
*/
void set_elem_at(agentie_t* ag, oferta off, int poz);

/*
	search for an element into the given list
	ag- the list of elements
	off- the element we are searching
*/
int find_elem(agentie_t* ag, oferta off);

/*
	add a new element to the list
	ag- the list of elements
	off- the elemnt to add
*/
void add_elem(agentie_t* ag, oferta off);

/*
	modify the value of an element
	off- the element to modify
	new_of- the new value for the element
*/
void update_elem(agentie_t* ag, oferta off, oferta new_of);

/*
	delete the element located at the given position
	ag- the list of elements
	poz- the given position
*/
void delete_elem_at(agentie_t* ag, int poz);

/*
	dealocate the space for the list
*/
void destroy_agentie(agentie_t* ag);

/*
	make a copy for a list
	ag- the list
*/
agentie_t* copyList(agentie_t* ag);