package yojo.stwPlugIn.Client.parser.misc;

import yojo.stwPlugIn.Client.Messages.StitzResponse;
import yojo.stwPlugIn.Client.parser.ResponseParser;
import yojo.stwPlugIn.Client.parser.XMLLine;
import yojo.stwPlugIn.Client.parser.XmlParser.LineParserException;
import yojo.stwPlugIn.Client.util.ResponseListener;

/**
 * parses the stitz message
 * @author Yojo
 *
 */
public class StitzResponseParser implements ResponseParser {

	@Override
	public void parse(XMLLine line, ResponseListener responseListener) throws LineParserException {
		String regional = line.getString("region");
		String common = line.getString("allgemein");
		
		responseListener.onStitz(new StitzResponse(regional, common));
	}

}
