bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
; avem expresia: 200-[3*(c+b-d/a)-300] 
segment data use32 class=data
    a DB 10
    b DB 50
    c DB 100
    d DW 100
    x DW 0

; our code starts here
segment code use32 class=code
    start:
        MOV AL, [c] ; AL= c= 100
        ADD AL, [b] ; AL= c+b= 150
        
        MOV BX, [d] ; BX= d= 100
        MOV AH, [a] 
        DIV AH ; BL= d/a= 10 si BH= d%a =0
        
        SUB AL, BL ; AL= AL-BL =c+b-d/a= 140
        MOV AH, 3
        MUL AL ; AX= AH*AL=3*(c+b-d/a)= 420
        SUB AX, 300 ; AX= 3*(c+b-d/a)-300=120
        MOV BX, 200 ;  BX= 200
        SUB BX, AX ; BX= 200-[3*(c+b-d/a)-300]= 200-120= 80
        MOV [x], BX ; x= 200-[3*(c+b-d/a)-300]
        
        push dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
