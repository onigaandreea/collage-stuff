% Funcție pentru rezolvarea unui sistem de ecuații liniare folosind metoda lui Gauss
function [num_operations, time_elapsed] = solve_system(n)
    % Generăm o matrice de coeficienți aleatoare și un vector de termeni liberi aleatorii
    A = randn(n);
    b = randn(n, 1);

    % Măsurăm timpul inițial
    tic;

    % Aplicăm metoda lui Gauss
    for i = 1:n
        % Pivotare parțială pentru a evita divizarea la zero
        [~, pivot_row] = max(abs(A(i:n, i)));
        pivot_row = pivot_row + i - 1;
        % Schimbăm rândurile
        temp = A(i, :);
        A(i, :) = A(pivot_row, :);
        A(pivot_row, :) = temp;
        temp = b(i);
        b(i) = b(pivot_row);
        b(pivot_row) = temp;

        % Eliminare gaussiana
        for j = i+1:n
            factor = A(j, i) / A(i, i);
            A(j, :) = A(j, :) - factor * A(i, :);
            b(j) = b(j) - factor * b(i);
        end
    end

    % Substituție înapoi
    x = zeros(n, 1);
    for i = n:-1:1
        x(i) = (b(i) - A(i, i+1:n) * x(i+1:n)) / A(i, i);
    end

    % Măsurăm timpul final și numărul total de operații elementare
    time_elapsed = toc;
    num_operations = n^3 / 3 + n^2 / 2 + n / 6; % Formula pentru numărul total de operații în metoda Gauss
end
