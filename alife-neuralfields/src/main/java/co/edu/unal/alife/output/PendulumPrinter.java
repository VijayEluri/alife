/**
 * 
 */
package co.edu.unal.alife.output;

import java.util.List;

/**
 * @author Juan Figueredo
 *
 */
public class PendulumPrinter extends SimpleOutputPrinter {
	private int pendulumStates;
	private int inputFieldStates;
	private int outputFieldStates;
	
	private double t;
	private List<Double> pendulumAngles;
	private List<Double> inputFieldValues;
	private List<Double> outputFieldValues;
	
	/**
	 * @param pendulumStates
	 * @param inputFieldStates
	 * @param outputFieldStates
	 */
	public PendulumPrinter(int pendulumStates, int inputFieldStates, int outputFieldStates) {
		super();
		this.inputFieldStates = inputFieldStates;
		this.outputFieldStates = outputFieldStates;
		this.pendulumStates = pendulumStates;
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.alife.output.core.SimpleOutputTracer#printTrace()
	 */
	@Override
	public void printTrace() {
		System.out.println("t: "+t+" | tetha: "+pendulumAngles.get(1));
		System.out.println("inputField: "+inputFieldValues);
		System.out.println("outputField: "+outputFieldValues);
	}

	/* (non-Javadoc)
	 * @see co.edu.unal.alife.output.core.SimpleOutputTracer#processTuple(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void processTuple(Object arg) {
		Object[] pair = (Object[])arg;
		t = (Double)pair[0];
		List values = (List)pair[1];
		int from = 0;
		int to = pendulumStates;
		pendulumAngles = values.subList(from, to);
		from = to;
		to += inputFieldStates;
		inputFieldValues = values.subList(from, to);
		from = to;
		to += outputFieldStates;
		outputFieldValues = values.subList(from, to);
	}
	
}
