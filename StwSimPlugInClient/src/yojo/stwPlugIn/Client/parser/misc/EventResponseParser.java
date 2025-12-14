package yojo.stwPlugIn.Client.parser.misc;

import yojo.stwPlugIn.Client.Messages.EventResponse;
import yojo.stwPlugIn.Client.Messages.definitions.EventType;
import yojo.stwPlugIn.Client.parser.ResponseParser;
import yojo.stwPlugIn.Client.parser.XMLLine;
import yojo.stwPlugIn.Client.parser.XmlParser.LineParserException;
import yojo.stwPlugIn.Client.util.ResponseListener;

/**
 * parses an event message
 * @author Yojo
 *
 */
public class EventResponseParser implements ResponseParser {

	@Override
	public void parse(XMLLine line, ResponseListener responseListener) throws LineParserException {
		String value;
		
		EventType type; 
		value = line.getString("art");
		if(value == null)
			throw new LineParserException("expected event type", line);
		switch(value) {
		case "abfahrt": type = EventType.Departure; break;
		case "ankunft": type = EventType.Arrival; break;
		case "ausfahrt": type = EventType.Exit; break;
		case "einfahrt": type = EventType.Entrance; break;
		case "fluegeln": type = EventType.Split; break;
		case "kuppeln": type = EventType.Combine; break;
		case "rothalt": type = EventType.RedStop; break;
		case "wurdegruen": type = EventType.GotGreen; break;
		default:
			throw new LineParserException("not a event type", line);
		}
		
		int trainId = line.getInt("zid");
		
		int delay = line.getInt("verspaetung");
		
		boolean visible = Boolean.parseBoolean(line.getString("sichtbar"));
		boolean atPlattform = Boolean.parseBoolean(line.getString("amgleis"));
		
		
		String plattform = line.getString("gleis");
		String regularPlattform = line.getString("plangleis");
		String destination = line.getString("nach");
		String source = line.getString("von");
		String name = line.getString("name");
		
		
		responseListener.onEvent(new EventResponse(type, visible, trainId, source, 
				regularPlattform, plattform, name, destination, atPlattform, delay));
	}

}
