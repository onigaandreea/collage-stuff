#include <string.h>
#include <stdlib.h>
#include <stdio.h>

#include "service.h"

int AddElem(agentie_t* ag, char* tip, int suprafata, char* adresa, int pret)
{
	oferta off = init_oferta(tip, suprafata, adresa, pret);
	int succ = validate_oferta(off);
	if (succ != 0) {
		destroy_oferta(off);
		return succ;
	}
	add_elem(ag, off);
	return 0; // all ok
	
}

int DelElem(agentie_t* ag, char* tip, int suprafata, char* adresa, int pret)
{
	oferta off = init_oferta(tip, suprafata, adresa, pret);
	int poz = find_elem(ag, off);
	if (poz == -1) return poz;
	
	delete_elem_at(ag, poz);
	return 0; //all ok
}

int Update(agentie_t* ag, oferta of, oferta new_of)
{
	int succ = validate_oferta(new_of);
	if (succ != 0) {
		destroy_oferta(of);
		return -2;
	}
	int poz = find_elem(ag, of);
	if (poz == -1) return poz;

	update_elem(ag, of, new_of);
	return 0; //all ok
}

agentie_t* getAllOfferts(agentie_t* ag, char* typeSubstring) {
	if (typeSubstring == NULL || strlen(typeSubstring) == 0) {
		return copyList(ag);
	}
	agentie_t* rez = init_agentie();
	for (int i = 0; i < get_len(ag); i++) {
		oferta of = get_elem_at(ag, i);
		if (strstr(of->tip, typeSubstring) != NULL) {
			add_elem(rez, of);
		}
	}
	return rez;
}

int cmpPrice(oferta o1, oferta o2) {
	if (o1->pret > o2->pret)
		return 1;
	else if (o1->pret == o2->pret)
		return 0;
	else return -1;
}

int cmpType(oferta o1, oferta o2) {
	return strcmp(o1->tip, o2->tip);
}

void sort_asc(agentie_t* ag, FunctieCompara cmpF) {
	int i, j;
	for (i = 0; i < get_len(ag); i++) {
		for (j = i + 1; j < get_len(ag); j++) {
			oferta o1 = get_elem_at(ag, i);
			oferta o2 = get_elem_at(ag, j);
			oferta aux =copyelem(o1);
			if (cmpF(o1,o2) > 0) {
				//interschimbam
				set_elem_at(ag, o2, i);
				set_elem_at(ag, aux, j);
			}
		}
	}
}

void sort_des(agentie_t* ag, FunctieCompara cmpF) {
	int i, j, imax;
	oferta maxim;
	for (i = 0; i < get_len(ag) - 1; i++) {
		maxim = ag->elem[i];
		imax = i;
		for (j = i + 1; j < get_len(ag); j++)
		{
			oferta o1 = get_elem_at(ag, imax);
			oferta o2 = get_elem_at(ag, j);
			if (cmpF(o1, o2) < 0) {
				maxim = ag->elem[j];
				imax = j;
			}
		}
		oferta o1 = get_elem_at(ag, i);
		oferta aux = copyelem(o1);
		set_elem_at(ag, maxim, i);
		set_elem_at(ag, aux, imax);
	}
}

agentie_t* sort_by_price(agentie_t* ag) {
	agentie_t* l = copyList(ag);
	sort_asc(l, cmpPrice);
	return l;
}

agentie_t* sort_by_type(agentie_t* ag) {
	agentie_t* l = copyList(ag);
	sort_des(l, cmpType);
	return l;
}