% Definirea funcției
f = @(x) x .* exp(-x.^2) .* sqrt(1 - x.^2);

% Numărul de noduri și coeficienți
n = 10;

% Calculul nodurilor și coeficienților folosind funcția cebisev1
[nod, coef] = cebisev1(n);

% Calculul aproximării integralei
approx_integral = 0;
for i = 1:length(coef)
    approx_integral = approx_integral + coef(i) * f(nod(i));
end



% Calculul valorii exacte a integralei
exact_integral = integral(f, -1, 1);

% Calculul erorii de aproximare
approx_error = abs(approx_integral - exact_integral);

% Afișarea rezultatelor
disp('Aproximare integrală:');
disp(approx_integral);

disp('Valoare exactă a integralei:');
disp(exact_integral);

disp('Eroare de aproximare:');
disp(approx_error);
