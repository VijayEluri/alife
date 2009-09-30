package jml.examples;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileFilter;

import jml.evolution.Operator;
import jml.evolution.Population;
import jml.evolution.binary.fitness.BoundedlyDeceptive;
import jml.evolution.binary.fitness.Deceptive;
import jml.evolution.binary.fitness.M7_Deceptive;
import jml.evolution.binary.fitness.M8_Deceptive;
import jml.evolution.binary.fitness.M9_Deceptive;
import jml.evolution.binary.fitness.MaxOnes;
import jml.evolution.binary.fitness.RoyalRoad;
import jml.evolution.binary.operators.AddGen;
import jml.evolution.binary.operators.DelGen;
import jml.evolution.binary.operators.Join;
import jml.evolution.binary.operators.Mutation;
import jml.evolution.binary.operators.SingleBitMutation;
import jml.evolution.binary.operators.Transposition;
import jml.evolution.binary.operators.XOver;
import jml.evolution.real.RealVectorGenotype;
import jml.evolution.real.RealVectorLimits;
import jml.evolution.real.fitness.Ackley;
import jml.evolution.real.fitness.Bohachevsky;
import jml.evolution.real.fitness.CentralTwoPeakTrap;
import jml.evolution.real.fitness.Griewangk;
import jml.evolution.real.fitness.M1;
import jml.evolution.real.fitness.M2;
import jml.evolution.real.fitness.M3;
import jml.evolution.real.fitness.M4;
import jml.evolution.real.fitness.Rastrigin;
import jml.evolution.real.fitness.RosenbrockSaddle;
import jml.evolution.real.fitness.Schwefel;
import jml.evolution.real.fitness.Shubert_1;
import jml.evolution.real.fitness.TwoPeakTrap;
import jml.evolution.real.operators.FlipMutation;
import jml.evolution.real.operators.GaussianMutation;
import jml.evolution.real.operators.GeneXOver;
import jml.evolution.real.operators.LinearXOver;
import jml.evolution.real.operators.LinearXOverPerDimension;
import jml.evolution.real.operators.OneDimensionLinearXOver;
import jml.evolution.real.operators.OneDimensionSimpleXOver;
import jml.evolution.real.operators.SimpleXOver;
import jml.evolution.real.operators.UniformMutation;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class PanelParameters extends JPanel {
    Vector operators= new Vector();
    String genotype;
    Vector operatorsSel= new Vector();
    String fitness;

	public PanelParameters() {
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
    	jComboBox2.addItem("BIT ARRAY");
    	jComboBox2.addItem("REAL VECTOR");
    	fillBitArray();
    	jButton5.setBounds(new Rectangle(235, 35, 101, 20));
        jButton5.setText("Create");
        jButton5.addActionListener(new PanelParameters_jButton5_actionAdapter(this));
        this.setLayout(null);
        jComboBox1.setBounds(new Rectangle(69, 86, 208, 20));
        jComboBox1.addActionListener(new
                                     PanelParameters_jComboBox1_actionAdapter(this));
        jLabel1.setText("Fitness");
        jLabel1.setBounds(new Rectangle(14, 60, 106, 21));
        jLabel2.setText("Genotype");
        jLabel2.setBounds(new Rectangle(12, 10, 106, 21));
        jLabel3.setText("Operators");
        jLabel3.setBounds(new Rectangle(12, 118, 90, 21));
        
       
        jScrollPane.setBorder(BorderFactory.createEtchedBorder());
        jScrollPane.setBounds(68, 140, 210, 86);
        jScrollPane.getViewport().add(jList1, null);
        jScrollPane.getViewport().setBackground(Color.white);
        jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       // jList1.setBounds(new Rectangle(68, 140, 210, 86));
        jButton1.setBounds(new Rectangle(97, 234, 66, 26));
        jButton1.setSelected(false);
        jButton1.setSelectedIcon(null);
        jButton1.setText("ADD");
        jButton1.addActionListener(new PanelParameters_jButton1_actionAdapter(this));
        jButton2.setBounds(new Rectangle(182, 236, 66, 26));
        jButton2.setText("DEL");
        jButton2.addActionListener(new PanelParameters_jButton2_actionAdapter(this));
        jTextField1.setText("10");
        jTextField1.addKeyListener(new KeyAdapter() {
	       	 public void keyTyped(KeyEvent e) {
	       	 	char c = e.getKeyChar();      
	       	      if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
	       	        getToolkit().beep();
	       	        e.consume();
	       	      }	       	 
	       	 }
	    });
        jTextField1.setBounds(new Rectangle(12, 399, 120, 22));
        jLabel4.setText("Iterators:");
        jLabel4.setBounds(new Rectangle(14, 374, 124, 19));
        jLabel5.setText("Populations Lenght:");
        jLabel5.setBounds(new Rectangle(13, 427, 115, 19));
        jButton3.setBounds(new Rectangle(212, 399, 116, 29));
        jButton3.setText("Evolve");
        jButton3.addActionListener(new PanelParameters_jButton3_actionAdapter(this));
        jTextField2.setText("10");
        jTextField2.setBounds(new Rectangle(14, 451, 121, 22));
        jTextField2.addKeyListener(new KeyAdapter() {
	       	 public void keyTyped(KeyEvent e) {
	       	 	char c = e.getKeyChar();      
	       	      if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
	       	        getToolkit().beep();
	       	        e.consume();
	       	      }	       	 
	       	 }
	    });
        jComboBox2.setBounds(new Rectangle(12, 35, 208, 20));
        jComboBox2.addActionListener(new
                                     PanelParameters_jComboBox2_actionAdapter(this));
        jList2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setBorder(BorderFactory.createEtchedBorder());
        jScrollPane1.setBounds(65, 270, 217, 87);
        jScrollPane1.getViewport().add(jList2, null);
        jScrollPane1.getViewport().setBackground(Color.white);
       // jList2.setBounds(new Rectangle(65, 270, 217, 87));
        jButton4.setBounds(new Rectangle(212, 446, 116, 29));
        jButton4.setVisible(false);
        jButton4.setText("View Stadistics");
        
        this.add(jButton5);
        this.add(jLabel2);
        this.add(jLabel5);
        this.add(jTextField1);
        this.add(jLabel4);
        this.add(jLabel1);
        this.add(jLabel3);
        this.add(jButton1);
        this.add(jButton2);
       // this.add(jList2);
        this.add(jTextField2);
        this.add(jComboBox2);
        this.add(jComboBox1);
        this.add(jButton4);
        this.add(jButton3);
        this.add(jScrollPane);
        this.add(jScrollPane1);
       
        
    }

    JComboBox jComboBox1 = new JComboBox();
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    JList jList1 = new JList();
    JButton jButton5 = new JButton();
    
    
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JTextField jTextField1 = new JTextField();
    JLabel jLabel4 = new JLabel();
    JLabel jLabel5 = new JLabel();
    JButton jButton3 = new JButton();
    JTextField jTextField2 = new JTextField();
    JComboBox jComboBox2 = new JComboBox();
    JList jList2 = new JList();
    JButton jButton4 = new JButton();
    JScrollPane jScrollPane = new JScrollPane();
    JScrollPane jScrollPane1 = new JScrollPane();
    
    public void jButton1_actionPerformed(ActionEvent e) {

    	int pos=jList1.getSelectedIndex();
    	if(pos >=0){

    		operatorsSel.add(jList1.getModel().getElementAt(pos));
    		operators.remove(pos);
    		jList1.setListData(operators);
    		jList2.setListData(operatorsSel);
    		
    	}

    }

    public void jButton2_actionPerformed(ActionEvent e) {
    	int pos=jList2.getSelectedIndex();
    	if(pos >=0){
    		operators.add(jList2.getModel().getElementAt(pos));
    		operatorsSel.remove(pos);
    		jList2.setListData(operatorsSel);
    		jList1.setListData(operators);
    	}
    }

    public void jButton3_actionPerformed(ActionEvent e) {
    	String Geno = (String) jComboBox2.getSelectedItem();  	
    	if(Geno.equals("BIT ARRAY")){
    	
    	if(HaeaTest.getInstance().getGenotype()==null){
        	HaeaTest.getInstance().setGenotypeBit(40);	
        }
        
       if(HaeaTest.getInstance().getPhenotype()==null){
    	   HaeaTest.getInstance().setPhenotype();
        }
      
       String fit=(String) jComboBox1.getSelectedItem();
       
   
       if(fit.equals("BOUNTEDLY DECEPTIVE")){
    	   HaeaTest.getInstance().setFitness(new BoundedlyDeceptive());
    	   
       }else if(fit.equals("DECEPTIVE")){
    	   HaeaTest.getInstance().setFitness(new Deceptive());
    	   
       }else if(fit.equals("M7 DECEPTIVE")){
    	   HaeaTest.getInstance().setFitness(new M7_Deceptive());
    	   
       }else if(fit.equals("M8 DECEPTIVE")){
    	   HaeaTest.getInstance().setFitness(new M8_Deceptive());
    	   
       }else if(fit.equals("M9 DECEPTIVE")){
    	   HaeaTest.getInstance().setFitness(new M9_Deceptive());
    	   
       }else if(fit.equals("MAX ONES")){
    	   HaeaTest.getInstance().setFitness(new MaxOnes());
    	   
       }else if(fit.equals("ROYAL ROAD")){
    	   HaeaTest.getInstance().setFitness(new RoyalRoad(5));
    	   
       }
       
       HaeaTest.getInstance().setEnvironment();
       
       Vector opera= new Vector();
       for(int j=0;j<jList2.getModel().getSize();j++){
			String ope=(String) jList2.getModel().getElementAt(j); 
			
			if(ope.equals("ADD GEN")){
				 AddGen addg = new AddGen(HaeaTest.getInstance().getEnvironment());		
				 opera.add(addg);		
				 System.out.println("ADD GEN");
			}else if(ope.equals("DEL GEN")){
				DelGen delg = new DelGen(HaeaTest.getInstance().getEnvironment());		
				opera.add(delg);	
				System.out.println("DEL GEN");
			}else if(ope.equals("JOIN")){
				Join jo = new Join(HaeaTest.getInstance().getEnvironment());		
				opera.add(jo);		
				System.out.println("JOIN");
			}else if(ope.equals("MUTATION")) {
				 Mutation mutation = new Mutation(HaeaTest.getInstance().getEnvironment());		
				 opera.add(mutation);
				 System.out.println("MUTATION");
			}else if(ope.equals("SINGLE BIT MUTATION")) {
				SingleBitMutation sbm = new SingleBitMutation(HaeaTest.getInstance().getEnvironment());
				opera.add(sbm);
				System.out.println("SINGLE BIT MUTATION");
			}else if(ope.equals("TRANSPOSITION")){
				Transposition transpose = new Transposition(HaeaTest.getInstance().getEnvironment());
				opera.add(transpose);
				System.out.println("TRANSPOSITION");
			}else if(ope.equals("XOVER")){
				XOver xo = new XOver(HaeaTest.getInstance().getEnvironment());
				opera.add(xo);		
				System.out.println("XOVER");
			}	
			
		}
       
       Operator ope[] = new  Operator[opera.size()];
       for(int j=0;j<opera.size();j++){
    	   ope[j]=(Operator) opera.get(j);
       }
       
       HaeaTest.getInstance().setOperator(ope);
       
       try{
			int val1= Integer.parseInt(jTextField1.getText());
			int val2= Integer.parseInt(jTextField2.getText());
			HaeaTest.getInstance().setPopulator(val2);
			HaeaTest.getInstance().setIterator(val1);
			
			if(HaeaTest.getInstance().getOperator().length >0){
			 Population popu = HaeaTest.getInstance().run();
			}
			 
		}catch(Exception ex){
			System.out.println(ex.toString());
		}
      
       
    	}else if(Geno.equals("REAL VECTOR")){
    		
    		if(HaeaTest.getInstance().getGenotype()==null){
    			double data[][]=openFileWeight(null);
        		
        		
        		System.out.println(data[0].length);
        		double min[] = data[0];
        		double max[] = data[1];
        		HaeaTest.getInstance().setGenotypeReal(min, max);
            	
            }
          
            
           if(HaeaTest.getInstance().getPhenotype()==null){
        	   HaeaTest.getInstance().setPhenotype();
            	
            }
           
           String fit=(String) jComboBox1.getSelectedItem();
        
           
           
           if(fit.equals("ACKLEY")){
        	   HaeaTest.getInstance().setFitness(new Ackley());
        	   
           }else if(fit.equals("BOHACHEVSKY")){
        	   HaeaTest.getInstance().setFitness(new Bohachevsky(true));
        	   
           }else if(fit.equals("CENTRAL TWO PEAK TRAP")){
        	   HaeaTest.getInstance().setFitness(new CentralTwoPeakTrap());
        	   
           }else if(fit.equals("GRIEWANGK")){
        	   HaeaTest.getInstance().setFitness(new Griewangk());
        	   
           }else if(fit.equals("M1")){
        	   HaeaTest.getInstance().setFitness(new M1());
        	   
           }else if(fit.equals("M2")){
        	   HaeaTest.getInstance().setFitness(new M2());
        	   
           }else if(fit.equals("M3")){
        	   HaeaTest.getInstance().setFitness(new M3());
        	   
           }else if(fit.equals("M4")){
        	   HaeaTest.getInstance().setFitness(new M4());
        	   
           }else if(fit.equals("RASTRIGIN")){
        	   HaeaTest.getInstance().setFitness(new Rastrigin());
        	   
           }else if(fit.equals("ROSEN BROCK SADDLE")){
        	   HaeaTest.getInstance().setFitness(new RosenbrockSaddle());
        	   
           }else if(fit.equals("SCHWEFEL")){
        	   HaeaTest.getInstance().setFitness(new Schwefel());
        	   
           }else if(fit.equals("SHUBERT")){
        	   HaeaTest.getInstance().setFitness(new Shubert_1());
        	   
           }else if(fit.equals("TWO PEAK TRAP")){
        	   HaeaTest.getInstance().setFitness(new TwoPeakTrap());
        	   
           }
           
           HaeaTest.getInstance().setEnvironment();
           
           Vector opera= new Vector();
           for(int j=0;j<jList2.getModel().getSize();j++){
    			String ope=(String) jList2.getModel().getElementAt(j); 
    			if(ope.equals("FLIP MUTATION")){
    				RealVectorGenotype genot = (RealVectorGenotype)HaeaTest.getInstance().getEnvironment().getGenotype();
        			RealVectorLimits limites = genot.getLimits();
    				 FlipMutation op = new FlipMutation(HaeaTest.getInstance().getEnvironment(),limites);		
    				 opera.add(op);		
    				 System.out.println("FLIP MUTATION");
    			}else if(ope.equals("GAUSSIAN MUTATION")){
    				RealVectorGenotype genot = (RealVectorGenotype)HaeaTest.getInstance().getEnvironment().getGenotype();
        			RealVectorLimits limites = genot.getLimits();
    				GaussianMutation op = new GaussianMutation(HaeaTest.getInstance().getEnvironment(),limites,2.5);		
    				opera.add(op);	
    				System.out.println("GAUSSIAN MUTATION");
    			}else if(ope.equals("GENE XOVER")){
    				GeneXOver op = new GeneXOver(HaeaTest.getInstance().getEnvironment());		
    				opera.add(op);		
    				System.out.println("GENE XOVER");
    			}else if(ope.equals("LINE XOVER")) {
    				 LinearXOver op = new LinearXOver(HaeaTest.getInstance().getEnvironment());		
    				 opera.add(op);
    				 System.out.println("LINE XOVER");
    			}else if(ope.equals("LINE XOVER PERDIMENSION")) {
    				LinearXOverPerDimension op = new LinearXOverPerDimension(HaeaTest.getInstance().getEnvironment());
    				opera.add(op);
    				System.out.println("LINE XOVER PERDIMENSION");
    			}else if(ope.equals("ONE DIMENSION LINEAR XOVER")){
    				OneDimensionLinearXOver op = new OneDimensionLinearXOver(HaeaTest.getInstance().getEnvironment());
    				opera.add(op);
    				System.out.println("ONE DIMENSION LINEAR XOVER");
    			}else if(ope.equals("ONE DIMENSION SIMPLE XOVER")){
    				OneDimensionSimpleXOver op = new OneDimensionSimpleXOver(HaeaTest.getInstance().getEnvironment());
    				opera.add(op);		
    				System.out.println("ONE DIMENSION SIMPLE XOVER");
    			}else if(ope.equals("SIMPLE XOVER")){
    				SimpleXOver op = new SimpleXOver(HaeaTest.getInstance().getEnvironment());
    				opera.add(op);		
    				System.out.println("SIMPLE XOVER");
    			}else if(ope.equals("UNIFORM MUTATION")) {
    				RealVectorGenotype genot = (RealVectorGenotype)HaeaTest.getInstance().getEnvironment().getGenotype();
        			RealVectorLimits limites = genot.getLimits();
    				UniformMutation op = new UniformMutation(HaeaTest.getInstance().getEnvironment(),limites);
    				opera.add(op);		
    				System.out.println("UNIFORM MUTATION");
    			}		
    			
    		}
           
           Operator ope[] = new  Operator[opera.size()];
           for(int j=0;j<opera.size();j++){
        	   ope[j]=(Operator) opera.get(j);
           }
           
           HaeaTest.getInstance().setOperator(ope);
           
           try{
    			int val1= Integer.parseInt(jTextField1.getText());
    			int val2= Integer.parseInt(jTextField2.getText());
    			HaeaTest.getInstance().setPopulator(val2);
    			HaeaTest.getInstance().setIterator(val1);
    			
    			if(HaeaTest.getInstance().getOperator().length >0){
    			 Population popu = HaeaTest.getInstance().run();
    			}
    			 
    		}catch(Exception ex){
    			
    		}
          
    	}
       
    }

    public void jButton5_actionPerformed(ActionEvent e) {
    	String Geno = (String) jComboBox2.getSelectedItem();
    	if(Geno.equals("BIT ARRAY")){	
    	 new GenotypeDialog(true).setVisible(true);
    	}else if(Geno.equals("REAL VECTOR")){
    		String path = setpathweight();	
    		double data[][]=openFileWeight(path);
    		
    		
    		System.out.println(data[0].length);
    		double min[] = data[0];
    		double max[] = data[1];
    		HaeaTest.getInstance().setGenotypeReal(min, max);
            
    	}
    }
    
    public void jComboBox2_actionPerformed(ActionEvent e) {

    	if(jComboBox2.getSelectedItem().equals("BIT ARRAY")){
    		fillBitArray();
    	}
    	
    	if(jComboBox2.getSelectedItem().equals("REAL VECTOR")){
    		fillRealVector();
    	}
    }

    public void jComboBox1_actionPerformed(ActionEvent e) {
//Change de Fitness
    }
    public void fillBitArray(){
    	
    	operatorsSel.removeAllElements();
    	operators.removeAllElements();
    	operators.add("ADD GEN");
    	operators.add("DEL GEN");
    	operators.add("JOIN");
    	operators.add("MUTATION");
    	operators.add("SINGLE BIT MUTATION");
    	operators.add("TRANSPOSITION");
    	operators.add("XOVER");
      	jList1.setListData(operators);
      	jList2.setListData(operatorsSel);
      	
      	
    	
    	jComboBox1.removeAllItems();
    	jComboBox1.addItem("BOUNTEDLY DECEPTIVE");
    	jComboBox1.addItem("DECEPTIVE");
    	jComboBox1.addItem("M7 DECEPTIVE");
    	jComboBox1.addItem("M8 DECEPTIVE");
    	jComboBox1.addItem("M9 DECEPTIVE");
    	jComboBox1.addItem("MAX ONES");
    	jComboBox1.addItem("ROYAL ROAD");
    	
        
    }
    
    public void fillRealVector(){
    	operatorsSel.removeAllElements();
    	operators.removeAllElements();
    	operators.add("FLIP MUTATION");
    	operators.add("GAUSSIAN MUTATION");
    	operators.add("GENE XOVER");
    	operators.add("LINE XOVER");
    	operators.add("LINE XOVER PERDIMENSION");
    	operators.add("ONE DIMENSION LINEAR XOVER");
    	operators.add("ONE DIMENSION SIMPLE XOVER");
    	operators.add("SIMPLE XOVER");
    	operators.add("UNIFORM MUTATION");
       	jList1.setListData(operators);
       	jList2.setListData(operatorsSel);
       	
     	jComboBox1.removeAllItems();
    	jComboBox1.addItem("ACKLEY");
    	jComboBox1.addItem("BOHACHEVSKY");
    	jComboBox1.addItem("CENTRAL TWO PEAK TRAP");
    	jComboBox1.addItem("GRIEWANGK");
    	jComboBox1.addItem("M1");
    	jComboBox1.addItem("M2");
    	jComboBox1.addItem("M3");
    	jComboBox1.addItem("M4");
    	jComboBox1.addItem("RASTRIGIN");
    	jComboBox1.addItem("ROSEN BROCK SADDLE");
    	jComboBox1.addItem("SCHWEFEL");
    	jComboBox1.addItem("SHUBERT");
    	jComboBox1.addItem("TWO PEAK TRAP");
    	
    }
    
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
	 private double[][] openFileWeight(String path) {
	       double[][]weight = null;
	     
	       ArrayList listMin;
	       ArrayList listMax;
	       try {
	           File filePatron = new File(path);
	           FileReader lector = new FileReader(filePatron);
	           BufferedReader input = new BufferedReader(lector);
	           String texto;
	           listMin = new ArrayList();
	           listMax = new ArrayList();
		       while ((texto = input.readLine()) != null) {
		              
		    	      String linea[] = texto.split(" ");	             
		              String numero = linea[0];
		              String numero2 = linea[1];
		              String a = ".";
		              String b = ",";
		              numero = numero.replaceAll(b, a);
		              numero2 = numero2.replaceAll(b, a);	
		              listMin.add(numero);
		              listMax.add(numero);
		        }       
		        if (listMax != null) {
			        weight = new double[listMax.size()][];
			        for (long i = 0; i < listMax.size(); i++) {        	
			        	weight[0][(int) i] = Double.parseDouble((String) listMin.get((int) i));        	
			        	weight[1][(int) i] = Double.parseDouble((String) listMax.get((int) i));
			        }       
			        System.out.println("Read " + listMax.size() + " rows");
		        } else {
		               weight = new double[2][10];
		               for (int i = 0; i < 10; i++) {
		            	   weight[0][i] = 1;
		            	   weight[1][i] = i + 10;
		               }
		        }
	            return weight;
	        } catch (Exception ex) {
	               System.out.println(ex.toString());
	               weight = new double[2][10];
	               for (int i = 0; i < 10; i++) {
	            	   weight[0][i] = 1;
	            	   weight[1][i] = i + 10;
	               }
	               return weight;
	        }        
	    }
	 
}


class PanelParameters_jComboBox1_actionAdapter implements ActionListener {
    private PanelParameters adaptee;
    PanelParameters_jComboBox1_actionAdapter(PanelParameters adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jComboBox1_actionPerformed(e);
    }
}


class PanelParameters_jComboBox2_actionAdapter implements ActionListener {
    private PanelParameters adaptee;
    PanelParameters_jComboBox2_actionAdapter(PanelParameters adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jComboBox2_actionPerformed(e);
    }
}


class PanelParameters_jButton3_actionAdapter implements ActionListener {
    private PanelParameters adaptee;
    PanelParameters_jButton3_actionAdapter(PanelParameters adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton3_actionPerformed(e);
    }
}


class PanelParameters_jButton2_actionAdapter implements ActionListener {
    private PanelParameters adaptee;
    PanelParameters_jButton2_actionAdapter(PanelParameters adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton2_actionPerformed(e);
    }
}


class PanelParameters_jButton1_actionAdapter implements ActionListener {
    private PanelParameters adaptee;
    PanelParameters_jButton1_actionAdapter(PanelParameters adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton1_actionPerformed(e);
    }
}

class PanelParameters_jButton5_actionAdapter implements ActionListener {
    private PanelParameters adaptee;
    PanelParameters_jButton5_actionAdapter(PanelParameters adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {

        adaptee.jButton5_actionPerformed(e);
    }
}