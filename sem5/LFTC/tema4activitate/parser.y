%{
#include <stdio.h>
%}

%token CEREMONIE
%token WORD

%%

input: word
     | input word
     | word '\n' 
     ;

word: CEREMONIE 
    | WORD
    ;      

%%

