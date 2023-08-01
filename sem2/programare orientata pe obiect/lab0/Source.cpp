#include <stdio.h>

int suma(int s, int nr);

void proces_sum();

int main()
{
	printf("%s", "Hello World \n");
	proces_sum();
}

int suma(int s, int nr)
{
	return s + nr;
}

void proces_sum()
{
	int n, a, s = 0;
	scanf_s("%d", &n);
	for (int i = 0; i < n; i++)
	{
		scanf_s("%d", &a);
		s = suma(s, a);
	}
	printf("%s", "Suma este: ");
	printf("%d", s);
}


