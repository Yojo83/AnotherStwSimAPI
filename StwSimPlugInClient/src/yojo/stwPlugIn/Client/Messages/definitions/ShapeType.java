package yojo.stwPlugIn.Client.Messages.definitions;

public enum ShapeType {
	
	Signal(2),
	SwitchDown(3),
	SwitchUp(4),
	Plattform(5),
	Entry(6),
	Exit(7),
	Stop(12);
	
	public final int typeId;
	
	private ShapeType(int id) {
		typeId = id;
	}
}
