package yojo.stwPlugIn.Client.Messages;

import java.util.Collections;
import java.util.List;

import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;
import yojo.stwPlugIn.Client.Messages.definitions.ShapeConnection;
import yojo.stwPlugIn.Client.Messages.definitions.Shapes;

public class StructureResponse extends ResponseMessage {

	public final Shapes shapes;
	
	public final List<ShapeConnection> connections;
	
	public StructureResponse(List<ShapeConnection> connections, Shapes shapes) {
		super(ResponseType.Structure);
		this.shapes = shapes;
		this.connections = Collections.unmodifiableList(connections);
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("<wege >");
		
		shapes.foreach(shape -> {
			str.append(shape.toString());
		});
		
		for(ShapeConnection c : connections) {
			str.append(c.toString());
		}
		
		str.append("</wege>");
		return str.toString();
	}
}
