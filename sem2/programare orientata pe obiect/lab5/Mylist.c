
//
//  Mylist.c
//  Bank account
//
//  Created by Denisa
//
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include "Mylist.h"

MyList* createEmpty() {
    MyList* account = malloc(sizeof(MyList));
    account->capacity = 2;
    account->length = 0;
    account->elems = malloc(sizeof(ElemType) * account->capacity);
    return account;
}

ElemType get(MyList* account, int poz) {
    return account->elems[poz];
}

void destroy(MyList* account) {
    for (int i = 0; i < account->length; i++) {

        destroyTrans(account->elems[i]);
    }
    
    free(account->elems);
    free(account);
}


ElemType setElem(MyList* account, int poz, ElemType el) {
    ElemType replacedElem = account->elems[poz];
    account->elems[poz] = el;
    return replacedElem;
}


int size(MyList* account) {
    return account->length;
}


void add(MyList* account, ElemType el) {
    if (account->length == account->capacity) {
        int newCapacity = account->capacity * 2;
        ElemType* aux_elems = malloc(sizeof(ElemType) * newCapacity);
        for (int i = 0; i < account->length; i++) {
            aux_elems[i] = account->elems[i];
        }
        free(account->elems);
        account->elems = aux_elems;
        account->capacity = newCapacity;
    }
    account->elems[account->length] = el;
    account->length++;
}


ElemType delete_el(MyList* account, int poz) {
    ElemType el = account->elems[poz];
    for (int i = poz; i < account->length - 1; i++) {
        account->elems[i] = account->elems[i + 1];
    }
    account->length--;
    return el;
}

ElemType removeLast(MyList* l)
{
    ElemType rez = l->elems[l->length - 1];
    l->length -= 1;
    return rez;
}

MyList* copyList(MyList* l) {
    MyList* rez = createEmpty();
    for (int i = 0; i < size(l); i++) {
        ElemType p = get(l, i);
        add(rez, copyTrans(p));
    }
    return rez;
}


//tests
void testCreate(void) {
    MyList* account = createEmpty();
    assert(size(account) == 0);
    destroy(account);
}


void testIterate(void) {
    MyList* account = createEmpty();
    Transaction* t1 = createTrans(10, 207, "intrare", "in regula");
    Transaction* t2 = createTrans(16, 25.5, "iesire", "mancare");
    Transaction* t3 = createTrans(20, 1200, "intrare", "premiu");

    add(account, t1);
    add(account, t2);
    add(account, t3);

    assert(size(account) == 3);
    Transaction* t = get(account, 0);

    assert(t->day == 10);
    assert(t->sum == 207);
    assert(strcmp(t->type, "intrare") == 0);
    assert(strcmp(t->description, "in regula") == 0);

    destroy(account);
}


void testCopy(void) {
    MyList* account1 = createEmpty();
    add(account1, createTrans(11, 1200, "intrare", "salariu"));
    add(account1, createTrans(13, 230, "iesire", "mancare"));
    assert(size(account1) == 2);
    MyList* account2 = copyList(account1);
    assert(size(account2) == 2);
    Transaction* t = get(account2, 0);
    assert(t->day == 11);
    assert(t->sum == 1200);
    assert(strcmp(t->type, "intrare") == 0);
    assert(strcmp(t->description, "salariu") == 0);

    destroy(account1);
    destroy(account2);

}


void test_add(void) {
    MyList* account = createEmpty();
    Transaction* t = createTrans(10, 1000, "intrare", "prima");
    add(account, t);

    Transaction* tr = get(account, 0);
    assert(tr->day == 10);
    assert(tr->sum == 1000);
    assert(strcmp(tr->type, "intrare") == 0);
    assert(strcmp(tr->description, "prima") == 0);

    assert(size(account) == 1);

    destroy(account);
}


void test_delete(void) {
    MyList* account = createEmpty();
    add(account, createTrans(11, 1200, "intrare", "salariu"));
    add(account, createTrans(13, 230, "iesire", "mancare"));
    add(account, createTrans(29, 123.5, "intrare", "prima"));

    assert(size(account) == 3);

    Transaction* t_del = delete_el(account, 1);
    assert(t_del->day == 13);
    assert(t_del->sum == 230);
    assert(strcmp(t_del->type, "iesire") == 0);
    assert(strcmp(t_del->description, "mancare") == 0);

    assert(size(account) == 2);

    destroyTrans(t_del);
    destroy(account);
}


void test_set(void) {
    MyList* account = createEmpty();
    add(account, createTrans(11, 1200, "intrare", "salariu"));
    add(account, createTrans(13, 230, "iesire", "mancare"));

    Transaction* new = createTrans(20, 2000, "intrare", "ok");
    Transaction* replaced_elem = setElem(account, 1, new);


    assert(replaced_elem->day == 13);
    assert(replaced_elem->sum == 230);
    assert(strcmp(replaced_elem->type, "iesire") == 0);
    assert(strcmp(replaced_elem->description, "mancare") == 0);


    Transaction* t = get(account, 1);
    assert(t->day == 20);
    assert(t->sum == 2000);
    assert(strcmp(t->type, "intrare") == 0);
    assert(strcmp(t->description, "ok") == 0);

    destroyTrans(replaced_elem);
    destroy(account);
}