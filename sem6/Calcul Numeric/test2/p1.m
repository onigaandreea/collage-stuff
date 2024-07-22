% Definirea functiei f(x) = sin(x^2)
f = @(x) sin(x.^2);

% Generarea nodurilor Cebâșev
N = 9;
nodes = sort(2*pi*cos((2*(0:N)+1)/(2*(N+1))*pi));
nodevals = f(nodes);

% Definirea lui t = pi/5
t = pi/5;

% Interpolare Lagrange
lagrange_interp = interpolareLagrange(nodes, nodevals, t);

% Interpolare Hermite
% Pentru derivata primei functiei, vom folosi derivata functiei f(x) = sin(x^2)
fd = @(x) 2*x.*cos(x.^2);
hermite_interp = interpolareHermite(nodes, nodevals, fd, t);

% Calcularea valorii exacte a lui f(t)
exact_value = f(t);

% Calcularea erorilor teoretice
lagrange_theoretical_error = abs(exact_value - lagrange_interp);
hermite_theoretical_error = abs(exact_value - hermite_interp);

% Calcularea erorilor practice
lagrange_practical_error = abs(f(t) - lagrange_interp);
hermite_practical_error = abs(f(t) - hermite_interp);

% Afisarea rezultatelor
fprintf('Rezultate pentru t = pi/5 cu %d noduri:\n', N);
fprintf('Valoarea exacta a lui f(t): %.6f\n', exact_value);
fprintf('Interpolare Lagrange: %.6f\n', lagrange_interp);
fprintf('Eroarea teoretica Lagrange: %.6f\n', lagrange_theoretical_error);
fprintf('Eroarea practica Lagrange: %.6f\n', lagrange_practical_error);
fprintf('Interpolare Hermite: %.6f\n', hermite_interp);
fprintf('Eroarea teoretica Hermite: %.6f\n', hermite_theoretical_error);
fprintf('Eroarea practica Hermite: %.6f\n', hermite_practical_error);

% Afișarea graficului
x_values = linspace(-2*pi, 2*pi, 1000);
y_values = f(x_values);

% Inițializăm variabilele fără a afișa valorile
lagrange_interp_values = interpolareLagrangeMultiplePoints(nodes, nodevals, x_values);
hermite_interp_values = interpolareHermite(nodes, nodevals, fd, x_values);

plot(x_values, y_values, '');
hold on;
plot(x_values, lagrange_interp_values, 'r-');
plot(x_values, hermite_interp_values, 'g-');
plot(nodes, nodevals, 'ko');
##legend('f(x)', 'Interpolare Lagrange', 'Interpolare Hermite', 'Noduri de interpolare', 'Location', 'best');
##xlabel('x');
##ylabel('f(x)');
title('Interpolare Lagrange si Hermite pentru f(x) = sin(x^2)');
grid on;
hold off;


