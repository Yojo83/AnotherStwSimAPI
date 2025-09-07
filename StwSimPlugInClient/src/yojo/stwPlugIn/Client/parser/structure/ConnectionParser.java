package yojo.stwPlugIn.Client.parser.structure;

import java.util.ArrayList;
import java.util.HashMap;

import yojo.stwPlugIn.Client.Messages.definitions.ShapeConnection;
import yojo.stwPlugIn.Client.Messages.definitions.Shapes.Shape;
import yojo.stwPlugIn.Client.parser.Token;
import yojo.stwPlugIn.Client.parser.XmlParser.ParserException;
import yojo.stwPlugIn.Client.parser.misc.MiscParser;
import yojo.stwPlugIn.Client.util.ResponseListener;

/**
 * parses one connector
 * @author Yojo
 *
 */
public class ConnectionParser extends MiscParser {
	
	
	private final ArrayList<ShapeConnection> connections;
	private final HashMap<String, Shape> shapes;
	
	private Shape shape1;
	private Shape shape2;
	
	private boolean expectFirst;
	
	public ConnectionParser(ArrayList<ShapeConnection> connections, HashMap<String, Shape> shapes) {
		this.connections = connections;
		this.shapes = shapes;
	}

	@Override
	protected void doAction(ResponseListener responseListener, Token t) throws ParserException {
		if(shape1 != null && shape2 != null)
			connections.add(new ShapeConnection(shape1, shape2));
	}

	@Override
	protected void setExpectedValue(Token t) throws ParserException {
		switch(t.value) {
		case "enr1":
		case "name1":
			expectFirst = true;
			break;
		case "enr2":
		case "name2":
			expectFirst = false;
			break;
		default:
			throw new ParserException("expected enr1, enr2, name1 or name2", t);
		}
	}

	@Override
	protected void setValue(Token t) throws ParserException {
		if(expectFirst) {
			shape1 = shapes.get(t.value);
			//if(shape1 == null)
				//throw new ParserException("not a shape enr nor name", t);
		}
		else {
			shape2 = shapes.get(t.value);
			//if(shape2 == null)
				//throw new ParserException("not a shape enr nor name", t);
		}
	}
	
}
