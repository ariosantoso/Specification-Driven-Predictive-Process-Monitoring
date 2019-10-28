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

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class NonNumCondAggregationLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		OR=1, AND=2, NEG=3, IMPL=4, End=5, EQUAL=6, NOTEQUAL=7, GT=8, GTE=9, LT=10, 
		LTE=11, Plus=12, Minus=13, Multiply=14, Divide=15, String=16, True=17, 
		False=18, DOT=19, LP=20, RP=21, WS=22, Var=23, SPEC=24, AttName=25, Qf=26, 
		Quote=27, NEWLINE=28, PosNumber=29, AGG_SEPARATOR=30, AGG_RANGE_SEPARATOR=31;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"OR", "AND", "NEG", "IMPL", "End", "EQUAL", "NOTEQUAL", "GT", "GTE", "LT", 
		"LTE", "Plus", "Minus", "Multiply", "Divide", "String", "True", "False", 
		"DOT", "LP", "RP", "WS", "Var", "SPEC", "AttName", "Qf", "Quote", "NEWLINE", 
		"PosNumber", "AGG_SEPARATOR", "AGG_RANGE_SEPARATOR", "UNDERSCORE", "DASH"
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


	public NonNumCondAggregationLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "NonNumCondAggregation.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2!\u00c5\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\7\3"+
		"\7\3\7\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3"+
		"\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\21\3\21\7\21p\n\21\f\21"+
		"\16\21s\13\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3"+
		"\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\6\27\u0089\n\27\r\27\16\27"+
		"\u008a\3\27\3\27\3\30\6\30\u0090\n\30\r\30\16\30\u0091\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\5\31\u009c\n\31\3\32\3\32\3\32\3\32\6\32\u00a2"+
		"\n\32\r\32\16\32\u00a3\3\33\3\33\3\33\3\34\3\34\3\35\6\35\u00ac\n\35\r"+
		"\35\16\35\u00ad\3\36\6\36\u00b1\n\36\r\36\16\36\u00b2\3\36\3\36\6\36\u00b7"+
		"\n\36\r\36\16\36\u00b8\3\36\5\36\u00bc\n\36\3\37\3\37\3 \3 \3!\3!\3\""+
		"\3\"\2\2#\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33"+
		"\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67"+
		"\359\36;\37= ?!A\2C\2\3\2\b\5\2\62;C\\c|\5\2\13\f\17\17\"\"\3\2c|\5\2"+
		"<<C\\c|\4\2\f\f\17\17\3\2\62;\2\u00ce\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3"+
		"\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3"+
		"\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65"+
		"\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\3E\3"+
		"\2\2\2\5H\3\2\2\2\7K\3\2\2\2\tM\3\2\2\2\13P\3\2\2\2\rR\3\2\2\2\17U\3\2"+
		"\2\2\21X\3\2\2\2\23Z\3\2\2\2\25]\3\2\2\2\27_\3\2\2\2\31b\3\2\2\2\33d\3"+
		"\2\2\2\35f\3\2\2\2\37h\3\2\2\2!j\3\2\2\2#v\3\2\2\2%{\3\2\2\2\'\u0081\3"+
		"\2\2\2)\u0083\3\2\2\2+\u0085\3\2\2\2-\u0088\3\2\2\2/\u008f\3\2\2\2\61"+
		"\u009b\3\2\2\2\63\u009d\3\2\2\2\65\u00a5\3\2\2\2\67\u00a8\3\2\2\29\u00ab"+
		"\3\2\2\2;\u00b0\3\2\2\2=\u00bd\3\2\2\2?\u00bf\3\2\2\2A\u00c1\3\2\2\2C"+
		"\u00c3\3\2\2\2EF\7~\2\2FG\7~\2\2G\4\3\2\2\2HI\7(\2\2IJ\7(\2\2J\6\3\2\2"+
		"\2KL\7#\2\2L\b\3\2\2\2MN\7/\2\2NO\7@\2\2O\n\3\2\2\2PQ\7=\2\2Q\f\3\2\2"+
		"\2RS\7?\2\2ST\7?\2\2T\16\3\2\2\2UV\7#\2\2VW\7?\2\2W\20\3\2\2\2XY\7@\2"+
		"\2Y\22\3\2\2\2Z[\7@\2\2[\\\7?\2\2\\\24\3\2\2\2]^\7>\2\2^\26\3\2\2\2_`"+
		"\7>\2\2`a\7?\2\2a\30\3\2\2\2bc\7-\2\2c\32\3\2\2\2de\7/\2\2e\34\3\2\2\2"+
		"fg\7,\2\2g\36\3\2\2\2hi\7^\2\2i \3\2\2\2jq\7$\2\2kp\t\2\2\2lp\5A!\2mp"+
		"\5C\"\2np\7\"\2\2ok\3\2\2\2ol\3\2\2\2om\3\2\2\2on\3\2\2\2ps\3\2\2\2qo"+
		"\3\2\2\2qr\3\2\2\2rt\3\2\2\2sq\3\2\2\2tu\7$\2\2u\"\3\2\2\2vw\7V\2\2wx"+
		"\7T\2\2xy\7W\2\2yz\7G\2\2z$\3\2\2\2{|\7H\2\2|}\7C\2\2}~\7N\2\2~\177\7"+
		"U\2\2\177\u0080\7G\2\2\u0080&\3\2\2\2\u0081\u0082\7\60\2\2\u0082(\3\2"+
		"\2\2\u0083\u0084\7*\2\2\u0084*\3\2\2\2\u0085\u0086\7+\2\2\u0086,\3\2\2"+
		"\2\u0087\u0089\t\3\2\2\u0088\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u0088"+
		"\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008d\b\27\2\2"+
		"\u008d.\3\2\2\2\u008e\u0090\t\4\2\2\u008f\u008e\3\2\2\2\u0090\u0091\3"+
		"\2\2\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\60\3\2\2\2\u0093"+
		"\u0094\7N\2\2\u0094\u0095\7C\2\2\u0095\u0096\7U\2\2\u0096\u009c\7V\2\2"+
		"\u0097\u0098\7E\2\2\u0098\u0099\7W\2\2\u0099\u009a\7T\2\2\u009a\u009c"+
		"\7T\2\2\u009b\u0093\3\2\2\2\u009b\u0097\3\2\2\2\u009c\62\3\2\2\2\u009d"+
		"\u009e\7_\2\2\u009e\u009f\7\60\2\2\u009f\u00a1\3\2\2\2\u00a0\u00a2\t\5"+
		"\2\2\u00a1\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3"+
		"\u00a4\3\2\2\2\u00a4\64\3\2\2\2\u00a5\u00a6\7g\2\2\u00a6\u00a7\7]\2\2"+
		"\u00a7\66\3\2\2\2\u00a8\u00a9\7$\2\2\u00a98\3\2\2\2\u00aa\u00ac\t\6\2"+
		"\2\u00ab\u00aa\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ae"+
		"\3\2\2\2\u00ae:\3\2\2\2\u00af\u00b1\t\7\2\2\u00b0\u00af\3\2\2\2\u00b1"+
		"\u00b2\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00bb\3\2"+
		"\2\2\u00b4\u00b6\7\60\2\2\u00b5\u00b7\t\7\2\2\u00b6\u00b5\3\2\2\2\u00b7"+
		"\u00b8\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00bc\3\2"+
		"\2\2\u00ba\u00bc\3\2\2\2\u00bb\u00b4\3\2\2\2\u00bb\u00ba\3\2\2\2\u00bc"+
		"<\3\2\2\2\u00bd\u00be\7%\2\2\u00be>\3\2\2\2\u00bf\u00c0\7<\2\2\u00c0@"+
		"\3\2\2\2\u00c1\u00c2\7a\2\2\u00c2B\3\2\2\2\u00c3\u00c4\7/\2\2\u00c4D\3"+
		"\2\2\2\16\2oq\u008a\u0091\u009b\u00a1\u00a3\u00ad\u00b2\u00b8\u00bb\3"+
		"\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}