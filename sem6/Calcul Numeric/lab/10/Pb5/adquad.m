function [ res ] = adquad( f, a, b, eps )
res = quadstep(f, a, b, eps, f(a), f((a+b)/2), f(b));
end