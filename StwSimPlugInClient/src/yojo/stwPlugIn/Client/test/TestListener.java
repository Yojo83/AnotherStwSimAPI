package yojo.stwPlugIn.Client.test;

import yojo.stwPlugIn.Client.Messages.ResponseMessage;
import yojo.stwPlugIn.Client.util.ResponseListener;

public class TestListener extends ResponseListener {
	
	@Override
	public void onClose() {
		System.out.println("Test: closed");
	}
	
	@Override
	public void unhandeledMessage(ResponseMessage msg) {
		System.out.println("Test: Msg " + msg.toString() + " of type " + msg.type.name());
	}
}
