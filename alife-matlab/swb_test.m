function [value,isterminal,direction] = swb_test(t,q)
% q[1] := \theta
% q[2] := \phi
% q[3] := \theta_dot
% q[4] := \phi_dot
value = [1 1 1];
if(t~=0)
    value(1)=q(2)-2*q(1);
end
if(q(1) > pi/2 || q(1) < -pi/2)
    value(2)=0;
end
if(q(2) > pi/2 || q(2) < -pi/2)
    value(2)=0;
end
isterminal=[1 1 1];
direction=[1 0 0];
end

