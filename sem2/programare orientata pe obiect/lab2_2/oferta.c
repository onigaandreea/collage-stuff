#pragma warning (disable:4996)
#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include "oferta.h"


oferta_t* init_oferta(char* tip, int suprafata, char* adresa, int pret) {
	oferta_t* off = (oferta_t*)malloc(sizeof(oferta_t));
	off->tip = (char*)malloc(20 * sizeof(char));
	off->adresa = (char*)malloc(50 * sizeof(char));
	strcpy_s(off->tip, 20 * sizeof(char), tip);
	off->suprafata = suprafata;
	strcpy_s(off->adresa, 50 * sizeof(char), adresa);
	off->pret = pret;
	return off;
}

int validate_oferta(oferta_t* off) {
	if (off->pret < 0) return -1;
	if (off->suprafata < 0) return -3;
	if (strlen(off->adresa) == 0) return -4;
	if (strcmp(off->tip, "teren") == 0) return 0;
	if (strcmp(off->tip, "casa") == 0) return 0;
	if (strcmp(off->tip, "apartament") == 0) return 0;
	return -2;
}

char* get_tip(oferta_t* off) {
	return off->tip;
}

int get_suprafata(oferta_t* off) {
	return off->suprafata;
}

char* get_adresa(oferta_t* off) {
	return off->adresa;
}

int get_pret(oferta_t* off) {
	return off->pret;
}

void set_tip(oferta_t* off, char* new_tip) {
	strcpy_s(off->tip, 20, new_tip);
}

void set_suprafata(oferta_t* off, int new_suprafata) {
	off->suprafata = new_suprafata;
}

void set_adresa(oferta_t* off, char* new_adresa) {
	strcpy_s(off->adresa, 50, new_adresa);
}

void set_pret(oferta_t* off, int new_pret) {
	off->pret = new_pret;
}

void set_oferta(oferta_t* off1, oferta_t* off2) {
	set_tip(off1, get_tip(off2));
	set_suprafata(off1, get_suprafata(off2));
	set_adresa(off1, get_adresa(off2));
	set_pret(off1, get_pret(off2));
}

int cmp_oferta(oferta_t* off1, oferta_t* off2) {
	if (strcmp(get_tip(off1), get_tip(off2)) != 0) return 0;
	if (get_suprafata(off1) != get_suprafata(off2)) return 0;
	if (strcmp(get_adresa(off1), get_adresa(off2)) != 0) return 0;
	if (get_pret(off1) != get_pret(off2)) return 0;
	return 1;
}

oferta_t* copyelem(oferta_t* off)
{
	return init_oferta(off->tip, off->suprafata, off->adresa, off->pret);
}

void destroy_oferta(oferta_t* off) {
	free(off->tip);
	free(off->adresa);
	free(off);
}