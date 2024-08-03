package yojo.stwPlugIn.Client.Messages;

import yojo.stwPlugIn.Client.Messages.definitions.FsSetResult;
import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;

/**
 * The parsed message for an fsset result. This can be tricky due to inconsistency in the game interface:
 * If the fs is not possible this message with UNKNOWN is send. 
 * If the fs memory is already filled this message with BUS is send.
 * If the fs will be set in the fs memory, this message will never be send.
 * If the fs can be set directly this message is send twice with SUCCESS before and after the switches are set.  
 * @author Yojo
 *
 */
public class FsSetResponse extends ResponseMessage{

	/**
	 * the start of the requested fs
	 */
	public final int enr1;
	/**
	 * the end of the requested fs
	 */
	public final int enr2;
	/**
	 * the result given for the action
	 */
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
