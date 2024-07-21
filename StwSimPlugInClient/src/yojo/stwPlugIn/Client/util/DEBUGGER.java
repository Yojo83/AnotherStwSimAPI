package yojo.stwPlugIn.Client.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class DEBUGGER {

	public static boolean debugMode = false;
	
	private static ArrayList<String> log = new ArrayList<>();

	public static void log(String string) {
		log.add(string);

		if(debugMode)
			System.out.println(string);
	}
	
	public static void printLog(OutputStream out) {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
		try {
			for(String s : log) {
				writer.write(s);
				writer.flush();
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
