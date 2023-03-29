bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
; Problema 13: Se da un sir S de dublucuvinte. Sa se obtina sirul D format din octetii inferiori ai cuvintelor inferioare din elementele sirului de dublucuvinte, care sunt multiplii de 7.
segment data use32 class=data
    s dd 12345607h, 1A2B3C15h, 13A33412h
    l equ ($-s)/4
    d times l db 0
    aux db 0

; our code starts here
segment code use32 class=code
    start:
        mov esi, s
        mov ecx, l 
        mov edi, d 
        jecxz final
        cld
        repeta:
            lodsd
            mov [aux], al
            mov ah, 0
            mov bl, 7
            div bl
            cmp ah, 0
            jne urm
            mov al, [aux]
            stosb
            urm:
        loop repeta
        final:
            
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
