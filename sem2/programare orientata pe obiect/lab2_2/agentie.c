#include <stdlib.h>
#include <stdio.h>

#include "agentie.h"
#include "oferta.h"

agentie_t* init_agentie() {
	agentie_t* ag = (agentie_t*)malloc(sizeof(agentie_t));
	ag->elem = (oferta*)malloc(200 * sizeof(oferta));
	for (int i = 0; i < 200; i++) {
		ag->elem[i] = init_oferta(" ", 0, " ", 0);
	}
	ag->len = 0;
	return ag;
}

void resize(agentie_t* ag) {
	int nCap = 2 * ag->cap;
	oferta* nElems = malloc(nCap * sizeof(oferta));
	//copiez din vectorul existent
	int i;
	for (i = 0; i < ag->len; i++) {
		nElems[i] = ag->elem[i];
	}
	//dealocam memoria ocupata de vector
	free(ag->elem);
	ag->elem = nElems;
	ag->cap = nCap;
}


int get_len(agentie_t* ag) {
	return ag->len;
}

oferta get_elem_at(agentie_t* ag, int poz) {
	return ag->elem[poz];
}

void set_len(agentie_t* ag, int len) {
	ag->len = len;
}

void set_elem_at(agentie_t* ag, oferta off, int poz) {
	set_oferta(ag->elem[poz], off);
}

int find_elem(agentie_t* ag, oferta off) {
	for (int i = 0; i < ag->len; i++) {
		if (cmp_oferta(off, get_elem_at(ag, i)) == 1) {
			return i;
		}
	}
	return -1;
}

void add_elem(agentie_t* ag, oferta off) {
	if (ag->len == ag->cap) {
		resize(ag);
	}
	set_elem_at(ag, off, ag->len);
	set_len(ag, ag->len + 1);
}

void update_elem(agentie_t* ag, oferta off, oferta new_of)
{
	int poz = find_elem(ag, off);
	set_elem_at(ag, new_of, poz);
}

void delete_elem_at(agentie_t* ag, int poz) {
	for (int i = poz; i < ag->len; i++) {
		set_elem_at(ag, ag->elem[i + 1], i);
	}
	set_len(ag, ag->len - 1);
}

void destroy_agentie(agentie_t* ag) {
	for (int i = 0; i < 200; i++) {
		destroy_oferta(ag->elem[i]);
	}
	free(ag->elem);
	free(ag);
}

agentie_t* copyList(agentie_t* ag)
{
	agentie_t* copyl = init_agentie();
	for (int i = 0; i < get_len(ag); i++) {
		oferta of=get_elem_at(ag, i);
		add_elem(copyl, of);
	}
	return copyl;
}


