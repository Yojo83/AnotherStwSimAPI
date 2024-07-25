package yojo.stwPlugIn.Client.Messages.definitions;

public enum EventType {
	Departure("abfahrt"),
	Arrival("ankunft"),
	Exit("ausfahrt"),
	Entrance("einfahrt"),
	Split("fluegeln"),
	Combine("kuppeln"),
	RedStop("rothalt"),
	GotGreen("wurdegruen");
	
	public final String forXml;
	
	private EventType(String xml) {
		forXml = xml;
	}
}
