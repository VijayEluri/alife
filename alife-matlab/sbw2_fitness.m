function fitness = sbw2_fitness(kk,a)
k=kk';
an = a+(0.5-rand(1,2))/20;
theta0 = 0.4*an(1);
thetap0 = -0.434*an(2);
gamma = 0.004;
%qprev = [theta0 0 thetap0 0];
%qinit = sbw1_switch(qprev);
qinit = [theta0 2*theta0 thetap0 (1-cos(2*theta0))*thetap0];
%fprintf('gamma = %f, theta+ = %f, thetadot+ = %f\n', gamma, qinit(1), qinit(3)); 

%k=[-0;0]; % theta*1 thetap*1
%k=[-0.8;0.45]; % theta*1.3
%k=[0.2;1.5]; % thetap*1.3
%k=[0;1.8]; % thetap*1.3

maxiters = 10;

refine = 1;
options = odeset('Events',@swb_test,...
                 'Refine',refine,...
                 'RelTol',1e-12,...
                 'AbsTol',[1e-12 1e-12 1e-12 1e-12]);
t0 = 0;
tfinal = 20;
%set(gca,'xlim',[tstart tfinal],'ylim',[-4 4]);
%box on
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
while(tprev < tfinal && ien(length(ien)) == 1 && n<=maxiters)
    [tn,qn,ten,yen,ien] = ode45(@(t,y) sbw2(t,y,gamma,k),[t0 tfinal],qinit,options);
    tt = [tt;tprev+tn];
    qt = [qt;qn];
    tet = [tet;tprev+ten];
    yet = [yet;yen];
    num = length(tn);
    tprev = tprev+tn(num);
    qprev = qn(num,:);
    qinit = sbw1_switch(qprev);
    n=n+1;
end
fitness = 0;
if(~isempty(ien) && ien(length(ien)) == 3) 
    fitness = 10;
end
fitness = fitness + k'*k;
%disp(fitness);
plot(tt,qt(:,1:2));
%plot(tt,0)
%plot(tt,qt(:,2)-2*qt(:,1),'r-.');
%plot(tt,qt(:,4),'b--');
plot(tet,mod(yet(:,1:2)+pi,2*pi)-pi,'ro');
%disp(tet)
%disp(yet(:,4)-2*yet(:,3))
%xlabel('time');
%ylabel('angular positions');
%title('Simplest Biped Walking');
%hold off;