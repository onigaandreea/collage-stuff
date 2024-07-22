function [nod,coef] = jacobi (n,x,y)

  k1 = 1:n-1;
  k2 = 2:n-1;

  b1=4*(1+x)*(1+y)/((2+x+y)^2)/(3+x+y);
  b=[2^(x+y+1)*beta(x+1,y+1), b1, 4*k2.*(k2+x+y).*(k2+x).*...
        (k2+y)./(2*k2+x+y-1)./(2*k2+x+y).^2./(2*k2+x+y+1)];

  if x == y
    a = zeros(1,n);
  else
    a = [(y-x)./(x+y+2),(y^2-x^2)./(2*k1+x+y)./(2*k1+x+y+2)];
  end
  
  [nod,coef] = gauss(a,b);

end
