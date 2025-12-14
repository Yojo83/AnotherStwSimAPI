package yojo.stwPlugIn.Client.parser.misc;

import yojo.stwPlugIn.Client.Messages.EnrFromElementResponse;
import yojo.stwPlugIn.Client.parser.ResponseParser;
import yojo.stwPlugIn.Client.parser.XMLLine;
import yojo.stwPlugIn.Client.parser.XmlParser.LineParserException;
import yojo.stwPlugIn.Client.util.ResponseListener;

/**
 * parses the enr4 message
 * @author Yojo
 *
 */
public class Enr4ResponseParser implements ResponseParser {

	@Override
	public void parse(XMLLine line, ResponseListener responseListener) throws LineParserException {
		String element = line.getString("element");
		if(element == null) 
			throw new LineParserException("didn't found element", line);
		
		int enr = line.getInt("enr");

		responseListener.onEnrFromElement(new EnrFromElementResponse(enr, element));
	}

}
