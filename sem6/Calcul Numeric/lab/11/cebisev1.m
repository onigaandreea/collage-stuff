function [nod, coef] = cebisev1 (n)

  coef = pi/n*ones(1,n);
  nod = cos(pi*((1:n)'-0.5)/n);

end
