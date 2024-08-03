package yojo.stwPlugIn.Client.Messages;

import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;

/**
 * The parsed response for the time request
 * @author Yojo
 *
 */
public class TimeResponse extends ResponseMessage {

	/**
	 * the current time in millis after 00:00
	 */
	public final long time;
	
	public TimeResponse(long time) {
		super(ResponseType.Time);
		this.time = time;
	}

	@Override
	public String toString() {
		return "<simzeit zeit='" + time + "' />";
	}

}
