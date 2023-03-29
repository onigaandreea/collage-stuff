bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
; avem expresia: (a-b-c)+(a-c-d-d)
segment data use32 class=data
    a DW 400
    b DW 150
    c DW 200
    d DW 90
    x DW 0

; our code starts here
segment code use32 class=code
    start:
        MOV AX, [a] ; AX = a= 400
        SUB AX, [b] ; AX = a-b= 400-150= 250
        SUB AX, [c] ; AX = a-b-c= 250-200= 50
        MOV BX, [a] ; BX = a= 400
        SUB BX, [c] ; BX = a-c= 400-200=200
        SUB BX, [d] ; BX = a-c-d= 200-90= 110
        SUB BX, [d] ; BX = a-c-d-d= 110-90= 20
        ADD AX, BX ; AX = AX +BX= (a-b-c)+(a-c-d-d)= 50+20= 70
        MOV [x], AX; x=(a-b-c)+(a-c-d-d) 
        
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
