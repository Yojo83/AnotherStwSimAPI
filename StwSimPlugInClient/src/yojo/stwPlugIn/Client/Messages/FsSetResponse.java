package yojo.stwPlugIn.Client.Messages;

import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;
import yojo.stwPlugIn.Client.Messages.definitions.ShapeConnection;

public class FsSetResponse extends ResponseMessage{

	public final ShapeConnection signals;
	public final FsSetResult result;
	
	public FsSetResponse(String raw, ShapeConnection signals, FsSetResult result) {
		super(ResponseType.FsSet, raw);
		this.signals = signals;
		this.result = result;
	}
	
	public static enum FsSetResult{
		UNKNOWN,
		BUSY,
		SUCCESS;
	}
}
