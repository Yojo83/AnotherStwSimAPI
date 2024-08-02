package yojo.stwPlugIn.Client.parser.schedule;

import java.util.ArrayList;

import yojo.stwPlugIn.Client.Messages.TrainScheduleResponse;
import yojo.stwPlugIn.Client.Messages.definitions.ScheduleEntry;
import yojo.stwPlugIn.Client.parser.ResponseParser;
import yojo.stwPlugIn.Client.parser.Token;
import yojo.stwPlugIn.Client.parser.XmlParser.ParserException;
import yojo.stwPlugIn.Client.util.ResponseListener;

/**
 * parses the train schedule message
 * @author Yojo
 *
 */
public class TrainScheduleResponseParser implements ResponseParser {

	private ScheduleEntryParser parser;
	
	private int trainId;
	private ArrayList<ScheduleEntry> entrys = new ArrayList<>();;
	
	private boolean isFinished = false;
	private State state = State.ReadZid;
	
	@Override
	public boolean isFinished() {
		return isFinished;
	}

	@Override
	public void parse(Token t, ResponseListener responseListener) throws ParserException {
		switch(state) {
		case ReadZid:
			if(!"zid".equals(t.value))
				throw new ParserException("expected zid", t);
			state = State.ReadEqual;
			break;
		case ReadEqual:
			if(t != Token.EQUAL)
				throw new ParserException("expected =", t);
			state = State.ReadTrainId;
			break;
		case ReadTrainId:
			if(t.value == null)
				throw new ParserException("expected zid value", t);
			try {
				this.trainId = Integer.parseInt(t.value);
			} catch (NumberFormatException e) {
				throw new ParserException("not a number", t);
			}
			state = State.ReadFirstEnd;
			break;
		case ReadFirstEnd:
			if(t != Token.END)
				throw new ParserException("expected >", t);
			state = State.ReadNewXml;
			break;
		case ReadNewXml:
			if(t != Token.START)
				throw new ParserException("expected <", t);
			state = State.ReadXmlIdentifier;
			break;
		case ReadXmlIdentifier:
			if(t == Token.SLASH) {
				state = State.ReadZugfahrplan;
				break;
			}
			if("gleis".equals(t.value)) {
				state = State.ReadPlattform;
				parser = new ScheduleEntryParser();
				break;
			}
			throw new ParserException("Expected / or gleis", t);
		case ReadPlattform:
			if(t == Token.END) {
				if(!parser.isFinished())
					throw new ParserException("not all values of plattform are set but message is over", t);
				state = State.ReadNewXml;
				entrys.add(parser.getEntry());
				break;
			}
			parser.parse(t);
			break;
		case ReadZugfahrplan:
			if(!"zugfahrplan".equals(t.value))
				throw new ParserException("expected zugfahrplan", t);
			state = State.ReadSecondEnd;
			break;
		case ReadSecondEnd:
			if(t != Token.END)
				throw new ParserException("expected >", t);
			this.isFinished = true;
			responseListener.onTrainSchedule(new TrainScheduleResponse(trainId, entrys));
			break;
		default:
			throw new ParserException("State violation", t);
		}
	}

	
	private static enum State{
		ReadZid,
		ReadEqual,
		ReadTrainId,
		ReadFirstEnd,
		ReadNewXml,
		ReadXmlIdentifier,
		ReadPlattform,
		ReadZugfahrplan,
		ReadSecondEnd;
	}
}
