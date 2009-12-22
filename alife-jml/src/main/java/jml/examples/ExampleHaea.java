package jml.examples;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class ExampleHaea extends JFrame {

		public ExampleHaea() {
			
			java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	       	setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		    setTitle("JML EXAMPLES - HAEA");
		    setResizable(false);
		    getContentPane().setLayout(null);
		    
		    panelParameters= new PanelParameters();
		  
		    
		    jTabbedPane = new JTabbedPane();
		    jTabbedPane.add(panelParameters,"HAEA");
		    jTabbedPane.setBounds(0,0,355,574);
		   
		    
	        getContentPane().add(jTabbedPane);
		    	   
		    setBounds((screenSize.width-355)/2, (screenSize.height-574)/2, 355, 574);
		    setVisible(true);
		}
		
		
		 /**
	     * Singleton dessign pattern.
	     */
	    private static ExampleHaea instance;
		
		
		/**
	     * Returns the only one instance allwoed for the class.
	     * @return The class instance.
	     */
	    public static synchronized JFrame getInstance() {
	        if(instance == null){
	            instance = new ExampleHaea();
	        }
	        return instance;
	            
	    }

		/**
		 * @param args
		 */
		public static void main (String[] args) {
			 try {
			   //	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				 ExampleHaea w = (ExampleHaea) ExampleHaea.getInstance();
			     w.setVisible(true);			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}  
		/**
		 * The JTabbedPane
		 */
		private JTabbedPane jTabbedPane;
		private PanelParameters panelParameters;
	

}
