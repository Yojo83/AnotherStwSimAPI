package yojo.stwPlugIn.Client.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.function.Supplier;

import yojo.stwPlugIn.Client.parser.XmlParser;

public class SocketManager extends Thread {


	/**
	 * the TCP Socket connected to the game
	 */
	private final Socket socket;
	/**
	 * a BufferedReader from the InputStream of the Socket, used to read messages from the game
	 */
	private final BufferedReader reader;
	/**
	 * a BufferedWriter from the OutputStream of the Socket. Used to send messages to the game
	 */
	private final BufferedWriter writer;
	/**
	 * a Supplier method for getting the current listener
	 */
	private final Supplier<ResponseListener> listenerSupplier;
	/**
	 * a Parser to parse the raw xml messages from the game and (if finished) execute on a listener
	 */
	private final XmlParser parser;
	
	/**
	 * creates a new SocketManager. Also directly connects to the host
	 * @param host the url, the tcp connection searches the game
	 * @param listenerSupplier a supplier method for getting the current listener
	 * @throws IOException propagated from the raw-socket
	 */
	public SocketManager(String host, Supplier<ResponseListener> listenerSupplier) throws IOException {
		socket = new Socket(host, 3691);
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		this.listenerSupplier = listenerSupplier;
		this.parser = new XmlParser();
	}
	
	/**
	 * closes the socket
	 * @throws IOException
	 */
	public void close() throws IOException {
		socket.shutdownInput();
		socket.close();
	}
	
	/**
	 * sends a message and logs it
	 * @param xmlMsg the message send in raw xml
	 * @throws IOException
	 */
	public void sendMessage(String xmlMsg) throws IOException {
		writer.write(xmlMsg);
		writer.flush();
		DEBUGGER.log("send Message: " + xmlMsg);
	}
	
	/**
	 * reads the socket and handles the messages through the current listener
	 */
	@Override
	public void run() {
		String line;
		try {
			while((line = reader.readLine()) != null) {
				DEBUGGER.log("received Message: " + line + "\n");
				parser.handleLine(line, listenerSupplier.get());
			}
			socket.close();
		} catch (IOException e) {
			DEBUGGER.log("Error at reading socket line");
		}
		listenerSupplier.get().onClose();
		DEBUGGER.log("Stopt reading the Socket");
	}

}
