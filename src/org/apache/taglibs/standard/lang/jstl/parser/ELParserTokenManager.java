/* Generated By:JavaCC: Do not edit this line. ELParserTokenManager.java */
package org.apache.taglibs.standard.lang.jstl.parser;
import org.apache.taglibs.standard.lang.jstl.*;
import java.util.ArrayList;
import java.util.List;

public class ELParserTokenManager implements ELParserConstants
{
  public  java.io.PrintStream debugStream = System.out;
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_0(int pos, long active0)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0x4L) != 0L)
            return 2;
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_0(int pos, long active0)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
private final int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private final int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
private final int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 36:
         return jjMoveStringLiteralDfa1_0(0x4L);
      default :
         return jjMoveNfa_0(3, 0);
   }
}
private final int jjMoveStringLiteralDfa1_0(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 123:
         if ((active0 & 0x4L) != 0L)
            return jjStopAtPos(1, 2);
         break;
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
private final void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private final void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private final void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}
private final void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}
private final void jjCheckNAddStates(int start)
{
   jjCheckNAdd(jjnextStates[start]);
   jjCheckNAdd(jjnextStates[start + 1]);
}
static final long[] jjbitVec0 = {
   0xfffffffffffffffeL, 0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffffffffffffffL
};
static final long[] jjbitVec2 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
private final int jjMoveNfa_0(int startState, int curPos)
{
   int[] nextStates;
   int startsAt = 0;
   jjnewStateCnt = 3;
   int i = 1;
   jjstateSet[0] = startState;
   int j, kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         MatchLoop: do
         {
            switch(jjstateSet[--i])
            {
               case 3:
                  if ((0xffffffefffffffffL & l) != 0L)
                  {
                     if (kind > 1)
                        kind = 1;
                     jjCheckNAddTwoStates(0, 1);
                  }
                  else if (curChar == 36)
                     jjstateSet[jjnewStateCnt++] = 2;
                  break;
               case 0:
                  if ((0xffffffefffffffffL & l) == 0L)
                     break;
                  if (kind > 1)
                     kind = 1;
                  jjCheckNAddTwoStates(0, 1);
                  break;
               case 1:
                  if (curChar == 36)
                     jjstateSet[jjnewStateCnt++] = 2;
                  break;
               case 2:
                  if (kind > 1)
                     kind = 1;
                  jjCheckNAddTwoStates(0, 1);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         MatchLoop: do
         {
            switch(jjstateSet[--i])
            {
               case 3:
               case 0:
                  if (kind > 1)
                     kind = 1;
                  jjCheckNAddTwoStates(0, 1);
                  break;
               case 2:
                  if ((0xf7ffffffffffffffL & l) == 0L)
                     break;
                  if (kind > 1)
                     kind = 1;
                  jjCheckNAddTwoStates(0, 1);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (int)(curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         MatchLoop: do
         {
            switch(jjstateSet[--i])
            {
               case 3:
               case 0:
               case 2:
                  if (!jjCanMove_0(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 1)
                     kind = 1;
                  jjCheckNAddTwoStates(0, 1);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 3 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private final int jjStopStringLiteralDfa_1(int pos, long active0)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0x10000L) != 0L)
            return 1;
         if ((active0 & 0x3ff00003800L) != 0L)
         {
            jjmatchedKind = 42;
            return 30;
         }
         return -1;
      case 1:
         if ((active0 & 0x3fb00003800L) != 0L)
         {
            jjmatchedKind = 42;
            jjmatchedPos = 1;
            return 30;
         }
         if ((active0 & 0x400000000L) != 0L)
            return 30;
         return -1;
      case 2:
         if ((active0 & 0x3f800003800L) != 0L)
         {
            jjmatchedKind = 42;
            jjmatchedPos = 2;
            return 30;
         }
         if ((active0 & 0x300000000L) != 0L)
            return 30;
         return -1;
      case 3:
         if ((active0 & 0x3e000001000L) != 0L)
         {
            if (jjmatchedPos != 3)
            {
               jjmatchedKind = 42;
               jjmatchedPos = 3;
            }
            return 30;
         }
         if ((active0 & 0x1800002800L) != 0L)
            return 30;
         return -1;
      case 4:
         if ((active0 & 0x30000001000L) != 0L)
            return 30;
         if ((active0 & 0xe800000000L) != 0L)
         {
            if (jjmatchedPos != 4)
            {
               jjmatchedKind = 42;
               jjmatchedPos = 4;
            }
            return 30;
         }
         return -1;
      case 5:
         if ((active0 & 0xe800000000L) != 0L)
         {
            jjmatchedKind = 42;
            jjmatchedPos = 5;
            return 30;
         }
         if ((active0 & 0x20000000000L) != 0L)
            return 30;
         return -1;
      case 6:
         if ((active0 & 0x8800000000L) != 0L)
         {
            jjmatchedKind = 42;
            jjmatchedPos = 6;
            return 30;
         }
         if ((active0 & 0x6000000000L) != 0L)
            return 30;
         return -1;
      case 7:
         if ((active0 & 0x8800000000L) != 0L)
         {
            jjmatchedKind = 42;
            jjmatchedPos = 7;
            return 30;
         }
         return -1;
      case 8:
         if ((active0 & 0x8800000000L) != 0L)
         {
            jjmatchedKind = 42;
            jjmatchedPos = 8;
            return 30;
         }
         return -1;
      case 9:
         if ((active0 & 0x8800000000L) != 0L)
         {
            jjmatchedKind = 42;
            jjmatchedPos = 9;
            return 30;
         }
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_1(int pos, long active0)
{
   return jjMoveNfa_1(jjStopStringLiteralDfa_1(pos, active0), pos + 1);
}
private final int jjStartNfaWithStates_1(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_1(state, pos + 1);
}
private final int jjMoveStringLiteralDfa0_1()
{
   switch(curChar)
   {
      case 33:
         return jjMoveStringLiteralDfa1_1(0x400000L);
      case 40:
         return jjStopAtPos(0, 23);
      case 41:
         return jjStopAtPos(0, 24);
      case 42:
         return jjStopAtPos(0, 29);
      case 43:
         return jjStopAtPos(0, 27);
      case 45:
         return jjStopAtPos(0, 28);
      case 46:
         return jjStartNfaWithStates_1(0, 16, 1);
      case 58:
         return jjStopAtPos(0, 15);
      case 61:
         return jjMoveStringLiteralDfa1_1(0x80000L);
      case 91:
         return jjStopAtPos(0, 25);
      case 93:
         return jjStopAtPos(0, 26);
      case 97:
         return jjMoveStringLiteralDfa1_1(0x8200000000L);
      case 102:
         return jjMoveStringLiteralDfa1_1(0x1000L);
      case 110:
         return jjMoveStringLiteralDfa1_1(0x100002000L);
      case 111:
         return jjMoveStringLiteralDfa1_1(0x400000000L);
      case 112:
         return jjMoveStringLiteralDfa1_1(0x31800000000L);
      case 114:
         return jjMoveStringLiteralDfa1_1(0x2000000000L);
      case 115:
         return jjMoveStringLiteralDfa1_1(0x4000000000L);
      case 116:
         return jjMoveStringLiteralDfa1_1(0x800L);
      case 125:
         return jjStopAtPos(0, 14);
      default :
         return jjMoveNfa_1(0, 0);
   }
}
private final int jjMoveStringLiteralDfa1_1(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_1(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 61:
         if ((active0 & 0x80000L) != 0L)
            return jjStopAtPos(1, 19);
         else if ((active0 & 0x400000L) != 0L)
            return jjStopAtPos(1, 22);
         break;
      case 97:
         return jjMoveStringLiteralDfa2_1(active0, 0x31800001000L);
      case 101:
         return jjMoveStringLiteralDfa2_1(active0, 0x6000000000L);
      case 110:
         return jjMoveStringLiteralDfa2_1(active0, 0x200000000L);
      case 111:
         return jjMoveStringLiteralDfa2_1(active0, 0x100000000L);
      case 112:
         return jjMoveStringLiteralDfa2_1(active0, 0x8000000000L);
      case 114:
         if ((active0 & 0x400000000L) != 0L)
            return jjStartNfaWithStates_1(1, 34, 30);
         return jjMoveStringLiteralDfa2_1(active0, 0x800L);
      case 117:
         return jjMoveStringLiteralDfa2_1(active0, 0x2000L);
      default :
         break;
   }
   return jjStartNfa_1(0, active0);
}
private final int jjMoveStringLiteralDfa2_1(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_1(0, old0); 
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_1(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 100:
         if ((active0 & 0x200000000L) != 0L)
            return jjStartNfaWithStates_1(2, 33, 30);
         break;
      case 103:
         return jjMoveStringLiteralDfa3_1(active0, 0x1800000000L);
      case 108:
         return jjMoveStringLiteralDfa3_1(active0, 0x3000L);
      case 112:
         return jjMoveStringLiteralDfa3_1(active0, 0x8000000000L);
      case 113:
         return jjMoveStringLiteralDfa3_1(active0, 0x2000000000L);
      case 114:
         return jjMoveStringLiteralDfa3_1(active0, 0x30000000000L);
      case 115:
         return jjMoveStringLiteralDfa3_1(active0, 0x4000000000L);
      case 116:
         if ((active0 & 0x100000000L) != 0L)
            return jjStartNfaWithStates_1(2, 32, 30);
         break;
      case 117:
         return jjMoveStringLiteralDfa3_1(active0, 0x800L);
      default :
         break;
   }
   return jjStartNfa_1(1, active0);
}
private final int jjMoveStringLiteralDfa3_1(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_1(1, old0); 
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_1(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa4_1(active0, 0x30000000000L);
      case 101:
         if ((active0 & 0x800L) != 0L)
            return jjStartNfaWithStates_1(3, 11, 30);
         else if ((active0 & 0x1000000000L) != 0L)
         {
            jjmatchedKind = 36;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_1(active0, 0x800000000L);
      case 108:
         if ((active0 & 0x2000L) != 0L)
            return jjStartNfaWithStates_1(3, 13, 30);
         return jjMoveStringLiteralDfa4_1(active0, 0x8000000000L);
      case 115:
         return jjMoveStringLiteralDfa4_1(active0, 0x4000001000L);
      case 117:
         return jjMoveStringLiteralDfa4_1(active0, 0x2000000000L);
      default :
         break;
   }
   return jjStartNfa_1(2, active0);
}
private final int jjMoveStringLiteralDfa4_1(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_1(2, old0); 
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_1(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 67:
         return jjMoveStringLiteralDfa5_1(active0, 0x800000000L);
      case 101:
         if ((active0 & 0x1000L) != 0L)
            return jjStartNfaWithStates_1(4, 12, 30);
         return jjMoveStringLiteralDfa5_1(active0, 0x2000000000L);
      case 105:
         return jjMoveStringLiteralDfa5_1(active0, 0xc000000000L);
      case 109:
         if ((active0 & 0x10000000000L) != 0L)
         {
            jjmatchedKind = 40;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_1(active0, 0x20000000000L);
      default :
         break;
   }
   return jjStartNfa_1(3, active0);
}
private final int jjMoveStringLiteralDfa5_1(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_1(3, old0); 
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_1(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case 99:
         return jjMoveStringLiteralDfa6_1(active0, 0x8000000000L);
      case 111:
         return jjMoveStringLiteralDfa6_1(active0, 0x4800000000L);
      case 115:
         if ((active0 & 0x20000000000L) != 0L)
            return jjStartNfaWithStates_1(5, 41, 30);
         return jjMoveStringLiteralDfa6_1(active0, 0x2000000000L);
      default :
         break;
   }
   return jjStartNfa_1(4, active0);
}
private final int jjMoveStringLiteralDfa6_1(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_1(4, old0); 
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_1(5, active0);
      return 6;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa7_1(active0, 0x8000000000L);
      case 110:
         if ((active0 & 0x4000000000L) != 0L)
            return jjStartNfaWithStates_1(6, 38, 30);
         return jjMoveStringLiteralDfa7_1(active0, 0x800000000L);
      case 116:
         if ((active0 & 0x2000000000L) != 0L)
            return jjStartNfaWithStates_1(6, 37, 30);
         break;
      default :
         break;
   }
   return jjStartNfa_1(5, active0);
}
private final int jjMoveStringLiteralDfa7_1(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_1(5, old0); 
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_1(6, active0);
      return 7;
   }
   switch(curChar)
   {
      case 116:
         return jjMoveStringLiteralDfa8_1(active0, 0x8800000000L);
      default :
         break;
   }
   return jjStartNfa_1(6, active0);
}
private final int jjMoveStringLiteralDfa8_1(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_1(6, old0); 
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_1(7, active0);
      return 8;
   }
   switch(curChar)
   {
      case 101:
         return jjMoveStringLiteralDfa9_1(active0, 0x800000000L);
      case 105:
         return jjMoveStringLiteralDfa9_1(active0, 0x8000000000L);
      default :
         break;
   }
   return jjStartNfa_1(7, active0);
}
private final int jjMoveStringLiteralDfa9_1(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_1(7, old0); 
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_1(8, active0);
      return 9;
   }
   switch(curChar)
   {
      case 111:
         return jjMoveStringLiteralDfa10_1(active0, 0x8000000000L);
      case 120:
         return jjMoveStringLiteralDfa10_1(active0, 0x800000000L);
      default :
         break;
   }
   return jjStartNfa_1(8, active0);
}
private final int jjMoveStringLiteralDfa10_1(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_1(8, old0); 
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_1(9, active0);
      return 10;
   }
   switch(curChar)
   {
      case 110:
         if ((active0 & 0x8000000000L) != 0L)
            return jjStartNfaWithStates_1(10, 39, 30);
         break;
      case 116:
         if ((active0 & 0x800000000L) != 0L)
            return jjStartNfaWithStates_1(10, 35, 30);
         break;
      default :
         break;
   }
   return jjStartNfa_1(9, active0);
}
static final long[] jjbitVec3 = {
   0x1ff00000fffffffeL, 0xffffffffffffc000L, 0xffffffffL, 0x600000000000000L
};
static final long[] jjbitVec4 = {
   0x0L, 0x0L, 0x0L, 0xff7fffffff7fffffL
};
static final long[] jjbitVec5 = {
   0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffffffffffffffL
};
static final long[] jjbitVec6 = {
   0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffL, 0x0L
};
static final long[] jjbitVec7 = {
   0xffffffffffffffffL, 0xffffffffffffffffL, 0x0L, 0x0L
};
static final long[] jjbitVec8 = {
   0x3fffffffffffL, 0x0L, 0x0L, 0x0L
};
private final int jjMoveNfa_1(int startState, int curPos)
{
   int[] nextStates;
   int startsAt = 0;
   jjnewStateCnt = 49;
   int i = 1;
   jjstateSet[0] = startState;
   int j, kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         MatchLoop: do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 7)
                        kind = 7;
                     jjCheckNAddStates(0, 4);
                  }
                  else if ((0x1800000000L & l) != 0L)
                  {
                     if (kind > 42)
                        kind = 42;
                     jjCheckNAdd(30);
                  }
                  else if (curChar == 37)
                  {
                     if (kind > 31)
                        kind = 31;
                  }
                  else if (curChar == 47)
                  {
                     if (kind > 30)
                        kind = 30;
                  }
                  else if (curChar == 62)
                     jjstateSet[jjnewStateCnt++] = 19;
                  else if (curChar == 60)
                     jjstateSet[jjnewStateCnt++] = 17;
                  else if (curChar == 39)
                     jjCheckNAddStates(5, 7);
                  else if (curChar == 34)
                     jjCheckNAddStates(8, 10);
                  else if (curChar == 46)
                     jjCheckNAdd(1);
                  if (curChar == 60)
                  {
                     if (kind > 18)
                        kind = 18;
                  }
                  else if (curChar == 62)
                  {
                     if (kind > 17)
                        kind = 17;
                  }
                  break;
               case 1:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 8)
                     kind = 8;
                  jjCheckNAddTwoStates(1, 2);
                  break;
               case 3:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(4);
                  break;
               case 4:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 8)
                     kind = 8;
                  jjCheckNAdd(4);
                  break;
               case 5:
               case 8:
                  if (curChar == 34)
                     jjCheckNAddStates(8, 10);
                  break;
               case 6:
                  if ((0xfffffffbffffffffL & l) != 0L)
                     jjCheckNAddStates(8, 10);
                  break;
               case 9:
                  if (curChar == 34 && kind > 10)
                     kind = 10;
                  break;
               case 10:
               case 13:
                  if (curChar == 39)
                     jjCheckNAddStates(5, 7);
                  break;
               case 11:
                  if ((0xffffff7fffffffffL & l) != 0L)
                     jjCheckNAddStates(5, 7);
                  break;
               case 14:
                  if (curChar == 39 && kind > 10)
                     kind = 10;
                  break;
               case 15:
                  if (curChar == 62 && kind > 17)
                     kind = 17;
                  break;
               case 16:
                  if (curChar == 60 && kind > 18)
                     kind = 18;
                  break;
               case 17:
                  if (curChar == 61 && kind > 20)
                     kind = 20;
                  break;
               case 18:
                  if (curChar == 60)
                     jjstateSet[jjnewStateCnt++] = 17;
                  break;
               case 19:
                  if (curChar == 61 && kind > 21)
                     kind = 21;
                  break;
               case 20:
                  if (curChar == 62)
                     jjstateSet[jjnewStateCnt++] = 19;
                  break;
               case 21:
                  if (curChar == 47 && kind > 30)
                     kind = 30;
                  break;
               case 25:
                  if (curChar == 37 && kind > 31)
                     kind = 31;
                  break;
               case 29:
                  if ((0x1800000000L & l) == 0L)
                     break;
                  if (kind > 42)
                     kind = 42;
                  jjCheckNAdd(30);
                  break;
               case 30:
                  if ((0x3ff001000000000L & l) == 0L)
                     break;
                  if (kind > 42)
                     kind = 42;
                  jjCheckNAdd(30);
                  break;
               case 31:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 7)
                     kind = 7;
                  jjCheckNAddStates(0, 4);
                  break;
               case 32:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 7)
                     kind = 7;
                  jjCheckNAdd(32);
                  break;
               case 33:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(33, 34);
                  break;
               case 34:
                  if (curChar != 46)
                     break;
                  if (kind > 8)
                     kind = 8;
                  jjCheckNAddTwoStates(35, 36);
                  break;
               case 35:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 8)
                     kind = 8;
                  jjCheckNAddTwoStates(35, 36);
                  break;
               case 37:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(38);
                  break;
               case 38:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 8)
                     kind = 8;
                  jjCheckNAdd(38);
                  break;
               case 39:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(39, 40);
                  break;
               case 41:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(42);
                  break;
               case 42:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 8)
                     kind = 8;
                  jjCheckNAdd(42);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         MatchLoop: do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 42)
                        kind = 42;
                     jjCheckNAdd(30);
                  }
                  if (curChar == 108)
                     jjAddStates(11, 12);
                  else if (curChar == 103)
                     jjAddStates(13, 14);
                  else if (curChar == 109)
                     jjstateSet[jjnewStateCnt++] = 27;
                  else if (curChar == 100)
                     jjstateSet[jjnewStateCnt++] = 23;
                  break;
               case 2:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStates(15, 16);
                  break;
               case 6:
                  if ((0xffffffffefffffffL & l) != 0L)
                     jjCheckNAddStates(8, 10);
                  break;
               case 7:
                  if (curChar == 92)
                     jjstateSet[jjnewStateCnt++] = 8;
                  break;
               case 8:
                  if (curChar == 92)
                     jjCheckNAddStates(8, 10);
                  break;
               case 11:
                  if ((0xffffffffefffffffL & l) != 0L)
                     jjCheckNAddStates(5, 7);
                  break;
               case 12:
                  if (curChar == 92)
                     jjstateSet[jjnewStateCnt++] = 13;
                  break;
               case 13:
                  if (curChar == 92)
                     jjCheckNAddStates(5, 7);
                  break;
               case 22:
                  if (curChar == 118 && kind > 30)
                     kind = 30;
                  break;
               case 23:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 22;
                  break;
               case 24:
                  if (curChar == 100)
                     jjstateSet[jjnewStateCnt++] = 23;
                  break;
               case 26:
                  if (curChar == 100 && kind > 31)
                     kind = 31;
                  break;
               case 27:
                  if (curChar == 111)
                     jjstateSet[jjnewStateCnt++] = 26;
                  break;
               case 28:
                  if (curChar == 109)
                     jjstateSet[jjnewStateCnt++] = 27;
                  break;
               case 29:
               case 30:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 42)
                     kind = 42;
                  jjCheckNAdd(30);
                  break;
               case 36:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStates(17, 18);
                  break;
               case 40:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStates(19, 20);
                  break;
               case 43:
                  if (curChar == 103)
                     jjAddStates(13, 14);
                  break;
               case 44:
                  if (curChar == 116 && kind > 17)
                     kind = 17;
                  break;
               case 45:
                  if (curChar == 101 && kind > 21)
                     kind = 21;
                  break;
               case 46:
                  if (curChar == 108)
                     jjAddStates(11, 12);
                  break;
               case 47:
                  if (curChar == 116 && kind > 18)
                     kind = 18;
                  break;
               case 48:
                  if (curChar == 101 && kind > 20)
                     kind = 20;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (int)(curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         MatchLoop: do
         {
            switch(jjstateSet[--i])
            {
               case 0:
               case 30:
                  if (!jjCanMove_1(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 42)
                     kind = 42;
                  jjCheckNAdd(30);
                  break;
               case 6:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjAddStates(8, 10);
                  break;
               case 11:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjAddStates(5, 7);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 49 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
   32, 33, 34, 39, 40, 11, 12, 14, 6, 7, 9, 47, 48, 44, 45, 3, 
   4, 37, 38, 41, 42, 
};
private static final boolean jjCanMove_0(int hiByte, int i1, int i2, long l1, long l2)
{
   switch(hiByte)
   {
      case 0:
         return ((jjbitVec2[i2] & l2) != 0L);
      default : 
         if ((jjbitVec0[i1] & l1) != 0L)
            return true;
         return false;
   }
}
private static final boolean jjCanMove_1(int hiByte, int i1, int i2, long l1, long l2)
{
   switch(hiByte)
   {
      case 0:
         return ((jjbitVec4[i2] & l2) != 0L);
      case 48:
         return ((jjbitVec5[i2] & l2) != 0L);
      case 49:
         return ((jjbitVec6[i2] & l2) != 0L);
      case 51:
         return ((jjbitVec7[i2] & l2) != 0L);
      case 61:
         return ((jjbitVec8[i2] & l2) != 0L);
      default : 
         if ((jjbitVec3[i1] & l1) != 0L)
            return true;
         return false;
   }
}
public static final String[] jjstrLiteralImages = {
"", null, "\44\173", null, null, null, null, null, null, null, null, 
"\164\162\165\145", "\146\141\154\163\145", "\156\165\154\154", "\175", "\72", "\56", null, null, 
"\75\75", null, null, "\41\75", "\50", "\51", "\133", "\135", "\53", "\55", "\52", null, 
null, "\156\157\164", "\141\156\144", "\157\162", 
"\160\141\147\145\103\157\156\164\145\170\164", "\160\141\147\145", "\162\145\161\165\145\163\164", 
"\163\145\163\163\151\157\156", "\141\160\160\154\151\143\141\164\151\157\156", "\160\141\162\141\155", 
"\160\141\162\141\155\163", null, null, null, null, };
public static final String[] lexStateNames = {
   "DEFAULT", 
   "IN_EXPRESSION", 
};
public static final int[] jjnewLexState = {
   -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
};
static final long[] jjtoToken = {
   0x7fffffffd87L, 
};
static final long[] jjtoSkip = {
   0x78L, 
};
private JavaCharStream input_stream;
private final int[] jjrounds = new int[49];
private final int[] jjstateSet = new int[98];
protected char curChar;
public ELParserTokenManager(JavaCharStream stream)
{
   if (JavaCharStream.staticFlag)
      throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
   input_stream = stream;
}
public ELParserTokenManager(JavaCharStream stream, int lexState)
{
   this(stream);
   SwitchTo(lexState);
}
public void ReInit(JavaCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
private final void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 49; i-- > 0;)
      jjrounds[i] = 0x80000000;
}
public void ReInit(JavaCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}
public void SwitchTo(int lexState)
{
   if (lexState >= 2 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

private final Token jjFillToken()
{
   Token t = Token.newToken(jjmatchedKind);
   t.kind = jjmatchedKind;
   String im = jjstrLiteralImages[jjmatchedKind];
   t.image = (im == null) ? input_stream.GetImage() : im;
   t.beginLine = input_stream.getBeginLine();
   t.beginColumn = input_stream.getBeginColumn();
   t.endLine = input_stream.getEndLine();
   t.endColumn = input_stream.getEndColumn();
   return t;
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

public final Token getNextToken() 
{
  int kind;
  Token specialToken = null;
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {   
   try   
   {     
      curChar = input_stream.BeginToken();
   }     
   catch(java.io.IOException e)
   {        
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   switch(curLexState)
   {
     case 0:
       jjmatchedKind = 0x7fffffff;
       jjmatchedPos = 0;
       curPos = jjMoveStringLiteralDfa0_0();
       break;
     case 1:
       try { input_stream.backup(0);
          while (curChar <= 32 && (0x100002600L & (1L << curChar)) != 0L)
             curChar = input_stream.BeginToken();
       }
       catch (java.io.IOException e1) { continue EOFLoop; }
       jjmatchedKind = 0x7fffffff;
       jjmatchedPos = 0;
       curPos = jjMoveStringLiteralDfa0_1();
       break;
   }
     if (jjmatchedKind != 0x7fffffff)
     {
        if (jjmatchedPos + 1 < curPos)
           input_stream.backup(curPos - jjmatchedPos - 1);
        if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
        {
           matchedToken = jjFillToken();
       if (jjnewLexState[jjmatchedKind] != -1)
         curLexState = jjnewLexState[jjmatchedKind];
           return matchedToken;
        }
        else
        {
         if (jjnewLexState[jjmatchedKind] != -1)
           curLexState = jjnewLexState[jjmatchedKind];
           continue EOFLoop;
        }
     }
     int error_line = input_stream.getEndLine();
     int error_column = input_stream.getEndColumn();
     String error_after = null;
     boolean EOFSeen = false;
     try { input_stream.readChar(); input_stream.backup(1); }
     catch (java.io.IOException e1) {
        EOFSeen = true;
        error_after = curPos <= 1 ? "" : input_stream.GetImage();
        if (curChar == '\n' || curChar == '\r') {
           error_line++;
           error_column = 0;
        }
        else
           error_column++;
     }
     if (!EOFSeen) {
        input_stream.backup(1);
        error_after = curPos <= 1 ? "" : input_stream.GetImage();
     }
     throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

}