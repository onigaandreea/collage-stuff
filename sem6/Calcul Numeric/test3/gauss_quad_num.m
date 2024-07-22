function [I,nodes,coefs]=gauss_quad_num(f,n)
  [alpha,beta]=orto_coef_num(0:n-1);
  J=diag(alpha)+diag(sqrt(beta(2:end)),-1)+diag(sqrt(beta(2:end)),1);
  [V,nodes]=eig(J,'vector');
  coefs=beta(1)*V(1,:).^2;
  I=coefs*f(nodes);
  nodes=nodes';
endfunction
