package yojo.stwPlugIn.Client.Messages;

import java.util.Collections;
import java.util.List;

import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;
import yojo.stwPlugIn.Client.Messages.definitions.Shape;
import yojo.stwPlugIn.Client.Messages.definitions.ShapeConnection;

public class StructureResponse extends ResponseMessage {

	public final List<Shape> shapes;
	public final List<ShapeConnection> connections;
	
	public StructureResponse(String raw, List<Shape> shapes, List<ShapeConnection> connections) {
		super(ResponseType.Structure, raw);
		this.shapes = Collections.unmodifiableList(shapes);
		this.connections = Collections.unmodifiableList(connections);
	}
}
