function PP4()
    % Definirea funcțiilor Runge și Bernstein
    f = @(x) 1./(1 + x.^2); % Funcția Runge
    g = @(x) abs(x); % Funcția Bernstein

    % Definirea nodurilor echidistante și nodurilor Chebyshev de speța a doua
    n = 10; % Numărul de noduri
    x_eq = linspace(-5, 5, n); % Noduri echidistante pentru contraexemplul lui Runge
    x_cheb = 5*cos((2*(1:n)-1)*pi/(2*n)); % Noduri Chebyshev pentru contraexemplul lui Runge
    m = 10; % Numărul de noduri pentru contraexemplul lui Bernstein
    x_eq_b = linspace(-1, 1, m); % Noduri echidistante pentru contraexemplul lui Bernstein
    x_cheb_b = cos((2*(1:m)-1)*pi/(2*m)); % Noduri Chebyshev pentru contraexemplul lui Bernstein

    % Calculul interpolării Lagrange pentru funcția Runge
    L_eq_f = interpolareLagrange(x_eq, f(x_eq), x_eq);
    L_cheb_f = interpolareLagrange(x_cheb, f(x_cheb), x_cheb);

    % Calculul interpolării Lagrange pentru funcția Bernstein
    L_eq_g = interpolareLagrange(x_eq_b, g(x_eq_b), x_eq_b);
    L_cheb_g = interpolareLagrange(x_cheb_b, g(x_cheb_b), x_cheb_b);

    % Graficele pentru contraexemplul lui Runge
    subplot(2, 2, 1);
    x = linspace(-5, 5, 1000);
    plot(x, f(x), 'LineWidth', 2, 'DisplayName', 'f(x)');
    hold on;
    plot(x_eq, L_eq_f, '--', 'LineWidth', 1.5, 'DisplayName', 'Interpolare echidistanta');
    plot(x_cheb, L_cheb_f, '-.', 'LineWidth', 1.5, 'DisplayName', 'Interpolare Chebyshev');
    legend('Location', 'NorthEast');
    title('Contraexemplul lui Runge - Interpolare Lagrange');
    xlabel('x');
    ylabel('y');
    grid on;

    % Graficele pentru contraexemplul lui Bernstein
    subplot(2, 2, 2);
    x = linspace(-1, 1, 1000);
    plot(x, g(x), 'LineWidth', 2, 'DisplayName', 'g(x)');
    hold on;
    plot(x_eq_b, L_eq_g, '--', 'LineWidth', 1.5, 'DisplayName', 'Interpolare echidistanta');
    plot(x_cheb_b, L_cheb_g, '-.', 'LineWidth', 1.5, 'DisplayName', 'Interpolare Chebyshev');
    legend('Location', 'NorthEast');
    title('Contraexemplul lui Bernstein - Interpolare Lagrange');
    xlabel('x');
    ylabel('y');
    grid on;

end
