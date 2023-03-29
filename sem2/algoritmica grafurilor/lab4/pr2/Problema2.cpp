#include <iostream>
#include <vector>
#include <fstream>

using namespace std;
using VI = vector<int>;

ifstream fin("fisier.in");
ofstream fout("fisier.out");

VI T, K;

int n;

void Citire()
{
	fin >> n;
	T = VI(n + 1);
	K = VI(n);
	for (int i = 0; i < n; i++)
		fin >> K[i];
}

int Minim()
{
	for (int i = 0; i <= n; i++)
		if (find(K.begin(), K.end(), i) != K.end());
		else
			return i;
}

void Afisare(VI V)
{
	fout << V.size() << '\n';
	for (auto& y : V)
		fout << y << " ";
}

void Decod_Prufer()
{
	for (int i = 0; i < n; i++)
	{
		int x = K[i];
		int y = Minim();
		T[y] = x;
		K.emplace_back(y);
		K[i] = -1;
	}
	T[Minim()] = -1;
}

int main()
{
	Citire();
	Decod_Prufer();
	Afisare(T);
}