package yojo.stwPlugIn.Client.Messages;

import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;

/**
 * The parsed response for the system info request
 * @author Yojo
 *
 */
public class SysteminfoResponse extends ResponseMessage {

	/**
	 * the id of the signal box
	 */
	public final int systemId;
	/**
	 * the build id of the signal box
	 */
	public final String build;
	/**
	 * the name of the signal box
	 */
	public final String name;
	/**
	 * true if the game is in one of the online instances, false if its a local game
	 */
	public final boolean online;
	/**
	 * the region of the signal box
	 */
	public final String region;
	
	
	public SysteminfoResponse(int systemId, String region, boolean online, String name, String build) {
		super(ResponseType.Systeminfo);
		this.systemId = systemId;
		this.build = build;
		this.name = name;
		this.online = online;
		this.region = region;
	}


	@Override
	public String toString() {
		return "<anlageninfo simbuild='" + build + "' name='" + name + "' online='" + online
				+ "' region='" + region + "' aid='" + systemId + "' />";
	}

}
