#pragma once

#define CAPACITATE 60000;
#define NIL -60000;

typedef int TElem;

typedef bool(*Relatie)(TElem, TElem);

//in implementarea operatiilor se va folosi functia (relatia) rel (de ex, pentru <=)
// va fi declarata in .h si implementata in .cpp ca functie externa colectiei
bool rel(TElem, TElem);


class IteratorColectie;


class Colectie
{
	friend class IteratorColectie;

private:
	int rad; //radacina arborelui
	static const int cp = CAPACITATE;
	int liber = NIL;
	TElem elems[cp];
	int st[cp];
	int dr[cp];
	int lg;
	int primLiber;
	void actualizarePrimLiber();
	int minim(int nod);

public:
		//constructorul implicit
		Colectie();

		//adauga un element in colectie
		void adauga(TElem e);

		int adauga_rec(int crt, TElem e);

		//sterge o aparitie a unui element din colectie
		//returneaza adevarat daca s-a putut sterge
		bool sterge(TElem e);

		int sterge_rec(int nod, TElem e, int& gasit);

		//verifica daca un element se afla in colectie
		bool cauta(TElem elem);

		//returneaza numar de aparitii ale unui element in colectie
		int nrAparitii(TElem elem) const;

		int ștergeToateElementeleRepetitive();
		//intoarce numarul de elemente din colectie;
		int dim() const;

		//verifica daca colectia e vida;
		bool vida() const;

		//returneaza un iterator pe colectie
		IteratorColectie iterator() const;

		// destructorul colectiei
		~Colectie();

};

