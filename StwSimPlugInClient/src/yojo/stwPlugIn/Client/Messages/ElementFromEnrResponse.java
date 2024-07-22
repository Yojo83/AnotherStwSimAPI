package yojo.stwPlugIn.Client.Messages;

import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;

public class ElementFromEnrResponse extends ResponseMessage {
	
	public final int enr;
	public final String name;
	
	public ElementFromEnrResponse(String name, int enr) {
		super(ResponseType.ElementFromEnr);
		this.enr = enr;
		this.name = name;
	}

	@Override
	public String toString() {
		return "<element4 enr='" + enr + "' element='" + name + "' />";
	}
}
