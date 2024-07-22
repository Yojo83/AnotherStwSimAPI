package yojo.stwPlugIn.Client.Messages;

import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;

public class TrainDetailsResponse extends ResponseMessage {


	public final int trainId;
	public final String name;
	public final int delay;
	public final boolean atPlattform;
	public final String plattform;
	public final String regularPlattform;
	public final String destination;
	public final String source;
	public final boolean visible;
	public final String userText;
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
