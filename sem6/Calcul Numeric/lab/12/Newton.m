function p = Newton(f, df, x0, tol, max_iter)
  if nargin < 5
    max_iter = 100;  % Numărul maxim de iterații implicit
  endif

  xold = x0;
  xnew = xold - f(xold) / df(xold);  % Prima iterație

  iter = 1;
  while abs(xnew - xold) > tol
    if iter > max_iter
      warning('Numărul maxim de iterații a fost atins fără a atinge toleranța dorită');
      break;
    endif

    xold = xnew;
    xnew = xold - f(xold) / df(xold);  % Iterația Newton-Raphson
    iter += 1;
  endwhile

  p = xnew;
endfunction

