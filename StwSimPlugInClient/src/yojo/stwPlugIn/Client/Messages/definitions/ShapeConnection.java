package yojo.stwPlugIn.Client.Messages.definitions;

import yojo.stwPlugIn.Client.Messages.definitions.Shapes.Shape;

public class ShapeConnection {

	
	public final Shape shape1;
	public final Shape shape2;
	
	public ShapeConnection(Shape shape1, Shape shape2) {
		this.shape1 = shape1;
		this.shape2 = shape2;
	}
	
	@Override
	public String toString() {
		return "<connector shape1='" + shape1 + "' shape2='" + shape2 + "' />";
	}
}
