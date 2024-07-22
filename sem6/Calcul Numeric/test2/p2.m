% Definirea funcției f(x) = x^2 * sin(x)
f = @(x) x.^2 .* sin(x);

% Definirea intervalului
a = -2*pi;
b = 2*pi;

% Generarea nodurilor echidistante și Cebâșev de speța a II-a
N = 12;
nodes_equidistant = linspace(a, b, N);
nodes_chebyshev = sort(cos((2*(0:N-1)'+1)*pi/(2*N))*(b-a)/2 + (a+b)/2);

% Aproximarea folosind spline-uri deBoor cu noduri echidistante
cubic_spline_equidistant = deBoor(nodes_equidistant, f(nodes_equidistant));

% Aproximarea folosind spline-uri deBoor cu noduri Cebâșev
cubic_spline_chebyshev = deBoor(nodes_chebyshev, f(nodes_chebyshev));

% Generarea unui set de puncte pentru reprezentarea grafică
x_values = linspace(a, b, 1000);
y_values = f(x_values);

% Calcularea aproximărilor pentru spline-uri
y_approx_equidistant = evalsplinec(nodes_equidistant, cubic_spline_equidistant, x_values);
y_approx_chebyshev = evalsplinec(nodes_chebyshev, cubic_spline_chebyshev, x_values);

% Afișarea rezultatelor
figure;
plot(x_values, y_values, 'b', 'LineWidth', 1.5);
hold on;
plot(x_values, y_approx_equidistant, 'r--', 'LineWidth', 1);
plot(x_values, y_approx_chebyshev, 'g-.', 'LineWidth', 1);
plot(nodes_equidistant, f(nodes_equidistant), 'ko');
legend('f(x)', 'Spline cubic (echidistante)', 'Spline cubic (Cebâșev)');
xlabel('x');
ylabel('f(x)');
title('Aproximarea funcției f(x) = x^2 * sin(x)');
grid on;
hold off;

