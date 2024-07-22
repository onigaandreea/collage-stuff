% Definirea funcției f(x) = x^2 * sin(x)
f = @(x) x.^2 .* sin(x);

% Definirea intervalului și numărului de noduri
a = -2 * pi;
b = 2 * pi;
N = 11;

% Generarea nodurilor echidistante
nodes = linspace(a, b, N)';

% Calcularea valorilor funcției în nodurile respective
y = f(nodes);

% Definirea funcțiilor de bază pentru metoda celor mai mici pătrate
functions = @(x) [ones(size(x)), x, x.^2, x.^3, x.^4, x.^5, x.^6, x.^7, sin(2*x)];

% Definirea punctelor pentru aproximare
points = linspace(a, b, 1000)'; % 1000 de puncte pe intervalul dat pentru a afișa graficul

% Aproximarea folosind metoda celor mai mici pătrate
approximated_values = least_squares_approx(nodes, y, functions, points);

% Afișarea rezultatelor
plot(points, f(points), 'b', 'LineWidth', 1.5);
hold on;
plot(points, approximated_values, 'r--', 'LineWidth', 1);
plot(nodes, y, 'ko');
legend('f(x)', 'Aproximare (celor mai mici pătrate)', 'Noduri echidistante');
xlabel('x');
ylabel('f(x)');
title('Aproximarea funcției f(x) = x^2 * sin(x)');
grid on;
hold off;
