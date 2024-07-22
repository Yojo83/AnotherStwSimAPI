package yojo.stwPlugIn.Client.Messages;

import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;

public class HeatResponse extends ResponseMessage {

	public final long heat;
	
	public HeatResponse(long heat) {
		super(ResponseType.Heat);
		this.heat = heat;
	}

	@Override
	public String toString() {
		return "<hitze hitze='" + heat + "' />";
	}
}
