function plotPS(data)
figure;
plot(data(:,2),data(:,4),'ro',data(:,2),data(:,4),'k-');
axis([0,0.4,-0.4,0]);
xlabel('theta');
ylabel('theta dot');