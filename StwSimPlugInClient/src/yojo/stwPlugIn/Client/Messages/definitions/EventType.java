package yojo.stwPlugIn.Client.Messages.definitions;

/**
 * the event types, the game accepts
 * @author Yojo
 *
 */
public enum EventType {
	/**
	 * this triggers everytime a train leaves a platform
	 */
	Departure("abfahrt"),
	/**
	 * this triggers everytime a train arrives at a platform
	 */
	Arrival("ankunft"),
	/**
	 * this triggers, when the train exits the signal box
	 */
	Exit("ausfahrt"),
	/**
	 * this triggers, when the train enters the signal box
	 */
	Entrance("einfahrt"),
	/**
	 * this is called when a train splitts into two seperate trains
	 */
	Split("fluegeln"),
	/**
	 * this is called when two train combine into one train
	 */
	Combine("kuppeln"),
	/**
	 * this is called, when a train stops at a red signal
	 */
	RedStop("rothalt"),
	/**
	 * this is called, when a train, stopped at a red signal, sees a green signal and starts driing again
	 */
	GotGreen("wurdegruen");
	
	/**
	 * the way this is represented in a xml message
	 */
	public final String forXml;
	
	private EventType(String xml) {
		forXml = xml;
	}
}
