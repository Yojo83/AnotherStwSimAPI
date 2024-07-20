package yojo.stwPlugIn.Client.Messages;

import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;

public class SysteminfoResponse extends ResponseMessage {

	public final int systemId;
	public final String build;
	public final String name;
	public final boolean online;
	public final String region;
	
	
	public SysteminfoResponse(String raw, int systemId, String region, boolean online, String name, String build) {
		super(ResponseType.Systeminfo, raw);
		this.systemId = systemId;
		this.build = build;
		this.name = name;
		this.online = online;
		this.region = region;
	}

}
