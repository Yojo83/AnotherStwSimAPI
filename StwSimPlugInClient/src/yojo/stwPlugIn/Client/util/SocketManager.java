package yojo.stwPlugIn.Client.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.function.Supplier;

public class SocketManager extends Thread {


	private final Socket socket;
	private final BufferedReader reader;
	private final BufferedWriter writer;
	private final Supplier<ResponseListener> listenerSupplier;
	
	public SocketManager(String host, Supplier<ResponseListener> listenerSupplier) throws IOException {
		socket = new Socket(host, 3691);
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		this.listenerSupplier = listenerSupplier;
	}
	
	public void close() throws IOException {
		socket.shutdownInput();
	}
	
	public void sendMessage(String xmlMsg) throws IOException {
		writer.write(xmlMsg);
		writer.flush();
	}
	
	@Override
	public void run() {
		String line;
		try {
			while((line = reader.readLine()) != null) {
				
			}
			socket.close();
		} catch (IOException e) {
			DEBUGGER.log("Error at reading socket line");
		}
		DEBUGGER.log("Stopt reading the Socket");
	}

}
