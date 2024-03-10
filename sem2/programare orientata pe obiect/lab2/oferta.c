#include <stdio.h>
#include "Oferta.h"
#include <string.h>
#include <assert.h>


/*
 * create an offer
 */
Offer create(char type[], int surface, char address[], float price)
{
	Offer of;
	strcpy_s(of.type,sizeof(of.type),type);
	of.surface = surface;
	strcpy_s(of.address,sizeof(of.address),address);
	of.price = price;
	return of;
}

/*
* distroy an offer
*/
void destroyOffer(Offer *of)
{
	//mark that offer is destroyed, avoid accidental use after destroy
	of->type[0] = '\0';
	of->surface = -1;
	of->address[0] = '\0';
	of->price = -1;
}


/*
* validate data
*/

int validate(Offer of) {
	if (strlen(of.type) == 0) {
		return 1;
	}
	if (of.surface < 0)
	{
		return 2;
	}
	if (strlen(of.address) == 0) {
		return 3;
	}
	if (of.price < 0) {
		return 4;
	}
	return 0;
}

int cmp_oferta(Offer off1, Offer off2) {
	if (strcmp(off1.type, off2.type) != 0) return 0;
	if (off1.surface != off2.surface) return 0;
	if (strcmp(off1.address, off2.address) != 0) return 0;
	if (off1.price != off2.price) return 0;
	return 1;
}

/*
* test creation and distruction
*/
void testCreateDestroy()
{
	Offer of;
	of=create("casa", 100, "Blaj,nr.24", 25000);
	assert(strcmp(of.type,"casa")==0);
	assert(of.surface == 100);
	assert(strcmp(of.address,"Blaj,nr.24")==0);
	assert(of.price == 25000);

	destroyOffer(&of);
	assert(strlen(of.type) == 0);
	assert(of.surface == -1);
	assert(strlen(of.address) == 0);
	assert(of.price == -1);
}