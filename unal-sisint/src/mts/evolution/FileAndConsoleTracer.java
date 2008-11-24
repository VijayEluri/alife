/**
 * 
 */
package mts.evolution;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

import jml.basics.Algorithm;
import jml.util.SimpleConsoleTracer;

/**
 * @author Juan Figueredo
 *
 */
public class FileAndConsoleTracer extends SimpleConsoleTracer {
	
	FileWriter fw;
	BufferedWriter bw; 
	
	public FileAndConsoleTracer(String filename) {
		try {
			fw = new FileWriter(filename);
			bw = new BufferedWriter(fw);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void add(Algorithm algorithm, Object obj) {
	    super.add(algorithm, obj);
		String s = obj.toString();
		s = s.trim();
		StringTokenizer st = new StringTokenizer(s);
		st.nextToken();
		String fitness = st.nextToken();
		try {
			bw.write(fitness+"\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	
	public void close() {
		try {
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
