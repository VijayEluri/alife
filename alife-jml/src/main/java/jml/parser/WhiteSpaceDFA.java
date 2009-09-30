package jml.parser;
/**
 * Title: WhiteSpaceDFA
 * Description: Fills transition matrix with ascii code that
 * represents tab and space
 * Copyright:    Copyright (c) 2004
 * Company: Universidad Nacional de Colombia
 * @author Jonatan Gomez
 * @version 1.0
 */
public class WhiteSpaceDFA extends TokenDFA {
  /**
   * Public constructor
   * Tab and Space (9, 32)
   */
	public WhiteSpaceDFA() {
    int nStates = 1;
    transitionTable = new int[nStates][ASCII_CODE];
    output = new int[nStates];
    output[0] = ACEPTED;
    for (int j = 0; j < ASCII_CODE; j++) {
      transitionTable[0][j] = STOP_ACEPTED;
    }
    // white_space and tab
    transitionTable[0][32] = 0;
    transitionTable[0][9] = 0;
  }

	/**
	 * Gets string white space token 
	 * @param str The word assigned to the word token
	 * @return A Token
	 */
  public Token getToken(String str) {
    return new Token(Token.ttWHITESPACE, str);
  }

}
