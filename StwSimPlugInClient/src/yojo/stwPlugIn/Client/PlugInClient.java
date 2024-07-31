package yojo.stwPlugIn.Client;

import java.io.IOException;

import yojo.stwPlugIn.Client.Messages.definitions.EventType;
import yojo.stwPlugIn.Client.util.DEBUGGER;
import yojo.stwPlugIn.Client.util.ResponseListener;
import yojo.stwPlugIn.Client.util.SocketManager;

/**
 * A Interface for a game Connection. 
 * Use the various send methods to send messages to the game, and receive answers through the listener.
 * needs to be connected via the connect method first.
 */
public class PlugInClient {

	/**
	 * the xml message, with witch the client gets registrated to the game
	 */
	private final String registrationXml;
	
	/**
	 * a setable listener, that will receive functioncalls, when a message is read
	 */
	private ResponseListener listener = new ResponseListener();
	/**
	 * The connection to the game, handled by a exchangeable Socket Manager
	 */
	private SocketManager readerThread;
	
	
	/**
	 * Creates a new PlugIn Client.
	 * Use connect() or connect(String host) to connect it to the game
	 * @param name			the name of the PlugIn
	 * @param author		the author of the PlugIn
	 * @param version		the Version of the PlugIn
	 * @param description	a descritption of the PlugIn
	 */
	public PlugInClient(String name, String author, String version, String description) {
		this.registrationXml = "<register name='" + name + "' autor='" + author 
				+ "' version='" + version + "' protokoll='1' text='" + description + "' />\n";
	}
	
	/**
	 * connects this client to a game found under localhost if present
	 * Same as connect("localhost");
	 * @throws IOException
	 */
	public void connect() throws IOException {
		connect("localhost");
	}
	
	/**
	 * connects this client to a game found under the host
	 * @param host
	 * @throws IOException
	 */
	public void connect(String host) throws IOException {
		if(readerThread != null)
			close();
		
		readerThread = new SocketManager(host, () -> listener);
		readerThread.start();
		
		readerThread.sendMessage(registrationXml);
	}
	
	/**
	 * closes the connection to the Game
	 * @throws IOException
	 * @throws NullPointerException if the client isn't connected to a game
	 */
	public void close() throws IOException {
		readerThread.close();
		DEBUGGER.log("Debug: closed Socket Manager");
		readerThread = null;
	}
	
	/**
	 * sets the listener to new messages from the game
	 * removes the old one if present
	 * @param newListener the new listener
	 * @throws NullPointerException if newListener is null
	 */
	public void setResponseListener(ResponseListener newListener) {
		if(newListener == null)
			throw new NullPointerException();
		
		listener = newListener;
	}
	
	/**
	 * sends a message directly to game
	 * only use if you know the PlugIn Interface of the game or want to have fun
	 * @param msg the message that will be send to the client
	 * @throws IOException
	 */
	public void sendMessageRaw(String msg) throws IOException {
		readerThread.sendMessage(msg + "\n");
	}
	
	/**
	 * registers an event to the game
	 * @param trainId the train for the event
	 * @param type the action when the event shall trigger
	 * @throws IOException
	 */
	public void registerEvent(int trainId, EventType type) throws IOException {
		sendMessageRaw("<ereignis zid=\"" + trainId + "\" art=\"" + type.forXml + "\"/>");
	}
	
	/**
	 * request the heat from the game
	 * @throws IOException
	 */
	public void requestHeat() throws IOException {
		sendMessageRaw("<hitze />");
	}
	
	/**
	 * request stitz from the game
	 * @throws IOException
	 */
	public void requestStitz() throws IOException {
		sendMessageRaw("<stitz />");
	}
	
	/**
	 * request the structure of the game
	 * @throws IOException
	 */
	public void requestStructure() throws IOException {
		sendMessageRaw("<wege />");
	}
	
	/**
	 * requests the element name from an enr
	 * @param enr enr of the requesting element
	 * @throws IOException
	 */
	public void requestElement4(int enr) throws IOException {
		sendMessageRaw("<enr2element enr=\"" + enr + "\" />");
	}
	
	/**
	 * requests an enr from an element name
	 * @param element the element name
	 * @throws IOException
	 */
	public void requestEnr4(String element) throws IOException {
		sendMessageRaw("<element2enr element=\"" + element + "\" />");
	}
	
	/**
	 * requests to set an FS between the two Elements represented by their enr
	 * @param enr1
	 * @param enr2
	 * @throws IOException
	 */
	public void requestSetFS(int enr1, int enr2) throws IOException {
		sendMessageRaw("<setfs start=\"" + enr1 + "\" stop=\"" + enr2 + "\" />");
	}
	
	/**
	 * enables or disables the debug mode of the game
	 * @param enabled
	 * @throws IOException
	 */
	public void requestDebugMode(boolean enabled) throws IOException {
		sendMessageRaw("<debug mode=\"" + enabled + "\" />");
	}
	
	/**
	 * request the schedule for a train
	 * @param trainId id of requesting train
	 * @throws IOException
	 */
	public void requestTrainSchedule(int trainId) throws IOException {
		sendMessageRaw("<zugfahrplan zid=\"" + trainId + "\" />");
	}
	
	/**
	 * request detail for a train
	 * @param trainId id of trequesting train
	 * @throws IOException
	 */
	public void requestTrainDetails(int trainId) throws IOException {
		sendMessageRaw("<zugdetails  zid=\"" + trainId + "\" />");
	}
	
	/**
	 * request the train list
	 * @throws IOException
	 */
	public void requestTrainList() throws IOException {
		sendMessageRaw("<zugliste />");
	}
	
	/**
	 * request the plattformlist
	 * @throws IOException
	 */
	public void requestPlattformList() throws IOException {
		sendMessageRaw("<bahnsteigliste />");
	}
	
	/**
	 * requests the systeminfo
	 * @throws IOException
	 */
	public void requestSystemInfo() throws IOException {
		sendMessageRaw("<anlageninfo />");
	}
	
	/**
	 * requests the time in the simulator
	 * @throws IOException
	 */
	public void requestSimTime() throws IOException{
		sendMessageRaw("<simzeit sender='0' />");
	}
	
	
}
