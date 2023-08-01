#include <iostream>
#include <fstream>
#include <vector>
using namespace std;
using VI = vector<int>;
using VVI = vector<VI>;

ifstream fin("eulerian.in");
ofstream fout("eulerian.out");

int n;
VI L;
VVI G;

void Eulerian(int k)
{
    for (int i = 0; i < n; i++)
        if (G[k][i] != 0)
        {
            G[k][i] = G[i][k] = 0;
            Eulerian(i);
        }
    L.emplace_back(k);
}

void Citire()
{
    int x, y, m;
    fin >> n >> m;
    G = VVI(n);
    for (int i = 0; i < n; i++)
        G[i] = VI(n, 0);
    for (int i = 1; i <= m; i++)
    {
        fin >> x >> y;
        G[x][y] = G[y][x] = 1;
    }
}

void Afisare()
{
    for (auto& y : L)
        fout << y << " ";
}

int main()
{
    Citire();
    Eulerian(0);
    Afisare();
    return 0;
}
