function [nod,coef] = hermite (n)

  b = [sqrt(pi),(1:n-1)/2];
  a=zeros(n,1);
  
  [nod,coef] = gauss(a,b);

end
