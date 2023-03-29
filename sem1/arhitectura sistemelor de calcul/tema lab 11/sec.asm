%ifndef _SEC_ASM_
%define _SEC_ASM_

    conversie:

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
%endif