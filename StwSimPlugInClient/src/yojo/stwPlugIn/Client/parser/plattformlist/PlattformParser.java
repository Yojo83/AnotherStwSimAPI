package yojo.stwPlugIn.Client.parser.plattformlist;

import java.util.ArrayList;

import yojo.stwPlugIn.Client.Messages.definitions.PlattformData;
import yojo.stwPlugIn.Client.parser.Token;
import yojo.stwPlugIn.Client.parser.XmlParser.ParserException;
import yojo.stwPlugIn.Client.util.ResponseListener;

/**
 * parses one plattform of the plattform list
 * @author Yojo
 *
 */
public class PlattformParser {

	private NeighbourParser parser;
	
	private String name;
	private boolean stop;
	private ArrayList<String> neighbours = new ArrayList<>();
	
	private State state = State.ExpectType;
	private boolean expectName;
	
	public boolean isFinished() {
		return state == State.Finished;
	}

	public void parse(Token t, ResponseListener listener) throws ParserException {
		switch(state) {
		case ExpectType:
			if(t == Token.END) {
				state = State.ExpectXmlStart;
				break;
			}
			if("name".equals(t.value)) {
				expectName = true;
				state = State.ExpectEqual;
				break;
			}
			if("haltepunkt".equals(t.value)) {
				expectName = false;
				state = State.ExpectEqual;
				break;
			}
			throw new ParserException("expected name, haltepunkt or >", t);
		case ExpectEqual:
			if(t != Token.EQUAL)
				throw new ParserException("Expected =", t);
			state = State.ExpectValue;
			break;
		case ExpectValue:
			if(t.value == null)
				throw new ParserException("Expected value", t);
			if(expectName)
				name = t.value;
			else
				stop = Boolean.parseBoolean(t.value);
			state = State.ExpectType;
			break;
		case ExpectXmlStart:
			if(t != Token.START)
				throw new ParserException("Expected <", t);
			state = State.ExpectXmlIdentifier;
			break;
		case ExpectXmlIdentifier:
			if(t == Token.SLASH) {
				state = State.ExpectBahnsteig;
				break;
			}
			if("n".equals(t.value)) {
				state = State.ReadNeighbour;
				parser = new NeighbourParser();
				break;
			}
			throw new ParserException("Expected / or n", t);
		case ReadNeighbour:
			parser.parse(t, listener);
			if(t == Token.END && parser.isFinished()) {
				state = State.ExpectXmlStart;
				this.neighbours.add(parser.value);
			}
			break;
		case ExpectBahnsteig:
			if(!"bahnsteig".equals(t.value))
				throw new ParserException("Expected bahnsteig", t);
			state = State.ExpectEnd;
			break;
		case ExpectEnd:
			state = State.Finished;
			break;
		default:
			throw new ParserException("State Violation", t);
		}
	}

	public PlattformData getEntry() {
		return new PlattformData(name, stop, neighbours);
	}

	private static enum State{
		ExpectType,
		ExpectEqual,
		ExpectValue,
		ExpectXmlStart,
		ExpectXmlIdentifier,
		ReadNeighbour,
		ExpectBahnsteig,
		ExpectEnd,
		Finished;
	}
}
