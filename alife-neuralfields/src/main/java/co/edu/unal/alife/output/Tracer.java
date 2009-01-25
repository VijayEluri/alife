package co.edu.unal.alife.output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import co.edu.unal.alife.dynamics.DeltaPopulation;

public class Tracer implements Visualizer {

	private List<List<String>> labels;
	protected List<List<DeltaPopulation<Double>>> data;

	/**
	 * @param N
	 */
	public Tracer(int N) {
		this.labels = new ArrayList<List<String>>(N);
		this.data = new ArrayList<List<DeltaPopulation<Double>>>(N);
		for (int i = 0; i < N; i++) {
			labels.add(new ArrayList<String>());
			data.add(i, new ArrayList<DeltaPopulation<Double>>());
		}
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
		Object[] tuple = (Object[]) arg;
		int number = (Integer) tuple[0];
		Double t = (Double) tuple[1];
		DeltaPopulation<Double> population = (DeltaPopulation<Double>) tuple[2];
		labels.get(number).add(t.toString());
		data.get(number).add(population);
	}

	/**
	 * @return the labels
	 */
	public List<List<String>> getLabels() {
		return labels;
	}

	/**
	 * @return the data
	 */
	public List<List<DeltaPopulation<Double>>> getData() {
		return data;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		int i = 0;
		for (List<DeltaPopulation<Double>> dataSource : data) {
			sb.append(toString(i++, dataSource));
		}
		return sb.toString();
	}

	/**
	 * @param sb
	 * @param i
	 * @param dataSource
	 */
	private String toString(int i, List<DeltaPopulation<Double>> dataSource) {
		StringBuffer sb = new StringBuffer();
		sb.append("\n#Printing population "+i+":\n");
		List<String> times = labels.get(i);
		int j = 0;
		for (DeltaPopulation<Double> population : dataSource) {
			sb.append(population.toString(times.get(j++)));
			sb.append("\n");
		}
		sb.append("\n");
		return sb.toString();
	}
	
	public void printToFiles(String[] filenames) {
		try {
			int i = 0;
			for (List<DeltaPopulation<Double>> dataSource : data) {
				File f = new File(filenames[i]);
				FileWriter fw = new FileWriter(f);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(toString(i, dataSource));
				i++;
				bw.close();
				fw.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
