package yojo.stwPlugIn.Client.parser.misc;

import yojo.stwPlugIn.Client.Messages.StitzResponse;
import yojo.stwPlugIn.Client.parser.Token;
import yojo.stwPlugIn.Client.parser.XmlParser.ParserException;
import yojo.stwPlugIn.Client.util.ResponseListener;

/**
 * parses the stitz message
 * @author Yojo
 *
 */
public class StitzResponseParser extends MiscParser {

	private String regional;
	private String common;
	
	
	private boolean expectCommon = false;
	
	
	@Override
	protected void doAction(ResponseListener responseListener, Token t) throws ParserException {
		responseListener.onStitz(new StitzResponse(regional, common));
	}

	@Override
	protected void setExpectedValue(Token t) throws ParserException {
		if(t.value == null)
			throw new ParserException("Expected string but didn't found it", t);
		else if(t.value.equals("region"))
			expectCommon = false;
		else if(t.value.equals("allgemein"))
			expectCommon = true;
		else
			throw new ParserException("expected region or allgemein", t);
	}

	@Override
	protected void setValue(Token t) throws ParserException {
		if(t.value == null)
			throw new ParserException("Expected string but didn't found it", t);
		if(expectCommon)
			common = t.value;
		else
			regional = t.value;
	}

}
