package yojo.stwPlugIn.Client.Messages;

import java.util.Map;

import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;

public class TrainListResponse extends ResponseMessage {
	
	public final Map<Integer, String> trains;
	
	public TrainListResponse(String raw, Map<Integer, String> trains) {
		super(ResponseType.Trainlist, raw);
		this.trains = trains;
	}
}
