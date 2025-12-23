package yojo.stwPlugIn.Client.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class DEBUGGER {

	/**
	 * if true, stores all new log lines. 
	 * use printLog to print them
	 */
	public static boolean logMessages = false;
	
	/**
	 * while true, print all new log lines to the console
	 */
	public static boolean debugMode = false;
	
	/**
	 * stores the log lines
	 */
	private static ArrayList<String> log = new ArrayList<>();

	/**
	 * logs the line. If debugMode is true this gets printed to the console
	 * @param string the message to log
	 */
	public static void log(String string) {
		if(logMessages)
			log.add(string);

		if(debugMode)
			System.out.println(string);
	}
	
	/**
	 * writes the enitre saved log (all log statements) to the specified stream
	 * @param out the OutputStream to write the log to
	 */
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
