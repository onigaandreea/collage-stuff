% A - B - C - D
% AB = x1; BC = x2; CD = x3;
% X = [ AD; AC; BD; AB; CD] 
X = [1 1 1; 1 1 0; 0 1 1; 1 0 0; 0 0 1];
Y = [89; 67; 53; 35; 20];

a = linsolve(X, Y)