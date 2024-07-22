pkg load symbolic

syms A1 A2 A3 A4 t3 t4

% Definirea ecuațiilor
eq1 = A1 + A3 + A4 - 2 == 0;
eq2 = -A1 + A2 + t3*A3 + t4*A4 == 0;
eq3 = A1 + t3^2*A3 + t4^2*A4 - 2/3 == 0;
eq4 = -A1 + A2 + t3^3*A3 + t4^3*A4 == 0;
eq5 = A1 + t3^4*A3 + t4^4*A4 - 2/5 == 0;
eq6 = -A1 + A2 + t3^5*A3 + t4^5*A4 == 0;

% Rezolvarea sistemului de ecuații
[A1, A2, A3, A4, t3, t4] = solve([eq1, eq2, eq3, eq4, eq5, eq6],
[A1, A2, A3, A4, t3, t4])

% Afișarea rezultatelor
disp('Coeficienți:');
disp([A1_sol, A2_sol, A3_sol, A4_sol]);
disp('Puncte de evaluare:');
disp([t3_sol, t4_sol]);

