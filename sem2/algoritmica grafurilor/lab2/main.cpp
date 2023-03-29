#include <iostream>
#include <fstream>
#include <queue>
#define inf 100000000

using namespace std;
ifstream f("input");



int n, n1, n2, ma[10][10], i, j, m, l[100], v[100], p[100];
int mi[10][10], maxx=100000;

//Roy-Floyd
void rf()
{
    int k;
    for(k=1; k<=n; k++)
        for(i=1; i<=n; i++)
            for(j=1; j<=n; j++)
                if(mi[i][j]>mi[i][k]+mi[k][j])
                    mi[i][j]=1;
}

void afisare()
{
    for(i=1; i<=n; i++)
    {
        for(j=1; j<=n; j++)
            if(mi[i][j]!=maxx)
                cout<<"1 ";
            else
                cout<<"0 ";
        cout<<endl;
    }
}

void Algorimul_lui_Moore(int u)
{
    int x;
    queue<int> q;
    l[u]=0;
    for(int i=1;i<=n;i++)
        if(i!=u)
            l[i]=inf;
    q.push(u);
    while(!q.empty())
    {
        x = q.front();
        q.pop();
        v[x]=1;
        for(int i=1;i<=n;i++)
        {
            if(ma[x][i]==1)
            {
                if(l[i]>l[x]+1)
                {
                    p[i]=x;
                    l[i]=l[x]+1;
                    if(!v[i])
                        q.push(i);
                }
            }
        }
    }
}

void Moore_drum(int v)
{
    int k, d[15], i;
    if(l[v]==inf)
        cout<<"infinit"<<'\n';
    else
    {
        k=l[v];
        d[k]=v;
        while(k!=0)
        {
            d[k-1]=p[d[k]];
            k--;
        }
        for(i=0;i<=l[v];i++)
            cout<<d[i]<<" ";
        cout<<endl;
        cout<<"drumul are lungimea "<<l[v];
    }

}


int main()
{
    int a,b;

    f>>n;
    f>>m;

    for(i=1; i<=n; i++)
        for(j=1; j<=n; j++)
            ma[i][j]=0;

    while(m)
    {
        f>>n1;
        f>>n2;
        ma[n1][n2]=1;
        m--;
    }

    for(i=1; i<=n; i++)
    {
        for(j=1; j<=n; j++)
            cout<<ma[i][j]<<" ";
        cout<<endl;
    }

    for(i=1; i<=n; i++)
        for(j=1; j<=n; j++)
            mi[i][j]= mi[j][i]= maxx;

    for(i=1; i<=n; i++)
        for(j=1; j<=n; j++)
            if(ma[i][j]==1)
                mi[i][j]=1;


    rf();
    afisare();

    cout<<"dati nodul sursa pentru drumul minim:";
    cin>>a;
    cout<<endl<<"dati nodul destinatie:";
    cin>>b;
    cout<<"drumul minim este: ";
    Algorimul_lui_Moore(a);
    Moore_drum(b);

}
