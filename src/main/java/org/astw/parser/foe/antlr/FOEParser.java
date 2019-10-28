// Generated from FOE.g4 by ANTLR 4.7

package org.astw.parser.foe.antlr;

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
public class FOEParser extends Parser {
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
		RULE_parse = 0, RULE_formula = 1, RULE_quantification = 2, RULE_eventExp = 3, 
		RULE_nonNumExp = 4, RULE_numExp = 5, RULE_indexExp = 6, RULE_query = 7, 
		RULE_queryNumeric = 8, RULE_realNumber = 9;
	public static final String[] ruleNames = {
		"parse", "formula", "quantification", "eventExp", "nonNumExp", "numExp", 
		"indexExp", "query", "queryNumeric", "realNumber"
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
	public String getGrammarFileName() { return "FOE.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public FOEParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ParseContext extends ParserRuleContext {
		public Formula parsedFormula;
		public FormulaContext f;
		public TerminalNode EOF() { return getToken(FOEParser.EOF, 0); }
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public ParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOEListener ) ((FOEListener)listener).enterParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOEListener ) ((FOEListener)listener).exitParse(this);
		}
	}

	public final ParseContext parse() throws RecognitionException {
		ParseContext _localctx = new ParseContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_parse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20);
			((ParseContext)_localctx).f = formula(0);
			setState(21);
			match(EOF);

			            ((ParseContext)_localctx).parsedFormula =  ((ParseContext)_localctx).f.frm;
			        
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

	public static class FormulaContext extends ParserRuleContext {
		public Formula frm;
		public FormulaContext f1;
		public EventExpContext ee;
		public FormulaContext f;
		public Token q;
		public Token var;
		public QuantificationContext fq;
		public Token cop;
		public FormulaContext f2;
		public EventExpContext eventExp() {
			return getRuleContext(EventExpContext.class,0);
		}
		public TerminalNode LP() { return getToken(FOEParser.LP, 0); }
		public TerminalNode RP() { return getToken(FOEParser.RP, 0); }
		public List<FormulaContext> formula() {
			return getRuleContexts(FormulaContext.class);
		}
		public FormulaContext formula(int i) {
			return getRuleContext(FormulaContext.class,i);
		}
		public TerminalNode NEG() { return getToken(FOEParser.NEG, 0); }
		public TerminalNode DOT() { return getToken(FOEParser.DOT, 0); }
		public TerminalNode Var() { return getToken(FOEParser.Var, 0); }
		public QuantificationContext quantification() {
			return getRuleContext(QuantificationContext.class,0);
		}
		public TerminalNode FORALL() { return getToken(FOEParser.FORALL, 0); }
		public TerminalNode EXIST() { return getToken(FOEParser.EXIST, 0); }
		public TerminalNode AND() { return getToken(FOEParser.AND, 0); }
		public TerminalNode OR() { return getToken(FOEParser.OR, 0); }
		public TerminalNode IMPL() { return getToken(FOEParser.IMPL, 0); }
		public FormulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formula; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOEListener ) ((FOEListener)listener).enterFormula(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOEListener ) ((FOEListener)listener).exitFormula(this);
		}
	}

	public final FormulaContext formula() throws RecognitionException {
		return formula(0);
	}

	private FormulaContext formula(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		FormulaContext _localctx = new FormulaContext(_ctx, _parentState);
		FormulaContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_formula, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(25);
				((FormulaContext)_localctx).ee = eventExp();

				            System.out.println("DEBUGA: EVENT: "+(((FormulaContext)_localctx).ee!=null?_input.getText(((FormulaContext)_localctx).ee.start,((FormulaContext)_localctx).ee.stop):null));
				            ((FormulaContext)_localctx).frm =  ((FormulaContext)_localctx).ee.eventExpression;
				        
				}
				break;
			case 2:
				{
				setState(28);
				match(LP);
				setState(29);
				((FormulaContext)_localctx).f = formula(0);
				setState(30);
				match(RP);

				            System.out.println("DEBUGA: BRACKET: "+(((FormulaContext)_localctx).f!=null?_input.getText(((FormulaContext)_localctx).f.start,((FormulaContext)_localctx).f.stop):null));
				            ((FormulaContext)_localctx).frm =  new BracketFormula(((FormulaContext)_localctx).f.frm);
				        
				}
				break;
			case 3:
				{
				setState(33);
				match(NEG);
				setState(34);
				((FormulaContext)_localctx).f = formula(4);

				            System.out.println("DEBUGA: UNARY Formula: "+(((FormulaContext)_localctx).f!=null?_input.getText(((FormulaContext)_localctx).f.start,((FormulaContext)_localctx).f.stop):null));
				            ((FormulaContext)_localctx).frm =  new FormulaNEG(((FormulaContext)_localctx).f.frm);;
				        
				}
				break;
			case 4:
				{
				setState(37);
				((FormulaContext)_localctx).q = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==EXIST || _la==FORALL) ) {
					((FormulaContext)_localctx).q = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(38);
				((FormulaContext)_localctx).var = match(Var);
				setState(39);
				match(DOT);
				setState(40);
				((FormulaContext)_localctx).fq = quantification();
				 
				            System.out.println("DEBUGA: Quantification: "+(((FormulaContext)_localctx).q!=null?((FormulaContext)_localctx).q.getText():null)+", "+(((FormulaContext)_localctx).var!=null?((FormulaContext)_localctx).var.getText():null)+", "+(((FormulaContext)_localctx).fq!=null?_input.getText(((FormulaContext)_localctx).fq.start,((FormulaContext)_localctx).fq.stop):null));

				            if((((FormulaContext)_localctx).q!=null?((FormulaContext)_localctx).q.getText():null).equals("FORALL"))
				                ((FormulaContext)_localctx).frm =  new FormulaFORALL((((FormulaContext)_localctx).var!=null?((FormulaContext)_localctx).var.getText():null), ((FormulaContext)_localctx).fq.frm);
				            else if((((FormulaContext)_localctx).q!=null?((FormulaContext)_localctx).q.getText():null).equals("EXISTS"))
				                ((FormulaContext)_localctx).frm =  new FormulaEXISTS((((FormulaContext)_localctx).var!=null?((FormulaContext)_localctx).var.getText():null), ((FormulaContext)_localctx).fq.frm);
				        
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(57);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(55);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
					case 1:
						{
						_localctx = new FormulaContext(_parentctx, _parentState);
						_localctx.f1 = _prevctx;
						_localctx.f1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_formula);
						setState(45);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(46);
						((FormulaContext)_localctx).cop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==OR || _la==AND) ) {
							((FormulaContext)_localctx).cop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(47);
						((FormulaContext)_localctx).f2 = formula(4);

						                      System.out.println("DEBUGA: Binary Formula: "+(((FormulaContext)_localctx).f1!=null?_input.getText(((FormulaContext)_localctx).f1.start,((FormulaContext)_localctx).f1.stop):null)+", "+(((FormulaContext)_localctx).cop!=null?((FormulaContext)_localctx).cop.getText():null)+", "+(((FormulaContext)_localctx).f2!=null?_input.getText(((FormulaContext)_localctx).f2.start,((FormulaContext)_localctx).f2.stop):null));
						                      
						                      if((((FormulaContext)_localctx).cop!=null?((FormulaContext)_localctx).cop.getText():null).equals("||"))
						                          ((FormulaContext)_localctx).frm =  new FormulaOR(((FormulaContext)_localctx).f1.frm, ((FormulaContext)_localctx).f2.frm);
						                      else if((((FormulaContext)_localctx).cop!=null?((FormulaContext)_localctx).cop.getText():null).equals("&&"))
						                          ((FormulaContext)_localctx).frm =  new FormulaAND(((FormulaContext)_localctx).f1.frm, ((FormulaContext)_localctx).f2.frm);
						                  
						}
						break;
					case 2:
						{
						_localctx = new FormulaContext(_parentctx, _parentState);
						_localctx.f1 = _prevctx;
						_localctx.f1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_formula);
						setState(50);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(51);
						((FormulaContext)_localctx).cop = match(IMPL);
						setState(52);
						((FormulaContext)_localctx).f2 = formula(2);

						                      System.out.println("DEBUGA: Binary Formula: "+(((FormulaContext)_localctx).f1!=null?_input.getText(((FormulaContext)_localctx).f1.start,((FormulaContext)_localctx).f1.stop):null)+", "+(((FormulaContext)_localctx).cop!=null?((FormulaContext)_localctx).cop.getText():null)+", "+(((FormulaContext)_localctx).f2!=null?_input.getText(((FormulaContext)_localctx).f2.start,((FormulaContext)_localctx).f2.stop):null));
						                      ((FormulaContext)_localctx).frm =  new FormulaIMPL(((FormulaContext)_localctx).f1.frm, ((FormulaContext)_localctx).f2.frm);
						                  
						}
						break;
					}
					} 
				}
				setState(59);
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

	public static class QuantificationContext extends ParserRuleContext {
		public Formula frm;
		public FormulaContext f;
		public QuantificationContext fq;
		public Token q;
		public Token var;
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public TerminalNode LP() { return getToken(FOEParser.LP, 0); }
		public TerminalNode RP() { return getToken(FOEParser.RP, 0); }
		public QuantificationContext quantification() {
			return getRuleContext(QuantificationContext.class,0);
		}
		public TerminalNode DOT() { return getToken(FOEParser.DOT, 0); }
		public TerminalNode Var() { return getToken(FOEParser.Var, 0); }
		public TerminalNode FORALL() { return getToken(FOEParser.FORALL, 0); }
		public TerminalNode EXIST() { return getToken(FOEParser.EXIST, 0); }
		public QuantificationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quantification; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOEListener ) ((FOEListener)listener).enterQuantification(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOEListener ) ((FOEListener)listener).exitQuantification(this);
		}
	}

	public final QuantificationContext quantification() throws RecognitionException {
		QuantificationContext _localctx = new QuantificationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_quantification);
		int _la;
		try {
			setState(74);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(60);
				((QuantificationContext)_localctx).f = formula(0);

				            System.out.println("DEBUGA: QUANT - formula: "+(((QuantificationContext)_localctx).f!=null?_input.getText(((QuantificationContext)_localctx).f.start,((QuantificationContext)_localctx).f.stop):null));
				            ((QuantificationContext)_localctx).frm =  ((QuantificationContext)_localctx).f.frm;
				        
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(63);
				match(LP);
				setState(64);
				((QuantificationContext)_localctx).fq = quantification();
				setState(65);
				match(RP);

				            System.out.println("DEBUGA: QUANT - Quant: "+(((QuantificationContext)_localctx).fq!=null?_input.getText(((QuantificationContext)_localctx).fq.start,((QuantificationContext)_localctx).fq.stop):null));                          
				            ((QuantificationContext)_localctx).frm =  new BracketFormula(((QuantificationContext)_localctx).fq.frm);
				        
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(68);
				((QuantificationContext)_localctx).q = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==EXIST || _la==FORALL) ) {
					((QuantificationContext)_localctx).q = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(69);
				((QuantificationContext)_localctx).var = match(Var);
				setState(70);
				match(DOT);
				setState(71);
				((QuantificationContext)_localctx).fq = quantification();

				            System.out.println("DEBUGA: QUANT - recur: "+(((QuantificationContext)_localctx).q!=null?((QuantificationContext)_localctx).q.getText():null)+", "+(((QuantificationContext)_localctx).var!=null?((QuantificationContext)_localctx).var.getText():null)+", "+(((QuantificationContext)_localctx).fq!=null?_input.getText(((QuantificationContext)_localctx).fq.start,((QuantificationContext)_localctx).fq.stop):null));

				            if((((QuantificationContext)_localctx).q!=null?((QuantificationContext)_localctx).q.getText():null).equals("FORALL"))
				                ((QuantificationContext)_localctx).frm =  new FormulaFORALL((((QuantificationContext)_localctx).var!=null?((QuantificationContext)_localctx).var.getText():null), ((QuantificationContext)_localctx).fq.frm);
				            else if((((QuantificationContext)_localctx).q!=null?((QuantificationContext)_localctx).q.getText():null).equals("EXISTS"))
				                ((QuantificationContext)_localctx).frm =  new FormulaEXISTS((((QuantificationContext)_localctx).var!=null?((QuantificationContext)_localctx).var.getText():null), ((QuantificationContext)_localctx).fq.frm);
				        
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
		public TerminalNode True() { return getToken(FOEParser.True, 0); }
		public TerminalNode False() { return getToken(FOEParser.False, 0); }
		public TerminalNode LP() { return getToken(FOEParser.LP, 0); }
		public TerminalNode RP() { return getToken(FOEParser.RP, 0); }
		public EventExpContext eventExp() {
			return getRuleContext(EventExpContext.class,0);
		}
		public List<NonNumExpContext> nonNumExp() {
			return getRuleContexts(NonNumExpContext.class);
		}
		public NonNumExpContext nonNumExp(int i) {
			return getRuleContext(NonNumExpContext.class,i);
		}
		public TerminalNode EQUAL() { return getToken(FOEParser.EQUAL, 0); }
		public TerminalNode NOTEQUAL() { return getToken(FOEParser.NOTEQUAL, 0); }
		public List<NumExpContext> numExp() {
			return getRuleContexts(NumExpContext.class);
		}
		public NumExpContext numExp(int i) {
			return getRuleContext(NumExpContext.class,i);
		}
		public TerminalNode GT() { return getToken(FOEParser.GT, 0); }
		public TerminalNode GTE() { return getToken(FOEParser.GTE, 0); }
		public TerminalNode LT() { return getToken(FOEParser.LT, 0); }
		public TerminalNode LTE() { return getToken(FOEParser.LTE, 0); }
		public EventExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eventExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOEListener ) ((FOEListener)listener).enterEventExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOEListener ) ((FOEListener)listener).exitEventExp(this);
		}
	}

	public final EventExpContext eventExp() throws RecognitionException {
		EventExpContext _localctx = new EventExpContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_eventExp);
		int _la;
		try {
			setState(93);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(76);
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
				setState(78);
				match(LP);
				setState(79);
				((EventExpContext)_localctx).ee = eventExp();
				setState(80);
				match(RP);

				            System.out.println("DEBUGA: EE-BRACKET: "+(((EventExpContext)_localctx).ee!=null?_input.getText(((EventExpContext)_localctx).ee.start,((EventExpContext)_localctx).ee.stop):null));
				            ((EventExpContext)_localctx).eventExpression =  new BracketEventExp(((EventExpContext)_localctx).ee.eventExpression);
				        
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(83);
				((EventExpContext)_localctx).nne1 = nonNumExp();
				setState(84);
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
				setState(85);
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
				setState(88);
				((EventExpContext)_localctx).ne1 = numExp(0);
				setState(89);
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
				setState(90);
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
		public Token concatAgg;
		public NonNumExpContext nne;
		public TerminalNode String() { return getToken(FOEParser.String, 0); }
		public TerminalNode True() { return getToken(FOEParser.True, 0); }
		public TerminalNode False() { return getToken(FOEParser.False, 0); }
		public QueryContext query() {
			return getRuleContext(QueryContext.class,0);
		}
		public TerminalNode NON_NUMERIC_AGG_FUNC_CONCAT() { return getToken(FOEParser.NON_NUMERIC_AGG_FUNC_CONCAT, 0); }
		public TerminalNode LP() { return getToken(FOEParser.LP, 0); }
		public TerminalNode RP() { return getToken(FOEParser.RP, 0); }
		public NonNumExpContext nonNumExp() {
			return getRuleContext(NonNumExpContext.class,0);
		}
		public NonNumExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nonNumExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOEListener ) ((FOEListener)listener).enterNonNumExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOEListener ) ((FOEListener)listener).exitNonNumExp(this);
		}
	}

	public final NonNumExpContext nonNumExp() throws RecognitionException {
		NonNumExpContext _localctx = new NonNumExpContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_nonNumExp);
		int _la;
		try {
			setState(109);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case String:
				enterOuterAlt(_localctx, 1);
				{
				setState(95);
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
				setState(97);
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
				setState(99);
				((NonNumExpContext)_localctx).nneQuery = query();

				            System.out.println("DEBUGA: NonNE-Query: "+(((NonNumExpContext)_localctx).nneQuery!=null?_input.getText(((NonNumExpContext)_localctx).nneQuery.start,((NonNumExpContext)_localctx).nneQuery.stop):null));
				            ((NonNumExpContext)_localctx).nonNumericExpression =  ((NonNumExpContext)_localctx).nneQuery.attAccessor;
				         
				}
				break;
			case NON_NUMERIC_AGG_FUNC_CONCAT:
				enterOuterAlt(_localctx, 4);
				{
				setState(102);
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
				setState(104);
				match(LP);
				setState(105);
				((NonNumExpContext)_localctx).nne = nonNumExp();
				setState(106);
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
		public IndexExpContext indexExp() {
			return getRuleContext(IndexExpContext.class,0);
		}
		public QueryNumericContext queryNumeric() {
			return getRuleContext(QueryNumericContext.class,0);
		}
		public TerminalNode NUMERIC_AGG_FUNC_SUM() { return getToken(FOEParser.NUMERIC_AGG_FUNC_SUM, 0); }
		public TerminalNode NUMERIC_AGG_FUNC_AVG() { return getToken(FOEParser.NUMERIC_AGG_FUNC_AVG, 0); }
		public TerminalNode NUMERIC_AGG_FUNC_MIN() { return getToken(FOEParser.NUMERIC_AGG_FUNC_MIN, 0); }
		public TerminalNode NUMERIC_AGG_FUNC_MAX() { return getToken(FOEParser.NUMERIC_AGG_FUNC_MAX, 0); }
		public TerminalNode NUMERIC_AGG_FUNC_COUNT() { return getToken(FOEParser.NUMERIC_AGG_FUNC_COUNT, 0); }
		public TerminalNode NUMERIC_AGG_FUNC_COUNTVAL() { return getToken(FOEParser.NUMERIC_AGG_FUNC_COUNTVAL, 0); }
		public TerminalNode LP() { return getToken(FOEParser.LP, 0); }
		public TerminalNode RP() { return getToken(FOEParser.RP, 0); }
		public List<NumExpContext> numExp() {
			return getRuleContexts(NumExpContext.class);
		}
		public NumExpContext numExp(int i) {
			return getRuleContext(NumExpContext.class,i);
		}
		public TerminalNode Plus() { return getToken(FOEParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(FOEParser.Minus, 0); }
		public NumExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOEListener ) ((FOEListener)listener).enterNumExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOEListener ) ((FOEListener)listener).exitNumExp(this);
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
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_numExp, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(112);
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
				setState(115);
				((NumExpContext)_localctx).ip = indexExp(0);

				            System.out.println("DEBUGA: NE-IP: "+(((NumExpContext)_localctx).ip!=null?_input.getText(((NumExpContext)_localctx).ip.start,((NumExpContext)_localctx).ip.stop):null));
				            ((NumExpContext)_localctx).numericExpression =  ((NumExpContext)_localctx).ip.indexExpression;
				      
				}
				break;
			case 3:
				{
				setState(118);
				((NumExpContext)_localctx).neQuery = queryNumeric();

				            System.out.println("DEBUGA: NE-Query: "+(((NumExpContext)_localctx).neQuery!=null?_input.getText(((NumExpContext)_localctx).neQuery.start,((NumExpContext)_localctx).neQuery.stop):null));
				            ((NumExpContext)_localctx).numericExpression =  ((NumExpContext)_localctx).neQuery.attAccessorNumeric;
				      
				}
				break;
			case 4:
				{
				setState(121);
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
				                
				                System.out.println("DEBUGA: AggType: "+Const.NumAggregationType.SUM);

				                ((NumExpContext)_localctx).numericExpression =  nca;

				            }catch(Exception e){
				              notifyErrorListeners("ERROR: " + e.getMessage());
				            }
				            
				      
				}
				break;
			case 5:
				{
				setState(123);
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
			case 6:
				{
				setState(125);
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
			case 7:
				{
				setState(127);
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
			case 8:
				{
				setState(129);
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
			case 9:
				{
				setState(131);
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
			case 10:
				{
				setState(133);
				match(LP);
				setState(134);
				((NumExpContext)_localctx).ne5 = numExp(0);
				setState(135);
				match(RP);

				            System.out.println("DEBUGA: EE-BRACKET: "+(((NumExpContext)_localctx).ne5!=null?_input.getText(((NumExpContext)_localctx).ne5.start,((NumExpContext)_localctx).ne5.stop):null));
				            ((NumExpContext)_localctx).numericExpression =  new BracketNumExp(((NumExpContext)_localctx).ne5.numericExpression);
				      
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(147);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
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
					setState(140);
					if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
					setState(141);
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
					setState(142);
					((NumExpContext)_localctx).ne2 = numExp(10);

					                      System.out.println("DEBUGA: NE-Arithmetic: "+(((NumExpContext)_localctx).ne1!=null?_input.getText(((NumExpContext)_localctx).ne1.start,((NumExpContext)_localctx).ne1.stop):null)+", "+(((NumExpContext)_localctx).op!=null?((NumExpContext)_localctx).op.getText():null)+", "+(((NumExpContext)_localctx).ne2!=null?_input.getText(((NumExpContext)_localctx).ne2.start,((NumExpContext)_localctx).ne2.stop):null));
					                      if((((NumExpContext)_localctx).op!=null?((NumExpContext)_localctx).op.getText():null).equals("+"))
					                          ((NumExpContext)_localctx).numericExpression =  new NumExpBinary(((NumExpContext)_localctx).ne1.numericExpression, Const.ArithmeticOperator.PLUS, ((NumExpContext)_localctx).ne2.numericExpression);
					                      else if((((NumExpContext)_localctx).op!=null?((NumExpContext)_localctx).op.getText():null).equals("-"))
					                          ((NumExpContext)_localctx).numericExpression =  new NumExpBinary(((NumExpContext)_localctx).ne1.numericExpression, Const.ArithmeticOperator.MINUS, ((NumExpContext)_localctx).ne2.numericExpression);
					                
					}
					} 
				}
				setState(149);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
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
		public TerminalNode PosNumber() { return getToken(FOEParser.PosNumber, 0); }
		public TerminalNode SPEC() { return getToken(FOEParser.SPEC, 0); }
		public TerminalNode Var() { return getToken(FOEParser.Var, 0); }
		public List<IndexExpContext> indexExp() {
			return getRuleContexts(IndexExpContext.class);
		}
		public IndexExpContext indexExp(int i) {
			return getRuleContext(IndexExpContext.class,i);
		}
		public TerminalNode Plus() { return getToken(FOEParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(FOEParser.Minus, 0); }
		public IndexExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOEListener ) ((FOEListener)listener).enterIndexExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOEListener ) ((FOEListener)listener).exitIndexExp(this);
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
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_indexExp, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PosNumber:
				{
				setState(151);
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
				setState(153);
				((IndexExpContext)_localctx).iespec = match(SPEC);

				            System.out.println("DEBUGA: IP-SPEC: "+(((IndexExpContext)_localctx).iespec!=null?((IndexExpContext)_localctx).iespec.getText():null));
				            if((((IndexExpContext)_localctx).iespec!=null?((IndexExpContext)_localctx).iespec.getText():null).equals("CURR"))
				                ((IndexExpContext)_localctx).indexExpression =  new IndexExpSpec(Const.SpecialIndexType.CURR);
				            else if((((IndexExpContext)_localctx).iespec!=null?((IndexExpContext)_localctx).iespec.getText():null).equals("LAST"))
				                ((IndexExpContext)_localctx).indexExpression =  new IndexExpSpec(Const.SpecialIndexType.LAST);
				        
				}
				break;
			case Var:
				{
				setState(155);
				((IndexExpContext)_localctx).var = match(Var);

				            System.out.println("DEBUGA: IP-Var: "+(((IndexExpContext)_localctx).var!=null?((IndexExpContext)_localctx).var.getText():null));
				            ((IndexExpContext)_localctx).indexExpression =  new IndexExpVar((((IndexExpContext)_localctx).var!=null?((IndexExpContext)_localctx).var.getText():null));
				        
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(166);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
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
					setState(159);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(160);
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
					setState(161);
					((IndexExpContext)_localctx).ie2 = indexExp(4);

					                      System.out.println("DEBUGA: IP-Arithmetic: "+(((IndexExpContext)_localctx).ie1!=null?_input.getText(((IndexExpContext)_localctx).ie1.start,((IndexExpContext)_localctx).ie1.stop):null)+", "+(((IndexExpContext)_localctx).op!=null?((IndexExpContext)_localctx).op.getText():null)+", "+(((IndexExpContext)_localctx).ie2!=null?_input.getText(((IndexExpContext)_localctx).ie2.start,((IndexExpContext)_localctx).ie2.stop):null));
					                      
					                      if((((IndexExpContext)_localctx).op!=null?((IndexExpContext)_localctx).op.getText():null).equals("+"))
					                          ((IndexExpContext)_localctx).indexExpression =  new IndexExpBinary(((IndexExpContext)_localctx).ie1.indexExpression, Const.ArithmeticOperator.PLUS, ((IndexExpContext)_localctx).ie2.indexExpression);
					                      else if((((IndexExpContext)_localctx).op!=null?((IndexExpContext)_localctx).op.getText():null).equals("-"))
					                          ((IndexExpContext)_localctx).indexExpression =  new IndexExpBinary(((IndexExpContext)_localctx).ie1.indexExpression, Const.ArithmeticOperator.MINUS, ((IndexExpContext)_localctx).ie2.indexExpression);
					                  
					}
					} 
				}
				setState(168);
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

	public static class QueryContext extends ParserRuleContext {
		public AttributeAccessor attAccessor;
		public Token q;
		public IndexExpContext idx;
		public Token attName;
		public TerminalNode Qf() { return getToken(FOEParser.Qf, 0); }
		public IndexExpContext indexExp() {
			return getRuleContext(IndexExpContext.class,0);
		}
		public TerminalNode AttName() { return getToken(FOEParser.AttName, 0); }
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOEListener ) ((FOEListener)listener).enterQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOEListener ) ((FOEListener)listener).exitQuery(this);
		}
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_query);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			((QueryContext)_localctx).q = match(Qf);
			setState(170);
			((QueryContext)_localctx).idx = indexExp(0);
			setState(171);
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
		public TerminalNode Qf() { return getToken(FOEParser.Qf, 0); }
		public IndexExpContext indexExp() {
			return getRuleContext(IndexExpContext.class,0);
		}
		public TerminalNode AttName() { return getToken(FOEParser.AttName, 0); }
		public QueryNumericContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_queryNumeric; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOEListener ) ((FOEListener)listener).enterQueryNumeric(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOEListener ) ((FOEListener)listener).exitQueryNumeric(this);
		}
	}

	public final QueryNumericContext queryNumeric() throws RecognitionException {
		QueryNumericContext _localctx = new QueryNumericContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_queryNumeric);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			((QueryNumericContext)_localctx).q = match(Qf);
			setState(175);
			((QueryNumericContext)_localctx).idx = indexExp(0);
			setState(176);
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
		public TerminalNode PosNumber() { return getToken(FOEParser.PosNumber, 0); }
		public RealNumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_realNumber; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOEListener ) ((FOEListener)listener).enterRealNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOEListener ) ((FOEListener)listener).exitRealNumber(this);
		}
	}

	public final RealNumberContext realNumber() throws RecognitionException {
		RealNumberContext _localctx = new RealNumberContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_realNumber);
		try {
			setState(187);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(181);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case Minus:
					{
					setState(179);
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
				setState(183);
				((RealNumberContext)_localctx).p = match(PosNumber);

				                    
				       ((RealNumberContext)_localctx).num =  '-' + (((RealNumberContext)_localctx).p!=null?((RealNumberContext)_localctx).p.getText():null);
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(185);
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
		case 1:
			return formula_sempred((FormulaContext)_localctx, predIndex);
		case 5:
			return numExp_sempred((NumExpContext)_localctx, predIndex);
		case 6:
			return indexExp_sempred((IndexExpContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean formula_sempred(FormulaContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean numExp_sempred(NumExpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 9);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3(\u00c0\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3.\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\7\3:\n\3\f\3\16\3=\13\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\5\4M\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\5\5`\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\5\6p\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5"+
		"\7\u008d\n\7\3\7\3\7\3\7\3\7\3\7\7\7\u0094\n\7\f\7\16\7\u0097\13\7\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u00a0\n\b\3\b\3\b\3\b\3\b\3\b\7\b\u00a7\n"+
		"\b\f\b\16\b\u00aa\13\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3"+
		"\13\5\13\u00b8\n\13\3\13\3\13\3\13\3\13\5\13\u00be\n\13\3\13\2\5\4\f\16"+
		"\f\2\4\6\b\n\f\16\20\22\24\2\b\3\2\25\26\3\2\3\4\3\2\23\24\3\2\b\t\3\2"+
		"\b\r\3\2\16\17\2\u00d2\2\26\3\2\2\2\4-\3\2\2\2\6L\3\2\2\2\b_\3\2\2\2\n"+
		"o\3\2\2\2\f\u008c\3\2\2\2\16\u009f\3\2\2\2\20\u00ab\3\2\2\2\22\u00b0\3"+
		"\2\2\2\24\u00bd\3\2\2\2\26\27\5\4\3\2\27\30\7\2\2\3\30\31\b\2\1\2\31\3"+
		"\3\2\2\2\32\33\b\3\1\2\33\34\5\b\5\2\34\35\b\3\1\2\35.\3\2\2\2\36\37\7"+
		"\30\2\2\37 \5\4\3\2 !\7\31\2\2!\"\b\3\1\2\".\3\2\2\2#$\7\5\2\2$%\5\4\3"+
		"\6%&\b\3\1\2&.\3\2\2\2\'(\t\2\2\2()\7\33\2\2)*\7\27\2\2*+\5\6\4\2+,\b"+
		"\3\1\2,.\3\2\2\2-\32\3\2\2\2-\36\3\2\2\2-#\3\2\2\2-\'\3\2\2\2.;\3\2\2"+
		"\2/\60\f\5\2\2\60\61\t\3\2\2\61\62\5\4\3\6\62\63\b\3\1\2\63:\3\2\2\2\64"+
		"\65\f\3\2\2\65\66\7\6\2\2\66\67\5\4\3\4\678\b\3\1\28:\3\2\2\29/\3\2\2"+
		"\29\64\3\2\2\2:=\3\2\2\2;9\3\2\2\2;<\3\2\2\2<\5\3\2\2\2=;\3\2\2\2>?\5"+
		"\4\3\2?@\b\4\1\2@M\3\2\2\2AB\7\30\2\2BC\5\6\4\2CD\7\31\2\2DE\b\4\1\2E"+
		"M\3\2\2\2FG\t\2\2\2GH\7\33\2\2HI\7\27\2\2IJ\5\6\4\2JK\b\4\1\2KM\3\2\2"+
		"\2L>\3\2\2\2LA\3\2\2\2LF\3\2\2\2M\7\3\2\2\2NO\t\4\2\2O`\b\5\1\2PQ\7\30"+
		"\2\2QR\5\b\5\2RS\7\31\2\2ST\b\5\1\2T`\3\2\2\2UV\5\n\6\2VW\t\5\2\2WX\5"+
		"\n\6\2XY\b\5\1\2Y`\3\2\2\2Z[\5\f\7\2[\\\t\6\2\2\\]\5\f\7\2]^\b\5\1\2^"+
		"`\3\2\2\2_N\3\2\2\2_P\3\2\2\2_U\3\2\2\2_Z\3\2\2\2`\t\3\2\2\2ab\7\22\2"+
		"\2bp\b\6\1\2cd\t\4\2\2dp\b\6\1\2ef\5\20\t\2fg\b\6\1\2gp\3\2\2\2hi\7(\2"+
		"\2ip\b\6\1\2jk\7\30\2\2kl\5\n\6\2lm\7\31\2\2mn\b\6\1\2np\3\2\2\2oa\3\2"+
		"\2\2oc\3\2\2\2oe\3\2\2\2oh\3\2\2\2oj\3\2\2\2p\13\3\2\2\2qr\b\7\1\2rs\5"+
		"\24\13\2st\b\7\1\2t\u008d\3\2\2\2uv\5\16\b\2vw\b\7\1\2w\u008d\3\2\2\2"+
		"xy\5\22\n\2yz\b\7\1\2z\u008d\3\2\2\2{|\7\"\2\2|\u008d\b\7\1\2}~\7#\2\2"+
		"~\u008d\b\7\1\2\177\u0080\7%\2\2\u0080\u008d\b\7\1\2\u0081\u0082\7$\2"+
		"\2\u0082\u008d\b\7\1\2\u0083\u0084\7&\2\2\u0084\u008d\b\7\1\2\u0085\u0086"+
		"\7\'\2\2\u0086\u008d\b\7\1\2\u0087\u0088\7\30\2\2\u0088\u0089\5\f\7\2"+
		"\u0089\u008a\7\31\2\2\u008a\u008b\b\7\1\2\u008b\u008d\3\2\2\2\u008cq\3"+
		"\2\2\2\u008cu\3\2\2\2\u008cx\3\2\2\2\u008c{\3\2\2\2\u008c}\3\2\2\2\u008c"+
		"\177\3\2\2\2\u008c\u0081\3\2\2\2\u008c\u0083\3\2\2\2\u008c\u0085\3\2\2"+
		"\2\u008c\u0087\3\2\2\2\u008d\u0095\3\2\2\2\u008e\u008f\f\13\2\2\u008f"+
		"\u0090\t\7\2\2\u0090\u0091\5\f\7\f\u0091\u0092\b\7\1\2\u0092\u0094\3\2"+
		"\2\2\u0093\u008e\3\2\2\2\u0094\u0097\3\2\2\2\u0095\u0093\3\2\2\2\u0095"+
		"\u0096\3\2\2\2\u0096\r\3\2\2\2\u0097\u0095\3\2\2\2\u0098\u0099\b\b\1\2"+
		"\u0099\u009a\7!\2\2\u009a\u00a0\b\b\1\2\u009b\u009c\7\34\2\2\u009c\u00a0"+
		"\b\b\1\2\u009d\u009e\7\33\2\2\u009e\u00a0\b\b\1\2\u009f\u0098\3\2\2\2"+
		"\u009f\u009b\3\2\2\2\u009f\u009d\3\2\2\2\u00a0\u00a8\3\2\2\2\u00a1\u00a2"+
		"\f\5\2\2\u00a2\u00a3\t\7\2\2\u00a3\u00a4\5\16\b\6\u00a4\u00a5\b\b\1\2"+
		"\u00a5\u00a7\3\2\2\2\u00a6\u00a1\3\2\2\2\u00a7\u00aa\3\2\2\2\u00a8\u00a6"+
		"\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\17\3\2\2\2\u00aa\u00a8\3\2\2\2\u00ab"+
		"\u00ac\7\36\2\2\u00ac\u00ad\5\16\b\2\u00ad\u00ae\7\35\2\2\u00ae\u00af"+
		"\b\t\1\2\u00af\21\3\2\2\2\u00b0\u00b1\7\36\2\2\u00b1\u00b2\5\16\b\2\u00b2"+
		"\u00b3\7\35\2\2\u00b3\u00b4\b\n\1\2\u00b4\23\3\2\2\2\u00b5\u00b8\7\17"+
		"\2\2\u00b6\u00b8\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b7\u00b6\3\2\2\2\u00b8"+
		"\u00b9\3\2\2\2\u00b9\u00ba\7!\2\2\u00ba\u00be\b\13\1\2\u00bb\u00bc\7!"+
		"\2\2\u00bc\u00be\b\13\1\2\u00bd\u00b7\3\2\2\2\u00bd\u00bb\3\2\2\2\u00be"+
		"\25\3\2\2\2\16-9;L_o\u008c\u0095\u009f\u00a8\u00b7\u00bd";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}