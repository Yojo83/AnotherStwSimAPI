package yojo.stwPlugIn.Client.Messages;

import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;

public class ElementFromEnrResponse extends ResponseMessage {
	
	public final int enr;
	public final String name;
	
	public ElementFromEnrResponse(String raw, String name, int enr) {
		super(ResponseType.ElementFromEnr, raw);
		this.enr = enr;
		this.name = name;
	}
}
