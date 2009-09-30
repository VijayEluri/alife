package jml.examples;

import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import jml.parser.DFA;
import jml.parser.FileTokenizer;
import jml.parser.TokenDFA;
import jml.parser.WhiteSpaceDFA;

public class TestParser extends JPanel {
	/**
	 * To store the path of the file with the DFA 
	 */
	private String pathfile;
	
	/**
	 * Default Contructor
	 */
    public TestParser() { initComponents(); }
	 /**
     * Initialize the components.
     */
    private void initComponents() {
    	
    	inputLabel = new JLabel("Input to test in the DFA");
    	fileLabel = new JLabel("File not selected");
    	answerLabel = new JLabel("No simulado");
    	tInput = new JTextField("");
	    bFile = new JButton("DFA File");
	    bSimu = new JButton("Simulate");
	    panelh = new JPanel();
		
        this.setLayout(null);        
        inputLabel.setBounds(20, 20, 200, 15);
        tInput.setBounds(20,40,250,20);
        bFile.setBounds(20,70,100,30);
        fileLabel.setBounds(20, 110, 300, 15);
        bSimu.setBounds(20, 135, 100, 30);
        answerLabel.setBounds(20, 180, 300, 15);
        
        this.add(inputLabel);
        this.add(fileLabel);
        this.add(answerLabel);
        this.add(tInput);
        this.add(bFile);
        this.add(bSimu);   

        
        bFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	pathfile = setpathweight();   
            	if (pathfile != null) { fileLabel.setText(pathfile); }            	
            }
        });
        bSimu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
        	    int[] input = TokenDFA.toInt(tInput.getText());
        	    int res = simu(input, pathfile);
        	    answerLabel.setText("The transition was made until the state " + res);    
            }
        });
        panelh.setLayout(null);
        panelh.add(inputLabel);
        panelh.add(fileLabel);
        panelh.add(answerLabel);
        panelh.add(tInput);
        panelh.add(bFile);
        panelh.add(bSimu);
        panelh.setBorder(BorderFactory.createTitledBorder("DFA"));
        panelh.setBounds(10, 0, 330, 220);
        this.add(panelh);
    }
  
	/**
	 * 
	 * @param arr Input vector 
	 * @param f File with the DFA
	 * @return index of the input vector, represents the point 
	 * until transition was made
	 */
	private int simu(int [] arr, String f) {
		FileTokenizer filetok = new FileTokenizer(f);
		filetok.start = 0;
		String linea;
		int lin;
		int col = 0;
		int row = 1;
		while ((linea = filetok.getLine()) != null) {		
			if(TestSpace(linea)) {
					row++;
			} else {
				if(row == 1){ col++; }
			}
		}				
		
		filetok = new FileTokenizer(f);
		filetok.start = 0;				
		int[][] ok = new int[row][col];		
		int i = 0;
		int j = 0;
		
		while((linea = filetok.getLine()) != null) {		
			if(TestSpace(linea)) {
					i++;
					j = -1;
			} else {
					lin = Integer.parseInt(linea);
					ok[i][j] = lin;
			}
			j++;
		}
		DFA dfax = new DFA(ok);
		dfax.transitionTable = ok;
		return dfax.simulate(0, arr);
	}

	/**
	 * If a line is only space
	 * @param num String to test
	 * @return true if its a space, false in other case
	 */
	private boolean TestSpace(String num) {	
		WhiteSpaceDFA number = new WhiteSpaceDFA();
	    int[] input = TokenDFA.toInt(num);
	    number.simulate(0,input);
	    if (number.state == TokenDFA.STOP_REJECTED || number.state == TokenDFA.STOP_ACEPTED) {
	    	return (false);
	    } else {
	    	return (true);
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
	    private JButton bFile;
	    private JButton bSimu;
	    private JTextField tInput;
	    private JLabel fileLabel;
	    private JLabel answerLabel;
	    private JLabel inputLabel;
	    private JPanel panelh;
}
