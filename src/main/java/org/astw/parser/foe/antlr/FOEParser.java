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
		AttName=27, Qf=28, Quote=29, NEWLINE=30, Number=31;
	public static final int
		RULE_parse = 0, RULE_formula = 1, RULE_quantification = 2, RULE_eventExp = 3, 
		RULE_nonNumExp = 4, RULE_numExp = 5, RULE_indexExp = 6, RULE_query = 7, 
		RULE_queryNumeric = 8;
	public static final String[] ruleNames = {
		"parse", "formula", "quantification", "eventExp", "nonNumExp", "numExp", 
		"indexExp", "query", "queryNumeric"
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
		"Qf", "Quote", "NEWLINE", "Number"
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
			setState(18);
			((ParseContext)_localctx).f = formula(0);
			setState(19);
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
			setState(41);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(23);
				((FormulaContext)_localctx).ee = eventExp(0);

				            System.out.println("DEBUGA: EVENT: "+(((FormulaContext)_localctx).ee!=null?_input.getText(((FormulaContext)_localctx).ee.start,((FormulaContext)_localctx).ee.stop):null));
				            ((FormulaContext)_localctx).frm =  ((FormulaContext)_localctx).ee.eventExpression;
				        
				}
				break;
			case 2:
				{
				setState(26);
				match(LP);
				setState(27);
				((FormulaContext)_localctx).f = formula(0);
				setState(28);
				match(RP);

				            System.out.println("DEBUGA: BRACKET: "+(((FormulaContext)_localctx).f!=null?_input.getText(((FormulaContext)_localctx).f.start,((FormulaContext)_localctx).f.stop):null));
				            ((FormulaContext)_localctx).frm =  new BracketFormula(((FormulaContext)_localctx).f.frm);
				        
				}
				break;
			case 3:
				{
				setState(31);
				match(NEG);
				setState(32);
				((FormulaContext)_localctx).f = formula(4);

				            System.out.println("DEBUGA: UNARY Formula: "+(((FormulaContext)_localctx).f!=null?_input.getText(((FormulaContext)_localctx).f.start,((FormulaContext)_localctx).f.stop):null));
				            ((FormulaContext)_localctx).frm =  new FormulaNEG(((FormulaContext)_localctx).f.frm);;
				        
				}
				break;
			case 4:
				{
				setState(35);
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
				setState(36);
				((FormulaContext)_localctx).var = match(Var);
				setState(37);
				match(DOT);
				setState(38);
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
			setState(55);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(53);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
					case 1:
						{
						_localctx = new FormulaContext(_parentctx, _parentState);
						_localctx.f1 = _prevctx;
						_localctx.f1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_formula);
						setState(43);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(44);
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
						setState(45);
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
						setState(48);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(49);
						((FormulaContext)_localctx).cop = match(IMPL);
						setState(50);
						((FormulaContext)_localctx).f2 = formula(2);

						                      System.out.println("DEBUGA: Binary Formula: "+(((FormulaContext)_localctx).f1!=null?_input.getText(((FormulaContext)_localctx).f1.start,((FormulaContext)_localctx).f1.stop):null)+", "+(((FormulaContext)_localctx).cop!=null?((FormulaContext)_localctx).cop.getText():null)+", "+(((FormulaContext)_localctx).f2!=null?_input.getText(((FormulaContext)_localctx).f2.start,((FormulaContext)_localctx).f2.stop):null));
						                      ((FormulaContext)_localctx).frm =  new FormulaIMPL(((FormulaContext)_localctx).f1.frm, ((FormulaContext)_localctx).f2.frm);
						                  
						}
						break;
					}
					} 
				}
				setState(57);
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
			setState(72);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(58);
				((QuantificationContext)_localctx).f = formula(0);

				            System.out.println("DEBUGA: QUANT - formula: "+(((QuantificationContext)_localctx).f!=null?_input.getText(((QuantificationContext)_localctx).f.start,((QuantificationContext)_localctx).f.stop):null));
				            ((QuantificationContext)_localctx).frm =  ((QuantificationContext)_localctx).f.frm;
				        
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(61);
				match(LP);
				setState(62);
				((QuantificationContext)_localctx).fq = quantification();
				setState(63);
				match(RP);

				            System.out.println("DEBUGA: QUANT - Quant: "+(((QuantificationContext)_localctx).fq!=null?_input.getText(((QuantificationContext)_localctx).fq.start,((QuantificationContext)_localctx).fq.stop):null));                          
				            ((QuantificationContext)_localctx).frm =  new BracketFormula(((QuantificationContext)_localctx).fq.frm);
				        
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(66);
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
				setState(67);
				((QuantificationContext)_localctx).var = match(Var);
				setState(68);
				match(DOT);
				setState(69);
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
		public EventExpContext ee1;
		public Token eeT;
		public EventExpContext ee;
		public NonNumExpContext nne1;
		public Token opnne;
		public NonNumExpContext nne2;
		public NumExpContext ne1;
		public Token op;
		public NumExpContext ne2;
		public EventExpContext ee2;
		public TerminalNode True() { return getToken(FOEParser.True, 0); }
		public TerminalNode False() { return getToken(FOEParser.False, 0); }
		public TerminalNode LP() { return getToken(FOEParser.LP, 0); }
		public TerminalNode RP() { return getToken(FOEParser.RP, 0); }
		public List<EventExpContext> eventExp() {
			return getRuleContexts(EventExpContext.class);
		}
		public EventExpContext eventExp(int i) {
			return getRuleContext(EventExpContext.class,i);
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
		return eventExp(0);
	}

	private EventExpContext eventExp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		EventExpContext _localctx = new EventExpContext(_ctx, _parentState);
		EventExpContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_eventExp, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(75);
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
				{
				setState(77);
				match(LP);
				setState(78);
				((EventExpContext)_localctx).ee = eventExp(0);
				setState(79);
				match(RP);

				            System.out.println("DEBUGA: EE-BRACKET: "+(((EventExpContext)_localctx).ee!=null?_input.getText(((EventExpContext)_localctx).ee.start,((EventExpContext)_localctx).ee.stop):null));
				            ((EventExpContext)_localctx).eventExpression =  new BracketEventExp(((EventExpContext)_localctx).ee.eventExpression);
				        
				}
				break;
			case 3:
				{
				setState(82);
				((EventExpContext)_localctx).nne1 = nonNumExp();
				setState(83);
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
				setState(84);
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
				{
				setState(87);
				((EventExpContext)_localctx).ne1 = numExp(0);
				setState(88);
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
				setState(89);
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
			_ctx.stop = _input.LT(-1);
			setState(101);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new EventExpContext(_parentctx, _parentState);
					_localctx.ee1 = _prevctx;
					_localctx.ee1 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_eventExp);
					setState(94);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(95);
					((EventExpContext)_localctx).op = _input.LT(1);
					_la = _input.LA(1);
					if ( !(_la==EQUAL || _la==NOTEQUAL) ) {
						((EventExpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(96);
					((EventExpContext)_localctx).ee2 = eventExp(2);

					                      System.out.println("DEBUGA: EE-EE-OP: "+(((EventExpContext)_localctx).ee1!=null?_input.getText(((EventExpContext)_localctx).ee1.start,((EventExpContext)_localctx).ee1.stop):null)+", "+(((EventExpContext)_localctx).op!=null?((EventExpContext)_localctx).op.getText():null)+", "+(((EventExpContext)_localctx).ee2!=null?_input.getText(((EventExpContext)_localctx).ee2.start,((EventExpContext)_localctx).ee2.stop):null));
					                      
					                      if((((EventExpContext)_localctx).op!=null?((EventExpContext)_localctx).op.getText():null).equals("==")){
					                          ((EventExpContext)_localctx).eventExpression =  new EventExpComparison(
					                              ((EventExpContext)_localctx).ee1.eventExpression, Const.ComparisonOperator.EQUAL, ((EventExpContext)_localctx).ee2.eventExpression);
					                      }else if((((EventExpContext)_localctx).op!=null?((EventExpContext)_localctx).op.getText():null).equals("!=")){
					                          ((EventExpContext)_localctx).eventExpression =  new EventExpComparison(
					                              ((EventExpContext)_localctx).ee1.eventExpression, Const.ComparisonOperator.NOT_EQUAL, ((EventExpContext)_localctx).ee2.eventExpression);
					                      }
					                  
					}
					} 
				}
				setState(103);
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

	public static class NonNumExpContext extends ParserRuleContext {
		public NonNumExp nonNumericExpression;
		public Token nneString;
		public Token nneBool;
		public QueryContext nneQuery;
		public NonNumExpContext nne;
		public TerminalNode String() { return getToken(FOEParser.String, 0); }
		public TerminalNode True() { return getToken(FOEParser.True, 0); }
		public TerminalNode False() { return getToken(FOEParser.False, 0); }
		public QueryContext query() {
			return getRuleContext(QueryContext.class,0);
		}
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
			setState(116);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case String:
				enterOuterAlt(_localctx, 1);
				{
				setState(104);
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
				setState(106);
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
				setState(108);
				((NonNumExpContext)_localctx).nneQuery = query();

				            System.out.println("DEBUGA: NonNE-Query: "+(((NonNumExpContext)_localctx).nneQuery!=null?_input.getText(((NonNumExpContext)_localctx).nneQuery.start,((NonNumExpContext)_localctx).nneQuery.stop):null));
				            ((NonNumExpContext)_localctx).nonNumericExpression =  ((NonNumExpContext)_localctx).nneQuery.attAccessor;
				         
				}
				break;
			case LP:
				enterOuterAlt(_localctx, 4);
				{
				setState(111);
				match(LP);
				setState(112);
				((NonNumExpContext)_localctx).nne = nonNumExp();
				setState(113);
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
		public Token ne;
		public IndexExpContext ip;
		public QueryNumericContext neQuery;
		public NumExpContext ne5;
		public Token op;
		public NumExpContext ne2;
		public TerminalNode Number() { return getToken(FOEParser.Number, 0); }
		public IndexExpContext indexExp() {
			return getRuleContext(IndexExpContext.class,0);
		}
		public QueryNumericContext queryNumeric() {
			return getRuleContext(QueryNumericContext.class,0);
		}
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
			setState(132);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(119);
				((NumExpContext)_localctx).ne = match(Number);

				            System.out.println("DEBUGA: NE-INT: "+(((NumExpContext)_localctx).ne!=null?((NumExpContext)_localctx).ne.getText():null));
				            try{
				                ((NumExpContext)_localctx).numericExpression =  new NumExpNumber((((NumExpContext)_localctx).ne!=null?((NumExpContext)_localctx).ne.getText():null));
				            }catch(Exception e){
				              notifyErrorListeners("ERROR: " + e.getMessage());
				            }
				      
				}
				break;
			case 2:
				{
				setState(121);
				((NumExpContext)_localctx).ip = indexExp(0);

				            System.out.println("DEBUGA: NE-IP: "+(((NumExpContext)_localctx).ip!=null?_input.getText(((NumExpContext)_localctx).ip.start,((NumExpContext)_localctx).ip.stop):null));
				            ((NumExpContext)_localctx).numericExpression =  ((NumExpContext)_localctx).ip.indexExpression;
				      
				}
				break;
			case 3:
				{
				setState(124);
				((NumExpContext)_localctx).neQuery = queryNumeric();

				            System.out.println("DEBUGA: NE-Query: "+(((NumExpContext)_localctx).neQuery!=null?_input.getText(((NumExpContext)_localctx).neQuery.start,((NumExpContext)_localctx).neQuery.stop):null));
				            ((NumExpContext)_localctx).numericExpression =  ((NumExpContext)_localctx).neQuery.attAccessorNumeric;
				      
				}
				break;
			case 4:
				{
				setState(127);
				match(LP);
				setState(128);
				((NumExpContext)_localctx).ne5 = numExp(0);
				setState(129);
				match(RP);

				            System.out.println("DEBUGA: EE-BRACKET: "+(((NumExpContext)_localctx).ne5!=null?_input.getText(((NumExpContext)_localctx).ne5.start,((NumExpContext)_localctx).ne5.stop):null));
				            ((NumExpContext)_localctx).numericExpression =  new BracketNumExp(((NumExpContext)_localctx).ne5.numericExpression);
				      
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(141);
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
					setState(134);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(135);
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
					setState(136);
					((NumExpContext)_localctx).ne2 = numExp(4);

					                      System.out.println("DEBUGA: NE-Arithmetic: "+(((NumExpContext)_localctx).ne1!=null?_input.getText(((NumExpContext)_localctx).ne1.start,((NumExpContext)_localctx).ne1.stop):null)+", "+(((NumExpContext)_localctx).op!=null?((NumExpContext)_localctx).op.getText():null)+", "+(((NumExpContext)_localctx).ne2!=null?_input.getText(((NumExpContext)_localctx).ne2.start,((NumExpContext)_localctx).ne2.stop):null));
					                      if((((NumExpContext)_localctx).op!=null?((NumExpContext)_localctx).op.getText():null).equals("+"))
					                          ((NumExpContext)_localctx).numericExpression =  new NumExpBinary(((NumExpContext)_localctx).ne1.numericExpression, Const.ArithmeticOperator.PLUS, ((NumExpContext)_localctx).ne2.numericExpression);
					                      else if((((NumExpContext)_localctx).op!=null?((NumExpContext)_localctx).op.getText():null).equals("-"))
					                          ((NumExpContext)_localctx).numericExpression =  new NumExpBinary(((NumExpContext)_localctx).ne1.numericExpression, Const.ArithmeticOperator.MINUS, ((NumExpContext)_localctx).ne2.numericExpression);
					                
					}
					} 
				}
				setState(143);
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
		public TerminalNode Number() { return getToken(FOEParser.Number, 0); }
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
			setState(151);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Number:
				{
				setState(145);
				((IndexExpContext)_localctx).num = match(Number);

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
				setState(147);
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
				setState(149);
				((IndexExpContext)_localctx).var = match(Var);

				            System.out.println("DEBUGA: IP-Var: "+(((IndexExpContext)_localctx).var!=null?((IndexExpContext)_localctx).var.getText():null));
				            ((IndexExpContext)_localctx).indexExpression =  new IndexExpVar((((IndexExpContext)_localctx).var!=null?((IndexExpContext)_localctx).var.getText():null));
				        
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(160);
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
					setState(153);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(154);
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
					setState(155);
					((IndexExpContext)_localctx).ie2 = indexExp(4);

					                      System.out.println("DEBUGA: IP-Arithmetic: "+(((IndexExpContext)_localctx).ie1!=null?_input.getText(((IndexExpContext)_localctx).ie1.start,((IndexExpContext)_localctx).ie1.stop):null)+", "+(((IndexExpContext)_localctx).op!=null?((IndexExpContext)_localctx).op.getText():null)+", "+(((IndexExpContext)_localctx).ie2!=null?_input.getText(((IndexExpContext)_localctx).ie2.start,((IndexExpContext)_localctx).ie2.stop):null));
					                      
					                      if((((IndexExpContext)_localctx).op!=null?((IndexExpContext)_localctx).op.getText():null).equals("+"))
					                          ((IndexExpContext)_localctx).indexExpression =  new IndexExpBinary(((IndexExpContext)_localctx).ie1.indexExpression, Const.ArithmeticOperator.PLUS, ((IndexExpContext)_localctx).ie2.indexExpression);
					                      else if((((IndexExpContext)_localctx).op!=null?((IndexExpContext)_localctx).op.getText():null).equals("-"))
					                          ((IndexExpContext)_localctx).indexExpression =  new IndexExpBinary(((IndexExpContext)_localctx).ie1.indexExpression, Const.ArithmeticOperator.MINUS, ((IndexExpContext)_localctx).ie2.indexExpression);
					                  
					}
					} 
				}
				setState(162);
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
			setState(163);
			((QueryContext)_localctx).q = match(Qf);
			setState(164);
			((QueryContext)_localctx).idx = indexExp(0);
			setState(165);
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
			setState(168);
			((QueryNumericContext)_localctx).q = match(Qf);
			setState(169);
			((QueryNumericContext)_localctx).idx = indexExp(0);
			setState(170);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return formula_sempred((FormulaContext)_localctx, predIndex);
		case 3:
			return eventExp_sempred((EventExpContext)_localctx, predIndex);
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
	private boolean eventExp_sempred(EventExpContext _localctx, int predIndex) {
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3!\u00b0\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2"+
		"\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\5\3,\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\38\n"+
		"\3\f\3\16\3;\13\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\5\4K\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\5\5_\n\5\3\5\3\5\3\5\3\5\3\5\7\5f\n\5\f\5\16\5i\13\5"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6w\n\6\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u0087\n\7\3\7\3\7\3\7"+
		"\3\7\3\7\7\7\u008e\n\7\f\7\16\7\u0091\13\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\5\b\u009a\n\b\3\b\3\b\3\b\3\b\3\b\7\b\u00a1\n\b\f\b\16\b\u00a4\13\b\3"+
		"\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\2\6\4\b\f\16\13\2\4\6\b\n\f"+
		"\16\20\22\2\b\3\2\25\26\3\2\3\4\3\2\23\24\3\2\b\t\3\2\b\r\3\2\16\17\2"+
		"\u00bb\2\24\3\2\2\2\4+\3\2\2\2\6J\3\2\2\2\b^\3\2\2\2\nv\3\2\2\2\f\u0086"+
		"\3\2\2\2\16\u0099\3\2\2\2\20\u00a5\3\2\2\2\22\u00aa\3\2\2\2\24\25\5\4"+
		"\3\2\25\26\7\2\2\3\26\27\b\2\1\2\27\3\3\2\2\2\30\31\b\3\1\2\31\32\5\b"+
		"\5\2\32\33\b\3\1\2\33,\3\2\2\2\34\35\7\30\2\2\35\36\5\4\3\2\36\37\7\31"+
		"\2\2\37 \b\3\1\2 ,\3\2\2\2!\"\7\5\2\2\"#\5\4\3\6#$\b\3\1\2$,\3\2\2\2%"+
		"&\t\2\2\2&\'\7\33\2\2\'(\7\27\2\2()\5\6\4\2)*\b\3\1\2*,\3\2\2\2+\30\3"+
		"\2\2\2+\34\3\2\2\2+!\3\2\2\2+%\3\2\2\2,9\3\2\2\2-.\f\5\2\2./\t\3\2\2/"+
		"\60\5\4\3\6\60\61\b\3\1\2\618\3\2\2\2\62\63\f\3\2\2\63\64\7\6\2\2\64\65"+
		"\5\4\3\4\65\66\b\3\1\2\668\3\2\2\2\67-\3\2\2\2\67\62\3\2\2\28;\3\2\2\2"+
		"9\67\3\2\2\29:\3\2\2\2:\5\3\2\2\2;9\3\2\2\2<=\5\4\3\2=>\b\4\1\2>K\3\2"+
		"\2\2?@\7\30\2\2@A\5\6\4\2AB\7\31\2\2BC\b\4\1\2CK\3\2\2\2DE\t\2\2\2EF\7"+
		"\33\2\2FG\7\27\2\2GH\5\6\4\2HI\b\4\1\2IK\3\2\2\2J<\3\2\2\2J?\3\2\2\2J"+
		"D\3\2\2\2K\7\3\2\2\2LM\b\5\1\2MN\t\4\2\2N_\b\5\1\2OP\7\30\2\2PQ\5\b\5"+
		"\2QR\7\31\2\2RS\b\5\1\2S_\3\2\2\2TU\5\n\6\2UV\t\5\2\2VW\5\n\6\2WX\b\5"+
		"\1\2X_\3\2\2\2YZ\5\f\7\2Z[\t\6\2\2[\\\5\f\7\2\\]\b\5\1\2]_\3\2\2\2^L\3"+
		"\2\2\2^O\3\2\2\2^T\3\2\2\2^Y\3\2\2\2_g\3\2\2\2`a\f\3\2\2ab\t\5\2\2bc\5"+
		"\b\5\4cd\b\5\1\2df\3\2\2\2e`\3\2\2\2fi\3\2\2\2ge\3\2\2\2gh\3\2\2\2h\t"+
		"\3\2\2\2ig\3\2\2\2jk\7\22\2\2kw\b\6\1\2lm\t\4\2\2mw\b\6\1\2no\5\20\t\2"+
		"op\b\6\1\2pw\3\2\2\2qr\7\30\2\2rs\5\n\6\2st\7\31\2\2tu\b\6\1\2uw\3\2\2"+
		"\2vj\3\2\2\2vl\3\2\2\2vn\3\2\2\2vq\3\2\2\2w\13\3\2\2\2xy\b\7\1\2yz\7!"+
		"\2\2z\u0087\b\7\1\2{|\5\16\b\2|}\b\7\1\2}\u0087\3\2\2\2~\177\5\22\n\2"+
		"\177\u0080\b\7\1\2\u0080\u0087\3\2\2\2\u0081\u0082\7\30\2\2\u0082\u0083"+
		"\5\f\7\2\u0083\u0084\7\31\2\2\u0084\u0085\b\7\1\2\u0085\u0087\3\2\2\2"+
		"\u0086x\3\2\2\2\u0086{\3\2\2\2\u0086~\3\2\2\2\u0086\u0081\3\2\2\2\u0087"+
		"\u008f\3\2\2\2\u0088\u0089\f\5\2\2\u0089\u008a\t\7\2\2\u008a\u008b\5\f"+
		"\7\6\u008b\u008c\b\7\1\2\u008c\u008e\3\2\2\2\u008d\u0088\3\2\2\2\u008e"+
		"\u0091\3\2\2\2\u008f\u008d\3\2\2\2\u008f\u0090\3\2\2\2\u0090\r\3\2\2\2"+
		"\u0091\u008f\3\2\2\2\u0092\u0093\b\b\1\2\u0093\u0094\7!\2\2\u0094\u009a"+
		"\b\b\1\2\u0095\u0096\7\34\2\2\u0096\u009a\b\b\1\2\u0097\u0098\7\33\2\2"+
		"\u0098\u009a\b\b\1\2\u0099\u0092\3\2\2\2\u0099\u0095\3\2\2\2\u0099\u0097"+
		"\3\2\2\2\u009a\u00a2\3\2\2\2\u009b\u009c\f\5\2\2\u009c\u009d\t\7\2\2\u009d"+
		"\u009e\5\16\b\6\u009e\u009f\b\b\1\2\u009f\u00a1\3\2\2\2\u00a0\u009b\3"+
		"\2\2\2\u00a1\u00a4\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3"+
		"\17\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a5\u00a6\7\36\2\2\u00a6\u00a7\5\16"+
		"\b\2\u00a7\u00a8\7\35\2\2\u00a8\u00a9\b\t\1\2\u00a9\21\3\2\2\2\u00aa\u00ab"+
		"\7\36\2\2\u00ab\u00ac\5\16\b\2\u00ac\u00ad\7\35\2\2\u00ad\u00ae\b\n\1"+
		"\2\u00ae\23\3\2\2\2\r+\679J^gv\u0086\u008f\u0099\u00a2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}