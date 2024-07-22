function X = AproxSucSistem(f, J, x0, epsilon, N)
  i = 1;
  lambda = -inv(J(x0));
  while (i <= N)
    x1 = x0 + (lambda * f(x0))';
    if norm(x1 - x0) < epsilon
      X = x1;
      return
    endif
    i = i + 1;
    x0 = x1;
  endwhile
  X = x0;  % Returnează ultima aproximare dacă nu s-a atins eroarea dorită
  warning('Numarul maxim de iteratii a fost atins fara a atinge eroarea dorita');
endfunction

