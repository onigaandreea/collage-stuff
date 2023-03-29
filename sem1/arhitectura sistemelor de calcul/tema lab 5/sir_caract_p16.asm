bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
;problema 16: Se dau doua siruri de caractere S1 si S2. Sa se construiasca sirul D prin concatenarea elementelor de pe pozitiile impare din S2 cu elementele de pe pozitiile pare din S1.  Exemplu:  S1: 'a', 'b', 'c', 'b', 'e', 'f'  S2: '1', '2', '3', '4', '5'  D: '1', '3', '5', 'b', 'b', 'f'
segment data use32 class=data
    s1 db 'a','b','c','b','e','f', 'g'
    lung1 equ $-s1
    s2 db '1', '2', '3', '4', '5'
    lung2 equ $-s2
    lung3 equ ($-s1)/2+($-s2)/2
    d times lung3 db 0

; our code starts here
segment code use32 class=code
    start:
        mov esi, 1 ; index pt sirul sursa
        mov edi, 0 ; index pt sirul destinatie
        mov ecx, lung2
        repeta:
            mov al, [s2+esi] ; retinem elem din s2 care are indexul esi
            mov [d+edi], al ; adaugam elementul pe pozitia edi in sirul destinatie
            add esi, 2 ; crestem cu 2 pentru a pastra indecsi impari
            inc edi ; crestem indexul sirului destinatie
            cmp esi, ecx ; comparam indecsi pentru a stii unde ne oprim
        jb repeta
        
        mov esi, 0 ; index pt sirul sursa
        mov ecx, lung1
        
        bucla:
            mov al, [s1+esi] ; retinem elem din s1 care are indexul esi
            mov [d+edi], al ; adaugam elementul pe pozitia edi in sirul destinatie
            add esi, 2 ; crestem cu 2 pentru a pastra indecsi pari
            inc edi ; crestem indexul sirului destinatie
            cmp esi, ecx ; comparam indecsi pentru a stii unde ne oprim
            
        jbe bucla
        
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
