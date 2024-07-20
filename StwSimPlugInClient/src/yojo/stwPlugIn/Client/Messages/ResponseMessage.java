package yojo.stwPlugIn.Client.Messages;

import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;

public class ResponseMessage {

	
	public final ResponseType type;
	public final String raw;
	
	public ResponseMessage(ResponseType type, String raw) {
		this.type = type;
		this.raw = raw;
	}
	
	
	@Override
	public String toString() {
		return raw;
	}
	
	
}
