
function [nod,coef] = legendre(n)
  
  b = [2,(4-(1:n-1).^(-2)).^(-1)];
  a = zeros(n,1);
  
  [nod,coef] = gauss(a,b);
  
end
 