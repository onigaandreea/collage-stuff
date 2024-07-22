function x = AproxSuc(f, g, x0, tol, max_iter)
  % f: funcția originală
  % g: funcția derivată pentru metoda aproximărilor succesive
  % x0: valoarea inițială
  % tol: toleranța dorită
  % max_iter: numărul maxim de iterații

  % Inițializăm prima aproximare
  x_old = x0;
  x_new = g(x_old);

  % Iterăm până când diferența este mai mică decât toleranța sau atingem numărul maxim de iterații
  iter = 1;
  while abs(x_new - x_old) > tol
    if iter > max_iter
      warning('Numărul maxim de iterații a fost atins fără a atinge toleranța dorită');
      break;
    endif

    x_old = x_new;
    x_new = g(x_old);
    iter += 1;
  endwhile

  x = x_new;
endfunction

