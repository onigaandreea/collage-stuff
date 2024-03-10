//
//  transaction.h
//  Bank account
//
//  Created by Denisa
//
#pragma once
#ifndef transaction_h
#define transaction_h

#include <stdlib.h>
#include <string.h>


/*
 New data type to store a transaction
 */
typedef struct {
    int day;
    float sum;
    char* type;
    char* description;
}Transaction;

/*
 Create a new transaction
 @param day: day of the transaction
 @param sum: sum of the transaction
 @param type: type of the transaction
 @param description: description of the transaction
 */
Transaction* createTrans(int day, float sum, char* type, char* description);


/*
 Destroy transaction
 */
void destroyTrans(Transaction* t);


/*
 Validate a transaction
 A transaction is valid if:
 0<day<32
 sum>0
 type is "intrare" or "iesire"
 description other than ""

 @param t: the transaction to validate
 @return: 1 if the transaction is valid, 0 otherwise
 */
int validateTrans(Transaction* t);


/*
 Make a copy of a transaction
 */
Transaction* copyTrans(Transaction* t);


/*
 Tests
 */
void test_create_destroy(void);
void test_validate(void);


#endif /* transaction_h */
