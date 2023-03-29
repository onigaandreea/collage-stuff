bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
; Problema 26: Se dau 2 dublucuvinte R si T. Sa se obtina dublucuvantul Q astfel:
;bitii 0-6 din Q coincid cu bitii 10-16 a lui T
;bitii 7-24 din Q concid cu bitii obtinuti 7-24 in urma aplicarii R XOR T.
;bitii 25-31 din Q coincid cu bitii 5-11 a lui R.
segment data use32 class=data
    R dd 00110110000110010110111001010110b ; 36196E56h
    T dd 00001001011011001010100011011100b ; 96CA8DCh
    Q dd 0

; our code starts here
segment code use32 class=code
    start:
        mov eax, [R] 
        ;eax= 0011 0110 0001 1001 0110 1110 0101 0110b =36196E56h
        
        and eax, 00000000000000011111110000000000b 
        ;izolam bitii 10-16 din R
        ;eax= 0000 0000 0000 0001 0110 1100 0000 0000b =16C00h
        
        mov cl, 10
        shr eax, cl 
        ;eax = 0000 0000 0000 0000 0000 0000 0101 1011b =5Bh
        
        mov ebx, 0
        or ebx, eax ; incepem constructia lui Q
        ;ebx=0000 0000 0000 0000 0000 0000 0101 1011b =5Bh
        
        mov eax, [R] 
        ;eax= 0011 0110 0001 1001 0110 1110 0101 0110b =36196E56h
        
        xor eax, [T] 
        ;eax= 0011 1111 0111 0101 1100 0110 1000 1010b =3F75C68Ah
        
        and eax, 00000001111111111111111110000000b 
        ;izolam bitii 7-24 din R xor T
        ;eax= 0000 0001 0111 0101 1100 0110 1000 0000b= 175C680h
        
        or ebx, eax 
        ;ebx= 0000 0001 0111 0101 1100 0110 1101 1011b= 175C6DBh
        
        mov eax, [R] 
        ;eax= 0011 0110 0001 1001 0110 1110 0101 0110b =36196E56h
        
        and eax, 00000000000000000000111111100000b
        ;eax= 0000 0000 0000 0000 0000 1110 0100 0000b= E40h
        
        mov cl, 20
        rol eax, cl
        ;eax= 1110 0100 0000 0000 0000 0000 0000 0000b= E4000000h
        
        or ebx, eax
        ;ebx =1110 0101 0111 0101 1100 0110 1101 1011b=E575C6DBh
        
        mov [Q], ebx 
        ;Q= 1110 0101 0111 0101 1100 0110 1101 1011b=E575C6DBh
       
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
