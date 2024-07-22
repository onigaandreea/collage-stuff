function [ vals ] = interpolareLagrangeMultiplePoints( nodes, nodevals, points )
vals = zeros(size(points));
[lin, col] = size(points);
for i = 1 : col
   vals(1, i) = interpolareLagrange(nodes, nodevals, points(1, i)); 
end
end

