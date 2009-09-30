/**
 * 
 */
package evolution.operators;

import util.MatrixOperator;
import evolution.RnnParameters;
import jml.evolution.Environment;
import jml.evolution.real.RealVectorLimits;
import jml.random.UniformNumberGenerator;

/**
 * @author Juan Figueredo
 *
 */
public class ParametricInputMutation extends ParametricMutation {

	/**
	 * @param _environment
	 * @param _limits
	 * @param _sigma
	 */
	public ParametricInputMutation(Environment _environment,
			RealVectorLimits _limits, double _sigma) {
		super(_environment, _limits, _sigma);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object apply(Object gen) {
		RnnParameters genome = (RnnParameters) gen;
		double[][] chromosome = genome.Wb;
	    double min = limits.min[0];
	    double max = limits.max[0];
	    try {
		/*for (int i = 0; i < chromosome.length; i++) {
			for (int j = 0; j < chromosome[0].length; j++) {
				double x = chromosome[i][j];
				g.setSigma(sigma);
				double y = g.newDouble();
				x += y;
				if (x < min) {
					x = min;
				} else {
					if (x > max) {
						x = max;
					}
				}
				chromosome[i][j] = x;
			}			
		}*/
	    	genome.Wb = MatrixOperator.random(genome.Wb.length, genome.Wb[0].length, 4, -2);
	    	genome.Wa = MatrixOperator.random(genome.Wa.length, genome.Wa[0].length, 4, -2);
	    } catch (Exception e) { System.err.println("[Parametric Input Mutation]" + e.getMessage()); }
	    //System.out.println("Parameter Input Mutation");
	    return null;
	}

	
}
