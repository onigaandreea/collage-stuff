function [z, ni] = Secanta(f, x0, x1, ea, er, Nmax)
  if nargin < 6, Nmax = 50; end
  if nargin < 5, er = 0; end
  if nargin < 4, ea = 1e-3; end

  xv = x0; fv = f(xv); % Evaluăm funcția în x0
  xc = x1; fc = f(xc); % Evaluăm funcția în x1

  for k = 1:Nmax
    xn = xc - fc * (xc - xv) / (fc - fv); % Calculăm noua aproximare folosind formula secantei
    if abs(xn - xc) < ea + er * abs(xn) % Verificăm condiția de oprire
      z = xn; % Rădăcina aproximată
      ni = k; % Numărul de iterații efectuate
      return; % Ieșim din funcție
    endif
    % Actualizăm valorile pentru următoarea iterație
    xv = xc;
    fv = fc;
    xc = xn;
    fc = f(xn);
  endfor

  error('S-a depasit numarul maxim de iteratii'); % Aruncăm eroare dacă se depășește numărul maxim de iterații
endfunction

