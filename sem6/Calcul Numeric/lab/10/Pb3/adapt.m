function [ res ] = adapt( f, a, b, eps, met )
    m = 6;  % Starting number of subintervals
    if abs(met(f, a, b, m) - met(f, a, b, 2 * m)) < eps
        res = met(f, a, b, 2 * m);
    else
        res = adapt(f, a, (a + b) / 2, eps, met) + adapt(f, (a + b) / 2, b, eps, met);
    end
end
