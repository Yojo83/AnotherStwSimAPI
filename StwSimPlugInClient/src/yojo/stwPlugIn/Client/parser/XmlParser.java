package yojo.stwPlugIn.Client.parser;

import yojo.stwPlugIn.Client.Messages.RawMessage;
import yojo.stwPlugIn.Client.util.DEBUGGER;
import yojo.stwPlugIn.Client.util.ResponseListener;

public class XmlParser {
	
	private MessageTree state;

	public void handleLine(String line, ResponseListener responseListener) {
		Token[] tokens = Token.toTokenArray(line);
		try {
			handlerTokenstream(line, tokens, responseListener);
		} catch (ParserException e) {
			DEBUGGER.log("Error at Token " + e.token.toString() + " in Message: " + line + ": " + e.msg);
			responseListener.unhandeledMessage(new RawMessage(line));
		}
	}
	
	private void handlerTokenstream(final String line, Token[] tokens, ResponseListener responseListener) throws ParserException {
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
