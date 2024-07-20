package yojo.stwPlugIn.Client;

import java.io.IOException;

import yojo.stwPlugIn.Client.util.ResponseListener;
import yojo.stwPlugIn.Client.util.SocketManager;

public class PlugInClient {

	private final String registrationXml;
	
	private ResponseListener listener = new ResponseListener();
	private SocketManager readerThread;
	
	
	
	public PlugInClient(String name, String author, String version, String description) {
		this.registrationXml = "<register name='" + name + "' autor='" + author 
				+ "' version='" + version + "' protokoll='1' text='" + description + "' />";
	}
	
	
	public void connect(String host) throws IOException {
		if(readerThread != null)
			close();
		
		readerThread = new SocketManager(host, () -> listener);
		readerThread.start();
		
		readerThread.sendMessage(registrationXml);
	}
	
	public void close() throws IOException {
		readerThread.close();
		
		readerThread = null;
	}
	
	public void setResponseListener(ResponseListener newListener) {
		if(newListener == null)
			throw new NullPointerException();
		
		listener = newListener;
	}
	
}
