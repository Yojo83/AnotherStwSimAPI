package yojo.stwPlugIn.Client.Messages;

import java.util.Collections;
import java.util.List;

import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;
import yojo.stwPlugIn.Client.Messages.definitions.Shape;
import yojo.stwPlugIn.Client.Messages.definitions.ShapeConnection;

public class StructureResponse extends ResponseMessage {

	public final List<Shape> shapes;
	public final List<ShapeConnection> connections;
	
	public StructureResponse(List<Shape> shapes, List<ShapeConnection> connections) {
		super(ResponseType.Structure);
		this.shapes = Collections.unmodifiableList(shapes);
		this.connections = Collections.unmodifiableList(connections);
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("<wege >");
		
		for(Shape s : shapes) {
			str.append(s.toString());
		}
		for(ShapeConnection c : connections) {
			str.append(c.toString());
		}
		
		str.append("</wege>");
		return str.toString();
	}
}
