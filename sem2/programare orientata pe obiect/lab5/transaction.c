//
//  transaction.c
//  Bank account
//
//  Created by Denisa
//
#include <stdlib.h>
#include <string.h>
#include <assert.h>

#include "transaction.h"

Transaction* createTrans(int day, float sum, char* type, char* description) {
    Transaction* t=malloc(sizeof(Transaction));
    t->day = day;
    t->sum = sum;

    int nr1 = (int)strlen(type) + 1;
    t->type = malloc(nr1 * sizeof(char));
    strncpy(t->type, type, nr1);

    int nr2 = (int)strlen(description) + 1;
    t->description = malloc(nr2 * sizeof(char));
    strncpy(t->description, description, nr2);

    return t;
}



void destroyTrans(Transaction* t) {
    
    free(t->type);
    free(t->description);
    free(t);
}


int validateTrans(Transaction* t) {
    if (t->day < 1 || t->day > 31)
        return 0;
    if (t->sum < 0)
        return 0;
    if (strcmp(t->type, "intrare") != 0 && strcmp(t->type, "iesire") != 0)
        return 0;
    if (strlen(t->description) == 0)
        return 0;
    return 1;
}


Transaction* copyTrans(Transaction* t) {
    return createTrans(t->day, t->sum, t->type, t->description);
}


void test_create_destroy(void) {
    Transaction* t = createTrans(12, 20, "iesire", "ok ok");

    assert(t->day == 12);
    assert(t->sum == 20);
    assert(strcmp(t->type, "iesire") == 0);
    assert(strcmp(t->description, "ok ok") == 0);

    destroyTrans(t);
}

void test_validate(void) {
    Transaction* t1 = createTrans(32, 20, "iesire", "ok");
    assert(validateTrans(t1) == 0);

    Transaction* t2 = createTrans(10, -12, "intrare", "bine");
    assert(validateTrans(t2) == 0);

    Transaction* t3 = createTrans(10, 12, "ok", "bine");
    assert(validateTrans(t3) == 0);

    Transaction* t4 = createTrans(10, 12, "intrare", "");
    assert(validateTrans(t4) == 0);

    Transaction* t5 = createTrans(10, 12, "intrare", "ok");
    assert(validateTrans(t5) == 1);

    destroyTrans(t1);
    destroyTrans(t2);
    destroyTrans(t3);
    destroyTrans(t4);
    destroyTrans(t5);
}
