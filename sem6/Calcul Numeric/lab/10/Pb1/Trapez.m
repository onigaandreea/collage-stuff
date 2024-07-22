function [ res ] = Trapez( f, a, b, n )
    h = (b - a) / n;
    x = a + h * (0:n);
    sum = 0;
    for k = 2 : n
        sum = sum + f(x(k));
    end
    res = (h / 2) * (f(a) + f(b) + 2 * sum);
end
