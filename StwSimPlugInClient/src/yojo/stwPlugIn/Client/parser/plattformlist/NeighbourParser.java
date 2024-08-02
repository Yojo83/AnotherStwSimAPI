package yojo.stwPlugIn.Client.parser.plattformlist;

import yojo.stwPlugIn.Client.parser.Token;
import yojo.stwPlugIn.Client.parser.XmlParser.ParserException;
import yojo.stwPlugIn.Client.parser.misc.MiscParser;
import yojo.stwPlugIn.Client.util.ResponseListener;

/**
 * parses one neightbour of plattforms
 * @author Yojo
 *
 */
public class NeighbourParser extends MiscParser {

	public String value;

	@Override
	protected void doAction(ResponseListener responseListener, Token t) throws ParserException {
	}

	@Override
	protected void setExpectedValue(Token t) throws ParserException {
		if(!"name".equals(t.value))
			throw new ParserException("expected name", t);
	}

	@Override
	protected void setValue(Token t) throws ParserException {
		value = t.value;
	}

}
