%nrAparitii(E:Element, L:Lista, N:Intreg)
%Modele de flux: (i,i,o), (i,i,i)
%E - Elementul al caror aparitii le numaram
%L - Lista in care numaram aparitiile elementului E
%N - Numarul de aparitii al elementului E in lista L
%
%nrAparitii(a,l1..ln)={ 0, n=0
%                       1, n=1 && l1=a
%                       nrAparitii(a,l2...ln)+1, n>1 && l1=a
%                       nrAparitii(a,l2...ln), n>1 && l1!=a
%                       }
%
%teste: (1,[],0), (1,[2,3],0), (1,[1,3,4,5,1],2)

nrAparitii(_,[],0).
nrAparitii(E,[H|T],N):-E=H,nrAparitii(E,T,NT),N is NT+1.
nrAparitii(E,[H|T],N):-E\=H,nrAparitii(E,T,N).


%multime(L:Lista, R:Lista)
%Modele de flux: (i,o), (i,i)
%L - Lista din care elimin elemente pentru a obtine o multime
%R - Lista care va deveni o multime (nu vom avea duplicate)
%
%multime(l1...ln)={ [], n=0
%                   multime(l2...ln), n>1 && nrAparitii(l1,l1..ln)!=1
%                   l1 U multime(l2...ln),n>1 && nrAparitii(l1,l1..ln)=1
%                 }
%
%teste: ([],[]), ([1],[1]), ([1,2,4],[1,2,4]), ([1,2,3,2,1],[3,2,1])

multime([],[]).
multime([H|T],R):-nrAparitii(H,[H|T],N),N\=1,multime(T,R).
multime([H|T],R):-nrAparitii(H,[H|T],N),N=1,multime(T,R1),R=[H|R1].


%cmmdc(A:Element, B:Element, D:Intreg)
%Modele de flux: (i,i,o),(i,i,i)
%A - Unul dintre cele doua elemente pentru care vrem sa aflam cmmdc
%B - Al doilea element pentru care vrem sa aflam cmmdc
%D - cel mai mare divizor comun al elementelor A si B
%
%cmmdc(a,b)={ 0, a=0||b=0
%             a, a=b
%             cmmdc(a-b,b), a>b
%             cmmdc(a,b-a), b>a
%             }
%
% teste: (0,1,0), (2,0,0), (3,3,3), (2,68,2), (3,5,1), (48,64,16)

% cmmdc se calculeaza sub forma de scaderi repetate
cmmdc(_,0,0):-!.
cmmdc(0,_,0):-!.
cmmdc(A,B,D):-A=B,D=A.
cmmdc(A,B,D):-A>B,C is A-B,cmmdc(C,B,D1),D=D1.
cmmdc(A,B,D):-A<B,C is B-A,cmmdc(A,C,D1),D=D1.

%cmmdcLista(L:Lista, D:Intreg)
%Modele de flux: (i,o), (i,i)
% L - Lista pentru care se va afla cmmdc al elementelor
% D - Cel mai mare divizor comun al elementelor listei L
%
% cmmdcLista(l1..ln)={ 0, n=0
%                      l1, n=1
%                      cmmdc(l1,cmmdcLista(l2...ln)), n>1
%                      }
%
% teste: ([],0), ([3],3), ([0,2],0), ([4,0],0), ([4,12],4), ([3,7],1),
% ([6,12,16],2), ([3,5,7,11],1), ([64,24,20],4)

cmmdcLista([],0).
cmmdcLista([E],E).
cmmdcLista([H|[E|T]],D):-cmmdcLista([E|T],D1),cmmdc(H,D1,D2),D=D2.




