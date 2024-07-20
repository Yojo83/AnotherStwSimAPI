package yojo.stwPlugIn.Client.Messages;

import yojo.stwPlugIn.Client.Messages.definitions.EventType;
import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;

public class EventResponse extends ResponseMessage{

	public final int trainId;
	public final EventType type;
	public final TrainDetailsResponse details;
	
	public EventResponse(String raw, int trainId, EventType type, TrainDetailsResponse details) {
		super(ResponseType.Event, raw);
		this.trainId = trainId;
		this.type = type;
		this.details = details;
	}
}
