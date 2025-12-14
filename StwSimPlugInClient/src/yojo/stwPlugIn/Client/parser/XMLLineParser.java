package yojo.stwPlugIn.Client.parser;

import java.util.HashMap;

import yojo.stwPlugIn.Client.parser.XmlParser.TokenParserException;

public class XMLLineParser {

	private HashMap<String, String> values = new HashMap<>();
	private String expectedValue = "";
	private State state = State.EXPECT_START;
	private XMLLineType type = null;
	
	public boolean finished() {
		return state == State.ENDED;
	}
	
	public XMLLine getLine() {
		return new XMLLine(type, values);
	}
	
	public void parse(Token t) throws TokenParserException {
		if(t == Token.SLASH) {
			state = State.EXPECT_END;
			return;
		}
		
		switch(state) {
		case EXPECT_START:
			if(t == Token.END){
				state = State.EXPECT_ID;
				break;
			}
			throw new TokenParserException("Expected <", t);
		case EXPECT_ID:
			if(t.value != null) {
				type = XMLLineType.parse(t, false);
				state = State.EXPECT_TYPE;
				break;
			}
			throw new TokenParserException("Expected string", t);
		case EXPECT_TYPE:
			if(t.value != null) {
				state = State.EXPECT_EQUAL;
				expectedValue = t.value;
				break;
			}
			throw new TokenParserException("Expected string ", t);
		case EXPECT_EQUAL:
			if(t == Token.EQUAL) {
				state = State.EXPECT_VALUE;
				break;
			}
			throw new TokenParserException("Expected = ", t);
		case EXPECT_VALUE:
			if(t.value != null) {
				state = State.EXPECT_TYPE;
				values.put(expectedValue, t.value);
				break;
			}
			throw new TokenParserException("Expected string ", t);
		case EXPECT_END:
			if(t == Token.END) {
				state = State.ENDED;
				break;
			}
			if(type == null && t.value != null) {
				type = XMLLineType.parse(t, true);
				break;
			}
			throw new TokenParserException("Expected >", t);
		case ENDED:
			throw new TokenParserException("Expected no Tokens", t);
		default:
			throw new TokenParserException("TrainDetailsResponseParser was not in a valid state", t);
		}
	}

	private static enum State{
		EXPECT_START,
		EXPECT_ID,
		EXPECT_TYPE,
		EXPECT_EQUAL,
		EXPECT_VALUE,
		EXPECT_END,
		ENDED;
	}


}
