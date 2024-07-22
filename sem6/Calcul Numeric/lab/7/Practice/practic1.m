% Datele de intrare
x = [0 1 2];
y = exp(x);
y_prim = exp(x); % Derivatele funcției f(x) = e^x sunt aceleași cu valorile funcției în nodurile date

% Punctul la care să se calculeze aproximările
punct = 0.25;

% Aproximarea prin interpolare Hermite
aprox_hermite = interpolareHermite(x, y, y_prim, punct);

% Aproximarea prin interpolare Lagrange
aprox_lagrange = interpolareLagrange(x, y, punct);

% Valoarea reală a lui f(0.25)
valoare_reala = exp(punct);

% Calculul erorii pentru interpolare Hermite
eroare_hermite = abs(aprox_hermite - valoare_reala);

% Calculul erorii pentru interpolare Lagrange
eroare_lagrange = abs(aprox_lagrange - valoare_reala);

% Afișarea rezultatelor
fprintf('Aproximare Hermite: %.6f\n', aprox_hermite);
fprintf('Aproximare Lagrange: %.6f\n', aprox_lagrange);
fprintf('Valoare reală: %.6f\n', valoare_reala);
fprintf('Eroare Hermite: %.6f\n', eroare_hermite);
fprintf('Eroare Lagrange: %.6f\n', eroare_lagrange);

