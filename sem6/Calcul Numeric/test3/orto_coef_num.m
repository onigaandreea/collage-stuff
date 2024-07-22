function [alpha,beta]=orto_coef_num(k)
  n=length(k);
  alpha=zeros(1,n); beta=[];
  %case for 'Cebisev2'
    for i=1:n
      if k(i)==0
        beta(i)=pi/2;
      else
        beta(i)=1/4;
      endif
    endfor
endfunction
