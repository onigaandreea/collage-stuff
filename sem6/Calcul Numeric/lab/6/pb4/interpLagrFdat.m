function [ val ] = interpLagrFdat( x, f, m, nodes )
%4. Dandu-se x, f, m si nodurile, aproximati f(x) utilizand interpolarea
%Lagrage
considered = nodes(1 : m);
consideredVals = f(considered);

val = interpolareLagrange(considered, consideredVals, x);
end

