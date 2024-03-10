#pragma once

typedef int TElem;

#define CAPACITATE 10

typedef bool(*Relatie)(TElem, TElem);

//in implementarea operatiilor se va folosi functia (relatia) rel (de ex, pentru <=)
// va fi declarata in .h si implementata in .cpp ca functie externa colectiei
bool rel(TElem, TElem);

class IteratorMultime;

class Multime {

	friend class IteratorMultime;

private:
	int cp;
	int lg;
	//vectorul static de elemente de tip TElem (cu spatiu fix de memorare - CAPACITATE)
	TElem* e;
	//vectorul static pentru legaturi
	int* urm;
	//vector static pentru legaturi
	int* prec;
	//referinta catre primul element al listei
	int prim;
	//referinta catre ultimul element al listei
	int ultim;
	//referinta catre primul element din lista spatiului liber
	int primLiber;

	//functii pentru alocarea/dealocarea unui spatiu liber
	//se returneaza pozitia unui spatiu liber in lista
	int aloca();
	//dealoca spatiul de indice i
	void dealoca(int i);
	//functie privata care creeaza un nod in lista inlantuita
	int creeazaNod(TElem e);


public:

	void afisare();

	void redim();
		//constructorul implicit
		Multime();

		//adauga un element in multime
		//returneaza adevarat daca elementul a fost adaugat (nu exista deja in multime)
		bool adauga(TElem e);

		//sterge un element din multime
		//returneaza adevarat daca elementul a existat si a fost sters
		bool sterge(TElem e);

		//verifica daca un element se afla in multime
		bool cauta(TElem elem) const;


		//intoarce numarul de elemente din multime;
		int dim() const;

		//verifica daca multimea e vida;
		bool vida() const;

		//returneaza un iterator pe multime
		IteratorMultime iterator();

		// destructorul multimii
		~Multime();

};

