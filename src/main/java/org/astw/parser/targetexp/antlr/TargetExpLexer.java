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


import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TargetExpLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"OR", "AND", "NEG", "IMPL", "End", "EQUAL", "NOTEQUAL", "GT", "GTE", "LT", 
		"LTE", "Plus", "Minus", "Multiply", "Divide", "String", "True", "False", 
		"EXIST", "FORALL", "DOT", "LP", "RP", "WS", "Var", "SPEC", "AttName", 
		"Qf", "Quote", "NEWLINE", "PosNumber", "NUMERIC_AGG_FUNC_SUM", "NUMERIC_AGG_FUNC_AVG", 
		"NUMERIC_AGG_FUNC_MAX", "NUMERIC_AGG_FUNC_MIN", "NUMERIC_AGG_FUNC_COUNT", 
		"NUMERIC_AGG_FUNC_COUNTVAL", "NON_NUMERIC_AGG_FUNC_CONCAT", "UNDERSCORE", 
		"DASH"
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


	public TargetExpLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "TargetExp.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2(\u013b\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\3\2\3\2\3\2\3"+
		"\3\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t"+
		"\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3"+
		"\20\3\21\3\21\3\21\3\21\3\21\7\21~\n\21\f\21\16\21\u0081\13\21\3\21\3"+
		"\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3"+
		"\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3"+
		"\27\3\27\3\30\3\30\3\31\6\31\u00a5\n\31\r\31\16\31\u00a6\3\31\3\31\3\32"+
		"\6\32\u00ac\n\32\r\32\16\32\u00ad\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3"+
		"\33\5\33\u00b8\n\33\3\34\3\34\3\34\3\34\6\34\u00be\n\34\r\34\16\34\u00bf"+
		"\3\35\3\35\3\35\3\36\3\36\3\37\6\37\u00c8\n\37\r\37\16\37\u00c9\3 \6 "+
		"\u00cd\n \r \16 \u00ce\3 \3 \6 \u00d3\n \r \16 \u00d4\3 \5 \u00d8\n \3"+
		"!\3!\3!\3!\3!\3!\6!\u00e0\n!\r!\16!\u00e1\3!\3!\3\"\3\"\3\"\3\"\3\"\3"+
		"\"\6\"\u00ec\n\"\r\"\16\"\u00ed\3\"\3\"\3#\3#\3#\3#\3#\3#\6#\u00f8\n#"+
		"\r#\16#\u00f9\3#\3#\3$\3$\3$\3$\3$\3$\6$\u0104\n$\r$\16$\u0105\3$\3$\3"+
		"%\3%\3%\3%\3%\3%\3%\3%\6%\u0112\n%\r%\16%\u0113\3%\3%\3&\3&\3&\3&\3&\3"+
		"&\3&\3&\3&\3&\3&\6&\u0123\n&\r&\16&\u0124\3&\3&\3\'\3\'\3\'\3\'\3\'\3"+
		"\'\3\'\3\'\3\'\6\'\u0132\n\'\r\'\16\'\u0133\3\'\3\'\3(\3(\3)\3)\2\2*\3"+
		"\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37"+
		"\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37="+
		" ?!A\"C#E$G%I&K\'M(O\2Q\2\3\2\t\5\2\62;C\\c|\5\2\13\f\17\17\"\"\3\2c|"+
		"\5\2<<C\\c|\4\2\f\f\17\17\3\2\62;\4\2}}\177\177\2\u014b\2\3\3\2\2\2\2"+
		"\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2"+
		"\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2"+
		"\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2"+
		"\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2"+
		"\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2"+
		"\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2"+
		"K\3\2\2\2\2M\3\2\2\2\3S\3\2\2\2\5V\3\2\2\2\7Y\3\2\2\2\t[\3\2\2\2\13^\3"+
		"\2\2\2\r`\3\2\2\2\17c\3\2\2\2\21f\3\2\2\2\23h\3\2\2\2\25k\3\2\2\2\27m"+
		"\3\2\2\2\31p\3\2\2\2\33r\3\2\2\2\35t\3\2\2\2\37v\3\2\2\2!x\3\2\2\2#\u0084"+
		"\3\2\2\2%\u0089\3\2\2\2\'\u008f\3\2\2\2)\u0096\3\2\2\2+\u009d\3\2\2\2"+
		"-\u009f\3\2\2\2/\u00a1\3\2\2\2\61\u00a4\3\2\2\2\63\u00ab\3\2\2\2\65\u00b7"+
		"\3\2\2\2\67\u00b9\3\2\2\29\u00c1\3\2\2\2;\u00c4\3\2\2\2=\u00c7\3\2\2\2"+
		"?\u00cc\3\2\2\2A\u00d9\3\2\2\2C\u00e5\3\2\2\2E\u00f1\3\2\2\2G\u00fd\3"+
		"\2\2\2I\u0109\3\2\2\2K\u0117\3\2\2\2M\u0128\3\2\2\2O\u0137\3\2\2\2Q\u0139"+
		"\3\2\2\2ST\7~\2\2TU\7~\2\2U\4\3\2\2\2VW\7(\2\2WX\7(\2\2X\6\3\2\2\2YZ\7"+
		"#\2\2Z\b\3\2\2\2[\\\7/\2\2\\]\7@\2\2]\n\3\2\2\2^_\7=\2\2_\f\3\2\2\2`a"+
		"\7?\2\2ab\7?\2\2b\16\3\2\2\2cd\7#\2\2de\7?\2\2e\20\3\2\2\2fg\7@\2\2g\22"+
		"\3\2\2\2hi\7@\2\2ij\7?\2\2j\24\3\2\2\2kl\7>\2\2l\26\3\2\2\2mn\7>\2\2n"+
		"o\7?\2\2o\30\3\2\2\2pq\7-\2\2q\32\3\2\2\2rs\7/\2\2s\34\3\2\2\2tu\7,\2"+
		"\2u\36\3\2\2\2vw\7^\2\2w \3\2\2\2x\177\7$\2\2y~\t\2\2\2z~\5O(\2{~\5Q)"+
		"\2|~\7\"\2\2}y\3\2\2\2}z\3\2\2\2}{\3\2\2\2}|\3\2\2\2~\u0081\3\2\2\2\177"+
		"}\3\2\2\2\177\u0080\3\2\2\2\u0080\u0082\3\2\2\2\u0081\177\3\2\2\2\u0082"+
		"\u0083\7$\2\2\u0083\"\3\2\2\2\u0084\u0085\7V\2\2\u0085\u0086\7T\2\2\u0086"+
		"\u0087\7W\2\2\u0087\u0088\7G\2\2\u0088$\3\2\2\2\u0089\u008a\7H\2\2\u008a"+
		"\u008b\7C\2\2\u008b\u008c\7N\2\2\u008c\u008d\7U\2\2\u008d\u008e\7G\2\2"+
		"\u008e&\3\2\2\2\u008f\u0090\7G\2\2\u0090\u0091\7Z\2\2\u0091\u0092\7K\2"+
		"\2\u0092\u0093\7U\2\2\u0093\u0094\7V\2\2\u0094\u0095\7U\2\2\u0095(\3\2"+
		"\2\2\u0096\u0097\7H\2\2\u0097\u0098\7Q\2\2\u0098\u0099\7T\2\2\u0099\u009a"+
		"\7C\2\2\u009a\u009b\7N\2\2\u009b\u009c\7N\2\2\u009c*\3\2\2\2\u009d\u009e"+
		"\7\60\2\2\u009e,\3\2\2\2\u009f\u00a0\7*\2\2\u00a0.\3\2\2\2\u00a1\u00a2"+
		"\7+\2\2\u00a2\60\3\2\2\2\u00a3\u00a5\t\3\2\2\u00a4\u00a3\3\2\2\2\u00a5"+
		"\u00a6\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a8\3\2"+
		"\2\2\u00a8\u00a9\b\31\2\2\u00a9\62\3\2\2\2\u00aa\u00ac\t\4\2\2\u00ab\u00aa"+
		"\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae"+
		"\64\3\2\2\2\u00af\u00b0\7N\2\2\u00b0\u00b1\7C\2\2\u00b1\u00b2\7U\2\2\u00b2"+
		"\u00b8\7V\2\2\u00b3\u00b4\7E\2\2\u00b4\u00b5\7W\2\2\u00b5\u00b6\7T\2\2"+
		"\u00b6\u00b8\7T\2\2\u00b7\u00af\3\2\2\2\u00b7\u00b3\3\2\2\2\u00b8\66\3"+
		"\2\2\2\u00b9\u00ba\7_\2\2\u00ba\u00bb\7\60\2\2\u00bb\u00bd\3\2\2\2\u00bc"+
		"\u00be\t\5\2\2\u00bd\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00bd\3\2"+
		"\2\2\u00bf\u00c0\3\2\2\2\u00c08\3\2\2\2\u00c1\u00c2\7g\2\2\u00c2\u00c3"+
		"\7]\2\2\u00c3:\3\2\2\2\u00c4\u00c5\7$\2\2\u00c5<\3\2\2\2\u00c6\u00c8\t"+
		"\6\2\2\u00c7\u00c6\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00c7\3\2\2\2\u00c9"+
		"\u00ca\3\2\2\2\u00ca>\3\2\2\2\u00cb\u00cd\t\7\2\2\u00cc\u00cb\3\2\2\2"+
		"\u00cd\u00ce\3\2\2\2\u00ce\u00cc\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d7"+
		"\3\2\2\2\u00d0\u00d2\7\60\2\2\u00d1\u00d3\t\7\2\2\u00d2\u00d1\3\2\2\2"+
		"\u00d3\u00d4\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d8"+
		"\3\2\2\2\u00d6\u00d8\3\2\2\2\u00d7\u00d0\3\2\2\2\u00d7\u00d6\3\2\2\2\u00d8"+
		"@\3\2\2\2\u00d9\u00da\7U\2\2\u00da\u00db\7W\2\2\u00db\u00dc\7O\2\2\u00dc"+
		"\u00dd\7}\2\2\u00dd\u00df\3\2\2\2\u00de\u00e0\n\b\2\2\u00df\u00de\3\2"+
		"\2\2\u00e0\u00e1\3\2\2\2\u00e1\u00df\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2"+
		"\u00e3\3\2\2\2\u00e3\u00e4\7\177\2\2\u00e4B\3\2\2\2\u00e5\u00e6\7C\2\2"+
		"\u00e6\u00e7\7X\2\2\u00e7\u00e8\7I\2\2\u00e8\u00e9\7}\2\2\u00e9\u00eb"+
		"\3\2\2\2\u00ea\u00ec\n\b\2\2\u00eb\u00ea\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed"+
		"\u00eb\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef\u00f0\7\177"+
		"\2\2\u00f0D\3\2\2\2\u00f1\u00f2\7O\2\2\u00f2\u00f3\7C\2\2\u00f3\u00f4"+
		"\7Z\2\2\u00f4\u00f5\7}\2\2\u00f5\u00f7\3\2\2\2\u00f6\u00f8\n\b\2\2\u00f7"+
		"\u00f6\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9\u00f7\3\2\2\2\u00f9\u00fa\3\2"+
		"\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00fc\7\177\2\2\u00fcF\3\2\2\2\u00fd\u00fe"+
		"\7O\2\2\u00fe\u00ff\7K\2\2\u00ff\u0100\7P\2\2\u0100\u0101\7}\2\2\u0101"+
		"\u0103\3\2\2\2\u0102\u0104\n\b\2\2\u0103\u0102\3\2\2\2\u0104\u0105\3\2"+
		"\2\2\u0105\u0103\3\2\2\2\u0105\u0106\3\2\2\2\u0106\u0107\3\2\2\2\u0107"+
		"\u0108\7\177\2\2\u0108H\3\2\2\2\u0109\u010a\7E\2\2\u010a\u010b\7Q\2\2"+
		"\u010b\u010c\7W\2\2\u010c\u010d\7P\2\2\u010d\u010e\7V\2\2\u010e\u010f"+
		"\7}\2\2\u010f\u0111\3\2\2\2\u0110\u0112\n\b\2\2\u0111\u0110\3\2\2\2\u0112"+
		"\u0113\3\2\2\2\u0113\u0111\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0115\3\2"+
		"\2\2\u0115\u0116\7\177\2\2\u0116J\3\2\2\2\u0117\u0118\7E\2\2\u0118\u0119"+
		"\7Q\2\2\u0119\u011a\7W\2\2\u011a\u011b\7P\2\2\u011b\u011c\7V\2\2\u011c"+
		"\u011d\7X\2\2\u011d\u011e\7C\2\2\u011e\u011f\7N\2\2\u011f\u0120\7}\2\2"+
		"\u0120\u0122\3\2\2\2\u0121\u0123\n\b\2\2\u0122\u0121\3\2\2\2\u0123\u0124"+
		"\3\2\2\2\u0124\u0122\3\2\2\2\u0124\u0125\3\2\2\2\u0125\u0126\3\2\2\2\u0126"+
		"\u0127\7\177\2\2\u0127L\3\2\2\2\u0128\u0129\7E\2\2\u0129\u012a\7Q\2\2"+
		"\u012a\u012b\7P\2\2\u012b\u012c\7E\2\2\u012c\u012d\7C\2\2\u012d\u012e"+
		"\7V\2\2\u012e\u012f\7}\2\2\u012f\u0131\3\2\2\2\u0130\u0132\n\b\2\2\u0131"+
		"\u0130\3\2\2\2\u0132\u0133\3\2\2\2\u0133\u0131\3\2\2\2\u0133\u0134\3\2"+
		"\2\2\u0134\u0135\3\2\2\2\u0135\u0136\7\177\2\2\u0136N\3\2\2\2\u0137\u0138"+
		"\7a\2\2\u0138P\3\2\2\2\u0139\u013a\7/\2\2\u013aR\3\2\2\2\25\2}\177\u00a6"+
		"\u00ad\u00b7\u00bd\u00bf\u00c9\u00ce\u00d4\u00d7\u00e1\u00ed\u00f9\u0105"+
		"\u0113\u0124\u0133\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}