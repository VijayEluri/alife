package co.edu.unal.alife.output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Set;

import co.edu.unal.alife.dynamics.DeltaPopulation;

public class Tracer implements Visualizer {
	
	private List<List<String>>				labels;
	private int								N;
	protected List<List<DeltaPopulation>>	data;
	
	/**
	 * @param N
	 */
	public Tracer(int N) {
		this.N = N;
		this.labels = new ArrayList<List<String>>(N);
		this.data = new ArrayList<List<DeltaPopulation>>(N);
		for (int i = 0; i < N; i++) {
			labels.add(new ArrayList<String>());
			data.add(i, new ArrayList<DeltaPopulation>());
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
	public void processTuple(Object arg) {
		Object[] tuple = (Object[]) arg;
		int number = (Integer) tuple[0];
		Double t = (Double) tuple[1];
		DeltaPopulation population = (DeltaPopulation) tuple[2];
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
	public List<List<DeltaPopulation>> getData() {
		return data;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		int i = 0;
		for (List<DeltaPopulation> dataSource : data) {
			sb.append(toString(i++, dataSource));
		}
		return sb.toString();
	}
	
	/**
	 * @param sb
	 * @param i
	 * @param dataSource
	 */
	private String toString(int i, List<DeltaPopulation> dataSource) {
		StringBuffer sb = new StringBuffer();
		sb.append("\n#Printing population " + i + ":\n");
		List<String> times = labels.get(i);
		int j = 0;
		for (DeltaPopulation population : dataSource) {
			sb.append(populationToString(population,times.get(j++)));
			sb.append("\n");
		}
		sb.append("\n");
		return sb.toString();
	}
	
	/**
	 * @param sb
	 * @param i
	 * @param dataSource
	 */
	private String toString2D(int i, List<DeltaPopulation> dataSource) {
		StringBuffer sb = new StringBuffer();
		sb.append("\n#Printing population " + i + ":\n");
		List<String> times = labels.get(i);
		DeltaPopulation pop = dataSource.get(0);
		sb.append(populationToString(times, dataSource));
		return sb.toString();
	}
	
	public void printToFiles(String[] filenames, boolean is2d) {
		try {
			int i = 0;
			for (List<DeltaPopulation> dataSource : data) {
				if (filenames[i] != null) {
					File f = new File(filenames[i]);
					FileWriter fw = new FileWriter(f);
					BufferedWriter bw = new BufferedWriter(fw);
					if (is2d) {
						bw.write(toString2D(i, dataSource)); //2d
					} else {
						bw.write(toString(i, dataSource)); //3d
					}
					bw.close();
					fw.close();
				}
				i++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getN() {
		return N;
	}
	
	private String populationToString(List<String> times, List<DeltaPopulation> dataSource) {
		StringBuffer sb = new StringBuffer();
		if (dataSource.size() == 0) {
			return null;
		}
		Set<Double> positions = dataSource.get(0).getPositions();
		for (Double x : positions) {
			int j = 0;
			for (DeltaPopulation pop : dataSource) {
				String t = times.get(j++);
				sb.append(t + "\t" + pop.getElementValue(x).toString());
				sb.append("\n");
			}
			sb.append("\n\n");
		}
		return sb.toString();
	};
	
	private String populationToString(DeltaPopulation pop, String t) {
		StringBuffer sb = new StringBuffer();
		Set<Double> positions = pop.getPositions();
		for (Double x : positions) {
			sb.append(x + "\t" + t + "\t" + pop.getElementValue(x).toString());
			sb.append("\n");
		}
		return sb.toString();
	}
}
