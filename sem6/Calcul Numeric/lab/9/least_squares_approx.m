function [ res ] = least_squares_approx( x, y, functions, points )
    % least_squares_approx - method of least squares
    % x, y - training data; y = f(x)
    % functions - basis functions
    % points - points to approximate

    phi = functions(x);
    phiApprox = functions(points);

    [n, m] = size(phi);
    [nAprox, ~] = size(phiApprox);

    if n ~= nAprox
        error('Dimension mismatch between training data and approximation points.');
    end

    % A = Z^T * Z ; B = Z^T * y ; where Z^T is phi
    A = phi' * phi;
    B = phi' * y;

    % A*a=B
    a = linsolve(A, B);

    res = phiApprox * a;
end

