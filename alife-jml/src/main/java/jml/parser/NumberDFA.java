package jml.parser;


/**
 * Title: NumberDFA
 * Description: Fills transition matrix with ascii code that
 * represents numbers
 * Copyright:    Copyright (c) 2004
 * Company: Universidad Nacional de Colombia
 * @author Jonatan Gomez
 * @version 1.0
 */
public class NumberDFA extends TokenDFA {
  /**
   * Public Contructor, to fill transition table 
   * @param signed deteminates if position (0, 43) and (0, 45) fills with 1 or -1
   */
	public NumberDFA(boolean signed) {
    int nStates = 8;
    transitionTable = new int[nStates][ASCII_CODE];
    output = new int[nStates];
    for (int i = 0; i < 5; i++) {
      output[i] = REJECTED;
      for (int j = 0; j < ASCII_CODE; j++) {
        transitionTable[i][j] = STOP_REJECTED;
      }
    }
    // digit
    for (int j = 48; j < 58; j++) {
      transitionTable[0][j] = 5;
      transitionTable[1][j] = 5;
      transitionTable[2][j] = 6;
      transitionTable[3][j] = 7;
      transitionTable[4][j] = 7;
    }

    // +, -
    if (signed) {
      transitionTable[0][43] = 1;
      transitionTable[0][45] = 1;
    }

    transitionTable[3][43] = 4;
    transitionTable[3][45] = 4;

    for (int i = 5; i < nStates; i++) {
      output[i] = ACEPTED;
      for (int j = 0; j < ASCII_CODE; j++) {
        transitionTable[i][j] = STOP_ACEPTED;
      }
      // digit
      for (int j = 48; j < 58; j++) {
        transitionTable[i][j] = i;
      }
    }

    // E, e
    transitionTable[5][69] = 3;
    transitionTable[5][101] = 3;
    transitionTable[6][69] = 3;
    transitionTable[6][101] = 3;

    // .
    transitionTable[0][46] = 2;
    transitionTable[1][46] = 2;
    transitionTable[5][46] = 6;
    transitionTable[6][46] = STOP_REJECTED;
    transitionTable[7][46] = STOP_REJECTED;
  }

	/**
	 * Creates a token with double parameter
	 * @param str A String that represents a double  
	 * @return The token
	 */
  public Token getToken(String str) {
    try {
      return new Token(Double.parseDouble(str));
    } catch (Exception e) {
      // e.printStackTrace();
    }
    return null;
  }
}
