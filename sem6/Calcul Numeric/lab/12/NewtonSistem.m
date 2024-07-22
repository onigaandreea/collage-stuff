function p = NewtonSistem(f, J, x0, tol, max_iter)
  if nargin < 5
    max_iter = 100;  % Numărul maxim de iterații implicit
  endif

  xold = x0;
  xnew = xold - J(xold) \ f(xold);  % Folosim operatorul de backslash pentru a rezolva sistemul

  iter = 1;
  while norm(xnew - xold) > tol
    if iter > max_iter
      warning('Numărul maxim de iterații a fost atins fără a atinge toleranța dorită');
      break;
    endif

    xold = xnew;
    xnew = xold - J(xold) \ f(xold);  % Folosim operatorul de backslash pentru a rezolva sistemul
    iter += 1;
  endwhile

  p = xnew;
endfunction

