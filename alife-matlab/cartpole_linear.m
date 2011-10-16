tetha = sym('tetha');
F=sym('F');
tao = sym('tao');
tethap = sym('tethap');
g = sym('g');
l=sym('l');
M=sym('M');
m=sym('m');
mu_p=sym('mu_p');

%%
fprintf('* System Equation *\n');
fprintf('\ntethapp := ');
fq = (g*sin(tetha)+cos(tetha)*((-F-m*l*tethap^2*sin(tetha)))/(M+m) - (mu_p*tethap)/(m*l)) / (l*(4/3-(m*cos(tetha)^2)/(M+m)));
pretty(fq)

%%
fprintf('\n* Equilibrium Points *\n');
fq_0 = subs(subs(fq,tethap,0),F,0);
fprintf('\nf_tetha, where F=0 and qp=0 =');
pretty(fq_0);

tetha_eq = double(solve(subs(subs(fq,tethap,0),F,0),tetha));
fprintf('\ntetha_eq (tetha at equilibrium) = %d\n', tetha_eq);

%%
fprintf('\n* Jacobian Matrix *\n');
q(1) = tetha;
q(2) = tethap;
f(1) = q(2);
f(2) = fq;

A = subs(jacobian(f,q),[q(1),q(2),F],[0,0,0]);
fprintf('\nA = \n');
disp(A);

B = subs(jacobian(f,[F]),[q(1),q(2),F],[0,0,0]);
fprintf('\nB = \n');
disp(B);

fprintf('\nReplacing l=1, g=10, m=1, M=2:\n');
As = subs(A, [g l m M mu_p], [9.8 0.5 0.1 1 0.0005]);
fprintf('\nA = \n');
disp(As);

Bs = subs(B, [g l m M mu_p], [9.8 0.5 0.1 1 0.0005]);
fprintf('\nB = \n');
disp(Bs);

%%
fprintf('\n* Eigenvalues and Eigenvectors *\n');
[T,E] = eig(As)

%%
fprintf('\n* Linearized Model *\n');
Cs = [1, 0];
Ds = zeros(1);
L1 = ss(As,Bs,Cs,Ds)
L2 = tf(L1)
%sisotool(L2);

