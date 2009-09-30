package jml.examples;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;




/**
 * 
 * @author Administrador
 *
 */
public class FrameExamples extends JFrame {	
	/**
	 * test sort
	 */
	TestSort sort;	
	/**
	 * test random
	 */
	TestRandom ran;
	/**
	 * test bitarray
	 */
	TestBitArray bi;
	/**
	 * test parser
	 */
	TestParser pa;
	/**
	 * Constructor
	 */
	public FrameExamples() {
		
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
       	setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	    setTitle("JML EXAMPLES");
	    setResizable(false);
	    jTabbedPane= new JTabbedPane(); 
	    getContentPane().setLayout(null);
			    
	    ran = new TestRandom();
	    ran.setVisible(true);
	    
	    sort = new TestSort();
		ran.setVisible(true);

	    bi = new TestBitArray();
	    bi.setVisible(true);
	    
	    pa = new TestParser();
	    pa.setVisible(true);
	    
	   
	    jTabbedPane.setBounds(0,0, 355, 574);
	    jTabbedPane.add(sort,"SORT EXAMPLE");
	    jTabbedPane.add(bi,"BIT ARRAY EXAMPLE");
	    jTabbedPane.add(pa,"PARSER EXAMPLE");
	    jTabbedPane.add(ran,"RAMDOM EXAMPLE");
	    
        getContentPane().add(jTabbedPane);
	    	   
	    setBounds((screenSize.width-355)/2, (screenSize.height-574)/2, 355, 574);
	    setVisible(true);
	}
	
	
	 /**
     * Singleton dessign pattern.
     */
    private static FrameExamples instance;
	
	
	/**
     * Returns the only one instance allwoed for the class.
     * @return The class instance.
     */
    public static synchronized JFrame getInstance() {
        if(instance == null){
            instance = new FrameExamples();
        }
        return instance;
            
    }

	/**
	 * @param args
	 */
	public static void main (String[] args) {
		 try {
		//	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			FrameExamples w = (FrameExamples) FrameExamples.getInstance();
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
}
