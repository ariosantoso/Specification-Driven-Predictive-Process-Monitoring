/**
 * FOE Grammar
 * @author: Ario Santoso [santoso.ario@gmail.com]
 */

grammar NumCondAggregation;

@header {
package org.astw.parser.numConditionalAggregation.antlr;

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
}


/*
 * ----------------------------------------
 *  Parser Rules
 * ----------------------------------------
 */

//----------------------------------------------

parseCountAggregateFunc returns [NumConditionalAggregation parsedCntAggFunc]: 
        nac=cntAggContent EOF{
                                
            $parsedCntAggFunc = $nac.numConditionalAggregation;
        };

cntAggContent returns [NumConditionalAggregation numConditionalAggregation]: 
        aggCond=aggCondition AGG_SEPARATOR 
        aggRangeStart=aggRange AGG_RANGE_SEPARATOR 
        aggRangeEnd=aggRange {
                                
            try{
                $numConditionalAggregation = 
                    new NumConditionalAggregation(new NumExpNumber("0"),
                                                    $aggRangeStart.aggregationRange,
                                                    $aggRangeEnd.aggregationRange,
                                                    $aggCond.aggregationCondition);

                System.out.println("DEBUGA: AggCond: "+$aggCond.aggregationCondition);
                System.out.println("DEBUGA: AggRangeSt: "+$aggRangeStart.aggregationRange);
                System.out.println("DEBUGA: AggRangeEd: "+$aggRangeEnd.aggregationRange);

            }catch(Exception e){
              notifyErrorListeners("ERROR: " + e.getMessage());
            }
        };

parseConditionalAggregation returns [NumConditionalAggregation parsedNumConditionalAggregation]: 
        nac=numAggContent EOF{
                                
            $parsedNumConditionalAggregation = $nac.numConditionalAggregation;
        };

numAggContent returns [NumConditionalAggregation numConditionalAggregation]: 
        aggSrc=aggSource AGG_SEPARATOR 
        aggRangeStart=aggRange AGG_RANGE_SEPARATOR 
        aggRangeEnd=aggRange AGG_SEPARATOR 
        aggCond=aggCondition{
                                
            try{
                $numConditionalAggregation = 
                    new NumConditionalAggregation($aggSrc.aggregationSource,
                                                    $aggRangeStart.aggregationRange,
                                                    $aggRangeEnd.aggregationRange,
                                                    $aggCond.aggregationCondition);

                System.out.println("DEBUGA: AggSrc: "+$aggSrc.aggregationSource);
                System.out.println("DEBUGA: AggRangeSt: "+$aggRangeStart.aggregationRange);
                System.out.println("DEBUGA: AggRangeEd: "+$aggRangeEnd.aggregationRange);
                System.out.println("DEBUGA: AggCond: "+$aggCond.aggregationCondition);

            }catch(Exception e){
              notifyErrorListeners("ERROR: " + e.getMessage());
            }
        };

//----------------------------------------------
//Aggregation Source
//----------------------------------------------

aggSource returns [NumExp aggregationSource]: 
      ne=realNumber{
            System.out.println("DEBUGA: AggSrc-INT: "+$ne.text);
            try{
                $aggregationSource = new NumExpNumber($ne.text);
            }catch(Exception e){
              notifyErrorListeners("ERROR: " + e.getMessage());
            }
      }
      | ip=indexExp{
            System.out.println("DEBUGA: AggSrc-IE: "+$ip.text);
            $aggregationSource = $ip.indexExpression;
      }
      | ne1=aggSource op=(Plus | Minus) ne2=aggSource{
            System.out.println("DEBUGA: AggSrc-Arithmetic: "+$ne1.text+", "+$op.text+", "+$ne2.text);
            if($op.text.equals("+"))
                $aggregationSource = 
                    new NumExpBinary($ne1.aggregationSource, 
                                        Const.ArithmeticOperator.PLUS, 
                                        $ne2.aggregationSource);
            else if($op.text.equals("-"))
                $aggregationSource = 
                    new NumExpBinary($ne1.aggregationSource, 
                                        Const.ArithmeticOperator.MINUS, 
                                        $ne2.aggregationSource);
      } 
      | neQuery=queryNumeric{
            System.out.println("DEBUGA: AggSrc-Query: "+$neQuery.text);
            $aggregationSource = $neQuery.attAccessorNumeric;
      }
      | LP ne5=aggSource RP{
            System.out.println("DEBUGA: AggSrc-BRACKET: "+$ne5.text);
            $aggregationSource = new BracketNumExp($ne5.aggregationSource);
      }
      ;
//----------------------------------------------
//END OF Aggregation Source
//----------------------------------------------


//----------------------------------------------
//Aggregation Range
//----------------------------------------------

aggRange returns [IndexExp aggregationRange]: 
        num=PosNumber{
          System.out.println("DEBUGA: AgR-INT: "+$num.text);
          try{
            $aggregationRange = new IndexExpNum($num.text);
          }catch(Exception e){
            notifyErrorListeners("ERROR - invalid index value: " + e.getMessage());
          }
        }
        | ie1=aggRange op=(Plus | Minus) ie2=aggRange{
            System.out.println("DEBUGA: AgR-Arithmetic: "+$ie1.text+", "+$op.text+", "+$ie2.text);
            
            if($op.text.equals("+"))
                $aggregationRange = new IndexExpBinary( $ie1.aggregationRange, 
                                                        Const.ArithmeticOperator.PLUS, 
                                                        $ie2.aggregationRange);
            else if($op.text.equals("-"))
                $aggregationRange = new IndexExpBinary( $ie1.aggregationRange, 
                                                        Const.ArithmeticOperator.MINUS, 
                                                        $ie2.aggregationRange);
        }
        | iespec=SPEC{
            System.out.println("DEBUGA: AgR-SPEC: "+$iespec.text);
            if($iespec.text.equals("CURR"))
                $aggregationRange = new IndexExpSpec(Const.SpecialIndexType.CURR);
            else if($iespec.text.equals("LAST"))
                $aggregationRange = new IndexExpSpec(Const.SpecialIndexType.LAST);
        }
        ;
//----------------------------------------------
//END OF Aggregation Range
//----------------------------------------------


//----------------------------------------------
//AggregationCondition 
//----------------------------------------------

aggCondition returns [Formula aggregationCondition]:
        ee=eventExp{
            System.out.println("DEBUGA: EVENT: "+$ee.text);
            $aggregationCondition = $ee.eventExpression;
        }
        | LP f=aggCondition RP{
            System.out.println("DEBUGA: BRACKET: "+$f.text);
            $aggregationCondition = new BracketFormula($f.aggregationCondition);
        }
        | NEG f=aggCondition{
            System.out.println("DEBUGA: UNARY Formula: "+$f.text);
            $aggregationCondition = new FormulaNEG($f.aggregationCondition);;
        }
        | f1=aggCondition cop=(AND | OR) f2=aggCondition{
            System.out.println("DEBUGA: Binary Formula: "+$f1.text+", "+$cop.text+", "+$f2.text);
            
            if($cop.text.equals("||"))
                $aggregationCondition = new FormulaOR($f1.aggregationCondition, $f2.aggregationCondition);
            else if($cop.text.equals("&&"))
                $aggregationCondition = new FormulaAND($f1.aggregationCondition, $f2.aggregationCondition);
        }
        ;
 
//----------------------------------------------
//END OF AggregationCondition 
//----------------------------------------------

//----------------------------------------------
//Event Expression
//----------------------------------------------

eventExp returns [EventExp eventExpression]: 
        eeT=(True|False){
            System.out.println("DEBUGA: EE-True: "+$eeT.text);
            try{
                $eventExpression = new EventExpBoolean($eeT.text);
            }catch(Exception e){
              notifyErrorListeners("ERROR: " + e.getMessage());
            }
        }         
        | LP ee=eventExp RP{
            System.out.println("DEBUGA: EE-BRACKET: "+$ee.text);
            $eventExpression = new BracketEventExp($ee.eventExpression);
        }
        | nne1=nonNumExp opnne=( EQUAL | NOTEQUAL ) nne2=nonNumExp{
            System.out.println("DEBUGA: EE-NonNE-OP: "+$nne1.text+", "+$opnne.text+", "+$nne2.text);
            
            if($opnne.text.equals("==")){
                $eventExpression = new EventExpComponentComparison(
                    $nne1.nonNumericExpression, Const.ComparisonOperator.EQUAL, $nne2.nonNumericExpression);
            }else if($opnne.text.equals("!=")){
                $eventExpression = new EventExpComponentComparison(
                    $nne1.nonNumericExpression, Const.ComparisonOperator.NOT_EQUAL, $nne2.nonNumericExpression);
            }
        }
        | ne1=numExp op=(EQUAL | NOTEQUAL | GT | GTE | LT | LTE) ne2=numExp{
            System.out.println("DEBUGA: EE-NE-OP: "+$ne1.text+", "+$op.text+", "+$ne2.text);
            
            if($op.text.equals("==")){
                $eventExpression = new EventExpComponentComparison(
                    $ne1.numericExpression, Const.ComparisonOperator.EQUAL, $ne2.numericExpression);
            }else if($op.text.equals("!=")){
                $eventExpression = new EventExpComponentComparison(
                    $ne1.numericExpression, Const.ComparisonOperator.NOT_EQUAL, $ne2.numericExpression);
            }else if($op.text.equals(">")){
                $eventExpression = new EventExpComponentComparison(
                    $ne1.numericExpression, Const.ComparisonOperator.GREATER_THAN, $ne2.numericExpression);
            }else if($op.text.equals("<")){
                $eventExpression = new EventExpComponentComparison(
                    $ne1.numericExpression, Const.ComparisonOperator.LESS_THAN, $ne2.numericExpression);
            }else if($op.text.equals(">=")){
                $eventExpression = new EventExpComponentComparison(
                    $ne1.numericExpression, Const.ComparisonOperator.GREATER_THAN_OR_EQUAL, $ne2.numericExpression);
            }else if($op.text.equals("<=")){
                $eventExpression = new EventExpComponentComparison(
                    $ne1.numericExpression, Const.ComparisonOperator.LESS_THAN_OR_EQUAL, $ne2.numericExpression);
            }
        }
//        | ee1=eventExp op=( EQUAL | NOTEQUAL ) ee2=eventExp{
//            System.out.println("DEBUGA: EE-EE-OP: "+$ee1.text+", "+$op.text+", "+$ee2.text);
//            
//            if($op.text.equals("==")){
//                $eventExpression = new EventExpComparison(
//                   $ee1.eventExpression, Const.ComparisonOperator.EQUAL, $ee2.eventExpression);
//            }else if($op.text.equals("!=")){
//                $eventExpression = new EventExpComparison(
//                    $ee1.eventExpression, Const.ComparisonOperator.NOT_EQUAL, $ee2.eventExpression);
//            }
//        }
        ;
          
//----------------------------------------------
//END OF Event Expression
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
      ne=realNumber{
            System.out.println("DEBUGA: NE-INT: "+$ne.text);
            try{
                $numericExpression = new NumExpNumber($ne.text);
            }catch(Exception e){
              notifyErrorListeners("ERROR: " + e.getMessage());
            }
      }
      | ip=indexExp{
            System.out.println("DEBUGA: NE-IE: "+$ip.text);
            $numericExpression = $ip.indexExpression;
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
        num=PosNumber{
          System.out.println("DEBUGA: IE-INT: "+$num.text);
          try{
            $indexExpression = new IndexExpNum($num.text);
          }catch(Exception e){
            notifyErrorListeners("ERROR - invalid index value: " + e.getMessage());
          }
        }
        | ie1=indexExp op=(Plus | Minus) ie2=indexExp{
            System.out.println("DEBUGA: IE-Arithmetic: "+$ie1.text+", "+$op.text+", "+$ie2.text);
            
            if($op.text.equals("+"))
                $indexExpression = new IndexExpBinary($ie1.indexExpression, Const.ArithmeticOperator.PLUS, $ie2.indexExpression);
            else if($op.text.equals("-"))
                $indexExpression = new IndexExpBinary($ie1.indexExpression, Const.ArithmeticOperator.MINUS, $ie2.indexExpression);
        }
        | iespec=SPEC{
            System.out.println("DEBUGA: IE-SPEC: "+$iespec.text);
            if($iespec.text.equals("CURR"))
                $indexExpression = new IndexExpSpec(Const.SpecialIndexType.CURR);
            else if($iespec.text.equals("LAST"))
                $indexExpression = new IndexExpSpec(Const.SpecialIndexType.LAST);
        }
        | var=Var{
            System.out.println("DEBUGA: IE-Var: "+$var.text);
            $indexExpression = new IndexExpVar($var.text);
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

//numericAggregation:
                      

//----------------------------------------------
//END OF Numeric, Non Numeric, and Index Expression
//----------------------------------------------


realNumber returns [String num]:
    ('-'|)p=PosNumber{
                    
       $num = '-' + $p.text;
    }
    | pn=PosNumber{
                   
       $num = $pn.text;
    };
                            


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
String: '"'([a-z] | [A-Z] | [0-9]| UNDERSCORE | DASH | ' ' )*'"';
True: ('TRUE');
False: ('FALSE');

//Lexicon for quantifier
//EXIST: ('EXISTS');
//FORALL: ('FORALL');

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
PosNumber: ([0-9])+(('.'([0-9])+)|);
//Number: ('-'|)PosNumber;

//Aggregation Separator
AGG_SEPARATOR: '#';
AGG_RANGE_SEPARATOR: ':';

fragment UNDERSCORE: '_' ;
fragment DASH: '-' ;

/*
 * ----------------------------------------
 *  END OF Lexer Rules
 * ----------------------------------------
 */

