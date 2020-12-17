// Generated from /workspaces/CW/src/main/antlr4/uk/ac/ucl/jsh/JshGrammar.g4 by ANTLR 4.8
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class JshGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, WHITESPACE=5, UNQUOTED=6, SINGLEQUOTE=7, 
		DOUBLEQUOTE=8, BACKQUOTE=9, NEWLINE=10;
	public static final int
		RULE_start = 0, RULE_command = 1, RULE_pipe = 2, RULE_seq = 3, RULE_quoted = 4, 
		RULE_singleQuoted = 5, RULE_backQuoted = 6, RULE_doubleQuoted = 7, RULE_call = 8, 
		RULE_atom = 9, RULE_argument = 10, RULE_redirection = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "command", "pipe", "seq", "quoted", "singleQuoted", "backQuoted", 
			"doubleQuoted", "call", "atom", "argument", "redirection"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'|'", "';'", "'<'", "'>'", null, null, "'''", "'\"'", "'`'", "'\n'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, "WHITESPACE", "UNQUOTED", "SINGLEQUOTE", 
			"DOUBLEQUOTE", "BACKQUOTE", "NEWLINE"
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
	public String getGrammarFileName() { return "JshGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public JshGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class StartContext extends ParserRuleContext {
		public CommandContext command() {
			return getRuleContext(CommandContext.class,0);
		}
		public TerminalNode EOF() { return getToken(JshGrammarParser.EOF, 0); }
		public SeqContext seq() {
			return getRuleContext(SeqContext.class,0);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			setState(30);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(24);
				command();
				setState(25);
				match(EOF);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(27);
				seq(0);
				setState(28);
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
	}

	public final CommandContext command() throws RecognitionException {
		CommandContext _localctx = new CommandContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_command);
		try {
			setState(34);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(32);
				pipe(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(33);
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
		public List<CallContext> call() {
			return getRuleContexts(CallContext.class);
		}
		public CallContext call(int i) {
			return getRuleContext(CallContext.class,i);
		}
		public PipeContext pipe() {
			return getRuleContext(PipeContext.class,0);
		}
		public PipeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pipe; }
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
			setState(37);
			call();
			setState(38);
			match(T__0);
			setState(39);
			call();
			}
			_ctx.stop = _input.LT(-1);
			setState(46);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new PipeContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_pipe);
					setState(41);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(42);
					match(T__0);
					setState(43);
					call();
					}
					} 
				}
				setState(48);
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
		public List<CommandContext> command() {
			return getRuleContexts(CommandContext.class);
		}
		public CommandContext command(int i) {
			return getRuleContext(CommandContext.class,i);
		}
		public SeqContext seq() {
			return getRuleContext(SeqContext.class,0);
		}
		public SeqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_seq; }
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
			setState(50);
			command();
			setState(51);
			match(T__1);
			setState(52);
			command();
			}
			_ctx.stop = _input.LT(-1);
			setState(59);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new SeqContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_seq);
					setState(54);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(55);
					match(T__1);
					setState(56);
					command();
					}
					} 
				}
				setState(61);
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

	public static class QuotedContext extends ParserRuleContext {
		public List<SingleQuotedContext> singleQuoted() {
			return getRuleContexts(SingleQuotedContext.class);
		}
		public SingleQuotedContext singleQuoted(int i) {
			return getRuleContext(SingleQuotedContext.class,i);
		}
		public List<DoubleQuotedContext> doubleQuoted() {
			return getRuleContexts(DoubleQuotedContext.class);
		}
		public DoubleQuotedContext doubleQuoted(int i) {
			return getRuleContext(DoubleQuotedContext.class,i);
		}
		public List<BackQuotedContext> backQuoted() {
			return getRuleContexts(BackQuotedContext.class);
		}
		public BackQuotedContext backQuoted(int i) {
			return getRuleContext(BackQuotedContext.class,i);
		}
		public QuotedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quoted; }
	}

	public final QuotedContext quoted() throws RecognitionException {
		QuotedContext _localctx = new QuotedContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_quoted);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(65); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					setState(65);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case SINGLEQUOTE:
						{
						setState(62);
						singleQuoted();
						}
						break;
					case DOUBLEQUOTE:
						{
						setState(63);
						doubleQuoted();
						}
						break;
					case BACKQUOTE:
						{
						setState(64);
						backQuoted();
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
				setState(67); 
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

	public static class SingleQuotedContext extends ParserRuleContext {
		public List<TerminalNode> SINGLEQUOTE() { return getTokens(JshGrammarParser.SINGLEQUOTE); }
		public TerminalNode SINGLEQUOTE(int i) {
			return getToken(JshGrammarParser.SINGLEQUOTE, i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(JshGrammarParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(JshGrammarParser.NEWLINE, i);
		}
		public SingleQuotedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_singleQuoted; }
	}

	public final SingleQuotedContext singleQuoted() throws RecognitionException {
		SingleQuotedContext _localctx = new SingleQuotedContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_singleQuoted);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			match(SINGLEQUOTE);
			setState(74);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(72);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						setState(70);
						_la = _input.LA(1);
						if ( _la <= 0 || (_la==NEWLINE) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					case 2:
						{
						setState(71);
						_la = _input.LA(1);
						if ( _la <= 0 || (_la==SINGLEQUOTE) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					}
					} 
				}
				setState(76);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
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

	public static class BackQuotedContext extends ParserRuleContext {
		public List<TerminalNode> BACKQUOTE() { return getTokens(JshGrammarParser.BACKQUOTE); }
		public TerminalNode BACKQUOTE(int i) {
			return getToken(JshGrammarParser.BACKQUOTE, i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(JshGrammarParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(JshGrammarParser.NEWLINE, i);
		}
		public BackQuotedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_backQuoted; }
	}

	public final BackQuotedContext backQuoted() throws RecognitionException {
		BackQuotedContext _localctx = new BackQuotedContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_backQuoted);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(BACKQUOTE);
			setState(84);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(82);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
					case 1:
						{
						setState(80);
						_la = _input.LA(1);
						if ( _la <= 0 || (_la==NEWLINE) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					case 2:
						{
						setState(81);
						_la = _input.LA(1);
						if ( _la <= 0 || (_la==BACKQUOTE) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					}
					} 
				}
				setState(86);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			setState(87);
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

	public static class DoubleQuotedContext extends ParserRuleContext {
		public List<TerminalNode> DOUBLEQUOTE() { return getTokens(JshGrammarParser.DOUBLEQUOTE); }
		public TerminalNode DOUBLEQUOTE(int i) {
			return getToken(JshGrammarParser.DOUBLEQUOTE, i);
		}
		public List<TerminalNode> BACKQUOTE() { return getTokens(JshGrammarParser.BACKQUOTE); }
		public TerminalNode BACKQUOTE(int i) {
			return getToken(JshGrammarParser.BACKQUOTE, i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(JshGrammarParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(JshGrammarParser.NEWLINE, i);
		}
		public DoubleQuotedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doubleQuoted; }
	}

	public final DoubleQuotedContext doubleQuoted() throws RecognitionException {
		DoubleQuotedContext _localctx = new DoubleQuotedContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_doubleQuoted);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			match(DOUBLEQUOTE);
			setState(96);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(94);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
					case 1:
						{
						setState(90);
						match(BACKQUOTE);
						}
						break;
					case 2:
						{
						setState(91);
						_la = _input.LA(1);
						if ( _la <= 0 || (_la==NEWLINE) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					case 3:
						{
						setState(92);
						_la = _input.LA(1);
						if ( _la <= 0 || (_la==BACKQUOTE) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					case 4:
						{
						setState(93);
						_la = _input.LA(1);
						if ( _la <= 0 || (_la==DOUBLEQUOTE) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					}
					} 
				}
				setState(98);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			setState(99);
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

	public static class CallContext extends ParserRuleContext {
		public ArgumentContext argument() {
			return getRuleContext(ArgumentContext.class,0);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(JshGrammarParser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(JshGrammarParser.WHITESPACE, i);
		}
		public List<RedirectionContext> redirection() {
			return getRuleContexts(RedirectionContext.class);
		}
		public RedirectionContext redirection(int i) {
			return getRuleContext(RedirectionContext.class,i);
		}
		public List<AtomContext> atom() {
			return getRuleContexts(AtomContext.class);
		}
		public AtomContext atom(int i) {
			return getRuleContext(AtomContext.class,i);
		}
		public CallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_call; }
	}

	public final CallContext call() throws RecognitionException {
		CallContext _localctx = new CallContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_call);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(101);
				match(WHITESPACE);
				}
				}
				setState(106);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(112);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2 || _la==T__3) {
				{
				{
				setState(107);
				redirection();
				setState(108);
				match(WHITESPACE);
				}
				}
				setState(114);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(115);
			argument();
			setState(120);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(116);
					match(WHITESPACE);
					setState(117);
					atom();
					}
					} 
				}
				setState(122);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			setState(126);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(123);
					match(WHITESPACE);
					}
					} 
				}
				setState(128);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
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

	public static class AtomContext extends ParserRuleContext {
		public RedirectionContext redirection() {
			return getRuleContext(RedirectionContext.class,0);
		}
		public ArgumentContext argument() {
			return getRuleContext(ArgumentContext.class,0);
		}
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_atom);
		try {
			setState(131);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
			case T__3:
				enterOuterAlt(_localctx, 1);
				{
				setState(129);
				redirection();
				}
				break;
			case UNQUOTED:
			case SINGLEQUOTE:
			case DOUBLEQUOTE:
			case BACKQUOTE:
				enterOuterAlt(_localctx, 2);
				{
				setState(130);
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

	public static class ArgumentContext extends ParserRuleContext {
		public List<QuotedContext> quoted() {
			return getRuleContexts(QuotedContext.class);
		}
		public QuotedContext quoted(int i) {
			return getRuleContext(QuotedContext.class,i);
		}
		public List<TerminalNode> UNQUOTED() { return getTokens(JshGrammarParser.UNQUOTED); }
		public TerminalNode UNQUOTED(int i) {
			return getToken(JshGrammarParser.UNQUOTED, i);
		}
		public ArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument; }
	}

	public final ArgumentContext argument() throws RecognitionException {
		ArgumentContext _localctx = new ArgumentContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_argument);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(135); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					setState(135);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case SINGLEQUOTE:
					case DOUBLEQUOTE:
					case BACKQUOTE:
						{
						setState(133);
						quoted();
						}
						break;
					case UNQUOTED:
						{
						setState(134);
						match(UNQUOTED);
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
				setState(137); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
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

	public static class RedirectionContext extends ParserRuleContext {
		public TerminalNode WHITESPACE() { return getToken(JshGrammarParser.WHITESPACE, 0); }
		public ArgumentContext argument() {
			return getRuleContext(ArgumentContext.class,0);
		}
		public RedirectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_redirection; }
	}

	public final RedirectionContext redirection() throws RecognitionException {
		RedirectionContext _localctx = new RedirectionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_redirection);
		try {
			setState(145);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(139);
				match(T__2);
				setState(140);
				match(WHITESPACE);
				setState(141);
				argument();
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(142);
				match(T__3);
				setState(143);
				match(WHITESPACE);
				setState(144);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\f\u0096\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\3\2\3\2\3\2\3\2\3\2\3\2\5\2!\n\2\3\3\3\3\5\3%\n\3"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4/\n\4\f\4\16\4\62\13\4\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\7\5<\n\5\f\5\16\5?\13\5\3\6\3\6\3\6\6\6D\n\6\r\6"+
		"\16\6E\3\7\3\7\3\7\7\7K\n\7\f\7\16\7N\13\7\3\7\3\7\3\b\3\b\3\b\7\bU\n"+
		"\b\f\b\16\bX\13\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\7\ta\n\t\f\t\16\td\13\t"+
		"\3\t\3\t\3\n\7\ni\n\n\f\n\16\nl\13\n\3\n\3\n\3\n\7\nq\n\n\f\n\16\nt\13"+
		"\n\3\n\3\n\3\n\7\ny\n\n\f\n\16\n|\13\n\3\n\7\n\177\n\n\f\n\16\n\u0082"+
		"\13\n\3\13\3\13\5\13\u0086\n\13\3\f\3\f\6\f\u008a\n\f\r\f\16\f\u008b\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\5\r\u0094\n\r\3\r\2\4\6\b\16\2\4\6\b\n\f\16\20"+
		"\22\24\26\30\2\6\3\2\f\f\3\2\t\t\3\2\13\13\3\2\n\n\2\u00a0\2 \3\2\2\2"+
		"\4$\3\2\2\2\6&\3\2\2\2\b\63\3\2\2\2\nC\3\2\2\2\fG\3\2\2\2\16Q\3\2\2\2"+
		"\20[\3\2\2\2\22j\3\2\2\2\24\u0085\3\2\2\2\26\u0089\3\2\2\2\30\u0093\3"+
		"\2\2\2\32\33\5\4\3\2\33\34\7\2\2\3\34!\3\2\2\2\35\36\5\b\5\2\36\37\7\2"+
		"\2\3\37!\3\2\2\2 \32\3\2\2\2 \35\3\2\2\2!\3\3\2\2\2\"%\5\6\4\2#%\5\22"+
		"\n\2$\"\3\2\2\2$#\3\2\2\2%\5\3\2\2\2&\'\b\4\1\2\'(\5\22\n\2()\7\3\2\2"+
		")*\5\22\n\2*\60\3\2\2\2+,\f\3\2\2,-\7\3\2\2-/\5\22\n\2.+\3\2\2\2/\62\3"+
		"\2\2\2\60.\3\2\2\2\60\61\3\2\2\2\61\7\3\2\2\2\62\60\3\2\2\2\63\64\b\5"+
		"\1\2\64\65\5\4\3\2\65\66\7\4\2\2\66\67\5\4\3\2\67=\3\2\2\289\f\3\2\29"+
		":\7\4\2\2:<\5\4\3\2;8\3\2\2\2<?\3\2\2\2=;\3\2\2\2=>\3\2\2\2>\t\3\2\2\2"+
		"?=\3\2\2\2@D\5\f\7\2AD\5\20\t\2BD\5\16\b\2C@\3\2\2\2CA\3\2\2\2CB\3\2\2"+
		"\2DE\3\2\2\2EC\3\2\2\2EF\3\2\2\2F\13\3\2\2\2GL\7\t\2\2HK\n\2\2\2IK\n\3"+
		"\2\2JH\3\2\2\2JI\3\2\2\2KN\3\2\2\2LJ\3\2\2\2LM\3\2\2\2MO\3\2\2\2NL\3\2"+
		"\2\2OP\7\t\2\2P\r\3\2\2\2QV\7\13\2\2RU\n\2\2\2SU\n\4\2\2TR\3\2\2\2TS\3"+
		"\2\2\2UX\3\2\2\2VT\3\2\2\2VW\3\2\2\2WY\3\2\2\2XV\3\2\2\2YZ\7\13\2\2Z\17"+
		"\3\2\2\2[b\7\n\2\2\\a\7\13\2\2]a\n\2\2\2^a\n\4\2\2_a\n\5\2\2`\\\3\2\2"+
		"\2`]\3\2\2\2`^\3\2\2\2`_\3\2\2\2ad\3\2\2\2b`\3\2\2\2bc\3\2\2\2ce\3\2\2"+
		"\2db\3\2\2\2ef\7\n\2\2f\21\3\2\2\2gi\7\7\2\2hg\3\2\2\2il\3\2\2\2jh\3\2"+
		"\2\2jk\3\2\2\2kr\3\2\2\2lj\3\2\2\2mn\5\30\r\2no\7\7\2\2oq\3\2\2\2pm\3"+
		"\2\2\2qt\3\2\2\2rp\3\2\2\2rs\3\2\2\2su\3\2\2\2tr\3\2\2\2uz\5\26\f\2vw"+
		"\7\7\2\2wy\5\24\13\2xv\3\2\2\2y|\3\2\2\2zx\3\2\2\2z{\3\2\2\2{\u0080\3"+
		"\2\2\2|z\3\2\2\2}\177\7\7\2\2~}\3\2\2\2\177\u0082\3\2\2\2\u0080~\3\2\2"+
		"\2\u0080\u0081\3\2\2\2\u0081\23\3\2\2\2\u0082\u0080\3\2\2\2\u0083\u0086"+
		"\5\30\r\2\u0084\u0086\5\26\f\2\u0085\u0083\3\2\2\2\u0085\u0084\3\2\2\2"+
		"\u0086\25\3\2\2\2\u0087\u008a\5\n\6\2\u0088\u008a\7\b\2\2\u0089\u0087"+
		"\3\2\2\2\u0089\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u0089\3\2\2\2\u008b"+
		"\u008c\3\2\2\2\u008c\27\3\2\2\2\u008d\u008e\7\5\2\2\u008e\u008f\7\7\2"+
		"\2\u008f\u0094\5\26\f\2\u0090\u0091\7\6\2\2\u0091\u0092\7\7\2\2\u0092"+
		"\u0094\5\26\f\2\u0093\u008d\3\2\2\2\u0093\u0090\3\2\2\2\u0094\31\3\2\2"+
		"\2\26 $\60=CEJLTV`bjrz\u0080\u0085\u0089\u008b\u0093";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}