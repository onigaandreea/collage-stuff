% Datele de intrare
x = [0, 3, 5, 8, 13];
y = [0, 225, 383, 623, 993];
y_prim = [75, 77, 80, 74, 72];

% Punctul la care să se calculeze aproximarea
punct = 10;

% Aproximarea prin interpolare Hermite pentru poziție
pozitie_aproximata = interpolareHermite(x, y, y_prim, punct);

% Aproximarea prin interpolare Hermite pentru viteză
viteza_aproximata = interpolareHermite(x, y_prim, zeros(size(y_prim)), punct);

% Afișarea rezultatelor
fprintf('Poziția aproximată la momentul t = 10: %.2f\n', pozitie_aproximata);
fprintf('Viteza aproximată la momentul t = 10: %.2f\n', viteza_aproximata);

