#pragma once

#ifndef SERVICE_H_
#define SERVICE_H_
#include "repo.h"

/*
Add an offer
repo- list of offerts
type- type of an offer
surface- surface of an offer
address- the address where is located
price- the price of the offer
*/
int Srvadd(Repo* repo, char type[], int surface, char address[], float price);

/*
  Filter offerts
  typeSubstring - cstring
  return all offerts where typeSubstring is a substring of the type
*/
Repo getAllOfferts(Repo* repo, char* typeSubstring);

void update_offer(Repo* repo, char type[], int surface, char address[], float price, char typen[], int surfacen, char addressn[], float pricen);

void testAddOffer();

#endif /* SERVICE_H_ */
