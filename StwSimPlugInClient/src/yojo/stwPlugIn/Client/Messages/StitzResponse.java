package yojo.stwPlugIn.Client.Messages;

import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;

public class StitzResponse extends ResponseMessage {

	public final String regionalTel;
	public final String commonTel;
	
	public StitzResponse(String regionalTel, String commonTel) {
		super(ResponseType.Stitz);
		this.regionalTel = regionalTel;
		this.commonTel = commonTel;
	}

	@Override
	public String toString() {
		return "<stitz region='" + regionalTel + "' allgemein='" + commonTel + "' />";
	}
	
}
