#include <iostream>
#include <fstream>

const int maxi=15000;
int c[20][20],d[20],t[20],p[20],n,m,s;

using namespace std;
ifstream f("d-in");
ofstream g("d-out");

void citire()
{
    int i,j,x,y,cost;
    f>>n>>m>>s;
    for(i=0;i<n;i++)
        for(j=0;j<n;j++)
            if(i==j) c[i][j]=0;
            else c[i][j]=maxi;
    for(i=1;i<=m;i++)
    {
        f>>x>>y>>cost;
        c[x][y]=cost;
    }
}

void dijkstra(int s)
{   int i,j,k,mini;
    for(i=0;i<n;i++)
    {
        d[i]=c[s][i];
        if(i!=s && d[i]!=maxi) t[i]=s;
    }
    p[s]=1;
    for(k=1;k<n;k++)
    {
        mini=maxi;
        for(i=0;i<n;i++)
            if(!p[i] && d[i]<mini)
            {
                mini=d[i]; j=i;
            }
        for(i=0;i<n;i++)
            if(!p[i])
                if(d[i]>d[j]+c[j][i])
                {
                    d[i]=d[j]+c[j][i];
                    t[i]=j;
                }
        p[j]=1;
    }
}

void drum(int i)
{
    if(t[i]) drum(t[i]);
    g<<i<<" ";
}

int main()
{
    citire();
    dijkstra(s);
    for(int i=0;i<n;i++)
    {
        if(d[i]==maxi) g<< "INF ";
        else g<<d[i]<<" ";
    }
    return 0;
}
