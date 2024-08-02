package yojo.stwPlugIn.Client.parser.misc;

import yojo.stwPlugIn.Client.Messages.TrainDetailsResponse;
import yojo.stwPlugIn.Client.parser.Token;
import yojo.stwPlugIn.Client.parser.XmlParser.ParserException;
import yojo.stwPlugIn.Client.util.ResponseListener;

/**
 * parses the train details message
 * @author Yojo
 *
 */
public class TrainDetailsResponseParser extends MiscParser {

	private int trainId;
	private String name;
	private int delay;
	private boolean atPlattform;
	private String plattform;
	private String regularPlattform;
	private String destination;
	private String source;
	private boolean visible;
	private String userText;
	private String userTextSender;
	
	private ExpectedValue expectedValue;
	
	


	@Override
	protected void doAction(ResponseListener responseListener, Token t) {
		responseListener.onTrainDetails(new TrainDetailsResponse(
				trainId, name, delay, atPlattform, plattform, regularPlattform, 
				destination, source, visible, userText, userTextSender));
	}

	@Override
	protected void setExpectedValue(Token t) throws ParserException {
		switch(t.value) {
		case "zid": expectedValue = ExpectedValue.TrainId; break;
		case "name": expectedValue = ExpectedValue.Name; break;
		case "verspaetung": expectedValue = ExpectedValue.Delay; break;
		case "gleis": expectedValue = ExpectedValue.Plattform; break;
		case "plangleis": expectedValue = ExpectedValue.RegularPlattform; break;
		case "von": expectedValue = ExpectedValue.Source; break;
		case "nach": expectedValue = ExpectedValue.Destination; break;
		case "sichtbar": expectedValue = ExpectedValue.Visible; break;
		case "amgleis": expectedValue = ExpectedValue.AtPlattform; break;
		case "usertext": expectedValue = ExpectedValue.UserText; break;
		case "usertextsender": expectedValue = ExpectedValue.UserTextSender; break;
		default:
			throw new ParserException("failed to deduce token to traindetail variable", t);
		}
	}

	@Override
	protected void setValue(Token t) throws ParserException {
		switch (expectedValue) {
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
		case UserText:
			userText = t.value;
			break;
		case UserTextSender:
			userTextSender = t.value;
			break;
		case Visible:
			visible = Boolean.parseBoolean(t.value);
			break;
		default:
			throw new ParserException("internal (impossible) error", t);
		}
	}
	

	private static enum ExpectedValue {
		TrainId,
		Name,
		Delay,
		AtPlattform,
		Plattform,
		RegularPlattform,
		Destination,
		Source,
		Visible,
		UserText,
		UserTextSender;
	}


}
