// Generated from C:/Users/aashv/Documents/GitHub/jsh-team-41/src/main/antlr4/uk/ac/ucl/jsh/.antlr\CommandLineGrammar.g4 by ANTLR 4.9
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CommandLineGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WHITESPACE=1, UNQUOTED=2, PIPE=3, SEMICOLON=4, INPUTREDIRECTION=5, OUTPUTREDIRECTION=6, 
		SINGLEQUOTE=7, DOUBLEQUOTE=8, BACKQUOTE=9;
	public static final int
		RULE_start = 0, RULE_command = 1, RULE_pipe = 2, RULE_seq = 3, RULE_call = 4, 
		RULE_quoted = 5, RULE_single_quote = 6, RULE_double_quote = 7, RULE_back_quote = 8;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "command", "pipe", "seq", "call", "quoted", "single_quote", 
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
	public String getGrammarFileName() { return "CommandLineGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CommandLineGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class StartContext extends ParserRuleContext {
		public CommandContext command() {
			return getRuleContext(CommandContext.class,0);
		}
		public TerminalNode EOF() { return getToken(CommandLineGrammarParser.EOF, 0); }
		public SeqContext seq() {
			return getRuleContext(SeqContext.class,0);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLineGrammarListener ) ((CommandLineGrammarListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLineGrammarListener ) ((CommandLineGrammarListener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandLineGrammarVisitor ) return ((CommandLineGrammarVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			setState(24);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(18);
				command();
				setState(19);
				match(EOF);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(21);
				seq(0);
				setState(22);
				match(EOF);
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

	public static class CommandContext extends ParserRuleContext {
		public PipeContext pipe() {
			return getRuleContext(PipeContext.class,0);
		}
		public CallContext call() {
			return getRuleContext(CallContext.class,0);
		}
		public CommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_command; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLineGrammarListener ) ((CommandLineGrammarListener)listener).enterCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLineGrammarListener ) ((CommandLineGrammarListener)listener).exitCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandLineGrammarVisitor ) return ((CommandLineGrammarVisitor<? extends T>)visitor).visitCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommandContext command() throws RecognitionException {
		CommandContext _localctx = new CommandContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_command);
		try {
			setState(28);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(26);
				pipe(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(27);
				call();
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

	public static class PipeContext extends ParserRuleContext {
		public PipeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pipe; }
	 
		public PipeContext() { }
		public void copyFrom(PipeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class PipeBaseCaseContext extends PipeContext {
		public CallContext call1;
		public CallContext call2;
		public TerminalNode PIPE() { return getToken(CommandLineGrammarParser.PIPE, 0); }
		public List<CallContext> call() {
			return getRuleContexts(CallContext.class);
		}
		public CallContext call(int i) {
			return getRuleContext(CallContext.class,i);
		}
		public PipeBaseCaseContext(PipeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLineGrammarListener ) ((CommandLineGrammarListener)listener).enterPipeBaseCase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLineGrammarListener ) ((CommandLineGrammarListener)listener).exitPipeBaseCase(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandLineGrammarVisitor ) return ((CommandLineGrammarVisitor<? extends T>)visitor).visitPipeBaseCase(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PipeRecursiveCaseContext extends PipeContext {
		public PipeContext pipe() {
			return getRuleContext(PipeContext.class,0);
		}
		public TerminalNode PIPE() { return getToken(CommandLineGrammarParser.PIPE, 0); }
		public CallContext call() {
			return getRuleContext(CallContext.class,0);
		}
		public PipeRecursiveCaseContext(PipeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLineGrammarListener ) ((CommandLineGrammarListener)listener).enterPipeRecursiveCase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLineGrammarListener ) ((CommandLineGrammarListener)listener).exitPipeRecursiveCase(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandLineGrammarVisitor ) return ((CommandLineGrammarVisitor<? extends T>)visitor).visitPipeRecursiveCase(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PipeContext pipe() throws RecognitionException {
		return pipe(0);
	}

	private PipeContext pipe(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		PipeContext _localctx = new PipeContext(_ctx, _parentState);
		PipeContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_pipe, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new PipeBaseCaseContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(31);
			((PipeBaseCaseContext)_localctx).call1 = call();
			setState(32);
			match(PIPE);
			setState(33);
			((PipeBaseCaseContext)_localctx).call2 = call();
			}
			_ctx.stop = _input.LT(-1);
			setState(40);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new PipeRecursiveCaseContext(new PipeContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_pipe);
					setState(35);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(36);
					match(PIPE);
					setState(37);
					call();
					}
					} 
				}
				setState(42);
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

	public static class SeqContext extends ParserRuleContext {
		public SeqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_seq; }
	 
		public SeqContext() { }
		public void copyFrom(SeqContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SeqBaseCaseContext extends SeqContext {
		public CommandContext command1;
		public CommandContext command2;
		public TerminalNode SEMICOLON() { return getToken(CommandLineGrammarParser.SEMICOLON, 0); }
		public List<CommandContext> command() {
			return getRuleContexts(CommandContext.class);
		}
		public CommandContext command(int i) {
			return getRuleContext(CommandContext.class,i);
		}
		public SeqBaseCaseContext(SeqContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLineGrammarListener ) ((CommandLineGrammarListener)listener).enterSeqBaseCase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLineGrammarListener ) ((CommandLineGrammarListener)listener).exitSeqBaseCase(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandLineGrammarVisitor ) return ((CommandLineGrammarVisitor<? extends T>)visitor).visitSeqBaseCase(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SeqRecursiveCaseContext extends SeqContext {
		public SeqContext seq() {
			return getRuleContext(SeqContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CommandLineGrammarParser.SEMICOLON, 0); }
		public CommandContext command() {
			return getRuleContext(CommandContext.class,0);
		}
		public SeqRecursiveCaseContext(SeqContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLineGrammarListener ) ((CommandLineGrammarListener)listener).enterSeqRecursiveCase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLineGrammarListener ) ((CommandLineGrammarListener)listener).exitSeqRecursiveCase(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandLineGrammarVisitor ) return ((CommandLineGrammarVisitor<? extends T>)visitor).visitSeqRecursiveCase(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SeqContext seq() throws RecognitionException {
		return seq(0);
	}

	private SeqContext seq(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		SeqContext _localctx = new SeqContext(_ctx, _parentState);
		SeqContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_seq, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new SeqBaseCaseContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(44);
			((SeqBaseCaseContext)_localctx).command1 = command();
			setState(45);
			match(SEMICOLON);
			setState(46);
			((SeqBaseCaseContext)_localctx).command2 = command();
			}
			_ctx.stop = _input.LT(-1);
			setState(53);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new SeqRecursiveCaseContext(new SeqContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_seq);
					setState(48);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(49);
					match(SEMICOLON);
					setState(50);
					command();
					}
					} 
				}
				setState(55);
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

	public static class CallContext extends ParserRuleContext {
		public List<TerminalNode> WHITESPACE() { return getTokens(CommandLineGrammarParser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(CommandLineGrammarParser.WHITESPACE, i);
		}
		public List<TerminalNode> UNQUOTED() { return getTokens(CommandLineGrammarParser.UNQUOTED); }
		public TerminalNode UNQUOTED(int i) {
			return getToken(CommandLineGrammarParser.UNQUOTED, i);
		}
		public List<TerminalNode> INPUTREDIRECTION() { return getTokens(CommandLineGrammarParser.INPUTREDIRECTION); }
		public TerminalNode INPUTREDIRECTION(int i) {
			return getToken(CommandLineGrammarParser.INPUTREDIRECTION, i);
		}
		public List<TerminalNode> OUTPUTREDIRECTION() { return getTokens(CommandLineGrammarParser.OUTPUTREDIRECTION); }
		public TerminalNode OUTPUTREDIRECTION(int i) {
			return getToken(CommandLineGrammarParser.OUTPUTREDIRECTION, i);
		}
		public List<QuotedContext> quoted() {
			return getRuleContexts(QuotedContext.class);
		}
		public QuotedContext quoted(int i) {
			return getRuleContext(QuotedContext.class,i);
		}
		public CallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLineGrammarListener ) ((CommandLineGrammarListener)listener).enterCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLineGrammarListener ) ((CommandLineGrammarListener)listener).exitCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandLineGrammarVisitor ) return ((CommandLineGrammarVisitor<? extends T>)visitor).visitCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallContext call() throws RecognitionException {
		CallContext _localctx = new CallContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_call);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(61); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					setState(61);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case WHITESPACE:
						{
						setState(56);
						match(WHITESPACE);
						}
						break;
					case UNQUOTED:
						{
						setState(57);
						match(UNQUOTED);
						}
						break;
					case INPUTREDIRECTION:
						{
						setState(58);
						match(INPUTREDIRECTION);
						}
						break;
					case OUTPUTREDIRECTION:
						{
						setState(59);
						match(OUTPUTREDIRECTION);
						}
						break;
					case SINGLEQUOTE:
					case DOUBLEQUOTE:
					case BACKQUOTE:
						{
						setState(60);
						quoted();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(63); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class QuotedContext extends ParserRuleContext {
		public Single_quoteContext single_quote() {
			return getRuleContext(Single_quoteContext.class,0);
		}
		public Double_quoteContext double_quote() {
			return getRuleContext(Double_quoteContext.class,0);
		}
		public Back_quoteContext back_quote() {
			return getRuleContext(Back_quoteContext.class,0);
		}
		public QuotedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quoted; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLineGrammarListener ) ((CommandLineGrammarListener)listener).enterQuoted(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLineGrammarListener ) ((CommandLineGrammarListener)listener).exitQuoted(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandLineGrammarVisitor ) return ((CommandLineGrammarVisitor<? extends T>)visitor).visitQuoted(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuotedContext quoted() throws RecognitionException {
		QuotedContext _localctx = new QuotedContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_quoted);
		try {
			setState(68);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SINGLEQUOTE:
				enterOuterAlt(_localctx, 1);
				{
				setState(65);
				single_quote();
				}
				break;
			case DOUBLEQUOTE:
				enterOuterAlt(_localctx, 2);
				{
				setState(66);
				double_quote();
				}
				break;
			case BACKQUOTE:
				enterOuterAlt(_localctx, 3);
				{
				setState(67);
				back_quote();
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
		public List<TerminalNode> SINGLEQUOTE() { return getTokens(CommandLineGrammarParser.SINGLEQUOTE); }
		public TerminalNode SINGLEQUOTE(int i) {
			return getToken(CommandLineGrammarParser.SINGLEQUOTE, i);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(CommandLineGrammarParser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(CommandLineGrammarParser.WHITESPACE, i);
		}
		public List<TerminalNode> UNQUOTED() { return getTokens(CommandLineGrammarParser.UNQUOTED); }
		public TerminalNode UNQUOTED(int i) {
			return getToken(CommandLineGrammarParser.UNQUOTED, i);
		}
		public List<TerminalNode> PIPE() { return getTokens(CommandLineGrammarParser.PIPE); }
		public TerminalNode PIPE(int i) {
			return getToken(CommandLineGrammarParser.PIPE, i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(CommandLineGrammarParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(CommandLineGrammarParser.SEMICOLON, i);
		}
		public List<TerminalNode> INPUTREDIRECTION() { return getTokens(CommandLineGrammarParser.INPUTREDIRECTION); }
		public TerminalNode INPUTREDIRECTION(int i) {
			return getToken(CommandLineGrammarParser.INPUTREDIRECTION, i);
		}
		public List<TerminalNode> OUTPUTREDIRECTION() { return getTokens(CommandLineGrammarParser.OUTPUTREDIRECTION); }
		public TerminalNode OUTPUTREDIRECTION(int i) {
			return getToken(CommandLineGrammarParser.OUTPUTREDIRECTION, i);
		}
		public List<TerminalNode> DOUBLEQUOTE() { return getTokens(CommandLineGrammarParser.DOUBLEQUOTE); }
		public TerminalNode DOUBLEQUOTE(int i) {
			return getToken(CommandLineGrammarParser.DOUBLEQUOTE, i);
		}
		public List<TerminalNode> BACKQUOTE() { return getTokens(CommandLineGrammarParser.BACKQUOTE); }
		public TerminalNode BACKQUOTE(int i) {
			return getToken(CommandLineGrammarParser.BACKQUOTE, i);
		}
		public Single_quoteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_single_quote; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLineGrammarListener ) ((CommandLineGrammarListener)listener).enterSingle_quote(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLineGrammarListener ) ((CommandLineGrammarListener)listener).exitSingle_quote(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandLineGrammarVisitor ) return ((CommandLineGrammarVisitor<? extends T>)visitor).visitSingle_quote(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Single_quoteContext single_quote() throws RecognitionException {
		Single_quoteContext _localctx = new Single_quoteContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_single_quote);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(SINGLEQUOTE);
			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << WHITESPACE) | (1L << UNQUOTED) | (1L << PIPE) | (1L << SEMICOLON) | (1L << INPUTREDIRECTION) | (1L << OUTPUTREDIRECTION) | (1L << DOUBLEQUOTE) | (1L << BACKQUOTE))) != 0)) {
				{
				{
				setState(71);
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
				setState(76);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(77);
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
		public List<TerminalNode> DOUBLEQUOTE() { return getTokens(CommandLineGrammarParser.DOUBLEQUOTE); }
		public TerminalNode DOUBLEQUOTE(int i) {
			return getToken(CommandLineGrammarParser.DOUBLEQUOTE, i);
		}
		public List<Back_quoteContext> back_quote() {
			return getRuleContexts(Back_quoteContext.class);
		}
		public Back_quoteContext back_quote(int i) {
			return getRuleContext(Back_quoteContext.class,i);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(CommandLineGrammarParser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(CommandLineGrammarParser.WHITESPACE, i);
		}
		public List<TerminalNode> UNQUOTED() { return getTokens(CommandLineGrammarParser.UNQUOTED); }
		public TerminalNode UNQUOTED(int i) {
			return getToken(CommandLineGrammarParser.UNQUOTED, i);
		}
		public List<TerminalNode> PIPE() { return getTokens(CommandLineGrammarParser.PIPE); }
		public TerminalNode PIPE(int i) {
			return getToken(CommandLineGrammarParser.PIPE, i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(CommandLineGrammarParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(CommandLineGrammarParser.SEMICOLON, i);
		}
		public List<TerminalNode> INPUTREDIRECTION() { return getTokens(CommandLineGrammarParser.INPUTREDIRECTION); }
		public TerminalNode INPUTREDIRECTION(int i) {
			return getToken(CommandLineGrammarParser.INPUTREDIRECTION, i);
		}
		public List<TerminalNode> SINGLEQUOTE() { return getTokens(CommandLineGrammarParser.SINGLEQUOTE); }
		public TerminalNode SINGLEQUOTE(int i) {
			return getToken(CommandLineGrammarParser.SINGLEQUOTE, i);
		}
		public Double_quoteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_double_quote; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLineGrammarListener ) ((CommandLineGrammarListener)listener).enterDouble_quote(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLineGrammarListener ) ((CommandLineGrammarListener)listener).exitDouble_quote(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandLineGrammarVisitor ) return ((CommandLineGrammarVisitor<? extends T>)visitor).visitDouble_quote(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Double_quoteContext double_quote() throws RecognitionException {
		Double_quoteContext _localctx = new Double_quoteContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_double_quote);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(DOUBLEQUOTE);
			setState(84);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << WHITESPACE) | (1L << UNQUOTED) | (1L << PIPE) | (1L << SEMICOLON) | (1L << INPUTREDIRECTION) | (1L << SINGLEQUOTE) | (1L << BACKQUOTE))) != 0)) {
				{
				setState(82);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case WHITESPACE:
				case UNQUOTED:
				case PIPE:
				case SEMICOLON:
				case INPUTREDIRECTION:
				case SINGLEQUOTE:
					{
					setState(80);
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
					setState(81);
					back_quote();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(86);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(87);
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
		public List<TerminalNode> BACKQUOTE() { return getTokens(CommandLineGrammarParser.BACKQUOTE); }
		public TerminalNode BACKQUOTE(int i) {
			return getToken(CommandLineGrammarParser.BACKQUOTE, i);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(CommandLineGrammarParser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(CommandLineGrammarParser.WHITESPACE, i);
		}
		public List<TerminalNode> UNQUOTED() { return getTokens(CommandLineGrammarParser.UNQUOTED); }
		public TerminalNode UNQUOTED(int i) {
			return getToken(CommandLineGrammarParser.UNQUOTED, i);
		}
		public List<TerminalNode> PIPE() { return getTokens(CommandLineGrammarParser.PIPE); }
		public TerminalNode PIPE(int i) {
			return getToken(CommandLineGrammarParser.PIPE, i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(CommandLineGrammarParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(CommandLineGrammarParser.SEMICOLON, i);
		}
		public List<TerminalNode> INPUTREDIRECTION() { return getTokens(CommandLineGrammarParser.INPUTREDIRECTION); }
		public TerminalNode INPUTREDIRECTION(int i) {
			return getToken(CommandLineGrammarParser.INPUTREDIRECTION, i);
		}
		public List<TerminalNode> OUTPUTREDIRECTION() { return getTokens(CommandLineGrammarParser.OUTPUTREDIRECTION); }
		public TerminalNode OUTPUTREDIRECTION(int i) {
			return getToken(CommandLineGrammarParser.OUTPUTREDIRECTION, i);
		}
		public List<TerminalNode> SINGLEQUOTE() { return getTokens(CommandLineGrammarParser.SINGLEQUOTE); }
		public TerminalNode SINGLEQUOTE(int i) {
			return getToken(CommandLineGrammarParser.SINGLEQUOTE, i);
		}
		public List<TerminalNode> DOUBLEQUOTE() { return getTokens(CommandLineGrammarParser.DOUBLEQUOTE); }
		public TerminalNode DOUBLEQUOTE(int i) {
			return getToken(CommandLineGrammarParser.DOUBLEQUOTE, i);
		}
		public Back_quoteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_back_quote; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLineGrammarListener ) ((CommandLineGrammarListener)listener).enterBack_quote(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandLineGrammarListener ) ((CommandLineGrammarListener)listener).exitBack_quote(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandLineGrammarVisitor ) return ((CommandLineGrammarVisitor<? extends T>)visitor).visitBack_quote(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Back_quoteContext back_quote() throws RecognitionException {
		Back_quoteContext _localctx = new Back_quoteContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_back_quote);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			match(BACKQUOTE);
			setState(93);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << WHITESPACE) | (1L << UNQUOTED) | (1L << PIPE) | (1L << SEMICOLON) | (1L << INPUTREDIRECTION) | (1L << OUTPUTREDIRECTION) | (1L << SINGLEQUOTE) | (1L << DOUBLEQUOTE))) != 0)) {
				{
				{
				setState(90);
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
				setState(95);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(96);
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
		case 2:
			return pipe_sempred((PipeContext)_localctx, predIndex);
		case 3:
			return seq_sempred((SeqContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean pipe_sempred(PipeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean seq_sempred(SeqContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\13e\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\5\2\33\n\2\3\3\3\3\5\3\37\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\7\4)\n\4\f\4\16\4,\13\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\66\n"+
		"\5\f\5\16\59\13\5\3\6\3\6\3\6\3\6\3\6\6\6@\n\6\r\6\16\6A\3\7\3\7\3\7\5"+
		"\7G\n\7\3\b\3\b\7\bK\n\b\f\b\16\bN\13\b\3\b\3\b\3\t\3\t\3\t\7\tU\n\t\f"+
		"\t\16\tX\13\t\3\t\3\t\3\n\3\n\7\n^\n\n\f\n\16\na\13\n\3\n\3\n\3\n\2\4"+
		"\6\b\13\2\4\6\b\n\f\16\20\22\2\5\4\2\3\b\n\13\4\2\3\7\t\t\3\2\3\n\2j\2"+
		"\32\3\2\2\2\4\36\3\2\2\2\6 \3\2\2\2\b-\3\2\2\2\n?\3\2\2\2\fF\3\2\2\2\16"+
		"H\3\2\2\2\20Q\3\2\2\2\22[\3\2\2\2\24\25\5\4\3\2\25\26\7\2\2\3\26\33\3"+
		"\2\2\2\27\30\5\b\5\2\30\31\7\2\2\3\31\33\3\2\2\2\32\24\3\2\2\2\32\27\3"+
		"\2\2\2\33\3\3\2\2\2\34\37\5\6\4\2\35\37\5\n\6\2\36\34\3\2\2\2\36\35\3"+
		"\2\2\2\37\5\3\2\2\2 !\b\4\1\2!\"\5\n\6\2\"#\7\5\2\2#$\5\n\6\2$*\3\2\2"+
		"\2%&\f\3\2\2&\'\7\5\2\2\')\5\n\6\2(%\3\2\2\2),\3\2\2\2*(\3\2\2\2*+\3\2"+
		"\2\2+\7\3\2\2\2,*\3\2\2\2-.\b\5\1\2./\5\4\3\2/\60\7\6\2\2\60\61\5\4\3"+
		"\2\61\67\3\2\2\2\62\63\f\3\2\2\63\64\7\6\2\2\64\66\5\4\3\2\65\62\3\2\2"+
		"\2\669\3\2\2\2\67\65\3\2\2\2\678\3\2\2\28\t\3\2\2\29\67\3\2\2\2:@\7\3"+
		"\2\2;@\7\4\2\2<@\7\7\2\2=@\7\b\2\2>@\5\f\7\2?:\3\2\2\2?;\3\2\2\2?<\3\2"+
		"\2\2?=\3\2\2\2?>\3\2\2\2@A\3\2\2\2A?\3\2\2\2AB\3\2\2\2B\13\3\2\2\2CG\5"+
		"\16\b\2DG\5\20\t\2EG\5\22\n\2FC\3\2\2\2FD\3\2\2\2FE\3\2\2\2G\r\3\2\2\2"+
		"HL\7\t\2\2IK\t\2\2\2JI\3\2\2\2KN\3\2\2\2LJ\3\2\2\2LM\3\2\2\2MO\3\2\2\2"+
		"NL\3\2\2\2OP\7\t\2\2P\17\3\2\2\2QV\7\n\2\2RU\t\3\2\2SU\5\22\n\2TR\3\2"+
		"\2\2TS\3\2\2\2UX\3\2\2\2VT\3\2\2\2VW\3\2\2\2WY\3\2\2\2XV\3\2\2\2YZ\7\n"+
		"\2\2Z\21\3\2\2\2[_\7\13\2\2\\^\t\4\2\2]\\\3\2\2\2^a\3\2\2\2_]\3\2\2\2"+
		"_`\3\2\2\2`b\3\2\2\2a_\3\2\2\2bc\7\13\2\2c\23\3\2\2\2\r\32\36*\67?AFL"+
		"TV_";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}