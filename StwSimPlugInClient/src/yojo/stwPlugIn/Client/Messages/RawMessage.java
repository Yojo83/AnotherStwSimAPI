package yojo.stwPlugIn.Client.Messages;

import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;

/**
 * An unparsed message from the game. This is used, if there are errors during parsing an xml message
 * @author Yojo
 *
 */
public class RawMessage extends ResponseMessage {

	/**
	 * the raw xml message
	 */
	public final String raw;
	
	public RawMessage(String raw) {
		super(ResponseType.Raw);
		this.raw = raw;
	}

	@Override
	public String toString() {
		return raw;
	}

}
