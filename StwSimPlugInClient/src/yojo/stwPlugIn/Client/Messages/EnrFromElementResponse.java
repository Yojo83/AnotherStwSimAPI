package yojo.stwPlugIn.Client.Messages;

import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;

public class EnrFromElementResponse extends ResponseMessage {

	public final int enr;
	public final String name;
	
	public EnrFromElementResponse(String raw, int enr, String name) {
		super(ResponseType.EnrFromElement, raw);
		this.enr = enr;
		this.name = name;
	}
}
