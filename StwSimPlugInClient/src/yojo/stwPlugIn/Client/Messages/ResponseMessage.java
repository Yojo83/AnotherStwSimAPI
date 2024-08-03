package yojo.stwPlugIn.Client.Messages;

import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;

/**
 * An abstract class for response messages, this can be any response messagefrom the game.
 * The toString() Method returns a not exact but close to the original xml version of the message
 * @author Yojo
 *
 */
public abstract class ResponseMessage {

	/**
	 * the type of the response message.
	 * the message can be casted based on this into the concrete message
	 */
	public final ResponseType type;
	
	public ResponseMessage(ResponseType type) {
		this.type = type;
	}
	
	
	@Override
	public abstract String toString();
	
	
	
}
