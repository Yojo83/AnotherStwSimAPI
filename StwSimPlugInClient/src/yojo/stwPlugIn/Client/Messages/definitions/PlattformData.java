package yojo.stwPlugIn.Client.Messages.definitions;

import java.util.Collections;
import java.util.List;

/**
 * An Entry in the plattform list, representing one platform of the signal box
 * @author Yojo
 *
 */
public class PlattformData {
	
	/**
	 * the name of the platform
	 */
	public final String name;
	/**
	 * true, if its just a stop and false if it is a platform at a train station
	 */
	public final boolean stop;
	/**
	 * an unmodifiable list of neighbouring platforms
	 */
	public final List<String> neighbours;
	
	public PlattformData(String name, boolean stop, List<String> neighbours) {
		this.name = name;
		this.stop = stop;
		this.neighbours = Collections.unmodifiableList(neighbours);
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("<bahnsteig name='" + name + "' haltepunkt='" + stop + "' >\n");
		
		for(String n : neighbours) {
			str.append("<n name='" + n + "' />\n");
		}
		
		str.append("</bahnsteig>");
		return str.toString();
	}
	
}
