package yojo.stwPlugIn.Client.parser.misc;

import yojo.stwPlugIn.Client.parser.ResponseParser;
import yojo.stwPlugIn.Client.parser.Token;
import yojo.stwPlugIn.Client.parser.XmlParser.ParserException;
import yojo.stwPlugIn.Client.util.ResponseListener;

/**
 * A base class for parsers, parsing one xml block.
 * This class will search through the xml block, calls setExpectedvalue with the parameter name,
 * and then setValue with the prameter value. 
 * At the end of the xml block it calls doAction at set the parser finished
 * @author Yojo
 *
 */
public abstract class MiscParser implements ResponseParser {

	private boolean finished;
	private State state = State.NULL;
	
	@Override
	public boolean isFinished() {
		return finished;
	}

	@Override
	public void parse(Token t, ResponseListener responseListener) throws ParserException {
		if(t == Token.SLASH) {
			state = State.EXPECT_END;
			return;
		}
		
		switch(state) {
		case EXPECT_EQUAL:
			if(t != Token.EQUAL)
				throw new ParserException("Expected = but didn't found it", t);
			state = State.EXPECT_VALUE;
			break;
		case EXPECT_VALUE:
			if(t.value == null)
				throw new ParserException("Expected string but didn't found it", t);
			setValue(t);
			state = State.NULL;
			break;
		case NULL:
			if(t.value == null)
				throw new ParserException("Expected string but didn't found it", t);
			state = State.EXPECT_EQUAL;
			setExpectedValue(t);
			break;
		case EXPECT_END:
			if(t == Token.END) {
				finished = true;
				doAction(responseListener, t);
				break;
			}
			throw new ParserException("Expected >", t);
		default:
			throw new ParserException("TrainDetailsResponseParser was not in a valid state", t);
		}
	}
	
	

	protected abstract void doAction(ResponseListener responseListener, Token t) throws ParserException;

	protected abstract void setExpectedValue(Token t) throws ParserException;

	protected abstract void setValue(Token t) throws ParserException;




	private static enum State{
		NULL,
		EXPECT_EQUAL,
		EXPECT_VALUE,
		EXPECT_END;
	}

}
