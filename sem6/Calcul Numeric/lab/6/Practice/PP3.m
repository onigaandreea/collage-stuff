addpath(genpath('../pb4'))

% Am ales ca valoriel nodurilor sa fie cuprinse intre 100 si 121, deoarece
% 115 este cuprins intre aceste patrate perfecte consecutive. Valoarea lui
% sqrt(115) este aproximativ egala cu 10.7238

% Pentru aproximarea radicalului cu 3 zecimale exacte, am ales ca noduri
% cele 2 capete si o valoare apropiata de 115, 113. Pentru interpolarea
% Lagrange cu m=3, rezultatul va fi 10.7239
disp("--- m=3 ---")
xs = interpLagrFdat(115, @sqrt, 3, [100 113 121]);
disp(xs);
s = sqrt(115);
disp(s);


% Pentru a ajunge la un rezultat cat mai exact, este nevoie de adaugarea
% mai multor noduri si impliciit marirea gradului. De exemplu, pentru
% nodurile [100 107 117 121] si m=4, rezultatul interpolarii devine 10.7238
disp("--- m=4 ---")
xs = interpLagrFdat(115, @sqrt, 4, [100 107 117 121]);
disp(xs);
s = sqrt(115);
disp(s);
