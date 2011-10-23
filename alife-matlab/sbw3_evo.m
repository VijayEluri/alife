tic;
nvars = 1;
lb = 0;
ub = 100;
% Start with the default options
options = gaoptimset('Display','iter','PopInitRange',[lb;ub],...
    'Generations',100,'StallGenLimit',25);
% Modify options setting
res = [];
for thetap0 = 0:-0.025:-0.4
    for theta0 = 0:0.025:-thetap0
        qi = [theta0 thetap0]
        [x,fval] = ...
        ga(@(x) sbw3_fitness([0 x],qi,0),nvars,[],[],[],[],lb,ub,[],options);
        resn = [qi, 0, x, fval]
        res = [res;resn];
        toc;
    end
end
disp(res);
toc