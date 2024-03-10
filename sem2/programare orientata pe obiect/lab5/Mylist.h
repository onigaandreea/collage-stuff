//
//  Mylist.h
//  Bank account
//
//  Created by Denisa
//
#pragma once
#ifndef Mylist_h
#define Mylist_h
#include <stdlib.h>
#include "transaction.h"

typedef void* ElemType;

typedef struct {
    ElemType* elems;
    int length;
    int capacity;
}MyList;

/*
 Create an empty list
 @return: the created list (MyList)
 */
MyList* createEmpty();


/*
 Destroy the list
 */
void destroy(MyList* account);


/*
 Return the element which is in the position poz
 @param account: (the adress for) the list (MyList*)
 @param poz: the position of an element
 */
ElemType get(MyList* account, int poz);


/*
 Put the element el on the position poz in the list
 @param account: (the adress for) the list (MyList*)
 @param poz: the position on which the element will be placed
 */
ElemType setElem(MyList* account, int poz, ElemType el);


/*
 Return the number of elements in the list
 @param account: (the adress for) the list (MyList*)
 */
int size(MyList* account);


/*
 Add an element
@param account: (the adress for) the list (MyList*)
@param el: the element to add
post: the element will be added to the list
*/
void add(MyList* account, ElemType el);


/*
 Delete an element
@param account: (the adress for) the list (MyList*)
@param poz: the position from which is the element deleted

@return: the element which has been deleted (ElemType)
post: the element was deleted from the list
*/
ElemType delete_el(MyList* account, int poz);


/*
Remove last element from the list
!!!! do not destroy removed element
return the removed element
*/
ElemType removeLast(MyList* l);
/*
  Make a shallow copy of the list
  return Mylist containing the same elements as l
*/
MyList* copyList(MyList* l);


//tests
void testCreate(void);
void testIterate(void);
void testCopy(void);
void test_add(void);
void test_delete(void);
void test_get(void);
void test_set(void);



#endif /* Mylist_h */

