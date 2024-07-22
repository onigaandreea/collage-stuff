% Definirea absciselor și ordonatelor
x = [0, 1, 2, 3, 4];
f = [0, 1, 0, 1, 0];

% Specificarea tipului spline-ului și a derivatelor (opțional)
type = 2; % Natural spline
der = [0, 0]; % Derivatele secunde la capetele intervalului

% Calcularea coeficienților spline-ului cubic
c = CubicSplinec(x, f, type, der);

% Afișarea coeficienților
disp('Coeficienții spline-ului cubic:');
disp(c);

