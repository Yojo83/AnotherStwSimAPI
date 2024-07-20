package yojo.stwPlugIn.Client.Messages.definitions;

import java.util.Collections;
import java.util.List;

public class PlattformData {
	public final String name;
	public final boolean stop;
	public final List<String> neighbours;
	
	public PlattformData(String name, boolean stop, List<String> neighbours) {
		this.name = name;
		this.stop = stop;
		this.neighbours = Collections.unmodifiableList(neighbours);
	}
}
