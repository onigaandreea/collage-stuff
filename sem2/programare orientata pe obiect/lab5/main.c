#define _CRTDBG_MAP_ALLOC
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <crtdbg.h>

#include "service.h"
#include "transaction.h"
#include "Mylist.h"

void test_all(void) {
    test_validate();
    test_create_destroy();
    testCopy();
    testCreate();
    testIterate();
    test_add();
    test_set();
    test_delete();
    testFilterSum();
    testAddService();
    testFilterType();
    testFindService();
    testFilterDay();
    testSortServiceDay();
    testSortServiceSum();
    testDeleteService();
    testModifyService();
    testUndo();
}

void print_menu(void)
{
    printf("MENIU\n");
    printf("0.Afisare tranzactii\n");
    printf("1.Adaugare tranzactie\n");
    printf("2.Modificare tranzactie existenta\n");
    printf("3.Stergere tranzactie existenta\n");
    printf("4.Tranzactii de un anumit tip\n");
    printf("5.Tranzactii cu suma mai mare decat o suma data\n");
    printf("6.Sortare crescatoare dupa suma\n");
    printf("7.Sortare descrescatoare dupa zi\n");
    printf("8.Tranzactiile dintr-o anumita zi\n");
    printf("9.Undo\n");
    printf("10.Exit\n");
}

void print_all_transactions(MyList* account) {
    if (account->length == 0)
        printf("Nu exista tranzactii.");
    else {
        printf("Tranzactiile curente sunt:\n");
        for (int i = 0; i < size(account); i++) {
            Transaction* t = get(account, i);
            printf("Ziua: %d | Suma: %f | Tipul: %s | Descrierea: %s\n", t->day, t->sum, t->type, t->description);
        }
    }
    
}

void showAll(MyList* account) {
    MyList* allTrans = copyList(account);
    print_all_transactions(allTrans);
    destroy(allTrans);
}

void ui_add(Bank* bank) {
    int day;
    float sum;
    char type[50], description[200];
    printf("Ziua tranzactiei:");
    scanf_s("%d", &day);
    printf("Tipul tranzactiei:");
    scanf_s("%s", type, 50);
    printf("Suma tranzactiei:");
    scanf_s("%f", &sum);
    printf("Descrierea tranzactiei:");
    //scanf("%s", description);
    //scanf("%[^\n]%*c",description);
    scanf_s("\n");
    scanf_s("%s", description, 200);
    int ok = addTrans(bank, day, sum, type, description);
    if (ok)
        printf("Tranzactie adaugata cu succes.\n");
    else
        printf("Tranzactie invalida.\n");
}


void ui_modify(Bank* bank) {
    int day, new_day;
    float sum, new_sum;
    char type[50], new_type[50], new_description[200];
    printf("Ziua tranzactiei este:");
    scanf_s("%d", &day);
    printf("Suma tranzactiei este:");
    scanf_s("%f", &sum);
    printf("Tipul tranzactiei este:");
    scanf_s("%s", type, 50);
    printf("Ziua actualizata:");
    scanf_s("%d", &new_day);
    printf("Suma actualizata:");
    scanf_s("%f", &new_sum);
    printf("Tipul actualizat:");
    scanf_s("%s", new_type, 50);
    printf("Descrierea actualizata:");
    scanf_s("%s", new_description, 200);


    int ok = modifyTrans(bank, day, sum, type, new_day, new_sum, new_type, new_description);
    if (ok)
        printf("Tranzactie modificata cu succes.\n");
    else
        printf("Nu exista tranzactie cu datele date.\n");
}

void ui_delete(Bank* bank) {
    int day;
    float sum;
    char type[50];
    printf("Ziua tranzactiei:");
    scanf_s("%d", &day);
    printf("Suma tranzactiei:");
    scanf_s("%f", &sum);
    printf("Tipul tranzactiei:");
    scanf_s("%s", type, 50);

    int ok = deleteTrans(bank, day, sum, type);
    if (ok)
        printf("Tranzactie stearsa cu succes.\n");
    else
        printf("Nu exista tranzactie cu datele date.\n");
}

void ui_filter_type(Bank* bank) {
    char type[50];
    printf("Tipul tranzactiei este:");
    scanf_s("%s", type, 50);
    MyList* filteredList = type_filter(bank, type);
    showAll(filteredList);
    destroy(filteredList);

}

void ui_filter_sum(Bank* bank) {
    float sum;
    printf("Suma tranzactiei sa fie mai mare decat:");
    scanf_s("%f", &sum);
    MyList* filteredList = sum_greater_filter(bank, sum);
    showAll(filteredList);
    destroy(filteredList);
}

void ui_filter_day(Bank* bank) {
    int day;
    printf("Ziua tranzactiei este:");
    scanf_s("%d", &day);
    MyList* filteredList = day_filter(bank, day);
    showAll(filteredList);
    destroy(filteredList);
}

void ui_sort_sum(Bank* bank) {
    MyList* sorted = sort_sum(bank);
    showAll(sorted);
    destroy(sorted);
}

void ui_sort_day(Bank* bank) {
    MyList* sorted = sort_day(bank);
    showAll(sorted);
    destroy(sorted);
}


void ui(void) {
    Bank bank = createBank();
    int run = 1;
    while (run) {
        print_menu();
        int cmd;
        printf("Comanda este:");
        scanf_s("%d", &cmd);
        if (cmd == 0)
            showAll(bank.allTransactions);
        else if (cmd == 1)
            ui_add(&bank);
        else if (cmd == 2)
            ui_modify(&bank);
        else if (cmd == 3)
            ui_delete(&bank);
        else if (cmd == 4)
            ui_filter_type(&bank);
        else if (cmd == 5)
            ui_filter_sum(&bank);
        else if (cmd == 6)
            ui_sort_sum(&bank);
        else if (cmd == 7)
            ui_sort_day(&bank);
        else if (cmd == 8)
            ui_filter_day(&bank);
        else if (cmd == 9)
        {

            Transaction* nush = (MyList*)(bank.allTransactions)->elems[0];
            if (undo(&bank) != 0) {
                printf("No more undo!!!\n");
            }
        }
        else if (cmd == 10) {
            destroyBank(&bank);
            break;
        }
        else
            printf("Comanda invalida!\n");
    }
}

int main() {
    test_all();
    //ui();
    _CrtDumpMemoryLeaks();
    return 0;
}