function plotUtil(data)
h=1/17;
z = size(data,2);
d=[data(:,1:5) data(:,z)];
figure;
plot(d(:,1),d(:,2),'k-',d(:,1),d(:,3),'r--');
title('System''s ODE evolution');
xlabel(sprintf('t'));
legend('theta','phi');
figure;
plot(d(:,1),d(:,6),'k-');
title('k_{phi} values.');
xlabel(sprintf('t'));
ylabel(sprintf('k_{phi}'));
