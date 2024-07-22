% problema 4; model polinomial de grad 3
x = [1900:10:2010];
y = [75.995, 91.972, 105.710, 123.200, 131.670, 150.700, 179.320, 203.210, 226.510, 249.630, 281.420, 308.790];
phi = @(x)[ones(1, length(x)); x; x.^2; x.^3];

xAprox = x(1):(x(length(x))-x(1))/100:x(length(x));
yAprox = least_squares_approx(x, y, phi, xAprox);

plot(x, y, 'o', xAprox, yAprox, '-');
