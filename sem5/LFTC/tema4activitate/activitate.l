%{
#include <stdio.h>
#include <string.h>
#include "parser.tab.h"

int nrCuv=0;
int nrDeriv=0;

int wasSuccessful=1;

void countW(char* atom, int value){
	
	if(value==0){
		nrCuv++;
        nrDeriv++;
	}
    else{
        nrCuv++;
    }
}	

void printVals(){
	printf("Numar cuvinte: %d\n", nrCuv);
	printf("Numar cuvinte derivate: %d\n", nrDeriv);
}
%}

%option noyywrap

CEREMONIE [a-z]*[ceremonie][a-z]*  
WORD [a-z]+  

%%

{CEREMONIE} {countW(yytext, 0); return CEREMONIE;}
{WORD} {countW(yytext,1); return WORD;}
.           { /* ignora caracterele necunoscute */ }

%%

yyerror()
{
	if(wasSuccessful){
		printf("Error");
		wasSuccessful=0;
	}
}

int main(int argc, char** argv) {
	
	yyparse();

	printVals();
	
	return 0;
}
