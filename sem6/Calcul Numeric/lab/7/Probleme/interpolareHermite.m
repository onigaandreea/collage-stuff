function [ interp_values ] = interpolareHermite(x, f, fd, points )
    [rows, m] = size(x);
    q = zeros(2 * m + 2, 2 * m + 2);
    z = zeros(2 * m + 2);

    for i = 1 : m
        z(2 * i - 1) = x(i);
        z(2 * i) = x(i);
        q(2 * i - 1, 1) = f(i);
        q(2 * i, 1) = f(i);
        q(2 * i, 2) = fd(i);
        if i ~= 1
            q(2*i-1,2) = (q(2*i-1,1) - q(2*i-2,1)) / (z(2*i-1) - z(2*i-2));
        end
    end

    for i = 3 : 2 * m
        for j = 3 : i
            q(i,j) = (q(i,j-1) - q(i-1,j-1)) / (z(i) - z(i-j+1));
        end
    end

    n = length(points);
    interp_values = zeros(1, n);
    for k = 1 : n
        p = q(1,1);
        for i = 2 : 2 * m + 1
            prod = 1;
            for j = 1 : i - 1
                prod = prod * (points(k) - z(j));
            end
            p = p + q(i, i) * prod;
        end
        interp_values(k) = p;
    end
end

