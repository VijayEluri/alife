function qnext = sbw1_switch(q)
% q[1] := \theta
% q[2] := \phi
% q[3] := \theta_dot
% q[4] := \phi_dot
qnext = zeros(4,1);
qnext(1) = -q(1);
qnext(2) = -2*q(1);
qnext(3) = cos(2*q(1))*q(3);
qnext(4) = (1-cos(2*q(1)))*qnext(3);
end
