bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
; Expresia este: x-(a+b+c*d)/(9-a)
segment data use32 class=data
    a db 5
    b dd 35
    c db 10
    d db 6
    x dq 100

; our code starts here
segment code use32 class=code
    start:
        mov al, [a] ; al=a=5=5h
        mov ah, 0 ; ax=a=5=5h
        mov dx, 0 ; dx:ax=5=5h
        add ax, word [b] 
        adc dx, word [b+2] ; dx:ax= a+b= 40= 28h
        mov bx, ax
        mov cx, dx
        mov al, [c] ; al=c=10=Ah
        mul byte [d] ; ax=c*d= 60= 3Ch
        mov dx, 0 ; dx:ax=c*d
        add bx, ax
        adc cx, dx; cx:bx= a+b+c*d =100 = 64h
        mov ax, bx
        mov dx, cx
        mov bl, 9
        sub bl, [a] ;  bl=4=4h
        mov bh, 0 ; bx=4=4h
        div bx ; ax=dx:ax/bx= 25= 19h
        mov dx, 0 ; dx:ax=(a+b+c*d)/(9-a)=25=19h
        push dx
        push ax
        pop eax ; eax=(a+b+c*d)/(9-a)
        mov edx, 0 ; edx:eax=(a+b+c*d)/(9-a)
        mov ebx, dword [x]
        mov ecx, dword [x+4]
        sub ebx, eax
        sbb ecx, edx ; ecx:ebx= x-(a+b+c*d)/(9-a)= 90= 4Bh
        
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
