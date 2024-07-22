addpath(genpath('../Pb1'))
addpath(genpath('../Pb5'))

% Define the test functions
f1 = @(x) exp(-x.^2);
f2 = @(x) sin(x.^2);
f3 = @(x) cos(x.^3);
f4 = @(x) log(x);

% Define the intervals
interval1 = [0, 1];
interval2 = [0, pi];
interval3 = [0, 1];
interval4 = [0.0001, 1];  % Avoid log(0) which is undefined

% Set tolerance
tol = 1e-6;

% Perform integration using integral function
int1 = integral(f1, interval1(1), interval1(2));
int2 = integral(f2, interval2(1), interval2(2));
int3 = integral(f3, interval3(1), interval3(2));
int4 = integral(f4, interval4(1), interval4(2));

% Perform integration using my functions
res1 = Dreptunghi(f1, interval1(1), interval1(2), 100);
res2 = Trapez(f2, interval2(1), interval2(2), 100);
res3 = Simpson(f3, interval3(1), interval3(2), 100);
res4 = adquad(f4, interval4(1), interval4(2), tol);

% Display results
fprintf('Integral of e^(-x^2) from 0 to 1:\n');
fprintf('Integral: %f\n', int1);
fprintf('adquad result: %f\n\n', res1);

fprintf('Integral of sin(x^2) from 0 to pi:\n');
fprintf('integral: %f\n', int2);
fprintf('adquad result: %f\n\n', res2);

fprintf('Integral of cos(x^3) from 0 to 1:\n');
fprintf('integral: %f\n', int3);
fprintf('adquad result: %f\n\n', res3);

fprintf('Integral of log(x) from 0.0001 to 1:\n');
fprintf('integral: %f\n', int4);
fprintf('adquad result: %f\n\n', res4);
