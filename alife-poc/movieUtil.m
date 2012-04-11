function F=movieUtil(data)
F(size(data,1)) = struct('cdata',[],'colormap',[]);
for i=1:size(data,1)
imagesc(reshape(data(i,6:294),17,17)',[-0.5,1.5]);colorbar;xlabel(data(i,1));F(i)=getframe;
end
