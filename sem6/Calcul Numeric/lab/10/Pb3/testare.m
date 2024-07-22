addpath(genpath('../Pb1'))

adaptare = adapt(@sin, 0, pi, 0.0001, @Trapez)
adaptare2 = adapt(@sin, 0, pi, 0.0001, @Dreptunghi)
adaptare3 = adapt(@sin, 0, pi, 0.0001, @Simpson)
