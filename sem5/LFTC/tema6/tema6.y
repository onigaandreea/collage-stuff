%{
#include <stdio.h>
#include <string.h>

void initASM() {
    printf("bits 32\nglobal start\nextern exit, printf, scanf\nimport exit msvcrt.dll\nimport printf msvcrt.dll\nimport scanf msvcrt.dll\n");
}

void initDataSegment() {
    printf("segment  data use32 class=data\n\tformat  db \"%%d\", 0\n");
}

void initCodeSegment() {
    printf("segment  code use32 class=code\n\tstart:\n");
}

void initEnd() {
    printf("\t\tpush dword 0\n\t\tcall [exit]\n");
}

void applyOp(int opCode) {
    if (opCode == 1) {
        printf("\t\tADD EAX, EBX\n");
    } else if (opCode == 2) {
        printf("\t\tSUB EAX, EBX\n");
    } else if (opCode == 3) {
        printf("\t\tIMUL EBX\n");
    } else if (opCode == 4) {
        printf("\t\tCDQ\n");
        printf("\t\tIDIV EBX\n");
    }
}

%}

%union {
    char value[255];
    int opCode;
}

%locations
%token CIN
%token CONST
%token COUT
%token GTGT
%token ID
%token INCLUDE
%token INT
%token IOSTREAM
%token LTLT
%token MAIN
%token NAMESPACE
%token RETURN
%token STD
%token USING

%type <value> CONST ID evaluabil_atomic
%type <opCode> operator_binar

%%

program : INCLUDE '<' IOSTREAM '>' USING NAMESPACE STD ';'
          INT MAIN '(' ')' '{'
              { initASM(); }
              { initDataSegment(); }
              lista_declarari
              { initCodeSegment(); }
              lista_instructiuni
              RETURN CONST ';'
              { initEnd(); }
          '}';

lista_declarari : declarare ';' lista_declarari | /* empty */;

declarare : INT ID { printf("\t%s dd 0\n", $2); };

lista_instructiuni : instructiune ';' lista_instructiuni | /* empty */;

instructiune : afisare | atribuire | citire;

afisare : COUT LTLT evaluabil_atomic
{
    printf("\t\tpush dword %s\n", $3);
    printf("\t\tpush dword format\n");
    printf("\t\tcall [printf]\n");
    printf("\t\tadd esp, 8\n");
};

atribuire : ID '=' evaluabil { printf("\t\tMOV [%s], EAX\n", $1); };

evaluabil : evaluabil_atomic | evaluabil_atomic operator_binar evaluabil_atomic { applyOp($2); };

evaluabil_atomic : CONST { strcpy($$, $1); }
                | ID { $$[0] = '['; strcpy($$ + 1, $1); $$[strlen($$) + 1] = 0; $$[strlen($$)] = ']'; };

operator_binar : '+' { $$ = 1; }
              | '-' { $$ = 2; }
              | '*' { $$ = 3; }
              | '/' { $$ = 4; };

citire : CIN GTGT ID
{
    printf("\t\tpush dword %s\n", $3);
    printf("\t\tpush dword format\n");
    printf("\t\tcall [scanf]\n");
    printf("\t\tadd esp, 8\n");
};
%%

