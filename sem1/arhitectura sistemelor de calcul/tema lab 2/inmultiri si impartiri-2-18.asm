bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
; avem expresia:f+(c-2)*(3+a)/(d-4)
segment data use32 class=data
    a DB 1
    c DB 10
    d DB 14
    f DW 20
    x DW 0

; our code starts here
segment code use32 class=code
    start:
        MOV AL, [c] ; AL= c= 10
        SUB AL, 2 ; AL=AL-2=8
        MOV AH, 3 ; AH=3
        ADD AH, [a] ; AH=AH+a=3+1=4
        MUL AH ; AX=AL*AH= 8*4= 32
        MOV BL, [d] ; BL=d=14
        SUB BL, 4 ; BL=BL-4=14-4=10
        DIV BL ; AL=AX/BL=3 si AH=AX%BL=2
        MOV BX, [f] ; BX=f=20
        MOV AH, 0 ; AX= 3
        ADD BX, AX ; BX=BX+AX= 20+3=23
        MOV [x], BX ; x=f+(c-2)*(3+a)/(d-4)= 23
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
