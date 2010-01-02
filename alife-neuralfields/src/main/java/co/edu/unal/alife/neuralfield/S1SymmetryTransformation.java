package co.edu.unal.alife.neuralfield;

public class S1SymmetryTransformation {
	/**
	 * The value (angle) of rotation or reflection
	 */
	private double angle;
	/**
	 * Is rotation or reflection?
	 */
	private boolean isReflection;

	public S1SymmetryTransformation(double angle, boolean isReflection) {
		super();
		this.angle = angle;
		this.isReflection = isReflection;
	}
	
	public double transform(double inputAngle) {
		double outputAngle;
		if(!this.isReflection) {
			outputAngle = inputAngle + this.angle;
		} else {
			outputAngle = -((inputAngle + this.angle)%2*Math.PI);
		}
		return S1TopologyUtility.stdAngle(outputAngle);
	}

}
