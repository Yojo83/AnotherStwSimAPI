package yojo.stwPlugIn.Client.parser.misc;

import yojo.stwPlugIn.Client.Messages.EnrFromElementResponse;
import yojo.stwPlugIn.Client.parser.Token;
import yojo.stwPlugIn.Client.parser.XmlParser.ParserException;
import yojo.stwPlugIn.Client.util.ResponseListener;

public class Enr4ResponseParser extends MiscParser {

	private int enr;
	private String element;
	
	private boolean expectEnr;
	
	
	@Override
	protected void doAction(ResponseListener responseListener, Token t) throws ParserException {
		responseListener.onEnrFromElement(new EnrFromElementResponse(enr, element));
	}

	@Override
	protected void setExpectedValue(Token t) throws ParserException {
		if(t.value == null)
			throw new ParserException("Expected string but didn't found it", t);
		else if(t.value.equals("element"))
			expectEnr = false;
		else if(t.value.equals("enr"))
			expectEnr = true;
		else
			throw new ParserException("expected enr or element", t);
	}

	@Override
	protected void setValue(Token t) throws ParserException {
		if(t.value == null)
			throw new ParserException("Expected string but didn't found it", t);
		if(expectEnr) {
			try {
				enr = Integer.parseInt(t.value);
			} catch (Exception e) {
				throw new ParserException("enr not a number", t);
			}
		}else
			element = t.value;
	}

}
