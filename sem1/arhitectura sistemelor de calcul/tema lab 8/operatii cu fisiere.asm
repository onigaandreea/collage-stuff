bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit, fopen, fclose, fread, printf               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions
import fopen msvcrt.dll
import fclose msvcrt.dll
import fread msvcrt.dll
import printf msvcrt.dll
                          
; our data is declared here (the variables needed by our program)
; Se da un fisier text care contine litere, spatii si puncte. Sa se citeasca continutul fisierului, sa se determine numarul de cuvinte si sa se afiseze pe ecran aceasta valoare. (Se considera cuvant orice secventa de litere separate prin spatiu sau punct)
segment data use32 class=data
    nume_fisier db "cuvinte.txt", 0 ; fisierul care contine litere, spatii si puncte
    mod_acces db "r", 0 ; mod de deschidere, r-citire
    descriptor dd -1   ;aici vom salva descriptorul fisierului
    len equ 100       ; numarul maxim de elemente citite din fisier.                            
    text times len db 0  ; sirul in care se va citi textul din fisier  
    n dd 0; aici vom avea numarul de cuvinte
    format db "Sunt %d cuvinte in fisier", 0 

; our code starts here
segment code use32 class=code
    start:
        ; apelam functia fopen pentru a desschide fisierul 
        ;eax = fopen(nume_fisier, mod_acces)
        push dword mod_acces     
        push dword nume_fisier
        call [fopen]
        add esp, 4*2    ; eliberam parametrii de pe stiva

        mov [descriptor], eax   ; salvam valoarea returnata de fopen in variabila descriptor 
        
        cmp eax, 0          ; verificam daca functia fopen a creat cu succes fisierul (daca EAX != 0)
        je final
        
        ; citim textul in fisierul deschis folosind functia fread
        ; eax = fread(text, 1, len, descriptor_fis)
        push dword [descriptor]
        push dword len
        push dword 1
        push dword text        
        call [fread]
        add esp, 4*4   ; dupa apelul functiei fread EAX contine numarul de caractere citite din fisier
        mov ebx, eax ; facem o copie a lungimii sirului 
        ; apelam functia fclose pentru a inchide fisierul
        ; fclose(descriptor_fis)
        
        push dword [descriptor]
        call [fclose]
        add esp, 4
        
        mov ecx, ebx ; retinem lungimea sirului
        mov esi, dword text ; retinem adresa sirului de caractere citit din fisier
        mov ebx, 0 ; folosim ebx pentru a numara cuvintele din fisier
        jecxz final1
        cld
        repeta:
            lodsb
            cmp al, ' ' ; verificam daca intalnim finalul unui cuvant
            jne verif1 ;verificam a doua conditie pentru care putem avea un final de cuvant
            inc ebx ; numaram cuvantul gasit
            verif1:
            cmp al, '.' ; verificam daca intalnim finalul unui cuvant
            jne urmator ; trecem la urmatorul caracter din sir
            inc ebx ; numaram cuvantul gasit
            urmator:
        loop repeta
        mov [n], ebx
        push dword [n]
        push format
        call [printf]
        add esp, 4*2
        
        final1:
        final:
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
