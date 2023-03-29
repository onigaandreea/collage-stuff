bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit, printf, gets               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

import printf msvcrt.dll
import gets msvcrt.dll

; our data is declared here (the variables needed by our program)

%include "sec.asm"

segment data use32 class=data
    len equ 33
    numar times len db 0
    format db '%o', 10 , 0 

; our code starts here
segment code use32 class=code
    start:
        
        push dword numar ;salvam pe stiva parametrul unde vom salva numarul citit
        call [gets] ; citim primul numar care va fi un sir de 0 si 1 care vor alcatui numarul pe care il vom converti
        ; citim pana ajungem la enter, adica pana dam de nul
        add esp, 4 ; eliberam stiva de parametrul utilizat pentru a ajunge la adresa de revenire a pasului anterior
        mov esi, numar ; salvam sirul de 0 si 1 in registrul esi pentru a-l putea prelucra ulterior
        mov bl, byte[esi] ; retinem primul caracter din sirul citit
        cmp bl, '2' ; conditia de oprire
        jne conversie1 ;  salt spre instructiunea repetitiva care v-a prelucra si afisa numarul citit
        je final ; salt spre finalul programului
        conversie1:
            push numar ; salvam pe stiva numarul (sirul de 0 si 1), pentru a realiza apelul procedurii
            call conversie ; apelam procedura implementata in sec.asm
            push eax ; punem pe stiva numarul returnat in urma apelarii procedurii pentru a-l afisa
            push format ; punem pe stiva formatul, necesar functiei printf
            call [printf] ; apelam functia de afisare
            add esp, 4*2 ; eliberam stiva
            jmp start ; salt spre eticheta start pentru a citi un nou numar
        final:
        
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
