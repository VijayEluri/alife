package co.edu.unal.alife.output.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Tracer implements Visualizer {

	private List<String> labels;
	protected List<List<Double>> data;

	/**
	 * @param labels
	 * @param data
	 */
	public Tracer() {
		this.labels = new ArrayList<String>();
		this.data = new ArrayList<List<Double>>();
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		processTuple(arg);
	}

	/**
	 * @param arg
	 */
	@SuppressWarnings("unchecked")
	public void processTuple(Object arg) {
		Object[] pair = (Object[]) arg;
		Double t = (Double) pair[0];
		List<Double> values = (List) pair[1];
		labels.add(t.toString());
		data.add(values);
	}

	public void appendData(List<Double> newValues) {
		data.add(newValues);
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (List<Double> values : data) {
			sb.append("\t");
			for (Double value : values) {
				sb.append(value + " ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	/**
	 * @return the labels
	 */
	public List<String> getLabels() {
		return labels;
	}
	
	/**
	 * @return the data
	 */
	public List<List<Double>> getData() {
		return data;
	}
}
