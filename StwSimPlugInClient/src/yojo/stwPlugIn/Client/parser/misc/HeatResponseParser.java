package yojo.stwPlugIn.Client.parser.misc;

import yojo.stwPlugIn.Client.Messages.HeatResponse;
import yojo.stwPlugIn.Client.parser.Token;
import yojo.stwPlugIn.Client.parser.XmlParser.ParserException;
import yojo.stwPlugIn.Client.util.ResponseListener;

/**
 * parses the heat message
 * @author Yojo
 *
 */
public class HeatResponseParser extends MiscParser {

	
	private long heat;
	
	
	@Override
	protected void doAction(ResponseListener responseListener, Token t) throws ParserException {
		responseListener.onHeat(new HeatResponse(heat));
	}

	@Override
	protected void setExpectedValue(Token t) throws ParserException {
		if(!"hitze".equals(t.value))
			throw new ParserException("expected hitze", t);
	}

	@Override
	protected void setValue(Token t) throws ParserException {
		try {
			heat = Long.parseLong(t.value);
		} catch (NumberFormatException e) {
			throw new ParserException("heat not a number", t);
		}
	}

}
