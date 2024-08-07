package yojo.stwPlugIn.Client.Messages;

import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;

/**
 * The parsed response for the enr4 request
 * This Feature is not finished in the sim and may cause troubles.
 * @author Yojo
 *
 */
public class EnrFromElementResponse extends ResponseMessage {

	/**
	 * the enr for that element
	 */
	public final int enr;
	/**
	 * the element name from the request
	 */
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
