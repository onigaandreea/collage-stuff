function c=deBoor(x,f,der)
%call c=deBoor(x,f,der)
%x - abscissas
%f - ordinates
%der - values of derivatives
%c - coefficients (length(x)-1) by 4 matrix, in decreasing order/line
%Method - see Gautschi - Numerical Analysis. An Introduction. 2nd edition
% Birkhauser

  n=length(x);
  if any(diff(x)<0), [x,ind]=sort(x); else ind=1:n; end %order nodes if needed
  y=f(ind); x=x(:); y=y(:);
%auxiliary unknowns - values of the spline derivative
  dx=diff(x);  %compute delta_x
  ddiv=diff(y)./dx; %divided differences
  ds=dx(1:end-1); dd=dx(2:end); %prepare diagonals
  dp=2*(ds+dd);
  md=3*(dd.*ddiv(1:end-1)+ds.*ddiv(2:end)); %rhs
%deBoor
  x31=x(3)-x(1);xn=x(n)-x(n-2);
  dp1=dx(2); dpn=dx(end-1);
  vd1=x31;
  vdn=xn;
  md1=((dx(1)+2*x31)*dx(2)*ddiv(1)+dx(1)^2*ddiv(2))/x31;
  mdn=(dx(end)^2*ddiv(end-1)+(2*xn+dx(end))*dx(end-1)*ddiv(end))/xn;

%sparse system
  dp=[dp1;dp;dpn];
  dp1=[0;vd1;dd];
  dm1=[ds;vdn;0];
  md=[md1;md;mdn];
  A=spdiags([dm1,dp,dp1],-1:1,n,n);
  m=A\md;
  c(:,4)=y(1:end-1);
  c(:,3)=m(1:end-1);
  c(:,1)=(m(2:end)+m(1:end-1)-2*ddiv)./(dx.^2);
  c(:,2)=(ddiv-m(1:end-1))./dx-dx.*c(:,1);
end
