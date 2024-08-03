package yojo.stwPlugIn.Client.Messages;

import yojo.stwPlugIn.Client.Messages.definitions.EventType;
import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;

/**
 * The parsed message for an event
 * @author Yojo
 *
 */
public class EventResponse extends ResponseMessage{

	/**
	 * the type of event, thats triggered
	 */
	public final EventType type;
	/**
	 * the train id id of the train, that triggered the event.
	 */
	public final int trainId;
	/**
	 * the name of the train, that triggered the event.
	 */
	public final String name;
	/**
	 * if the train, that triggered the event, is at a platform 
	 */
	public final boolean atPlattform;
	/**
	 * the delay of the train, that triggered the event.
	 */
	public final int delay;
	/**
	 * the actual platform of the train, that triggered the event.
	 */
	public final String plattform;
	/**
	 * the planned platform of the train, that triggered the event.
	 */
	public final String regularPlattform;
	/**
	 * where the train, that triggered the event, exits the signal box
	 */
	public final String destination;
	/**
	 * where the train, that triggered the event, entrys the signal box
	 */
	public final String source;
	/**
	 * if the train, that triggered the event, is currently in the signal box.
	 */
	public final boolean visible;
	
	public EventResponse(EventType type, boolean visible, int trainId, String source, String regularPlattform, String plattform, String name, String destination, boolean atPlattform, int delay) {
		super(ResponseType.Event);
		this.type = type;
		this.trainId = trainId;
		this.name = name;
		this.atPlattform = atPlattform;
		this.delay = delay;
		this.plattform = plattform;
		this.regularPlattform = regularPlattform;
		this.destination = destination;
		this.source = source;
		this.visible = visible;
	}

	@Override
	public String toString() {
		return "<ereignis zid='" + trainId + "' art='" + type.name() + "' name='" + name
				+ "' verspaetung='" + delay + "' gleis='" + plattform + "' plangleis='" 
				+ regularPlattform + "' von='" + source + "' nach='" + destination
				+ "' sichtbar='" + visible + "' amgleis='" + atPlattform + "' />";
	}
}
