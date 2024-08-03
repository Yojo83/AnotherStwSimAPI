package yojo.stwPlugIn.Client.Messages;

import java.util.Collections;
import java.util.List;

import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;
import yojo.stwPlugIn.Client.Messages.definitions.ShapeConnection;
import yojo.stwPlugIn.Client.Messages.definitions.Shapes;

/**
 * The parsed response for the structure request containing the shapes and connections
 * @author Yojo
 *
 */
public class StructureResponse extends ResponseMessage {

	/**
	 * An Object representing all shapes of the signal box
	 */
	public final Shapes shapes;
	
	/**
	 * An unmodifiable list with the connections between the Shapes
	 */
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
			str.append(shape.toString() + "\n");
		});
		
		for(ShapeConnection c : connections) {
			str.append(c.toString() + "\n");
		}
		
		str.append("</wege>");
		return str.toString();
	}
}
