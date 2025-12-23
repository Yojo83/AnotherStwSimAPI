package yojo.stwPlugIn.Client.parser.misc;

import yojo.stwPlugIn.Client.parser.ResponseParser;
import yojo.stwPlugIn.Client.parser.XMLLine;
import yojo.stwPlugIn.Client.parser.XmlParser.LineParserException;
import yojo.stwPlugIn.Client.util.DEBUGGER;
import yojo.stwPlugIn.Client.util.ResponseListener;

public class StatusResponseParser implements ResponseParser {

	@Override
	public void parse(XMLLine line, ResponseListener responseListener) throws LineParserException {
		DEBUGGER.log(line.toString());
	}

}
