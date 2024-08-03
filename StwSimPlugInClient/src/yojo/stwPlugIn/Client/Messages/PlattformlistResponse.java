package yojo.stwPlugIn.Client.Messages;

import java.util.Collections;
import java.util.List;

import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;
import yojo.stwPlugIn.Client.Messages.definitions.PlattformData;

/**
 * The parsed response for the platform list request containing all platforms
 * @author Yojo
 *
 */
public class PlattformlistResponse extends ResponseMessage{
	
	/**
	 * An unmodifiable list of all plattforms
	 */
	public final List<PlattformData> plattforms;
	
	public PlattformlistResponse(List<PlattformData> plattforms) {
		super(ResponseType.Plattformlist);
		this.plattforms = Collections.unmodifiableList(plattforms);
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("<bahnsteigliste >\n");
		
		for(PlattformData p : plattforms) {
			str.append(p.toString() + "\n");
		}

		str.append("</bahnsteigliste>");
		return str.toString();
	}
	
}
