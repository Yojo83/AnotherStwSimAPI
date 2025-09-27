package yojo.stwPlugIn.Client.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import yojo.stwPlugIn.Client.Messages.definitions.FlagData;
import yojo.stwPlugIn.Client.parser.Token;
import yojo.stwPlugIn.Client.parser.XmlParser.ParserException;
import yojo.stwPlugIn.Client.parser.schedule.FlagParser;

public class FlagTester {

	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line;
		try {
			while((line = reader.readLine()) != null) {
				if(line.equals("close")) {
					break;
				}
				/**
				try {
					System.out.println(customString(FlagParser.parse(new Token(line))));
				} catch (ParserException e) {
					System.out.println(e.msg);
				}
				**/
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Reading/sending Error");
		}
		System.out.println("Main Thread ended");
	
	}

	
	private static String customString(FlagData data) {
		return "raw: " + data.toString() + "; A: " + data.A + "; B: " + data.B + "; D: " + data.D
				+ "; E: " + (data.E == null ? "" : "E" + data.E.multiCount + "(" + data.E.zid + ")")
				+ "; F: " + (data.F == null ? "" : "F" + data.F.multiCount + "(" + data.F.zid + ")")
				+ "; K: " + (data.K == null ? "" : "K" + data.K.multiCount + "(" + data.K.zid + ")")
				+ "; L: " + data.L + "; P: " + (data.P == null ? "" : data.P.name()) + "; R: " + data.R
				+ "; W: " + (data.W == null ? "" : "W[" + data.W.enr1 + "][" + data.W.enr2 + "]");
	}
}
