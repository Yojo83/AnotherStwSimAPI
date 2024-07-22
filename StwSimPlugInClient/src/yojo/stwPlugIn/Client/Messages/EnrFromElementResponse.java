package yojo.stwPlugIn.Client.Messages;

import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;

public class EnrFromElementResponse extends ResponseMessage {

	public final int enr;
	public final String name;
	
	public EnrFromElementResponse(int enr, String name) {
		super(ResponseType.EnrFromElement);
		this.enr = enr;
		this.name = name;
	}

	@Override
	public String toString() {
		return "<enr4 enr='" + enr + "' element='" + name + "' />";
	}
}
