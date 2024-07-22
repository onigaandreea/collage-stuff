function [ val ] = interpolareLagrange( nodes, nodevals, point )
    val = 0;

    [lin, col] = size(nodes);
    m = col;
    x = point;

    for k = 1 : m
        f = nodevals(k); % f(xk)

        % Calculăm l_k(x)
        u = 1; d = 1;
        for j = 1 : m
            if j ~= k
                u = u * (x - nodes(j)); % x - xj
                d = d * (nodes(k) - nodes(j)); % xk - xj
            end
        end

        val = val + f * (u / d);
    end
end

