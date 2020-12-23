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
		RULE_argument = 4, RULE_single_quote = 5, RULE_double_quote = 6, RULE_back_quote = 7;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "arguments", "redirection", "call_type", "argument", "single_quote", 
			"double_quote", "back_quote"
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
			setState(16);
			arguments();
			setState(17);
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
			setState(22);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(19);
				match(WHITESPACE);
				}
				}
				setState(24);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(30);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==INPUTREDIRECTION || _la==OUTPUTREDIRECTION) {
				{
				{
				setState(25);
				redirection();
				setState(26);
				match(WHITESPACE);
				}
				}
				setState(32);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(33);
			argument();
			setState(38);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(34);
					match(WHITESPACE);
					setState(35);
					call_type(0);
					}
					} 
				}
				setState(40);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			setState(44);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(41);
					match(WHITESPACE);
					}
					} 
				}
				setState(46);
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
			setState(61);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INPUTREDIRECTION:
				enterOuterAlt(_localctx, 1);
				{
				setState(47);
				match(INPUTREDIRECTION);
				setState(49); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(48);
					match(WHITESPACE);
					}
					}
					setState(51); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==WHITESPACE );
				setState(53);
				argument();
				}
				break;
			case OUTPUTREDIRECTION:
				enterOuterAlt(_localctx, 2);
				{
				setState(54);
				match(OUTPUTREDIRECTION);
				setState(56); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(55);
					match(WHITESPACE);
					}
					}
					setState(58); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==WHITESPACE );
				setState(60);
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
			setState(66);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INPUTREDIRECTION:
			case OUTPUTREDIRECTION:
				{
				setState(64);
				redirection();
				}
				break;
			case UNQUOTED:
			case SINGLEQUOTE:
			case DOUBLEQUOTE:
			case BACKQUOTE:
				{
				setState(65);
				argument();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(77);
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
					setState(68);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(70); 
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
						case 1:
							{
							{
							setState(69);
							match(WHITESPACE);
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(72); 
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
					} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
					setState(74);
					arguments();
					}
					} 
				}
				setState(79);
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
			setState(96);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case UNQUOTED:
				enterOuterAlt(_localctx, 1);
				{
				setState(80);
				((ArgumentContext)_localctx).unquotedArgument = match(UNQUOTED);
				setState(82);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(81);
					argument();
					}
					break;
				}
				}
				break;
			case SINGLEQUOTE:
				enterOuterAlt(_localctx, 2);
				{
				setState(84);
				((ArgumentContext)_localctx).singleQuoteArgument = single_quote();
				setState(86);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
				case 1:
					{
					setState(85);
					argument();
					}
					break;
				}
				}
				break;
			case DOUBLEQUOTE:
				enterOuterAlt(_localctx, 3);
				{
				setState(88);
				((ArgumentContext)_localctx).doubleQuoteArgument = double_quote();
				setState(90);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
				case 1:
					{
					setState(89);
					argument();
					}
					break;
				}
				}
				break;
			case BACKQUOTE:
				enterOuterAlt(_localctx, 4);
				{
				setState(92);
				((ArgumentContext)_localctx).backQuoteArgument = back_quote();
				setState(94);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
				case 1:
					{
					setState(93);
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
		public Token contents;
		public List<TerminalNode> SINGLEQUOTE() { return getTokens(CallGrammarParser.SINGLEQUOTE); }
		public TerminalNode SINGLEQUOTE(int i) {
			return getToken(CallGrammarParser.SINGLEQUOTE, i);
		}
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			match(SINGLEQUOTE);
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << WHITESPACE) | (1L << UNQUOTED) | (1L << PIPE) | (1L << SEMICOLON) | (1L << INPUTREDIRECTION) | (1L << OUTPUTREDIRECTION) | (1L << DOUBLEQUOTE) | (1L << BACKQUOTE))) != 0)) {
				{
				{
				setState(99);
				((Single_quoteContext)_localctx).contents = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << WHITESPACE) | (1L << UNQUOTED) | (1L << PIPE) | (1L << SEMICOLON) | (1L << INPUTREDIRECTION) | (1L << OUTPUTREDIRECTION) | (1L << DOUBLEQUOTE) | (1L << BACKQUOTE))) != 0)) ) {
					((Single_quoteContext)_localctx).contents = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(105);
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

	public static class Double_quoteContext extends ParserRuleContext {
		public Token contents;
		public List<TerminalNode> DOUBLEQUOTE() { return getTokens(CallGrammarParser.DOUBLEQUOTE); }
		public TerminalNode DOUBLEQUOTE(int i) {
			return getToken(CallGrammarParser.DOUBLEQUOTE, i);
		}
		public List<Back_quoteContext> back_quote() {
			return getRuleContexts(Back_quoteContext.class);
		}
		public Back_quoteContext back_quote(int i) {
			return getRuleContext(Back_quoteContext.class,i);
		}
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
		public List<TerminalNode> SINGLEQUOTE() { return getTokens(CallGrammarParser.SINGLEQUOTE); }
		public TerminalNode SINGLEQUOTE(int i) {
			return getToken(CallGrammarParser.SINGLEQUOTE, i);
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
		enterRule(_localctx, 12, RULE_double_quote);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			match(DOUBLEQUOTE);
			setState(112);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << WHITESPACE) | (1L << UNQUOTED) | (1L << PIPE) | (1L << SEMICOLON) | (1L << INPUTREDIRECTION) | (1L << SINGLEQUOTE) | (1L << BACKQUOTE))) != 0)) {
				{
				setState(110);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case WHITESPACE:
				case UNQUOTED:
				case PIPE:
				case SEMICOLON:
				case INPUTREDIRECTION:
				case SINGLEQUOTE:
					{
					setState(108);
					((Double_quoteContext)_localctx).contents = _input.LT(1);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << WHITESPACE) | (1L << UNQUOTED) | (1L << PIPE) | (1L << SEMICOLON) | (1L << INPUTREDIRECTION) | (1L << SINGLEQUOTE))) != 0)) ) {
						((Double_quoteContext)_localctx).contents = (Token)_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					break;
				case BACKQUOTE:
					{
					setState(109);
					back_quote();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(114);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(115);
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

	public static class Back_quoteContext extends ParserRuleContext {
		public Token contents;
		public List<TerminalNode> BACKQUOTE() { return getTokens(CallGrammarParser.BACKQUOTE); }
		public TerminalNode BACKQUOTE(int i) {
			return getToken(CallGrammarParser.BACKQUOTE, i);
		}
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
		enterRule(_localctx, 14, RULE_back_quote);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			match(BACKQUOTE);
			setState(121);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << WHITESPACE) | (1L << UNQUOTED) | (1L << PIPE) | (1L << SEMICOLON) | (1L << INPUTREDIRECTION) | (1L << OUTPUTREDIRECTION) | (1L << SINGLEQUOTE) | (1L << DOUBLEQUOTE))) != 0)) {
				{
				{
				setState(118);
				((Back_quoteContext)_localctx).contents = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << WHITESPACE) | (1L << UNQUOTED) | (1L << PIPE) | (1L << SEMICOLON) | (1L << INPUTREDIRECTION) | (1L << OUTPUTREDIRECTION) | (1L << SINGLEQUOTE) | (1L << DOUBLEQUOTE))) != 0)) ) {
					((Back_quoteContext)_localctx).contents = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(123);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(124);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\13\u0081\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2\3"+
		"\3\7\3\27\n\3\f\3\16\3\32\13\3\3\3\3\3\3\3\7\3\37\n\3\f\3\16\3\"\13\3"+
		"\3\3\3\3\3\3\7\3\'\n\3\f\3\16\3*\13\3\3\3\7\3-\n\3\f\3\16\3\60\13\3\3"+
		"\4\3\4\6\4\64\n\4\r\4\16\4\65\3\4\3\4\3\4\6\4;\n\4\r\4\16\4<\3\4\5\4@"+
		"\n\4\3\5\3\5\3\5\5\5E\n\5\3\5\3\5\6\5I\n\5\r\5\16\5J\3\5\7\5N\n\5\f\5"+
		"\16\5Q\13\5\3\6\3\6\5\6U\n\6\3\6\3\6\5\6Y\n\6\3\6\3\6\5\6]\n\6\3\6\3\6"+
		"\5\6a\n\6\5\6c\n\6\3\7\3\7\7\7g\n\7\f\7\16\7j\13\7\3\7\3\7\3\b\3\b\3\b"+
		"\7\bq\n\b\f\b\16\bt\13\b\3\b\3\b\3\t\3\t\7\tz\n\t\f\t\16\t}\13\t\3\t\3"+
		"\t\3\t\2\3\b\n\2\4\6\b\n\f\16\20\2\5\4\2\3\b\n\13\4\2\3\7\t\t\3\2\3\n"+
		"\2\u008d\2\22\3\2\2\2\4\30\3\2\2\2\6?\3\2\2\2\bD\3\2\2\2\nb\3\2\2\2\f"+
		"d\3\2\2\2\16m\3\2\2\2\20w\3\2\2\2\22\23\5\4\3\2\23\24\7\2\2\3\24\3\3\2"+
		"\2\2\25\27\7\3\2\2\26\25\3\2\2\2\27\32\3\2\2\2\30\26\3\2\2\2\30\31\3\2"+
		"\2\2\31 \3\2\2\2\32\30\3\2\2\2\33\34\5\6\4\2\34\35\7\3\2\2\35\37\3\2\2"+
		"\2\36\33\3\2\2\2\37\"\3\2\2\2 \36\3\2\2\2 !\3\2\2\2!#\3\2\2\2\" \3\2\2"+
		"\2#(\5\n\6\2$%\7\3\2\2%\'\5\b\5\2&$\3\2\2\2\'*\3\2\2\2(&\3\2\2\2()\3\2"+
		"\2\2).\3\2\2\2*(\3\2\2\2+-\7\3\2\2,+\3\2\2\2-\60\3\2\2\2.,\3\2\2\2./\3"+
		"\2\2\2/\5\3\2\2\2\60.\3\2\2\2\61\63\7\7\2\2\62\64\7\3\2\2\63\62\3\2\2"+
		"\2\64\65\3\2\2\2\65\63\3\2\2\2\65\66\3\2\2\2\66\67\3\2\2\2\67@\5\n\6\2"+
		"8:\7\b\2\29;\7\3\2\2:9\3\2\2\2;<\3\2\2\2<:\3\2\2\2<=\3\2\2\2=>\3\2\2\2"+
		">@\5\n\6\2?\61\3\2\2\2?8\3\2\2\2@\7\3\2\2\2AB\b\5\1\2BE\5\6\4\2CE\5\n"+
		"\6\2DA\3\2\2\2DC\3\2\2\2EO\3\2\2\2FH\f\3\2\2GI\7\3\2\2HG\3\2\2\2IJ\3\2"+
		"\2\2JH\3\2\2\2JK\3\2\2\2KL\3\2\2\2LN\5\4\3\2MF\3\2\2\2NQ\3\2\2\2OM\3\2"+
		"\2\2OP\3\2\2\2P\t\3\2\2\2QO\3\2\2\2RT\7\4\2\2SU\5\n\6\2TS\3\2\2\2TU\3"+
		"\2\2\2Uc\3\2\2\2VX\5\f\7\2WY\5\n\6\2XW\3\2\2\2XY\3\2\2\2Yc\3\2\2\2Z\\"+
		"\5\16\b\2[]\5\n\6\2\\[\3\2\2\2\\]\3\2\2\2]c\3\2\2\2^`\5\20\t\2_a\5\n\6"+
		"\2`_\3\2\2\2`a\3\2\2\2ac\3\2\2\2bR\3\2\2\2bV\3\2\2\2bZ\3\2\2\2b^\3\2\2"+
		"\2c\13\3\2\2\2dh\7\t\2\2eg\t\2\2\2fe\3\2\2\2gj\3\2\2\2hf\3\2\2\2hi\3\2"+
		"\2\2ik\3\2\2\2jh\3\2\2\2kl\7\t\2\2l\r\3\2\2\2mr\7\n\2\2nq\t\3\2\2oq\5"+
		"\20\t\2pn\3\2\2\2po\3\2\2\2qt\3\2\2\2rp\3\2\2\2rs\3\2\2\2su\3\2\2\2tr"+
		"\3\2\2\2uv\7\n\2\2v\17\3\2\2\2w{\7\13\2\2xz\t\4\2\2yx\3\2\2\2z}\3\2\2"+
		"\2{y\3\2\2\2{|\3\2\2\2|~\3\2\2\2}{\3\2\2\2~\177\7\13\2\2\177\21\3\2\2"+
		"\2\25\30 (.\65<?DJOTX\\`bhpr{";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}