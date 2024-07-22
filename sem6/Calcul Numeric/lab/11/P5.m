% Definirea funcțiilor
f1 = @(x) exp(-x.^2).*sin(x);
f2 = @(x) exp(-x.^2).*cos(x);

% Intervalul integralei
a = 0;
b = Inf;

% Numărul de noduri și coeficienți
n = 10;

% Calculul nodurilor și coeficienților folosind funcția laguerre
[nod, coef] = laguerre(n, a, b);

% Calculul aproximării integralei pentru f1 (sin)
I2_sin_laguerre = coef * f1(nod);

% Calculul aproximării integralei pentru f2 (cos)
I2_cos_laguerre = coef * f2(nod);

% Afișarea rezultatelor cu 8 zecimale
disp("Pentru integrala cu sin:");
fprintf("Integrala aproximata: %.8f\n", I2_sin_laguerre);
fprintf("Integrala exacta: %.8f\n", integral(f1, a, b));

disp("-------------------------------");
disp("Pentru integrala cu cos:");
fprintf("Integrala aproximata: %.8f\n", I2_cos_laguerre);
fprintf("Integrala exacta: %.8f\n", integral(f2, a, b));

