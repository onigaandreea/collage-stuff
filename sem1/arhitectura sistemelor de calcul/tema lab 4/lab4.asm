bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
; Problema 10: Sa se inlocuiasca bitii 0-3 ai octetului B cu bitii 8-11 ai cuvantului A.
segment data use32 class=data
    a dw 0011011100001010b ; 370Ah
    b db 11000101b ; C5h

; our code starts here
segment code use32 class=code
    start:
        mov ax, [a] ; izolam bitii 11-8 ai lui a 
        and ax, 0000111100000000b 
        ; ax= 0000 0111 0000 0000b =700h
        
        ror ax, 8; ax=0000 0000 0000 0111b =7h
        mov bx, ax
        mov al, [b]
        mov ah, 0 ; ax= 0000 0000 1100 0101b = C5h
        or ax, bx ; ax= 0000 0000 1100 0111b = C7h
        
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
