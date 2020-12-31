// Generated from C:/Users/aashv/Documents/GitHub/jsh-team-41/src/main/antlr4/uk/ac/ucl/jsh/.antlr\CallGrammar.g4 by ANTLR 4.9
package uk.ac.ucl.jsh.Parser.Call;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CallGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WHITESPACE=1, UNQUOTED=2, PIPE=3, SEMICOLON=4, INPUTREDIRECTION=5, OUTPUTREDIRECTION=6, 
		SINGLEQUOTE=7, DOUBLEQUOTE=8, BACKQUOTE=9;
	public static final int
		RULE_start = 0, RULE_arguments = 1, RULE_redirection = 2, RULE_call_type = 3, 
		RULE_argument = 4, RULE_single_quote = 5, RULE_single_quote_contents = 6, 
		RULE_double_quote = 7, RULE_double_quote_options = 8, RULE_back_quote = 9, 
		RULE_back_quote_contents = 10;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "arguments", "redirection", "call_type", "argument", "single_quote", 
			"single_quote_contents", "double_quote", "double_quote_options", "back_quote", 
			"back_quote_contents"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, "'|'", "';'", "'<'", "'>'", "'''", "'\"'", "'`'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "WHITESPACE", "UNQUOTED", "PIPE", "SEMICOLON", "INPUTREDIRECTION", 
			"OUTPUTREDIRECTION", "SINGLEQUOTE", "DOUBLEQUOTE", "BACKQUOTE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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
	public String getGrammarFileName() { return "CallGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CallGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class StartContext extends ParserRuleContext {
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public TerminalNode EOF() { return getToken(CallGrammarParser.EOF, 0); }
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CallGrammarListener ) ((CallGrammarListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CallGrammarListener ) ((CallGrammarListener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CallGrammarVisitor ) return ((CallGrammarVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(22);
			arguments();
			setState(23);
			match(EOF);
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

	public static class ArgumentsContext extends ParserRuleContext {
		public ArgumentContext argument() {
			return getRuleContext(ArgumentContext.class,0);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(CallGrammarParser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(CallGrammarParser.WHITESPACE, i);
		}
		public List<RedirectionContext> redirection() {
			return getRuleContexts(RedirectionContext.class);
		}
		public RedirectionContext redirection(int i) {
			return getRuleContext(RedirectionContext.class,i);
		}
		public List<Call_typeContext> call_type() {
			return getRuleContexts(Call_typeContext.class);
		}
		public Call_typeContext call_type(int i) {
			return getRuleContext(Call_typeContext.class,i);
		}
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CallGrammarListener ) ((CallGrammarListener)listener).enterArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CallGrammarListener ) ((CallGrammarListener)listener).exitArguments(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CallGrammarVisitor ) return ((CallGrammarVisitor<? extends T>)visitor).visitArguments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_arguments);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(25);
				match(WHITESPACE);
				}
				}
				setState(30);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(36);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==INPUTREDIRECTION || _la==OUTPUTREDIRECTION) {
				{
				{
				setState(31);
				redirection();
				setState(32);
				match(WHITESPACE);
				}
				}
				setState(38);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(39);
			argument();
			setState(44);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(40);
					match(WHITESPACE);
					setState(41);
					call_type(0);
					}
					} 
				}
				setState(46);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			setState(50);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(47);
					match(WHITESPACE);
					}
					} 
				}
				setState(52);
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
			exitRule();
		}
		return _localctx;
	}

	public static class RedirectionContext extends ParserRuleContext {
		public TerminalNode INPUTREDIRECTION() { return getToken(CallGrammarParser.INPUTREDIRECTION, 0); }
		public ArgumentContext argument() {
			return getRuleContext(ArgumentContext.class,0);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(CallGrammarParser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(CallGrammarParser.WHITESPACE, i);
		}
		public TerminalNode OUTPUTREDIRECTION() { return getToken(CallGrammarParser.OUTPUTREDIRECTION, 0); }
		public RedirectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_redirection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CallGrammarListener ) ((CallGrammarListener)listener).enterRedirection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CallGrammarListener ) ((CallGrammarListener)listener).exitRedirection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CallGrammarVisitor ) return ((CallGrammarVisitor<? extends T>)visitor).visitRedirection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RedirectionContext redirection() throws RecognitionException {
		RedirectionContext _localctx = new RedirectionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_redirection);
		int _la;
		try {
			setState(68);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INPUTREDIRECTION:
				enterOuterAlt(_localctx, 1);
				{
				setState(53);
				match(INPUTREDIRECTION);
				setState(57);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==WHITESPACE) {
					{
					{
					setState(54);
					match(WHITESPACE);
					}
					}
					setState(59);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(60);
				argument();
				}
				break;
			case OUTPUTREDIRECTION:
				enterOuterAlt(_localctx, 2);
				{
				setState(61);
				match(OUTPUTREDIRECTION);
				setState(63); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(62);
					match(WHITESPACE);
					}
					}
					setState(65); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==WHITESPACE );
				setState(67);
				argument();
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

	public static class Call_typeContext extends ParserRuleContext {
		public RedirectionContext redirection() {
			return getRuleContext(RedirectionContext.class,0);
		}
		public ArgumentContext argument() {
			return getRuleContext(ArgumentContext.class,0);
		}
		public Call_typeContext call_type() {
			return getRuleContext(Call_typeContext.class,0);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(CallGrammarParser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(CallGrammarParser.WHITESPACE, i);
		}
		public Call_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_call_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CallGrammarListener ) ((CallGrammarListener)listener).enterCall_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CallGrammarListener ) ((CallGrammarListener)listener).exitCall_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CallGrammarVisitor ) return ((CallGrammarVisitor<? extends T>)visitor).visitCall_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Call_typeContext call_type() throws RecognitionException {
		return call_type(0);
	}

	private Call_typeContext call_type(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Call_typeContext _localctx = new Call_typeContext(_ctx, _parentState);
		Call_typeContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_call_type, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INPUTREDIRECTION:
			case OUTPUTREDIRECTION:
				{
				setState(71);
				redirection();
				}
				break;
			case UNQUOTED:
			case SINGLEQUOTE:
			case DOUBLEQUOTE:
			case BACKQUOTE:
				{
				setState(72);
				argument();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(84);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Call_typeContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_call_type);
					setState(75);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(77); 
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
						case 1:
							{
							{
							setState(76);
							match(WHITESPACE);
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(79); 
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
					} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
					setState(81);
					arguments();
					}
					} 
				}
				setState(86);
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

	public static class ArgumentContext extends ParserRuleContext {
		public Token unquotedArgument;
		public Single_quoteContext singleQuoteArgument;
		public Double_quoteContext doubleQuoteArgument;
		public Back_quoteContext backQuoteArgument;
		public TerminalNode UNQUOTED() { return getToken(CallGrammarParser.UNQUOTED, 0); }
		public ArgumentContext argument() {
			return getRuleContext(ArgumentContext.class,0);
		}
		public Single_quoteContext single_quote() {
			return getRuleContext(Single_quoteContext.class,0);
		}
		public Double_quoteContext double_quote() {
			return getRuleContext(Double_quoteContext.class,0);
		}
		public Back_quoteContext back_quote() {
			return getRuleContext(Back_quoteContext.class,0);
		}
		public ArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CallGrammarListener ) ((CallGrammarListener)listener).enterArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CallGrammarListener ) ((CallGrammarListener)listener).exitArgument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CallGrammarVisitor ) return ((CallGrammarVisitor<? extends T>)visitor).visitArgument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentContext argument() throws RecognitionException {
		ArgumentContext _localctx = new ArgumentContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_argument);
		try {
			setState(103);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case UNQUOTED:
				enterOuterAlt(_localctx, 1);
				{
				setState(87);
				((ArgumentContext)_localctx).unquotedArgument = match(UNQUOTED);
				setState(89);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(88);
					argument();
					}
					break;
				}
				}
				break;
			case SINGLEQUOTE:
				enterOuterAlt(_localctx, 2);
				{
				setState(91);
				((ArgumentContext)_localctx).singleQuoteArgument = single_quote();
				setState(93);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
				case 1:
					{
					setState(92);
					argument();
					}
					break;
				}
				}
				break;
			case DOUBLEQUOTE:
				enterOuterAlt(_localctx, 3);
				{
				setState(95);
				((ArgumentContext)_localctx).doubleQuoteArgument = double_quote();
				setState(97);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
				case 1:
					{
					setState(96);
					argument();
					}
					break;
				}
				}
				break;
			case BACKQUOTE:
				enterOuterAlt(_localctx, 4);
				{
				setState(99);
				((ArgumentContext)_localctx).backQuoteArgument = back_quote();
				setState(101);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
				case 1:
					{
					setState(100);
					argument();
					}
					break;
				}
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

	public static class Single_quoteContext extends ParserRuleContext {
		public Single_quote_contentsContext contents;
		public List<TerminalNode> SINGLEQUOTE() { return getTokens(CallGrammarParser.SINGLEQUOTE); }
		public TerminalNode SINGLEQUOTE(int i) {
			return getToken(CallGrammarParser.SINGLEQUOTE, i);
		}
		public Single_quote_contentsContext single_quote_contents() {
			return getRuleContext(Single_quote_contentsContext.class,0);
		}
		public Single_quoteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_single_quote; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CallGrammarListener ) ((CallGrammarListener)listener).enterSingle_quote(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CallGrammarListener ) ((CallGrammarListener)listener).exitSingle_quote(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CallGrammarVisitor ) return ((CallGrammarVisitor<? extends T>)visitor).visitSingle_quote(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Single_quoteContext single_quote() throws RecognitionException {
		Single_quoteContext _localctx = new Single_quoteContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_single_quote);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			match(SINGLEQUOTE);
			setState(106);
			((Single_quoteContext)_localctx).contents = single_quote_contents();
			setState(107);
			match(SINGLEQUOTE);
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

	public static class Single_quote_contentsContext extends ParserRuleContext {
		public List<TerminalNode> WHITESPACE() { return getTokens(CallGrammarParser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(CallGrammarParser.WHITESPACE, i);
		}
		public List<TerminalNode> UNQUOTED() { return getTokens(CallGrammarParser.UNQUOTED); }
		public TerminalNode UNQUOTED(int i) {
			return getToken(CallGrammarParser.UNQUOTED, i);
		}
		public List<TerminalNode> PIPE() { return getTokens(CallGrammarParser.PIPE); }
		public TerminalNode PIPE(int i) {
			return getToken(CallGrammarParser.PIPE, i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(CallGrammarParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(CallGrammarParser.SEMICOLON, i);
		}
		public List<TerminalNode> INPUTREDIRECTION() { return getTokens(CallGrammarParser.INPUTREDIRECTION); }
		public TerminalNode INPUTREDIRECTION(int i) {
			return getToken(CallGrammarParser.INPUTREDIRECTION, i);
		}
		public List<TerminalNode> OUTPUTREDIRECTION() { return getTokens(CallGrammarParser.OUTPUTREDIRECTION); }
		public TerminalNode OUTPUTREDIRECTION(int i) {
			return getToken(CallGrammarParser.OUTPUTREDIRECTION, i);
		}
		public List<TerminalNode> DOUBLEQUOTE() { return getTokens(CallGrammarParser.DOUBLEQUOTE); }
		public TerminalNode DOUBLEQUOTE(int i) {
			return getToken(CallGrammarParser.DOUBLEQUOTE, i);
		}
		public List<TerminalNode> BACKQUOTE() { return getTokens(CallGrammarParser.BACKQUOTE); }
		public TerminalNode BACKQUOTE(int i) {
			return getToken(CallGrammarParser.BACKQUOTE, i);
		}
		public Single_quote_contentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_single_quote_contents; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CallGrammarListener ) ((CallGrammarListener)listener).enterSingle_quote_contents(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CallGrammarListener ) ((CallGrammarListener)listener).exitSingle_quote_contents(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CallGrammarVisitor ) return ((CallGrammarVisitor<? extends T>)visitor).visitSingle_quote_contents(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Single_quote_contentsContext single_quote_contents() throws RecognitionException {
		Single_quote_contentsContext _localctx = new Single_quote_contentsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_single_quote_contents);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << WHITESPACE) | (1L << UNQUOTED) | (1L << PIPE) | (1L << SEMICOLON) | (1L << INPUTREDIRECTION) | (1L << OUTPUTREDIRECTION) | (1L << DOUBLEQUOTE) | (1L << BACKQUOTE))) != 0)) {
				{
				{
				setState(109);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << WHITESPACE) | (1L << UNQUOTED) | (1L << PIPE) | (1L << SEMICOLON) | (1L << INPUTREDIRECTION) | (1L << OUTPUTREDIRECTION) | (1L << DOUBLEQUOTE) | (1L << BACKQUOTE))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(114);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class Double_quoteContext extends ParserRuleContext {
		public List<TerminalNode> DOUBLEQUOTE() { return getTokens(CallGrammarParser.DOUBLEQUOTE); }
		public TerminalNode DOUBLEQUOTE(int i) {
			return getToken(CallGrammarParser.DOUBLEQUOTE, i);
		}
		public Double_quote_optionsContext double_quote_options() {
			return getRuleContext(Double_quote_optionsContext.class,0);
		}
		public Double_quoteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_double_quote; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CallGrammarListener ) ((CallGrammarListener)listener).enterDouble_quote(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CallGrammarListener ) ((CallGrammarListener)listener).exitDouble_quote(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CallGrammarVisitor ) return ((CallGrammarVisitor<? extends T>)visitor).visitDouble_quote(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Double_quoteContext double_quote() throws RecognitionException {
		Double_quoteContext _localctx = new Double_quoteContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_double_quote);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			match(DOUBLEQUOTE);
			setState(116);
			double_quote_options();
			setState(117);
			match(DOUBLEQUOTE);
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

	public static class Double_quote_optionsContext extends ParserRuleContext {
		public Token contents;
		public Double_quote_optionsContext double_quote_options() {
			return getRuleContext(Double_quote_optionsContext.class,0);
		}
		public TerminalNode WHITESPACE() { return getToken(CallGrammarParser.WHITESPACE, 0); }
		public TerminalNode UNQUOTED() { return getToken(CallGrammarParser.UNQUOTED, 0); }
		public TerminalNode PIPE() { return getToken(CallGrammarParser.PIPE, 0); }
		public TerminalNode SEMICOLON() { return getToken(CallGrammarParser.SEMICOLON, 0); }
		public TerminalNode INPUTREDIRECTION() { return getToken(CallGrammarParser.INPUTREDIRECTION, 0); }
		public TerminalNode SINGLEQUOTE() { return getToken(CallGrammarParser.SINGLEQUOTE, 0); }
		public Back_quoteContext back_quote() {
			return getRuleContext(Back_quoteContext.class,0);
		}
		public Double_quote_optionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_double_quote_options; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CallGrammarListener ) ((CallGrammarListener)listener).enterDouble_quote_options(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CallGrammarListener ) ((CallGrammarListener)listener).exitDouble_quote_options(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CallGrammarVisitor ) return ((CallGrammarVisitor<? extends T>)visitor).visitDouble_quote_options(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Double_quote_optionsContext double_quote_options() throws RecognitionException {
		Double_quote_optionsContext _localctx = new Double_quote_optionsContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_double_quote_options);
		int _la;
		try {
			setState(125);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WHITESPACE:
			case UNQUOTED:
			case PIPE:
			case SEMICOLON:
			case INPUTREDIRECTION:
			case SINGLEQUOTE:
				enterOuterAlt(_localctx, 1);
				{
				setState(119);
				((Double_quote_optionsContext)_localctx).contents = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << WHITESPACE) | (1L << UNQUOTED) | (1L << PIPE) | (1L << SEMICOLON) | (1L << INPUTREDIRECTION) | (1L << SINGLEQUOTE))) != 0)) ) {
					((Double_quote_optionsContext)_localctx).contents = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(120);
				double_quote_options();
				}
				break;
			case BACKQUOTE:
				enterOuterAlt(_localctx, 2);
				{
				setState(121);
				back_quote();
				setState(122);
				double_quote_options();
				}
				break;
			case DOUBLEQUOTE:
				enterOuterAlt(_localctx, 3);
				{
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

	public static class Back_quoteContext extends ParserRuleContext {
		public Back_quote_contentsContext contents;
		public List<TerminalNode> BACKQUOTE() { return getTokens(CallGrammarParser.BACKQUOTE); }
		public TerminalNode BACKQUOTE(int i) {
			return getToken(CallGrammarParser.BACKQUOTE, i);
		}
		public Back_quote_contentsContext back_quote_contents() {
			return getRuleContext(Back_quote_contentsContext.class,0);
		}
		public Back_quoteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_back_quote; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CallGrammarListener ) ((CallGrammarListener)listener).enterBack_quote(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CallGrammarListener ) ((CallGrammarListener)listener).exitBack_quote(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CallGrammarVisitor ) return ((CallGrammarVisitor<? extends T>)visitor).visitBack_quote(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Back_quoteContext back_quote() throws RecognitionException {
		Back_quoteContext _localctx = new Back_quoteContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_back_quote);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			match(BACKQUOTE);
			setState(128);
			((Back_quoteContext)_localctx).contents = back_quote_contents();
			setState(129);
			match(BACKQUOTE);
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

	public static class Back_quote_contentsContext extends ParserRuleContext {
		public List<TerminalNode> WHITESPACE() { return getTokens(CallGrammarParser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(CallGrammarParser.WHITESPACE, i);
		}
		public List<TerminalNode> UNQUOTED() { return getTokens(CallGrammarParser.UNQUOTED); }
		public TerminalNode UNQUOTED(int i) {
			return getToken(CallGrammarParser.UNQUOTED, i);
		}
		public List<TerminalNode> PIPE() { return getTokens(CallGrammarParser.PIPE); }
		public TerminalNode PIPE(int i) {
			return getToken(CallGrammarParser.PIPE, i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(CallGrammarParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(CallGrammarParser.SEMICOLON, i);
		}
		public List<TerminalNode> INPUTREDIRECTION() { return getTokens(CallGrammarParser.INPUTREDIRECTION); }
		public TerminalNode INPUTREDIRECTION(int i) {
			return getToken(CallGrammarParser.INPUTREDIRECTION, i);
		}
		public List<TerminalNode> OUTPUTREDIRECTION() { return getTokens(CallGrammarParser.OUTPUTREDIRECTION); }
		public TerminalNode OUTPUTREDIRECTION(int i) {
			return getToken(CallGrammarParser.OUTPUTREDIRECTION, i);
		}
		public List<TerminalNode> SINGLEQUOTE() { return getTokens(CallGrammarParser.SINGLEQUOTE); }
		public TerminalNode SINGLEQUOTE(int i) {
			return getToken(CallGrammarParser.SINGLEQUOTE, i);
		}
		public List<TerminalNode> DOUBLEQUOTE() { return getTokens(CallGrammarParser.DOUBLEQUOTE); }
		public TerminalNode DOUBLEQUOTE(int i) {
			return getToken(CallGrammarParser.DOUBLEQUOTE, i);
		}
		public Back_quote_contentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_back_quote_contents; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CallGrammarListener ) ((CallGrammarListener)listener).enterBack_quote_contents(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CallGrammarListener ) ((CallGrammarListener)listener).exitBack_quote_contents(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CallGrammarVisitor ) return ((CallGrammarVisitor<? extends T>)visitor).visitBack_quote_contents(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Back_quote_contentsContext back_quote_contents() throws RecognitionException {
		Back_quote_contentsContext _localctx = new Back_quote_contentsContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_back_quote_contents);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << WHITESPACE) | (1L << UNQUOTED) | (1L << PIPE) | (1L << SEMICOLON) | (1L << INPUTREDIRECTION) | (1L << OUTPUTREDIRECTION) | (1L << SINGLEQUOTE) | (1L << DOUBLEQUOTE))) != 0)) {
				{
				{
				setState(131);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << WHITESPACE) | (1L << UNQUOTED) | (1L << PIPE) | (1L << SEMICOLON) | (1L << INPUTREDIRECTION) | (1L << OUTPUTREDIRECTION) | (1L << SINGLEQUOTE) | (1L << DOUBLEQUOTE))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(136);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 3:
			return call_type_sempred((Call_typeContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean call_type_sempred(Call_typeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\13\u008c\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\3\2\3\2\3\2\3\3\7\3\35\n\3\f\3\16\3 \13\3\3\3\3\3\3\3\7"+
		"\3%\n\3\f\3\16\3(\13\3\3\3\3\3\3\3\7\3-\n\3\f\3\16\3\60\13\3\3\3\7\3\63"+
		"\n\3\f\3\16\3\66\13\3\3\4\3\4\7\4:\n\4\f\4\16\4=\13\4\3\4\3\4\3\4\6\4"+
		"B\n\4\r\4\16\4C\3\4\5\4G\n\4\3\5\3\5\3\5\5\5L\n\5\3\5\3\5\6\5P\n\5\r\5"+
		"\16\5Q\3\5\7\5U\n\5\f\5\16\5X\13\5\3\6\3\6\5\6\\\n\6\3\6\3\6\5\6`\n\6"+
		"\3\6\3\6\5\6d\n\6\3\6\3\6\5\6h\n\6\5\6j\n\6\3\7\3\7\3\7\3\7\3\b\7\bq\n"+
		"\b\f\b\16\bt\13\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u0080\n"+
		"\n\3\13\3\13\3\13\3\13\3\f\7\f\u0087\n\f\f\f\16\f\u008a\13\f\3\f\2\3\b"+
		"\r\2\4\6\b\n\f\16\20\22\24\26\2\5\4\2\3\b\n\13\4\2\3\7\t\t\3\2\3\n\2\u0095"+
		"\2\30\3\2\2\2\4\36\3\2\2\2\6F\3\2\2\2\bK\3\2\2\2\ni\3\2\2\2\fk\3\2\2\2"+
		"\16r\3\2\2\2\20u\3\2\2\2\22\177\3\2\2\2\24\u0081\3\2\2\2\26\u0088\3\2"+
		"\2\2\30\31\5\4\3\2\31\32\7\2\2\3\32\3\3\2\2\2\33\35\7\3\2\2\34\33\3\2"+
		"\2\2\35 \3\2\2\2\36\34\3\2\2\2\36\37\3\2\2\2\37&\3\2\2\2 \36\3\2\2\2!"+
		"\"\5\6\4\2\"#\7\3\2\2#%\3\2\2\2$!\3\2\2\2%(\3\2\2\2&$\3\2\2\2&\'\3\2\2"+
		"\2\')\3\2\2\2(&\3\2\2\2).\5\n\6\2*+\7\3\2\2+-\5\b\5\2,*\3\2\2\2-\60\3"+
		"\2\2\2.,\3\2\2\2./\3\2\2\2/\64\3\2\2\2\60.\3\2\2\2\61\63\7\3\2\2\62\61"+
		"\3\2\2\2\63\66\3\2\2\2\64\62\3\2\2\2\64\65\3\2\2\2\65\5\3\2\2\2\66\64"+
		"\3\2\2\2\67;\7\7\2\28:\7\3\2\298\3\2\2\2:=\3\2\2\2;9\3\2\2\2;<\3\2\2\2"+
		"<>\3\2\2\2=;\3\2\2\2>G\5\n\6\2?A\7\b\2\2@B\7\3\2\2A@\3\2\2\2BC\3\2\2\2"+
		"CA\3\2\2\2CD\3\2\2\2DE\3\2\2\2EG\5\n\6\2F\67\3\2\2\2F?\3\2\2\2G\7\3\2"+
		"\2\2HI\b\5\1\2IL\5\6\4\2JL\5\n\6\2KH\3\2\2\2KJ\3\2\2\2LV\3\2\2\2MO\f\3"+
		"\2\2NP\7\3\2\2ON\3\2\2\2PQ\3\2\2\2QO\3\2\2\2QR\3\2\2\2RS\3\2\2\2SU\5\4"+
		"\3\2TM\3\2\2\2UX\3\2\2\2VT\3\2\2\2VW\3\2\2\2W\t\3\2\2\2XV\3\2\2\2Y[\7"+
		"\4\2\2Z\\\5\n\6\2[Z\3\2\2\2[\\\3\2\2\2\\j\3\2\2\2]_\5\f\7\2^`\5\n\6\2"+
		"_^\3\2\2\2_`\3\2\2\2`j\3\2\2\2ac\5\20\t\2bd\5\n\6\2cb\3\2\2\2cd\3\2\2"+
		"\2dj\3\2\2\2eg\5\24\13\2fh\5\n\6\2gf\3\2\2\2gh\3\2\2\2hj\3\2\2\2iY\3\2"+
		"\2\2i]\3\2\2\2ia\3\2\2\2ie\3\2\2\2j\13\3\2\2\2kl\7\t\2\2lm\5\16\b\2mn"+
		"\7\t\2\2n\r\3\2\2\2oq\t\2\2\2po\3\2\2\2qt\3\2\2\2rp\3\2\2\2rs\3\2\2\2"+
		"s\17\3\2\2\2tr\3\2\2\2uv\7\n\2\2vw\5\22\n\2wx\7\n\2\2x\21\3\2\2\2yz\t"+
		"\3\2\2z\u0080\5\22\n\2{|\5\24\13\2|}\5\22\n\2}\u0080\3\2\2\2~\u0080\3"+
		"\2\2\2\177y\3\2\2\2\177{\3\2\2\2\177~\3\2\2\2\u0080\23\3\2\2\2\u0081\u0082"+
		"\7\13\2\2\u0082\u0083\5\26\f\2\u0083\u0084\7\13\2\2\u0084\25\3\2\2\2\u0085"+
		"\u0087\t\4\2\2\u0086\u0085\3\2\2\2\u0087\u008a\3\2\2\2\u0088\u0086\3\2"+
		"\2\2\u0088\u0089\3\2\2\2\u0089\27\3\2\2\2\u008a\u0088\3\2\2\2\24\36&."+
		"\64;CFKQV[_cgir\177\u0088";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}