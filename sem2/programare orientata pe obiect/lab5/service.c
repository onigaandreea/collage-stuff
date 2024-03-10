//
//  service.c
//  Bank account
//
//  Created by Denisa
//
#define _CRTDBG_MAP_ALLOC
#include "service.h"
#include <assert.h>
#include <stdlib.h>
#include <string.h>



Bank createBank()
{
    Bank rez;
    rez.allTransactions = createEmpty();
    rez.undoList = createEmpty();
    return rez;
}

void destroyBank(Bank* bank)
{
    destroy(bank->allTransactions);
    for (int i = 0; i < bank->undoList->length; ++i) {
        destroy(bank->undoList->elems[i]);
    }
    free(bank->undoList->elems);
    free(bank->undoList);
}

int addTrans(Bank* bank, int day, float sum, char* type, char* description) {
    Transaction* t = createTrans(day, sum, type, description);
    MyList* toUndo = copyList(bank->allTransactions);
    int ok = validateTrans(t);
    if (!ok) {
        destroyTrans(t);
        return 0;
    }
    else
    {
        add(bank->allTransactions, t);
    
        add(bank->undoList, toUndo);
        
        return 1;
    }
   
     
}


int findTrans(Bank* bank, int day, float sum, char* type) {
    int poz = -1;
    for (int i = 0; i < bank->allTransactions->length; i++) {
        Transaction* t = get(bank->allTransactions, i);
        if (t->day == day && t->sum == sum && strcmp(t->type, type) == 0) {
            poz = i;
            break;
        }
    }
    return poz;
}

int deleteTrans(Bank* bank, int day, float sum, char* type) {
    MyList* toUndo = copyList(bank->allTransactions);

    add(bank->undoList, toUndo);

    int poz_to_delete = findTrans(bank, day, sum, type);
    if (poz_to_delete != -1) {
        Transaction* t = delete_el(bank->allTransactions, poz_to_delete);
        destroyTrans(t);
        return 1;
    }
    return 0;
}


int modifyTrans(Bank* bank, int day, float sum, char* type, int new_day, float new_sum, char* new_type, char* new_description) {
    MyList* toUndo = copyList(bank->allTransactions);

    add(bank->undoList, toUndo);
    
    int poz = findTrans(bank, day, sum, type);

    if (poz != -1) {
        Transaction* new_trans = createTrans(new_day, new_sum, new_type, new_description);
        Transaction* t = get(bank->allTransactions, poz);
        destroyTrans(t);
        setElem(bank->allTransactions, poz, new_trans);
        return 1;
    }
    else
        return 0;
}


MyList* type_filter(Bank* bank, char* type) {
    if (type == NULL || strlen(type) == 0) {
        return copyList(bank->allTransactions);
    }
    MyList* filtered_list = createEmpty();
    for (int i = 0; i < bank->allTransactions->length; i++) {
        Transaction* t = get(bank->allTransactions, i);
        if (strcmp(type, t->type) == 0)
            add(filtered_list, createTrans(t->day, t->sum, t->type, t->description));
    }
    return filtered_list;

}

MyList* day_filter(Bank* bank, int day) {

    MyList* filtered_list = createEmpty();
    for (int i = 0; i < bank->allTransactions->length; i++) {
        Transaction* t = get(bank->allTransactions, i);
        if (t->day == day)
            add(filtered_list, createTrans(t->day, t->sum, t->type, t->description));
    }
    return filtered_list;

}

MyList* sum_greater_filter(Bank* bank, float sum) {
    MyList* filtered_list = createEmpty();
    for (int i = 0; i < bank->allTransactions->length; i++) {
        Transaction* t = get(bank->allTransactions, i);
        if (t->sum > sum)
            add(filtered_list, createTrans(t->day, t->sum, t->type, t->description));
    }
    return filtered_list;
}


MyList* sort_sum(Bank* bank) {
    MyList* sorted_list = copyList(bank->allTransactions);
    for (int i = 0; i < sorted_list->length - 1; i++)
        for (int j = i + 1; j < sorted_list->length; j++) {
            Transaction* t1 = get(sorted_list, i);
            Transaction* t2 = get(sorted_list, j);
            if (t1->sum > t2->sum) {
                setElem(sorted_list, i, t2);
                setElem(sorted_list, j, t1);
            }
        }
    return sorted_list;
}


MyList* sort_day(Bank* bank) {
    MyList* sorted_list = copyList(bank->allTransactions);
    for (int i = 0; i < sorted_list->length - 1; i++)
        for (int j = i + 1; j < sorted_list->length; j++) {
            Transaction* t1 = get(sorted_list, i);
            Transaction* t2 = get(sorted_list, j);
            if (t1->day < t2->day) {
                setElem(sorted_list, i, t2);
                setElem(sorted_list, j, t1);
            }
        }
    return sorted_list;
}

int undo(Bank* bank) {
    if (size(bank->undoList) == 0) {
        return 1;//no more undo
    }
    MyList* l = removeLast(bank->undoList);
    destroy(bank->allTransactions);

    bank->allTransactions = l;
    return 0;
}

/*typedef int* FunctieComparare(ElemType* el1, ElemType* el2){
    return el1->sum - el2->sum;
}*/

/*void sort(MyList* list, FunctieComparare cmpF) {
    int i, j;
    for (i = 0; i < size(list); i++) {
        for (j = i + 1; j < size(list); j++) {
            ElemType t1 = get(list, i);
            ElemType t2 = get(list, j);
            if (cmpF(&t1, &t2) > 0) {
                setElem(list, i, t2);
                setElem(list, j, t1);
            }
        }
    }
}*/

//tests

void testAddService(void) {
    Bank bank = createBank();

    int ok1 = addTrans(&bank, 10, 1000, "intrare", "salariu");
    assert(ok1 == 1);

    /*int ok2 = addTrans(&bank, 35, 123, "iesire", "ok");
    assert(ok2 == 0);

    int ok3 = addTrans(&bank, 13, -9, "intrare", "ok");
    assert(ok3 == 0);

    int ok4 = addTrans(&bank, 12, 20.9, "ok", "ok");
    assert(ok4 == 0);

    int ok5 = addTrans(&bank, 12, 12, "iesire", "");
    assert(ok5 == 0);*/

    assert(size(bank.allTransactions) == 1);
    Transaction* t = get(bank.allTransactions, 0);

    assert(t->day == 10);
    assert(t->sum == 1000);
    assert(strcmp(t->type, "intrare") == 0);
    assert(strcmp(t->description, "salariu") == 0);


    destroyBank(&bank);

}


void testFindService(void) {
    Bank bank = createBank();

    int ok1 = addTrans(&bank, 10, 1000, "intrare", "salariu nou");
    assert(ok1 == 1);

    int ok2 = addTrans(&bank, 30, 123, "iesire", "ok");
    assert(ok2 == 1);

    int ok3 = addTrans(&bank, 13, 123, "intrare", "ok");
    assert(ok3 == 1);

    assert(size(bank.allTransactions) == 3);
    int poz = findTrans(&bank, 30, 123, "iesire");

    assert(poz == 1);

    destroyBank(&bank);
}


void testModifyService(void) {
    Bank bank = createBank();

    int ok1 = addTrans(&bank, 10, 1000, "intrare", "salariu nou");
    assert(ok1 == 1);

    int ok2 = addTrans(&bank, 30, 123, "iesire", "ok");
    assert(ok2 == 1);

    int ok3 = addTrans(&bank, 13, 123, "intrare", "ok");
    assert(ok3 == 1);

    assert(size(bank.allTransactions) == 3);
    int modify_success = modifyTrans(&bank, 30, 123, "iesire", 10, 1200, "intrare", "new");
    assert(modify_success == 1);
    int modify_success2 = modifyTrans(&bank, 15, 123, "iesire", 14, 124, "intrare", "ok");
    assert(modify_success2 == 0);
    destroyBank(&bank);
}


void testDeleteService(void) {
    Bank bank = createBank();

    int ok1 = addTrans(&bank, 10, 1000, "intrare", "salariu nou");
    assert(ok1 == 1);

    int ok2 = addTrans(&bank, 30, 123, "iesire", "ok");
    assert(ok2 == 1);

    int ok3 = addTrans(&bank, 13, 123, "intrare", "ok");
    assert(ok3 == 1);

    assert(size(bank.allTransactions) == 3);

    int succes1 = deleteTrans(&bank, 30, 123, "iesire");
    assert(succes1 == 1);
    int succes2 = deleteTrans(&bank, 15, 124, "intrare");
    assert(succes2 == 0);
    destroyBank(&bank);
}

void testFilterType(void) {
    Bank bank = createBank();

    int ok1 = addTrans(&bank, 10, 1000, "intrare", "salariu nou");
    assert(ok1 == 1);

    int ok2 = addTrans(&bank, 30, 123, "iesire", "ok");
    assert(ok2 == 1);

    int ok3 = addTrans(&bank, 13, 123, "intrare", "ok");
    assert(ok3 == 1);

    assert(size(bank.allTransactions) == 3);

    MyList* filteredList = type_filter(&bank, "intrare");
    assert(size(filteredList) == 2);
    destroy(filteredList);

    filteredList = type_filter(&bank, "iesire");
    assert(size(filteredList) == 1);
    destroy(filteredList);


    destroyBank(&bank);

}

void testFilterSum(void) {
    Bank bank = createBank();

    int ok1 = addTrans(&bank, 10, 1000, "intrare", "salariu nou");
    assert(ok1 == 1);

    int ok2 = addTrans(&bank, 30, 13, "iesire", "ok");
    assert(ok2 == 1);

    int ok3 = addTrans(&bank, 13, 123, "intrare", "ok");
    assert(ok3 == 1);

    assert(size(bank.allTransactions) == 3);

    MyList* filteredList = sum_greater_filter(&bank, 120);
    assert(size(filteredList) == 2);
    destroy(filteredList);

    filteredList = sum_greater_filter(&bank, 10);
    assert(size(filteredList) == 3);
    destroy(filteredList);


    destroyBank(&bank);

}

void testFilterDay(void) {
    Bank bank = createBank();

    int ok1 = addTrans(&bank, 10, 1000, "intrare", "salariu nou");
    assert(ok1 == 1);

    int ok2 = addTrans(&bank, 30, 13, "iesire", "ok");
    assert(ok2 == 1);

    int ok3 = addTrans(&bank, 10, 123, "intrare", "ok");
    assert(ok3 == 1);

    assert(size(bank.allTransactions) == 3);

    MyList* filteredList = day_filter(&bank, 10);
    assert(size(filteredList) == 2);
    destroy(filteredList);

    filteredList = day_filter(&bank, 30);
    assert(size(filteredList) == 1);
    destroy(filteredList);


    destroyBank(&bank);

}

void testSortServiceSum(void) {
    Bank bank = createBank();

    int ok1 = addTrans(&bank, 10, 1000, "intrare", "salariu nou");
    assert(ok1 == 1);

    int ok2 = addTrans(&bank, 30, 127, "iesire", "ok");
    assert(ok2 == 1);

    int ok3 = addTrans(&bank, 13, 123, "intrare", "ok");
    assert(ok3 == 1);

    assert(size(bank.allTransactions) == 3);
    MyList* sortedList = sort_sum(&bank);
    Transaction* t = get(sortedList, 0);
    assert(t->sum == 123);
    t = get(sortedList, 1);
    assert(t->sum == 127);
    t = get(sortedList, 2);
    assert(t->sum == 1000);

    
    destroy(sortedList);
    destroyBank(&bank);

}


void testSortServiceDay(void) {
    Bank bank = createBank();

    int ok1 = addTrans(&bank, 10, 1000, "intrare", "salariu nou");
    assert(ok1 == 1);

    int ok2 = addTrans(&bank, 30, 127, "iesire", "ok");
    assert(ok2 == 1);

    int ok3 = addTrans(&bank, 13, 123, "intrare", "ok");
    assert(ok3 == 1);

    assert(size(bank.allTransactions) == 3);
    MyList* sortedList = sort_day(&bank);
    Transaction* t = get(sortedList, 0);
    assert(t->day == 30);
    t = get(sortedList, 1);
    assert(t->day == 13);
    t = get(sortedList, 2);
    assert(t->day == 10);

    
    destroy(sortedList);
    destroyBank(&bank);
}

void testUndo() {
    Bank bank = createBank();
    
    int ok1 = addTrans(&bank, 10, 1000, "intrare", "salariu nou");
    assert(ok1 == 1);

    int ok2 = addTrans(&bank, 30, 127, "iesire", "ok");
    assert(ok2 == 1);

    undo(&bank);
    MyList* l = type_filter(&bank, NULL);
    assert(size(l) == 1);
    destroy(l);

    undo(&bank);
    l = type_filter(&bank, NULL);
    assert(size(l) == 0);
    destroy(l);

    assert(undo(&bank) != 0);
    assert(undo(&bank) != 0);
    assert(undo(&bank) != 0);

    destroyBank(&bank);
}
