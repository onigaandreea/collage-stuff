% Numărul de sisteme de ecuații de rezolvat
num_systems = 100;
dimensions = 4; % Dimensiunea sistemelor de ecuații

num_operations_list = zeros(num_systems, 1);
time_elapsed_list = zeros(num_systems, 1);

% Rezolvăm fiecare sistem și înregistrăm numărul de operații și timpul
for i = 1:num_systems
    [num_operations, time_elapsed] = solve_system(dimensions);
    num_operations_list(i) = num_operations;
    time_elapsed_list(i) = time_elapsed;
end

% Graficăm rezultatele
figure;
subplot(2, 1, 1);
plot(1:num_systems, num_operations_list, 'b.-');
title('Numărul de operații elementare');
xlabel('Numărul sistemului');
ylabel('Numărul de operații');

subplot(2, 1, 2);
plot(1:num_systems, time_elapsed_list, 'r.-');
title('Timpul de rezolvare');
xlabel('Numărul sistemului');
ylabel('Timpul (secunde)');

