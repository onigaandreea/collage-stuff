function [ res ] = least_squares_approx( x, y, functions, points )
    % metoda celor mai mici patrate
    % x, y datele de antrenament ; y = f(x)
    % functions - functiile de baza
    % points - punctele de aproximat

    phi = functions(x);
    phiApprox = functions(points);

    [n, ~] = size(phi); % nr pct * nr functii

    % A = Z^T * Z ; B = Z^T * y ; unde Z^T e phi
    A = phi' * phi;
    B = phi' * y;

    % A*a=B
    a = linsolve(A, B);

    res = phiApprox * a;
end

