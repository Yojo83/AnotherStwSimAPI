package yojo.stwPlugIn.Client.Messages;

import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;

/**
 * The parsed response for the train details request
 * @author Yojo
 *
 */
public class TrainDetailsResponse extends ResponseMessage {


	/**
	 * the train id of the train
	 */
	public final int trainId;
	/**
	 * the name of the train
	 */
	public final String name;
	/**
	 * the current delay of the train
	 */
	public final int delay;
	/**
	 * true, if the train is currently at a platform, false if it is driving
	 */
	public final boolean atPlattform;
	/**
	 * the current platform of the train, or the next one, if the train is currently driving.
	 * Also can be the last platform, if the train is leaving and has no further platforms.
	 * This includes platform changes from the player
	 */
	public final String plattform;
	/**
	 * the current platform of the train, or the next one, if the train is currently driving.
	 * Also can be the last platform, if the train is leaving and has no further platforms.
	 * This doesn't include platform changes from the player
	 */
	public final String regularPlattform;
	/**
	 * the exit, the train will leave this signal box
	 */
	public final String destination;
	/**
	 * the entry, the train will/has enter/-d the signal box
	 */
	public final String source;
	/**
	 * if this train is visible in this signal box.
	 */
	public final boolean visible;
	/**
	 * the user text added to the train
	 */
	public final String userText;
	/**
	 * the user, that added the current user text
	 */
	public final String userTextSender;
	
	public TrainDetailsResponse(int trainId, String name, int delay, 
			boolean atPlattform, String plattform, String regularPlattform, 
			String destination, String source, 
			boolean visible, String userText, String userTextSender) {
		super(ResponseType.Traindetails);
		this.trainId = trainId;
		this.name = name;
		this.delay = delay;
		this.atPlattform = atPlattform;
		this.plattform = plattform;
		this.regularPlattform = regularPlattform;
		this.destination = destination;
		this.source = source;
		this.visible = visible;
		this.userText = userText;
		this.userTextSender = userTextSender;
		
	}

	@Override
	public String toString() {
		return "<zugdetails usertextsender='" + userTextSender + "' zid='" + trainId + "' verspaetung='" 
				+ delay + "' gleis='" + plattform + "' amgleis='" + atPlattform + "' von='" + source 
				+ "' usertext='" + userText + "' name='" + name + "' nach='" + destination 
				+ "' plangleis='" + regularPlattform + "' sichtbar='" + visible + "' />";
	}
	
}
