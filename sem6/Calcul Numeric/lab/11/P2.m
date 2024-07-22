function P2 ()
  
  %integrala definita pe intervalul [-1;1]
  a = -1; b = 1; 
  n = 10;   

  f = @(x) sin(x.^2);
  [nod,coef] = legendre(n);
  
  I2 = integral(f,a,b)
  I2_legendre = coef*f(nod)
  
end
