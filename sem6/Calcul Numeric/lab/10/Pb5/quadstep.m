function [ res ] = quadstep( f, a, b, eps, fa, fc, fb )

h = b - a;
c = (a + b) / 2;
fd = f((a + c) / 2);
fe = f((c + b) / 2);
S1 = h/6 * (fa + fb + 4 * fc);
S2 = h/12 * (fa + 4 * fd + 2 * fc + 4 * fe + fb);
if abs(S1 - S2) < eps
    res = S2 + (S2 - S1) / 15;
else
    res = quadstep(f, a, c, eps, fa, fd, fc) + quadstep(f, c, b, eps, fe, fc, fb);
end

end
