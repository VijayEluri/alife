package test.evolution.dfa;

//import jml.coral.*;
//import jml.cml.function.*;
import java.io.FileWriter;

import jml.evolution.Environment;
import jml.evolution.Operator;
import jml.evolution.Selection;
import jml.evolution.selections.Elitism;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Universidad Nacional de Colombia - The University of Memphis</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class DFATest {

  public static void savePrediction( String fileName, int[] prediction ){
    try{
      FileWriter file = new FileWriter( fileName );
      file.write(""+prediction.length+"\n");
      for( int i=0; i<prediction.length; i++ ){
        file.write(""+prediction[i]+"\n");
      }
      file.close();
    }catch( Exception e ){
      e.printStackTrace();
    }
  }


  public static Operator[] getOperators( int option, boolean smart ){
    Environment env = null;
    Operator mut1 = new SimpleDFAMutation(env, smart);
    Operator mut2 = new SimpleDFAMutation(env, smart, 0.1);
    Operator mut3 = new SimpleDFAMutation(env, smart, 0.3);
    Operator mut4 = new SpecialDFAMutation(env, smart, 0.1);
    Operator xover = new MultiIntegerXOver(env);
    Operator[] opers = null;
    switch( option ){
      case 0:
        opers = new Operator[1];
        opers[0] = mut1;
      break;
      case 1:
        opers = new Operator[1];
        opers[0] = mut2;
      break;
      case 2:
        opers = new Operator[1];
        opers[0] = mut3;
      break;
      case 3:
        opers = new Operator[2];
        opers[0] = mut1;
        opers[1] = mut2;
      break;
      case 4:
        opers = new Operator[2];
        opers[0] = mut1;
        opers[1] = mut3;
      break;
      case 5:
        opers = new Operator[2];
        opers[0] = mut2;
        opers[1] = mut3;
      break;
      case 6:
        opers = new Operator[3];
        opers[0] = mut1;
        opers[1] = mut2;
        opers[2] = mut3;
      break;
      case 7:
        opers = new Operator[2];
        opers[0] = mut1;
        opers[1] = xover;
      break;
      case 8:
        opers = new Operator[3];
        opers[0] = mut1;
        opers[1] = xover;
        opers[2] = mut3;
      break;
      case 9:
        opers = new Operator[3];
        opers[0] = mut1;
        opers[1] = mut3;
        opers[2] = mut4;
      break;
    }
    return opers;
  }

  public static Selection getSelection( Environment env ){
    return new Elitism( env, 1, false, 1.0, 0.0 );
  }


  public static void main( String[] argv ){
//    String path = "C:/jonatan/projects/java/jbproject/un/data/gecco-results/";
    String path = argv[0];
    String truth_file_name = path + argv[1];
    String train_file_name = path + argv[2];
    String test_file_name = path + argv[3];
    String out_file_name = path + argv[4];
    double error = Double.parseDouble(argv[5]);
    long MAX_ITER = Long.parseLong(argv[6]);
    long RUNNING_TIME = Long.parseLong(argv[7]);
    System.out.println( MAX_ITER );
    String algorithm = argv[8];
    int oper = Integer.parseInt(argv[9]);
    Operator[] operators;
    DFAEvolver evo = null;
    if( algorithm.equals("haea") ){
      int POP_SIZE = Integer.parseInt(argv[10]);
      boolean smart = argv[11].equals("true");
      operators = getOperators(oper, smart);
      boolean sort = argv[12].equals("true");
      boolean incremental = argv[13].equals("true");
      int PACKAGE_SIZE = 0;
      if( incremental ){ PACKAGE_SIZE = Integer.parseInt(argv[14]); }
      evo = new DFAHaea( train_file_name, test_file_name, smart, error,
                         operators, getSelection(null), POP_SIZE,
                         sort, incremental, PACKAGE_SIZE );
    }else{
      if( algorithm.equals("smart") ){
        operators = getOperators(oper,true);
        long MAX_DFA_ITER = Long.parseLong(argv[10]);
        evo = new DFASmartLabeling( train_file_name,
                                    test_file_name, error,
                                    operators,
                                    getSelection(null),
                                    MAX_DFA_ITER );
      }else{
        System.out.println( "Undefined evolutionary algorithm " + algorithm );
      }
    }
    evo.initThread(RUNNING_TIME);
    DFA a = evo.evolve(MAX_ITER);
    evo.saveStat(out_file_name, truth_file_name,a);
  }
}
