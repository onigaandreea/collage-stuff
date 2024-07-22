function [z, ni] = ModifiedNewton(f, fd, fd2, x0, ea, er, nmax)
    if nargin < 7
        nmax = 50;
    end
    if nargin < 6
        er = 0;
    end
    if nargin < 5
        ea = 1e-3;
    end

    xp = x0;

    for k = 1:nmax
        % Calculul noii aproximări
        xc = xp - 2 * f(xp) / (fd(xp) * (1 + sqrt(1 - 2 * f(xp) * fd2(xp) / fd(xp)^2)));

        % Verificarea condiției de oprire
        if abs(xc - xp) < ea + er * abs(xc)
            z = xc;  % Succes
            ni = k;
            return
        end

        xp = xc;
    end

    error('S-a depășit numărul maxim de iterații');
end

