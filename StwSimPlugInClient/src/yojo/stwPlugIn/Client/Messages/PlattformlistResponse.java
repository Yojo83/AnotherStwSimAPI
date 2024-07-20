package yojo.stwPlugIn.Client.Messages;

import java.util.Collections;
import java.util.List;

import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;
import yojo.stwPlugIn.Client.Messages.definitions.PlattformData;

public class PlattformlistResponse extends ResponseMessage{
	
	
	public final List<PlattformData> plattforms;
	
	public PlattformlistResponse(String raw, List<PlattformData> plattforms) {
		super(ResponseType.Plattformlist, raw);
		this.plattforms = Collections.unmodifiableList(plattforms);
	}
	
}
