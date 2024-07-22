function [ res ] = Simpson( f, a, b, n )
    if mod(n, 2) ~= 0
        error('n must be an even number.');
    end
    h = (b - a) / n;
    x = a + h * (0:n);
    sum1 = 0;
    for k = 2:2:n-2
        sum1 = sum1 + f(x(k));
    end
    sum2 = 0;
    for k = 1:2:n
        sum2 = sum2 + f(x(k));
    end
    res = (h / 3) * (f(a) + f(b) + 2 * sum1 + 4 * sum2);
end
