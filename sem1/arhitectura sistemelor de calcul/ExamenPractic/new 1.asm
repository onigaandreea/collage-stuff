bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit, fprintf, scanf, fopen, fclose, printf               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions
import fprintf msvcrt.dll
import scanf msvcrt.dll
import fopen msvcrt.dll
import fclose msvcrt.dll
import printf msvcrt.dll

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    m db 0
    n db 0
    numar db 0
    format_citire db "%d" , 0
    format_scriere db "%d", 0
    nume_fisier db "output.txt", 0 
    mod_acces db "w", 0 
    descriptor dd -1   ;aici vom salva descriptorul fisierului
    len equ 100       ; numarul maxim de elemente        

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
        
        ;citim primul numar m 
        push dword m
        push dword format_citire
        call [scanf]
        add esp, 4*2 ; eliberam stiva
        mov ecx, [m] ; salvam valoarea lui m pentru loop
        ;citim al doilea numar
        push dword n
        push dword format_citire
        call [scanf]
        add esp, 4*2
        
        mov dx, [n] ; stocam valoarea lui n 
        
        repeta:
            ;citim pe rand cate un numar
            push numar
            push format_citire
            call [scanf]
            add esp, 4*2
            ;salvam valoarea pentru a o prelucra
            mov eax, [numar]
            mov bl, 0
            numara:
                mov bx, 10
                div bx ;luam cifrele zecimale
                mov dl, al ; salvam catul
                mov eax, edx 
                mov ax, 0 ; pregatim cifra pentru a verifica daca se divide cu n 
                div dx 
                cmp ax, 0
                je numara1
                jne altul
                numara1:
                    add bl , 1
                altul:
                mov al, dl 
                mov ax, 0 ; pregatim verificarea urmatoarei cifrele
                cmp eax, 0
                je afara
                jne numara
            afara:
            cmp bl, 2
            ja printeaza
            jb urm 
            printeaza:
                push dword [numar]
                push dword format_scriere
                call [printf]
                add esp, 4*2
                
            urm:
        loop repeta
            
        call [fclose]
        final:
        
        
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
