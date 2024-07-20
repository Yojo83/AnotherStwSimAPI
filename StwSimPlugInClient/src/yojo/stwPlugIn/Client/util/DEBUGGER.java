package yojo.stwPlugIn.Client.util;

import java.util.ArrayList;

public class DEBUGGER {

	public static boolean debugMode = false;
	
	private static ArrayList<String> log = new ArrayList<>();

	public static void log(String string) {
		log.add(string);

		if(debugMode)
			System.out.println(string);
	}
	
	public static void printLog() {
		for(String s : log) {
			System.out.println(s);
		}
	}
	
}
