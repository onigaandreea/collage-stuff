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
    b dd -25
    c db 10
    d db 6
    x dq 100
    y dw 0
    

; our code starts here
segment code use32 class=code
    start:
        mov al, [a] ; al=a=5=5h
        cbw ; ax=a 
        cwd ; dx:ax= a 
        add ax, word [b] 
        adc dx, word [b+2] ; dx:ax= a+b= 5+(-25)= -20 = ECh
        mov bx, ax
        mov cx, dx
        mov al, [c] ; al=c=10=Ah
        imul byte [d] ; ax=c*d= 10*6 =60= 3Ch
        cwd ; dx:ax= c*d= 60=3Ch
        add bx, ax 
        adc cx, dx ; cx:bx=a+b+c*d = -20+60= 40= 28h
        mov al, 9 
        sub al,[a] ; al=9-a=9-5=4=4h
        cbw ; ax=9-a 
        mov [y], ax
        mov ax, bx
        mov dx, cx
        idiv word [y] ; ax= (a+b+c*d)/(9-a)=10= Ah si dx=(a+b+c*d)%(9-a)
        cwde ; eax=(a+b+c*d)/(9-a)= 40/4=10=Ah
        cdq ; edx:eax=(a+b+c*d)/(9-a)
        mov ebx, dword [x] 
        mov ecx, dword [x+4] ; ecx:ebx=x=100=64h
        sub ebx, eax 
        sbb ecx, edx ; ecx:ebx= x-(a+b+c*d)/(9-a)= 100-10=90= 5Ah
        
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
