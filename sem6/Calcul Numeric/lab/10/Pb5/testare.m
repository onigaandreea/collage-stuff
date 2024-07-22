int = integral(@sin, 0, pi)
res = adquad(@sin, 0, pi, 0.000001)

int2 = integral(@exp, 0, 10)
r2 = adquad(@exp, 0, 10, 0.001)

% Integral of e^(-x^2) from 0 to 1
int3 = integral(@(x) exp(-x.^2), 0, 1)
r3 = adquad(@(x) exp(-x.^2), 0, 1, 0.000001)
