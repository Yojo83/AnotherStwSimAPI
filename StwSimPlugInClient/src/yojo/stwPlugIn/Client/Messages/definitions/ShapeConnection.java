package yojo.stwPlugIn.Client.Messages.definitions;

public class ShapeConnection {

	
	public final int enr1;
	public final int enr2;
	
	public ShapeConnection(int enr1, int enr2) {
		this.enr1 = enr1;
		this.enr2 = enr2;
	}
	
	@Override
	public String toString() {
		return "<connector enr1='" + enr1 + "' enr2='" + enr2 + "' />";
	}
}
