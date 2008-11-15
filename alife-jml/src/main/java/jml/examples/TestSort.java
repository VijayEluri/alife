package jml.examples;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.filechooser.FileFilter;
import jml.util.sort.BubbleSort;
import jml.util.sort.MergeSort;
/**
 * <p>Title: TestSort</p>
 * <p>Description: This class is a example of the classes 
 * jml.util.sort.BubbleSort and jml.util.sort.MergeSort.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author icarus 
 * @version 1.0
 */
public class TestSort extends JPanel {
	
	/**
	 * Arraylist to store the data 
	 */
	private ArrayList list;
	/**
	 * Array of doubles to be sorted
	 */
	private double array[];    
	/**
	 * Band for the group of caption (Algorithm to use)
	 */
	private boolean bandOne;
	/**
	 * Band for the group of caption (Hight-low or low-hight)
	 */
	private boolean bandTwo;
	/**
	 * Constructor: default constructor
	 */
	public TestSort() {
		initComponents();
	}
	
	 /**
     * Initialize the components.
     */
    private void initComponents() {
    	selected = new JPanel();
    	selectedOne = new JPanel();
      	labelAlgorithmt = new javax.swing.JLabel();
    	labelOrder = new javax.swing.JLabel();
    	bandOne = true;
    	bandTwo = true;
    	ButtonGroup groupOne = new ButtonGroup();
		ButtonGroup groupTwo = new ButtonGroup();
	    algorithmtOne = new JRadioButton("BubbleSort");
	    algorithmtOne = new JRadioButton("BubbleSort");
	    algorithmtTwo = new JRadioButton("MergeSort");
	    orderOne = new JRadioButton("Low - High");
	    orderTwo = new JRadioButton("High - Low");
	    getData = new javax.swing.JButton();
	    clean = new javax.swing.JButton();
	    sorted = new javax.swing.JButton();
	    view = new javax.swing.JButton();
	    this.setLayout(null);
        labelAlgorithmt.setText("SORT ALGORITHM");
        labelAlgorithmt.setBounds(20, 20, 200, 15);
        labelOrder.setText("ORDER");
        //this.add(labelOrder);
        labelOrder.setBounds(200, 20, 200, 15);
        algorithmtOne.setSelected(true);
        algorithmtOne.setBounds(20, 40, 100, 15);
        groupOne.add(algorithmtOne);
        algorithmtOne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
             bandOne = true;  	  	
            }
        });
        algorithmtTwo.setBounds(20, 60, 100, 15);
        groupOne.add(algorithmtTwo);
        algorithmtTwo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
          	 bandOne = false;
            }
        });
        orderOne.setSelected(true);
        orderOne.setBounds(200, 40, 100, 15);
        groupTwo.add(orderOne);
        orderOne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	bandTwo = true;   	
            }
        });
        orderTwo.setBounds(200, 60, 100, 15);
        groupTwo.add(orderTwo);
        orderTwo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
          	 bandTwo = false;
            }
        });
        getData.setText("Gets Data");
        getData.setBounds(10, 120, 130, 30);
        getData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
              	buttonSearchFileActionPerformed();
            }
        });
        clean.setText("Clean");
        clean.setBounds(10, 170, 130, 30);
        clean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               array = null;
               list = null;
            }
        });
     
        sorted.setText("Sorted");
        sorted.setBounds(180, 120, 130, 30);
        sorted.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	sort();
            }
        });
        view.setText("View Data");
        view.setBounds(180, 170, 130, 30);
        view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	view();
           }
        });
        selectedOne.setLayout(null);
        selectedOne.add(orderTwo);
        selectedOne.add(labelAlgorithmt);
        selectedOne.add(labelOrder);
        selectedOne.add(orderOne);
        selectedOne.add(algorithmtOne);
        selectedOne.add(algorithmtTwo);
        selectedOne.setBounds(10, 0, 330, 105);
        selectedOne.setBorder(BorderFactory.createTitledBorder("SELECTED"));
        this.add(selectedOne);
        selected.add(getData);
        selected.add(sorted);
        selected.add(clean);
        selected.add(view);
        selected.setLayout(null);
        selected.setBounds(10, 110, 330, 330);
        selected.setBorder(BorderFactory.createTitledBorder("PRACTICE"));
        this.add(selected);
    }
    
  
	/**
	  * Action associated to an event in the <code>sort</code>.
	  * Sorts the array.
	  */    
    public void sort() {
	 Date date;
	 String ftoday;
	 SimpleDateFormat format;
     if (array != null) {
		 if (bandOne) {
		    	 BubbleSort order = new BubbleSort();	 
		    	 if (bandTwo) {
		    		date = new Date();
		    		format = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
		    		ftoday = format.format(date);
		    		System.out.println("BubbleSort (Low-Higt) Star = " + ftoday);
		    		order.low2high(array);
		    		date = new Date();
		     		format = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
		     		ftoday = format.format(date);
		    		System.out.println("BubbleSort (Low-Higt) End = " + ftoday);
		         } else {
		        	date = new Date();
		     		format = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
		     		ftoday = format.format(date);
		        	System.out.println("BubbleSort (Higt-Low) Star = " + ftoday);
		        	order.high2low(array);
		        	date = new Date();
		     		format = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
		     		ftoday = format.format(date);
		        	System.out.println("BubbleSort (Higt-Low) Star = " + ftoday);
		         }
		     } else {
		    	 MergeSort order = new MergeSort();
		    	 if (bandTwo) {
		    		 date = new Date();
		     		format = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
		     		ftoday = format.format(date);
		    		 System.out.println("MergeSort (Low-Higt) Star = " + ftoday);
		        	 order.low2high(array);
		        	 date = new Date();
		     		format = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
		     		ftoday = format.format(date);
		        	 System.out.println("MergeSort (Low-Higt) Star = " + ftoday);
		         } else {
		        	date = new Date();
		     		format = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
		     		ftoday = format.format(date);
		        	System.out.println("MergeSort (Higt-Low) Star = " + ftoday);
		        	order.high2low(array);
		        	date = new Date();
		     		format = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
		     		ftoday = format.format(date);
		        	System.out.println("MergeSort (Higt-Low) Star = " + ftoday);
		         }
		     }
   
     } 
}   
    
 
    
 /**
  * Action associated to an event in the <code>getData</code>.
  * Opens a FileChooser dialog, for select the test file.
  */
    void buttonSearchFileActionPerformed() {
       list = null;
       array = null;
      
       list = new ArrayList();	
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
          String archivoPatron = filechooser.getSelectedFile().getAbsolutePath();
          try {
            File filePatron = new File(archivoPatron);
            FileReader lector = new FileReader(filePatron);
            BufferedReader input = new BufferedReader(lector);
            String texto;
            long num = 0;
            list = new ArrayList();
            while ((texto = input.readLine()) != null) {
              String linea[] = texto.split(" ");
              num++;
             
              String numero = linea[0];
              String a = ".";
              String b = ",";
              numero = numero.replaceAll(b, a);
              
              if (num < 1000) {
             	 list.add(numero);
              }
            }
          } catch (Exception ex) {
               System.out.println(ex.toString());
          }          
        }       
        if (list != null) {
	        array = new double[list.size()];
	        for (long i = 0; i < list.size(); i++) {        	
	        	array[(int) i] = Double.parseDouble((String) list.get((int) i));        	
	        }       
	        
	        System.out.println("Read " + list.size() + " rows");
        }        
    }

    
    /**
     * View Data
     *
     */
    public void view() {
       ViewData dialog = ViewData.getInstance();
       String [] t = new String[2];
       t[0] = "Index";
       t[1] = "Data";
       dialog.setColumnNames(t);
       dialog.createDialog(array);
       System.out.println(array.length);
      }
    
    
	 // Variables declaration - do not modify//GEN-BEGIN:variables
   /**
    * 
    */
    private javax.swing.JLabel labelAlgorithmt;
    
    /**
     * 
     */
    private javax.swing.JLabel labelOrder;
    
    /**
     * 
     */
    private JRadioButton algorithmtOne;
	
    /**
     * 
     */
    private JRadioButton algorithmtTwo;
	
    /**
     * 
     */
    private JRadioButton orderOne;
	
    /**
     * 
     */
    private JRadioButton orderTwo;
	
    /**
     * 
     */
    private javax.swing.JButton getData;
    
    /**
     * 
     */
    private javax.swing.JButton clean;
    
    /**
     * 
     */
    private javax.swing.JButton sorted;
    
   /**
     * 
     */
    private javax.swing.JButton view;
    
    /**
     * 
     */
    private javax.swing.JPanel selected;
    
    /**
     * 
     */
    private javax.swing.JPanel selectedOne;
    // End of variables declaration//GEN-END:variables

}

