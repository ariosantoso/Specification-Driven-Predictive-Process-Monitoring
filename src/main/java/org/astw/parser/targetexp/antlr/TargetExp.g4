/**
 * FOE Grammar
 * @author: Ario Santoso [santoso.ario@gmail.com]
 */

grammar TargetExp;

@header {
package org.astw.parser.targetexp.antlr;

import org.astw.foe.*;
import org.astw.foe.impl.*;
import org.astw.foe.impl.eventexp.*;
import org.astw.foe.impl.frm.*;
import org.astw.foe.impl.nonnumeric.*;
import org.astw.foe.impl.numeric.*;
import org.astw.foe.impl.numeric.indexexp.*;
import org.astw.*;
import org.astw.util.*;
import org.astw.util.Const;
import org.astw.analyticrules.*;
}


/*
 * ----------------------------------------
 *  Parser Rules
 * ----------------------------------------
 */

//----------------------------------------------


//----------------------------------------------
//Target Expression
//----------------------------------------------

targetExp returns [TargetExpression te]: 
        nne=nonNumExp EOF{

            $te = $nne.nonNumericExpression;
        }
        | ne=numExp EOF{
                    
            $te = $ne.numericExpression;
        };

//----------------------------------------------
//END OF Target Expression
//----------------------------------------------



//----------------------------------------------
//Numeric, Non Numeric, and Index Expression
//----------------------------------------------

nonNumExp returns [NonNumExp nonNumericExpression]: 
         nneString=String{

            StringBuilder sb = new StringBuilder($nneString.text);
            sb = sb.deleteCharAt(sb.length()-1);
            sb = sb.deleteCharAt(0);
            String str = new String(sb);

            System.out.println("DEBUGA: NonNE-String: "+$nneString.text+" -> "+str);
            $nonNumericExpression = new NonNumExpString(str);
         }
         | nneBool=(True | False){
            System.out.println("DEBUGA: NonNE-True: "+$nneBool.text);
            try{
                $nonNumericExpression = new NonNumExpBoolean($nneBool.text);
            }catch(Exception e){
              notifyErrorListeners("ERROR: " + e.getMessage());
            }
         } 
         | nneQuery=query{
            System.out.println("DEBUGA: NonNE-Query: "+$nneQuery.text);
            $nonNumericExpression = $nneQuery.attAccessor;
         }
         | LP nne=nonNumExp RP{
            System.out.println("DEBUGA: NonEE-BRACKET: "+$nne.text);
            $nonNumericExpression = new BracketNonNumExp($nne.nonNumericExpression);
         }
         ;

numExp returns [NumExp numericExpression]: 
      ne=Number{
            System.out.println("DEBUGA: NE-INT: "+$ne.text);
            try{
                $numericExpression = new NumExpNumber($ne.text);
            }catch(Exception e){
              notifyErrorListeners("ERROR: " + e.getMessage());
            }
      }
      | ne1=numExp op=(Plus | Minus) ne2=numExp{
            System.out.println("DEBUGA: NE-Arithmetic: "+$ne1.text+", "+$op.text+", "+$ne2.text);
            if($op.text.equals("+"))
                $numericExpression = new NumExpBinary($ne1.numericExpression, Const.ArithmeticOperator.PLUS, $ne2.numericExpression);
            else if($op.text.equals("-"))
                $numericExpression = new NumExpBinary($ne1.numericExpression, Const.ArithmeticOperator.MINUS, $ne2.numericExpression);
      } 
      | neQuery=queryNumeric{
            System.out.println("DEBUGA: NE-Query: "+$neQuery.text);
            $numericExpression = $neQuery.attAccessorNumeric;
      }
      | LP ne5=numExp RP{
            System.out.println("DEBUGA: EE-BRACKET: "+$ne5.text);
            $numericExpression = new BracketNumExp($ne5.numericExpression);
      }
      ;

indexExp returns [IndexExp indexExpression]: 
        num=Number{
          System.out.println("DEBUGA: IP-INT: "+$num.text);
          try{
            $indexExpression = new IndexExpNum($num.text);
          }catch(Exception e){
            notifyErrorListeners("ERROR - invalid index value: " + e.getMessage());
          }
        }
        | ie1=indexExp op=(Plus | Minus) ie2=indexExp{
            System.out.println("DEBUGA: IP-Arithmetic: "+$ie1.text+", "+$op.text+", "+$ie2.text);
            
            if($op.text.equals("+"))
                $indexExpression = new IndexExpBinary($ie1.indexExpression, Const.ArithmeticOperator.PLUS, $ie2.indexExpression);
            else if($op.text.equals("-"))
                $indexExpression = new IndexExpBinary($ie1.indexExpression, Const.ArithmeticOperator.MINUS, $ie2.indexExpression);
        }
        | iespec=SPEC{
            System.out.println("DEBUGA: IP-SPEC: "+$iespec.text);
            if($iespec.text.equals("CURR"))
                $indexExpression = new IndexExpSpec(Const.SpecialIndexType.CURR);
            else if($iespec.text.equals("LAST"))
                $indexExpression = new IndexExpSpec(Const.SpecialIndexType.LAST);
        }
        ;

query returns [AttributeAccessor attAccessor]: 
         
         q=Qf idx=indexExp attName=AttName{

            StringBuilder sb = new StringBuilder($attName.text);

            sb = sb.deleteCharAt(0);
            sb = sb.deleteCharAt(0);
            String attributeName = new String(sb);

            System.out.println("DEBUGA: query-non-numeric: "+$idx.text+", "+attributeName);

            $attAccessor = 
                new AttributeAccessor(  $idx.indexExpression, attributeName);
        };

queryNumeric returns [AttributeAccessor attAccessorNumeric]: 
         
         q=Qf idx=indexExp attName=AttName{

            StringBuilder sb = new StringBuilder($attName.text);

            sb = sb.deleteCharAt(0);
            sb = sb.deleteCharAt(0);
            String attributeName = new String(sb);

            System.out.println("DEBUGA: query-numeric: "+$idx.text+", "+attributeName);

            $attAccessorNumeric = 
                new AttributeAccessor(  $idx.indexExpression, attributeName);
        };

//----------------------------------------------
//END OF Numeric, Non Numeric, and Index Expression
//----------------------------------------------

/*
 * ----------------------------------------
 *  END OF Parser Rules
 * ----------------------------------------
 */



/*
 * ----------------------------------------
 *  Lexer Rules
 * ----------------------------------------
 */

// Lexicon for logical operator
OR: '||';
AND: '&&';
NEG: '!';
IMPL: '->';

//logical comparison operator
End: ';'; // End of Formula
EQUAL: '==';
NOTEQUAL: '!=';

//arithmetic comparison operator
GT: '>';
GTE: '>=';
LT: '<';
LTE: '<=';

//arithmetic operators
Plus: '+';
Minus: '-';
Multiply: '*';
Divide: '\\';

//Non Numeric Expression
String: '"'([a-z] | [A-Z] | [0-9]| ' ' )*'"';
True: ('TRUE');
False: ('FALSE');

//Lexicon for quantifier
EXIST: ('EXISTS');
FORALL: ('FORALL');

// Other Lexicons
DOT: '.'; // Dot
LP: '('; // Open parentheses
RP: ')'; // Close parentheses
WS : [ \t\r\n]+ -> skip ; // White space
Var: ([a-z])+;
SPEC: ('LAST' | 'CURR' );
AttName: '].'([a-z] | [A-Z] | ':' )+;
Qf: 'e[';
Quote: '"';
NEWLINE : [\r\n]+ ;
Number: ('-'|)([0-9])+(('.'([0-9])+)|);

/*
 * ----------------------------------------
 *  END OF Lexer Rules
 * ----------------------------------------
 */

