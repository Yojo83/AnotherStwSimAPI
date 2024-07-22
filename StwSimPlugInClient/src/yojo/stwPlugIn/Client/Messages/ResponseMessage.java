package yojo.stwPlugIn.Client.Messages;

import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;

public abstract class ResponseMessage {

	
	public final ResponseType type;
	
	public ResponseMessage(ResponseType type) {
		this.type = type;
	}
	
	
	@Override
	public abstract String toString();
	
	
	
}
