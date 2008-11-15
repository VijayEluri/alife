package test.evolution.dfa;
import jml.evolution.Environment;
import jml.evolution.binary.operators.AddGen;
import jml.structures.BitArray;
/**
 * <p>Title: AddState</p>
 * <p>Description: Add a state to the DFA.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class AddState extends AddGen{

  public AddState( Environment _environment ){
    super( _environment );
  }
  /**
   * Flips a bit in the given genome
   * @param genome Genome to be modified
   * @return Index of the flipped bit
   */
  public Object apply(BitArray genome) {
//    GenomeLimits limits = ((BinaryGenotype)environment.getGenotype()).getLimits();
/*
    int count = 0;
    DFA dfa = new DFA( genome, true );
    int size = dfa.output.length;
    Object x = super.apply(genome);
    int start = 0;
    UniformNumberGenerator g = new UniformNumberGenerator(size);
    int pos = g.newInt();
    dfa.transition_table[0][pos] = size;
    pos = g.newInt();
    dfa.transition_table[1][pos] = size;
    for( int i=0; i<size; i++ ){
      genome.setInt(start,dfa.transition_table[0][i]);
      start++;
      genome.setInt(start,dfa.transition_table[1][i]);
      start++;
    }
    return x;
 */
    return null;
  }


}
