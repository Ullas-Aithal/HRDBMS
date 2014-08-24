package com.exascale.optimizer;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SelectLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__46=1, T__45=2, T__44=3, T__43=4, T__42=5, T__41=6, T__40=7, T__39=8, 
		T__38=9, T__37=10, T__36=11, T__35=12, T__34=13, T__33=14, T__32=15, T__31=16, 
		T__30=17, T__29=18, T__28=19, T__27=20, T__26=21, T__25=22, T__24=23, 
		T__23=24, T__22=25, T__21=26, T__20=27, T__19=28, T__18=29, T__17=30, 
		T__16=31, T__15=32, T__14=33, T__13=34, T__12=35, T__11=36, T__10=37, 
		T__9=38, T__8=39, T__7=40, T__6=41, T__5=42, T__4=43, T__3=44, T__2=45, 
		T__1=46, T__0=47, STRING=48, STAR=49, COUNT=50, CONCAT=51, NEGATIVE=52, 
		EQUALS=53, OPERATOR=54, NULLOPERATOR=55, AND=56, OR=57, NOT=58, NULL=59, 
		DIRECTION=60, JOINTYPE=61, CROSSJOIN=62, TABLECOMBINATION=63, DISTINCT=64, 
		INTEGER=65, WS=66, UNIQUE=67, REPLACE=68, RESUME=69, NONE=70, ALL=71, 
		ANYTEXT=72, HASH=73, RANGE=74, DATE=75, IDENTIFIER=76, ANY=77;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'RUNSTATS ON'", "'INDEX'", "'EXISTS'", "','", "'DROP VIEW'", "'WHERE'", 
		"'HAVING'", "'('", "'DELETE FROM'", "'DROP TABLE'", "'PRIMARY KEY'", "'AS'", 
		"'{'", "'LOAD'", "'BIGINT'", "'}'", "'ORDER BY'", "'JOIN'", "'SET'", "'INTO'", 
		"'VARCHAR'", "'INSERT INTO'", "')'", "'.'", "'CHAR'", "'+'", "'UPDATE'", 
		"'CREATE VIEW'", "'ONLY'", "'CREATE'", "'ROW'", "'ON'", "'ROWS'", "'DROP INDEX'", 
		"'DOUBLE'", "'DELIMITER'", "'FLOAT'", "'VALUES('", "'CREATE TABLE'", "'GROUP BY'", 
		"'WITH'", "'FROM'", "'/'", "'FETCH FIRST'", "'|'", "'SELECT'", "'INTEGER'", 
		"STRING", "'*'", "'COUNT'", "'||'", "'-'", "'='", "OPERATOR", "NULLOPERATOR", 
		"'AND'", "'OR'", "'NOT'", "'NULL'", "DIRECTION", "JOINTYPE", "'CROSS JOIN'", 
		"TABLECOMBINATION", "'DISTINCT'", "INTEGER", "WS", "'UNIQUE'", "'REPLACE'", 
		"'RESUME'", "'NONE'", "'ALL'", "'ANY'", "'HASH'", "'RANGE'", "'DATE'", 
		"IDENTIFIER", "ANY"
	};
	public static final String[] ruleNames = {
		"T__46", "T__45", "T__44", "T__43", "T__42", "T__41", "T__40", "T__39", 
		"T__38", "T__37", "T__36", "T__35", "T__34", "T__33", "T__32", "T__31", 
		"T__30", "T__29", "T__28", "T__27", "T__26", "T__25", "T__24", "T__23", 
		"T__22", "T__21", "T__20", "T__19", "T__18", "T__17", "T__16", "T__15", 
		"T__14", "T__13", "T__12", "T__11", "T__10", "T__9", "T__8", "T__7", "T__6", 
		"T__5", "T__4", "T__3", "T__2", "T__1", "T__0", "STRING", "ESC", "STAR", 
		"COUNT", "CONCAT", "NEGATIVE", "EQUALS", "OPERATOR", "NULLOPERATOR", "AND", 
		"OR", "NOT", "NULL", "DIRECTION", "JOINTYPE", "CROSSJOIN", "TABLECOMBINATION", 
		"DISTINCT", "INTEGER", "WS", "UNIQUE", "REPLACE", "RESUME", "NONE", "ALL", 
		"ANYTEXT", "HASH", "RANGE", "DATE", "IDENTIFIER", "ANY"
	};


	public SelectLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Select.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2O\u02fa\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16"+
		"\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23"+
		"\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37"+
		"\3\37\3 \3 \3 \3 \3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3"+
		"#\3#\3#\3#\3$\3$\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3&\3&\3"+
		"&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\3(\3(\3("+
		"\3(\3(\3(\3(\3)\3)\3)\3)\3)\3)\3)\3)\3)\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+"+
		"\3,\3,\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3.\3.\3/\3/\3/\3/\3/\3/\3/"+
		"\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\61\3\61\3\61\7\61\u01d7\n\61"+
		"\f\61\16\61\u01da\13\61\3\61\3\61\3\62\3\62\3\62\3\63\3\63\3\64\3\64\3"+
		"\64\3\64\3\64\3\64\3\65\3\65\3\65\3\66\3\66\3\67\3\67\38\38\38\38\38\3"+
		"8\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\3"+
		"8\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\58\u021e\n8\39\3"+
		"9\39\39\39\39\39\39\39\39\39\39\39\39\39\39\39\39\59\u0232\n9\3:\3:\3"+
		":\3:\3;\3;\3;\3<\3<\3<\3<\3=\3=\3=\3=\3=\3>\3>\3>\3>\3>\3>\3>\5>\u024b"+
		"\n>\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?"+
		"\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?"+
		"\3?\3?\3?\3?\5?\u027e\n?\3@\3@\3@\3@\3@\3@\3@\3@\3@\3@\3@\3A\3A\3A\3A"+
		"\3A\3A\3A\3A\3A\3A\3A\3A\3A\3A\3A\3A\3A\3A\3A\3A\3A\3A\3A\3A\3A\3A\3A"+
		"\3A\3A\5A\u02a8\nA\3B\3B\3B\3B\3B\3B\3B\3B\3B\3C\6C\u02b4\nC\rC\16C\u02b5"+
		"\3D\6D\u02b9\nD\rD\16D\u02ba\3D\3D\3E\3E\3E\3E\3E\3E\3E\3F\3F\3F\3F\3"+
		"F\3F\3F\3F\3G\3G\3G\3G\3G\3G\3G\3H\3H\3H\3H\3H\3I\3I\3I\3I\3J\3J\3J\3"+
		"J\3K\3K\3K\3K\3K\3L\3L\3L\3L\3L\3L\3M\3M\3M\3M\3M\3N\3N\7N\u02f4\nN\f"+
		"N\16N\u02f7\13N\3O\3O\3\u01d8\2P\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31"+
		"\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60"+
		"_\61a\62c\2e\63g\64i\65k\66m\67o8q9s:u;w<y={>}?\177@\u0081A\u0083B\u0085"+
		"C\u0087D\u0089E\u008bF\u008dG\u008fH\u0091I\u0093J\u0095K\u0097L\u0099"+
		"M\u009bN\u009dO\3\2\6\3\2\62;\5\2\13\f\17\17\"\"\3\2C\\\5\2\62;C\\aa\u0313"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3"+
		"\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2"+
		"\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2"+
		"U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3"+
		"\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2"+
		"\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2"+
		"}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083\3\2\2\2\2\u0085\3\2\2"+
		"\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b\3\2\2\2\2\u008d\3\2\2\2\2\u008f"+
		"\3\2\2\2\2\u0091\3\2\2\2\2\u0093\3\2\2\2\2\u0095\3\2\2\2\2\u0097\3\2\2"+
		"\2\2\u0099\3\2\2\2\2\u009b\3\2\2\2\2\u009d\3\2\2\2\3\u009f\3\2\2\2\5\u00ab"+
		"\3\2\2\2\7\u00b1\3\2\2\2\t\u00b8\3\2\2\2\13\u00ba\3\2\2\2\r\u00c4\3\2"+
		"\2\2\17\u00ca\3\2\2\2\21\u00d1\3\2\2\2\23\u00d3\3\2\2\2\25\u00df\3\2\2"+
		"\2\27\u00ea\3\2\2\2\31\u00f6\3\2\2\2\33\u00f9\3\2\2\2\35\u00fb\3\2\2\2"+
		"\37\u0100\3\2\2\2!\u0107\3\2\2\2#\u0109\3\2\2\2%\u0112\3\2\2\2\'\u0117"+
		"\3\2\2\2)\u011b\3\2\2\2+\u0120\3\2\2\2-\u0128\3\2\2\2/\u0134\3\2\2\2\61"+
		"\u0136\3\2\2\2\63\u0138\3\2\2\2\65\u013d\3\2\2\2\67\u013f\3\2\2\29\u0146"+
		"\3\2\2\2;\u0152\3\2\2\2=\u0157\3\2\2\2?\u015e\3\2\2\2A\u0162\3\2\2\2C"+
		"\u0165\3\2\2\2E\u016a\3\2\2\2G\u0175\3\2\2\2I\u017c\3\2\2\2K\u0186\3\2"+
		"\2\2M\u018c\3\2\2\2O\u0194\3\2\2\2Q\u01a1\3\2\2\2S\u01aa\3\2\2\2U\u01af"+
		"\3\2\2\2W\u01b4\3\2\2\2Y\u01b6\3\2\2\2[\u01c2\3\2\2\2]\u01c4\3\2\2\2_"+
		"\u01cb\3\2\2\2a\u01d3\3\2\2\2c\u01dd\3\2\2\2e\u01e0\3\2\2\2g\u01e2\3\2"+
		"\2\2i\u01e8\3\2\2\2k\u01eb\3\2\2\2m\u01ed\3\2\2\2o\u021d\3\2\2\2q\u0231"+
		"\3\2\2\2s\u0233\3\2\2\2u\u0237\3\2\2\2w\u023a\3\2\2\2y\u023e\3\2\2\2{"+
		"\u024a\3\2\2\2}\u027d\3\2\2\2\177\u027f\3\2\2\2\u0081\u02a7\3\2\2\2\u0083"+
		"\u02a9\3\2\2\2\u0085\u02b3\3\2\2\2\u0087\u02b8\3\2\2\2\u0089\u02be\3\2"+
		"\2\2\u008b\u02c5\3\2\2\2\u008d\u02cd\3\2\2\2\u008f\u02d4\3\2\2\2\u0091"+
		"\u02d9\3\2\2\2\u0093\u02dd\3\2\2\2\u0095\u02e1\3\2\2\2\u0097\u02e6\3\2"+
		"\2\2\u0099\u02ec\3\2\2\2\u009b\u02f1\3\2\2\2\u009d\u02f8\3\2\2\2\u009f"+
		"\u00a0\7T\2\2\u00a0\u00a1\7W\2\2\u00a1\u00a2\7P\2\2\u00a2\u00a3\7U\2\2"+
		"\u00a3\u00a4\7V\2\2\u00a4\u00a5\7C\2\2\u00a5\u00a6\7V\2\2\u00a6\u00a7"+
		"\7U\2\2\u00a7\u00a8\7\"\2\2\u00a8\u00a9\7Q\2\2\u00a9\u00aa\7P\2\2\u00aa"+
		"\4\3\2\2\2\u00ab\u00ac\7K\2\2\u00ac\u00ad\7P\2\2\u00ad\u00ae\7F\2\2\u00ae"+
		"\u00af\7G\2\2\u00af\u00b0\7Z\2\2\u00b0\6\3\2\2\2\u00b1\u00b2\7G\2\2\u00b2"+
		"\u00b3\7Z\2\2\u00b3\u00b4\7K\2\2\u00b4\u00b5\7U\2\2\u00b5\u00b6\7V\2\2"+
		"\u00b6\u00b7\7U\2\2\u00b7\b\3\2\2\2\u00b8\u00b9\7.\2\2\u00b9\n\3\2\2\2"+
		"\u00ba\u00bb\7F\2\2\u00bb\u00bc\7T\2\2\u00bc\u00bd\7Q\2\2\u00bd\u00be"+
		"\7R\2\2\u00be\u00bf\7\"\2\2\u00bf\u00c0\7X\2\2\u00c0\u00c1\7K\2\2\u00c1"+
		"\u00c2\7G\2\2\u00c2\u00c3\7Y\2\2\u00c3\f\3\2\2\2\u00c4\u00c5\7Y\2\2\u00c5"+
		"\u00c6\7J\2\2\u00c6\u00c7\7G\2\2\u00c7\u00c8\7T\2\2\u00c8\u00c9\7G\2\2"+
		"\u00c9\16\3\2\2\2\u00ca\u00cb\7J\2\2\u00cb\u00cc\7C\2\2\u00cc\u00cd\7"+
		"X\2\2\u00cd\u00ce\7K\2\2\u00ce\u00cf\7P\2\2\u00cf\u00d0\7I\2\2\u00d0\20"+
		"\3\2\2\2\u00d1\u00d2\7*\2\2\u00d2\22\3\2\2\2\u00d3\u00d4\7F\2\2\u00d4"+
		"\u00d5\7G\2\2\u00d5\u00d6\7N\2\2\u00d6\u00d7\7G\2\2\u00d7\u00d8\7V\2\2"+
		"\u00d8\u00d9\7G\2\2\u00d9\u00da\7\"\2\2\u00da\u00db\7H\2\2\u00db\u00dc"+
		"\7T\2\2\u00dc\u00dd\7Q\2\2\u00dd\u00de\7O\2\2\u00de\24\3\2\2\2\u00df\u00e0"+
		"\7F\2\2\u00e0\u00e1\7T\2\2\u00e1\u00e2\7Q\2\2\u00e2\u00e3\7R\2\2\u00e3"+
		"\u00e4\7\"\2\2\u00e4\u00e5\7V\2\2\u00e5\u00e6\7C\2\2\u00e6\u00e7\7D\2"+
		"\2\u00e7\u00e8\7N\2\2\u00e8\u00e9\7G\2\2\u00e9\26\3\2\2\2\u00ea\u00eb"+
		"\7R\2\2\u00eb\u00ec\7T\2\2\u00ec\u00ed\7K\2\2\u00ed\u00ee\7O\2\2\u00ee"+
		"\u00ef\7C\2\2\u00ef\u00f0\7T\2\2\u00f0\u00f1\7[\2\2\u00f1\u00f2\7\"\2"+
		"\2\u00f2\u00f3\7M\2\2\u00f3\u00f4\7G\2\2\u00f4\u00f5\7[\2\2\u00f5\30\3"+
		"\2\2\2\u00f6\u00f7\7C\2\2\u00f7\u00f8\7U\2\2\u00f8\32\3\2\2\2\u00f9\u00fa"+
		"\7}\2\2\u00fa\34\3\2\2\2\u00fb\u00fc\7N\2\2\u00fc\u00fd\7Q\2\2\u00fd\u00fe"+
		"\7C\2\2\u00fe\u00ff\7F\2\2\u00ff\36\3\2\2\2\u0100\u0101\7D\2\2\u0101\u0102"+
		"\7K\2\2\u0102\u0103\7I\2\2\u0103\u0104\7K\2\2\u0104\u0105\7P\2\2\u0105"+
		"\u0106\7V\2\2\u0106 \3\2\2\2\u0107\u0108\7\177\2\2\u0108\"\3\2\2\2\u0109"+
		"\u010a\7Q\2\2\u010a\u010b\7T\2\2\u010b\u010c\7F\2\2\u010c\u010d\7G\2\2"+
		"\u010d\u010e\7T\2\2\u010e\u010f\7\"\2\2\u010f\u0110\7D\2\2\u0110\u0111"+
		"\7[\2\2\u0111$\3\2\2\2\u0112\u0113\7L\2\2\u0113\u0114\7Q\2\2\u0114\u0115"+
		"\7K\2\2\u0115\u0116\7P\2\2\u0116&\3\2\2\2\u0117\u0118\7U\2\2\u0118\u0119"+
		"\7G\2\2\u0119\u011a\7V\2\2\u011a(\3\2\2\2\u011b\u011c\7K\2\2\u011c\u011d"+
		"\7P\2\2\u011d\u011e\7V\2\2\u011e\u011f\7Q\2\2\u011f*\3\2\2\2\u0120\u0121"+
		"\7X\2\2\u0121\u0122\7C\2\2\u0122\u0123\7T\2\2\u0123\u0124\7E\2\2\u0124"+
		"\u0125\7J\2\2\u0125\u0126\7C\2\2\u0126\u0127\7T\2\2\u0127,\3\2\2\2\u0128"+
		"\u0129\7K\2\2\u0129\u012a\7P\2\2\u012a\u012b\7U\2\2\u012b\u012c\7G\2\2"+
		"\u012c\u012d\7T\2\2\u012d\u012e\7V\2\2\u012e\u012f\7\"\2\2\u012f\u0130"+
		"\7K\2\2\u0130\u0131\7P\2\2\u0131\u0132\7V\2\2\u0132\u0133\7Q\2\2\u0133"+
		".\3\2\2\2\u0134\u0135\7+\2\2\u0135\60\3\2\2\2\u0136\u0137\7\60\2\2\u0137"+
		"\62\3\2\2\2\u0138\u0139\7E\2\2\u0139\u013a\7J\2\2\u013a\u013b\7C\2\2\u013b"+
		"\u013c\7T\2\2\u013c\64\3\2\2\2\u013d\u013e\7-\2\2\u013e\66\3\2\2\2\u013f"+
		"\u0140\7W\2\2\u0140\u0141\7R\2\2\u0141\u0142\7F\2\2\u0142\u0143\7C\2\2"+
		"\u0143\u0144\7V\2\2\u0144\u0145\7G\2\2\u01458\3\2\2\2\u0146\u0147\7E\2"+
		"\2\u0147\u0148\7T\2\2\u0148\u0149\7G\2\2\u0149\u014a\7C\2\2\u014a\u014b"+
		"\7V\2\2\u014b\u014c\7G\2\2\u014c\u014d\7\"\2\2\u014d\u014e\7X\2\2\u014e"+
		"\u014f\7K\2\2\u014f\u0150\7G\2\2\u0150\u0151\7Y\2\2\u0151:\3\2\2\2\u0152"+
		"\u0153\7Q\2\2\u0153\u0154\7P\2\2\u0154\u0155\7N\2\2\u0155\u0156\7[\2\2"+
		"\u0156<\3\2\2\2\u0157\u0158\7E\2\2\u0158\u0159\7T\2\2\u0159\u015a\7G\2"+
		"\2\u015a\u015b\7C\2\2\u015b\u015c\7V\2\2\u015c\u015d\7G\2\2\u015d>\3\2"+
		"\2\2\u015e\u015f\7T\2\2\u015f\u0160\7Q\2\2\u0160\u0161\7Y\2\2\u0161@\3"+
		"\2\2\2\u0162\u0163\7Q\2\2\u0163\u0164\7P\2\2\u0164B\3\2\2\2\u0165\u0166"+
		"\7T\2\2\u0166\u0167\7Q\2\2\u0167\u0168\7Y\2\2\u0168\u0169\7U\2\2\u0169"+
		"D\3\2\2\2\u016a\u016b\7F\2\2\u016b\u016c\7T\2\2\u016c\u016d\7Q\2\2\u016d"+
		"\u016e\7R\2\2\u016e\u016f\7\"\2\2\u016f\u0170\7K\2\2\u0170\u0171\7P\2"+
		"\2\u0171\u0172\7F\2\2\u0172\u0173\7G\2\2\u0173\u0174\7Z\2\2\u0174F\3\2"+
		"\2\2\u0175\u0176\7F\2\2\u0176\u0177\7Q\2\2\u0177\u0178\7W\2\2\u0178\u0179"+
		"\7D\2\2\u0179\u017a\7N\2\2\u017a\u017b\7G\2\2\u017bH\3\2\2\2\u017c\u017d"+
		"\7F\2\2\u017d\u017e\7G\2\2\u017e\u017f\7N\2\2\u017f\u0180\7K\2\2\u0180"+
		"\u0181\7O\2\2\u0181\u0182\7K\2\2\u0182\u0183\7V\2\2\u0183\u0184\7G\2\2"+
		"\u0184\u0185\7T\2\2\u0185J\3\2\2\2\u0186\u0187\7H\2\2\u0187\u0188\7N\2"+
		"\2\u0188\u0189\7Q\2\2\u0189\u018a\7C\2\2\u018a\u018b\7V\2\2\u018bL\3\2"+
		"\2\2\u018c\u018d\7X\2\2\u018d\u018e\7C\2\2\u018e\u018f\7N\2\2\u018f\u0190"+
		"\7W\2\2\u0190\u0191\7G\2\2\u0191\u0192\7U\2\2\u0192\u0193\7*\2\2\u0193"+
		"N\3\2\2\2\u0194\u0195\7E\2\2\u0195\u0196\7T\2\2\u0196\u0197\7G\2\2\u0197"+
		"\u0198\7C\2\2\u0198\u0199\7V\2\2\u0199\u019a\7G\2\2\u019a\u019b\7\"\2"+
		"\2\u019b\u019c\7V\2\2\u019c\u019d\7C\2\2\u019d\u019e\7D\2\2\u019e\u019f"+
		"\7N\2\2\u019f\u01a0\7G\2\2\u01a0P\3\2\2\2\u01a1\u01a2\7I\2\2\u01a2\u01a3"+
		"\7T\2\2\u01a3\u01a4\7Q\2\2\u01a4\u01a5\7W\2\2\u01a5\u01a6\7R\2\2\u01a6"+
		"\u01a7\7\"\2\2\u01a7\u01a8\7D\2\2\u01a8\u01a9\7[\2\2\u01a9R\3\2\2\2\u01aa"+
		"\u01ab\7Y\2\2\u01ab\u01ac\7K\2\2\u01ac\u01ad\7V\2\2\u01ad\u01ae\7J\2\2"+
		"\u01aeT\3\2\2\2\u01af\u01b0\7H\2\2\u01b0\u01b1\7T\2\2\u01b1\u01b2\7Q\2"+
		"\2\u01b2\u01b3\7O\2\2\u01b3V\3\2\2\2\u01b4\u01b5\7\61\2\2\u01b5X\3\2\2"+
		"\2\u01b6\u01b7\7H\2\2\u01b7\u01b8\7G\2\2\u01b8\u01b9\7V\2\2\u01b9\u01ba"+
		"\7E\2\2\u01ba\u01bb\7J\2\2\u01bb\u01bc\7\"\2\2\u01bc\u01bd\7H\2\2\u01bd"+
		"\u01be\7K\2\2\u01be\u01bf\7T\2\2\u01bf\u01c0\7U\2\2\u01c0\u01c1\7V\2\2"+
		"\u01c1Z\3\2\2\2\u01c2\u01c3\7~\2\2\u01c3\\\3\2\2\2\u01c4\u01c5\7U\2\2"+
		"\u01c5\u01c6\7G\2\2\u01c6\u01c7\7N\2\2\u01c7\u01c8\7G\2\2\u01c8\u01c9"+
		"\7E\2\2\u01c9\u01ca\7V\2\2\u01ca^\3\2\2\2\u01cb\u01cc\7K\2\2\u01cc\u01cd"+
		"\7P\2\2\u01cd\u01ce\7V\2\2\u01ce\u01cf\7G\2\2\u01cf\u01d0\7I\2\2\u01d0"+
		"\u01d1\7G\2\2\u01d1\u01d2\7T\2\2\u01d2`\3\2\2\2\u01d3\u01d8\7)\2\2\u01d4"+
		"\u01d7\5c\62\2\u01d5\u01d7\13\2\2\2\u01d6\u01d4\3\2\2\2\u01d6\u01d5\3"+
		"\2\2\2\u01d7\u01da\3\2\2\2\u01d8\u01d9\3\2\2\2\u01d8\u01d6\3\2\2\2\u01d9"+
		"\u01db\3\2\2\2\u01da\u01d8\3\2\2\2\u01db\u01dc\7)\2\2\u01dcb\3\2\2\2\u01dd"+
		"\u01de\7^\2\2\u01de\u01df\7)\2\2\u01dfd\3\2\2\2\u01e0\u01e1\7,\2\2\u01e1"+
		"f\3\2\2\2\u01e2\u01e3\7E\2\2\u01e3\u01e4\7Q\2\2\u01e4\u01e5\7W\2\2\u01e5"+
		"\u01e6\7P\2\2\u01e6\u01e7\7V\2\2\u01e7h\3\2\2\2\u01e8\u01e9\7~\2\2\u01e9"+
		"\u01ea\7~\2\2\u01eaj\3\2\2\2\u01eb\u01ec\7/\2\2\u01ecl\3\2\2\2\u01ed\u01ee"+
		"\7?\2\2\u01een\3\2\2\2\u01ef\u01f0\7>\2\2\u01f0\u021e\7@\2\2\u01f1\u01f2"+
		"\7#\2\2\u01f2\u021e\7?\2\2\u01f3\u01f4\7>\2\2\u01f4\u021e\7?\2\2\u01f5"+
		"\u021e\7>\2\2\u01f6\u01f7\7@\2\2\u01f7\u021e\7?\2\2\u01f8\u021e\7@\2\2"+
		"\u01f9\u01fa\7G\2\2\u01fa\u01fb\7S\2\2\u01fb\u01fc\7W\2\2\u01fc\u01fd"+
		"\7C\2\2\u01fd\u01fe\7N\2\2\u01fe\u021e\7U\2\2\u01ff\u0200\7P\2\2\u0200"+
		"\u0201\7Q\2\2\u0201\u0202\7V\2\2\u0202\u0203\7\"\2\2\u0203\u0204\7G\2"+
		"\2\u0204\u0205\7S\2\2\u0205\u0206\7W\2\2\u0206\u0207\7C\2\2\u0207\u0208"+
		"\7N\2\2\u0208\u021e\7U\2\2\u0209\u020a\7N\2\2\u020a\u020b\7K\2\2\u020b"+
		"\u020c\7M\2\2\u020c\u021e\7G\2\2\u020d\u020e\7P\2\2\u020e\u020f\7Q\2\2"+
		"\u020f\u0210\7V\2\2\u0210\u0211\7\"\2\2\u0211\u0212\7N\2\2\u0212\u0213"+
		"\7K\2\2\u0213\u0214\7M\2\2\u0214\u021e\7G\2\2\u0215\u0216\7K\2\2\u0216"+
		"\u021e\7P\2\2\u0217\u0218\7P\2\2\u0218\u0219\7Q\2\2\u0219\u021a\7V\2\2"+
		"\u021a\u021b\7\"\2\2\u021b\u021c\7K\2\2\u021c\u021e\7P\2\2\u021d\u01ef"+
		"\3\2\2\2\u021d\u01f1\3\2\2\2\u021d\u01f3\3\2\2\2\u021d\u01f5\3\2\2\2\u021d"+
		"\u01f6\3\2\2\2\u021d\u01f8\3\2\2\2\u021d\u01f9\3\2\2\2\u021d\u01ff\3\2"+
		"\2\2\u021d\u0209\3\2\2\2\u021d\u020d\3\2\2\2\u021d\u0215\3\2\2\2\u021d"+
		"\u0217\3\2\2\2\u021ep\3\2\2\2\u021f\u0220\7K\2\2\u0220\u0221\7U\2\2\u0221"+
		"\u0222\7\"\2\2\u0222\u0223\7P\2\2\u0223\u0224\7Q\2\2\u0224\u0225\7V\2"+
		"\2\u0225\u0226\7\"\2\2\u0226\u0227\7P\2\2\u0227\u0228\7W\2\2\u0228\u0229"+
		"\7N\2\2\u0229\u0232\7N\2\2\u022a\u022b\7K\2\2\u022b\u022c\7U\2\2\u022c"+
		"\u022d\7\"\2\2\u022d\u022e\7P\2\2\u022e\u022f\7W\2\2\u022f\u0230\7N\2"+
		"\2\u0230\u0232\7N\2\2\u0231\u021f\3\2\2\2\u0231\u022a\3\2\2\2\u0232r\3"+
		"\2\2\2\u0233\u0234\7C\2\2\u0234\u0235\7P\2\2\u0235\u0236\7F\2\2\u0236"+
		"t\3\2\2\2\u0237\u0238\7Q\2\2\u0238\u0239\7T\2\2\u0239v\3\2\2\2\u023a\u023b"+
		"\7P\2\2\u023b\u023c\7Q\2\2\u023c\u023d\7V\2\2\u023dx\3\2\2\2\u023e\u023f"+
		"\7P\2\2\u023f\u0240\7W\2\2\u0240\u0241\7N\2\2\u0241\u0242\7N\2\2\u0242"+
		"z\3\2\2\2\u0243\u0244\7C\2\2\u0244\u0245\7U\2\2\u0245\u024b\7E\2\2\u0246"+
		"\u0247\7F\2\2\u0247\u0248\7G\2\2\u0248\u0249\7U\2\2\u0249\u024b\7E\2\2"+
		"\u024a\u0243\3\2\2\2\u024a\u0246\3\2\2\2\u024b|\3\2\2\2\u024c\u024d\7"+
		"K\2\2\u024d\u024e\7P\2\2\u024e\u024f\7P\2\2\u024f\u0250\7G\2\2\u0250\u027e"+
		"\7T\2\2\u0251\u0252\7N\2\2\u0252\u0253\7G\2\2\u0253\u0254\7H\2\2\u0254"+
		"\u027e\7V\2\2\u0255\u0256\7N\2\2\u0256\u0257\7G\2\2\u0257\u0258\7H\2\2"+
		"\u0258\u0259\7V\2\2\u0259\u025a\7\"\2\2\u025a\u025b\7Q\2\2\u025b\u025c"+
		"\7W\2\2\u025c\u025d\7V\2\2\u025d\u025e\7G\2\2\u025e\u027e\7T\2\2\u025f"+
		"\u0260\7T\2\2\u0260\u0261\7K\2\2\u0261\u0262\7I\2\2\u0262\u0263\7J\2\2"+
		"\u0263\u027e\7V\2\2\u0264\u0265\7T\2\2\u0265\u0266\7K\2\2\u0266\u0267"+
		"\7I\2\2\u0267\u0268\7J\2\2\u0268\u0269\7V\2\2\u0269\u026a\7\"\2\2\u026a"+
		"\u026b\7Q\2\2\u026b\u026c\7W\2\2\u026c\u026d\7V\2\2\u026d\u026e\7G\2\2"+
		"\u026e\u027e\7T\2\2\u026f\u0270\7H\2\2\u0270\u0271\7W\2\2\u0271\u0272"+
		"\7N\2\2\u0272\u027e\7N\2\2\u0273\u0274\7H\2\2\u0274\u0275\7W\2\2\u0275"+
		"\u0276\7N\2\2\u0276\u0277\7N\2\2\u0277\u0278\7\"\2\2\u0278\u0279\7Q\2"+
		"\2\u0279\u027a\7W\2\2\u027a\u027b\7V\2\2\u027b\u027c\7G\2\2\u027c\u027e"+
		"\7T\2\2\u027d\u024c\3\2\2\2\u027d\u0251\3\2\2\2\u027d\u0255\3\2\2\2\u027d"+
		"\u025f\3\2\2\2\u027d\u0264\3\2\2\2\u027d\u026f\3\2\2\2\u027d\u0273\3\2"+
		"\2\2\u027e~\3\2\2\2\u027f\u0280\7E\2\2\u0280\u0281\7T\2\2\u0281\u0282"+
		"\7Q\2\2\u0282\u0283\7U\2\2\u0283\u0284\7U\2\2\u0284\u0285\7\"\2\2\u0285"+
		"\u0286\7L\2\2\u0286\u0287\7Q\2\2\u0287\u0288\7K\2\2\u0288\u0289\7P\2\2"+
		"\u0289\u0080\3\2\2\2\u028a\u028b\7W\2\2\u028b\u028c\7P\2\2\u028c\u028d"+
		"\7K\2\2\u028d\u028e\7Q\2\2\u028e\u028f\7P\2\2\u028f\u0290\7\"\2\2\u0290"+
		"\u0291\7C\2\2\u0291\u0292\7N\2\2\u0292\u02a8\7N\2\2\u0293\u0294\7W\2\2"+
		"\u0294\u0295\7P\2\2\u0295\u0296\7K\2\2\u0296\u0297\7Q\2\2\u0297\u02a8"+
		"\7P\2\2\u0298\u0299\7K\2\2\u0299\u029a\7P\2\2\u029a\u029b\7V\2\2\u029b"+
		"\u029c\7G\2\2\u029c\u029d\7T\2\2\u029d\u029e\7U\2\2\u029e\u029f\7G\2\2"+
		"\u029f\u02a0\7E\2\2\u02a0\u02a8\7V\2\2\u02a1\u02a2\7G\2\2\u02a2\u02a3"+
		"\7Z\2\2\u02a3\u02a4\7E\2\2\u02a4\u02a5\7G\2\2\u02a5\u02a6\7R\2\2\u02a6"+
		"\u02a8\7V\2\2\u02a7\u028a\3\2\2\2\u02a7\u0293\3\2\2\2\u02a7\u0298\3\2"+
		"\2\2\u02a7\u02a1\3\2\2\2\u02a8\u0082\3\2\2\2\u02a9\u02aa\7F\2\2\u02aa"+
		"\u02ab\7K\2\2\u02ab\u02ac\7U\2\2\u02ac\u02ad\7V\2\2\u02ad\u02ae\7K\2\2"+
		"\u02ae\u02af\7P\2\2\u02af\u02b0\7E\2\2\u02b0\u02b1\7V\2\2\u02b1\u0084"+
		"\3\2\2\2\u02b2\u02b4\t\2\2\2\u02b3\u02b2\3\2\2\2\u02b4\u02b5\3\2\2\2\u02b5"+
		"\u02b3\3\2\2\2\u02b5\u02b6\3\2\2\2\u02b6\u0086\3\2\2\2\u02b7\u02b9\t\3"+
		"\2\2\u02b8\u02b7\3\2\2\2\u02b9\u02ba\3\2\2\2\u02ba\u02b8\3\2\2\2\u02ba"+
		"\u02bb\3\2\2\2\u02bb\u02bc\3\2\2\2\u02bc\u02bd\bD\2\2\u02bd\u0088\3\2"+
		"\2\2\u02be\u02bf\7W\2\2\u02bf\u02c0\7P\2\2\u02c0\u02c1\7K\2\2\u02c1\u02c2"+
		"\7S\2\2\u02c2\u02c3\7W\2\2\u02c3\u02c4\7G\2\2\u02c4\u008a\3\2\2\2\u02c5"+
		"\u02c6\7T\2\2\u02c6\u02c7\7G\2\2\u02c7\u02c8\7R\2\2\u02c8\u02c9\7N\2\2"+
		"\u02c9\u02ca\7C\2\2\u02ca\u02cb\7E\2\2\u02cb\u02cc\7G\2\2\u02cc\u008c"+
		"\3\2\2\2\u02cd\u02ce\7T\2\2\u02ce\u02cf\7G\2\2\u02cf\u02d0\7U\2\2\u02d0"+
		"\u02d1\7W\2\2\u02d1\u02d2\7O\2\2\u02d2\u02d3\7G\2\2\u02d3\u008e\3\2\2"+
		"\2\u02d4\u02d5\7P\2\2\u02d5\u02d6\7Q\2\2\u02d6\u02d7\7P\2\2\u02d7\u02d8"+
		"\7G\2\2\u02d8\u0090\3\2\2\2\u02d9\u02da\7C\2\2\u02da\u02db\7N\2\2\u02db"+
		"\u02dc\7N\2\2\u02dc\u0092\3\2\2\2\u02dd\u02de\7C\2\2\u02de\u02df\7P\2"+
		"\2\u02df\u02e0\7[\2\2\u02e0\u0094\3\2\2\2\u02e1\u02e2\7J\2\2\u02e2\u02e3"+
		"\7C\2\2\u02e3\u02e4\7U\2\2\u02e4\u02e5\7J\2\2\u02e5\u0096\3\2\2\2\u02e6"+
		"\u02e7\7T\2\2\u02e7\u02e8\7C\2\2\u02e8\u02e9\7P\2\2\u02e9\u02ea\7I\2\2"+
		"\u02ea\u02eb\7G\2\2\u02eb\u0098\3\2\2\2\u02ec\u02ed\7F\2\2\u02ed\u02ee"+
		"\7C\2\2\u02ee\u02ef\7V\2\2\u02ef\u02f0\7G\2\2\u02f0\u009a\3\2\2\2\u02f1"+
		"\u02f5\t\4\2\2\u02f2\u02f4\t\5\2\2\u02f3\u02f2\3\2\2\2\u02f4\u02f7\3\2"+
		"\2\2\u02f5\u02f3\3\2\2\2\u02f5\u02f6\3\2\2\2\u02f6\u009c\3\2\2\2\u02f7"+
		"\u02f5\3\2\2\2\u02f8\u02f9\13\2\2\2\u02f9\u009e\3\2\2\2\16\2\u01d6\u01d8"+
		"\u021d\u0231\u024a\u027d\u02a7\u02b5\u02ba\u02f3\u02f5\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}