// Generated from NumCondAggregation.g4 by ANTLR 4.7

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

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class NumCondAggregationParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		OR=1, AND=2, NEG=3, IMPL=4, End=5, EQUAL=6, NOTEQUAL=7, GT=8, GTE=9, LT=10, 
		LTE=11, Plus=12, Minus=13, Multiply=14, Divide=15, String=16, True=17, 
		False=18, DOT=19, LP=20, RP=21, WS=22, Var=23, SPEC=24, AttName=25, Qf=26, 
		Quote=27, NEWLINE=28, PosNumber=29, AGG_SEPARATOR=30, AGG_RANGE_SEPARATOR=31;
	public static final int
		RULE_parseCountAggregateFunc = 0, RULE_cntAggContent = 1, RULE_parseConditionalAggregation = 2, 
		RULE_numAggContent = 3, RULE_aggSource = 4, RULE_aggRange = 5, RULE_aggCondition = 6, 
		RULE_eventExp = 7, RULE_nonNumExp = 8, RULE_numExp = 9, RULE_indexExp = 10, 
		RULE_query = 11, RULE_queryNumeric = 12, RULE_realNumber = 13;
	public static final String[] ruleNames = {
		"parseCountAggregateFunc", "cntAggContent", "parseConditionalAggregation", 
		"numAggContent", "aggSource", "aggRange", "aggCondition", "eventExp", 
		"nonNumExp", "numExp", "indexExp", "query", "queryNumeric", "realNumber"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'||'", "'&&'", "'!'", "'->'", "';'", "'=='", "'!='", "'>'", "'>='", 
		"'<'", "'<='", "'+'", "'-'", "'*'", "'\\'", null, null, null, "'.'", "'('", 
		"')'", null, null, null, null, "'e['", "'\"'", null, null, "'#'", "':'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "OR", "AND", "NEG", "IMPL", "End", "EQUAL", "NOTEQUAL", "GT", "GTE", 
		"LT", "LTE", "Plus", "Minus", "Multiply", "Divide", "String", "True", 
		"False", "DOT", "LP", "RP", "WS", "Var", "SPEC", "AttName", "Qf", "Quote", 
		"NEWLINE", "PosNumber", "AGG_SEPARATOR", "AGG_RANGE_SEPARATOR"
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
	public String getGrammarFileName() { return "NumCondAggregation.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public NumCondAggregationParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ParseCountAggregateFuncContext extends ParserRuleContext {
		public NumConditionalAggregation parsedCntAggFunc;
		public CntAggContentContext nac;
		public TerminalNode EOF() { return getToken(NumCondAggregationParser.EOF, 0); }
		public CntAggContentContext cntAggContent() {
			return getRuleContext(CntAggContentContext.class,0);
		}
		public ParseCountAggregateFuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parseCountAggregateFunc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NumCondAggregationListener ) ((NumCondAggregationListener)listener).enterParseCountAggregateFunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NumCondAggregationListener ) ((NumCondAggregationListener)listener).exitParseCountAggregateFunc(this);
		}
	}

	public final ParseCountAggregateFuncContext parseCountAggregateFunc() throws RecognitionException {
		ParseCountAggregateFuncContext _localctx = new ParseCountAggregateFuncContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_parseCountAggregateFunc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			((ParseCountAggregateFuncContext)_localctx).nac = cntAggContent();
			setState(29);
			match(EOF);

			                                
			            ((ParseCountAggregateFuncContext)_localctx).parsedCntAggFunc =  ((ParseCountAggregateFuncContext)_localctx).nac.numConditionalAggregation;
			        
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

	public static class CntAggContentContext extends ParserRuleContext {
		public NumConditionalAggregation numConditionalAggregation;
		public AggConditionContext aggCond;
		public AggRangeContext aggRangeStart;
		public AggRangeContext aggRangeEnd;
		public TerminalNode AGG_SEPARATOR() { return getToken(NumCondAggregationParser.AGG_SEPARATOR, 0); }
		public TerminalNode AGG_RANGE_SEPARATOR() { return getToken(NumCondAggregationParser.AGG_RANGE_SEPARATOR, 0); }
		public AggConditionContext aggCondition() {
			return getRuleContext(AggConditionContext.class,0);
		}
		public List<AggRangeContext> aggRange() {
			return getRuleContexts(AggRangeContext.class);
		}
		public AggRangeContext aggRange(int i) {
			return getRuleContext(AggRangeContext.class,i);
		}
		public CntAggContentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cntAggContent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NumCondAggregationListener ) ((NumCondAggregationListener)listener).enterCntAggContent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NumCondAggregationListener ) ((NumCondAggregationListener)listener).exitCntAggContent(this);
		}
	}

	public final CntAggContentContext cntAggContent() throws RecognitionException {
		CntAggContentContext _localctx = new CntAggContentContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_cntAggContent);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			((CntAggContentContext)_localctx).aggCond = aggCondition(0);
			setState(33);
			match(AGG_SEPARATOR);
			setState(34);
			((CntAggContentContext)_localctx).aggRangeStart = aggRange(0);
			setState(35);
			match(AGG_RANGE_SEPARATOR);
			setState(36);
			((CntAggContentContext)_localctx).aggRangeEnd = aggRange(0);

			                                
			            try{
			                ((CntAggContentContext)_localctx).numConditionalAggregation =  
			                    new NumConditionalAggregation(new NumExpNumber("0"),
			                                                    ((CntAggContentContext)_localctx).aggRangeStart.aggregationRange,
			                                                    ((CntAggContentContext)_localctx).aggRangeEnd.aggregationRange,
			                                                    ((CntAggContentContext)_localctx).aggCond.aggregationCondition);

			                System.out.println("DEBUGA: AggCond: "+((CntAggContentContext)_localctx).aggCond.aggregationCondition);
			                System.out.println("DEBUGA: AggRangeSt: "+((CntAggContentContext)_localctx).aggRangeStart.aggregationRange);
			                System.out.println("DEBUGA: AggRangeEd: "+((CntAggContentContext)_localctx).aggRangeEnd.aggregationRange);

			            }catch(Exception e){
			              notifyErrorListeners("ERROR: " + e.getMessage());
			            }
			        
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

	public static class ParseConditionalAggregationContext extends ParserRuleContext {
		public NumConditionalAggregation parsedNumConditionalAggregation;
		public NumAggContentContext nac;
		public TerminalNode EOF() { return getToken(NumCondAggregationParser.EOF, 0); }
		public NumAggContentContext numAggContent() {
			return getRuleContext(NumAggContentContext.class,0);
		}
		public ParseConditionalAggregationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parseConditionalAggregation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NumCondAggregationListener ) ((NumCondAggregationListener)listener).enterParseConditionalAggregation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NumCondAggregationListener ) ((NumCondAggregationListener)listener).exitParseConditionalAggregation(this);
		}
	}

	public final ParseConditionalAggregationContext parseConditionalAggregation() throws RecognitionException {
		ParseConditionalAggregationContext _localctx = new ParseConditionalAggregationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_parseConditionalAggregation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			((ParseConditionalAggregationContext)_localctx).nac = numAggContent();
			setState(40);
			match(EOF);

			                                
			            ((ParseConditionalAggregationContext)_localctx).parsedNumConditionalAggregation =  ((ParseConditionalAggregationContext)_localctx).nac.numConditionalAggregation;
			        
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

	public static class NumAggContentContext extends ParserRuleContext {
		public NumConditionalAggregation numConditionalAggregation;
		public AggSourceContext aggSrc;
		public AggRangeContext aggRangeStart;
		public AggRangeContext aggRangeEnd;
		public AggConditionContext aggCond;
		public List<TerminalNode> AGG_SEPARATOR() { return getTokens(NumCondAggregationParser.AGG_SEPARATOR); }
		public TerminalNode AGG_SEPARATOR(int i) {
			return getToken(NumCondAggregationParser.AGG_SEPARATOR, i);
		}
		public TerminalNode AGG_RANGE_SEPARATOR() { return getToken(NumCondAggregationParser.AGG_RANGE_SEPARATOR, 0); }
		public AggSourceContext aggSource() {
			return getRuleContext(AggSourceContext.class,0);
		}
		public List<AggRangeContext> aggRange() {
			return getRuleContexts(AggRangeContext.class);
		}
		public AggRangeContext aggRange(int i) {
			return getRuleContext(AggRangeContext.class,i);
		}
		public AggConditionContext aggCondition() {
			return getRuleContext(AggConditionContext.class,0);
		}
		public NumAggContentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numAggContent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NumCondAggregationListener ) ((NumCondAggregationListener)listener).enterNumAggContent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NumCondAggregationListener ) ((NumCondAggregationListener)listener).exitNumAggContent(this);
		}
	}

	public final NumAggContentContext numAggContent() throws RecognitionException {
		NumAggContentContext _localctx = new NumAggContentContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_numAggContent);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			((NumAggContentContext)_localctx).aggSrc = aggSource(0);
			setState(44);
			match(AGG_SEPARATOR);
			setState(45);
			((NumAggContentContext)_localctx).aggRangeStart = aggRange(0);
			setState(46);
			match(AGG_RANGE_SEPARATOR);
			setState(47);
			((NumAggContentContext)_localctx).aggRangeEnd = aggRange(0);
			setState(48);
			match(AGG_SEPARATOR);
			setState(49);
			((NumAggContentContext)_localctx).aggCond = aggCondition(0);

			                                
			            try{
			                ((NumAggContentContext)_localctx).numConditionalAggregation =  
			                    new NumConditionalAggregation(((NumAggContentContext)_localctx).aggSrc.aggregationSource,
			                                                    ((NumAggContentContext)_localctx).aggRangeStart.aggregationRange,
			                                                    ((NumAggContentContext)_localctx).aggRangeEnd.aggregationRange,
			                                                    ((NumAggContentContext)_localctx).aggCond.aggregationCondition);

			                System.out.println("DEBUGA: AggSrc: "+((NumAggContentContext)_localctx).aggSrc.aggregationSource);
			                System.out.println("DEBUGA: AggRangeSt: "+((NumAggContentContext)_localctx).aggRangeStart.aggregationRange);
			                System.out.println("DEBUGA: AggRangeEd: "+((NumAggContentContext)_localctx).aggRangeEnd.aggregationRange);
			                System.out.println("DEBUGA: AggCond: "+((NumAggContentContext)_localctx).aggCond.aggregationCondition);

			            }catch(Exception e){
			              notifyErrorListeners("ERROR: " + e.getMessage());
			            }
			        
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

	public static class AggSourceContext extends ParserRuleContext {
		public NumExp aggregationSource;
		public AggSourceContext ne1;
		public RealNumberContext ne;
		public IndexExpContext ip;
		public QueryNumericContext neQuery;
		public AggSourceContext ne5;
		public Token op;
		public AggSourceContext ne2;
		public RealNumberContext realNumber() {
			return getRuleContext(RealNumberContext.class,0);
		}
		public IndexExpContext indexExp() {
			return getRuleContext(IndexExpContext.class,0);
		}
		public QueryNumericContext queryNumeric() {
			return getRuleContext(QueryNumericContext.class,0);
		}
		public TerminalNode LP() { return getToken(NumCondAggregationParser.LP, 0); }
		public TerminalNode RP() { return getToken(NumCondAggregationParser.RP, 0); }
		public List<AggSourceContext> aggSource() {
			return getRuleContexts(AggSourceContext.class);
		}
		public AggSourceContext aggSource(int i) {
			return getRuleContext(AggSourceContext.class,i);
		}
		public TerminalNode Plus() { return getToken(NumCondAggregationParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(NumCondAggregationParser.Minus, 0); }
		public AggSourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggSource; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NumCondAggregationListener ) ((NumCondAggregationListener)listener).enterAggSource(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NumCondAggregationListener ) ((NumCondAggregationListener)listener).exitAggSource(this);
		}
	}

	public final AggSourceContext aggSource() throws RecognitionException {
		return aggSource(0);
	}

	private AggSourceContext aggSource(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		AggSourceContext _localctx = new AggSourceContext(_ctx, _parentState);
		AggSourceContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_aggSource, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(53);
				((AggSourceContext)_localctx).ne = realNumber();

				            System.out.println("DEBUGA: AggSrc-INT: "+(((AggSourceContext)_localctx).ne!=null?_input.getText(((AggSourceContext)_localctx).ne.start,((AggSourceContext)_localctx).ne.stop):null));
				            try{
				                ((AggSourceContext)_localctx).aggregationSource =  new NumExpNumber((((AggSourceContext)_localctx).ne!=null?_input.getText(((AggSourceContext)_localctx).ne.start,((AggSourceContext)_localctx).ne.stop):null));
				            }catch(Exception e){
				              notifyErrorListeners("ERROR: " + e.getMessage());
				            }
				      
				}
				break;
			case 2:
				{
				setState(56);
				((AggSourceContext)_localctx).ip = indexExp(0);

				            System.out.println("DEBUGA: AggSrc-IE: "+(((AggSourceContext)_localctx).ip!=null?_input.getText(((AggSourceContext)_localctx).ip.start,((AggSourceContext)_localctx).ip.stop):null));
				            ((AggSourceContext)_localctx).aggregationSource =  ((AggSourceContext)_localctx).ip.indexExpression;
				      
				}
				break;
			case 3:
				{
				setState(59);
				((AggSourceContext)_localctx).neQuery = queryNumeric();

				            System.out.println("DEBUGA: AggSrc-Query: "+(((AggSourceContext)_localctx).neQuery!=null?_input.getText(((AggSourceContext)_localctx).neQuery.start,((AggSourceContext)_localctx).neQuery.stop):null));
				            ((AggSourceContext)_localctx).aggregationSource =  ((AggSourceContext)_localctx).neQuery.attAccessorNumeric;
				      
				}
				break;
			case 4:
				{
				setState(62);
				match(LP);
				setState(63);
				((AggSourceContext)_localctx).ne5 = aggSource(0);
				setState(64);
				match(RP);

				            System.out.println("DEBUGA: AggSrc-BRACKET: "+(((AggSourceContext)_localctx).ne5!=null?_input.getText(((AggSourceContext)_localctx).ne5.start,((AggSourceContext)_localctx).ne5.stop):null));
				            ((AggSourceContext)_localctx).aggregationSource =  new BracketNumExp(((AggSourceContext)_localctx).ne5.aggregationSource);
				      
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(76);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new AggSourceContext(_parentctx, _parentState);
					_localctx.ne1 = _prevctx;
					_localctx.ne1 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_aggSource);
					setState(69);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(70);
					((AggSourceContext)_localctx).op = _input.LT(1);
					_la = _input.LA(1);
					if ( !(_la==Plus || _la==Minus) ) {
						((AggSourceContext)_localctx).op = (Token)_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(71);
					((AggSourceContext)_localctx).ne2 = aggSource(4);

					                      System.out.println("DEBUGA: AggSrc-Arithmetic: "+(((AggSourceContext)_localctx).ne1!=null?_input.getText(((AggSourceContext)_localctx).ne1.start,((AggSourceContext)_localctx).ne1.stop):null)+", "+(((AggSourceContext)_localctx).op!=null?((AggSourceContext)_localctx).op.getText():null)+", "+(((AggSourceContext)_localctx).ne2!=null?_input.getText(((AggSourceContext)_localctx).ne2.start,((AggSourceContext)_localctx).ne2.stop):null));
					                      if((((AggSourceContext)_localctx).op!=null?((AggSourceContext)_localctx).op.getText():null).equals("+"))
					                          ((AggSourceContext)_localctx).aggregationSource =  
					                              new NumExpBinary(((AggSourceContext)_localctx).ne1.aggregationSource, 
					                                                  Const.ArithmeticOperator.PLUS, 
					                                                  ((AggSourceContext)_localctx).ne2.aggregationSource);
					                      else if((((AggSourceContext)_localctx).op!=null?((AggSourceContext)_localctx).op.getText():null).equals("-"))
					                          ((AggSourceContext)_localctx).aggregationSource =  
					                              new NumExpBinary(((AggSourceContext)_localctx).ne1.aggregationSource, 
					                                                  Const.ArithmeticOperator.MINUS, 
					                                                  ((AggSourceContext)_localctx).ne2.aggregationSource);
					                
					}
					} 
				}
				setState(78);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
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

	public static class AggRangeContext extends ParserRuleContext {
		public IndexExp aggregationRange;
		public AggRangeContext ie1;
		public Token num;
		public Token iespec;
		public Token op;
		public AggRangeContext ie2;
		public TerminalNode PosNumber() { return getToken(NumCondAggregationParser.PosNumber, 0); }
		public TerminalNode SPEC() { return getToken(NumCondAggregationParser.SPEC, 0); }
		public List<AggRangeContext> aggRange() {
			return getRuleContexts(AggRangeContext.class);
		}
		public AggRangeContext aggRange(int i) {
			return getRuleContext(AggRangeContext.class,i);
		}
		public TerminalNode Plus() { return getToken(NumCondAggregationParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(NumCondAggregationParser.Minus, 0); }
		public AggRangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggRange; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NumCondAggregationListener ) ((NumCondAggregationListener)listener).enterAggRange(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NumCondAggregationListener ) ((NumCondAggregationListener)listener).exitAggRange(this);
		}
	}

	public final AggRangeContext aggRange() throws RecognitionException {
		return aggRange(0);
	}

	private AggRangeContext aggRange(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		AggRangeContext _localctx = new AggRangeContext(_ctx, _parentState);
		AggRangeContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_aggRange, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PosNumber:
				{
				setState(80);
				((AggRangeContext)_localctx).num = match(PosNumber);

				          System.out.println("DEBUGA: AgR-INT: "+(((AggRangeContext)_localctx).num!=null?((AggRangeContext)_localctx).num.getText():null));
				          try{
				            ((AggRangeContext)_localctx).aggregationRange =  new IndexExpNum((((AggRangeContext)_localctx).num!=null?((AggRangeContext)_localctx).num.getText():null));
				          }catch(Exception e){
				            notifyErrorListeners("ERROR - invalid index value: " + e.getMessage());
				          }
				        
				}
				break;
			case SPEC:
				{
				setState(82);
				((AggRangeContext)_localctx).iespec = match(SPEC);

				            System.out.println("DEBUGA: AgR-SPEC: "+(((AggRangeContext)_localctx).iespec!=null?((AggRangeContext)_localctx).iespec.getText():null));
				            if((((AggRangeContext)_localctx).iespec!=null?((AggRangeContext)_localctx).iespec.getText():null).equals("CURR"))
				                ((AggRangeContext)_localctx).aggregationRange =  new IndexExpSpec(Const.SpecialIndexType.CURR);
				            else if((((AggRangeContext)_localctx).iespec!=null?((AggRangeContext)_localctx).iespec.getText():null).equals("LAST"))
				                ((AggRangeContext)_localctx).aggregationRange =  new IndexExpSpec(Const.SpecialIndexType.LAST);
				        
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(93);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new AggRangeContext(_parentctx, _parentState);
					_localctx.ie1 = _prevctx;
					_localctx.ie1 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_aggRange);
					setState(86);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(87);
					((AggRangeContext)_localctx).op = _input.LT(1);
					_la = _input.LA(1);
					if ( !(_la==Plus || _la==Minus) ) {
						((AggRangeContext)_localctx).op = (Token)_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(88);
					((AggRangeContext)_localctx).ie2 = aggRange(3);

					                      System.out.println("DEBUGA: AgR-Arithmetic: "+(((AggRangeContext)_localctx).ie1!=null?_input.getText(((AggRangeContext)_localctx).ie1.start,((AggRangeContext)_localctx).ie1.stop):null)+", "+(((AggRangeContext)_localctx).op!=null?((AggRangeContext)_localctx).op.getText():null)+", "+(((AggRangeContext)_localctx).ie2!=null?_input.getText(((AggRangeContext)_localctx).ie2.start,((AggRangeContext)_localctx).ie2.stop):null));
					                      
					                      if((((AggRangeContext)_localctx).op!=null?((AggRangeContext)_localctx).op.getText():null).equals("+"))
					                          ((AggRangeContext)_localctx).aggregationRange =  new IndexExpBinary( ((AggRangeContext)_localctx).ie1.aggregationRange, 
					                                                                  Const.ArithmeticOperator.PLUS, 
					                                                                  ((AggRangeContext)_localctx).ie2.aggregationRange);
					                      else if((((AggRangeContext)_localctx).op!=null?((AggRangeContext)_localctx).op.getText():null).equals("-"))
					                          ((AggRangeContext)_localctx).aggregationRange =  new IndexExpBinary( ((AggRangeContext)_localctx).ie1.aggregationRange, 
					                                                                  Const.ArithmeticOperator.MINUS, 
					                                                                  ((AggRangeContext)_localctx).ie2.aggregationRange);
					                  
					}
					} 
				}
				setState(95);
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

	public static class AggConditionContext extends ParserRuleContext {
		public Formula aggregationCondition;
		public AggConditionContext f1;
		public EventExpContext ee;
		public AggConditionContext f;
		public Token cop;
		public AggConditionContext f2;
		public EventExpContext eventExp() {
			return getRuleContext(EventExpContext.class,0);
		}
		public TerminalNode LP() { return getToken(NumCondAggregationParser.LP, 0); }
		public TerminalNode RP() { return getToken(NumCondAggregationParser.RP, 0); }
		public List<AggConditionContext> aggCondition() {
			return getRuleContexts(AggConditionContext.class);
		}
		public AggConditionContext aggCondition(int i) {
			return getRuleContext(AggConditionContext.class,i);
		}
		public TerminalNode NEG() { return getToken(NumCondAggregationParser.NEG, 0); }
		public TerminalNode AND() { return getToken(NumCondAggregationParser.AND, 0); }
		public TerminalNode OR() { return getToken(NumCondAggregationParser.OR, 0); }
		public AggConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NumCondAggregationListener ) ((NumCondAggregationListener)listener).enterAggCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NumCondAggregationListener ) ((NumCondAggregationListener)listener).exitAggCondition(this);
		}
	}

	public final AggConditionContext aggCondition() throws RecognitionException {
		return aggCondition(0);
	}

	private AggConditionContext aggCondition(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		AggConditionContext _localctx = new AggConditionContext(_ctx, _parentState);
		AggConditionContext _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_aggCondition, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(97);
				((AggConditionContext)_localctx).ee = eventExp();

				            System.out.println("DEBUGA: EVENT: "+(((AggConditionContext)_localctx).ee!=null?_input.getText(((AggConditionContext)_localctx).ee.start,((AggConditionContext)_localctx).ee.stop):null));
				            ((AggConditionContext)_localctx).aggregationCondition =  ((AggConditionContext)_localctx).ee.eventExpression;
				        
				}
				break;
			case 2:
				{
				setState(100);
				match(LP);
				setState(101);
				((AggConditionContext)_localctx).f = aggCondition(0);
				setState(102);
				match(RP);

				            System.out.println("DEBUGA: BRACKET: "+(((AggConditionContext)_localctx).f!=null?_input.getText(((AggConditionContext)_localctx).f.start,((AggConditionContext)_localctx).f.stop):null));
				            ((AggConditionContext)_localctx).aggregationCondition =  new BracketFormula(((AggConditionContext)_localctx).f.aggregationCondition);
				        
				}
				break;
			case 3:
				{
				setState(105);
				match(NEG);
				setState(106);
				((AggConditionContext)_localctx).f = aggCondition(2);

				            System.out.println("DEBUGA: UNARY Formula: "+(((AggConditionContext)_localctx).f!=null?_input.getText(((AggConditionContext)_localctx).f.start,((AggConditionContext)_localctx).f.stop):null));
				            ((AggConditionContext)_localctx).aggregationCondition =  new FormulaNEG(((AggConditionContext)_localctx).f.aggregationCondition);;
				        
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(118);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new AggConditionContext(_parentctx, _parentState);
					_localctx.f1 = _prevctx;
					_localctx.f1 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_aggCondition);
					setState(111);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(112);
					((AggConditionContext)_localctx).cop = _input.LT(1);
					_la = _input.LA(1);
					if ( !(_la==OR || _la==AND) ) {
						((AggConditionContext)_localctx).cop = (Token)_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(113);
					((AggConditionContext)_localctx).f2 = aggCondition(2);

					                      System.out.println("DEBUGA: Binary Formula: "+(((AggConditionContext)_localctx).f1!=null?_input.getText(((AggConditionContext)_localctx).f1.start,((AggConditionContext)_localctx).f1.stop):null)+", "+(((AggConditionContext)_localctx).cop!=null?((AggConditionContext)_localctx).cop.getText():null)+", "+(((AggConditionContext)_localctx).f2!=null?_input.getText(((AggConditionContext)_localctx).f2.start,((AggConditionContext)_localctx).f2.stop):null));
					                      
					                      if((((AggConditionContext)_localctx).cop!=null?((AggConditionContext)_localctx).cop.getText():null).equals("||"))
					                          ((AggConditionContext)_localctx).aggregationCondition =  new FormulaOR(((AggConditionContext)_localctx).f1.aggregationCondition, ((AggConditionContext)_localctx).f2.aggregationCondition);
					                      else if((((AggConditionContext)_localctx).cop!=null?((AggConditionContext)_localctx).cop.getText():null).equals("&&"))
					                          ((AggConditionContext)_localctx).aggregationCondition =  new FormulaAND(((AggConditionContext)_localctx).f1.aggregationCondition, ((AggConditionContext)_localctx).f2.aggregationCondition);
					                  
					}
					} 
				}
				setState(120);
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

	public static class EventExpContext extends ParserRuleContext {
		public EventExp eventExpression;
		public Token eeT;
		public EventExpContext ee;
		public NonNumExpContext nne1;
		public Token opnne;
		public NonNumExpContext nne2;
		public NumExpContext ne1;
		public Token op;
		public NumExpContext ne2;
		public TerminalNode True() { return getToken(NumCondAggregationParser.True, 0); }
		public TerminalNode False() { return getToken(NumCondAggregationParser.False, 0); }
		public TerminalNode LP() { return getToken(NumCondAggregationParser.LP, 0); }
		public TerminalNode RP() { return getToken(NumCondAggregationParser.RP, 0); }
		public EventExpContext eventExp() {
			return getRuleContext(EventExpContext.class,0);
		}
		public List<NonNumExpContext> nonNumExp() {
			return getRuleContexts(NonNumExpContext.class);
		}
		public NonNumExpContext nonNumExp(int i) {
			return getRuleContext(NonNumExpContext.class,i);
		}
		public TerminalNode EQUAL() { return getToken(NumCondAggregationParser.EQUAL, 0); }
		public TerminalNode NOTEQUAL() { return getToken(NumCondAggregationParser.NOTEQUAL, 0); }
		public List<NumExpContext> numExp() {
			return getRuleContexts(NumExpContext.class);
		}
		public NumExpContext numExp(int i) {
			return getRuleContext(NumExpContext.class,i);
		}
		public TerminalNode GT() { return getToken(NumCondAggregationParser.GT, 0); }
		public TerminalNode GTE() { return getToken(NumCondAggregationParser.GTE, 0); }
		public TerminalNode LT() { return getToken(NumCondAggregationParser.LT, 0); }
		public TerminalNode LTE() { return getToken(NumCondAggregationParser.LTE, 0); }
		public EventExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eventExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NumCondAggregationListener ) ((NumCondAggregationListener)listener).enterEventExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NumCondAggregationListener ) ((NumCondAggregationListener)listener).exitEventExp(this);
		}
	}

	public final EventExpContext eventExp() throws RecognitionException {
		EventExpContext _localctx = new EventExpContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_eventExp);
		int _la;
		try {
			setState(138);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(121);
				((EventExpContext)_localctx).eeT = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==True || _la==False) ) {
					((EventExpContext)_localctx).eeT = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}

				            System.out.println("DEBUGA: EE-True: "+(((EventExpContext)_localctx).eeT!=null?((EventExpContext)_localctx).eeT.getText():null));
				            try{
				                ((EventExpContext)_localctx).eventExpression =  new EventExpBoolean((((EventExpContext)_localctx).eeT!=null?((EventExpContext)_localctx).eeT.getText():null));
				            }catch(Exception e){
				              notifyErrorListeners("ERROR: " + e.getMessage());
				            }
				        
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(123);
				match(LP);
				setState(124);
				((EventExpContext)_localctx).ee = eventExp();
				setState(125);
				match(RP);

				            System.out.println("DEBUGA: EE-BRACKET: "+(((EventExpContext)_localctx).ee!=null?_input.getText(((EventExpContext)_localctx).ee.start,((EventExpContext)_localctx).ee.stop):null));
				            ((EventExpContext)_localctx).eventExpression =  new BracketEventExp(((EventExpContext)_localctx).ee.eventExpression);
				        
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(128);
				((EventExpContext)_localctx).nne1 = nonNumExp();
				setState(129);
				((EventExpContext)_localctx).opnne = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==EQUAL || _la==NOTEQUAL) ) {
					((EventExpContext)_localctx).opnne = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(130);
				((EventExpContext)_localctx).nne2 = nonNumExp();

				            System.out.println("DEBUGA: EE-NonNE-OP: "+(((EventExpContext)_localctx).nne1!=null?_input.getText(((EventExpContext)_localctx).nne1.start,((EventExpContext)_localctx).nne1.stop):null)+", "+(((EventExpContext)_localctx).opnne!=null?((EventExpContext)_localctx).opnne.getText():null)+", "+(((EventExpContext)_localctx).nne2!=null?_input.getText(((EventExpContext)_localctx).nne2.start,((EventExpContext)_localctx).nne2.stop):null));
				            
				            if((((EventExpContext)_localctx).opnne!=null?((EventExpContext)_localctx).opnne.getText():null).equals("==")){
				                ((EventExpContext)_localctx).eventExpression =  new EventExpComponentComparison(
				                    ((EventExpContext)_localctx).nne1.nonNumericExpression, Const.ComparisonOperator.EQUAL, ((EventExpContext)_localctx).nne2.nonNumericExpression);
				            }else if((((EventExpContext)_localctx).opnne!=null?((EventExpContext)_localctx).opnne.getText():null).equals("!=")){
				                ((EventExpContext)_localctx).eventExpression =  new EventExpComponentComparison(
				                    ((EventExpContext)_localctx).nne1.nonNumericExpression, Const.ComparisonOperator.NOT_EQUAL, ((EventExpContext)_localctx).nne2.nonNumericExpression);
				            }
				        
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(133);
				((EventExpContext)_localctx).ne1 = numExp(0);
				setState(134);
				((EventExpContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQUAL) | (1L << NOTEQUAL) | (1L << GT) | (1L << GTE) | (1L << LT) | (1L << LTE))) != 0)) ) {
					((EventExpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(135);
				((EventExpContext)_localctx).ne2 = numExp(0);

				            System.out.println("DEBUGA: EE-NE-OP: "+(((EventExpContext)_localctx).ne1!=null?_input.getText(((EventExpContext)_localctx).ne1.start,((EventExpContext)_localctx).ne1.stop):null)+", "+(((EventExpContext)_localctx).op!=null?((EventExpContext)_localctx).op.getText():null)+", "+(((EventExpContext)_localctx).ne2!=null?_input.getText(((EventExpContext)_localctx).ne2.start,((EventExpContext)_localctx).ne2.stop):null));
				            
				            if((((EventExpContext)_localctx).op!=null?((EventExpContext)_localctx).op.getText():null).equals("==")){
				                ((EventExpContext)_localctx).eventExpression =  new EventExpComponentComparison(
				                    ((EventExpContext)_localctx).ne1.numericExpression, Const.ComparisonOperator.EQUAL, ((EventExpContext)_localctx).ne2.numericExpression);
				            }else if((((EventExpContext)_localctx).op!=null?((EventExpContext)_localctx).op.getText():null).equals("!=")){
				                ((EventExpContext)_localctx).eventExpression =  new EventExpComponentComparison(
				                    ((EventExpContext)_localctx).ne1.numericExpression, Const.ComparisonOperator.NOT_EQUAL, ((EventExpContext)_localctx).ne2.numericExpression);
				            }else if((((EventExpContext)_localctx).op!=null?((EventExpContext)_localctx).op.getText():null).equals(">")){
				                ((EventExpContext)_localctx).eventExpression =  new EventExpComponentComparison(
				                    ((EventExpContext)_localctx).ne1.numericExpression, Const.ComparisonOperator.GREATER_THAN, ((EventExpContext)_localctx).ne2.numericExpression);
				            }else if((((EventExpContext)_localctx).op!=null?((EventExpContext)_localctx).op.getText():null).equals("<")){
				                ((EventExpContext)_localctx).eventExpression =  new EventExpComponentComparison(
				                    ((EventExpContext)_localctx).ne1.numericExpression, Const.ComparisonOperator.LESS_THAN, ((EventExpContext)_localctx).ne2.numericExpression);
				            }else if((((EventExpContext)_localctx).op!=null?((EventExpContext)_localctx).op.getText():null).equals(">=")){
				                ((EventExpContext)_localctx).eventExpression =  new EventExpComponentComparison(
				                    ((EventExpContext)_localctx).ne1.numericExpression, Const.ComparisonOperator.GREATER_THAN_OR_EQUAL, ((EventExpContext)_localctx).ne2.numericExpression);
				            }else if((((EventExpContext)_localctx).op!=null?((EventExpContext)_localctx).op.getText():null).equals("<=")){
				                ((EventExpContext)_localctx).eventExpression =  new EventExpComponentComparison(
				                    ((EventExpContext)_localctx).ne1.numericExpression, Const.ComparisonOperator.LESS_THAN_OR_EQUAL, ((EventExpContext)_localctx).ne2.numericExpression);
				            }
				        
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
		public NonNumExpContext nne;
		public TerminalNode String() { return getToken(NumCondAggregationParser.String, 0); }
		public TerminalNode True() { return getToken(NumCondAggregationParser.True, 0); }
		public TerminalNode False() { return getToken(NumCondAggregationParser.False, 0); }
		public QueryContext query() {
			return getRuleContext(QueryContext.class,0);
		}
		public TerminalNode LP() { return getToken(NumCondAggregationParser.LP, 0); }
		public TerminalNode RP() { return getToken(NumCondAggregationParser.RP, 0); }
		public NonNumExpContext nonNumExp() {
			return getRuleContext(NonNumExpContext.class,0);
		}
		public NonNumExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nonNumExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NumCondAggregationListener ) ((NumCondAggregationListener)listener).enterNonNumExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NumCondAggregationListener ) ((NumCondAggregationListener)listener).exitNonNumExp(this);
		}
	}

	public final NonNumExpContext nonNumExp() throws RecognitionException {
		NonNumExpContext _localctx = new NonNumExpContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_nonNumExp);
		int _la;
		try {
			setState(152);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case String:
				enterOuterAlt(_localctx, 1);
				{
				setState(140);
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
				setState(142);
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
				setState(144);
				((NonNumExpContext)_localctx).nneQuery = query();

				            System.out.println("DEBUGA: NonNE-Query: "+(((NonNumExpContext)_localctx).nneQuery!=null?_input.getText(((NonNumExpContext)_localctx).nneQuery.start,((NonNumExpContext)_localctx).nneQuery.stop):null));
				            ((NonNumExpContext)_localctx).nonNumericExpression =  ((NonNumExpContext)_localctx).nneQuery.attAccessor;
				         
				}
				break;
			case LP:
				enterOuterAlt(_localctx, 4);
				{
				setState(147);
				match(LP);
				setState(148);
				((NonNumExpContext)_localctx).nne = nonNumExp();
				setState(149);
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
		public IndexExpContext ip;
		public QueryNumericContext neQuery;
		public NumExpContext ne5;
		public Token op;
		public NumExpContext ne2;
		public RealNumberContext realNumber() {
			return getRuleContext(RealNumberContext.class,0);
		}
		public IndexExpContext indexExp() {
			return getRuleContext(IndexExpContext.class,0);
		}
		public QueryNumericContext queryNumeric() {
			return getRuleContext(QueryNumericContext.class,0);
		}
		public TerminalNode LP() { return getToken(NumCondAggregationParser.LP, 0); }
		public TerminalNode RP() { return getToken(NumCondAggregationParser.RP, 0); }
		public List<NumExpContext> numExp() {
			return getRuleContexts(NumExpContext.class);
		}
		public NumExpContext numExp(int i) {
			return getRuleContext(NumExpContext.class,i);
		}
		public TerminalNode Plus() { return getToken(NumCondAggregationParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(NumCondAggregationParser.Minus, 0); }
		public NumExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NumCondAggregationListener ) ((NumCondAggregationListener)listener).enterNumExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NumCondAggregationListener ) ((NumCondAggregationListener)listener).exitNumExp(this);
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
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_numExp, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(155);
				((NumExpContext)_localctx).ne = realNumber();

				            System.out.println("DEBUGA: NE-INT: "+(((NumExpContext)_localctx).ne!=null?_input.getText(((NumExpContext)_localctx).ne.start,((NumExpContext)_localctx).ne.stop):null));
				            try{
				                ((NumExpContext)_localctx).numericExpression =  new NumExpNumber((((NumExpContext)_localctx).ne!=null?_input.getText(((NumExpContext)_localctx).ne.start,((NumExpContext)_localctx).ne.stop):null));
				            }catch(Exception e){
				              notifyErrorListeners("ERROR: " + e.getMessage());
				            }
				      
				}
				break;
			case 2:
				{
				setState(158);
				((NumExpContext)_localctx).ip = indexExp(0);

				            System.out.println("DEBUGA: NE-IE: "+(((NumExpContext)_localctx).ip!=null?_input.getText(((NumExpContext)_localctx).ip.start,((NumExpContext)_localctx).ip.stop):null));
				            ((NumExpContext)_localctx).numericExpression =  ((NumExpContext)_localctx).ip.indexExpression;
				      
				}
				break;
			case 3:
				{
				setState(161);
				((NumExpContext)_localctx).neQuery = queryNumeric();

				            System.out.println("DEBUGA: NE-Query: "+(((NumExpContext)_localctx).neQuery!=null?_input.getText(((NumExpContext)_localctx).neQuery.start,((NumExpContext)_localctx).neQuery.stop):null));
				            ((NumExpContext)_localctx).numericExpression =  ((NumExpContext)_localctx).neQuery.attAccessorNumeric;
				      
				}
				break;
			case 4:
				{
				setState(164);
				match(LP);
				setState(165);
				((NumExpContext)_localctx).ne5 = numExp(0);
				setState(166);
				match(RP);

				            System.out.println("DEBUGA: EE-BRACKET: "+(((NumExpContext)_localctx).ne5!=null?_input.getText(((NumExpContext)_localctx).ne5.start,((NumExpContext)_localctx).ne5.stop):null));
				            ((NumExpContext)_localctx).numericExpression =  new BracketNumExp(((NumExpContext)_localctx).ne5.numericExpression);
				      
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(178);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
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
					setState(171);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(172);
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
					setState(173);
					((NumExpContext)_localctx).ne2 = numExp(4);

					                      System.out.println("DEBUGA: NE-Arithmetic: "+(((NumExpContext)_localctx).ne1!=null?_input.getText(((NumExpContext)_localctx).ne1.start,((NumExpContext)_localctx).ne1.stop):null)+", "+(((NumExpContext)_localctx).op!=null?((NumExpContext)_localctx).op.getText():null)+", "+(((NumExpContext)_localctx).ne2!=null?_input.getText(((NumExpContext)_localctx).ne2.start,((NumExpContext)_localctx).ne2.stop):null));
					                      if((((NumExpContext)_localctx).op!=null?((NumExpContext)_localctx).op.getText():null).equals("+"))
					                          ((NumExpContext)_localctx).numericExpression =  new NumExpBinary(((NumExpContext)_localctx).ne1.numericExpression, Const.ArithmeticOperator.PLUS, ((NumExpContext)_localctx).ne2.numericExpression);
					                      else if((((NumExpContext)_localctx).op!=null?((NumExpContext)_localctx).op.getText():null).equals("-"))
					                          ((NumExpContext)_localctx).numericExpression =  new NumExpBinary(((NumExpContext)_localctx).ne1.numericExpression, Const.ArithmeticOperator.MINUS, ((NumExpContext)_localctx).ne2.numericExpression);
					                
					}
					} 
				}
				setState(180);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
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
		public Token var;
		public Token op;
		public IndexExpContext ie2;
		public TerminalNode PosNumber() { return getToken(NumCondAggregationParser.PosNumber, 0); }
		public TerminalNode SPEC() { return getToken(NumCondAggregationParser.SPEC, 0); }
		public TerminalNode Var() { return getToken(NumCondAggregationParser.Var, 0); }
		public List<IndexExpContext> indexExp() {
			return getRuleContexts(IndexExpContext.class);
		}
		public IndexExpContext indexExp(int i) {
			return getRuleContext(IndexExpContext.class,i);
		}
		public TerminalNode Plus() { return getToken(NumCondAggregationParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(NumCondAggregationParser.Minus, 0); }
		public IndexExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NumCondAggregationListener ) ((NumCondAggregationListener)listener).enterIndexExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NumCondAggregationListener ) ((NumCondAggregationListener)listener).exitIndexExp(this);
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
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_indexExp, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PosNumber:
				{
				setState(182);
				((IndexExpContext)_localctx).num = match(PosNumber);

				          System.out.println("DEBUGA: IE-INT: "+(((IndexExpContext)_localctx).num!=null?((IndexExpContext)_localctx).num.getText():null));
				          try{
				            ((IndexExpContext)_localctx).indexExpression =  new IndexExpNum((((IndexExpContext)_localctx).num!=null?((IndexExpContext)_localctx).num.getText():null));
				          }catch(Exception e){
				            notifyErrorListeners("ERROR - invalid index value: " + e.getMessage());
				          }
				        
				}
				break;
			case SPEC:
				{
				setState(184);
				((IndexExpContext)_localctx).iespec = match(SPEC);

				            System.out.println("DEBUGA: IE-SPEC: "+(((IndexExpContext)_localctx).iespec!=null?((IndexExpContext)_localctx).iespec.getText():null));
				            if((((IndexExpContext)_localctx).iespec!=null?((IndexExpContext)_localctx).iespec.getText():null).equals("CURR"))
				                ((IndexExpContext)_localctx).indexExpression =  new IndexExpSpec(Const.SpecialIndexType.CURR);
				            else if((((IndexExpContext)_localctx).iespec!=null?((IndexExpContext)_localctx).iespec.getText():null).equals("LAST"))
				                ((IndexExpContext)_localctx).indexExpression =  new IndexExpSpec(Const.SpecialIndexType.LAST);
				        
				}
				break;
			case Var:
				{
				setState(186);
				((IndexExpContext)_localctx).var = match(Var);

				            System.out.println("DEBUGA: IE-Var: "+(((IndexExpContext)_localctx).var!=null?((IndexExpContext)_localctx).var.getText():null));
				            ((IndexExpContext)_localctx).indexExpression =  new IndexExpVar((((IndexExpContext)_localctx).var!=null?((IndexExpContext)_localctx).var.getText():null));
				        
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(197);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
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
					setState(190);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(191);
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
					setState(192);
					((IndexExpContext)_localctx).ie2 = indexExp(4);

					                      System.out.println("DEBUGA: IE-Arithmetic: "+(((IndexExpContext)_localctx).ie1!=null?_input.getText(((IndexExpContext)_localctx).ie1.start,((IndexExpContext)_localctx).ie1.stop):null)+", "+(((IndexExpContext)_localctx).op!=null?((IndexExpContext)_localctx).op.getText():null)+", "+(((IndexExpContext)_localctx).ie2!=null?_input.getText(((IndexExpContext)_localctx).ie2.start,((IndexExpContext)_localctx).ie2.stop):null));
					                      
					                      if((((IndexExpContext)_localctx).op!=null?((IndexExpContext)_localctx).op.getText():null).equals("+"))
					                          ((IndexExpContext)_localctx).indexExpression =  new IndexExpBinary(((IndexExpContext)_localctx).ie1.indexExpression, Const.ArithmeticOperator.PLUS, ((IndexExpContext)_localctx).ie2.indexExpression);
					                      else if((((IndexExpContext)_localctx).op!=null?((IndexExpContext)_localctx).op.getText():null).equals("-"))
					                          ((IndexExpContext)_localctx).indexExpression =  new IndexExpBinary(((IndexExpContext)_localctx).ie1.indexExpression, Const.ArithmeticOperator.MINUS, ((IndexExpContext)_localctx).ie2.indexExpression);
					                  
					}
					} 
				}
				setState(199);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
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
		public TerminalNode Qf() { return getToken(NumCondAggregationParser.Qf, 0); }
		public IndexExpContext indexExp() {
			return getRuleContext(IndexExpContext.class,0);
		}
		public TerminalNode AttName() { return getToken(NumCondAggregationParser.AttName, 0); }
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NumCondAggregationListener ) ((NumCondAggregationListener)listener).enterQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NumCondAggregationListener ) ((NumCondAggregationListener)listener).exitQuery(this);
		}
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_query);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			((QueryContext)_localctx).q = match(Qf);
			setState(201);
			((QueryContext)_localctx).idx = indexExp(0);
			setState(202);
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
		public TerminalNode Qf() { return getToken(NumCondAggregationParser.Qf, 0); }
		public IndexExpContext indexExp() {
			return getRuleContext(IndexExpContext.class,0);
		}
		public TerminalNode AttName() { return getToken(NumCondAggregationParser.AttName, 0); }
		public QueryNumericContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_queryNumeric; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NumCondAggregationListener ) ((NumCondAggregationListener)listener).enterQueryNumeric(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NumCondAggregationListener ) ((NumCondAggregationListener)listener).exitQueryNumeric(this);
		}
	}

	public final QueryNumericContext queryNumeric() throws RecognitionException {
		QueryNumericContext _localctx = new QueryNumericContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_queryNumeric);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			((QueryNumericContext)_localctx).q = match(Qf);
			setState(206);
			((QueryNumericContext)_localctx).idx = indexExp(0);
			setState(207);
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
		public TerminalNode PosNumber() { return getToken(NumCondAggregationParser.PosNumber, 0); }
		public RealNumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_realNumber; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NumCondAggregationListener ) ((NumCondAggregationListener)listener).enterRealNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NumCondAggregationListener ) ((NumCondAggregationListener)listener).exitRealNumber(this);
		}
	}

	public final RealNumberContext realNumber() throws RecognitionException {
		RealNumberContext _localctx = new RealNumberContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_realNumber);
		try {
			setState(218);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(212);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case Minus:
					{
					setState(210);
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
				setState(214);
				((RealNumberContext)_localctx).p = match(PosNumber);

				                    
				       ((RealNumberContext)_localctx).num =  '-' + (((RealNumberContext)_localctx).p!=null?((RealNumberContext)_localctx).p.getText():null);
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(216);
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
		case 4:
			return aggSource_sempred((AggSourceContext)_localctx, predIndex);
		case 5:
			return aggRange_sempred((AggRangeContext)_localctx, predIndex);
		case 6:
			return aggCondition_sempred((AggConditionContext)_localctx, predIndex);
		case 9:
			return numExp_sempred((NumExpContext)_localctx, predIndex);
		case 10:
			return indexExp_sempred((IndexExpContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean aggSource_sempred(AggSourceContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		}
		return true;
	}
	private boolean aggRange_sempred(AggRangeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean aggCondition_sempred(AggConditionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean numExp_sempred(NumExpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 3);
		}
		return true;
	}
	private boolean indexExp_sempred(IndexExpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3!\u00df\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6F\n\6\3\6"+
		"\3\6\3\6\3\6\3\6\7\6M\n\6\f\6\16\6P\13\6\3\7\3\7\3\7\3\7\3\7\5\7W\n\7"+
		"\3\7\3\7\3\7\3\7\3\7\7\7^\n\7\f\7\16\7a\13\7\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bp\n\b\3\b\3\b\3\b\3\b\3\b\7\bw\n\b\f\b\16"+
		"\bz\13\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\5\t\u008d\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5"+
		"\n\u009b\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\5\13\u00ac\n\13\3\13\3\13\3\13\3\13\3\13\7\13\u00b3"+
		"\n\13\f\13\16\13\u00b6\13\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00bf\n\f"+
		"\3\f\3\f\3\f\3\f\3\f\7\f\u00c6\n\f\f\f\16\f\u00c9\13\f\3\r\3\r\3\r\3\r"+
		"\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\5\17\u00d7\n\17\3\17\3\17\3\17"+
		"\3\17\5\17\u00dd\n\17\3\17\2\7\n\f\16\24\26\20\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\2\7\3\2\16\17\3\2\3\4\3\2\23\24\3\2\b\t\3\2\b\r\2\u00e8\2"+
		"\36\3\2\2\2\4\"\3\2\2\2\6)\3\2\2\2\b-\3\2\2\2\nE\3\2\2\2\fV\3\2\2\2\16"+
		"o\3\2\2\2\20\u008c\3\2\2\2\22\u009a\3\2\2\2\24\u00ab\3\2\2\2\26\u00be"+
		"\3\2\2\2\30\u00ca\3\2\2\2\32\u00cf\3\2\2\2\34\u00dc\3\2\2\2\36\37\5\4"+
		"\3\2\37 \7\2\2\3 !\b\2\1\2!\3\3\2\2\2\"#\5\16\b\2#$\7 \2\2$%\5\f\7\2%"+
		"&\7!\2\2&\'\5\f\7\2\'(\b\3\1\2(\5\3\2\2\2)*\5\b\5\2*+\7\2\2\3+,\b\4\1"+
		"\2,\7\3\2\2\2-.\5\n\6\2./\7 \2\2/\60\5\f\7\2\60\61\7!\2\2\61\62\5\f\7"+
		"\2\62\63\7 \2\2\63\64\5\16\b\2\64\65\b\5\1\2\65\t\3\2\2\2\66\67\b\6\1"+
		"\2\678\5\34\17\289\b\6\1\29F\3\2\2\2:;\5\26\f\2;<\b\6\1\2<F\3\2\2\2=>"+
		"\5\32\16\2>?\b\6\1\2?F\3\2\2\2@A\7\26\2\2AB\5\n\6\2BC\7\27\2\2CD\b\6\1"+
		"\2DF\3\2\2\2E\66\3\2\2\2E:\3\2\2\2E=\3\2\2\2E@\3\2\2\2FN\3\2\2\2GH\f\5"+
		"\2\2HI\t\2\2\2IJ\5\n\6\6JK\b\6\1\2KM\3\2\2\2LG\3\2\2\2MP\3\2\2\2NL\3\2"+
		"\2\2NO\3\2\2\2O\13\3\2\2\2PN\3\2\2\2QR\b\7\1\2RS\7\37\2\2SW\b\7\1\2TU"+
		"\7\32\2\2UW\b\7\1\2VQ\3\2\2\2VT\3\2\2\2W_\3\2\2\2XY\f\4\2\2YZ\t\2\2\2"+
		"Z[\5\f\7\5[\\\b\7\1\2\\^\3\2\2\2]X\3\2\2\2^a\3\2\2\2_]\3\2\2\2_`\3\2\2"+
		"\2`\r\3\2\2\2a_\3\2\2\2bc\b\b\1\2cd\5\20\t\2de\b\b\1\2ep\3\2\2\2fg\7\26"+
		"\2\2gh\5\16\b\2hi\7\27\2\2ij\b\b\1\2jp\3\2\2\2kl\7\5\2\2lm\5\16\b\4mn"+
		"\b\b\1\2np\3\2\2\2ob\3\2\2\2of\3\2\2\2ok\3\2\2\2px\3\2\2\2qr\f\3\2\2r"+
		"s\t\3\2\2st\5\16\b\4tu\b\b\1\2uw\3\2\2\2vq\3\2\2\2wz\3\2\2\2xv\3\2\2\2"+
		"xy\3\2\2\2y\17\3\2\2\2zx\3\2\2\2{|\t\4\2\2|\u008d\b\t\1\2}~\7\26\2\2~"+
		"\177\5\20\t\2\177\u0080\7\27\2\2\u0080\u0081\b\t\1\2\u0081\u008d\3\2\2"+
		"\2\u0082\u0083\5\22\n\2\u0083\u0084\t\5\2\2\u0084\u0085\5\22\n\2\u0085"+
		"\u0086\b\t\1\2\u0086\u008d\3\2\2\2\u0087\u0088\5\24\13\2\u0088\u0089\t"+
		"\6\2\2\u0089\u008a\5\24\13\2\u008a\u008b\b\t\1\2\u008b\u008d\3\2\2\2\u008c"+
		"{\3\2\2\2\u008c}\3\2\2\2\u008c\u0082\3\2\2\2\u008c\u0087\3\2\2\2\u008d"+
		"\21\3\2\2\2\u008e\u008f\7\22\2\2\u008f\u009b\b\n\1\2\u0090\u0091\t\4\2"+
		"\2\u0091\u009b\b\n\1\2\u0092\u0093\5\30\r\2\u0093\u0094\b\n\1\2\u0094"+
		"\u009b\3\2\2\2\u0095\u0096\7\26\2\2\u0096\u0097\5\22\n\2\u0097\u0098\7"+
		"\27\2\2\u0098\u0099\b\n\1\2\u0099\u009b\3\2\2\2\u009a\u008e\3\2\2\2\u009a"+
		"\u0090\3\2\2\2\u009a\u0092\3\2\2\2\u009a\u0095\3\2\2\2\u009b\23\3\2\2"+
		"\2\u009c\u009d\b\13\1\2\u009d\u009e\5\34\17\2\u009e\u009f\b\13\1\2\u009f"+
		"\u00ac\3\2\2\2\u00a0\u00a1\5\26\f\2\u00a1\u00a2\b\13\1\2\u00a2\u00ac\3"+
		"\2\2\2\u00a3\u00a4\5\32\16\2\u00a4\u00a5\b\13\1\2\u00a5\u00ac\3\2\2\2"+
		"\u00a6\u00a7\7\26\2\2\u00a7\u00a8\5\24\13\2\u00a8\u00a9\7\27\2\2\u00a9"+
		"\u00aa\b\13\1\2\u00aa\u00ac\3\2\2\2\u00ab\u009c\3\2\2\2\u00ab\u00a0\3"+
		"\2\2\2\u00ab\u00a3\3\2\2\2\u00ab\u00a6\3\2\2\2\u00ac\u00b4\3\2\2\2\u00ad"+
		"\u00ae\f\5\2\2\u00ae\u00af\t\2\2\2\u00af\u00b0\5\24\13\6\u00b0\u00b1\b"+
		"\13\1\2\u00b1\u00b3\3\2\2\2\u00b2\u00ad\3\2\2\2\u00b3\u00b6\3\2\2\2\u00b4"+
		"\u00b2\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\25\3\2\2\2\u00b6\u00b4\3\2\2"+
		"\2\u00b7\u00b8\b\f\1\2\u00b8\u00b9\7\37\2\2\u00b9\u00bf\b\f\1\2\u00ba"+
		"\u00bb\7\32\2\2\u00bb\u00bf\b\f\1\2\u00bc\u00bd\7\31\2\2\u00bd\u00bf\b"+
		"\f\1\2\u00be\u00b7\3\2\2\2\u00be\u00ba\3\2\2\2\u00be\u00bc\3\2\2\2\u00bf"+
		"\u00c7\3\2\2\2\u00c0\u00c1\f\5\2\2\u00c1\u00c2\t\2\2\2\u00c2\u00c3\5\26"+
		"\f\6\u00c3\u00c4\b\f\1\2\u00c4\u00c6\3\2\2\2\u00c5\u00c0\3\2\2\2\u00c6"+
		"\u00c9\3\2\2\2\u00c7\u00c5\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\27\3\2\2"+
		"\2\u00c9\u00c7\3\2\2\2\u00ca\u00cb\7\34\2\2\u00cb\u00cc\5\26\f\2\u00cc"+
		"\u00cd\7\33\2\2\u00cd\u00ce\b\r\1\2\u00ce\31\3\2\2\2\u00cf\u00d0\7\34"+
		"\2\2\u00d0\u00d1\5\26\f\2\u00d1\u00d2\7\33\2\2\u00d2\u00d3\b\16\1\2\u00d3"+
		"\33\3\2\2\2\u00d4\u00d7\7\17\2\2\u00d5\u00d7\3\2\2\2\u00d6\u00d4\3\2\2"+
		"\2\u00d6\u00d5\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00d9\7\37\2\2\u00d9"+
		"\u00dd\b\17\1\2\u00da\u00db\7\37\2\2\u00db\u00dd\b\17\1\2\u00dc\u00d6"+
		"\3\2\2\2\u00dc\u00da\3\2\2\2\u00dd\35\3\2\2\2\20ENV_ox\u008c\u009a\u00ab"+
		"\u00b4\u00be\u00c7\u00d6\u00dc";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}