package test.object;

import java.util.Enumeration;
import java.util.Vector;

import jml.math.realvector.RealVectorGenerator;
import jml.object.LabeledObject;
import jml.object.ObjectGenerator;
import jml.object.sources.BufferSource;
import jml.object.sources.FileSource;
import jml.object.sources.ObjectSource;
import jml.object.sources.PartitionSource;
import jml.object.sources.SamplingSource;
import jml.object.transformation.RealVectorToLabeledObject;
import jml.object.transformation.TransformedObjectGenerator;
import jml.parser.FileTokenizer;
import jml.parser.NumberDFA;
import jml.parser.TokenDFA;
import jml.parser.Tokenizer;
import jml.parser.WhiteSpaceDFA;
import jml.random.Partition;
import jml.random.UniformNumberGenerator;
import jml.util.sort.MergeSort;


/**
 * <p>Title: ExampleDataSource</p>
 * <p>Description: A program using the data sources</p>
 * <p>Copyright:    Copyright (c) 2002</p>
 * <p>Company: Universidad Nacional de Colombia - The University of Memphis</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public class ExampleDataSource {

  /*public static void print(Object obj) {
    if(obj instanceof LabeledObject) {
      LabeledObject lobj = (LabeledObject)obj;
      System.out.print(" " + lobj.getLabel() + ":");
      obj = lobj.get();
    }
    double[] x = (double[])obj;
    for (int i = 0; i < x.length; i++) {
      System.out.print(" " + x[i]);
    }
    System.out.println();
  }*/

  /**
   * A program using the DataSources
   * @param argv Folder containing the data file (config file and data samples files)
   * The config file is config.txt and the data file is data.txt. You can use
   * the files data.txt and config.txt stored in folder /files/wine (for the wine data set).
   */
  public static void main( String[] argv ){
   
     
     
      ObjectSource source = null;
      TokenDFA[] dfa = new TokenDFA[2];
      dfa[0] = new NumberDFA(true);
      dfa[1] = new WhiteSpaceDFA();
      Tokenizer tok =  new Tokenizer( dfa );
      ObjectGenerator gen = new RealVectorGenerator();
      //source = new FileSource( fileName, gen, tok, 100000 );
     
     Vector vec=new Vector();
      FileTokenizer fileToken=new FileTokenizer("C:/b.txt");
  	String numero;
  	int cont=0;
  	while((numero=fileToken.getLine())!=null){
  	   vec.add(numero);
  	}
  	
  	source= new BufferSource(vec);
  	
      System.out.println("**** Original Data Source ****");
      Enumeration iter = source.elements();
      while( iter.hasMoreElements() ){
        System.out.println(iter.nextElement());
      }

     // A random sample of the data set
      UniformNumberGenerator g = new UniformNumberGenerator( source.size() );
      System.out.println("**** Index to use ****" );
      int[] index = g.getArrayInteger(10);
      for( int i=0; i<index.length; i++ ){
        System.out.print( " " + index[i] );
      }
      System.out.println();

      System.out.println("**** Sorted index ****" );
      MergeSort sort = new MergeSort();
      sort.low2high(index);
      for( int i=0; i<index.length; i++ ){
        System.out.print( " " + index[i] );
      }
      System.out.println();
      SamplingSource sample = new SamplingSource( source, index );

      System.out.println("**** Samples Data Source ****");
      iter = sample.elements();
      while( iter.hasMoreElements() ){
        System.out.println(iter.nextElement());
      }
      source.close();

      System.out.println("***Generating a partition of 10 groups in the data set ***");
      Partition p = new Partition( source.size(), 10, true );
      for( int i=0; i<10; i++ ){
        System.out.println("[Group "+i+"]");
        int[] group = p.getGroup(i);
        for( int k=0; k<group.length; k++ ){
          System.out.print(" "+group[k]);
        }
        System.out.println();
      }

      System.out.println("***Generating a partition data source using group 0***");
      PartitionSource pdata = new PartitionSource( source, p, 0, true );
      System.out.println("**** Samples of Group 0 ****");
      iter = pdata.elements();
      while( iter.hasMoreElements() ){
        iter.nextElement();
      }

      System.out.println("**** Data samples do not belong to Group 0 ****");
      pdata.init(0,false);
      iter = pdata.elements();
      while( iter.hasMoreElements() ){
        iter.nextElement();
      }

      System.out.println("**** Labeled Objects ****");

      RealVectorGenerator or = new RealVectorGenerator();
      RealVectorToLabeledObject tr = new RealVectorToLabeledObject(-1);
      ObjectGenerator generator = new TransformedObjectGenerator( or, tr );
      source = new FileSource("C:/b.txt", generator, tok , 100000);
      iter = source.elements();
      while( iter.hasMoreElements() ){
        iter.nextElement();
      }

      source.close();
   
  }
}
