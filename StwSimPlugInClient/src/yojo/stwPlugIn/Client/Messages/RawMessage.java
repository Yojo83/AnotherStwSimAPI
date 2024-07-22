package yojo.stwPlugIn.Client.Messages;

import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;

public class RawMessage extends ResponseMessage {

	public final String raw;
	
	public RawMessage(String raw) {
		super(ResponseType.Raw);
		this.raw = raw;
	}

	@Override
	public String toString() {
		return raw;
	}

}
