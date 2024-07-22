package yojo.stwPlugIn.Client.Messages.definitions;

public class Shape {

	
	public final int enr;
	public final String name;
	public final ShapeType type;
	
	public Shape(ShapeType type, String name, int enr) {
		this.enr = enr;
		this.name = name;
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "<shape name='" + name + "' enr='" + enr + "' type='" + type.name() + "' />";
	}
}
