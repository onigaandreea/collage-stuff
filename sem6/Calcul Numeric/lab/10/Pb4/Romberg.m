function [ res ] = Romberg( f, a, b, eps, max_it )

R = zeros(max_it, max_it);
h = b - a;
R(1,1) = ((f(a) + f(b)) / 2) * h; 

for k  = 2 : max_it
    
   n_nodes = 1 : 2^(k-2);
   nodes = a + (n_nodes - 1/2) * h;
   R(k, 1) = 1/2 * (R(k-1, 1) + h * sum(f(nodes)));
   
   plj = 4;
   for j = 2 : k
      R(k,j) = (plj * R(k,j-1) - R(k-1,j-1)) / (plj - 1);
      plj = plj * 4;
   end
   if (abs(R(k,k) - R(k-1,k-1)) < eps) && (k > 3)
      res = R(k,k);
      return
   end
   
   h = h / 2;
   
end
error('Numarul de iteratii a fost atins')
end
