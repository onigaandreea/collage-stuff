n = 10;
A = zeros(n);

% Actualizarea diagramei secundare
for i = 1:n
    j = n - i + 1;
    A(i, j) = 0.5;
end

% Actualizarea superdiagonalei și subdiagonalei
A(2:n+1:end) = -1;
A(n+1:n+1:end) = -1;

% Actualizarea diagonalei principale
A(1:n+1:end) = 3;

%disp(A);

disp('a) Descompunere LUP:');
[L,U,P] = descLUP(A);
L
U
P

disp('Descompunere Cholesky:');
R = factCholesky(A);
disp(R);

n = 100;
A1 = zeros(n);

% Actualizarea diagramei secundare
for i = 1:n
    j = n - i + 1;
    A1(i, j) = 0.5;
end

% Actualizarea superdiagonalei și subdiagonalei
A1(2:n+1:end) = -1;
A1(n+1:n+1:end) = -1;

% Actualizarea diagonalei principale
A1(1:n+1:end) = 3;

b=[2.5;ones(48,1)*1.5;ones(2,1)*1.0;ones(48,1)*1.5;2.5];
%disp(b);


[L1,U1,P1] = descLUP(A1);
x_lup =  U1 \ (L1 \ (P1 * b));

x_chol = rezCholesky(A1,b);

% Afișarea soluțiilor
disp('Soluția sistemului Ax = b folosind metoda LUP:');
disp(x_lup);

disp('Soluția sistemului Ax = b folosind metoda Cholesky:');
disp(x_chol);
