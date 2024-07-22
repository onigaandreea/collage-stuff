function [aproximare, eroare] = grafic()

    % Definirea funcției v (interpolare liniară)
    v = @(x, x0, x1, y0, y1) y0 + (y1 - y0) * (x - x0) / (x1 - x0);

    % Definirea funcției e (exponentială)
    e = @(x) e(x);

    % Valorile cunoscute ale funcției e
    x_valori_cunoscute = [1, 1.1, 1.2, 1.3, 1.4];
    y_valori_cunoscute = e(x_valori_cunoscute);

    % Valorile pentru aproximare
    x_aproximare = 1.25;
    y_aproximare = v(x_aproximare, 1.2, 1.3, e(1.2), e(1.3));

    % Calculul erorii
    eroare = abs(e(1.25) - y_aproximare);

    % Afișarea aproximării și a erorii
    fprintf('Aproximarea f(1.25): %.6f\n', y_aproximare);
    fprintf('Eroare: %.6f\n', eroare);

    % Graficul
    x = 1:0.01:2;
    plot(x, e(x), 'red'); % Funcția e(x)
    hold on;
    plot(x_valori_cunoscute, y_valori_cunoscute, 'o'); % Punctele cunoscute
    plot(x_aproximare, y_aproximare, 'gx'); % Aproximarea pentru f(1.25)
    legend('e(x)', 'Valori cunoscute', 'Aproximare f(1.25)', 'Location', 'best');
    xlabel('x');
    ylabel('f(x)');
    title('Aproximare f(1.25) utilizând interpolare liniară');

end

