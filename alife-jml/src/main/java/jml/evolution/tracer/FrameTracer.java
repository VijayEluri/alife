package jml.evolution.tracer;

import grafics.chart.CategoryPlot;
import grafics.chart.ChartFactory;
import grafics.chart.ChartPanel;
import grafics.chart.HorizontalCategoryAxis;
import grafics.chart.JFreeChart;
import grafics.chart.NumberAxis;
import grafics.data.CategoryDataset;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import grafics.chart.demo.DemoDatasetFactoryOne;





/**
 * 
 * @author Administrador
 *
 */
public class FrameTracer extends JFrame {
	
	private static Vector data;
	static public ChartPanel chartpanel;
	ChartPanel chartpanel1;
	ChartPanel chartpanel2;
	String [] str1;					//Los datos que se van a printar en el grafico
	String [][] dat_rec; 			//Recibe los datos
	String [] nomColumna;			//El Nombre de la columna
	String [] nomCliente;			//El nombre del cliente
	public static Color [] colorCliente;			//El color con que se va a pintar cada cliente
	Color [] arrCol;
	
	
	/**
	 * 
	 *
	 */
	public FrameTracer() {
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
       //	setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	    setTitle("JML EXAMPLES");
	    setResizable(false);
	    getContentPane().setLayout(null);
	    
	    data= new Vector();
	    
	  



//pack();   
setBounds((screenSize.width-600)/2, (screenSize.height-650)/2, 600, 650);
	    jTabbedPane = new JTabbedPane();
	    jTabbedPane.setBounds(0,0,600, 600);
	    
	    //jTabbedPane.add(chartpanel,"GRAFICA 1");
	    //jTabbedPane.add(chartpanel1,"GRAFICA 2");
	    //jTabbedPane.add(chartpanel2,"GRAFICA 2");
	    
	    jTabbedPane.setAutoscrolls(true);
        getContentPane().add(jTabbedPane);
	    	
        //RefineryUtilities.positionFrameRandomly(this);
       
	    setVisible( true );
	}
	
	
	 /**
     * Singleton dessign pattern.
     */
    private static FrameTracer instance;
	
	
	/**
     * Returns the only one instance allwoed for the class.
     * @return The class instance.
     */
    public static synchronized JFrame getInstance() {
        if(instance == null){
            instance = new FrameTracer();
        }
        return instance;
            
    }
	
	
	
	/**
	 * @param args
	 */
	public static void main (String[] args) {
		 try {
		//	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			FrameTracer w = (FrameTracer) FrameTracer.getInstance();
		     w.setVisible(true);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 

	}
	

	
	
	public void setColorCliente(String [] cl){
		int lon=cl.length;
		int r;
		int g;
		int b;
		colorCliente=new Color[lon];
		
		//Se genera un color aleatorio para pintar cada cliente
		for (int i=0;i<lon;i++){
			r=(int)(Math.random()*255);
			g=(int)(Math.random()*255);
			b=(int)(Math.random()*255);
			colorCliente[i]=new Color(r,g,b);
		
		}		
		
	}
	
	
	/**
	 * Sets Data
	 * @param data Data for paint
	 */
	public void addData(String [] dataOne){
		data.add(dataOne);
		    
	}
	
	

	/**
	 * 
	 */
	public void paintData(int eac){
		
		  
	        
		if (data != null) {
			
			if(data.size()%eac==0){
				double lenght=0;
				  String nombres[]= new String [3];
			        nombres[0]="BEST";
			        nombres[1]="AVG";
			        nombres[2]="WORST";
			        
			        String nombres1[]= new String [2];
			        nombres1[0]="BEST LENGHT";
			        nombres1[1]="AVG LENGHT";
			        
				
				
			
				
				 String columna[]= new String [data.size()];
				 String columna1[]= new String [data.size()];
				 double val[][]= new double [3][data.size()];
				 double val1[][]= new double [2][data.size()];
				
			      
			       
		
				
				for(int i = 0; i< data.size(); i++) {	
					String array[]= (String [])data.get(i);
					columna[i]=String.valueOf(i);
					columna1[i]=String.valueOf(i);
					if(array!=null){
						
						val[0][i]=Double.parseDouble(array[2]);
						val[1][i]=Double.parseDouble(array[3]);
						val[2][i]=Double.parseDouble(array[4]);
						
						val1[0][i]=Double.parseDouble(array[5]);
						val1[1][i]=Double.parseDouble(array[6]);
						lenght=Double.parseDouble(array[7]);	
					}
				}
				setColorCliente(nombres);
					
				String title="Evolution - Population = " +lenght; 
				CategoryDataset categorydataset = DemoDatasetFactoryOne.createCategoryDataset(val,nombres,columna);
                JFreeChart jfreechart1 = ChartFactory.createLineChart(title, "Iterator", "Fitness", categorydataset, true, true, false);
		        jfreechart1.setBackgroundPaint(new Color(152,152,235));
		        CategoryPlot categoryplot = jfreechart1.getCategoryPlot();
		        categoryplot.setValueLabelsVisible(false);
		        categoryplot.setSeriesPaint(colorCliente);
		                
		        NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
		        numberaxis.setAutoRangeIncludesZero(true);
		        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		        
		        HorizontalCategoryAxis horizontalcategoryaxis = (HorizontalCategoryAxis)categoryplot.getDomainAxis();
		        horizontalcategoryaxis.setVerticalCategoryLabels(true);
		        horizontalcategoryaxis.setVerticalTickLabels(true);
		        horizontalcategoryaxis.setSkipCategoryLabelsToFit(true);
		        horizontalcategoryaxis.setTickLabelsVisible(true);
		        
		        setColorCliente(nombres1); 
		        
		        String title1="Evolution - Population = " +lenght; 
				CategoryDataset categorydataset2= DemoDatasetFactoryOne.createCategoryDataset(val1,nombres1,columna1);
                JFreeChart jfreechart2 = ChartFactory.createLineChart(title1, "Iterator", "Fitness", categorydataset2, true, true, false);
		        jfreechart2.setBackgroundPaint(new Color(152,152,235));
		        CategoryPlot categoryplot2 = jfreechart2.getCategoryPlot();
		        categoryplot2.setValueLabelsVisible(false);
		        categoryplot2.setSeriesPaint(colorCliente);
		                
		        NumberAxis numberaxis2 = (NumberAxis)categoryplot2.getRangeAxis();
		        numberaxis2.setAutoRangeIncludesZero(true);
		        numberaxis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		        
		        HorizontalCategoryAxis horizontalcategoryaxis2 = (HorizontalCategoryAxis)categoryplot2.getDomainAxis();
		        horizontalcategoryaxis2.setVerticalCategoryLabels(true);
		        horizontalcategoryaxis2.setVerticalTickLabels(true);
		        horizontalcategoryaxis2.setSkipCategoryLabelsToFit(true);
		        horizontalcategoryaxis2.setTickLabelsVisible(true);
		        
		        jTabbedPane.removeAll();
		       		        
		       // chartpanel1=(ChartPanel) jTabbedPane.getComponent(0);
		        chartpanel1 = new ChartPanel(jfreechart1);
		       // chartpanel2=(ChartPanel) jTabbedPane.getComponent(1);
		        chartpanel2 = new ChartPanel(jfreechart2);
		        
		         jTabbedPane.add(chartpanel1,"FITNESS");
		         //jTabbedPane.repaint(1);
		         jTabbedPane.add(chartpanel2,"LENGHT");
		         //jTabbedPane.repaint(2);
		         this.repaint();
			}
			
			
		}
	}
	
	/**
	 * 
	 */
    
	/**
	 * 
	 */
	private JTabbedPane jTabbedPane; 
}
