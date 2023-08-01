#include <iostream>
#include <fstream>
#include <queue>
#include <vector>
using namespace std;
using VI = vector<int>;
using VVI = vector<VI>;

ifstream fin("fisier.in");
ofstream fout("fisier.out");

VVI G;
VI P,D;
int n,m;

void Citire()
{
    int x, y, k;
    fin >> n >> m;
    G = VVI(n);
    for (int i = 0; i < n; i++)
        G[i] = VI(n, 0);
    for (int i = 0; i < m; i++)
    {
        fin >> x >> y >> k;
        G[x][y] = k;
    }
}

int BFS(int s,int t)
{
    P = VI(n, 0);
    D = VI(n);
    queue<int> Q;
    Q.push(s);
    P[s] = 1;
    D[s] = -1;

    while (!Q.empty())
    {
        int x = Q.front();
        Q.pop();
        for(int i=0;i<n;i++)
            if (!P[i] && G[x][i] > 0)
            {
                D[i] = x;
                if (i == t)
                    return 1;
                Q.push(i);
                P[i] = 1;
            }
    }
    return 0;
}

int FordFulkerson(int s, int t)
{
    int max = 0;
    while (BFS(s, t))
    {
        int cnt = INT_MAX;
        int i = t;
        while (i != s)
        {
            cnt = min(cnt, G[D[i]][i]);
            i = D[i];
        }
        i = t;
        while (i != s)
        {
            G[D[i]][i] -= cnt;
            G[i][D[i]] += cnt;
            i = D[i];
        }
        max += cnt;
    }
    return max;
}

void Afisare()
{
    fout << FordFulkerson(0, n-1);
}

int main()
{
    Citire();
    Afisare();
    return 0;
}