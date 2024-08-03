package yojo.stwPlugIn.Client.Messages;

import java.util.Collections;
import java.util.Map;

import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;

/**
 * The parsed response for the train list request containing all trains with train id and name
 * @author Yojo
 *
 */
public class TrainListResponse extends ResponseMessage {
	
	/**
	 * An unmodifiable Map of all trains. The train id is mapped onto the name 
	 */
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
