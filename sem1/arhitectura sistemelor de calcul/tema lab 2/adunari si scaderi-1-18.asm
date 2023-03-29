bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    a DB 4
    b DB 3
    c DB 2
    d DB 9
    x DB 0

; our code starts here
; avem expresia d-(a+b)+c
segment code use32 class=code
    start:
        
        MOV AL, [d] ; AL= d =9
        MOV BL, [a] ; BL= a= 4
        ADD BL, [b] ; BL= a+b= 4+3= 7
        SUB AL, BL ; AL= AL-BL= d-(a+b)= 9-7=2
        ADD AL, [c] ; AL= d-(a+b)+c= 2+2=4
        MOV [x], AL ; x=d-(a+b)+c
        
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
