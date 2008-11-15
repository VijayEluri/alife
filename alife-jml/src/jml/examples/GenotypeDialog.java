package jml.examples;

import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class GenotypeDialog extends JDialog {
    
    
	
	    ButtonGroup buttonGroup1 = new ButtonGroup();
	    boolean band=true; 
	    JRadioButton jRadioButton1 = new JRadioButton();
	    JRadioButton jRadioButton2 = new JRadioButton();
	    JLabel jLabel1 = new JLabel();
	    ButtonGroup buttonGroup2 = new ButtonGroup();
	    JLabel jLabel2 = new JLabel();
	    JTextField jTextField1 = new JTextField();
	    JLabel jLabel3 = new JLabel();
	    JLabel jLabel4 = new JLabel();
	    JTextField jTextField2 = new JTextField();
	    JTextField jTextField3 = new JTextField();
	    JLabel jLabel5 = new JLabel();
	    JButton jButton1 = new JButton();
    
    
    public GenotypeDialog(boolean modal) {
        super(ExampleHaea.getInstance(),true);
        this.setLayout(null);
        this.setTitle("Create Genotype");
        jButton1.setBounds(new Rectangle(228, 95, 52, 41));
        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	if(band){
            		try{
            			int val1= Integer.parseInt(jTextField1.getText());
            			HaeaTest.getInstance().setGenotypeBit(val1);
            			hide();
            			dispose();

            		}catch(Exception ex){
            			
            		}
            		
            	}else{
            		try{
            			int val1= Integer.parseInt(jTextField1.getText());
            			int val2= Integer.parseInt(jTextField2.getText());
            			int val3= Integer.parseInt(jTextField3.getText());
            			HaeaTest.getInstance().setGenotypeBit(val1, val2, val3);
            			hide();
            			dispose();

            		}catch(Exception ex){
            			
            		}
            		
            	}
           }
        });
        
        jRadioButton1.setText("BinaryGenotype");
        jRadioButton1.setBounds(new Rectangle(6, 18, 235, 27));
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
             band = true;  	
             jLabel2.setText("Lenght");
             jTextField1.setVisible(true);
             jTextField2.setVisible(false);
             jTextField3.setVisible(false);
             jLabel2.setVisible(true);
             jLabel3.setVisible(false);
             jLabel4.setVisible(false);
            }
        });
        jRadioButton1.setSelected(true);
        jRadioButton2.setText("VariableLengthBinaryGenotype");
        jRadioButton2.setBounds(new Rectangle(6, 42, 237, 24));
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
             band = true;  	 
             jLabel2.setText("Min");
             jTextField1.setVisible(true);
             jTextField2.setVisible(true);
             jTextField3.setVisible(true);
             jLabel2.setVisible(true);
             jLabel3.setVisible(true);
             jLabel4.setVisible(true);
            }
        });
        buttonGroup1.add(jRadioButton1);
        buttonGroup1.add(jRadioButton2);
        jLabel1.setText("Selected");
        jLabel1.setBounds(new Rectangle(11, 2, 113, 22));
        jLabel2.setText("Lenght:");
        jLabel2.setBounds(new Rectangle(11, 73, 66, 15));
        jTextField1.setBounds(new Rectangle(81, 76, 143, 21));
        jTextField1.addKeyListener(new KeyAdapter() {
	       	 public void keyTyped(KeyEvent e) {
	       	 	char c = e.getKeyChar();      
	       	      if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
	       	        getToolkit().beep();
	       	        e.consume();
	       	      }	       	 
	       	 }
	    });
        jTextField1.setText("1");
        jLabel3.setText("Max:");
        jLabel3.setBounds(new Rectangle(11, 109, 66, 15));
        jLabel3.setVisible(false);
        jLabel4.setText("Delta:");
        jLabel4.setBounds(new Rectangle(11, 141, 66, 15));
        jLabel4.setVisible(false);
        jTextField2.setText("1");
        jTextField2.setBounds(new Rectangle(81, 106, 143, 22));
        jTextField2.setVisible(false);
        jTextField2.addKeyListener(new KeyAdapter() {
	       	 public void keyTyped(KeyEvent e) {
	       	 	char c = e.getKeyChar();      
	       	      if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
	       	        getToolkit().beep();
	       	        e.consume();
	       	      }	       	 
	       	 }
	    });
        jTextField3.setText("1");
        jTextField3.setBounds(new Rectangle(81, 136, 143, 22));
        jTextField3.setVisible(false);
        jTextField3.addKeyListener(new KeyAdapter() {
	       	 public void keyTyped(KeyEvent e) {
	       	 	char c = e.getKeyChar();      
	       	      if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
	       	        getToolkit().beep();
	       	        e.consume();
	       	      }	       	 
	       	 }
	    });
        jLabel5.setText("jLabel5");
        jLabel5.setBounds(new Rectangle(0, 0, 34, 15));
        this.add(jRadioButton1);
        this.add(jRadioButton2);
        this.add(jLabel1);
        this.add(jTextField3);
        this.add(jTextField1);
        this.add(jTextField2);
        this.add(jLabel3);
        this.add(jLabel4);
        this.add(jLabel2);
        this.add(jButton1);
        
       
    	java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(150,100, 300, 210);
        setBounds((screenSize.width-300)/2, (screenSize.height-210)/2, 300, 210);
    }
    
   

}

