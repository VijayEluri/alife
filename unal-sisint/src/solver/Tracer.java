package solver;

import java.util.Iterator;
import java.util.Vector;

public class Tracer {

	private String[] labels;
	protected Vector<double[]> data;
	private int length;
	private int size;
	
	/**
	 * @param labels
	 * @param data
	 */
	public Tracer(int length) {
		this.length = length;
		this.labels = new String[length];
		this.data = new Vector();
		this.size = 0;
	}
	
	public void appendData(double[] newData){
		data.add(newData);
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for (Iterator<double[]> iter = data.iterator(); iter.hasNext();) {
			sb.append("\t");
			double[] element = iter.next();
			for (int i = 0; i < element.length; i++) {
				sb.append(element[i]+" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public Vector<double[]> getData() {
		return data;
	}
	public String[] getLabels() {
		return labels;
	}
	public void setData(Vector<double[]> data) {
		this.data = data;
	}
	public void setLabels(String[] labels) {
		this.labels = labels;
	}
	
	
}
