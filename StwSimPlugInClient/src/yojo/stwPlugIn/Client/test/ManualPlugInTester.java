package yojo.stwPlugIn.Client.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import yojo.stwPlugIn.Client.PlugInClient;
import yojo.stwPlugIn.Client.util.DEBUGGER;

public class ManualPlugInTester {
	
	
	public static void main(String[] args) {
		DEBUGGER.debugMode = true;
		PlugInClient client = new PlugInClient("ManualTester", "Yojo", "1.0", "lets a person test the xml connection");
		try {
			client.connect();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Connection Error");
		}
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line;
		try {
			while((line = reader.readLine()) != null) {
				if(line.equals("close")) {
					break;
				}
				client.sendMessageRaw(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Reading/sending Error");
		}
		
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("closing Error");
		}
	}
}
