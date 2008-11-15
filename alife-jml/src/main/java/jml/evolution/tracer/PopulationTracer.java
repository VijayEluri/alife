package jml.evolution.tracer;

import javax.swing.JFrame;

import jml.basics.Algorithm;
import jml.basics.Result;
import jml.basics.Tracer;

public class PopulationTracer implements Tracer {

		
	/**
	 * 
	 */
	String statistics[] = new String[8];
	
	/**
	 * 
	 */
	double cont;

  /**
   * Creates a console interface
   */
  public PopulationTracer() {
	  cont=1;
  }

  /**
   * Draws the results produced by the algorithm to the console
   * @param algorithm Algorithm being executed
   * @param obj Result Results produced by the algorithm (for example the
   * iteration result in an iterated algorithm
   */
  public void add(Algorithm algorithm, Object obj) {
	  String aux = obj.toString();
	  System.out.println(aux);
	  statistics = aux.split(" ");
	  
	  JFrame temp = FrameTracer.getInstance();
	  ((FrameTracer) temp).addData(statistics);
	  ((FrameTracer) temp).paintData(2);
	 
	  
	  
	  
  }
  
  /**
   * Return default value
   * @return default value
   */
  public Result getStat() { return null; }
}
