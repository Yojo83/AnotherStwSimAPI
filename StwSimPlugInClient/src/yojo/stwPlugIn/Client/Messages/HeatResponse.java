package yojo.stwPlugIn.Client.Messages;

import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;

/**
 * The parsed response for the heat request
 * @author Yojo
 *
 */
public class HeatResponse extends ResponseMessage {

	/**
	 * the current heat of the game
	 */
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
