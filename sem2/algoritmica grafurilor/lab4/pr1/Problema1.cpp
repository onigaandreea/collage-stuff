//CODARE PRUFER

#include <iostream>
#include <vector>
#include <fstream>

using namespace std;
using VI = vector<int>;

ifstream fin("fisier.in");
ofstream fout("fisier.out");

VI T, K, P;

const int INF = 0x3f3f3f3f;
int n, min1, root;

void Citire()
{
	fin >> n;
	T = VI(n); //Vectorul de tati
	P = VI(n, 0); //vector pt noduri vizitate, initializat cu 0
	for (int i = 0; i < n; i++)
	{
		fin >> T[i];
		if (T[i] == -1)
			root = i;
	}
}

void DFS(int x)
{
	P[x] = 1; //vizitam x
	int OK = 0;
	for (int i = 0; i < n; i++)
		if (T[i] == x && !P[i])
		{
			DFS(i);
			OK = 1; //am gasit frunza
		}
	if (!OK && x < min1)
		min1 = x;
}

int search_min_Leaf(int x)
{
	min1 = INF;
	P = VI(n, 0);
	DFS(x);
	if (min1 != x) //verificam daca am ajuns la final
		return min1;
	return INF;
}

void Prufer(int root)
{
	while (search_min_Leaf(root) != INF)
	{
		K.emplace_back(T[min1]);
		T[min1] = -1;
	}
}

void Afisare(VI V)
{
	fout << V.size() << '\n';
	for (auto& y : V)
		fout << y << " ";
}

int main()
{
	Citire();
	Prufer(root);
	Afisare(K);
}