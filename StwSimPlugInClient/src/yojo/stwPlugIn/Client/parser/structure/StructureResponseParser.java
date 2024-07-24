package yojo.stwPlugIn.Client.parser.structure;

import java.util.ArrayList;
import java.util.HashMap;

import yojo.stwPlugIn.Client.Messages.StructureResponse;
import yojo.stwPlugIn.Client.Messages.definitions.ShapeConnection;
import yojo.stwPlugIn.Client.Messages.definitions.Shapes;
import yojo.stwPlugIn.Client.Messages.definitions.Shapes.Entry;
import yojo.stwPlugIn.Client.Messages.definitions.Shapes.Exit;
import yojo.stwPlugIn.Client.Messages.definitions.Shapes.Plattform;
import yojo.stwPlugIn.Client.Messages.definitions.Shapes.Shape;
import yojo.stwPlugIn.Client.Messages.definitions.Shapes.Signal;
import yojo.stwPlugIn.Client.Messages.definitions.Shapes.Switch;
import yojo.stwPlugIn.Client.parser.ResponseParser;
import yojo.stwPlugIn.Client.parser.Token;
import yojo.stwPlugIn.Client.parser.XmlParser.ParserException;
import yojo.stwPlugIn.Client.parser.misc.MiscParser;
import yojo.stwPlugIn.Client.util.ResponseListener;

public class StructureResponseParser implements ResponseParser {

	private MiscParser parser;
	
	private ArrayList<Plattform> plattforms = new ArrayList<>();
	private ArrayList<Entry> entrys = new ArrayList<>();
	private ArrayList<Exit> exits = new ArrayList<>();
	private ArrayList<Signal> signals = new ArrayList<>();
	private ArrayList<Switch> switches = new ArrayList<>();
	private HashMap<String, Shape> shapes = new HashMap<>();

	private ArrayList<ShapeConnection> connections = new ArrayList<>();	
	
	private State state;
	
	@Override
	public boolean isFinished() {
		return state == State.Finished;
	}

	@Override
	public void parse(Token t, ResponseListener responseListener) throws ParserException {
		switch(state) {
		case ExpectFirstEnd:
			if(t != Token.END)
				throw new ParserException("Expected >", t);
			state = State.ExpectXmlStart;	
			break;
		case ExpectXmlStart:
			if(t != Token.START)
				throw new ParserException("Expected >", t);
			state = State.ExpectXmlIdentifier;
			break;
		case ExpectXmlIdentifier:
			if(t == Token.SLASH) {
				state = State.ExpectWege;
				break;
			}
			if("shape".equals(t.value)) {
				state = State.Read;
				parser = new ShapeParser(switches, signals, plattforms, exits, entrys, shapes);
				break;
			}
			if("connector".equals(t.value)) {
				state = State.Read;
				parser = new ConnectionParser(connections, shapes);
				break;
			}
			throw new ParserException("Expected shape, connector or /", t);
		case Read:
			parser.parse(t, responseListener);
			if(t == Token.END && parser.isFinished()) {
				state = State.ExpectXmlStart;
			}
			break;
		case ExpectWege:
			if(!"wege".equals(t.value))
				throw new ParserException("Expected wege", t);
			state = State.ExpectSecondEnd;
			break;
		case ExpectSecondEnd:
			if(t != Token.END)
				throw new ParserException("Expected >", t);
			state = State.Finished;
			responseListener.onStructure(new StructureResponse(connections, 
					new Shapes(switches, signals, plattforms, exits, entrys, shapes)));
			break;
		case Finished:
			throw new ParserException("Parser already finished", t);
		default:
			throw new ParserException("State Violation", t);
		}
	}
	
	private static enum State {
		ExpectFirstEnd,
		ExpectXmlStart,
		ExpectXmlIdentifier,
		Read,
		ExpectWege,
		ExpectSecondEnd,
		Finished;
	}

}
