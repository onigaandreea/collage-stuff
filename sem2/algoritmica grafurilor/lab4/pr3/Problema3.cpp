#include <queue>
#include <iostream>
#include <fstream>
#include <vector>
#include <string>
using namespace std;

ifstream fin("fisier.in");
ofstream fout("fisier.out");

struct Nod
{
	char c;
	int fr;
	Nod* st;
	Nod* dr;
};
using VN = vector<Nod*>;

struct Compare{
	bool operator()(Nod* p1, Nod* p2)
	{
		if (p1->fr == p2->fr)
				return p1->c > p2->c;
		return p1->fr > p2->fr;
	}
};

struct Frecv
{
	char c;
	int fr;
};
using VF = vector<Frecv>;
using VI = vector<int>;

VF A;
VI C,P;
priority_queue<Nod*, VN, Compare> Q;
string s;

int Find(VF A, char s)
{
	for(int i=0;i<A.size();i++)
		if (A[i].c == s)
			return i;
	return -1;
}

void Citire()
{
	getline(fin, s);
	for (int i = 0; i < s.size(); i++)
	{
		int poz = Find(A, s[i]);
		if(poz != -1)
			A[poz].fr++;
		else
		{
			Frecv aux;
			aux.c = s[i];
			aux.fr = 1;
			A.emplace_back(aux);
		}
	}
}

void Huffman(VF A)
{
	for (auto& y : A)
	{
		Nod* aux = new Nod;
		aux->c = y.c;
		aux->fr = y.fr;
		aux->st = nullptr;
		aux->dr = nullptr;
		Q.push(aux);
	}

	while (Q.size()>1)
	{
		Nod* x = Q.top();
		Q.pop();
		Nod* y = Q.top();
		Nod* z = new Nod;
		Q.pop();
		z->st = x;
		z->dr = y;
		z->c = '/';
		z->fr = x->fr + y->fr;
		Q.push(z);
	}
}

void DFS(Nod* x,string s,char c)
{
	if (x == nullptr)
		return;
	if (x->c == c)
		fout << s;
	DFS(x->st, s + '0', c);
	DFS(x->dr, s + '1', c);
}

void Afisare()
{
	fout << A.size() << '\n';
	for (auto& y : A)
		fout << y.c << " " << y.fr << '\n';
	for (int i = 0; i < s.size(); i++)
		DFS(Q.top(), "", s[i]);
}

int main()
{
	Citire();
	Huffman(A);
	Afisare();
}