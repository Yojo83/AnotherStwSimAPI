package yojo.stwPlugIn.Client.Messages;

import java.util.Collections;
import java.util.Map;

import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;

public class TrainListResponse extends ResponseMessage {
	
	public final Map<Integer, String> trains;
	
	public TrainListResponse(Map<Integer, String> trains) {
		super(ResponseType.Trainlist);
		this.trains = Collections.unmodifiableMap(trains);
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("<zugliste >\n");
		
		trains.forEach((tid, name) -> {
			str.append("<zug zid='" + tid + "' name='" + name + "' />\n");
		});
		
		str.append("</zugliste>");
		return str.toString();
	}
}
