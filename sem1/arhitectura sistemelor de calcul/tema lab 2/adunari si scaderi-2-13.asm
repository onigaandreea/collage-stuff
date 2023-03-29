bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    
    a DW 100
    b DW 50
    c DW 20
    d DW 40
    x DW 0

; our code starts here
; avem expresia: (a+a-c)-(b+b+d)
segment code use32 class=code
    start:
        
        MOV AX, [a] ; AX=a=100
        ADD AX, [a] ; AX= AX+a= 100+100= 200
        SUB AX, [c] ; AX= AX-c= a+a-c= 200-20=180
        MOV BX, [b] ; BX=b=50
        ADD BX, [b] ; BX=b+b=100
        ADD BX, [d] ; BX=b+b+d= 140
        SUB AX, BX ; AX=AX-BX=(a+a-c)-(b+b+d)= 180-140= 40
        MOV [x], AX; x=(a+a-c)-(b+b+d)
        
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
