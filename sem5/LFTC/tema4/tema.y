%{
#include <stdio.h>
%}

%token AND
%token CIN
%token CMATH
%token CONST
%token COUT
%token DOUBLE
%token ENDL
%token EQ
%token GTGT
%token ID
%token IF
%token INCLUDE
%token INT
%token IOSTREAM
%token LTLT
%token MAIN
%token MSG
%token NAMESPACE
%token NE
%token OR
%token RETURN
%token STD
%token USING
%token WHILE
%token OPEN_PARAN
%token OPEN_BRAK
%token CLOSE_PARAN
%token CLOSE_BRAK

%%

program	:	intro main
		;

intro   :   lista_biblioteci
            USING NAMESPACE STD ';'
        ;

lista_biblioteci    :   biblioteca
                    |   biblioteca lista_biblioteci
                    ;

biblioteca  :   INCLUDE '<' nume_b '>'
            ;

nume_b  :   IOSTREAM
        |   CMATH
        ;

main    :   INT MAIN OPEN_PARAN CLOSE_PARAN OPEN_BRAK
                bloc_instr
                RETURN CONST ';'
            CLOSE_BRAK
        ;

bloc_instr  :   lista_decl lista_instr 
            ;

lista_decl  :   type lista_var ';'
            ;

type    :   INT
        |   DOUBLE 
        ;

lista_var   :   ID
            |   ID ',' lista_var 
            ;

lista_instr     :   instr ';'
				|	instr ';' lista_instr 
				;

instr	:	scriere 
		| 	atribuire 
		| 	citire 
		| 	selectie
		| 	ciclare
		;

scriere	:	COUT LTLT mesaj
        ;
		
mesaj   :   MSG 
        |   MSG LTLT ID LTLT ENDL 
        ;

citire	:	CIN GTGT ID 
		;

atribuire	:	ID '=' expr
			;

expr	:	CONST op expr
		| 	ID
		| 	ID op expr
        |   CONST
		;

op	:	'+' | '-' | '!=' | '*' | '>' | '<' | '%'
	;
	
selectie	:	IF OPEN_PARAN expr CLOSE_PARAN OPEN_BRAK 
					lista_instr
				CLOSE_BRAK
			;

ciclare	:	WHILE OPEN_PARAN expr CLOSE_PARAN OPEN_BRAK
				lista_instr 
			CLOSE_BRAK
		;

%%