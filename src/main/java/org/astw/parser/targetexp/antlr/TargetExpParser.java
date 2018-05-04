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
		AttName=27, Qf=28, Quote=29, NEWLINE=30, Number=31;
	public static final int
		RULE_targetExp = 0, RULE_nonNumExp = 1, RULE_numExp = 2, RULE_indexExp = 3, 
		RULE_query = 4, RULE_queryNumeric = 5;
	public static final String[] ruleNames = {
		"targetExp", "nonNumExp", "numExp", "indexExp", "query", "queryNumeric"
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
			setState(20);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(12);
				((TargetExpContext)_localctx).nne = nonNumExp();
				setState(13);
				match(EOF);


				            ((TargetExpContext)_localctx).te =  ((TargetExpContext)_localctx).nne.nonNumericExpression;
				        
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(16);
				((TargetExpContext)_localctx).ne = numExp(0);
				setState(17);
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
		public NonNumExpContext nne;
		public TerminalNode String() { return getToken(TargetExpParser.String, 0); }
		public TerminalNode True() { return getToken(TargetExpParser.True, 0); }
		public TerminalNode False() { return getToken(TargetExpParser.False, 0); }
		public QueryContext query() {
			return getRuleContext(QueryContext.class,0);
		}
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
			setState(34);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case String:
				enterOuterAlt(_localctx, 1);
				{
				setState(22);
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
				setState(24);
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
				setState(26);
				((NonNumExpContext)_localctx).nneQuery = query();

				            System.out.println("DEBUGA: NonNE-Query: "+(((NonNumExpContext)_localctx).nneQuery!=null?_input.getText(((NonNumExpContext)_localctx).nneQuery.start,((NonNumExpContext)_localctx).nneQuery.stop):null));
				            ((NonNumExpContext)_localctx).nonNumericExpression =  ((NonNumExpContext)_localctx).nneQuery.attAccessor;
				         
				}
				break;
			case LP:
				enterOuterAlt(_localctx, 4);
				{
				setState(29);
				match(LP);
				setState(30);
				((NonNumExpContext)_localctx).nne = nonNumExp();
				setState(31);
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
		public QueryNumericContext neQuery;
		public NumExpContext ne5;
		public Token op;
		public NumExpContext ne2;
		public TerminalNode Number() { return getToken(TargetExpParser.Number, 0); }
		public QueryNumericContext queryNumeric() {
			return getRuleContext(QueryNumericContext.class,0);
		}
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
			setState(47);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Number:
				{
				setState(37);
				((NumExpContext)_localctx).ne = match(Number);

				            System.out.println("DEBUGA: NE-INT: "+(((NumExpContext)_localctx).ne!=null?((NumExpContext)_localctx).ne.getText():null));
				            try{
				                ((NumExpContext)_localctx).numericExpression =  new NumExpNumber((((NumExpContext)_localctx).ne!=null?((NumExpContext)_localctx).ne.getText():null));
				            }catch(Exception e){
				              notifyErrorListeners("ERROR: " + e.getMessage());
				            }
				      
				}
				break;
			case Qf:
				{
				setState(39);
				((NumExpContext)_localctx).neQuery = queryNumeric();

				            System.out.println("DEBUGA: NE-Query: "+(((NumExpContext)_localctx).neQuery!=null?_input.getText(((NumExpContext)_localctx).neQuery.start,((NumExpContext)_localctx).neQuery.stop):null));
				            ((NumExpContext)_localctx).numericExpression =  ((NumExpContext)_localctx).neQuery.attAccessorNumeric;
				      
				}
				break;
			case LP:
				{
				setState(42);
				match(LP);
				setState(43);
				((NumExpContext)_localctx).ne5 = numExp(0);
				setState(44);
				match(RP);

				            System.out.println("DEBUGA: EE-BRACKET: "+(((NumExpContext)_localctx).ne5!=null?_input.getText(((NumExpContext)_localctx).ne5.start,((NumExpContext)_localctx).ne5.stop):null));
				            ((NumExpContext)_localctx).numericExpression =  new BracketNumExp(((NumExpContext)_localctx).ne5.numericExpression);
				      
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(56);
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
					setState(49);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(50);
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
					setState(51);
					((NumExpContext)_localctx).ne2 = numExp(4);

					                      System.out.println("DEBUGA: NE-Arithmetic: "+(((NumExpContext)_localctx).ne1!=null?_input.getText(((NumExpContext)_localctx).ne1.start,((NumExpContext)_localctx).ne1.stop):null)+", "+(((NumExpContext)_localctx).op!=null?((NumExpContext)_localctx).op.getText():null)+", "+(((NumExpContext)_localctx).ne2!=null?_input.getText(((NumExpContext)_localctx).ne2.start,((NumExpContext)_localctx).ne2.stop):null));
					                      if((((NumExpContext)_localctx).op!=null?((NumExpContext)_localctx).op.getText():null).equals("+"))
					                          ((NumExpContext)_localctx).numericExpression =  new NumExpBinary(((NumExpContext)_localctx).ne1.numericExpression, Const.ArithmeticOperator.PLUS, ((NumExpContext)_localctx).ne2.numericExpression);
					                      else if((((NumExpContext)_localctx).op!=null?((NumExpContext)_localctx).op.getText():null).equals("-"))
					                          ((NumExpContext)_localctx).numericExpression =  new NumExpBinary(((NumExpContext)_localctx).ne1.numericExpression, Const.ArithmeticOperator.MINUS, ((NumExpContext)_localctx).ne2.numericExpression);
					                
					}
					} 
				}
				setState(58);
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
		public TerminalNode Number() { return getToken(TargetExpParser.Number, 0); }
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
			setState(64);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Number:
				{
				setState(60);
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
				setState(62);
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
			setState(73);
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
					setState(66);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(67);
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
					setState(68);
					((IndexExpContext)_localctx).ie2 = indexExp(3);

					                      System.out.println("DEBUGA: IP-Arithmetic: "+(((IndexExpContext)_localctx).ie1!=null?_input.getText(((IndexExpContext)_localctx).ie1.start,((IndexExpContext)_localctx).ie1.stop):null)+", "+(((IndexExpContext)_localctx).op!=null?((IndexExpContext)_localctx).op.getText():null)+", "+(((IndexExpContext)_localctx).ie2!=null?_input.getText(((IndexExpContext)_localctx).ie2.start,((IndexExpContext)_localctx).ie2.stop):null));
					                      
					                      if((((IndexExpContext)_localctx).op!=null?((IndexExpContext)_localctx).op.getText():null).equals("+"))
					                          ((IndexExpContext)_localctx).indexExpression =  new IndexExpBinary(((IndexExpContext)_localctx).ie1.indexExpression, Const.ArithmeticOperator.PLUS, ((IndexExpContext)_localctx).ie2.indexExpression);
					                      else if((((IndexExpContext)_localctx).op!=null?((IndexExpContext)_localctx).op.getText():null).equals("-"))
					                          ((IndexExpContext)_localctx).indexExpression =  new IndexExpBinary(((IndexExpContext)_localctx).ie1.indexExpression, Const.ArithmeticOperator.MINUS, ((IndexExpContext)_localctx).ie2.indexExpression);
					                  
					}
					} 
				}
				setState(75);
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
			setState(76);
			((QueryContext)_localctx).q = match(Qf);
			setState(77);
			((QueryContext)_localctx).idx = indexExp(0);
			setState(78);
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
			setState(81);
			((QueryNumericContext)_localctx).q = match(Qf);
			setState(82);
			((QueryNumericContext)_localctx).idx = indexExp(0);
			setState(83);
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
			return precpred(_ctx, 3);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3!Y\4\2\t\2\4\3\t\3"+
		"\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\27"+
		"\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3%\n\3\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\62\n\4\3\4\3\4\3\4\3\4\3\4\7"+
		"\49\n\4\f\4\16\4<\13\4\3\5\3\5\3\5\3\5\3\5\5\5C\n\5\3\5\3\5\3\5\3\5\3"+
		"\5\7\5J\n\5\f\5\16\5M\13\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\2\4\6\b\b\2\4\6\b\n\f\2\4\3\2\23\24\3\2\16\17\2[\2\26\3\2\2\2\4$\3\2"+
		"\2\2\6\61\3\2\2\2\bB\3\2\2\2\nN\3\2\2\2\fS\3\2\2\2\16\17\5\4\3\2\17\20"+
		"\7\2\2\3\20\21\b\2\1\2\21\27\3\2\2\2\22\23\5\6\4\2\23\24\7\2\2\3\24\25"+
		"\b\2\1\2\25\27\3\2\2\2\26\16\3\2\2\2\26\22\3\2\2\2\27\3\3\2\2\2\30\31"+
		"\7\22\2\2\31%\b\3\1\2\32\33\t\2\2\2\33%\b\3\1\2\34\35\5\n\6\2\35\36\b"+
		"\3\1\2\36%\3\2\2\2\37 \7\30\2\2 !\5\4\3\2!\"\7\31\2\2\"#\b\3\1\2#%\3\2"+
		"\2\2$\30\3\2\2\2$\32\3\2\2\2$\34\3\2\2\2$\37\3\2\2\2%\5\3\2\2\2&\'\b\4"+
		"\1\2\'(\7!\2\2(\62\b\4\1\2)*\5\f\7\2*+\b\4\1\2+\62\3\2\2\2,-\7\30\2\2"+
		"-.\5\6\4\2./\7\31\2\2/\60\b\4\1\2\60\62\3\2\2\2\61&\3\2\2\2\61)\3\2\2"+
		"\2\61,\3\2\2\2\62:\3\2\2\2\63\64\f\5\2\2\64\65\t\3\2\2\65\66\5\6\4\6\66"+
		"\67\b\4\1\2\679\3\2\2\28\63\3\2\2\29<\3\2\2\2:8\3\2\2\2:;\3\2\2\2;\7\3"+
		"\2\2\2<:\3\2\2\2=>\b\5\1\2>?\7!\2\2?C\b\5\1\2@A\7\34\2\2AC\b\5\1\2B=\3"+
		"\2\2\2B@\3\2\2\2CK\3\2\2\2DE\f\4\2\2EF\t\3\2\2FG\5\b\5\5GH\b\5\1\2HJ\3"+
		"\2\2\2ID\3\2\2\2JM\3\2\2\2KI\3\2\2\2KL\3\2\2\2L\t\3\2\2\2MK\3\2\2\2NO"+
		"\7\36\2\2OP\5\b\5\2PQ\7\35\2\2QR\b\6\1\2R\13\3\2\2\2ST\7\36\2\2TU\5\b"+
		"\5\2UV\7\35\2\2VW\b\7\1\2W\r\3\2\2\2\b\26$\61:BK";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}