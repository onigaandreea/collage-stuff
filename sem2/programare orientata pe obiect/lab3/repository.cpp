#include "repository.h"
#include "VectorDinamic.h"

void MedRepo:: store(const medicament& m) {
    IteratorVectorT<medicament> i = allMeds.begin();
    while (i.valid()) {
        if (i.element().getName() == m.getName()) {
            throw MedRepoException("Exista deja acest medicament!\n");
        }
        i.next();
    }
    allMeds.add(m);
}


//Cauta dupa denumire
//arunca exceptie daca nu exista medicament

medicament MedRepo::find(string name) {
    IteratorVectorT<medicament> i = allMeds.begin();
    while (i.valid()) {
        if (i.element().getName() == name) {
            return i.element();
        }
        i.next();
    }
    throw MedRepoException("Nu exista un medicament cu denumirea cautata.\n");
}


//Cauta dupa denumire
//returneaza pozitia medicamentului
//arunca exceptie daca nu exista medicament

int MedRepo:: cauta(string name) {
    int poz = 0;

    IteratorVectorT<medicament> i = allMeds.begin();
    while (i.valid()) {
        if (i.element().getName() == name) {
            return poz;
        }
        i.next();
        poz++;
    }
    throw MedRepoException("Nu exista un medicament cu denumirea cautata.\n");
}


//sterge
//arunca exceptie daca nu exista medicament
//returneaza medicamentul sters

medicament MedRepo::del(const medicament& m) {
    int poz = cauta(m.getName());
    //cauta va arunca exceptie daca medicamentul nu exista

    allMeds.erase(poz);
    return m;
}

//modifica  medicament
//arunca exceptie daca nu exista un medicament cu aceea denumire

void MedRepo:: modify(const medicament& m1, const medicament& m2) {
    int poz = cauta(m1.getName());
    //arunca exceptie daca nu exista elementul

    allMeds.set(poz, m2);
}


//returneaza numarul de elemente din lista
int MedRepo::size() {
    return allMeds.size();
}

//returneaza elementul de pe pozitia poz
medicament MedRepo:: getelem(int poz) {
    return allMeds.get(poz);
}

