package yojo.stwPlugIn.Client.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import yojo.stwPlugIn.Client.PlugInClient;
import yojo.stwPlugIn.Client.Messages.definitions.EventType;
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
		
		client.setResponseListener(new TestListener());
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line;
		try {
			while((line = reader.readLine()) != null) {
				if(line.equals("close")) {
					break;
				}
				sendCommand(client, line);
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
		System.out.println("Main Thread ended");
	}

	
	private static void sendCommand(PlugInClient client, String line) throws IOException {
		String[] args = line.split(" ");
		switch(args[0]) {
		case "heat":
			client.requestHeat();
			break;
		case "stitz":
			client.requestStitz();
			break;
		case "wege":
			client.requestStructure();
			break;
		case "trains":
			client.requestTrainList();
			break;
		case "plattforms"://redo
			client.requestPlattformList();
			break;
		case "system":
			client.requestSystemInfo();
			break;
		case "details":
			if(args.length == 2) {
				try {
					client.requestTrainDetails(Integer.parseInt(args[1]));
				} catch (NumberFormatException e) {
				}
			}
			break;
		case "schedule":
			if(args.length == 2) {
				try {
					client.requestTrainSchedule(Integer.parseInt(args[1]));
				} catch (NumberFormatException e) {
				}
			}
			break;
		case "debug":
			if(args.length == 2)
				client.requestDebugMode(Boolean.parseBoolean(args[1]));
			break;
		case "fs":
			if(args.length == 3) {
				try {
					client.requestSetFS(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
				} catch (NumberFormatException e) {
				}
			}
			break;
		case "element4":
			if(args.length == 2) {
				try {
					client.requestElement4(Integer.parseInt(args[1]));
				} catch (NumberFormatException e) {
				}
			}
			break;
		case "enr4":
			if(args.length == 2)
				client.requestEnr4(args[1]);
			break;
		case "event":
			if(args.length == 3) {
				try {
					int trainId = Integer.parseInt(args[1]);
					switch(args[2]) {
					case "a": client.registerEvent(trainId, EventType.Arrival); break;
					case "d": client.registerEvent(trainId, EventType.Departure); break;
					case "en": client.registerEvent(trainId, EventType.Entrance); break;
					case "ex": client.registerEvent(trainId, EventType.Exit); break;
					case "s": client.registerEvent(trainId, EventType.Split); break;
					case "c": client.registerEvent(trainId, EventType.Combine); break;
					case "r": client.registerEvent(trainId, EventType.RedStop); break;
					case "g": client.registerEvent(trainId, EventType.GotGreen); break;
					}
				} catch (NumberFormatException e) {
				}
			}
			break;
		case "time":
			client.requestSimTime();
			break;
		default:
			client.sendMessageRaw(line + "\n");	
		}
	}
}
