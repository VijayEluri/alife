function fitness = sbw4_main(qi)
%an = a+(0.5-rand(1,2))/20;
theta0 = qi(1);
thetap0 = qi(2);
gamma = 0.004;
qinit = [theta0 2*theta0 thetap0 (1-cos(2*theta0))*thetap0];

maxiters = 1000;

refine = 1;
options = odeset('Events',@sbw3_test,...
    'Refine',refine,...
    'RelTol',1e-9,...
    'AbsTol',[1e-9 1e-9 1e-9 1e-9]);
t0 = 0;
tfinal = 200;

tprev = t0;
tt=[];
qt=[];
tet=[];
yet=[];
ien = 1;
n=1;
kt = [];
while(tprev < tfinal && ien(length(ien)) == 1 && n<=maxiters)
    k_phi = sbw4_klookup([qinit(1) qinit(3)]);
    kt = [kt;k_phi];
    [tn,qn,ten,yen,ien] = ode45(@(t,y) sbw3(t,y,gamma,[0 k_phi]),[t0 tfinal],qinit,options);
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
if(~isempty(ien) && ien(length(ien)) == 2)
    fitness = 20000;
end

%if (isplot==1) 
    %subplot(2,2,[1 2]);
    figure;
    hold on;
    plot(tt,qt(:,1:2));
    plot(tt,0,'y');
    plot(tt,qt(:,2)-2*qt(:,1),'r-.');
    %plot(tt,qt(:,4),'b--');
    plot(tet,yet(:,1:2),'ro');
    %disp(tet)
    %disp(yet(:,4)-2*yet(:,3))
    xlabel('time');
    ylabel('angular positions');
    title('Sliding Mode Neural Field SBW - Time Simulation');
    legend('$\theta$','$\phi$');
    hold off;
    
    p = [];
    %subplot(2,2,3);
    figure;
    hold on;
    for i=1:length(yet(:,1))
        p(i,:) = sbw1_switch(yet(i,:));
    end
    p = [qi;p(:,1),p(:,3)]; 
    plot(p(:,1),p(:,2),'k-',p(:,1),p(:,2),'ro');
    xlabel('\theta');
    ylabel('$\theta + \dot{\theta}$');
    title('Sliding Mode SBW - Poincare Section');
    xlim([0 0.4]);
    ylim([-0.4 0]);
    grid on;
    hold off;
    
    %subplot(2,2,4)
    figure;
    hold on;
    plot([0;tet],[kt;kt(length(kt))],'k.');
    xlabel('time');
    ylabel('$k_{\phi}$');
    title('Sliding Mode SBW - K values');
    grid on;
    hold off;
%end