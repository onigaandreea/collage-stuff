function [nod, coef] = cebisev2(n)

  b = [pi/2, 1/4 * ones(1, n - 1)];
  a = zeros(n, 1);
  [nod, coef] = gauss(a, b);

end

