%                           adevarat, X1=X2=X3 sau Y1=Y2=Y3
% colin(X1,Y1,X2,Y2,X3,Y3)= adevarat, (Y2-Y1)/(X2-X1) =(Y3-Y1)/(X3-X1)
%                                si toate coordonatele sunt diferite
%                           fals altfel
%
%colin(X1:Intreg,Y1:Intreg,X2:Intreg,Y2:Intreg,X3:Intreg,Y3:Intreg)
%X1: coordonata de pe axa OX a primului numar
%Y1: coordonata de pe axa OY a primului numar
%X2: coordonata de pe axa OX a celui de al doilea numar
%Y2: coordonata de pe axa OY a celui de al doilea numar
%X3: coordonata de pe axa OX a celui de al treilea numar
%Y3: coordonata de pe axa OY a celui de al treilea numar
%
%Modele de flux: (i,i,i,i,i,i) determinist

colin(X,_,X,_,X,_):-!.
colin(_,Y,_,Y,_,Y):-!.
colin(X1,Y1,X2,Y2,X3,Y3):-X1=\=X2,X1=\=X3,X2=\=X3,
    Y1=\=Y2,Y1=\=Y3,Y2=\=Y3,
    P1 is (Y2-Y1)/(X2-X1),
    P2 is (Y3-Y1)/(X3-X1),
    P1=:=P2.


%candidat(l1...ln) = 1.(l1,l2,[]), n=2
%         2.(x1,y1,l1 U l2 U r), unde (x1,y1,r)=candidat(l3...ln), n>2
%
%candidat(L:lista,X:Intreg,Y:Intreg,R:lista)
%L: lista initiala de unde luam un punct
%X: prima coordonata selectata
%Y: a doua coordonata selectata
%R: lista initiala dar din care am scos deja candidatii
%
%Modele de flux:(i,i,i,i) determinist, (i,o,o,o) nedeterminist

candidat([X1,Y1|_],X1,Y1,[]).
candidat([L1,L2|T],X1,X2,R):-candidat(T,X1,X2,R1),
    R=[L1,L2|R1].

% secvente(l1...ln,c1...cm)=
%1. c1...cm
%2. secvente(rx,x1x2c1...cm), (x1,x2,rx)=candidat(l1...ln)
%                     colin(x1,x2,c1,c2,c,c4)
%
%secvente(L:lista,C:lista,R:lista)
%L: lista initiala de elemente
%C: lista colectoare
%R: lista rezultat
%
%Modele de flux:(i,i,i) determinist, (i,i,o) nedeterminist
secvente(_,C,C).
secvente(L,[X1,Y1,X2,Y2|C],R):-candidat(L,X,Y,RX),
    colin(X,Y,X1,Y1,X2,Y2),
    secvente(RX,[X,Y,X1,Y1,X2,Y2|C],R).

%solutie(l1...ln)=secvente(ry,[x1,x2,x3,x4]),
%                 unde(x1,x2,rx)=candidat(l1...ln)
%                 (x3,x4,ry)=candidat(rx)
%
%solutie(L: lista,R: lista)
%L: lista initiala de elemente
%R: lista rezultat
%
%Modele de flux: (i,i) determinist, (i,o) nedeterminist

solutie(L,R):-candidat(L,X1,Y1,RX),
    candidat(RX,X2,Y2,RY),
    secvente(RY,[X1,Y1,X2,Y2],R).

%test: solutie([1,1,2,2,3,3,5,0,6,0,8,0], R).