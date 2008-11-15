package jml.examples;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import jml.random.GaussianNumberGenerator;
import jml.random.UniformNumberGenerator;
import jml.random.WeightedNumberGenerator;
/**
 * <p>Title: TestRandom</p>
 * <p>Description: This class is a example of the package Random. 
 * The classes in this example are GaussianNumberGenerator, 
 * UniformNumberGenerator and WeightedNumberGenerator.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class TestRandom extends JPanel {    
	/**
	 * <p>Type of Random</p>
	 * <p>bandRandom is 0 if its a Gaussian </p>
	 * <p>bandRandom is 1 if its a Uniform </p>
	 * <p>bandRandom is 2 if its a Weighted </p>
	 */
	private int bandRandom = 0;
	/**
	 * <p>Type of Data to generate</p>
	 * <p>bandC is 0 if its a Boolean</p>
	 * <p>bandC is 1 if its a double</p>
	 * <p>bandC is 2 if its a int</p>
	 */
	private int bandC = 0;	
	/**
	 * Array to store the random numbers
	 */
	private double array[];
	/**
	 * String to store the path of the file with the weights
	 */
	private String pathfile = null;
	/**
	 * If the gaussian is only right or not
	 */
	private boolean bOnlyR = false;
	/**
	 * Constructor: default constructor
	 */
	public TestRandom() {
		initComponents();
	}	
	 /**
     * Initialize the components.
     */
	private void initComponents() {
		selected = new JPanel();
    	selectedOne = new JPanel();
    	labelRandom = new JLabel("RANDOM TYPE");
    	labelC = new JLabel("TYPE OF DATA"); 
    	labelNumber = new JLabel("Number of data:");
        labelMin = new JLabel("Inferior limit (Integer):");
        labelMax = new JLabel("Superior limit (Integer):");
        labelPath = new JLabel("File not selected");
        labelMiu = new JLabel("Miu:");
        labelSigma = new JLabel("Sigma:");
        jCheckRight = new JCheckBox("Only Right");
    	number = new JTextField("1");
    	superior = new JTextField("1");
    	inferior = new JTextField("0");
    	jTextMiu = new JTextField("0");
    	jTextSigma = new JTextField("1");
		ButtonGroup groupRandom = new ButtonGroup();
		ButtonGroup groupC = new ButtonGroup();
	    randomGaussian = new JRadioButton("Gaussian ");
	    randomUniform = new JRadioButton("Uniform");
	    randomWeighted = new JRadioButton("Weighted");
	    cBoolean = new JRadioButton("Boolean");
	    cDouble = new JRadioButton("Double");
	    cInt = new JRadioButton("Int");
	  
	 
	    rand = new JButton();
	    clean = new JButton();
	    path = new JButton();
	    view = new JButton();
		
        this.setLayout(null);        

       // this.add(labelRandom);
        labelRandom.setBounds(20, 20, 200, 15);
       // this.add(labelC);
        labelC.setBounds(200, 20, 250, 15);
       
        randomGaussian.setSelected(true);
        randomGaussian.setBounds(20, 40, 100, 15);
        groupRandom.add(randomGaussian);
        randomGaussian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
             bandRandom = 0;  
             inferior.setEnabled(false);
             inferior.setText("0");
             superior.setEnabled(false);
             superior.setText("1");
             path.setEnabled(false);
             labelPath.setVisible(false);
            }
        });
        this.add(randomGaussian);

        randomUniform.setBounds(20, 60, 100, 15);
        groupRandom.add(randomUniform);
        randomUniform.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
          	 bandRandom = 1;
          	inferior.setEnabled(true);
            superior.setEnabled(true);
            path.setEnabled(false);
            labelPath.setVisible(false);
            }
        });
        this.add(randomUniform);
        
        randomWeighted.setBounds(20, 80, 100, 15);
        groupRandom.add(randomWeighted);
        randomWeighted.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bandRandom = 2;
            	inferior.setEnabled(false);
                inferior.setText("0");
                superior.setEnabled(false);
                superior.setText("1");
                path.setEnabled(true);
                labelPath.setVisible(true);
            }
        });
        this.add(randomWeighted);
        
        cBoolean.setSelected(true);
        cBoolean.setBounds(200, 40, 100, 15);
        groupC.add(cBoolean);
        cBoolean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bandC = 0;   	
            }
        });
        this.add(cBoolean);

        cDouble.setBounds(200, 60, 100, 15);
        groupC.add(cDouble);
        cDouble.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bandC = 1;
            }
        });
        this.add(cDouble);
   
        cInt.setBounds(200, 80, 100, 15);
        groupC.add(cInt);
        cInt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bandC = 2;
            }
        });
        this.add(cInt);
        

        
        rand.setText("Random");
        rand.setBounds(30, 130, 130, 30);
        rand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	if (Integer.parseInt(number.getText()) != 0) {
            		if (Integer.parseInt(inferior.getText()) < Integer.parseInt(superior.getText())) {
            			randomize(Integer.parseInt(number.getText()));
    	       	    }   
            		
            	}
            }
        });
        this.add(rand);

        view.setText("View Data");
        view.setBounds(190, 130, 130, 30);
        view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	editTable();
            }
        });
        this.add(view);
        
        
        clean.setText("Clean");
        clean.setBounds(30, 180, 130, 30);
        clean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	
            }
        });
        this.add(clean);
        this.add(labelPath);
       
        labelPath.setBounds(30, 400, 300, 20);
        labelPath.setVisible(false);
    
        path.setText("Weight File");
        path.setBounds(190, 180, 130, 30);
        path.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	pathfile = setpathweight();  
            	if (pathfile != null) { labelPath.setText(pathfile); }
            }
        });
        path.setEnabled(false);
        this.add(path);
        
        labelNumber.setBounds(30, 340, 130, 20);
        this.add(labelNumber);
        
        number.setBounds(190, 340, 130, 20);
        number.addKeyListener(new KeyAdapter() {
	       	 public void keyTyped(KeyEvent e) {
	       	 	char c = e.getKeyChar();      
	       	      if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
	       	        getToolkit().beep();
	       	        e.consume();
	       	      }	       	 
	       	 }
	    });
        this.add(number);
        
        labelMin.setBounds(30, 220, 150, 20);
        this.add(labelMin);
        
        inferior.setBounds(190, 220, 130, 20);
        inferior.addKeyListener(new KeyAdapter() {
	       	 public void keyTyped(KeyEvent e) {
	       	 	char c = e.getKeyChar();      
	       	    if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
	       	        getToolkit().beep();
	       	        e.consume();
	       	    }	       	    
	       	 }
	    });
        inferior.setEnabled(false);
        this.add(inferior);
      
        labelMax.setBounds(30, 250, 200, 20);
        this.add(labelMax);
        
        superior.setBounds(190, 250, 130, 20);
        superior.addKeyListener(new KeyAdapter() {
	       	 public void keyTyped(KeyEvent e) {
	       	 	char c = e.getKeyChar();      
	       	    if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
	       	        getToolkit().beep();
	       	        e.consume();
	       	    }
	       	 }
	    });
        superior.setEnabled(false);
        this.add(superior);
        
        labelMiu.setBounds(30, 280, 200, 20);
        this.add(labelMiu);
        
        jTextMiu.setBounds(190, 280, 130, 20);
        jTextMiu.addKeyListener(new KeyAdapter() {
	       	 public void keyTyped(KeyEvent e) {
	       	 	char c = e.getKeyChar();      
	       	    if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
	       	        getToolkit().beep();
	       	        e.consume();
	       	    }
	       	 }
	    });
        this.add(jTextMiu);
        
        labelSigma.setBounds(30, 310, 200, 20);
        this.add(labelSigma);
        
        jTextSigma.setBounds(190, 310, 130, 20);
        jTextSigma.addKeyListener(new KeyAdapter() {
	       	 public void keyTyped(KeyEvent e) {
	       	 	char c = e.getKeyChar();      
	       	    if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
	       	        getToolkit().beep();
	       	        e.consume();
	       	    }
	       	 }
	    });        
        this.add(jTextSigma);
        
        
        
        jCheckRight.setBounds(30, 370, 100, 20); 
        jCheckRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	if (bOnlyR) { bOnlyR = false; } else { bOnlyR = true;}
            }
        });  
        this.add(jCheckRight);
        
        
        
        selected.setLayout(null);
        selected.add(labelRandom);
        selected.add(labelC);
        selected.add(randomGaussian);
        selected.add(randomUniform);
        selected.add(randomWeighted);
        selected.add(cBoolean);
        selected.add(cDouble);
        selected.add(cInt);
        selected.setBounds(10, 110, 330, 330);
        selected.setBorder(BorderFactory
				 .createTitledBorder("SELECTED"));
        this.add(selected);
        
        
        selectedOne.setLayout(null);
        selectedOne.add(labelRandom);
        selectedOne.add(labelC);
        selectedOne.add(randomGaussian);
        selectedOne.add(randomUniform);
        selectedOne.add(randomWeighted);
        selectedOne.add(cBoolean);
        selectedOne.add(cDouble);
        selectedOne.add(cInt);
        selectedOne.setBounds(10, 0, 330, 105);
        selectedOne.setBorder(BorderFactory
				 .createTitledBorder("SELECTED"));
        this.add(selectedOne);
        
    }
	/**
	  * Action associated to an event in the <code>rand</code>.
	  * Generates m random double, int or boolean. 
	  * For WeightedNumberGenerator case: reads the file given by pathFile
	  * or use the array [1 2 3 4 5 6 7 8 9 10] if the file is not found
	  */
	private void randomize (int m) {
	     if (bandRandom == 0) {
	    	 double dmiu = Double.parseDouble(jTextMiu.getText());
	    	 double dsigma = Double.parseDouble(jTextSigma.getText());
	    	 GaussianNumberGenerator gaussian = new GaussianNumberGenerator(dmiu, dsigma, bOnlyR);	 
	    	 if (bandC == 0) {
	    		 boolean arr[] = gaussian.getVectorBoolean(m);
	    		 this.array = new double[arr.length];
	    		 for (int i = 0; i < arr.length; i++) {
	    			 if (arr[i]) { array[i] = 1; } else { array[i] = 0; } 
	    		 }	    		
	         }
	    	 if (bandC == 1) {
	    		 array = gaussian.getArrayDouble(m);	    		
	         }
	    	 if (bandC == 2) {
	    		 int [] arr = gaussian.getArrayInteger(m);
	    		 array = new double[arr.length];
	    		 for (int i = 0; i < arr.length; i++) {
	    			 array[i] = (int) arr[i]; 
	    		 }	    		
	         }
	     }
	     if (bandRandom == 1) {
	    	 double a = Double.parseDouble(inferior.getText());
	    	 double b = Double.parseDouble(superior.getText());
	    	 UniformNumberGenerator unif = new UniformNumberGenerator(a, b);	 
	    	 if (bandC == 0) {
	    		 boolean arr[] = unif.getVectorBoolean(m);
	    		 this.array = new double[arr.length];
	    		 for (int i = 0; i < arr.length; i++) {
	    			 if (arr[i]) { array[i] = 1; } else { array[i] = 0; } 
	    		 }	    		
	         }
	    	 if (bandC == 1) {
	    		 array = unif.getArrayDouble(m);	    		
	         }
	    	 if (bandC == 2) {
	    		 int [] arr = unif.getArrayInteger(m);
	    		 array = new double[arr.length];
	    		 for (int i = 0; i < arr.length; i++) {
	    			 array[i] = (int) arr[i]; 
	    		 }
	         }
	     }	 
	     if (bandRandom == 2) {    	    	 
	    	 WeightedNumberGenerator weight = new WeightedNumberGenerator(openFileWeight());
	    	 weight.normalize();
	    	 if (bandC == 0) {
	    		 boolean arr[] = weight.getVectorBoolean(m);
	    		 this.array = new double[arr.length];
	    		 for (int i = 0; i < arr.length; i++) {
	    			 if (arr[i]) { array[i] = 1; } else { array[i] = 0; } 
	    		 }	    		
	         }
	    	 if (bandC == 1) {
	    		 array = weight.getArrayDouble(m);	    		
	         }
	    	 if (bandC == 2) {
	    		 int [] arr = weight.getArrayInteger(m);
	    		 array = new double[arr.length];
	    		 for (int i = 0; i < arr.length; i++) {
	    			 array[i] = (int) arr[i]; 
	    		 }
	         }
	     }	
	     
	     
	}
	
	 /**
	  * Opens a File with the weights for weighted number generator.
	  * @return The path of the file with the weights
	  */
	 private String setpathweight() {
		 String path = null;
		 
		 JFileChooser filechooser = new JFileChooser();
	     filechooser.setFileFilter(new FileFilter() {
	            public boolean accept(File f) {
	                if (f.isDirectory()) { return true; }
	                String extension = getExtension(f);
	                if (extension != null) {
	                    if (extension.equals("txt")) {
	                        return true;
	                    } else {
	                        return false;
	                    }
	                }
	                return false;
	            }
	            public String getDescription() {
	            	return "Text File (*.txt)";
	            }
	            public String getExtension(File f) {
	                String ext = null;
	                String s = f.getName();
	                int i = s.lastIndexOf('.');
	                if (i > 0 && i < s.length() - 1) {
	                    ext = s.substring(i + 1).toLowerCase();
	                }
	                return ext;
	            }
	        });
	        if (filechooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
	          path = filechooser.getSelectedFile().getAbsolutePath();
	        }
		 return path;
	 }
	 /**
	  * Opens a File with the weights for weighted number generator 
	  * or use the array [1 2 3 4 5 6 7 8 9 10] if the file is not found
	  * @return A double array with the weights in the file.
	  */
	 private double[] openFileWeight() {
	       double[]weight = null;
	     
	       ArrayList list;
	       try {
	           File filePatron = new File(pathfile);
	           FileReader lector = new FileReader(filePatron);
	           BufferedReader input = new BufferedReader(lector);
	           String texto;
	           list = new ArrayList();
		       while ((texto = input.readLine()) != null) {
		              String linea[] = texto.split(" ");	             
		              String numero = linea[0];
		              String a = ".";
		              String b = ",";
		              numero = numero.replaceAll(b, a);	      
		              list.add(numero);
		        }       
		        if (list != null) {
			        weight = new double[list.size()];
			        for (long i = 0; i < list.size(); i++) {        	
			        	weight[(int) i] = Double.parseDouble((String) list.get((int) i));        	
			        }       
			        System.out.println("Read " + list.size() + " rows");
		        } else {
		               weight = new double[10];
		               for (int i = 0; i < weight.length; i++) {
		            	   weight[i] = i + 1;
		               }
		        }
	            return weight;
	        } catch (Exception ex) {
	               System.out.println(ex.toString());
	               weight = new double[10];
	               for (int i = 0; i < weight.length; i++) {
	            	   weight[i] = i + 1;
	               }
	               return weight;
	        }        
	    }
	 
	 /**
	  * View Data
	  */
	 public void editTable() {
	       ViewData dialog = ViewData.getInstance();
	       String [] t = new String[2];
	       t[0] = "Index";
	       t[1] = "Data";
	       
	      dialog.setColumnNames(t);
	       dialog.createDialog(array);
      
	      }
	     
	 
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private JLabel labelRandom;   
	private JLabel labelC;
	private JLabel labelNumber;
	private JLabel labelMin;
	private JLabel labelMax;
	private JLabel labelPath;
	private JLabel labelMiu;
	private JLabel labelSigma;
	private JTextField number;
	private JTextField inferior;
	private JTextField superior;
	private JTextField jTextMiu;
	private JTextField jTextSigma;
	private JCheckBox jCheckRight;
	private JRadioButton randomUniform;
	private JRadioButton randomGaussian;
	private JRadioButton randomWeighted;
	private JRadioButton cDouble;
	private JRadioButton cInt;	
	private JRadioButton cBoolean;	
	private JButton clean;    
	private JButton rand;    
	private JButton path;    
	private javax.swing.JPanel selected;    
	private javax.swing.JPanel selectedOne;
	private javax.swing.JButton view;
    // End of variables declaration//GEN-END:variables
	
}
