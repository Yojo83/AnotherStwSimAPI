package yojo.stwPlugIn.Client.Messages;

import yojo.stwPlugIn.Client.Messages.definitions.FsSetResult;
import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;
import yojo.stwPlugIn.Client.Messages.definitions.ShapeConnection;

public class FsSetResponse extends ResponseMessage{

	public final ShapeConnection signals;
	public final FsSetResult result;
	
	public FsSetResponse(ShapeConnection signals, FsSetResult result) {
		super(ResponseType.FsSet);
		this.signals = signals;
		this.result = result;
	}


	@Override
	public String toString() {
		return "<fsset result='" + result.name() + "' stop='" + signals.enr1 + "' start='" + signals.enr2 + "' />";
	}
	
	
}
