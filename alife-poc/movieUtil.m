function F=movieUtil(data)
h=1/17;
cords_y=[h/2:h:1-h/2];
cords_x=cords_y';
F(size(data,1)) = struct('cdata',[],'colormap',[]);
for i=1:size(data,1)
    nf=reshape(data(i,6:294),17,17)';
    nf=nf.*heaviside(nf);
    sum_nf=sum(sum(nf));
    centroid_x=sum(nf*cords_x)/sum_nf;
    centroid_y=sum(cords_y*nf)/sum_nf;
    
    imagesc(cords_x,cords_y,nf,[-0.5,1.5]);
    colorbar;
    hold on;
    plot(centroid_x,centroid_y,'k*');
    title(sprintf('t = %g, k_{phi}= %g.',data(i,1),data(i,296)));
    xlabel(sprintf('theta'));
    ylabel(sprintf('theta dot'));
    F(i)=getframe(gcf);
    hold off;
end
