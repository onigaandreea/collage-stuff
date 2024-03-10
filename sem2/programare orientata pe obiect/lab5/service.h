//
//  service.h
//  Bank account
//
//  Created by Denisa
//
#pragma once
#ifndef service_h
#define service_h
#define _CRTDBG_MAP_ALLOC
#include <stdlib.h>
#include "Mylist.h"

typedef struct {
	MyList* allTransactions;
	MyList* undoList;//list of list 
} Bank;

/*
  create a bank
*/
Bank createBank();

void destroyBank(Bank* bank);

/*
 Add a transaction
 @param day: the day of the transaction that is added
 @param sum: the sum of the transaction that is added
 @param type: the type of the transaction that is added
 @param description: the description of the transaction that is added

 @return: 1 if the transaction has been added, 0 otherwise
 */
int addTrans(Bank* bank, int day, float sum, char* type, char* description);


/*
 Find a transaction in the list
 @param day: the day of the transaction that is searched for
 @param sum: the sum of the transaction that is searched for
 @param type: the type of the transaction that is searched for

 @return: the position where is the transaction in the list
 */
int findTrans(Bank* bank, int day, float sum, char* type);


/*
 Delete a transaction
 @param day: the day of the transaction that is deleted
 @param sum: the sum of the transaction that is deleted
 @param type: the type of the transaction that is deleted

 @return: 1 if the transaction has been deleted, 0 otherwise
 */
int deleteTrans(Bank* bank, int day, float sum, char* type);


/*
 Modify a transaction

 @param day: the day of the transaction that is modified
 @param sum: the sum of the transaction that is modified
 @param type: the type of the transaction that is modified
 @param new_day: the new day for the modified transaction
 @param new_sum: the new sum for the modified transaction
 @param neww_type: the new type for the modified transaction
 @param new_description: the new description for the modified transaction

 @return: 1 if the transaction has been modified, 0 otherwise
 */
int modifyTrans(Bank* bank, int day, float sum, char* type, int new_day, float new_sum, char* new_type, char* new_description);


/*
 Filter the list of transactions by a given type

 @param type: the type by which the filtering is done
 */
MyList* type_filter(Bank* bank, char* type);

MyList* day_filter(Bank* bank, int day);
/*
 Filter the list of transactions by a given sum; the sum of the transaction must be greater than the given sum

 @param account: (the adress for) the list (MyList*)
 @param sum: the sum by which the filtering is done
 */
MyList* sum_greater_filter(Bank* bank, float sum);


/*
 Filter the list of transactions by a given sum; the sum of the transaction must be less than the given sum

 @param sum: the sum by which the filtering is done
 */
MyList* sum_lower_filter(Bank* bank, float sum);


/*
 Sort a list by the sum in ascending order

 */
MyList* sort_sum(Bank* bank);


/*
 Sort a list by the day in descending order

 */
MyList* sort_day(Bank* bank);

//typedef int (*FunctieComparare)(ElemType* el1, ElemType* el2);
//void sort(MyList* list, FunctieComparare cmpF);

/*
Restore previous pet list
return 0 on ok, 1 if there is no more available undo
*/
int undo(Bank* bank);

//tests
void testAddService(void);
void testFindService(void);
void testModifyService(void);
void testDeleteService(void);
void testFilterType(void);
void testFilterSum(void);
void testFilterDay(void);
void testSortServiceSum(void);
void testSortServiceDay(void);
void testUndo();
#endif /* service_h */
