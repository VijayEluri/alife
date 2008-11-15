package test.evolution.dfa;
import jml.evolution.Environment;
import jml.evolution.GenomeLimits;
import jml.evolution.Phenotype;
import jml.util.IntUtil;

public class DFAEnvironment extends Environment{
  protected boolean smartLabeling = true;
  protected Sequence[] test_set = null;
  protected Sequence[] train_set = null;

  public DFAEnvironment(String trainFile, String testFile, boolean _smartLabeling){
    smartLabeling = _smartLabeling;
    DFA.BITS_STATE = IntUtil.INTEGER_SIZE;
    DFA.GENE_SIZE = 2*DFA.BITS_STATE;
    train_set = Sequence.readSequenceSet( trainFile, true );
    DFAFitness.PACKAGE_SIZE = train_set.length;
    test_set = Sequence.readSequenceSet( testFile, false );
    DFAFitness f = new DFAFitness(train_set, smartLabeling);
    f.prediction = new int[test_set.length];

    GenomeLimits limits;
    genotype = new DFAGenotype( DFA.MIN_NUMBER_STATES );
    phenotype = new Phenotype();
    fitness = f;

    link();

  }

  public int getCodingSize( int size ){
    if( smartLabeling ){
      return  size;
    }else{
      return 2 * size;
    }
  }
}
