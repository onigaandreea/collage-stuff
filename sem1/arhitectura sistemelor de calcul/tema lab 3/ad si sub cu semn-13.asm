bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
; expresia este: x=(b-a+c-d)-(d+c-a-b)
segment data use32 class=data
    a db 7
    b dw 20
    c dd 160
    d dq -100
    x dq 0

; our code starts here
segment code use32 class=code
    start:
        mov bx, [b] ; bx= b= 20= 14h
        mov al, [a] ; al= a= 7= 7h
        
        cbw ; ax= a= 7= 7h
        sub bx, ax ; bx= bx-ax= b-a= 20-7= 13= Dh
        mov ax, bx
        cwd ; dx:ax= b-a= 13= Dh
        mov bx, word [c] 
        mov cx, word [c+2] ; cx:bx=c =160= A0h
        add ax, bx ; ax=ax+bx
        adc dx, cx ; dx=dx+cx+cf
        
        ;in dx:ax avem rezultatul b-a+c= 173= ADh
        push dx
        push ax 
        pop eax ; eax=b-a+c =173 =ADh
        
        cdq ; edx:eax= b-a+c= 173= ADh
        sub eax, dword [d]
        sbb edx, dword [d+4] ; edx:eax= b-a+c-d= 173-(-100)= 273= 111h
        mov dword [x], eax
        mov dword [x+4], edx ; x=b-a+c-d=273=111h
        
        mov ebx, dword [d]
        mov ecx, dword [d+4] ; ecx:ebx= d=-100 =9Ch
        mov eax, [c] ; eax=c= 160= A0h
        cdq ; edx:eax=c 
        add ebx, eax 
        adc ecx, edx ; ecx:ebx= d+c=-100+160=60= 3Ch
        mov al, [a]; al=a=7=7h
        cbw 
        cwde
        cdq ; edx:eax=a 
        sub ebx, eax 
        sbb ecx, edx ; ecx:ebx= d+c-a =53= 35h
        mov ax, [b] ; ax=b=20=14h
        cwde
        cdq ; edx:eax=b 
        sub ebx, eax 
        sbb ecx, edx ; ecx:ebx= d+c-a-b= 33=21h
        mov eax, dword [x]
        mov edx, dword[x+4] ; edx:eax=b-a+c-d= 111h
        sub eax, ebx 
        sbb edx, ecx ; edx:eax=(b-a+c-d)-(d+c-a-b)= 240= F0h
        
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
