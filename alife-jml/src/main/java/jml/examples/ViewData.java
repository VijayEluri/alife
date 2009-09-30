package jml.examples;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;



public class ViewData extends JDialog {
	 
	  /**Data*/
	  private double array[][];
	  
	  
	  /**Data*/
	  private static int MAXROWS=2000;
	  
	  private static boolean INDEX=true;
	  
	  /**
	   * Lenth the columns
	   */
	  private double columns;
	  
	  /**
	   * Lenth the rows
	   */
	  private double rows;
	
	  /** Click to cancel the dialog */
	  protected JButton m_CancelButton = new JButton("Cancel");

	  
	  private ModeloTabla model;
	  private String[] columnNames = {""};  
	
	  /**
	   * creates all the elements of the dialog
	   */
	  protected void createDialog(double array[][]) {
	    
		if(array != null && array.length>=0 && array[0].length>=0){  
		this.array=array;
			
		JPanel panel;
		setTitle("Viewer");
		
		table = new JTable();
        jScrollPane = new JScrollPane();
        this.rows=array.length;
   	    this.columns=array[0].length;
   	    if(INDEX ==true){
   	    	this.columns=this.columns+1;
   	    }
        
        model=new ModeloTabla(columnNames);
        setData();
        table.setModel(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollPane.setBorder(BorderFactory.createEtchedBorder());
        jScrollPane.setBounds(20,130,500,250);
        jScrollPane.getViewport().add(table, null);
        jScrollPane.getViewport().setBackground(Color.white);
        //this.add(jScrollPane);
        
        
	    getContentPane().setLayout(new BorderLayout());
	    
   	    
	    // Buttons
	    panel = new JPanel(new BorderLayout());
	
	    m_CancelButton.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {

	        
	      }
	    });
	    
	    panel.add(jScrollPane,BorderLayout.NORTH);
	    panel.add(m_CancelButton,BorderLayout.SOUTH);
	    getContentPane().add(panel);
	    
	    java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	   // panel. setBounds((screenSize.width-255)/2, (screenSize.height-374)/2, 255, 374);
      
	    this.setVisible(true);
	    pack();
		}
	  }
	  
	 
	  /**
	   * creates all the elements of the dialog
	   */
	  protected void createDialog(double array[]) {
	    
		if(array != null && array.length>=0 ){  
		
			this.array=new double[array.length][1];
	    
			for(int k=0;k<array.length;k++){
	    	
				this.array[k][0]=array[k];
	    
			}
			
		JPanel panel;
		setTitle("Viewer");
		
		table = new JTable();
        jScrollPane = new JScrollPane();
        this.rows=array.length;
   	    this.columns=1;
   	    if(INDEX ==true){
   	    	this.columns=this.columns+1;
   	    }
        
        model=new ModeloTabla(columnNames);
        setData();
        table.setModel(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollPane.setBorder(BorderFactory.createEtchedBorder());
        jScrollPane.setBounds(20,130,500,250);
        jScrollPane.getViewport().add(table, null);
        jScrollPane.getViewport().setBackground(Color.white);
        //this.add(jScrollPane);
        
        
	    getContentPane().setLayout(new BorderLayout());
	    
   	    
	    // Buttons
	    panel = new JPanel(new BorderLayout());
	
	    m_CancelButton.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {

	        
	      }
	    });
	    
	    panel.add(jScrollPane,BorderLayout.NORTH);
	    panel.add(m_CancelButton,BorderLayout.SOUTH);
	    getContentPane().add(panel);
	    
	    java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	   // panel. setBounds((screenSize.width-255)/2, (screenSize.height-374)/2, 255, 374);
      
	    this.setVisible(true);
	    pack();
		}
	  }
	  

	
	  
	  public void setColumnNames(String [] titles){
		  this.columnNames =titles;
	  }
      
	  public void setData(){
		  
		  for(double i=0;(i<rows && rows <MAXROWS);i++){
			  model.addEmptyRow();
			  for(double j=0;j<columns;j++){
				  if(j==0){
				    model.setValueAt(String.valueOf(((int)i+1)),(int)i, (int)j); 
					  
				  }else{
				 
	        	   model.setValueAt(String.valueOf(array[(int) i][(int)j-1]),(int)i, (int)j);
	        	  
				  }
	 
			  }  
    	  }
	  }
	  
	  /**
	     * Singleton dessign pattern.
	     */
	    private static ViewData instance = null;
		
		
		/**
	     * Returns the only one instance allwoed for the class.
	     * @return The class instance.
	     */
	    public static synchronized ViewData getInstance() {
	        if(instance == null){
	            instance = new ViewData();
	        }
	        return instance;
	            
	    }
	  
	  private JTable table;
	  private JScrollPane jScrollPane;
        

}

