#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include "service.h"
#include "Oferta.h"

/*
* add an offer
*/
int Srvadd(Repo* repo, char type[], int surface, char address[], float price)
{
	Offer of = create(type, surface, address, price);
	int succ = validate(of);
	if (succ != 0) return succ;

	Repoadd(repo, of);
	return 0; // all ok
}

void update_offer(Repo* repo, char type[], int surface, char address[], float price, char typen[], int surfacen, char addressn[], float pricen)
{
	ElemType of1, of2;
	of1 = create(type, surface, address, price);
	of2 = create(typen, surfacen, addressn, pricen);
	update(repo, of1, of2);
}
/*
  Filter offerts
  typeSubstring - cstring
  return all offerts where typeSubstring is a substring of the type
*/
Repo getAllOfferts(Repo* repo, char* typeSubstring) {
	if (typeSubstring == NULL || strlen(typeSubstring) == 0) {
		return copyList(repo);
	}
	Repo rez = createRepo();
	for (int i = 0; i < size(repo); i++) {
		Offer of = get(repo, i);
		if (strstr(of.type, typeSubstring) != NULL) {
			Repoadd(&rez,of);
		}
	}
	return rez;
}


void testAddOffer() {
	Repo l = createRepo();
	//try to add invalid offerts
	int error = Srvadd(&l,"",100,"a",200);
	assert(error != 0);
	assert(size(&l) == 0);

	error = Srvadd(&l, "a", -100, "ab", 200);
	assert(error != 0);
	assert(size(&l) == 0);

	error = Srvadd(&l, "a", 100, "", 200);
	assert(error != 0);
	assert(size(&l) == 0);

	error = Srvadd(&l, "a", 100, "ab", -200);
	assert(error != 0);
	assert(size(&l) == 0);

	//try to add some valid offerts
	Srvadd(&l, "a", 100, "ab", 200);
	Srvadd(&l, "a1", 110, "ac", 210);
	Repo filtered = getAllOfferts(&l, NULL);
	assert(size(&filtered) == 2);

	filtered = getAllOfferts(&l, "1");
	assert(size(&filtered) == 1);

	filtered = getAllOfferts(&l, "a1");
	assert(size(&filtered) == 1);

	filtered = getAllOfferts(&l, "a");
	assert(size(&filtered) == 2);
}