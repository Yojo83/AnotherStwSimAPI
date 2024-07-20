package yojo.stwPlugIn.Client.Messages;

import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;

public class StitzResponse extends ResponseMessage {

	public final String regionalTel;
	public final String commonTel;
	
	public StitzResponse(String raw, String regionalTel, String commonTel) {
		super(ResponseType.Stitz, raw);
		this.regionalTel = regionalTel;
		this.commonTel = commonTel;
	}
	
}
