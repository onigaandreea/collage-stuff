
#ifndef REPO_H_
#define REPO_H_
#include "Oferta.h"

typedef Offer ElemType;
/*new data type to store a list of offerts*/

typedef struct
{
	ElemType elems[50];
	int lg;
}Repo;

/*
* create an empty list
*/
Repo createRepo();

/*
* distroy list
*/

void destroyRepo(Repo* repo);

/*
  Get an element from the list
  poz - position of the element, need to be valid
  return element on the given position
*/
ElemType get(Repo* repo, int poz);

/*
  return number of elements in the list
*/
int size(Repo* repo);

/*
  Add element into the list
  post: element is added to the end of the list
*/
void Repoadd(Repo* repo, ElemType el);

/*
  Make a shallow copy of the list
  return Mylist containing the same elements as l
*/
Repo copyList(Repo* l);

void update(Repo* repo, Offer offer, Offer newOffer);

void del(Repo* repo, Offer offer);

void testCreateRepo();
void testIterateList();
void testCopyList();

 
#endif /* REPO_H_ */

