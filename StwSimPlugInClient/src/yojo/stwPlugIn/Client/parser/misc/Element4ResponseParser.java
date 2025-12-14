package yojo.stwPlugIn.Client.parser.misc;

import yojo.stwPlugIn.Client.Messages.ElementFromEnrResponse;
import yojo.stwPlugIn.Client.parser.ResponseParser;
import yojo.stwPlugIn.Client.parser.XMLLine;
import yojo.stwPlugIn.Client.parser.XmlParser.LineParserException;
import yojo.stwPlugIn.Client.util.ResponseListener;

/**
 * parses the element4 message
 * @author Yojo
 *
 */
public class Element4ResponseParser implements ResponseParser {

	@Override
	public void parse(XMLLine line, ResponseListener responseListener) throws LineParserException {
		String element = line.getString("element");
		if(element == null) 
			throw new LineParserException("didn't found element", line);
		
		int enr = line.getInt("enr");
		
		responseListener.onElementFromEnr(new ElementFromEnrResponse(element, enr));
	}

}
