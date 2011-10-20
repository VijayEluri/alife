package co.edu.unal.alife.dynamics;

public abstract class EventDetector {
	
	public static double TOL = 1e-4;
	
	/**
	 * @param t
	 * @param localPopulation
	 * @return A event detection function evaluation, where the return value is composed {value, delta_value}. An event
	 *         occurs when value = 0.0+-TOL. delta_value indicates the direction of crossing. May or may be not the
	 *         actual derivative of value.
	 */
	public abstract double[] evaluate(double t, DeltaPopulation localPopulation);
	
	public boolean detectEvent(double t, DeltaPopulation localPopulation, int direction) {
		double[] r = evaluate(t, localPopulation);
		double value = r[0];
		double dvalue = r[1];
		if(-TOL < value && value < TOL) {
//			System.out.println("EventDetector.detectEvent: ["+value+"\t"+dvalue+"]");
			if(direction==0){
				return true;
			}
			if(direction>0 && dvalue>0){
				return true;
			}
			if(direction<0 && dvalue<0){
				return true;
			}
		}
		return false;
	}
	
}