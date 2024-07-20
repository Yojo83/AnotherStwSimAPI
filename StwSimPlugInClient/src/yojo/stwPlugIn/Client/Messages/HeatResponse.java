package yojo.stwPlugIn.Client.Messages;

import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;

public class HeatResponse extends ResponseMessage {

	public final long heat;
	
	public HeatResponse(String raw, long heat) {
		super(ResponseType.Heat, raw);
		this.heat = heat;
	}
}
