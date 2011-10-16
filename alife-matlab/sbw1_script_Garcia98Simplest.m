%simple walking%
%theta0 = -0.2;
%thetap0 = -0.2166;
%gamma = 0.009;

%limping%
theta0 = -0.23;
thetap0 = -0.25;
gamma = 0.017;

maxiters = 6;

refine = 1;
options = odeset('Events',@swb_test,...
                 'Refine',refine,...
                 'RelTol',1e-12,...
                 'AbsTol',[1e-12 1e-12 1e-12 1e-12]);
t0 = 0;
tfinal = 30;
qprev = [theta0 0 thetap0 0];
%set(gca,'xlim',[tstart tfinal],'ylim',[-4 4]);
box on
hold on;
%[t,q,te,ye,ie] = ode45(@sbw1,[t0 tfinal],q0, options);
%n1 = length(t);
%t1 = t(n1);
tprev = t0;
tt=[];
qt=[];
tet=[];
yet=[];
ien = 1;
n=1;
while(ien == 1 && n<=maxiters)
    qinit = sbw1_switch(qprev);
    [tn,qn,ten,yen,ien] = ode45(@(t,y) sbw1(t,y,gamma),[t0 tfinal],qinit,options);
    tt = [tt;tprev+tn];
    qt = [qt;qn];
    tet = [tet;tprev+ten];
    yet = [yet;yen];
    num = length(tn);
    tprev = tprev+tn(num);
    qprev = qn(num,:);
    n=n+1;
end    
plot(tt,mod(qt(:,1:2)+pi,2*pi)-pi);
plot(tet,mod(yet(:,1:2)+pi,2*pi)-pi,'ro');
xlabel('time');
ylabel('angular positions');
title('Simplest Biped Walking');
hold off;