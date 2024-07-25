package yojo.stwPlugIn.Client.parser.misc;

import yojo.stwPlugIn.Client.Messages.TimeResponse;
import yojo.stwPlugIn.Client.parser.Token;
import yojo.stwPlugIn.Client.parser.XmlParser.ParserException;
import yojo.stwPlugIn.Client.util.ResponseListener;

public class TimeResponseParser extends MiscParser {

	private long time;
	
	private boolean expectTime;
	
	@Override
	protected void doAction(ResponseListener responseListener, Token t) throws ParserException {
		responseListener.onTime(new TimeResponse(time));
	}

	@Override
	protected void setExpectedValue(Token t) throws ParserException {
		if("sender".equals(t.value)) {
			expectTime = false;
			return;
		}
		if("zeit".equals(t.value)) {
			expectTime = true;
			return;
		}
		expectTime = false;
		throw new ParserException("expected hitze", t);
	}

	@Override
	protected void setValue(Token t) throws ParserException {
		try {
			if(expectTime)
				time = Long.parseLong(t.value);
		} catch (NumberFormatException e) {
			throw new ParserException("heat not a number", t);
		}
	}

}
