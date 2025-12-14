package yojo.stwPlugIn.Client.parser.misc;

import yojo.stwPlugIn.Client.Messages.TimeResponse;
import yojo.stwPlugIn.Client.parser.ResponseParser;
import yojo.stwPlugIn.Client.parser.XMLLine;
import yojo.stwPlugIn.Client.parser.XmlParser.LineParserException;
import yojo.stwPlugIn.Client.util.ResponseListener;

/**
 * parses the time response message
 * @author Yojo
 *
 */
public class TimeResponseParser implements ResponseParser {

	@Override
	public void parse(XMLLine line, ResponseListener responseListener) throws LineParserException {
		long time = line.getLong("zeit");
		responseListener.onTime(new TimeResponse(time));
	}

}
