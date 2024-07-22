nodes = [0 1 2];
nodevals = exp(nodes);

t = 1 : 0.01 : 2;
res = interpolareHermite(nodes, nodevals, nodevals, t);

plot(t, res, 'red-');
hold on;
resexp = exp(t);
plot(t, resexp, 'blue--');
hold off;
