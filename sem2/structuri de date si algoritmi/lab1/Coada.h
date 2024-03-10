#pragma once
using namespace std;

typedef int TElem;

class Coada
{
	private:
		/* aici reprezentarea */
		int cp; //capacitatea
		int fata, spate; //indicii pentru fata si spate
		TElem *elem; //elementul de tip TElem
	public:
		//redimensionam vectorul, daca se depaseste capacitatea
		void redim();

		//constructorul implicit
		Coada();

		//adauga un element in coada
		void adauga(TElem);

		//acceseaza elementul cel mai devreme introdus in coada 
		//arunca exceptie daca coada e vida
		TElem element() const;

		//sterge elementul cel mai devreme introdus in coada si returneaza elementul sters (principiul FIFO)
		//arunca exceptie daca coada e vida
		TElem sterge();

		//verifica daca coada e vida;
		bool vida() const;


		// destructorul cozii
		~Coada();
};
