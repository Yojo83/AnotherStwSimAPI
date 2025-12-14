package yojo.stwPlugIn.Client.parser.misc;

import yojo.stwPlugIn.Client.Messages.HeatResponse;
import yojo.stwPlugIn.Client.parser.ResponseParser;
import yojo.stwPlugIn.Client.parser.XMLLine;
import yojo.stwPlugIn.Client.parser.XmlParser.LineParserException;
import yojo.stwPlugIn.Client.util.ResponseListener;

/**
 * parses the heat message
 * @author Yojo
 *
 */
public class HeatResponseParser implements ResponseParser {

	@Override
	public void parse(XMLLine line, ResponseListener responseListener) throws LineParserException {
		long heat = line.getLong("hitze");
		responseListener.onHeat(new HeatResponse(heat));
	}

}
