package yojo.stwPlugIn.Client.parser.structure;

import java.util.ArrayList;
import java.util.HashMap;

import yojo.stwPlugIn.Client.Messages.definitions.Shapes.Entry;
import yojo.stwPlugIn.Client.Messages.definitions.Shapes.Exit;
import yojo.stwPlugIn.Client.Messages.definitions.Shapes.Plattform;
import yojo.stwPlugIn.Client.Messages.definitions.Shapes.Shape;
import yojo.stwPlugIn.Client.Messages.definitions.Shapes.Signal;
import yojo.stwPlugIn.Client.Messages.definitions.Shapes.Switch;
import yojo.stwPlugIn.Client.parser.Token;
import yojo.stwPlugIn.Client.parser.XmlParser.ParserException;
import yojo.stwPlugIn.Client.parser.misc.MiscParser;
import yojo.stwPlugIn.Client.util.ResponseListener;

public class ShapeParser extends MiscParser {


	private final ArrayList<Plattform> plattforms;
	private final ArrayList<Entry> entrys;
	private final ArrayList<Exit> exits;
	private final ArrayList<Signal> signals;
	private final ArrayList<Switch> switches;
	
	private final HashMap<String, Shape> shapes;
	
	
	private int enr;
	private String name;
	private String type;
	
	private ExpectedValues ev;
	
	public ShapeParser(ArrayList<Switch> switches, ArrayList<Signal> signals, ArrayList<Plattform> plattforms, 
			ArrayList<Exit> exits, ArrayList<Entry> entrys, HashMap<String, Shape> shapes) {
		this.plattforms = plattforms;
		this.entrys = entrys;
		this.exits = exits;
		this.signals = signals;
		this.switches = switches;
		this.shapes = shapes;
	}

	@Override
	protected void doAction(ResponseListener responseListener, Token t) throws ParserException {
		switch(type) {
		case "2": 
			Signal signal = new Signal(name, enr);
			signals.add(signal);
			shapes.put(String.valueOf(enr), signal);
			break;
		case "3": 
			Switch s = new Switch(name, enr, false);
			switches.add(s);
			shapes.put(String.valueOf(enr), s);
			break;
		case "4":
			s = new Switch(name, enr, true);
			switches.add(s);
			shapes.put(String.valueOf(enr), s);
			break;
		case "5":
			Plattform p = new Plattform(name, false);
			plattforms.add(p);
			shapes.put(name, p);
			break;
		case "6":
			Entry en = new Entry(name, enr);
			entrys.add(en);
			shapes.put(String.valueOf(enr), en);
			break;
		case "7":
			Exit ex = new Exit(name, enr);
			exits.add(ex);
			shapes.put(String.valueOf(enr), ex);
			break;
		case "12":
			p = new Plattform(name, true);
			plattforms.add(p);
			shapes.put(name, p);
			break;
		default:
			throw new ParserException("shape type is invalid", t);	
		}
	}

	@Override
	protected void setExpectedValue(Token t) throws ParserException {
		switch(t.value) {
		case "enr": ev = ExpectedValues.Enr; break;
		case "name": ev = ExpectedValues.Name; break;
		case "type": ev = ExpectedValues.Type; break;
		default:
			throw new ParserException("can't reduce to shape field", t);
		}
	}

	@Override
	protected void setValue(Token t) throws ParserException {
		switch(ev) {
		case Enr:
			try {
				enr = Integer.parseInt(t.value);
			} catch (NumberFormatException e) {
				throw new ParserException("enr is not a number", t);
			}
			break;
		case Name:
			name = t.value;
			break;
		case Type:
			type = t.value;
			break;
		default:
			throw new ParserException("internal (impossible) failure", t);
		}
	}



	private static enum ExpectedValues {
		Enr,
		Name,
		Type;
	}

}
