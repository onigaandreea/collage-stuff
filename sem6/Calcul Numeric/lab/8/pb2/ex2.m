addpath(genpath('../pb1'))

% Definirea nodurilor și punctelor de evaluare
x = [0, 1, 2, 3, 4]; % nodurile spline-ului
t = linspace(0, 4, 100); % 100 de puncte între 0 și 4 pentru evaluare

% Calcularea coeficienților spline-ului cubic
f = [0, 1, 0, 1, 0]; % Valorile în nodurile spline-ului
type = 2; % Natural spline
der = [0, 0]; % Derivatele secunde la capetele intervalului
c = CubicSplinec(x, f, type, der); % Calculați coeficienții spline-ului cubic

% Evaluarea spline-ului cubic
z = evalsplinec(x, c, t);

% Afișarea rezultatelor sau altă utilizare ulterioară a valorilor spline-ului (z)
disp('Valorile spline-ului cubic în punctele de evaluare:');
disp(z);

