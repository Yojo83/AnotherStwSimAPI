package yojo.stwPlugIn.Client.parser.misc;

import yojo.stwPlugIn.Client.Messages.TrainDetailsResponse;
import yojo.stwPlugIn.Client.parser.ResponseParser;
import yojo.stwPlugIn.Client.parser.XMLLine;
import yojo.stwPlugIn.Client.parser.XmlParser.LineParserException;
import yojo.stwPlugIn.Client.util.ResponseListener;

/**
 * parses the train details message
 * @author Yojo
 *
 */
public class TrainDetailsResponseParser implements ResponseParser {

	@Override
	public void parse(XMLLine line, ResponseListener responseListener) throws LineParserException {
		int trainId = line.getInt("zid");
		int delay = line.getInt("verspaetung");
		
		boolean atPlattform = line.getBool("amgleis");
		boolean visible = line.getBool("sichtbar");
		
		String plattform = line.getString("gleis");
		String regularPlattform = line.getString("plangleis");
		String destination = line.getString("nach");
		String source = line.getString("von");
		String userText = line.getString("usertext");
		String userTextSender = line.getString("usertextsender");
		String name = line.getString("name");

		responseListener.onTrainDetails(new TrainDetailsResponse(
				trainId, name, delay, atPlattform, plattform, regularPlattform, 
				destination, source, visible, userText, userTextSender));
	}


}
