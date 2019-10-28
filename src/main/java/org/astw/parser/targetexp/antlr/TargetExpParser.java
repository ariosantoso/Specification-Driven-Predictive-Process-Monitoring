// Generated from TargetExp.g4 by ANTLR 4.7

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
import org.astw.parser.numConditionalAggregation.*;
import org.astw.parser.nonNumConditinoalAggregation.*;

import java.util.*;


import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TargetExpParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		OR=1, AND=2, NEG=3, IMPL=4, End=5, EQUAL=6, NOTEQUAL=7, GT=8, GTE=9, LT=10, 
		LTE=11, Plus=12, Minus=13, Multiply=14, Divide=15, String=16, True=17, 
		False=18, EXIST=19, FORALL=20, DOT=21, LP=22, RP=23, WS=24, Var=25, SPEC=26, 
		AttName=27, Qf=28, Quote=29, NEWLINE=30, PosNumber=31, NUMERIC_AGG_FUNC_SUM=32, 
		NUMERIC_AGG_FUNC_AVG=33, NUMERIC_AGG_FUNC_MAX=34, NUMERIC_AGG_FUNC_MIN=35, 
		NUMERIC_AGG_FUNC_COUNT=36, NUMERIC_AGG_FUNC_COUNTVAL=37, NON_NUMERIC_AGG_FUNC_CONCAT=38;
	public static final int
		RULE_targetExp = 0, RULE_nonNumExp = 1, RULE_numExp = 2, RULE_indexExp = 3, 
		RULE_query = 4, RULE_queryNumeric = 5, RULE_realNumber = 6;
	public static final String[] ruleNames = {
		"targetExp", "nonNumExp", "numExp", "indexExp", "query", "queryNumeric", 
		"realNumber"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'||'", "'&&'", "'!'", "'->'", "';'", "'=='", "'!='", "'>'", "'>='", 
		"'<'", "'<='", "'+'", "'-'", "'*'", "'\\'", null, null, null, null, null, 
		"'.'", "'('", "')'", null, null, null, null, "'e['", "'\"'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "OR", "AND", "NEG", "IMPL", "End", "EQUAL", "NOTEQUAL", "GT", "GTE", 
		"LT", "LTE", "Plus", "Minus", "Multiply", "Divide", "String", "True", 
		"False", "EXIST", "FORALL", "DOT", "LP", "RP", "WS", "Var", "SPEC", "AttName", 
		"Qf", "Quote", "NEWLINE", "PosNumber", "NUMERIC_AGG_FUNC_SUM", "NUMERIC_AGG_FUNC_AVG", 
		"NUMERIC_AGG_FUNC_MAX", "NUMERIC_AGG_FUNC_MIN", "NUMERIC_AGG_FUNC_COUNT", 
		"NUMERIC_AGG_FUNC_COUNTVAL", "NON_NUMERIC_AGG_FUNC_CONCAT"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "TargetExp.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TargetExpParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class TargetExpContext extends ParserRuleContext {
		public TargetExpression te;
		public NonNumExpContext nne;
		public NumExpContext ne;
		public TerminalNode EOF() { return getToken(TargetExpParser.EOF, 0); }
		public NonNumExpContext nonNumExp() {
			return getRuleContext(NonNumExpContext.class,0);
		}
		public NumExpContext numExp() {
			return getRuleContext(NumExpContext.class,0);
		}
		public TargetExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_targetExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TargetExpListener ) ((TargetExpListener)listener).enterTargetExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TargetExpListener ) ((TargetExpListener)listener).exitTargetExp(this);
		}
	}

	public final TargetExpContext targetExp() throws RecognitionException {
		TargetExpContext _localctx = new TargetExpContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_targetExp);
		try {
			setState(22);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(14);
				((TargetExpContext)_localctx).nne = nonNumExp();
				setState(15);
				match(EOF);


				            ((TargetExpContext)_localctx).te =  ((TargetExpContext)_localctx).nne.nonNumericExpression;
				        
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(18);
				((TargetExpContext)_localctx).ne = numExp(0);
				setState(19);
				match(EOF);

				                    
				            ((TargetExpContext)_localctx).te =  ((TargetExpContext)_localctx).ne.numericExpression;
				        
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NonNumExpContext extends ParserRuleContext {
		public NonNumExp nonNumericExpression;
		public Token nneString;
		public Token nneBool;
		public QueryContext nneQuery;
		public Token concatAgg;
		public NonNumExpContext nne;
		public TerminalNode String() { return getToken(TargetExpParser.String, 0); }
		public TerminalNode True() { return getToken(TargetExpParser.True, 0); }
		public TerminalNode False() { return getToken(TargetExpParser.False, 0); }
		public QueryContext query() {
			return getRuleContext(QueryContext.class,0);
		}
		public TerminalNode NON_NUMERIC_AGG_FUNC_CONCAT() { return getToken(TargetExpParser.NON_NUMERIC_AGG_FUNC_CONCAT, 0); }
		public TerminalNode LP() { return getToken(TargetExpParser.LP, 0); }
		public TerminalNode RP() { return getToken(TargetExpParser.RP, 0); }
		public NonNumExpContext nonNumExp() {
			return getRuleContext(NonNumExpContext.class,0);
		}
		public NonNumExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nonNumExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TargetExpListener ) ((TargetExpListener)listener).enterNonNumExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TargetExpListener ) ((TargetExpListener)listener).exitNonNumExp(this);
		}
	}

	public final NonNumExpContext nonNumExp() throws RecognitionException {
		NonNumExpContext _localctx = new NonNumExpContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_nonNumExp);
		int _la;
		try {
			setState(38);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case String:
				enterOuterAlt(_localctx, 1);
				{
				setState(24);
				((NonNumExpContext)_localctx).nneString = match(String);


				            StringBuilder sb = new StringBuilder((((NonNumExpContext)_localctx).nneString!=null?((NonNumExpContext)_localctx).nneString.getText():null));
				            sb = sb.deleteCharAt(sb.length()-1);
				            sb = sb.deleteCharAt(0);
				            String str = new String(sb);

				            System.out.println("DEBUGA: NonNE-String: "+(((NonNumExpContext)_localctx).nneString!=null?((NonNumExpContext)_localctx).nneString.getText():null)+" -> "+str);
				            ((NonNumExpContext)_localctx).nonNumericExpression =  new NonNumExpString(str);
				         
				}
				break;
			case True:
			case False:
				enterOuterAlt(_localctx, 2);
				{
				setState(26);
				((NonNumExpContext)_localctx).nneBool = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==True || _la==False) ) {
					((NonNumExpContext)_localctx).nneBool = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}

				            System.out.println("DEBUGA: NonNE-True: "+(((NonNumExpContext)_localctx).nneBool!=null?((NonNumExpContext)_localctx).nneBool.getText():null));
				            try{
				                ((NonNumExpContext)_localctx).nonNumericExpression =  new NonNumExpBoolean((((NonNumExpContext)_localctx).nneBool!=null?((NonNumExpContext)_localctx).nneBool.getText():null));
				            }catch(Exception e){
				              notifyErrorListeners("ERROR: " + e.getMessage());
				            }
				         
				}
				break;
			case Qf:
				enterOuterAlt(_localctx, 3);
				{
				setState(28);
				((NonNumExpContext)_localctx).nneQuery = query();

				            System.out.println("DEBUGA: NonNE-Query: "+(((NonNumExpContext)_localctx).nneQuery!=null?_input.getText(((NonNumExpContext)_localctx).nneQuery.start,((NonNumExpContext)_localctx).nneQuery.stop):null));
				            ((NonNumExpContext)_localctx).nonNumericExpression =  ((NonNumExpContext)_localctx).nneQuery.attAccessor;
				         
				}
				break;
			case NON_NUMERIC_AGG_FUNC_CONCAT:
				enterOuterAlt(_localctx, 4);
				{
				setState(31);
				((NonNumExpContext)_localctx).concatAgg = match(NON_NUMERIC_AGG_FUNC_CONCAT);

				            System.out.println("DEBUGA: CONCAT-agg-func: "+(((NonNumExpContext)_localctx).concatAgg!=null?((NonNumExpContext)_localctx).concatAgg.getText():null));
				                       
				            String aggStatement = (((NonNumExpContext)_localctx).concatAgg!=null?((NonNumExpContext)_localctx).concatAgg.getText():null);
				            
				            //filter out function name and the brackets
				            aggStatement = aggStatement.replace("CONCAT{","");
				            aggStatement = aggStatement.replace("}","");
				            System.out.println("DEBUGA: CONCAT-agg-func content: "+aggStatement);
				            
				            try{
				                NonNumConditionalAggregation nnca = 
				                    new NonNumConditionalAggregationParser().parse(aggStatement);
				                nnca.setAggregationType(Const.NonNumAggregationType.CONCAT);
				                
				                System.out.println("DEBUGA: AggType: "+Const.NonNumAggregationType.CONCAT);

				                ((NonNumExpContext)_localctx).nonNumericExpression =  nnca;

				            }catch(Exception e){
				              notifyErrorListeners("ERROR: " + e.getMessage());
				            }
				         
				}
				break;
			case LP:
				enterOuterAlt(_localctx, 5);
				{
				setState(33);
				match(LP);
				setState(34);
				((NonNumExpContext)_localctx).nne = nonNumExp();
				setState(35);
				match(RP);

				            System.out.println("DEBUGA: NonEE-BRACKET: "+(((NonNumExpContext)_localctx).nne!=null?_input.getText(((NonNumExpContext)_localctx).nne.start,((NonNumExpContext)_localctx).nne.stop):null));
				            ((NonNumExpContext)_localctx).nonNumericExpression =  new BracketNonNumExp(((NonNumExpContext)_localctx).nne.nonNumericExpression);
				         
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumExpContext extends ParserRuleContext {
		public NumExp numericExpression;
		public NumExpContext ne1;
		public RealNumberContext ne;
		public QueryNumericContext neQuery;
		public Token sumAgg;
		public Token avgAgg;
		public Token minAgg;
		public Token maxAgg;
		public Token cntAgg;
		public NumExpContext ne5;
		public Token op;
		public NumExpContext ne2;
		public RealNumberContext realNumber() {
			return getRuleContext(RealNumberContext.class,0);
		}
		public QueryNumericContext queryNumeric() {
			return getRuleContext(QueryNumericContext.class,0);
		}
		public TerminalNode NUMERIC_AGG_FUNC_SUM() { return getToken(TargetExpParser.NUMERIC_AGG_FUNC_SUM, 0); }
		public TerminalNode NUMERIC_AGG_FUNC_AVG() { return getToken(TargetExpParser.NUMERIC_AGG_FUNC_AVG, 0); }
		public TerminalNode NUMERIC_AGG_FUNC_MIN() { return getToken(TargetExpParser.NUMERIC_AGG_FUNC_MIN, 0); }
		public TerminalNode NUMERIC_AGG_FUNC_MAX() { return getToken(TargetExpParser.NUMERIC_AGG_FUNC_MAX, 0); }
		public TerminalNode NUMERIC_AGG_FUNC_COUNT() { return getToken(TargetExpParser.NUMERIC_AGG_FUNC_COUNT, 0); }
		public TerminalNode NUMERIC_AGG_FUNC_COUNTVAL() { return getToken(TargetExpParser.NUMERIC_AGG_FUNC_COUNTVAL, 0); }
		public TerminalNode LP() { return getToken(TargetExpParser.LP, 0); }
		public TerminalNode RP() { return getToken(TargetExpParser.RP, 0); }
		public List<NumExpContext> numExp() {
			return getRuleContexts(NumExpContext.class);
		}
		public NumExpContext numExp(int i) {
			return getRuleContext(NumExpContext.class,i);
		}
		public TerminalNode Plus() { return getToken(TargetExpParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(TargetExpParser.Minus, 0); }
		public NumExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TargetExpListener ) ((TargetExpListener)listener).enterNumExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TargetExpListener ) ((TargetExpListener)listener).exitNumExp(this);
		}
	}

	public final NumExpContext numExp() throws RecognitionException {
		return numExp(0);
	}

	private NumExpContext numExp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		NumExpContext _localctx = new NumExpContext(_ctx, _parentState);
		NumExpContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_numExp, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Minus:
			case PosNumber:
				{
				setState(41);
				((NumExpContext)_localctx).ne = realNumber();

				            System.out.println("DEBUGA: NE-INT: "+(((NumExpContext)_localctx).ne!=null?_input.getText(((NumExpContext)_localctx).ne.start,((NumExpContext)_localctx).ne.stop):null));
				            try{
				                ((NumExpContext)_localctx).numericExpression =  new NumExpNumber((((NumExpContext)_localctx).ne!=null?_input.getText(((NumExpContext)_localctx).ne.start,((NumExpContext)_localctx).ne.stop):null));
				            }catch(Exception e){
				              notifyErrorListeners("ERROR: " + e.getMessage());
				            }
				      
				}
				break;
			case Qf:
				{
				setState(44);
				((NumExpContext)_localctx).neQuery = queryNumeric();

				            System.out.println("DEBUGA: NE-Query: "+(((NumExpContext)_localctx).neQuery!=null?_input.getText(((NumExpContext)_localctx).neQuery.start,((NumExpContext)_localctx).neQuery.stop):null));
				            ((NumExpContext)_localctx).numericExpression =  ((NumExpContext)_localctx).neQuery.attAccessorNumeric;
				      
				}
				break;
			case NUMERIC_AGG_FUNC_SUM:
				{
				setState(47);
				((NumExpContext)_localctx).sumAgg = match(NUMERIC_AGG_FUNC_SUM);

				            System.out.println("DEBUGA: SUM-agg-func: "+(((NumExpContext)_localctx).sumAgg!=null?((NumExpContext)_localctx).sumAgg.getText():null));
				            
				            String aggStatement = (((NumExpContext)_localctx).sumAgg!=null?((NumExpContext)_localctx).sumAgg.getText():null);
				            
				            //filter out function name and the brackets
				            aggStatement = aggStatement.replace("SUM{","");
				            aggStatement = aggStatement.replace("}","");
				            System.out.println("DEBUGA: SUM-agg-func content: "+aggStatement);
				            
				            try{
				                NumConditionalAggregation nca = 
				                    new NumConditionalAggregationParser().parse(aggStatement);
				                nca.setAggregationType(Const.NumAggregationType.SUM);
				                
				                System.out.println(nca.getAggregationType());
				                
				                System.out.println("DEBUGA: AggType: "+Const.NumAggregationType.SUM);

				                ((NumExpContext)_localctx).numericExpression =  nca;

				            }catch(Exception e){
				              notifyErrorListeners("ERROR: " + e.getMessage());
				            }
				            
				      
				}
				break;
			case NUMERIC_AGG_FUNC_AVG:
				{
				setState(49);
				((NumExpContext)_localctx).avgAgg = match(NUMERIC_AGG_FUNC_AVG);

				            System.out.println("DEBUGA: AVG-agg-func: "+(((NumExpContext)_localctx).avgAgg!=null?((NumExpContext)_localctx).avgAgg.getText():null));
				            
				            String aggStatement = (((NumExpContext)_localctx).avgAgg!=null?((NumExpContext)_localctx).avgAgg.getText():null);
				            
				            //filter out function name and the brackets
				            aggStatement = aggStatement.replace("AVG{","");
				            aggStatement = aggStatement.replace("}","");
				            System.out.println("DEBUGA: AVG-agg-func content: "+aggStatement);
				            
				            try{
				                NumConditionalAggregation nca = 
				                    new NumConditionalAggregationParser().parse(aggStatement);
				                nca.setAggregationType(Const.NumAggregationType.AVG);
				                
				                System.out.println("DEBUGA: AggType: "+Const.NumAggregationType.AVG);
				                
				                ((NumExpContext)_localctx).numericExpression =  nca;

				            }catch(Exception e){
				              notifyErrorListeners("ERROR: " + e.getMessage());
				            }
				      
				}
				break;
			case NUMERIC_AGG_FUNC_MIN:
				{
				setState(51);
				((NumExpContext)_localctx).minAgg = match(NUMERIC_AGG_FUNC_MIN);

				            System.out.println("DEBUGA: MIN-agg-func: "+(((NumExpContext)_localctx).minAgg!=null?((NumExpContext)_localctx).minAgg.getText():null));
				            
				            String aggStatement = (((NumExpContext)_localctx).minAgg!=null?((NumExpContext)_localctx).minAgg.getText():null);
				            
				            //filter out function name and the brackets
				            aggStatement = aggStatement.replace("MIN{","");
				            aggStatement = aggStatement.replace("}","");
				            System.out.println("DEBUGA: MIN-agg-func content: "+aggStatement);
				            
				            try{
				                NumConditionalAggregation nca = 
				                    new NumConditionalAggregationParser().parse(aggStatement);
				                nca.setAggregationType(Const.NumAggregationType.MIN);
				                
				                System.out.println("DEBUGA: AggType: "+Const.NumAggregationType.MIN);
				                
				                ((NumExpContext)_localctx).numericExpression =  nca;

				            }catch(Exception e){
				              notifyErrorListeners("ERROR: " + e.getMessage());
				            }
				      
				}
				break;
			case NUMERIC_AGG_FUNC_MAX:
				{
				setState(53);
				((NumExpContext)_localctx).maxAgg = match(NUMERIC_AGG_FUNC_MAX);

				            System.out.println("DEBUGA: MAX-agg-func: "+(((NumExpContext)_localctx).maxAgg!=null?((NumExpContext)_localctx).maxAgg.getText():null));
				            
				            String aggStatement = (((NumExpContext)_localctx).maxAgg!=null?((NumExpContext)_localctx).maxAgg.getText():null);
				            
				            //filter out function name and the brackets
				            aggStatement = aggStatement.replace("MAX{","");
				            aggStatement = aggStatement.replace("}","");
				            System.out.println("DEBUGA: MAX-agg-func content: "+aggStatement);
				            
				            try{
				                NumConditionalAggregation nca = 
				                    new NumConditionalAggregationParser().parse(aggStatement);
				                nca.setAggregationType(Const.NumAggregationType.MAX);
				                
				                System.out.println("DEBUGA: AggType: "+Const.NumAggregationType.MAX);
				                
				                ((NumExpContext)_localctx).numericExpression =  nca;

				            }catch(Exception e){
				              notifyErrorListeners("ERROR: " + e.getMessage());
				            }

				      
				}
				break;
			case NUMERIC_AGG_FUNC_COUNT:
				{
				setState(55);
				((NumExpContext)_localctx).cntAgg = match(NUMERIC_AGG_FUNC_COUNT);

				            System.out.println("DEBUGA: COUNT-agg-func: "+(((NumExpContext)_localctx).cntAgg!=null?((NumExpContext)_localctx).cntAgg.getText():null));
				            
				            String aggStatement = (((NumExpContext)_localctx).cntAgg!=null?((NumExpContext)_localctx).cntAgg.getText():null);
				            
				            //filter out function name and the brackets
				            aggStatement = aggStatement.replace("COUNT{","");
				            aggStatement = aggStatement.replace("}","");
				            System.out.println("DEBUGA: COUNT-agg-func content: "+aggStatement);
				            
				            try{
				                NumConditionalAggregation nca = 
				                    new NumConditionalAggregationParser().parseCountAggFunc(aggStatement);
				                nca.setAggregationType(Const.NumAggregationType.COUNT);
				                
				                System.out.println("DEBUGA: AggType: "+Const.NumAggregationType.COUNT);
				                
				                ((NumExpContext)_localctx).numericExpression =  nca;

				            }catch(Exception e){
				              notifyErrorListeners("ERROR: " + e.getMessage());
				            }
				      
				}
				break;
			case NUMERIC_AGG_FUNC_COUNTVAL:
				{
				setState(57);
				((NumExpContext)_localctx).cntAgg = match(NUMERIC_AGG_FUNC_COUNTVAL);

				            System.out.println("DEBUGA: COUNTVAL-agg-func: "+(((NumExpContext)_localctx).cntAgg!=null?((NumExpContext)_localctx).cntAgg.getText():null));
				            
				            String aggStatement = (((NumExpContext)_localctx).cntAgg!=null?((NumExpContext)_localctx).cntAgg.getText():null);
				                        
				            //filter out function name and the brackets
				            aggStatement = aggStatement.replace("COUNTVAL{","");
				            aggStatement = aggStatement.replace("}","");
				            System.out.println("DEBUGA: COUNTVAL-agg-func content: "+aggStatement);
				            
				            //tokenize the content
				            StringTokenizer strtok = new StringTokenizer(aggStatement, "#");
				            String attName = strtok.nextToken().trim();
				            StringTokenizer strtok2 = new StringTokenizer(strtok.nextToken(), ":");
				            String aggRangeStart = strtok2.nextToken().trim();
				            String aggRangeEnd = strtok2.nextToken().trim();

				            
				            try{
				                IndexExp aggRangeSt = 
				                    new NumConditionalAggregationParser().parseAggRange(aggRangeStart);
				                IndexExp aggRangeEd = 
				                    new NumConditionalAggregationParser().parseAggRange(aggRangeEnd);
				                    
				                NumCountValAggregation ncva = 
				                        new NumCountValAggregation(attName, aggRangeSt, aggRangeEd);
				                    
				                ((NumExpContext)_localctx).numericExpression =  ncva;

				            }catch(Exception e){
				              notifyErrorListeners("ERROR: " + e.getMessage());
				            }

				            
				      
				}
				break;
			case LP:
				{
				setState(59);
				match(LP);
				setState(60);
				((NumExpContext)_localctx).ne5 = numExp(0);
				setState(61);
				match(RP);

				            System.out.println("DEBUGA: EE-BRACKET: "+(((NumExpContext)_localctx).ne5!=null?_input.getText(((NumExpContext)_localctx).ne5.start,((NumExpContext)_localctx).ne5.stop):null));
				            ((NumExpContext)_localctx).numericExpression =  new BracketNumExp(((NumExpContext)_localctx).ne5.numericExpression);
				      
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(73);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new NumExpContext(_parentctx, _parentState);
					_localctx.ne1 = _prevctx;
					_localctx.ne1 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_numExp);
					setState(66);
					if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
					setState(67);
					((NumExpContext)_localctx).op = _input.LT(1);
					_la = _input.LA(1);
					if ( !(_la==Plus || _la==Minus) ) {
						((NumExpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(68);
					((NumExpContext)_localctx).ne2 = numExp(10);

					                      System.out.println("DEBUGA: NE-Arithmetic: "+(((NumExpContext)_localctx).ne1!=null?_input.getText(((NumExpContext)_localctx).ne1.start,((NumExpContext)_localctx).ne1.stop):null)+", "+(((NumExpContext)_localctx).op!=null?((NumExpContext)_localctx).op.getText():null)+", "+(((NumExpContext)_localctx).ne2!=null?_input.getText(((NumExpContext)_localctx).ne2.start,((NumExpContext)_localctx).ne2.stop):null));
					                      if((((NumExpContext)_localctx).op!=null?((NumExpContext)_localctx).op.getText():null).equals("+"))
					                          ((NumExpContext)_localctx).numericExpression =  new NumExpBinary(((NumExpContext)_localctx).ne1.numericExpression, Const.ArithmeticOperator.PLUS, ((NumExpContext)_localctx).ne2.numericExpression);
					                      else if((((NumExpContext)_localctx).op!=null?((NumExpContext)_localctx).op.getText():null).equals("-"))
					                          ((NumExpContext)_localctx).numericExpression =  new NumExpBinary(((NumExpContext)_localctx).ne1.numericExpression, Const.ArithmeticOperator.MINUS, ((NumExpContext)_localctx).ne2.numericExpression);
					                
					}
					} 
				}
				setState(75);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class IndexExpContext extends ParserRuleContext {
		public IndexExp indexExpression;
		public IndexExpContext ie1;
		public Token num;
		public Token iespec;
		public Token op;
		public IndexExpContext ie2;
		public TerminalNode PosNumber() { return getToken(TargetExpParser.PosNumber, 0); }
		public TerminalNode SPEC() { return getToken(TargetExpParser.SPEC, 0); }
		public List<IndexExpContext> indexExp() {
			return getRuleContexts(IndexExpContext.class);
		}
		public IndexExpContext indexExp(int i) {
			return getRuleContext(IndexExpContext.class,i);
		}
		public TerminalNode Plus() { return getToken(TargetExpParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(TargetExpParser.Minus, 0); }
		public IndexExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TargetExpListener ) ((TargetExpListener)listener).enterIndexExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TargetExpListener ) ((TargetExpListener)listener).exitIndexExp(this);
		}
	}

	public final IndexExpContext indexExp() throws RecognitionException {
		return indexExp(0);
	}

	private IndexExpContext indexExp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		IndexExpContext _localctx = new IndexExpContext(_ctx, _parentState);
		IndexExpContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_indexExp, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PosNumber:
				{
				setState(77);
				((IndexExpContext)_localctx).num = match(PosNumber);

				          System.out.println("DEBUGA: IP-INT: "+(((IndexExpContext)_localctx).num!=null?((IndexExpContext)_localctx).num.getText():null));
				          try{
				            ((IndexExpContext)_localctx).indexExpression =  new IndexExpNum((((IndexExpContext)_localctx).num!=null?((IndexExpContext)_localctx).num.getText():null));
				          }catch(Exception e){
				            notifyErrorListeners("ERROR - invalid index value: " + e.getMessage());
				          }
				        
				}
				break;
			case SPEC:
				{
				setState(79);
				((IndexExpContext)_localctx).iespec = match(SPEC);

				            System.out.println("DEBUGA: IP-SPEC: "+(((IndexExpContext)_localctx).iespec!=null?((IndexExpContext)_localctx).iespec.getText():null));
				            if((((IndexExpContext)_localctx).iespec!=null?((IndexExpContext)_localctx).iespec.getText():null).equals("CURR"))
				                ((IndexExpContext)_localctx).indexExpression =  new IndexExpSpec(Const.SpecialIndexType.CURR);
				            else if((((IndexExpContext)_localctx).iespec!=null?((IndexExpContext)_localctx).iespec.getText():null).equals("LAST"))
				                ((IndexExpContext)_localctx).indexExpression =  new IndexExpSpec(Const.SpecialIndexType.LAST);
				        
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(90);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new IndexExpContext(_parentctx, _parentState);
					_localctx.ie1 = _prevctx;
					_localctx.ie1 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_indexExp);
					setState(83);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(84);
					((IndexExpContext)_localctx).op = _input.LT(1);
					_la = _input.LA(1);
					if ( !(_la==Plus || _la==Minus) ) {
						((IndexExpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(85);
					((IndexExpContext)_localctx).ie2 = indexExp(3);

					                      System.out.println("DEBUGA: IP-Arithmetic: "+(((IndexExpContext)_localctx).ie1!=null?_input.getText(((IndexExpContext)_localctx).ie1.start,((IndexExpContext)_localctx).ie1.stop):null)+", "+(((IndexExpContext)_localctx).op!=null?((IndexExpContext)_localctx).op.getText():null)+", "+(((IndexExpContext)_localctx).ie2!=null?_input.getText(((IndexExpContext)_localctx).ie2.start,((IndexExpContext)_localctx).ie2.stop):null));
					                      
					                      if((((IndexExpContext)_localctx).op!=null?((IndexExpContext)_localctx).op.getText():null).equals("+"))
					                          ((IndexExpContext)_localctx).indexExpression =  new IndexExpBinary(((IndexExpContext)_localctx).ie1.indexExpression, Const.ArithmeticOperator.PLUS, ((IndexExpContext)_localctx).ie2.indexExpression);
					                      else if((((IndexExpContext)_localctx).op!=null?((IndexExpContext)_localctx).op.getText():null).equals("-"))
					                          ((IndexExpContext)_localctx).indexExpression =  new IndexExpBinary(((IndexExpContext)_localctx).ie1.indexExpression, Const.ArithmeticOperator.MINUS, ((IndexExpContext)_localctx).ie2.indexExpression);
					                  
					}
					} 
				}
				setState(92);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class QueryContext extends ParserRuleContext {
		public AttributeAccessor attAccessor;
		public Token q;
		public IndexExpContext idx;
		public Token attName;
		public TerminalNode Qf() { return getToken(TargetExpParser.Qf, 0); }
		public IndexExpContext indexExp() {
			return getRuleContext(IndexExpContext.class,0);
		}
		public TerminalNode AttName() { return getToken(TargetExpParser.AttName, 0); }
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TargetExpListener ) ((TargetExpListener)listener).enterQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TargetExpListener ) ((TargetExpListener)listener).exitQuery(this);
		}
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_query);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			((QueryContext)_localctx).q = match(Qf);
			setState(94);
			((QueryContext)_localctx).idx = indexExp(0);
			setState(95);
			((QueryContext)_localctx).attName = match(AttName);


			            StringBuilder sb = new StringBuilder((((QueryContext)_localctx).attName!=null?((QueryContext)_localctx).attName.getText():null));

			            sb = sb.deleteCharAt(0);
			            sb = sb.deleteCharAt(0);
			            String attributeName = new String(sb);

			            System.out.println("DEBUGA: query-non-numeric: "+(((QueryContext)_localctx).idx!=null?_input.getText(((QueryContext)_localctx).idx.start,((QueryContext)_localctx).idx.stop):null)+", "+attributeName);

			            ((QueryContext)_localctx).attAccessor =  
			                new AttributeAccessor(  ((QueryContext)_localctx).idx.indexExpression, attributeName);
			        
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QueryNumericContext extends ParserRuleContext {
		public AttributeAccessor attAccessorNumeric;
		public Token q;
		public IndexExpContext idx;
		public Token attName;
		public TerminalNode Qf() { return getToken(TargetExpParser.Qf, 0); }
		public IndexExpContext indexExp() {
			return getRuleContext(IndexExpContext.class,0);
		}
		public TerminalNode AttName() { return getToken(TargetExpParser.AttName, 0); }
		public QueryNumericContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_queryNumeric; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TargetExpListener ) ((TargetExpListener)listener).enterQueryNumeric(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TargetExpListener ) ((TargetExpListener)listener).exitQueryNumeric(this);
		}
	}

	public final QueryNumericContext queryNumeric() throws RecognitionException {
		QueryNumericContext _localctx = new QueryNumericContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_queryNumeric);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			((QueryNumericContext)_localctx).q = match(Qf);
			setState(99);
			((QueryNumericContext)_localctx).idx = indexExp(0);
			setState(100);
			((QueryNumericContext)_localctx).attName = match(AttName);


			            StringBuilder sb = new StringBuilder((((QueryNumericContext)_localctx).attName!=null?((QueryNumericContext)_localctx).attName.getText():null));

			            sb = sb.deleteCharAt(0);
			            sb = sb.deleteCharAt(0);
			            String attributeName = new String(sb);

			            System.out.println("DEBUGA: query-numeric: "+(((QueryNumericContext)_localctx).idx!=null?_input.getText(((QueryNumericContext)_localctx).idx.start,((QueryNumericContext)_localctx).idx.stop):null)+", "+attributeName);

			            ((QueryNumericContext)_localctx).attAccessorNumeric =  
			                new AttributeAccessor(  ((QueryNumericContext)_localctx).idx.indexExpression, attributeName);
			        
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RealNumberContext extends ParserRuleContext {
		public String num;
		public Token p;
		public Token pn;
		public TerminalNode PosNumber() { return getToken(TargetExpParser.PosNumber, 0); }
		public RealNumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_realNumber; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TargetExpListener ) ((TargetExpListener)listener).enterRealNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TargetExpListener ) ((TargetExpListener)listener).exitRealNumber(this);
		}
	}

	public final RealNumberContext realNumber() throws RecognitionException {
		RealNumberContext _localctx = new RealNumberContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_realNumber);
		try {
			setState(111);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(105);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case Minus:
					{
					setState(103);
					match(Minus);
					}
					break;
				case PosNumber:
					{
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(107);
				((RealNumberContext)_localctx).p = match(PosNumber);

				                    
				       ((RealNumberContext)_localctx).num =  '-' + (((RealNumberContext)_localctx).p!=null?((RealNumberContext)_localctx).p.getText():null);
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(109);
				((RealNumberContext)_localctx).pn = match(PosNumber);

				                   
				       ((RealNumberContext)_localctx).num =  (((RealNumberContext)_localctx).pn!=null?((RealNumberContext)_localctx).pn.getText():null);
				    
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 2:
			return numExp_sempred((NumExpContext)_localctx, predIndex);
		case 3:
			return indexExp_sempred((IndexExpContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean numExp_sempred(NumExpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 9);
		}
		return true;
	}
	private boolean indexExp_sempred(IndexExpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3(t\4\2\t\2\4\3\t\3"+
		"\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\5\2\31\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\5\3)\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4C\n\4\3\4\3\4\3\4\3\4\3\4\7\4"+
		"J\n\4\f\4\16\4M\13\4\3\5\3\5\3\5\3\5\3\5\5\5T\n\5\3\5\3\5\3\5\3\5\3\5"+
		"\7\5[\n\5\f\5\16\5^\13\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3"+
		"\b\5\bl\n\b\3\b\3\b\3\b\3\b\5\br\n\b\3\b\2\4\6\b\t\2\4\6\b\n\f\16\2\4"+
		"\3\2\23\24\3\2\16\17\2~\2\30\3\2\2\2\4(\3\2\2\2\6B\3\2\2\2\bS\3\2\2\2"+
		"\n_\3\2\2\2\fd\3\2\2\2\16q\3\2\2\2\20\21\5\4\3\2\21\22\7\2\2\3\22\23\b"+
		"\2\1\2\23\31\3\2\2\2\24\25\5\6\4\2\25\26\7\2\2\3\26\27\b\2\1\2\27\31\3"+
		"\2\2\2\30\20\3\2\2\2\30\24\3\2\2\2\31\3\3\2\2\2\32\33\7\22\2\2\33)\b\3"+
		"\1\2\34\35\t\2\2\2\35)\b\3\1\2\36\37\5\n\6\2\37 \b\3\1\2 )\3\2\2\2!\""+
		"\7(\2\2\")\b\3\1\2#$\7\30\2\2$%\5\4\3\2%&\7\31\2\2&\'\b\3\1\2\')\3\2\2"+
		"\2(\32\3\2\2\2(\34\3\2\2\2(\36\3\2\2\2(!\3\2\2\2(#\3\2\2\2)\5\3\2\2\2"+
		"*+\b\4\1\2+,\5\16\b\2,-\b\4\1\2-C\3\2\2\2./\5\f\7\2/\60\b\4\1\2\60C\3"+
		"\2\2\2\61\62\7\"\2\2\62C\b\4\1\2\63\64\7#\2\2\64C\b\4\1\2\65\66\7%\2\2"+
		"\66C\b\4\1\2\678\7$\2\28C\b\4\1\29:\7&\2\2:C\b\4\1\2;<\7\'\2\2<C\b\4\1"+
		"\2=>\7\30\2\2>?\5\6\4\2?@\7\31\2\2@A\b\4\1\2AC\3\2\2\2B*\3\2\2\2B.\3\2"+
		"\2\2B\61\3\2\2\2B\63\3\2\2\2B\65\3\2\2\2B\67\3\2\2\2B9\3\2\2\2B;\3\2\2"+
		"\2B=\3\2\2\2CK\3\2\2\2DE\f\13\2\2EF\t\3\2\2FG\5\6\4\fGH\b\4\1\2HJ\3\2"+
		"\2\2ID\3\2\2\2JM\3\2\2\2KI\3\2\2\2KL\3\2\2\2L\7\3\2\2\2MK\3\2\2\2NO\b"+
		"\5\1\2OP\7!\2\2PT\b\5\1\2QR\7\34\2\2RT\b\5\1\2SN\3\2\2\2SQ\3\2\2\2T\\"+
		"\3\2\2\2UV\f\4\2\2VW\t\3\2\2WX\5\b\5\5XY\b\5\1\2Y[\3\2\2\2ZU\3\2\2\2["+
		"^\3\2\2\2\\Z\3\2\2\2\\]\3\2\2\2]\t\3\2\2\2^\\\3\2\2\2_`\7\36\2\2`a\5\b"+
		"\5\2ab\7\35\2\2bc\b\6\1\2c\13\3\2\2\2de\7\36\2\2ef\5\b\5\2fg\7\35\2\2"+
		"gh\b\7\1\2h\r\3\2\2\2il\7\17\2\2jl\3\2\2\2ki\3\2\2\2kj\3\2\2\2lm\3\2\2"+
		"\2mn\7!\2\2nr\b\b\1\2op\7!\2\2pr\b\b\1\2qk\3\2\2\2qo\3\2\2\2r\17\3\2\2"+
		"\2\n\30(BKS\\kq";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}