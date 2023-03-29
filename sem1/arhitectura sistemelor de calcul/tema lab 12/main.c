#include <stdio.h>
#include <string.h>


int conversie(char *s); //declaram procedura

int main()
{
    char s[100]; //declaram parametrii
    gets(s); //citim sirul de 0 si 1
    while(s[0]!='2') //conditia prin care se citesc numerele pana se introduce caracterul 2
    {
        // printf("%s", s);
        int nr = conversie(s); //obtinerea numarului de afisat
        printf("%o \n", nr); //afisarea in baza 8 a numarului obtinut anterior
        gets(s); //citirea unui nou sir de 1 si 0
        printf("%s \n",s);  
    }
}
     