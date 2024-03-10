%insert(E: Intreg, L: Lista, R: Lista)
%E: elementul care se insereaza in lista L, pe pozitia corecta
%L: lista de elemente in care vrem sa inseram
%R: lista rezultat, care contine elementul inserat corect
%Modele de flux: (i,i,o), (i,i,i)
%
%Model matematic:
%
%insert(e,l1..ln)={ [e]  ,n=0
%                   e U l1...ln , e <= l1 si n>0
%                   l1 U insert(e,l2...ln)  ,  e > l1 si n>0
%                 }
%teste: (4,[1,7,2,6],[1,4,7,2,6]),(2,[],[2]),(8,[1,2,3,4],[1,2,3,4,8])

insert(E,[],[E]).
insert(E,[H|T],[E,H|T]):-E=<H.
insert(E,[H|T],[H|R]):-E>H,
                       insert(E,T,R).

%sort(L: Lista,A:Lista,R: Lista)
%L: Lista de elemente pe care vrem s-o sortam
%A: Lista intermediara de acumulare a elementelor
%R: Lista care rezulta dupa sortarea listei initiale
%Modele de flux: (i,i,o),(i,i,i)
%
%Model matematic:
%
%sort(l1...ln,L1...Lm)={ L1..Lm, n=0
%                        sort(l2...ln,insert(l1,L1...Lm))
%                      }
%teste: ([],[],[]),([],[1,2,3],[1,2,3]),([1,6],[4,8,9],[1,4,6,8,9])

sort([],S,S).
sort([H|T],A,S):-insert(H,A,I),
                 sort(T,I,S).

%sortList(L: Lista,S: Lista)
%L: lista care trebuie sortata
%S: rezultatul sortarii listei initiale
%Modele de flux: (i,o),(i,i)
%
%Model matematic:
%
%sortList(l1...ln)=sort(l1...ln,[])
%
%teste: ([],[]),([1],[1]),([1,2,3],[1,2,3]),([5,7,3,1,4],[1,3,4,5,7])

sortList(L,S):-sort(L,[],S).

%ListaE=(Intreg/Lista)*
%listSort(L: ListaE, S: ListaE)
%L: lista cu elemente tip lista care trebuie sortate
%S: lista in care listele din lista initiala sunt sortate
%Modele de flux: (i,o),(i,i)
%
%Model matematic:
%
%listSort(l1...ln)={ [],  n=0
%                    sortList(l1) U listSort(l2...ln), n>0, l1 lista
%                    listSort(l2...ln) , n>0, l1 nu este lista
%                  }
% teste:([],[]),([1,5,3,6],[1,5,3,6]),
%([1,[7,4,6],6,2,[5,0,3,2],9],[1,[4,6,7],6,2,[0,2,3,5],9])

listSort([],[]).
listSort([H|T],R):-is_list(H),!,
                   sortList(H,R1),
                   listSort(T,R2),
                   R=[R1|R2].
listSort([H|T],[H|R]):-listSort(T,R).
