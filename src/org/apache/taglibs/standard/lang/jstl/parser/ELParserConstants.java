/* Generated By:JavaCC: Do not edit this line. ELParserConstants.java */
package org.apache.taglibs.standard.lang.jstl.parser;

public interface ELParserConstants {

  int EOF = 0;
  int NON_EXPRESSION_TEXT = 1;
  int START_EXPRESSION = 2;
  int INTEGER_LITERAL = 7;
  int FLOATING_POINT_LITERAL = 8;
  int EXPONENT = 9;
  int STRING_LITERAL = 10;
  int BADLY_ESCAPED_STRING_LITERAL = 11;
  int TRUE = 12;
  int FALSE = 13;
  int NULL = 14;
  int END_EXPRESSION = 15;
  int DOT = 16;
  int GT1 = 17;
  int GT2 = 18;
  int LT1 = 19;
  int LT2 = 20;
  int EQ1 = 21;
  int EQ2 = 22;
  int LE1 = 23;
  int LE2 = 24;
  int GE1 = 25;
  int GE2 = 26;
  int NE1 = 27;
  int NE2 = 28;
  int LPAREN = 29;
  int RPAREN = 30;
  int LBRACKET = 31;
  int RBRACKET = 32;
  int PLUS = 33;
  int MINUS = 34;
  int MULTIPLY = 35;
  int DIVIDE1 = 36;
  int DIVIDE2 = 37;
  int MODULUS1 = 38;
  int MODULUS2 = 39;
  int NOT1 = 40;
  int NOT2 = 41;
  int AND1 = 42;
  int AND2 = 43;
  int OR1 = 44;
  int OR2 = 45;
  int EMPTY = 46;
  int IDENTIFIER = 47;
  int IMPL_OBJ_START = 48;
  int LETTER = 49;
  int DIGIT = 50;
  int ILLEGAL_CHARACTER = 51;

  int DEFAULT = 0;
  int IN_EXPRESSION = 1;

  String[] tokenImage = {
    "<EOF>",
    "<NON_EXPRESSION_TEXT>",
    "\"${\"",
    "\" \"",
    "\"\\t\"",
    "\"\\n\"",
    "\"\\r\"",
    "<INTEGER_LITERAL>",
    "<FLOATING_POINT_LITERAL>",
    "<EXPONENT>",
    "<STRING_LITERAL>",
    "<BADLY_ESCAPED_STRING_LITERAL>",
    "\"true\"",
    "\"false\"",
    "\"null\"",
    "\"}\"",
    "\".\"",
    "\">\"",
    "\"gt\"",
    "\"<\"",
    "\"lt\"",
    "\"==\"",
    "\"eq\"",
    "\"<=\"",
    "\"le\"",
    "\">=\"",
    "\"ge\"",
    "\"!=\"",
    "\"ne\"",
    "\"(\"",
    "\")\"",
    "\"[\"",
    "\"]\"",
    "\"+\"",
    "\"-\"",
    "\"*\"",
    "\"/\"",
    "\"div\"",
    "\"%\"",
    "\"mod\"",
    "\"not\"",
    "\"!\"",
    "\"and\"",
    "\"&&\"",
    "\"or\"",
    "\"||\"",
    "\"empty\"",
    "<IDENTIFIER>",
    "\"#\"",
    "<LETTER>",
    "<DIGIT>",
    "<ILLEGAL_CHARACTER>",
  };

}
