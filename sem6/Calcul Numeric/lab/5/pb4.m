clc
n=10;
A = diag(randi([1, 10], 1, n));
b=(1:n)';
x0=A\b
x1=rezolvaGaussSeidel(A,b,1e-5)
x2=rezolvaJacobi(A,b,1e-5)
x3=rezolvaSOR(A,b,0.5,1e-5)

