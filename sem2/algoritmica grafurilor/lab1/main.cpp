#include <iostream>
#include <fstream>

using namespace std;
ifstream f("input");
int v, m, n1, n2, ma[10][10], i, j, mi[10][10], n, ok, l[0], viz[101]={0}, x;
int md[10][10], maxx=100000;

//parcurgere in adancime

void DFS(int x)
{
    viz[x]=1;
    for(i=1; i<=v; i++)
        if(ma[x][i]==1 && viz[i]==0)
            DFS(i);
}


//Roy-Floyd
void rf()
{
    int k;
    for(k=1; k<=v;k++)
        for(i=1; i<=v; i++)
            for(j=1; j<=v; j++)
                if(i!=j)
                    if(md[i][j]>md[i][k]+md[k][i])
                        md[i][j]=md[i][k]+md[k][i];
}

void afisare()
{
    for(i=1; i<=v; i++)
    {
        for(j=1; j<=v; j++)
            if(md[i][j]!=maxx)
                cout<<md[i][j]<<" ";
            else
                cout<<"inf ";
        cout<<endl;
    }
}

int main()
{

    f >> v;
    f >> m;

    //matrice adiacenta

    for(i=1; i <= v; i++)
        for(j=1; j<=v; j++)
            ma[i][j] = 0;
    while(m > 0)
    {
        f >> n1;
        f >> n2;
        ma[n1][n2] = 1;
        ma[n2][n1] = 1;
        m --;
    }
    for(i=1; i <= v; i++)
    {
        for(j=1; j<=v; j++)
            cout << ma[i][j] << " ";
        cout << endl;
    }

    //noduri izolate


    cout << "nodurile izolate sunt: ";
    for(i=1; i <= v; i++)
    {
        ok=0;
        for(j=1; j<=v; j++)
            if(ma[i][j] == 1)
                ok++;
        if(ok == 0)
        {
            cout << i;
            l[i] = 0;
        }
        else l[i] = ok;
    }
    ok=0;
    for(i=1; i<=v; i++)
        if(l[i] == 0)
            ok++;
    if(ok == 0) cout<<"nu sunt varfuri izolate";
    cout<<endl;

    //graf regular

    ok=0;
    for(i=2; i<=v; i++)
        if(l[i-1] != l[i])
            ok++;
    if(ok == 0)
        cout<<"Graful este regular"<<endl;
    else
        cout<<"Graful nu este regular"<<endl;
/*

    //matrice incidenta

    for(i=1; i <= v; i++)
        for(j=1; j<=m; j++)
            mi[i][j] = 0;
    n = m;
    for(j=1; j<=n;j++)
    {
        f >> n1;
        f >> n2;
        mi[n1][j] = 1;
        mi[n2][j] = 1;
    }
    for(i=1; i <= v; i++)
    {
        for(j=1; j<=m; j++)
            cout << mi[i][j] << " ";
        cout << endl;
    }*/

    //conexitate

    for(i=1; i<=v; i++)
        viz[i]=0;
    int gasit=1;
    DFS(1);
    for(i=1; i<=v; i++)
        if(viz[i]==0)
            gasit=0;
    if(gasit==1)
        cout<<"Graful este conex"<<endl;
    else cout<<"Graful nu este conex"<<endl;

    //matrice distante


    for(i=1; i<=v; i++)
        for(j=1; j<=v; j++)
            md[i][j]=md[j][i]=maxx;

    for(i=1; i<=v; i++)
        for(j=1; j<=v; j++)
            if(ma[i][j]==1)
                md[i][j]=md[j][i]=1;

    cout<<"Matricea distantelor este:"<<endl;
    rf();
    afisare();


    f.close();
    return 0;

}
