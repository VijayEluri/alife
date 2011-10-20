tic;
nvars = 2;
lb = [-1 1];
ub = [0 2];
% Start with the default options
options = gaoptimset('Display','iter');
% Modify options setting
res = [];
for a1 = 0.5:0.05:1
    for a2 = a1:0.05:1
        a = [a1 a2];
        [x,fval] = ...
        ga(@(x) sbw2_fitness(x,a),nvars,[],[],[],[],lb,ub,[],options);
        %fval = sbw2_fitness(x,a);
        resn = [a1 a2 x(1) x(2) fval]
        res = [res;resn];
        toc;
    end
end
disp(res);
toc