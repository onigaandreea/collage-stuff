function [x,ni] = pb2(A,b,x0,w,ea,er,nitmax)
     % Ax=b
     % ea - eroare abs
     % er - eroare relativa
     % nitmax - nr max de iteratii
     D = diag(diag(A));
     L = D-tril(A);
     M=D/w - L;
     N=M-A;
     T=M\N;
     c=M\b;
     for k=1:nitmax
       x=T*x0+c;
       if norm(x-x0)<ea-er*norm(x)
         ni = k;
         return;
       end
       x0=x;
     end
     display(x);
     error('s-a depasit nr maxim de iteratii');  
end