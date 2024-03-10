
#ifndef OFERTA_H_
#define OFERTA_H_

/**
 * New data type to store an offert
 */
typedef struct
{
	char type[25];
	int surface;
	char address[100];
	float price;
}Offer;

/*
 * create an offer
 * surface, price numbers
 * type, address strings
 * of - an offer
 */
Offer create(char type[], int surface, char address[], float price);

/*
* distroy an offer
*/

int cmp_oferta(Offer off1, Offer off2);

void destroyOffer(Offer* offer);

void testCreateDestroy();

int validate(Offer);

#endif /* OFERTA_H_ */

