#include <iostream>
#include <fstream>
#include <vector>
#include <queue>
using namespace std;
using PI = pair<int, int>;
using VB = vector<bool>;
using VP = vector<PI>;
using VVP = vector<VP>;
using VI = vector<int>;

ifstream fin("prim.in");
ofstream fout("prim.out");

const int INF = 0x3f3f3f3f;
VVP G;
VI D, T;
VB V;
int n, m, S;

void Citire()
{
    int x, y, c;
    fin >> n >> m;
    G = VVP(n + 1);
    D = VI(n + 1, INF);
    T = VI(n + 1);
    V = VB(n + 1, 0);
    for (int i = 1; i <= m; i++)
    {
        fin >> x >> y >> c;
        G[x].emplace_back(make_pair(c, y));
        G[y].emplace_back(make_pair(c, x));
    }
}

void Afisare()
{
    fout << S << '\n' << n - 1 << '\n';
    for (int i = 1; i < n; i++)
        fout << T[i] << " " << i << '\n';
}

void Algoritmul_Lui_Prim(int x)
{
    priority_queue<PI, VP, greater<PI>> Q;
    for (auto y : G[x])
    {
        D[y.second] = y.first;
        T[y.second] = x;
        Q.push({ y });
    }
    V[x] = 1;
    T[x] = 0;
    for (int i = 1; i < n; i++)
    {
        PI p;
        do
        {
            p = Q.top();
            Q.pop();
        } while (V[p.second]);
        V[p.second] = 1;
        S += p.first;
        for (auto y : G[p.second])
            if (V[y.second] == 0 && D[y.second] > y.first)
            {
                D[y.second] = y.first,
                    T[y.second] = p.second,
                    Q.push(y);
            }
    }
}

int main()
{
    Citire();
    Algoritmul_Lui_Prim(0);
    Afisare();
}
