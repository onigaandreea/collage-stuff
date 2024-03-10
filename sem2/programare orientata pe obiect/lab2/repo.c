#include <stdio.h>
#include <stdlib.h>
#include "repo.h"
#include <assert.h>

/*
* create an empty list
*/
Repo createRepo()
{
	Repo repo;
	repo.lg = 0;
	return repo;
}

/*
* distroy list
*/
void destroyRepo(Repo* repo)
{
	repo->lg = 0;
}

/*
  Get an element from the list
  poz - position of the element, need to be valid
  return element on the given position
*/
ElemType get(Repo* repo, int poz)
{
	return repo->elems[poz];
}

/*
  return number of elements in the list
*/
int size(Repo* repo)
{
	return repo->lg;
}

/*
  Add element into the list
  post: element is added to the end of the list
*/
void Repoadd(Repo* repo, ElemType el)
{
	repo->elems[repo->lg] = el;
	repo->lg++;
}

int find_elem(Repo* repo, ElemType off) {
	for (int i = 0; i < repo->lg; i++) {
		if (cmp_oferta(off, get(repo, i)) == 1) {
			return i;
		}
	}
	return -1;
}


void update(Repo* repo, ElemType el1, ElemType el2)
{
	el1 = el2;
}
/*
  Make a shallow copy of the list
  return Mylist containing the same elements as l
*/
Repo copyList(Repo* l)
{
	Repo copyl = createRepo();
	for (int i = 0; i < size(l); i++) {
		ElemType p = get(l, i);
		Repoadd(&copyl, p);
	}
	return copyl;
}

void testCreateRepo() {
	Repo repo = createRepo();
	assert(repo.lg == 0);
}

void testIterateList() {
	Repo repo = createRepo();
	Repoadd(&repo, create("a",20, "b", 10));
	Repoadd(&repo, create("a1", 22, "b1", 20));
	assert(size(&repo) == 2);
	Offer of = get(&repo, 0);

	assert(strcmp(of.type, "a") == 0);
	of = get(&repo, 1);
	assert(strcmp(of.address, "b1") == 0);
	destroyRepo(&repo);
	assert(size(&repo) == 0);
}
void testCopyList() {
	Repo repo = createRepo();
	Repoadd(&repo, create("a", 20, "b", 10));
	Repoadd(&repo, create("a1", 22, "b1", 20));
	Repo l = copyList(&repo);
	assert(size(&l) == 2);
	Offer of = get(&l, 0);
	assert(strcmp(of.type, "a") == 0);
}
