bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
; expresia este: d-b+a-(b+c) 
segment data use32 class=data
    a db 7
    b dw 20
    c dd 100
    d dq 160
    x dq 0

; our code starts here
segment code use32 class=code
    start:
        mov eax, dword [d]
        mov edx, dword [d+4] ; edx:eax= d= 160=A0h
        mov bx, [b] ; bx=b= 20= 14h
        mov cx, 0
        push cx
        push bx  
        pop ebx ; ebx=b
        mov ecx, 0 ; ecx:ebx= b
        sub eax, ebx
        sbb edx, ecx ; edx:eax=d-b=160-20=140= 8C
       
        mov bl, [a] ; bl=a=7=7h
        mov bh, 0 ; bx=a 
        mov cx, 0 ; cx:bx=a
        push cx
        push bx  
        pop ebx ; ebx=a
        mov ecx, 0 ; ecx:ebx= a 
        add eax, ebx 
        adc edx, ecx ; edx:eax= d-b+a= 147= 93h
        
        mov bx, [b] ; bx=b= 20= 14h
        mov cx, 0 ; cx:bx= b 
        add bx, word [c]
        adc cx, word [c+2] ; cx:bx= b+c= 120= 78h
        push cx
        push bx
        pop ebx ; ebx=b+c 
        mov ecx, 0 ; ecx:ebx=b+c
        sub eax, ebx 
        sbb edx, ecx ; edx:eax=d-b+a-(b+c) =27= 1Bh
        
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
