%{
#define MAX_ATOM_SIZE 8
#define MAX_FIP_SIZE 300
#define MAX_TS_SIZE 300

#include <stdio.h>
#include <string.h>

int wasSuccessful=1;
int lastWasSeparator=1;

typedef struct {
	int value;
	int code;
}nodeFIP;

int sizeFIP=0;
nodeFIP FIP[MAX_FIP_SIZE];

typedef struct{
	char value[MAX_ATOM_SIZE];
}nodeTS;

int sizeTS=0;
nodeTS TS[MAX_TS_SIZE];

void printFIP(){
	printf("FIP:\n");
	int i;
	for(i=0;i<sizeFIP;i++){
		if(FIP[i].value<2){
			printf("%d --> %d\n", FIP[i].value, FIP[i].code);				
		}
		else{
			printf("%d\n", FIP[i].value);
		}
	}
}

void printTS(){
	printf("TS:\n");
	int i;
	for(i=0;i<sizeTS;i++){
		printf("%d:%s\n", i,TS[i].value);
	}
}

void printError(){
	if(wasSuccessful){
		printf("Error at line %d\n",yylineno);
		wasSuccessful=0;
	}
}

int getTSCode(char* atom) {
    int i;

    // Verifică dacă atomul există deja în tabel
    for (i = 0; i < sizeTS; i++) {
        if (strcmp(atom, TS[i].value) == 0) {
            return i;  // Atomul există deja, returnează indicele existent
        }
        if (strcmp(atom, TS[i].value) < 0) {
            break;  // Atomul nu există, dar trebuie să fie adăugat aici
        }
    }

    // Mută atomii și face loc pentru noul atom
    for (int j = sizeTS; j > i; j--) {
        strcpy(TS[j].value, TS[j - 1].value);
    }

    strcpy(TS[i].value, atom);
    sizeTS++;

    return i;
}


void addToFIP(int value,char* atom, int isSeparator){
	if((!lastWasSeparator) && (!isSeparator)){
		printError();
		return;
	}
	if(value==0 && strlen(atom)>8){
		printError();
		return;
	}
	
	FIP[sizeFIP].value=value;
	if(value<2){
		int code = getTSCode(atom);
		FIP[sizeFIP].code=code;
	}
	sizeFIP++;
	
	lastWasSeparator=isSeparator;
}	
%}

%option noyywrap
%option yylineno

ID [a-zA-Z]+

BOOL_CONST "true"|"false"

DIGIT [0-9]

FLOAT_CONST [1-9]({DIGIT}*)[.]({DIGIT}+)
INT_CONST [1-9]({DIGIT})*|[0]
TEXT_CONST (["][:a-zA-Z ]+["])

CONST {FLOAT_CONST}|{INT_CONST}

%%

"#include" {addToFIP(2,yytext,0);}
"iostream" {addToFIP(3,yytext,0);}
"using" {addToFIP(4,yytext,0);}
"namespace" {addToFIP(5,yytext,0);}
"std" {addToFIP(6,yytext,0);}
"int" {addToFIP(7,yytext,0);}
"main" {addToFIP(8,yytext,0);}
"while" {addToFIP(9,yytext,0);}
"double" {addToFIP(10,yytext,0);}
"cout" {addToFIP(11,yytext,0);}
"cin" {addToFIP(12,yytext,0);}
"if" {addToFIP(13,yytext,0);}
"endl" {addToFIP(14,yytext,0);}
"return" {addToFIP(15,yytext,0);}
"cattimp" {addToFIP(37,yytext,0);}
"executa" {addToFIP(38,yytext,0);}
"sfcattimp" {addToFIP(39,yytext,0);}

"<<" {addToFIP(16,yytext,1);}
">>" {addToFIP(17,yytext,1);}
"!=" {addToFIP(18,yytext,1);}
"==" {addToFIP(19,yytext,1);}
"&&" {addToFIP(20,yytext,1);}
"||" {addToFIP(21,yytext,1);}
"+" {addToFIP(22,yytext,1);}
"-" {addToFIP(23,yytext,1);}
"*" {addToFIP(24,yytext,1);}
"/" {addToFIP(25,yytext,1);}
"%" {addToFIP(26,yytext,1);}
"<" {addToFIP(27,yytext,1);}
">" {addToFIP(28,yytext,1);}
"=" {addToFIP(29,yytext,1);}
"!" {addToFIP(30,yytext,1);}

"(" {addToFIP(31,yytext,1);}
")" {addToFIP(32,yytext,1);}
"{" {addToFIP(33,yytext,1);}
"}" {addToFIP(34,yytext,1);}
";" {addToFIP(35,yytext,1);}
"," {addToFIP(36,yytext,1);}

{CONST} {addToFIP(1,yytext,0);}
{ID} {addToFIP(0,yytext,0);}

[ \t\n\r] {lastWasSeparator=1;}
{TEXT_CONST} {lastWasSeparator=1;}

. {printError();}

%%
int main(int argc, char** argv) {
	//trecere pt construire TS
	yylex();

	// Resetare variabile
	yylineno = 0;
    sizeFIP = 0;
    wasSuccessful = 1;
    lastWasSeparator = 1;

	fseek(yyin, 0, SEEK_SET);

    // A doua trecere: Construirea FIP
    yylex();
	
	if(wasSuccessful){
		printFIP();
		printTS();
	}
	
	return 0;
}