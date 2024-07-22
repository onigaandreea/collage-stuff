function pb1()

A = [10 7 8 7; 7 5 6 5; 8 6 10 9; 7 5 9 10];
B = [32; 23; 33; 31];
xe = A\B;
inv(A);
format short
xe = A\B;
inv(A);
determinat=det(A);
 
bp = [32.1; 22.9; 33.1; 30.9];
xep = A\bp;
err_in = norm(B-bp)/norm(B) %-> eroare la intrare
err_out = norm(xep-xe)/norm(xe) %-> eroare la iesire
err_out/err_in; %-> raportul erorilor
cond(A) %-> conditionarea matricei

Ap = [10 7 8.1 7.2; 7.08 5.04 6 5; 8 5.98 9.89 9; 6.99 4.99 9 9.98];
xep1 = Ap\B;
err_in1 = norm(A-Ap)/norm(A) %-> eroare la intrare
err_out1 = norm(xep1-xe)/norm(xe) %-> eroare la iesire
err_out1/err_in1; %-> raportul erorilor
cond(Ap) %-> conditionarea matricei