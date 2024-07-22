function [ res ] = Dreptunghi( f, a, b, n )
    h = (b - a) / n;
    x = a + h * (0:n-1) + h/2;  % Midpoints
    sum = 0;
    for k = 1 : n
        sum = sum + f(x(k));
    end
    res = h * sum;
end

