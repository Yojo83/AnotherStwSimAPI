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
import yojo.stwPlugIn.Client.parser.XMLLine;
import yojo.stwPlugIn.Client.parser.XMLLineType;
import yojo.stwPlugIn.Client.parser.XmlParser.LineParserException;
import yojo.stwPlugIn.Client.util.ResponseListener;

/**
 * parses the structure (wege) message
 * @author Yojo
 *
 */
public class StructureResponseParser implements ResponseParser {

	private boolean running = false;
	
	private ArrayList<Plattform> plattforms;
	private ArrayList<Entry> entrys;
	private ArrayList<Exit> exits;
	private ArrayList<Signal> signals;
	private ArrayList<Switch> switches;
	private HashMap<String, Shape> shapes;

	private ArrayList<ShapeConnection> connections = new ArrayList<>();	
	
	@Override
	public void parse(XMLLine line, ResponseListener responseListener) throws LineParserException {
		if(line.Type == XMLLineType.wege) {
			if(running) 
				throw new LineParserException("already parsing", line);
			start();
		} else if(line.Type == XMLLineType.wege_end) {
			responseListener.onStructure(new StructureResponse(connections, 
					new Shapes(switches, signals, plattforms, exits, entrys, shapes)));
			stop();
		} else {
			if(!running)
				throw new LineParserException("currently not parsing schedule list", line);
			if(line.Type == XMLLineType.shape) {
				parseShape(line);
			} else if(line.Type == XMLLineType.connector) {
				parseConnector(line);
			} else {
				throw new LineParserException("expected connector or shape", line);
			}
		}
	}

	private void parseShape(XMLLine line) throws LineParserException {
		int enr = line.contains("enr") ? line.getInt("enr") : 0;
		String name = line.getString("name");
		if(name == null)
			throw new LineParserException("expected name", line);
		
		String type = line.getString("type");
		if(type == null)
			throw new LineParserException("expected type", line);
		
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
			throw new LineParserException("shape type is invalid", line);	
		}
	}

	private void parseConnector(XMLLine line) throws LineParserException {
		String name1 = line.getString("enr1");
		if(name1 == null)
			name1 = line.getString("name1");
		if(name1 == null)
			throw new LineParserException("expected enr1 or name1", line);
		
		Shape shape1 = shapes.get(name1);
		if(shape1 == null)
			throw new LineParserException(name1 + "is not a shape", line);
		
		String name2 = line.getString("enr2");
		if(name2 == null)
			name2 = line.getString("name2");
		if(name2 == null)
			throw new LineParserException("expected enr2 or name2", line);

		Shape shape2 = shapes.get(name1);
		if(shape2 == null)
			throw new LineParserException(name1 + "is not a shape", line);
		
		connections.add(new ShapeConnection(shape1, shape2));
	}

	private void start() {
		running = true;
		
		plattforms = new ArrayList<>();
		entrys = new ArrayList<>();
		exits = new ArrayList<>();
		signals = new ArrayList<>();
		switches = new ArrayList<>();
		shapes = new HashMap<>();

		connections = new ArrayList<>();	
	}

	private void stop() {
		running = false;
		
		plattforms = null;
		entrys = null;
		exits = null;
		signals = null;
		switches = null;
		shapes = null;

		connections = null;	
	}

}
