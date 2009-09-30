/**
 * 
 */
package solver;

import java.util.Iterator;

/**
 * @author Juan Figueredo
 *
 */
public class DistanceTracer extends Tracer {

	/**
	 * @param length
	 */
	public DistanceTracer(int length) {
		super(length);
		// TODO Auto-generated constructor stub
	}
	
	public double distanceMeasure() {
		return avgDistance();
	}

	/**
	 * @return
	 */
	public double minDistance() {
		double minDistance = 100;
		for (Iterator iter = data.iterator(); iter.hasNext();) {
			double[] element = (double[]) iter.next();
			double deltaX = element[6] - element[0];
			double deltaY = element[7] - element[1];
			double distance = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
			minDistance = Math.min(minDistance, distance);
		}
		return minDistance;
	}
	
	public double maxDistance() {
		double maxDistance = 0;
		for (Iterator iter = data.iterator(); iter.hasNext();) {
			double[] element = (double[]) iter.next();
			double deltaX = element[6] - element[0];
			double deltaY = element[7] - element[1];
			double distance = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
			maxDistance = Math.max(maxDistance, distance);
		}
		return maxDistance;
	}
	
	public double avgDistance() {
		double sumDistance = 0;
		int numData = 0;
		for (Iterator iter = data.iterator(); iter.hasNext();) {
			double[] element = (double[]) iter.next();
			double deltaX = element[6] - element[0];
			double deltaY = element[7] - element[1];
			double distance = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
			sumDistance += distance;
			numData++;
		}
		return sumDistance/numData;
	}

}
