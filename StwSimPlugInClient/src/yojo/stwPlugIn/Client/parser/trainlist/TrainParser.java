package yojo.stwPlugIn.Client.parser.trainlist;

import yojo.stwPlugIn.Client.parser.Token;
import yojo.stwPlugIn.Client.parser.XmlParser.ParserException;
import yojo.stwPlugIn.Client.parser.misc.MiscParser;
import yojo.stwPlugIn.Client.util.ResponseListener;

public class TrainParser extends MiscParser {

	public int tid;
	public String name;
	
	private boolean expectName;
	

	@Override
	protected void doAction(ResponseListener responseListener, Token t) throws ParserException {
	}

	@Override
	protected void setExpectedValue(Token t) throws ParserException {
		if(t.value == null)
			throw new ParserException("Expected string but didn't found it", t);
		else if(t.value.equals("zid"))
			expectName = false;
		else if(t.value.equals("name"))
			expectName = true;
		else
			throw new ParserException("expected region or allgemein", t);
	}

	@Override
	protected void setValue(Token t) throws ParserException {
		if(t.value == null)
			throw new ParserException("Expected string but didn't found it", t);
		if(expectName)
			name = t.value;
		else
			try {
				tid = Integer.parseInt(t.value);
			} catch (Exception e) {
				throw new ParserException("train id not a number", t);
			}
	}

}
