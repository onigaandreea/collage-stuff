% Definirea funcției
f = @(x) 1 ./ sqrt(sin(x));

% Intervalul integral
a = 0;
b = pi/2;

% Numărul de noduri
n = 10; % Alegem un număr suficient de mare pentru a obține precizia dorită

% Calculul nodurilor și coeficienților folosind funcția jacobi
[nod, coef] = jacobi(n, 0, 0); % Pentru simplificare, setăm x = y = 0

% Calculul aproximării integralei
approx_integral = 0;
for i = 1:length(coef)
    approx_integral = approx_integral + coef(i) * f(nod(i));
end

% Calculul valorii exacte a integralei
exact_integral = integral(f, a, b);

% Afișarea rezultatului cu 9 zecimale
fprintf('Aproximare integrală: %.9f\n', approx_integral);
fprintf('Valoare exactă a integralei: %.9f\n', exact_integral);


