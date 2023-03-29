bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
; avem expresia: [(a*b)-d]/(b+c)]
segment data use32 class=data
    
    a DB 100
    b DB 70
    c DB 30
    d DW 1000
    x DB 0

; our code starts here
segment code use32 class=code
    start:
        MOV AL, [a] ; AL = a= 100
        MUL BYTE [b] ; AX = a*b= 7000
        SUB AX, [d] ; AX = (a*b)-d= 6000
        MOV BL, [b] ; BL = b=70
        ADD BL, [c] ; BL =b+c= 100
        DIV BL ; AL= (catul) [(a*b)-d]/(b+c)]= 60 si AH= (restul)= 0
        MOV [x], AL ; x= [(a*b)-d]/(b+c)]
        
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
