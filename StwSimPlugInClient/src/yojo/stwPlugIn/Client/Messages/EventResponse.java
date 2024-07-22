package yojo.stwPlugIn.Client.Messages;

import yojo.stwPlugIn.Client.Messages.definitions.EventType;
import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;

public class EventResponse extends ResponseMessage{

	public final EventType type;
	public final int trainId;
	public final String name;
	public final boolean atPlattform;
	public final int delay;
	public final String plattform;
	public final String regularPlattform;
	public final String destination;
	public final String source;
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
