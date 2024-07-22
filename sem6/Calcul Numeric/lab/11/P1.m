f = @(x) sin(x.^2);
a = 0;
b = 1;
n = 10;

disp("Legendre:");
[n_l,c_l] = legendre(n)
I = c_l * f(n_l)

disp("----------------------------------------");
disp("Cebîsev #1:")
[n_c1,c_c1] = cebisev1(n)
I = c_c1 * f(n_l)


disp("----------------------------------------");
disp("Cebîsev #2:")
[n_c2,c_c2] = cebisev2(n)
I = c_c2 * f(n_c2)

disp("----------------------------------------");
disp("Jacobi:")
[n_j,c_j] = jacobi(n,a,b)
I = c_j * f(n_j)

disp("----------------------------------------");
disp("Laguerre:")
[n_l2,c_l2] = laguerre(n,a,b)
I = c_l2 * f(n_l2)

disp("----------------------------------------");
disp("Hermite:")
[n_h,c_h] = hermite(n)
I = c_h * f(n_h)

