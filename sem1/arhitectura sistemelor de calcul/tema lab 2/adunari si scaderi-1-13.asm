bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    a DB 10
    b DB 2
    c DB 3
    d DB 4
    x DB 0

; our code starts here
; rezolvati expresia a+b-c+d-(a-d) 
segment code use32 class=code
    start:
        
        MOV AL, [a] ; AL=a=10
        ADD AL, [b] ; AL=a+b =10+2=12
        SUB AL, [c] ; AL=a+b-c= 12-3=9
        ADD AL, [d] ; AL=a+b-c+d=9+4= 13
        MOV BL, [a] ; BL=a=10
        SUB BL, [d] ; BL= a-d= 10-4=6
        SUB AL, BL ; AL=AL-BL=a+b-c+d-(a-d) = 13-6= 7
        MOV [x], AL; x=a+b-c+d-(a-d)= 7

        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
