package jml.examples;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import jml.structures.BitArray;


public class TestBitArray extends JPanel {
    
	/**
	 * FIFO Display
	 */
	private UtilList list;


	private String caddisplay;
	/**
	 * Default Contructor
	 */
    public TestBitArray() { initComponents(); }
	
	 /**
     * Initialize the components.
     *
     */
    private void initComponents() {
    	 this.setFocusable(true);
    	 list = new UtilList();
    	 list.addF("");
    	 caddisplay="";
    	 display = new JTextArea();
         this.add(display);
         display.setBounds(20, 25, 310, 65);
         display.setEditable(false);
         display.setAutoscrolls(true);
         display.setAutoscrolls(true);
      
         
         display.setText(caddisplay);
    	selected = new JPanel();
    	selectedOne = new JPanel();
      
        one = new javax.swing.JButton();
        del = new javax.swing.JButton();
        zero = new javax.swing.JButton();
        solve = new javax.swing.JButton();
        create = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        inv = new javax.swing.JButton();
        uno = new javax.swing.JButton();
        dos = new javax.swing.JButton();
        tres = new javax.swing.JButton();
        cuatro = new javax.swing.JButton();
        operationOne = new javax.swing.JButton("AND");
        operationTwo = new javax.swing.JButton("OR");
        operationThree = new javax.swing.JButton("XOR");
       	this.setLayout(null);
    	
       	
       	this.addKeyListener(new KeyListener() { 
            public void keyTyped(KeyEvent e) {
           	 System.out.println("keyTyped");
           	 char key = e.getKeyChar();
               if((key == '1')){
               }
            }  
            public void keyPressed(KeyEvent e) {} 
            public void keyReleased(KeyEvent e) {} 
         });
    	 
    	 create.setText("RAND");
         create.setBounds(135, 310, 80, 20);
         create.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
             	
             	
             
             		
            	
            	
            	 BitArray temp = new BitArray((String)list.getFirst());
             		
             	
             	
             	double value=0;
             	for(int i = 0; i < temp.dimension() && temp.dimension() < 16; i++){
             		
             		if(temp.get(i)){
             			value = value + Math.pow(2, i);
                 		System.out.println(Math.pow(2, i));
             			
             		}
             		
             		
             	}
               
             	if(value<=32000){
             	BitArray tempOne = new BitArray((int) value, true);
             	
             	list.removeFirst();
             	list.addF(tempOne.toString());
             	 paintDisplay();
            	 caddisplay = tempOne.toString();
             	
            	 
            	 
             	}
                
             }
         });
         this.add(create);
    	 
         
            
         
            
         operationOne.setBounds(235, 210, 80, 20);
         operationOne.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
            	
            	 if(list.size()>=2){
            		 String operatorOne = (String) list.get(0);
            		 String operatorTwo = (String) list.get(1);
            		 
            		 BitArray temp = new BitArray(operatorOne);
            		 BitArray tempOne = new BitArray(operatorTwo);
            		 temp.and(tempOne);
            		 
            		 list.removeFirst();
            		 list.removeFirst();
            		 list.addF(temp.toString());
            		 paintDisplay();
                	 caddisplay = temp.toString();
            		 
            	 }
             }
         });

         
         this.add(operationOne);

         operationTwo.setBounds(135, 210, 80, 20);
         operationTwo.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
            	
            	 if(list.size()>=2){
            		 String operatorOne = (String) list.get(0);
            		 String operatorTwo = (String) list.get(1);
            		 
            		 BitArray temp = new BitArray(operatorOne);
            		 BitArray tempOne = new BitArray(operatorTwo);
            		 temp.or(tempOne);
            		 
            		 list.removeFirst();
            		 list.removeFirst();
            		 list.addF(temp.toString());
            		 paintDisplay();
                	 caddisplay = temp.toString();
            		 
            	 }
            	 
             }
         });
         this.add(operationTwo);
         
         operationThree.setBounds(35, 210, 80, 20);
         operationThree.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
            	 if(list.size()>=2){
            		 String operatorOne = (String) list.get(0);
            		 String operatorTwo = (String) list.get(1);
            		 
            		 BitArray temp = new BitArray(operatorOne);
            		 BitArray tempOne = new BitArray(operatorTwo);
            		 temp.xor(tempOne);
            		 
            		 list.removeFirst();
            		 list.removeFirst();
            		 list.addF(temp.toString());
            		 paintDisplay();
                	 caddisplay = temp.toString();
            		 
            	 }
             }
         });
         this.add(operationThree);
         
         solve.setText("ENTER");
         solve.setBounds(135, 360, 180, 20);
         solve.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
            	 list.removeFirst();
            	 list.addF(caddisplay);    
            	 list.addF("");
            	 paintDisplay();
            	 caddisplay="";
            	 
             }
         });
         this.add(solve);

         zero.setText("0");
         zero.setBounds(35, 160, 80, 20);
         zero.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
             	caddisplay = caddisplay + "0";
             	if(list.size()>0){ list.removeFirst(); }
             	list.addF(caddisplay);
             	paintDisplay();
                
             }
         });
         
         this.add(zero);
         
         one.setText("1");
         one.setBounds(135, 160, 80, 20);
         one.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
            	 caddisplay = caddisplay + "1";
            	 if(list.size()>0){
            	    list.removeFirst();
            	 }
              	 list.addF(caddisplay);
              	 paintDisplay(); 
             }
         });
         this.add(one); 	
         

         del.setText("DEL");
         del.setBounds(235, 160, 80, 20);
         del.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
            	 caddisplay = "";
            	 if(list.size()>0){ list.removeFirst(); }
              	 paintDisplay(); 
             }
         });
         this.add(del);
         
         clear.setText("CLEAR");
         clear.setBounds(35, 360, 80, 20);
         clear.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {    	
             		list=null;
             		list= new UtilList();
             		list.addF("");
             		caddisplay = "";
             	    paintDisplay();  
             }
         });
         this.add(clear);
         
         inv.setText("NOT");
         inv.setBounds(35, 310, 80, 20);
         inv.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {            	 
            		 String operatorOne = (String) list.get(0);
            	            		 
            		 BitArray temp = new BitArray(operatorOne);
            		 
            		 temp.not();
            		           		
            		 list.removeFirst();
            		 list.addF(temp.toString());
            		 paintDisplay();
                	 caddisplay = temp.toString();    
             }
         });
         this.add(inv);
         
         uno.setText("<");
         uno.setBounds(35, 260, 80, 20);
         uno.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
            	
            	 
            	if (!caddisplay.equals("")) {
            	 caddisplay = caddisplay.substring(0, caddisplay.length() - 1);
            	            
            	 if (list.size() > 0) {
             	    list.removeFirst();
             	 }
             	 list.addF(caddisplay);
             	 
             	 paintDisplay();
            	}
             }
         });
         this.add(uno);
         
         dos.setText(">");
         dos.setBounds(135, 260, 80, 20);
         dos.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
            	 
             	if (!caddisplay.equals("")) {
             	 caddisplay = caddisplay.substring(1, caddisplay.length());
             	            
             	 if (list.size() > 0) {
              	    list.removeFirst();
              	 }
              	 list.addF(caddisplay);
              	 
              	 paintDisplay();
             	}
             	
                
             }
         });
         this.add(dos);
         
         tres.setText("=");
         tres.setBounds(235, 260, 80, 20);
         tres.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
            	 if(list.size()>=2){
            		 String operatorOne = (String) list.get(0);
            		 String operatorTwo = (String) list.get(1);
            		 
            		 BitArray temp = new BitArray(operatorOne);
            		 BitArray tempOne = new BitArray(operatorTwo);
            		 boolean band = temp.equals(tempOne);
            		 if(band==true){
            			 list.addF(String.valueOf(1));	 
                    	 caddisplay = "1";            			 
            		 }else{
            			 list.addF(String.valueOf(0));
                    	 caddisplay = "0";
            		 }
            		 
            		 paintDisplay();

            		 
            	 }	
             	
                
             }
         });
         this.add(tres);
         
         cuatro.setText("?");
         cuatro.setBounds(235, 310, 80, 20);
         cuatro.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
             	
             	
                
             }
         });
         this.add(cuatro);
         
         selectedOne.setLayout(null);
       //  selectedOne.add(orderTwo);
       //  selectedOne.add(labelAlgorithmt);
       //  selectedOne.add(labelOrder);
       //  selectedOne.add(orderOne);
       //  selectedOne.add(algorithmtOne);
       //  selectedOne.add(algorithmtTwo);
         selectedOne.setBounds(10, 0, 330, 105);
         selectedOne.setBorder(BorderFactory.createTitledBorder("DISPLAY"));
         this.add(selectedOne);
       
        selected.setLayout(null);
        // selected.add(getData);
       //  selected.add(sorted);
       //  selected.add(clean);
        // selected.add(view);
         selected.setBounds(10, 110, 330, 330);
         selected.setBorder(BorderFactory.createTitledBorder("OPERATORS"));
         this.add(selected);
        

    }
    
    /**
     * Paint Display
     *
     */
    public void paintDisplay(){
    	
    	String temp="";
    	for (int i = 4; i > 0; i--) {
    		
    		if (i <= list.size()) {
    			temp = temp + list.get(i - 1);
    		}else{
    			temp=temp+"";
    			
    		}
    		temp=temp + "\n";
    	}
    	
    	display.setText(temp);
    	
    	
    }
 
   
    private javax.swing.JButton one;
	private javax.swing.JButton zero;
	private javax.swing.JButton del;
	private javax.swing.JButton create;
	private javax.swing.JButton solve;
	private javax.swing.JButton clear;
	private javax.swing.JButton inv;
	private javax.swing.JButton operationOne;
    private javax.swing.JButton operationTwo;
    private javax.swing.JButton operationThree;
    
    private javax.swing.JButton uno;
    private javax.swing.JButton dos;
    private javax.swing.JButton tres;
    private javax.swing.JButton cuatro;
    
    private JTextArea display;
    
    private javax.swing.JPanel selected;

    private javax.swing.JPanel selectedOne;
	
}

