bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
; avem expreia: (g+5)-a*d
segment data use32 class=data
    a DB 10
    d DB 7
    g DW 110
    x DW 0

; our code starts here
segment code use32 class=code
    start:
        MOV AL, [a] ; AL= a= 10
        MOV AH, [d] 
        MUL AH ; AX= a*d= 70
        MOV BX, [g] ; BX= g= 110
        MOV CX, 5
        ADD BX, CX ; BX= g+5 =115
        SUB BX, AX ; BX= BX-AX = 115-70= 45
        MOV [x], BX ; x= (g+5)-a*d
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
