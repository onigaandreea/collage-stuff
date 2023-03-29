bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
; Problema 13: Dandu-se 4 octeti, sa se obtina in AX suma numerelor intregi reprezentate de bitii 4-6 ai celor 4 octeti.
segment data use32 class=data
    a db 00001010b ; Ah
    b db 01101001b ; 69h
    c db 00100110b ; 26h
    d db 01001100b ; 4Ch

; our code starts here
segment code use32 class=code
    start:
        mov al, [a] ; al= a= 0000 1010b=0Ah
        and al, 01110000b ; al= 0000 0000b=0h
        mov cl, 4
        shr al, cl 
        ;al= 0000 0000b=0h , obtinem numarul format din bitii 4-6 ai lui a 
        
        mov ah, 0 
        ;in ax o sa avem primul numar intreg si aici vom aduna celelalte numere obtinute
        
        mov bl, [b] ; bl= 0110 1001b= 69h
        and bl, 01110000b ; bl= 0110 0000b=60h
        mov cl, 4
        shr bl, cl  
        ;bl= 0000 0110b=6h, obtinem numarul format din bitii 4-6 ai lui b 
        
        mov bh, 0 
        ; bx=0000 0000 0000 0110b=6h, cel de al doilea numar pe care il adunam
        
        add ax, bx 
        ; ax= 0000 0000 0000 0000b+ 0000 0000 0000 0110b=0000 0000 0000 0110b= 6h
        
        mov bl, [c] ; bl= 00100110b = 26h
        and bl, 01110000b ; bl= 0010 0000b=20h
        mov cl, 4
        shr bl, cl 
        ; bl= 0000 0010b=2h, obtinem numarul format din bitii 4-6 ai lui c 
        
        mov bh, 0 
        ; bx= 0000 0000 0000 0010b=2h, cel de al doilea numar pe care il adunam
        
        add ax, bx 
        ; ax=0000 0000 0000 0110b+ 0000 0000 0000 0010b= 0000 0000 0000 1000b= 8h
        
        mov bl, [d] ; bl= 01001100b= 4Ch
        and bl, 01110000b ; bl= 0100 0000b=40h
        mov cl, 4
        shr bl, cl 
        ; bl= 0000 0100b=4h, obtinem numarul format din bitii 4-6 ai lui d 
        
        mov bh, 0 
        ; bx= 0000 0000 0000 0100b=4h, cel de al doilea numar pe care il adunam
        
        add ax, bx 
        ; ax= 0000 0000 0000 0000b+ 0000 0000 0000 0110b=0000 0000 0000 0110b= Ch
        
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
