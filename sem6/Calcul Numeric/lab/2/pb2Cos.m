%calculul lui cos de x folosind  seria Taylor

function [tsin,tcos,k]=pb2Cos(x,eroare)
x=rem(x,2*pi);%restul impartirii lui x la 2pi
termen = ones(1,length(x));
tcos=termen;
termen=x;
tsin=termen;k=2;
while max(abs(termen))>eroare
    termen=-termen.*x/k;
    tcos=tcos+termen;
    k=k+1;
    termen= termen.*x/k;
    tsin=tsin+termen;
    k=k+1;
end