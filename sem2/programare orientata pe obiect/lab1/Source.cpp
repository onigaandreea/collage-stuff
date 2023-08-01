#include <stdio.h>
#define _CRT_SECURE_NO_WARNINGS
#include <math.h>


void desc_suma();
int isPrim(int nr);
void generate_prime();
void n_prime();
void subunitar();
void meniu();

int main()
{
	meniu();
}
/*
 Determina toate reprezentarile posibile a unui numar natural ca suma de numere naturale
   consecutive
   param- nu avem
   return- nu avem
*/
void desc_suma()
{
	int nr, i, j, s;
	printf("%s", "Dati un numar: ");
	scanf_s("%d", &nr);
	for (i = 1; i < nr; i++)
	{
		s = i;
		for (j = i + 1; j < nr; j++)
		{
			s += j;
			if (s >= nr)
				break;
		}
		if (s == nr)
		{
			printf("%d %s %d", nr, " = ", i);

			for (int k = i + 1; k <= j; k++)
				printf("%s %d", " + ", k);

			printf("%s", " \n");
		}
	}
}

void meniu()
{
	int chosen = 1;
	while (chosen)
	{
		int cmd;
		printf("%s", "Avem urmatoarele optiuni: \n");
		printf("%s", "1. Reprezentati un numar in toate sumele de numere consecutive. \n");
		printf("%s", "2. Toate numerele prime mai mici decat un numar dat. \n");
		printf("%s", "3. Primele n numere prime. \n");
		printf("4. n cifre zecimale ale unei fractii subunitare. \n");
		printf("%s", "Comanda este: \n");
		scanf_s("%d", &cmd);
		if (cmd == 1)
			desc_suma();
		else if (cmd == 2)
			generate_prime();
		else if (cmd == 3)
			n_prime();
		else if (cmd == 4)
			subunitar();
		else
		{
			chosen = 0;
		}
	}
}
//Verifica daca un numar este prim//

int isPrim(int nr)
{
	if (nr == 2)
		return 1;
	int ok = 0;
	for (int d = 2; d <= nr/2; d ++)
		if (nr % d == 0)
			ok = 1;
	if (ok == 0)
		return 1;
	else return 0;
}

//Genereaza toate numerele prime mai mici decat n dat//
void generate_prime()
{
	int n;
	printf("%s", "Dati un numar: ");
	scanf_s("%d", &n);
	for (int i = 2; i < n; i++)
		if (isPrim(i) == 1)
			printf("%d %s", i, " ");
	printf("%s", "\n");
}


//Genereaza primele n numere prime//
void n_prime()
{
	int n;
	printf("Dati un numar: ");
	scanf_s("%d", &n);

	printf("%s %d %s", "Primele", n, "numere prime sunt: ");
	int i = 2;
	while (n)
	{
		if (isPrim(i) == 1)
		{
			printf("%d %s", i, " ");
			n--;
		}
		i++;
	}
	printf("\n");
}

//Afiseaza primele n cifre zecimale din scrierea unui numar subunitar//
void subunitar()
{
	int k, m, n, d;
	float nr, a, b;
	printf("Dati k si m (k<m): ");
	scanf_s("%d %d", &k, &m);
	if (k >= m)
	{
		printf("Fractia nu este subunitara dati alte 2 numere: ");
		scanf_s("%d %d", &k, &m);
	}
	printf("Dati numarul de cifre zecimale: ");
	scanf_s("%d", &n);
	a = float(k);
	b = float(m);
	nr = a / b;
	while (n)
	{
		nr = nr * 10;
		d = int(nr);
		nr = nr - d;
		printf("%d ", d);
		n--;

	}
	printf("\n");

}