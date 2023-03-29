bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit, scanf               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions
import scanf msvcrt.dll
                          
; our data is declared here (the variables needed by our program)
; Sa se citeasca de la tastatura doua numere a si b (in baza 10) si sa se calculeze: (a+b) * (a-b). Rezultatul inmultirii se va salva in memorie in variabila "rezultat" (definita in segmentul de date).
segment data use32 class=data
    a db 0; aici se va stoca primul numar citit de la tastatura
    b db 0; aici se va stoca cel de al doilea numar citit de la tastatura
    rezultat dd 0 ; aici se va salva rezultatul inmultirii
    format db "%d", 0 ; formatul pentru numere in baza zece
    

; our code starts here
segment code use32 class=code
    start:
        push dword a ; punem pe stiva adresa lui a
        push dword format ; punem pe stiva adresa formatului
        call [scanf] ;  facem apelu functiei scanf pentru citirea primului numar
        add esp, 4*2 ; eliberam parametrii de pe stiva
        
        mov eax, 0 ;convertim a la dword pentru a pune valoarea pe stiva
        mov al, [a] ; stocam valoarea citita in eax
        
        mov ebx, eax ; eliberam registrul eax pentru citirea celui de al doilea numar ; ebx = a
        
        push dword b ; punem pe stiva adrresa lui baza
        push dword format ; punem pe stiva adresa formatului
        call [scanf] ;  facem apelu functiei scanf pentru citirea celui de al doilea numar
        add esp, 4*2 ; eliberam parametrii de pe stiva
        
        mov eax, 0 ;convertim b la dword pentru a pune valoarea pe stiva
        mov al, [b] ; stocam valoarea citita in eax
        
        mov ecx, eax ; facem o copie lui b
        mov eax, ebx ; facem o copie lui a 
        add ax, cx ; ax = a+b 
        sub bx, cx ; bx = a-b 
        mul bx ; eax = (a+b) * (a-b)
        
        mov dword [rezultat], eax; salvam rezultatul in memorie in variabila rezultat
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
