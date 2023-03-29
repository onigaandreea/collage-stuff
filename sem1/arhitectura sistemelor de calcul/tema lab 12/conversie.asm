bits 32 ; assembling for the 32 bits architecture
global _conversie
; declare the EntryPoint (a label defining the very first instruction of the program)
  ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data public data use32
    ; ...

; our code starts here
segment code public code use32
    _conversie:

        mov eax, 0 ; pregatim registrul in care vom salva numarul
        
        mov esi, [esp+4] ; preluam din stiva sirul de 0 si 1
       
        .repeta:
            mov bl, byte[esi] ; retinem primul caracter al sirului, necesar prelucrarii numarului
            inc esi ; incrementam esi pentru a ajunge la caracterul urmator
            cmp bl, 0 ; verificam daca am ajuns la finalul numarului
            je .outofloop ; daca da sarim la eticheta precizata
            SHL EAX, 1 ; incepem formarea numarului inmultind cu 2
            cmp bl, '1' ; verificam daca avem un bit de 1
            je .unu ; daca da sarim la eticheta precizata
            jne .zero ; altfel facem salt la eticheta precizata
            .unu:
                add eax, 1 ; adunam un 1 la numarul format
            .zero:
            
        jmp .repeta
            
        .outofloop:
        ret 4 ; eliberam stiva
            
        