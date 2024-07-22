clc;
clear;
syms t x;

% Definirea funcției
f_t = sqrt(3*t - t^2 - 2) * sin(t);

% Schimbarea de variabilă t = (x + 3) / 2
% t devine (x + 3) / 2, deci x = 2*t - 3
t_subs = (x + 3) / 2;

% Înlocuirea lui t cu (x + 3) / 2 în funcția originală
f_x = subs(f_t, t, t_subs);

% Determinarea elementului diferențial dt în funcție de dx
dt_dx = diff(t_subs, x);

% Transformarea integralei
integ_x = f_x * dt_dx;

% Simplificarea integralei
integ_x = simplify(integ_x);

% Afișarea noii funcții și a integralei transformate
disp('Funcția după schimbarea de variabilă:');
disp(integ_x);

% Calcularea integralei pe intervalul corespunzător
% Dacă t este în intervalul [1, 2], atunci x va fi în intervalul [-1,1]

new_a = -1;
new_b = 1;

% Calcularea integralei în noul interval
I = int(integ_x, x, new_a, new_b);
I = double(I);

% Afișarea rezultatului integralei
disp('Valoarea integralei după schimbarea de variabilă:');
disp(I);


% Definirea ponderii și a funcției
w = sqrt(1 - x^2);
f = (1/4) * sin((x + 3) / 2);

% Definire interval de lucru și precizie
a = -1;
b = 1;
err = 1e-10;

% Transformă funcția simbolică într-un handle de funcție
f_handle = matlabFunction(f);
w_handle = matlabFunction(w);

% Calcularea exactă a integralei
integral = double(int(f * w, x, a, b));

% Inițializarea variabilelor pentru cuadratura de tip Gauss-Cebîșev2
n = 1;
quad = gauss_quad_num(f_handle, n);

% Crește iterativ n-ul până când toleranța erorii este îndeplinită
while abs(integral - quad) > err
    n = n + 1;
    quad = gauss_quad_num(f_handle, n);
end

% Afișare rezultate
disp('Valoarea cuadraturii:');
disp(integral);
disp('Număr de iterații:');
disp(n);
