#pragma once

//define a new type of element
typedef struct {
	char* tip;
	int suprafata;
	char* adresa;
	int pret;
} oferta_t;

typedef oferta_t* oferta;

/*create an offer
* tip, suprafata, adresa, pret- parameters for the new offer
*/
oferta_t* init_oferta(char* tip, int suprafata, char* adresa, int pret);

/*
* validate an offer
*/
int validate_oferta(oferta_t* off);

/*
* returns the type of the offer
*/
char* get_tip(oferta_t* off);

/*
	returns the surface 
*/
int get_suprafata(oferta_t* off);

/*
	returns the address
*/
char* get_adresa(oferta_t* off);

/*
	returns the price
*/
int get_pret(oferta_t* off);

/*
	sets a new value for the type
*/
void set_tip(oferta_t* off, char* new_tip);

/*
	sets a new value for the surface
*/
void set_suprafata(oferta_t* off, int new_suprafata);

/*
	sets a new value for the address
*/
void set_adresa(oferta_t* off, char* new_adresa);

/*
	sets a new value for the price
*/
void set_pret(oferta_t* off, int new_pret);

/*
	sets a new value for the entire offer
*/
void set_oferta(oferta_t* off1, oferta_t* off2);

/*
	compare two offerts
*/
int cmp_oferta(oferta_t* off1, oferta_t* off2);

oferta_t* copyelem(oferta_t* off);

/*
	destroy an offer
*/
void destroy_oferta(oferta_t* off);