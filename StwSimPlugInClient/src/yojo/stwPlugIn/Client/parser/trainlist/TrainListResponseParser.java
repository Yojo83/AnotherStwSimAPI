package yojo.stwPlugIn.Client.parser.trainlist;

import java.util.HashMap;

import yojo.stwPlugIn.Client.Messages.TrainListResponse;
import yojo.stwPlugIn.Client.parser.ResponseParser;
import yojo.stwPlugIn.Client.parser.Token;
import yojo.stwPlugIn.Client.parser.XmlParser.ParserException;
import yojo.stwPlugIn.Client.util.ResponseListener;

public class TrainListResponseParser implements ResponseParser {

	private TrainParser parser;
	
	private HashMap<Integer, String> entrys = new HashMap<>();
	
	private boolean isFinished = false;
	private State state = State.ReadFirstEnd;
	
	@Override
	public boolean isFinished() {
		return isFinished;
	}

	@Override
	public void parse(Token t, ResponseListener responseListener) throws ParserException {
		switch(state) {
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
				state = State.ReadZugliste;
				break;
			}
			if("gleis".equals(t.value)) {
				state = State.ReadTrain;
				parser = new TrainParser();
				break;
			}
			throw new ParserException("Expected / or zug", t);
		case ReadTrain:
			parser.parse(t, responseListener);
			if(t == Token.END) {
				if(!parser.isFinished())
					throw new ParserException("not all values of plattform are set but message is over", t);
				state = State.ReadNewXml;
				entrys.put(parser.tid, parser.name);
			}
			break;
		case ReadZugliste:
			if(!"zugliste".equals(t.value.toLowerCase()))
				throw new ParserException("expected zugfahrplan", t);
			state = State.ReadSecondEnd;
			break;
		case ReadSecondEnd:
			if(t != Token.END)
				throw new ParserException("expected >", t);
			this.isFinished = true;
			responseListener.onTrainList(new TrainListResponse(entrys));
			break;
		default:
			throw new ParserException("State violation", t);
		}
	}

	
	private static enum State{
		ReadFirstEnd,
		ReadNewXml,
		ReadXmlIdentifier,
		ReadTrain,
		ReadZugliste,
		ReadSecondEnd;
	}
}
