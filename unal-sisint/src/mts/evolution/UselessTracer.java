package mts.evolution;

import jml.basics.Algorithm;
import jml.util.SimpleConsoleTracer;

public class UselessTracer extends SimpleConsoleTracer {

	/* (non-Javadoc)
	 * @see jml.util.SimpleConsoleTracer#add(jml.basics.Algorithm, java.lang.Object)
	 */
	@Override
	public void add(Algorithm algorithm, Object obj) {
		//Nothing to do
	}

}
