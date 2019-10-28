// Generated from NonNumCondAggregation.g4 by ANTLR 4.7

package org.astw.parser.nonNumConditinoalAggregation.antlr;

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
public class NonNumCondAggregationParser extends Parser {
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
		RULE_parseConditionalAggregation = 0, RULE_nonNumAggContent = 1, RULE_aggSource = 2, 
		RULE_aggRange = 3, RULE_aggCondition = 4, RULE_eventExp = 5, RULE_nonNumExp = 6, 
		RULE_numExp = 7, RULE_indexExp = 8, RULE_query = 9, RULE_queryNumeric = 10, 
		RULE_realNumber = 11;
	public static final String[] ruleNames = {
		"parseConditionalAggregation", "nonNumAggContent", "aggSource", "aggRange", 
		"aggCondition", "eventExp", "nonNumExp", "numExp", "indexExp", "query", 
		"queryNumeric", "realNumber"
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
	public String getGrammarFileName() { return "NonNumCondAggregation.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public NonNumCondAggregationParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ParseConditionalAggregationContext extends ParserRuleContext {
		public NonNumConditionalAggregation parsedNonNumConditionalAggregation;
		public NonNumAggContentContext nac;
		public TerminalNode EOF() { return getToken(NonNumCondAggregationParser.EOF, 0); }
		public NonNumAggContentContext nonNumAggContent() {
			return getRuleContext(NonNumAggContentContext.class,0);
		}
		public ParseConditionalAggregationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parseConditionalAggregation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NonNumCondAggregationListener ) ((NonNumCondAggregationListener)listener).enterParseConditionalAggregation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NonNumCondAggregationListener ) ((NonNumCondAggregationListener)listener).exitParseConditionalAggregation(this);
		}
	}

	public final ParseConditionalAggregationContext parseConditionalAggregation() throws RecognitionException {
		ParseConditionalAggregationContext _localctx = new ParseConditionalAggregationContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_parseConditionalAggregation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			((ParseConditionalAggregationContext)_localctx).nac = nonNumAggContent();
			setState(25);
			match(EOF);

			                                
			            ((ParseConditionalAggregationContext)_localctx).parsedNonNumConditionalAggregation =  ((ParseConditionalAggregationContext)_localctx).nac.nonNumConditionalAggregation;
			        
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

	public static class NonNumAggContentContext extends ParserRuleContext {
		public NonNumConditionalAggregation nonNumConditionalAggregation;
		public AggSourceContext aggSrc;
		public AggRangeContext aggRangeStart;
		public AggRangeContext aggRangeEnd;
		public AggConditionContext aggCond;
		public List<TerminalNode> AGG_SEPARATOR() { return getTokens(NonNumCondAggregationParser.AGG_SEPARATOR); }
		public TerminalNode AGG_SEPARATOR(int i) {
			return getToken(NonNumCondAggregationParser.AGG_SEPARATOR, i);
		}
		public TerminalNode AGG_RANGE_SEPARATOR() { return getToken(NonNumCondAggregationParser.AGG_RANGE_SEPARATOR, 0); }
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
		public NonNumAggContentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nonNumAggContent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NonNumCondAggregationListener ) ((NonNumCondAggregationListener)listener).enterNonNumAggContent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NonNumCondAggregationListener ) ((NonNumCondAggregationListener)listener).exitNonNumAggContent(this);
		}
	}

	public final NonNumAggContentContext nonNumAggContent() throws RecognitionException {
		NonNumAggContentContext _localctx = new NonNumAggContentContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_nonNumAggContent);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			((NonNumAggContentContext)_localctx).aggSrc = aggSource();
			setState(29);
			match(AGG_SEPARATOR);
			setState(30);
			((NonNumAggContentContext)_localctx).aggRangeStart = aggRange(0);
			setState(31);
			match(AGG_RANGE_SEPARATOR);
			setState(32);
			((NonNumAggContentContext)_localctx).aggRangeEnd = aggRange(0);
			setState(33);
			match(AGG_SEPARATOR);
			setState(34);
			((NonNumAggContentContext)_localctx).aggCond = aggCondition(0);

			                                
			            try{
			                ((NonNumAggContentContext)_localctx).nonNumConditionalAggregation =  
			                    new NonNumConditionalAggregation(((NonNumAggContentContext)_localctx).aggSrc.aggregationSource,
			                                                    ((NonNumAggContentContext)_localctx).aggRangeStart.aggregationRange,
			                                                    ((NonNumAggContentContext)_localctx).aggRangeEnd.aggregationRange,
			                                                    ((NonNumAggContentContext)_localctx).aggCond.aggregationCondition);

			                System.out.println("DEBUGA: AggSrc: "+((NonNumAggContentContext)_localctx).aggSrc.aggregationSource);
			                System.out.println("DEBUGA: AggRangeSt: "+((NonNumAggContentContext)_localctx).aggRangeStart.aggregationRange);
			                System.out.println("DEBUGA: AggRangeEd: "+((NonNumAggContentContext)_localctx).aggRangeEnd.aggregationRange);
			                System.out.println("DEBUGA: AggCond: "+((NonNumAggContentContext)_localctx).aggCond.aggregationCondition);

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
		public NonNumExp aggregationSource;
		public Token nneString;
		public Token nneBool;
		public QueryContext nneQuery;
		public NonNumExpContext nne;
		public TerminalNode String() { return getToken(NonNumCondAggregationParser.String, 0); }
		public TerminalNode True() { return getToken(NonNumCondAggregationParser.True, 0); }
		public TerminalNode False() { return getToken(NonNumCondAggregationParser.False, 0); }
		public QueryContext query() {
			return getRuleContext(QueryContext.class,0);
		}
		public TerminalNode LP() { return getToken(NonNumCondAggregationParser.LP, 0); }
		public TerminalNode RP() { return getToken(NonNumCondAggregationParser.RP, 0); }
		public NonNumExpContext nonNumExp() {
			return getRuleContext(NonNumExpContext.class,0);
		}
		public AggSourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggSource; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NonNumCondAggregationListener ) ((NonNumCondAggregationListener)listener).enterAggSource(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NonNumCondAggregationListener ) ((NonNumCondAggregationListener)listener).exitAggSource(this);
		}
	}

	public final AggSourceContext aggSource() throws RecognitionException {
		AggSourceContext _localctx = new AggSourceContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_aggSource);
		int _la;
		try {
			setState(49);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case String:
				enterOuterAlt(_localctx, 1);
				{
				setState(37);
				((AggSourceContext)_localctx).nneString = match(String);


				            StringBuilder sb = new StringBuilder((((AggSourceContext)_localctx).nneString!=null?((AggSourceContext)_localctx).nneString.getText():null));
				            sb = sb.deleteCharAt(sb.length()-1);
				            sb = sb.deleteCharAt(0);
				            String str = new String(sb);

				            System.out.println("DEBUGA: AggSrc-String: "+(((AggSourceContext)_localctx).nneString!=null?((AggSourceContext)_localctx).nneString.getText():null)+" -> "+str);
				            ((AggSourceContext)_localctx).aggregationSource =  new NonNumExpString(str);
				         
				}
				break;
			case True:
			case False:
				enterOuterAlt(_localctx, 2);
				{
				setState(39);
				((AggSourceContext)_localctx).nneBool = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==True || _la==False) ) {
					((AggSourceContext)_localctx).nneBool = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}

				            System.out.println("DEBUGA: AggSrc-True: "+(((AggSourceContext)_localctx).nneBool!=null?((AggSourceContext)_localctx).nneBool.getText():null));
				            try{
				                ((AggSourceContext)_localctx).aggregationSource =  new NonNumExpBoolean((((AggSourceContext)_localctx).nneBool!=null?((AggSourceContext)_localctx).nneBool.getText():null));
				            }catch(Exception e){
				              notifyErrorListeners("ERROR: " + e.getMessage());
				            }
				         
				}
				break;
			case Qf:
				enterOuterAlt(_localctx, 3);
				{
				setState(41);
				((AggSourceContext)_localctx).nneQuery = query();

				            System.out.println("DEBUGA: AggSrc-Query: "+(((AggSourceContext)_localctx).nneQuery!=null?_input.getText(((AggSourceContext)_localctx).nneQuery.start,((AggSourceContext)_localctx).nneQuery.stop):null));
				            ((AggSourceContext)_localctx).aggregationSource =  ((AggSourceContext)_localctx).nneQuery.attAccessor;
				         
				}
				break;
			case LP:
				enterOuterAlt(_localctx, 4);
				{
				setState(44);
				match(LP);
				setState(45);
				((AggSourceContext)_localctx).nne = nonNumExp();
				setState(46);
				match(RP);

				            System.out.println("DEBUGA: AggSrc-BRACKET: "+(((AggSourceContext)_localctx).nne!=null?_input.getText(((AggSourceContext)_localctx).nne.start,((AggSourceContext)_localctx).nne.stop):null));
				            ((AggSourceContext)_localctx).aggregationSource =  new BracketNonNumExp(((AggSourceContext)_localctx).nne.nonNumericExpression);
				         
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

	public static class AggRangeContext extends ParserRuleContext {
		public IndexExp aggregationRange;
		public AggRangeContext ie1;
		public Token num;
		public Token iespec;
		public Token op;
		public AggRangeContext ie2;
		public TerminalNode PosNumber() { return getToken(NonNumCondAggregationParser.PosNumber, 0); }
		public TerminalNode SPEC() { return getToken(NonNumCondAggregationParser.SPEC, 0); }
		public List<AggRangeContext> aggRange() {
			return getRuleContexts(AggRangeContext.class);
		}
		public AggRangeContext aggRange(int i) {
			return getRuleContext(AggRangeContext.class,i);
		}
		public TerminalNode Plus() { return getToken(NonNumCondAggregationParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(NonNumCondAggregationParser.Minus, 0); }
		public AggRangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggRange; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NonNumCondAggregationListener ) ((NonNumCondAggregationListener)listener).enterAggRange(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NonNumCondAggregationListener ) ((NonNumCondAggregationListener)listener).exitAggRange(this);
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
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_aggRange, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PosNumber:
				{
				setState(52);
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
				setState(54);
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
			setState(65);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
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
					setState(58);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(59);
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
					setState(60);
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
				setState(67);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
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
		public TerminalNode LP() { return getToken(NonNumCondAggregationParser.LP, 0); }
		public TerminalNode RP() { return getToken(NonNumCondAggregationParser.RP, 0); }
		public List<AggConditionContext> aggCondition() {
			return getRuleContexts(AggConditionContext.class);
		}
		public AggConditionContext aggCondition(int i) {
			return getRuleContext(AggConditionContext.class,i);
		}
		public TerminalNode NEG() { return getToken(NonNumCondAggregationParser.NEG, 0); }
		public TerminalNode AND() { return getToken(NonNumCondAggregationParser.AND, 0); }
		public TerminalNode OR() { return getToken(NonNumCondAggregationParser.OR, 0); }
		public AggConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NonNumCondAggregationListener ) ((NonNumCondAggregationListener)listener).enterAggCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NonNumCondAggregationListener ) ((NonNumCondAggregationListener)listener).exitAggCondition(this);
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
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_aggCondition, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(69);
				((AggConditionContext)_localctx).ee = eventExp();

				            System.out.println("DEBUGA: EVENT: "+(((AggConditionContext)_localctx).ee!=null?_input.getText(((AggConditionContext)_localctx).ee.start,((AggConditionContext)_localctx).ee.stop):null));
				            ((AggConditionContext)_localctx).aggregationCondition =  ((AggConditionContext)_localctx).ee.eventExpression;
				        
				}
				break;
			case 2:
				{
				setState(72);
				match(LP);
				setState(73);
				((AggConditionContext)_localctx).f = aggCondition(0);
				setState(74);
				match(RP);

				            System.out.println("DEBUGA: BRACKET: "+(((AggConditionContext)_localctx).f!=null?_input.getText(((AggConditionContext)_localctx).f.start,((AggConditionContext)_localctx).f.stop):null));
				            ((AggConditionContext)_localctx).aggregationCondition =  new BracketFormula(((AggConditionContext)_localctx).f.aggregationCondition);
				        
				}
				break;
			case 3:
				{
				setState(77);
				match(NEG);
				setState(78);
				((AggConditionContext)_localctx).f = aggCondition(2);

				            System.out.println("DEBUGA: UNARY Formula: "+(((AggConditionContext)_localctx).f!=null?_input.getText(((AggConditionContext)_localctx).f.start,((AggConditionContext)_localctx).f.stop):null));
				            ((AggConditionContext)_localctx).aggregationCondition =  new FormulaNEG(((AggConditionContext)_localctx).f.aggregationCondition);;
				        
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(90);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
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
					setState(83);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(84);
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
					setState(85);
					((AggConditionContext)_localctx).f2 = aggCondition(2);

					                      System.out.println("DEBUGA: Binary Formula: "+(((AggConditionContext)_localctx).f1!=null?_input.getText(((AggConditionContext)_localctx).f1.start,((AggConditionContext)_localctx).f1.stop):null)+", "+(((AggConditionContext)_localctx).cop!=null?((AggConditionContext)_localctx).cop.getText():null)+", "+(((AggConditionContext)_localctx).f2!=null?_input.getText(((AggConditionContext)_localctx).f2.start,((AggConditionContext)_localctx).f2.stop):null));
					                      
					                      if((((AggConditionContext)_localctx).cop!=null?((AggConditionContext)_localctx).cop.getText():null).equals("||"))
					                          ((AggConditionContext)_localctx).aggregationCondition =  new FormulaOR(((AggConditionContext)_localctx).f1.aggregationCondition, ((AggConditionContext)_localctx).f2.aggregationCondition);
					                      else if((((AggConditionContext)_localctx).cop!=null?((AggConditionContext)_localctx).cop.getText():null).equals("&&"))
					                          ((AggConditionContext)_localctx).aggregationCondition =  new FormulaAND(((AggConditionContext)_localctx).f1.aggregationCondition, ((AggConditionContext)_localctx).f2.aggregationCondition);
					                  
					}
					} 
				}
				setState(92);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
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
		public TerminalNode True() { return getToken(NonNumCondAggregationParser.True, 0); }
		public TerminalNode False() { return getToken(NonNumCondAggregationParser.False, 0); }
		public TerminalNode LP() { return getToken(NonNumCondAggregationParser.LP, 0); }
		public TerminalNode RP() { return getToken(NonNumCondAggregationParser.RP, 0); }
		public EventExpContext eventExp() {
			return getRuleContext(EventExpContext.class,0);
		}
		public List<NonNumExpContext> nonNumExp() {
			return getRuleContexts(NonNumExpContext.class);
		}
		public NonNumExpContext nonNumExp(int i) {
			return getRuleContext(NonNumExpContext.class,i);
		}
		public TerminalNode EQUAL() { return getToken(NonNumCondAggregationParser.EQUAL, 0); }
		public TerminalNode NOTEQUAL() { return getToken(NonNumCondAggregationParser.NOTEQUAL, 0); }
		public List<NumExpContext> numExp() {
			return getRuleContexts(NumExpContext.class);
		}
		public NumExpContext numExp(int i) {
			return getRuleContext(NumExpContext.class,i);
		}
		public TerminalNode GT() { return getToken(NonNumCondAggregationParser.GT, 0); }
		public TerminalNode GTE() { return getToken(NonNumCondAggregationParser.GTE, 0); }
		public TerminalNode LT() { return getToken(NonNumCondAggregationParser.LT, 0); }
		public TerminalNode LTE() { return getToken(NonNumCondAggregationParser.LTE, 0); }
		public EventExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eventExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NonNumCondAggregationListener ) ((NonNumCondAggregationListener)listener).enterEventExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NonNumCondAggregationListener ) ((NonNumCondAggregationListener)listener).exitEventExp(this);
		}
	}

	public final EventExpContext eventExp() throws RecognitionException {
		EventExpContext _localctx = new EventExpContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_eventExp);
		int _la;
		try {
			setState(110);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(93);
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
				setState(95);
				match(LP);
				setState(96);
				((EventExpContext)_localctx).ee = eventExp();
				setState(97);
				match(RP);

				            System.out.println("DEBUGA: EE-BRACKET: "+(((EventExpContext)_localctx).ee!=null?_input.getText(((EventExpContext)_localctx).ee.start,((EventExpContext)_localctx).ee.stop):null));
				            ((EventExpContext)_localctx).eventExpression =  new BracketEventExp(((EventExpContext)_localctx).ee.eventExpression);
				        
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(100);
				((EventExpContext)_localctx).nne1 = nonNumExp();
				setState(101);
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
				setState(102);
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
				setState(105);
				((EventExpContext)_localctx).ne1 = numExp(0);
				setState(106);
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
				setState(107);
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
		public TerminalNode String() { return getToken(NonNumCondAggregationParser.String, 0); }
		public TerminalNode True() { return getToken(NonNumCondAggregationParser.True, 0); }
		public TerminalNode False() { return getToken(NonNumCondAggregationParser.False, 0); }
		public QueryContext query() {
			return getRuleContext(QueryContext.class,0);
		}
		public TerminalNode LP() { return getToken(NonNumCondAggregationParser.LP, 0); }
		public TerminalNode RP() { return getToken(NonNumCondAggregationParser.RP, 0); }
		public NonNumExpContext nonNumExp() {
			return getRuleContext(NonNumExpContext.class,0);
		}
		public NonNumExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nonNumExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NonNumCondAggregationListener ) ((NonNumCondAggregationListener)listener).enterNonNumExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NonNumCondAggregationListener ) ((NonNumCondAggregationListener)listener).exitNonNumExp(this);
		}
	}

	public final NonNumExpContext nonNumExp() throws RecognitionException {
		NonNumExpContext _localctx = new NonNumExpContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_nonNumExp);
		int _la;
		try {
			setState(124);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case String:
				enterOuterAlt(_localctx, 1);
				{
				setState(112);
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
				setState(114);
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
				setState(116);
				((NonNumExpContext)_localctx).nneQuery = query();

				            System.out.println("DEBUGA: NonNE-Query: "+(((NonNumExpContext)_localctx).nneQuery!=null?_input.getText(((NonNumExpContext)_localctx).nneQuery.start,((NonNumExpContext)_localctx).nneQuery.stop):null));
				            ((NonNumExpContext)_localctx).nonNumericExpression =  ((NonNumExpContext)_localctx).nneQuery.attAccessor;
				         
				}
				break;
			case LP:
				enterOuterAlt(_localctx, 4);
				{
				setState(119);
				match(LP);
				setState(120);
				((NonNumExpContext)_localctx).nne = nonNumExp();
				setState(121);
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
		public TerminalNode LP() { return getToken(NonNumCondAggregationParser.LP, 0); }
		public TerminalNode RP() { return getToken(NonNumCondAggregationParser.RP, 0); }
		public List<NumExpContext> numExp() {
			return getRuleContexts(NumExpContext.class);
		}
		public NumExpContext numExp(int i) {
			return getRuleContext(NumExpContext.class,i);
		}
		public TerminalNode Plus() { return getToken(NonNumCondAggregationParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(NonNumCondAggregationParser.Minus, 0); }
		public NumExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NonNumCondAggregationListener ) ((NonNumCondAggregationListener)listener).enterNumExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NonNumCondAggregationListener ) ((NonNumCondAggregationListener)listener).exitNumExp(this);
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
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_numExp, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(127);
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
				setState(130);
				((NumExpContext)_localctx).ip = indexExp(0);

				            System.out.println("DEBUGA: NE-IE: "+(((NumExpContext)_localctx).ip!=null?_input.getText(((NumExpContext)_localctx).ip.start,((NumExpContext)_localctx).ip.stop):null));
				            ((NumExpContext)_localctx).numericExpression =  ((NumExpContext)_localctx).ip.indexExpression;
				      
				}
				break;
			case 3:
				{
				setState(133);
				((NumExpContext)_localctx).neQuery = queryNumeric();

				            System.out.println("DEBUGA: NE-Query: "+(((NumExpContext)_localctx).neQuery!=null?_input.getText(((NumExpContext)_localctx).neQuery.start,((NumExpContext)_localctx).neQuery.stop):null));
				            ((NumExpContext)_localctx).numericExpression =  ((NumExpContext)_localctx).neQuery.attAccessorNumeric;
				      
				}
				break;
			case 4:
				{
				setState(136);
				match(LP);
				setState(137);
				((NumExpContext)_localctx).ne5 = numExp(0);
				setState(138);
				match(RP);

				            System.out.println("DEBUGA: EE-BRACKET: "+(((NumExpContext)_localctx).ne5!=null?_input.getText(((NumExpContext)_localctx).ne5.start,((NumExpContext)_localctx).ne5.stop):null));
				            ((NumExpContext)_localctx).numericExpression =  new BracketNumExp(((NumExpContext)_localctx).ne5.numericExpression);
				      
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(150);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
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
					setState(143);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(144);
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
					setState(145);
					((NumExpContext)_localctx).ne2 = numExp(4);

					                      System.out.println("DEBUGA: NE-Arithmetic: "+(((NumExpContext)_localctx).ne1!=null?_input.getText(((NumExpContext)_localctx).ne1.start,((NumExpContext)_localctx).ne1.stop):null)+", "+(((NumExpContext)_localctx).op!=null?((NumExpContext)_localctx).op.getText():null)+", "+(((NumExpContext)_localctx).ne2!=null?_input.getText(((NumExpContext)_localctx).ne2.start,((NumExpContext)_localctx).ne2.stop):null));
					                      if((((NumExpContext)_localctx).op!=null?((NumExpContext)_localctx).op.getText():null).equals("+"))
					                          ((NumExpContext)_localctx).numericExpression =  new NumExpBinary(((NumExpContext)_localctx).ne1.numericExpression, Const.ArithmeticOperator.PLUS, ((NumExpContext)_localctx).ne2.numericExpression);
					                      else if((((NumExpContext)_localctx).op!=null?((NumExpContext)_localctx).op.getText():null).equals("-"))
					                          ((NumExpContext)_localctx).numericExpression =  new NumExpBinary(((NumExpContext)_localctx).ne1.numericExpression, Const.ArithmeticOperator.MINUS, ((NumExpContext)_localctx).ne2.numericExpression);
					                
					}
					} 
				}
				setState(152);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
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
		public TerminalNode PosNumber() { return getToken(NonNumCondAggregationParser.PosNumber, 0); }
		public TerminalNode SPEC() { return getToken(NonNumCondAggregationParser.SPEC, 0); }
		public TerminalNode Var() { return getToken(NonNumCondAggregationParser.Var, 0); }
		public List<IndexExpContext> indexExp() {
			return getRuleContexts(IndexExpContext.class);
		}
		public IndexExpContext indexExp(int i) {
			return getRuleContext(IndexExpContext.class,i);
		}
		public TerminalNode Plus() { return getToken(NonNumCondAggregationParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(NonNumCondAggregationParser.Minus, 0); }
		public IndexExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NonNumCondAggregationListener ) ((NonNumCondAggregationListener)listener).enterIndexExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NonNumCondAggregationListener ) ((NonNumCondAggregationListener)listener).exitIndexExp(this);
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
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_indexExp, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PosNumber:
				{
				setState(154);
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
				setState(156);
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
				setState(158);
				((IndexExpContext)_localctx).var = match(Var);

				            System.out.println("DEBUGA: IE-Var: "+(((IndexExpContext)_localctx).var!=null?((IndexExpContext)_localctx).var.getText():null));
				            ((IndexExpContext)_localctx).indexExpression =  new IndexExpVar((((IndexExpContext)_localctx).var!=null?((IndexExpContext)_localctx).var.getText():null));
				        
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(169);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
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
					setState(162);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(163);
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
					setState(164);
					((IndexExpContext)_localctx).ie2 = indexExp(4);

					                      System.out.println("DEBUGA: IE-Arithmetic: "+(((IndexExpContext)_localctx).ie1!=null?_input.getText(((IndexExpContext)_localctx).ie1.start,((IndexExpContext)_localctx).ie1.stop):null)+", "+(((IndexExpContext)_localctx).op!=null?((IndexExpContext)_localctx).op.getText():null)+", "+(((IndexExpContext)_localctx).ie2!=null?_input.getText(((IndexExpContext)_localctx).ie2.start,((IndexExpContext)_localctx).ie2.stop):null));
					                      
					                      if((((IndexExpContext)_localctx).op!=null?((IndexExpContext)_localctx).op.getText():null).equals("+"))
					                          ((IndexExpContext)_localctx).indexExpression =  new IndexExpBinary(((IndexExpContext)_localctx).ie1.indexExpression, Const.ArithmeticOperator.PLUS, ((IndexExpContext)_localctx).ie2.indexExpression);
					                      else if((((IndexExpContext)_localctx).op!=null?((IndexExpContext)_localctx).op.getText():null).equals("-"))
					                          ((IndexExpContext)_localctx).indexExpression =  new IndexExpBinary(((IndexExpContext)_localctx).ie1.indexExpression, Const.ArithmeticOperator.MINUS, ((IndexExpContext)_localctx).ie2.indexExpression);
					                  
					}
					} 
				}
				setState(171);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
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
		public TerminalNode Qf() { return getToken(NonNumCondAggregationParser.Qf, 0); }
		public IndexExpContext indexExp() {
			return getRuleContext(IndexExpContext.class,0);
		}
		public TerminalNode AttName() { return getToken(NonNumCondAggregationParser.AttName, 0); }
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NonNumCondAggregationListener ) ((NonNumCondAggregationListener)listener).enterQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NonNumCondAggregationListener ) ((NonNumCondAggregationListener)listener).exitQuery(this);
		}
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_query);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			((QueryContext)_localctx).q = match(Qf);
			setState(173);
			((QueryContext)_localctx).idx = indexExp(0);
			setState(174);
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
		public TerminalNode Qf() { return getToken(NonNumCondAggregationParser.Qf, 0); }
		public IndexExpContext indexExp() {
			return getRuleContext(IndexExpContext.class,0);
		}
		public TerminalNode AttName() { return getToken(NonNumCondAggregationParser.AttName, 0); }
		public QueryNumericContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_queryNumeric; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NonNumCondAggregationListener ) ((NonNumCondAggregationListener)listener).enterQueryNumeric(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NonNumCondAggregationListener ) ((NonNumCondAggregationListener)listener).exitQueryNumeric(this);
		}
	}

	public final QueryNumericContext queryNumeric() throws RecognitionException {
		QueryNumericContext _localctx = new QueryNumericContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_queryNumeric);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			((QueryNumericContext)_localctx).q = match(Qf);
			setState(178);
			((QueryNumericContext)_localctx).idx = indexExp(0);
			setState(179);
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
		public TerminalNode PosNumber() { return getToken(NonNumCondAggregationParser.PosNumber, 0); }
		public RealNumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_realNumber; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NonNumCondAggregationListener ) ((NonNumCondAggregationListener)listener).enterRealNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NonNumCondAggregationListener ) ((NonNumCondAggregationListener)listener).exitRealNumber(this);
		}
	}

	public final RealNumberContext realNumber() throws RecognitionException {
		RealNumberContext _localctx = new RealNumberContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_realNumber);
		try {
			setState(190);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(184);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case Minus:
					{
					setState(182);
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
				setState(186);
				((RealNumberContext)_localctx).p = match(PosNumber);

				                    
				       ((RealNumberContext)_localctx).num =  '-' + (((RealNumberContext)_localctx).p!=null?((RealNumberContext)_localctx).p.getText():null);
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(188);
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
		case 3:
			return aggRange_sempred((AggRangeContext)_localctx, predIndex);
		case 4:
			return aggCondition_sempred((AggConditionContext)_localctx, predIndex);
		case 7:
			return numExp_sempred((NumExpContext)_localctx, predIndex);
		case 8:
			return indexExp_sempred((IndexExpContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean aggRange_sempred(AggRangeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean aggCondition_sempred(AggConditionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean numExp_sempred(NumExpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 3);
		}
		return true;
	}
	private boolean indexExp_sempred(IndexExpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3!\u00c3\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\64\n\4\3\5\3\5"+
		"\3\5\3\5\3\5\5\5;\n\5\3\5\3\5\3\5\3\5\3\5\7\5B\n\5\f\5\16\5E\13\5\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6T\n\6\3\6\3\6\3\6"+
		"\3\6\3\6\7\6[\n\6\f\6\16\6^\13\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7q\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\5\b\177\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\5\t\u0090\n\t\3\t\3\t\3\t\3\t\3\t\7\t\u0097\n\t\f"+
		"\t\16\t\u009a\13\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00a3\n\n\3\n\3\n\3"+
		"\n\3\n\3\n\7\n\u00aa\n\n\f\n\16\n\u00ad\13\n\3\13\3\13\3\13\3\13\3\13"+
		"\3\f\3\f\3\f\3\f\3\f\3\r\3\r\5\r\u00bb\n\r\3\r\3\r\3\r\3\r\5\r\u00c1\n"+
		"\r\3\r\2\6\b\n\20\22\16\2\4\6\b\n\f\16\20\22\24\26\30\2\7\3\2\23\24\3"+
		"\2\16\17\3\2\3\4\3\2\b\t\3\2\b\r\2\u00cd\2\32\3\2\2\2\4\36\3\2\2\2\6\63"+
		"\3\2\2\2\b:\3\2\2\2\nS\3\2\2\2\fp\3\2\2\2\16~\3\2\2\2\20\u008f\3\2\2\2"+
		"\22\u00a2\3\2\2\2\24\u00ae\3\2\2\2\26\u00b3\3\2\2\2\30\u00c0\3\2\2\2\32"+
		"\33\5\4\3\2\33\34\7\2\2\3\34\35\b\2\1\2\35\3\3\2\2\2\36\37\5\6\4\2\37"+
		" \7 \2\2 !\5\b\5\2!\"\7!\2\2\"#\5\b\5\2#$\7 \2\2$%\5\n\6\2%&\b\3\1\2&"+
		"\5\3\2\2\2\'(\7\22\2\2(\64\b\4\1\2)*\t\2\2\2*\64\b\4\1\2+,\5\24\13\2,"+
		"-\b\4\1\2-\64\3\2\2\2./\7\26\2\2/\60\5\16\b\2\60\61\7\27\2\2\61\62\b\4"+
		"\1\2\62\64\3\2\2\2\63\'\3\2\2\2\63)\3\2\2\2\63+\3\2\2\2\63.\3\2\2\2\64"+
		"\7\3\2\2\2\65\66\b\5\1\2\66\67\7\37\2\2\67;\b\5\1\289\7\32\2\29;\b\5\1"+
		"\2:\65\3\2\2\2:8\3\2\2\2;C\3\2\2\2<=\f\4\2\2=>\t\3\2\2>?\5\b\5\5?@\b\5"+
		"\1\2@B\3\2\2\2A<\3\2\2\2BE\3\2\2\2CA\3\2\2\2CD\3\2\2\2D\t\3\2\2\2EC\3"+
		"\2\2\2FG\b\6\1\2GH\5\f\7\2HI\b\6\1\2IT\3\2\2\2JK\7\26\2\2KL\5\n\6\2LM"+
		"\7\27\2\2MN\b\6\1\2NT\3\2\2\2OP\7\5\2\2PQ\5\n\6\4QR\b\6\1\2RT\3\2\2\2"+
		"SF\3\2\2\2SJ\3\2\2\2SO\3\2\2\2T\\\3\2\2\2UV\f\3\2\2VW\t\4\2\2WX\5\n\6"+
		"\4XY\b\6\1\2Y[\3\2\2\2ZU\3\2\2\2[^\3\2\2\2\\Z\3\2\2\2\\]\3\2\2\2]\13\3"+
		"\2\2\2^\\\3\2\2\2_`\t\2\2\2`q\b\7\1\2ab\7\26\2\2bc\5\f\7\2cd\7\27\2\2"+
		"de\b\7\1\2eq\3\2\2\2fg\5\16\b\2gh\t\5\2\2hi\5\16\b\2ij\b\7\1\2jq\3\2\2"+
		"\2kl\5\20\t\2lm\t\6\2\2mn\5\20\t\2no\b\7\1\2oq\3\2\2\2p_\3\2\2\2pa\3\2"+
		"\2\2pf\3\2\2\2pk\3\2\2\2q\r\3\2\2\2rs\7\22\2\2s\177\b\b\1\2tu\t\2\2\2"+
		"u\177\b\b\1\2vw\5\24\13\2wx\b\b\1\2x\177\3\2\2\2yz\7\26\2\2z{\5\16\b\2"+
		"{|\7\27\2\2|}\b\b\1\2}\177\3\2\2\2~r\3\2\2\2~t\3\2\2\2~v\3\2\2\2~y\3\2"+
		"\2\2\177\17\3\2\2\2\u0080\u0081\b\t\1\2\u0081\u0082\5\30\r\2\u0082\u0083"+
		"\b\t\1\2\u0083\u0090\3\2\2\2\u0084\u0085\5\22\n\2\u0085\u0086\b\t\1\2"+
		"\u0086\u0090\3\2\2\2\u0087\u0088\5\26\f\2\u0088\u0089\b\t\1\2\u0089\u0090"+
		"\3\2\2\2\u008a\u008b\7\26\2\2\u008b\u008c\5\20\t\2\u008c\u008d\7\27\2"+
		"\2\u008d\u008e\b\t\1\2\u008e\u0090\3\2\2\2\u008f\u0080\3\2\2\2\u008f\u0084"+
		"\3\2\2\2\u008f\u0087\3\2\2\2\u008f\u008a\3\2\2\2\u0090\u0098\3\2\2\2\u0091"+
		"\u0092\f\5\2\2\u0092\u0093\t\3\2\2\u0093\u0094\5\20\t\6\u0094\u0095\b"+
		"\t\1\2\u0095\u0097\3\2\2\2\u0096\u0091\3\2\2\2\u0097\u009a\3\2\2\2\u0098"+
		"\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099\21\3\2\2\2\u009a\u0098\3\2\2"+
		"\2\u009b\u009c\b\n\1\2\u009c\u009d\7\37\2\2\u009d\u00a3\b\n\1\2\u009e"+
		"\u009f\7\32\2\2\u009f\u00a3\b\n\1\2\u00a0\u00a1\7\31\2\2\u00a1\u00a3\b"+
		"\n\1\2\u00a2\u009b\3\2\2\2\u00a2\u009e\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a3"+
		"\u00ab\3\2\2\2\u00a4\u00a5\f\5\2\2\u00a5\u00a6\t\3\2\2\u00a6\u00a7\5\22"+
		"\n\6\u00a7\u00a8\b\n\1\2\u00a8\u00aa\3\2\2\2\u00a9\u00a4\3\2\2\2\u00aa"+
		"\u00ad\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\23\3\2\2"+
		"\2\u00ad\u00ab\3\2\2\2\u00ae\u00af\7\34\2\2\u00af\u00b0\5\22\n\2\u00b0"+
		"\u00b1\7\33\2\2\u00b1\u00b2\b\13\1\2\u00b2\25\3\2\2\2\u00b3\u00b4\7\34"+
		"\2\2\u00b4\u00b5\5\22\n\2\u00b5\u00b6\7\33\2\2\u00b6\u00b7\b\f\1\2\u00b7"+
		"\27\3\2\2\2\u00b8\u00bb\7\17\2\2\u00b9\u00bb\3\2\2\2\u00ba\u00b8\3\2\2"+
		"\2\u00ba\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00bd\7\37\2\2\u00bd"+
		"\u00c1\b\r\1\2\u00be\u00bf\7\37\2\2\u00bf\u00c1\b\r\1\2\u00c0\u00ba\3"+
		"\2\2\2\u00c0\u00be\3\2\2\2\u00c1\31\3\2\2\2\17\63:CS\\p~\u008f\u0098\u00a2"+
		"\u00ab\u00ba\u00c0";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}