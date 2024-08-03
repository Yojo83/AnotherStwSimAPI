package yojo.stwPlugIn.Client.Messages;

import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;

/**
 * The parsed response for the element43 request
 * @author Yojo
 *
 */
public class ElementFromEnrResponse extends ResponseMessage {
	
	/**
	 * the enr from the request
	 */
	public final int enr;
	/**
	 * the element name
	 */
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
