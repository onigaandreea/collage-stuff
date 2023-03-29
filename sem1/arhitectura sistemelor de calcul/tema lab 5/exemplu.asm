bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
	s db 'a', 'b', 'c', 'm','n' ; declararea sirului initial s
	lung equ $-s ; stabilirea lungimea sirului initial l ; l=5
	d times lung db 0 ; rezervarea unui spatiu de dimensiune l pentru sirul destinatie d si initializarea acestuia

segment code use32 class=code
start:

	mov ecx, lung ;punem lungimea in ECX pentru a putea realiza bucla loop de ecx ori
	mov esi, 0    ; va fi un fel de pointer (index=0)
 
	jecxz Sfarsit  
    
	Repeta:
		mov AL, [s+esi] ;s+0=primul, s+1=al doilea, 
           ; cu cuvinte: s+0 =primul, s+2=al doilea, …
           ; cu dublucuv: s+0=primul, s+4=al doilea, ...
		mov BL, 'a'-'A' ;61h-41h=20h ; pentru a obtine litera mare corespunzatoare literei mici, vom scadea din codul ASCII 
		; al literei mici diferenta dintre 'a'-'A'
		sub AL, BL  ; -> ‘A’, ‘B’           
		mov [d+esi], AL    
		inc esi
	loop Repeta ; ECX=4

	Sfarsit:           ;terminarea programului
mov EAX,0
mov AL,[d+ESI-1]
push EAX

	; exit(0)
	push dword 0 ; push the parameter for exit onto the stack
	call [exit] ; call exit to terminate the program
