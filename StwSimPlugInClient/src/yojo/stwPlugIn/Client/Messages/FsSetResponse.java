package yojo.stwPlugIn.Client.Messages;

import yojo.stwPlugIn.Client.Messages.definitions.FsSetResult;
import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;

public class FsSetResponse extends ResponseMessage{

	public final int enr1;
	public final int enr2;
	public final FsSetResult result;
	
	public FsSetResponse(int enr1, int enr2, FsSetResult result) {
		super(ResponseType.FsSet);
		this.enr1 = enr1;
		this.enr2 = enr2;
		this.result = result;
	}


	@Override
	public String toString() {
		return "<fsset result='" + result.name() + "' stop='" + enr1 + "' start='" + enr2 + "' />";
	}
	
	
}
