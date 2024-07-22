% Datele de intrare
x = [0.30, 0.32, 0.35];
y = [0.29552, 0.31457, 0.34290];
y_prim = [0.95534, 0.94924, 0.93937];

% Punctul la care să se calculeze aproximarea
punct = 0.33;

% Aproximarea prin interpolare Hermite
aprox_hermite = interpolareHermite(x, y, y_prim, punct);

% Valoarea reală a lui sin(0.34)
valoare_reala = sin(punct);

% Calculul erorii pentru interpolare Hermite
eroare_hermite = abs(aprox_hermite - valoare_reala);

% Afișarea rezultatelor
fprintf('Aproximare Hermite: %.6f\n', aprox_hermite);
fprintf('Valoare reală: %.6f\n', valoare_reala);
fprintf('Eroare Hermite: %.6f\n', eroare_hermite);

