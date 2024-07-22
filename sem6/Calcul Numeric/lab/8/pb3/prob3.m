[x,y]=ginput();
n=length(x);
tn=0:n-1;
tg=linspace(0,n-1,1000);
xg=spline(tn,x,tg);
yg=spline(tn,y,tg);
plot(x,y,'o',xg,yg);