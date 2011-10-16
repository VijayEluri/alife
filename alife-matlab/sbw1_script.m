tstart = 0;
tfinal = 30;
theta0 = 0.2;
thetap0 = -0.19951;
q0 = [theta0; 2*theta0; thetap0; thetap0*(1-cos(2*theta0))];
refine = 1;
options = odeset('Events',@swb_test,...
                 'Refine',refine,...
                 'RelTol',1e-12,...
                 'AbsTol',[1e-12 1e-12 1e-12 1e-12]);

%set(gca,'xlim',[tstart tfinal],'ylim',[-4 4]);
box on
hold on;
[t,y,te,ye,ie] = ode45(@sbw1,[tstart tfinal],q0, options);
te
ye(:,1:2)
plot(t,mod(y(:,1:2)+pi,2*pi)-pi);
plot(te,mod(ye(:,1:2)+pi,2*pi)-pi,'ro');
xlabel('time');
ylabel('angular positions');
title('Simplest Biped Walking');
hold off;