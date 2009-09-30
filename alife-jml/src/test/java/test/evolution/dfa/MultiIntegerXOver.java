
package test.evolution.dfa;

import jml.evolution.Environment;
import jml.evolution.Selection;
import jml.evolution.binary.VariableLengthBinaryGenotype;
import jml.evolution.operators.ArityTwo;
import jml.random.UniformNumberGenerator;
import jml.structures.BitArray;

/**
 * Title: XOver
 * Description: The simple point crossover operator (variable length)
 * Copyright: Copyright (c) 2002
 * Company: The University of Memphis - Universidad Nacional de Colombia
 * @author Jonatan Gomez
 * @version 1.0
 */

public class MultiIntegerXOver extends ArityTwo {

  public MultiIntegerXOver( Environment _environment){ super(_environment); }

  /**
   * Constructor: Create a crossover operator with the given selection parent strategy
   */
  public MultiIntegerXOver( Environment _environment, Selection _selection ){
    super( _environment, _selection );
  }

  /**
   * Apply the simple point crossover operation over the given genomes
   * @param c1 The first parent
   * @param c2 The second parent
   */
  public Object apply(  Object c1, Object c2 ){
    BitArray child1 = (BitArray)c1;
    BitArray child2 = (BitArray)c2;

    VariableLengthBinaryGenotype genotype = (VariableLengthBinaryGenotype)environment.getGenotype();
    int size = 2 * Math.min( child1.size(), child2.size() ) / genotype.getDeltaLength();
//    UniformNumberGenerator g = new UniformNumberGenerator(size);
//    int count = g.newInt();
//    int t = child2.getInt(count);
//    child2.setInt(count, child1.getInt(count));
//    child1.setInt(count, t);
    UniformNumberGenerator g = new UniformNumberGenerator();
    int count = 0;
    for(int i=0; i<size; i++ ){
      if( g.newDouble() < 0.5 ){
//        System.out.print(".");
        int t = child2.getInt(i);
        child2.setInt(i, child1.getInt(i));
        child1.setInt(i, t);
        count++;
      }
    }
    return new Integer(count);
  }
}
