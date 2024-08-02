package yojo.stwPlugIn.Client.parser.misc;

import yojo.stwPlugIn.Client.Messages.EventResponse;
import yojo.stwPlugIn.Client.Messages.definitions.EventType;
import yojo.stwPlugIn.Client.parser.Token;
import yojo.stwPlugIn.Client.parser.XmlParser.ParserException;
import yojo.stwPlugIn.Client.util.ResponseListener;

/**
 * parses an event message
 * @author Yojo
 *
 */
public class EventResponseParser extends MiscParser {

	private EventType type;
	private int trainId;
	private String name;
	private boolean atPlattform;
	private int delay;
	private String plattform;
	private String regularPlattform;
	private String destination;
	private String source;
	private boolean visible;
	
	private ExpectedValues ev;
	
	@Override
	protected void doAction(ResponseListener responseListener, Token t) throws ParserException {
		responseListener.onEvent(new EventResponse(type, visible, trainId, source, 
				regularPlattform, plattform, name, destination, atPlattform, delay));
	}

	@Override
	protected void setExpectedValue(Token t) throws ParserException {
		switch(t.value) {
		case "zid": ev = ExpectedValues.TrainId; break;
		case "art": ev = ExpectedValues.Type; break;
		case "name": ev = ExpectedValues.Name; break;
		case "verspaetung": ev = ExpectedValues.Delay; break;
		case "gleis": ev = ExpectedValues.Plattform; break;
		case "plangleis": ev = ExpectedValues.RegularPlattform; break;
		case "von": ev = ExpectedValues.Source; break;
		case "nach": ev = ExpectedValues.Destination; break;
		case "sichtbar": ev = ExpectedValues.Visible; break;
		case "amgleis": ev = ExpectedValues.AtPlattform; break;
		default:
			throw new ParserException("failed to deduce token to event variable", t);
		}
	}

	@Override
	protected void setValue(Token t) throws ParserException {
		switch(ev) {
		case AtPlattform:
			atPlattform = Boolean.parseBoolean(t.value);
			break;
		case Delay:
			try {
				delay = Integer.parseInt(t.value);
			} catch (NumberFormatException e) {
				throw new ParserException("delay not a number", t);
			}
			break;
		case Destination:
			destination = t.value;
			break;
		case Name:
			name = t.value;
			break;
		case Plattform:
			plattform = t.value;
			break;
		case RegularPlattform:
			regularPlattform = t.value;
			break;
		case Source:
			source = t.value;
			break;
		case TrainId:
			try {
				trainId = Integer.parseInt(t.value);
			} catch (NumberFormatException e) {
				throw new ParserException("trainId not a number", t);
			}
			break;
		case Type:
			switch(t.value.toLowerCase()) {
			case "abfahrt": type = EventType.Departure; break;
			case "ankunft": type = EventType.Arrival; break;
			case "ausfahrt": type = EventType.Exit; break;
			case "einfahrt": type = EventType.Entrance; break;
			case "fluegeln": type = EventType.Split; break;
			case "kuppeln": type = EventType.Combine; break;
			case "rothalt": type = EventType.RedStop; break;
			case "wurdegruen": type = EventType.GotGreen; break;
			default:
				throw new ParserException("not a event type", t);
			}
			break;
		case Visible:
			visible = Boolean.parseBoolean(t.value);
			break;
		default:
			throw new ParserException("internal (impossible) error", t);
		}
	}

	private static enum ExpectedValues{
		Type,
		TrainId,
		Name,
		AtPlattform,
		Delay,
		Plattform,
		RegularPlattform,
		Destination,
		Source,
		Visible;
	}
	
}
