addpath(genpath('../pb5'))

x = [1900   1910   1920    1930    1940    1950    1960    1970    1980];
y = [75.995 91.972 105.711 123.203 131.669 150.697 179.323 203.212 226.505];

% Pentru estimarea populatiei din anul 1975 am ales datele din 1900 pana in
% 1990. Rezultatul este 215.759, cu marja de eroare 0.22 fata de rezultatul
% recensamantului din 1975, care a fost de 215.97
interpolareLagrangeBaricentrica(x, y, 1975)

x = [1990    2000    2010];
y = [249.633 281.422 308.786];

%Pentru anul 2018 am ales datele din 1990 pana in 2010, deoarece este
%nevoie de valori cat mai apropiate pentru a ajunge la un rezultat
%favorabil. Rezultatul este 327.491, cu o marja de eroare de aproximativ
%0.2 fata de rezultatul actual din 2018, 327.2
interpolareLagrangeBaricentrica(x, y, 2018)

