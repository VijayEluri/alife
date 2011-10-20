function qp = sbw2(t,q,gamma,k)
% q[1] := \theta
% q[2] := \phi
% q[3] := \theta_dot
% q[4] := \phi_dot
g = 1;
l = 1;
qp = zeros(4,1);
tao = k.*q(1:2);
%C=[0 0;q(3)*sin(q(2)) 0];
%tao_g = [-(g/l)*sin(q(1)-gamma); (g/l)*sin(q(1)-gamma)-(g/l)*cos(q(1)-gamma)*sin(q(2))];
qp(1) = q(3);
qp(2) = q(4);
qp(3) = tao(1) + (g/l)*sin(q(1)-gamma);
qp(4) = -tao(2) + (1-cos(q(2)))*qp(3) + q(3)^2*sin(q(2)) ... 
    + (g/l)*sin(q(1)-q(2)-gamma);
%fprintf('%g\t%g\n',q(1),qp(3));
end