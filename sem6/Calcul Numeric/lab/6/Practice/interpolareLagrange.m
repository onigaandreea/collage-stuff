function [val] = interpolareLagrange(nodes, nodevals, point)
    val = 0;
    m = length(nodes); % Numărul de noduri

    for k = 1 : m
        f = nodevals(k); % f(xk)
        u = 1; d = 1;
        for j = 1 : m
            if j ~= k
                u = u .* (point - nodes(j)); % x - xj
                d = d * (nodes(k) - nodes(j)); % xk - xj
            end
        end
        val = val + f * (u / d);
    end
end

