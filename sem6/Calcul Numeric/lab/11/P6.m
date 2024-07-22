%intervalul integralei
    a = -Inf;
    b = Inf;
    n = 10;

    f1 = @(x) exp(-x.^2).*sin(x);
    f2 = @(x) exp(-x.^2).*cos(x);

    [nod,coef] = hermite(n);
    disp("pentru integrala cu sin:");
    I2_sin = integral(f1,a,b)
    I2_sin_hermite = coef*f1(nod)
    disp("-------------------------------");
    disp("pentru integrala cu cos:");
    I2_cos = integral(f2,a,b)
    I2_cos_hermite = coef*f2(nod)

