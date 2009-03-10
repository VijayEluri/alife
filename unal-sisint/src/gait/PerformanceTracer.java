/**
 * 
 */
package gait;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import solver.Tracer;

/**
 * @author Juan Figueredo
 */
public class PerformanceTracer extends Tracer {

	public static double h = 1.0 / 40;

	/**
	 * @param length
	 */
	public PerformanceTracer(int length) {
		super(length);
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		double t = 0;
		for (int j = 0; j < data.size(); j++) {
			t += h;
			double[] dataJ = data.get(j);
			for (int k = 0; k < dataJ.length; k++) {
				sb.append(k + "\t" + t + "\t" + dataJ[k]);
				sb.append("\n");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public void printToFile(String filename) {
		try {
			// int i = 0;
			File f = new File(filename);
			FileWriter fw = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("\n#Printing population\n");
			bw.write(toString());
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
