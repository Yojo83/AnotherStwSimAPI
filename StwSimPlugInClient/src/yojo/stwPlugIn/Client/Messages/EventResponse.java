package yojo.stwPlugIn.Client.Messages;

import yojo.stwPlugIn.Client.Messages.definitions.EventType;
import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;

public class EventResponse extends ResponseMessage{

	public final EventType type;
	public final TrainDetailsResponse details;
	
	public EventResponse(EventType type, TrainDetailsResponse details) {
		super(ResponseType.Event);
		this.type = type;
		this.details = details;
	}

	@Override
	public String toString() {
		return "<ereignis zid='" + details.trainId + "' art='" + type.name() + "' name='" + details.name
				+ "' verspaetung='" + details.delay + "' gleis='" + details.plattform + "' plangleis='" 
				+ details.regularPlattform + "' von='" + details.source + "' nach='" + details.destination
				+ "' sichtbar='" + details.visible + "' amgleis='" + details.atPlattform + "' />";
	}
}
