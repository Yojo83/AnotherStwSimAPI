package yojo.stwPlugIn.Client.parser;

import yojo.stwPlugIn.Client.Messages.RawMessage;
import yojo.stwPlugIn.Client.util.DEBUGGER;
import yojo.stwPlugIn.Client.util.ResponseListener;

/**
 * a parser to parse xml messages from the game make function calls to the listener
 */
public class XmlParser {
	
	/**
	 * Sub Parser for one message
	 */
	private MessageTree state;

	/**
	 * parses this message
	 * @param line the next xml line
	 * @param responseListener the listener, thats functions are called if the xml message ends
	 */
	public void handleLine(String line, ResponseListener responseListener) {
		Token[] tokens = Token.toTokenArray(line);
		try {
			handlerTokenstream(tokens, responseListener);
		} catch (ParserException e) {
			DEBUGGER.log("Error at Token " + e.token.toString() + " in Message: " + line + ": " + e.msg);
			responseListener.unhandeledMessage(new RawMessage(line));
			state = null;
		}
	}
	
	/**
	 * the true parser, reaching but without exception handling
	 * @param tokens the line as token stream
	 * @param responseListener the listener, thats functions are called if the xml message ends
	 * @throws ParserException
	 */
	private void handlerTokenstream(Token[] tokens, ResponseListener responseListener) throws ParserException {
		for(Token t : tokens) {
			if(state != null) {
				if(state.isFinished()) {
					state = null;
				} else {
					state.parse(t, responseListener);
					continue;
				}
			}
			
			if(t == Token.START) {
				state = new MessageTree();
				continue;
			}
			
			throw new ParserException("token is not the start of a response nor in a response", t);
		}
	}

	/**
	 * thrown on any error during the parsing process.
	 * will be catch on top of the answer hierarchy to give an error to the log
	 * and call unhandeledMesage() from the listener with a raw message 
	 */
	public static class ParserException extends Exception{

		private static final long serialVersionUID = 1L;
		
		public final String msg;
		public final Token token;
		
		public ParserException(String msg, Token token) {
			this.msg = msg;
			this.token = token;
		}
	}
}
