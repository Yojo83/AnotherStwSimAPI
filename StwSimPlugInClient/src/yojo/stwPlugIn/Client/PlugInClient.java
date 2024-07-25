package yojo.stwPlugIn.Client;

import java.io.IOException;

import yojo.stwPlugIn.Client.Messages.definitions.EventType;
import yojo.stwPlugIn.Client.util.DEBUGGER;
import yojo.stwPlugIn.Client.util.ResponseListener;
import yojo.stwPlugIn.Client.util.SocketManager;

public class PlugInClient {

	private final String registrationXml;
	
	private ResponseListener listener = new ResponseListener();
	private SocketManager readerThread;
	
	
	
	public PlugInClient(String name, String author, String version, String description) {
		this.registrationXml = "<register name='" + name + "' autor='" + author 
				+ "' version='" + version + "' protokoll='1' text='" + description + "' />\n";
	}
	
	public void connect() throws IOException {
		connect("localhost");
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
		DEBUGGER.log("Debug: closed Socket Manager");
		readerThread = null;
	}
	
	public void setResponseListener(ResponseListener newListener) {
		if(newListener == null)
			throw new NullPointerException();
		
		listener = newListener;
	}
	
	
	public void sendMessageRaw(String msg) throws IOException {
		readerThread.sendMessage(msg);
	}
	
	public void registerEvent(int trainId, EventType type) throws IOException {
		sendMessageRaw("<ereignis zid=\"" + trainId + "\" art=\"" + type.forXml + "\"/>\n");
	}
	
	public void requestHeat() throws IOException {
		sendMessageRaw("<hitze />\n");
	}
	
	public void requestStitz() throws IOException {
		sendMessageRaw("<stitz />\n");
	}
	
	public void requestStructure() throws IOException {
		sendMessageRaw("<wege />\n");
	}
	
	public void requestElement4(int enr) throws IOException {
		sendMessageRaw("<enr2element enr=\"" + enr + "\" />\n");
	}
	
	public void requestEnr4(String element) throws IOException {
		sendMessageRaw("<element2enr element=\"" + element + "\" />\n");
	}
	
	public void requestSetFS(int enr1, int enr2) throws IOException {
		sendMessageRaw("<setfs start=\"" + enr1 + "\" stop=\"" + enr2 + "\" />\n");
	}
	
	public void requestDebugMode(boolean enabled) throws IOException {
		sendMessageRaw("<debug mode=\"" + enabled + "\" />\n");
	}
	
	public void requestTrainSchedule(int trainId) throws IOException {
		sendMessageRaw("<zugfahrplan zid=\"" + trainId + "\" />\n");
	}
	
	public void requestTrainDetails(int trainId) throws IOException {
		sendMessageRaw("<zugdetails  zid=\"" + trainId + "\" />\n");
	}
	
	public void requestTrainList() throws IOException {
		sendMessageRaw("<zugliste />\n");
	}
	
	public void requestPlattformList() throws IOException {
		sendMessageRaw("<bahnsteigliste />\n");
	}
	
	public void requestSystemInfo() throws IOException {
		sendMessageRaw("<anlageninfo />\n");
	}
	
	
}
