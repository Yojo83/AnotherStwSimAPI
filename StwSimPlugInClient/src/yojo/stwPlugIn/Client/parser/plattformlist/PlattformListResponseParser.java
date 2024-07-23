package yojo.stwPlugIn.Client.parser.plattformlist;

import java.util.ArrayList;

import yojo.stwPlugIn.Client.Messages.PlattformlistResponse;
import yojo.stwPlugIn.Client.Messages.definitions.PlattformData;
import yojo.stwPlugIn.Client.parser.ResponseParser;
import yojo.stwPlugIn.Client.parser.Token;
import yojo.stwPlugIn.Client.parser.XmlParser.ParserException;
import yojo.stwPlugIn.Client.util.ResponseListener;

public class PlattformListResponseParser implements ResponseParser {
	
	private PlattformParser parser;

	private ArrayList<PlattformData> entrys = new ArrayList<>();
	
	private State state = State.ReadFirstEnd;
	
	@Override
	public boolean isFinished() {
		return state == State.Finished;
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
			state = State.ReadNewXml;
			break;
		case ReadXmlIdentifier:
			if(t == Token.SLASH) {
				state = State.ReadBahnsteigListe;
				break;
			}
			if("bahnsteig".equals(t.value)) {
				state = State.ReadPlattform;
				parser = new PlattformParser();
				break;
			}
			throw new ParserException("expected / or bahnsteig", t);
		case ReadPlattform:
			parser.parse(t);
			if(t == Token.END && parser.isFinished()) {
				entrys.add(parser.getEntry());
				state = State.ReadNewXml;
			}
			break;
		case ReadBahnsteigListe:
			if(!"bahnsteigliste".equals(t.value))
				throw new ParserException("expected bahnsteigliste", t);
			break;
		case ReadSecondEnd:
			if(t != Token.END)
				throw new ParserException("expected >", t);
			state = State.Finished;
			responseListener.onPlattformList(new PlattformlistResponse(entrys));
			break;
		default:
			throw new ParserException("State violation", t);
		}
	}

	
	private static enum State{
		ReadFirstEnd,
		ReadNewXml,
		ReadXmlIdentifier,
		ReadPlattform,
		ReadBahnsteigListe,
		ReadSecondEnd,
		Finished;
	}
}
