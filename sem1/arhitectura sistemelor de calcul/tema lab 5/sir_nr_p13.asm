bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
;problema 13: Se da un sir de octeti S. Sa se construiasca sirul D1 ce contine elementele de pe pozitiile pare din S si sirul D2 ce contine elementele de pe pozitiile impare din S.Exemplu: S: 1, 5, 3, 8, 2, 9  D1: 1, 3, 2  D2: 5, 8, 9
segment data use32 class=data
    s db 1, 5, 3, 8, 2, 9
    ls equ $-s 
    ls1 equ ($-s)/2
    d1 times ls1 db 0
    d2 times ls1 db 0

; our code starts here
segment code use32 class=code
    start:
        mov esi, 0 ; index pentru parcurgerea sirului
        mov edi, 0 ; index pt sirul destinatie1
        mov ecx, ls1
        jecxz sfarsit 
        repeta:
            mov al, [s+esi] ; retinem elem cu index esi din s
            mov [d1+edi], al ; punem elementul retinut in sir
            inc edi ; crestem indexul pt sirul destinatie
            add esi, 2 ; crestem cu 2 pentru a pastra indecsi pari
        loop repeta ; face setul de instructiuni pana ecx=0
        sfarsit:
        
        mov esi, 1 ;index pentru parcurgerea sirului
        mov edi, 0 ; index pt sirul destinatie2
        mov ecx, ls1 ;ecx ia jumatate din lungimea lui s
        jecxz sfarsit1
        repeta1:
            mov bl, [s+esi] ; retinem elem cu index esi
            mov [d2+edi], bl ; adaugam in d2 elementul retinut anterior
            inc edi ; crestem indexul pentru sirul destinatie
            add esi, 2 ; crestem cu 2 pentru a pastra indecsi impari
        loop repeta1 ; repetam pana ecx este 0
        sfarsit1:
        
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
