package yojo.stwPlugIn.Client.Messages;

import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;

/**
 * The parsed response for the stitz reques
 * @author Yojo
 *
 */
public class StitzResponse extends ResponseMessage {

	/**
	 * the regional Tel
	 */
	public final String regionalTel;
	/**
	 * the common Tel
	 */
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
