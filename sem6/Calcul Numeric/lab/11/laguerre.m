function [nod,coef] = laguerre (n,x,y)

  if n<3
    x=0;
  end

  k=1:n-1;
  a=[x+1, 2*k+x+1];
  b=[gamma(1+x),k.*(k+x)];

  [nod,coef] = gauss(a,b);

end
