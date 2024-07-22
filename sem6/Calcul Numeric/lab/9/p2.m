x = [-1.024940 -0.949898 -0.866114 -0.773392 -0.671372 -0.559524 -0.437067 -0.302909 -0.159493 -0.007464];
y = [-0.389269 -0.322894 -0.265256 -0.216557 -0.177152 -0.147582 -0.128618 -0.121353 -0.127348 -0.148895];

% Model parabolic
phi_parabolic = @(x) [ones(1, length(x)); x.^2];

xAprox = linspace(min(x), max(x), numel(y));

% Calculul aproximării pentru modelul parabolic
yAprox_parabolic = least_squares_approx(x, y, phi_parabolic, xAprox);

% Calculul erorilor pentru modelul parabolic
MSE_parabolic = mean((y-yAprox_parabolic).^2);
RMSE_parabolic = sqrt(MSE_parabolic);

disp(['MSE pentru modelul parabolic: ' num2str(MSE_parabolic)]);
disp(['RMSE pentru modelul parabolic: ' num2str(RMSE_parabolic)]);

% Model elipsoidal
phi_elipsoidal = @(x) [ones(1, length(x)); y.^2; x .* y; x; y; ones(1, length(x))];

% Calculul aproximării pentru modelul elipsoidal
yAprox_elipsoidal = least_squares_approx(x, y, phi_elipsoidal, xAprox);

% Calculul erorilor pentru modelul elipsoidal
MSE_elipsoidal = mean((y - yAprox_elipsoidal).^2);
RMSE_elipsoidal = sqrt(MSE_elipsoidal);

disp(['MSE pentru modelul elipsoidal: ' num2str(MSE_elipsoidal)]);
disp(['RMSE pentru modelul elipsoidal: ' num2str(RMSE_elipsoidal)]);

% Trasarea datelor originale și a aproximărilor pentru ambele modele
plot(x, y, 'o', xAprox, yAprox_parabolic, '-', xAprox, yAprox_elipsoidal, '--');
legend('Date observate', 'Model parabolic', 'Model elipsoidal');
xlabel('x');
ylabel('y');
title('Aproximare cu modele parabolic și elipsoidale');

