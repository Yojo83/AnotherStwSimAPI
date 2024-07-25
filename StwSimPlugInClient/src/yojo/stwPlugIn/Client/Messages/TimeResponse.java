package yojo.stwPlugIn.Client.Messages;

import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;

public class TimeResponse extends ResponseMessage {

	public final long time;
	
	public TimeResponse(long time) {
		super(ResponseType.Time);
		this.time = time;
	}

	@Override
	public String toString() {
		return "<simzeit zeit='" + time + "' />";
	}

}
