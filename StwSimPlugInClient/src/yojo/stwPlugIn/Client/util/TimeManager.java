package yojo.stwPlugIn.Client.util;

import yojo.stwPlugIn.Client.parser.Token;
import yojo.stwPlugIn.Client.parser.XmlParser.ParserException;

public class TimeManager {

	public static long toLong(String value, Token t) throws ParserException {
		String[] args = value.split(":");
		if(args.length != 2)
			throw new ParserException("wrong time format; expected hh:mm", t);
		int hours, mins;
		try {
			hours = Integer.parseInt(args[0]);
			mins = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			throw new ParserException("times are not numbers; expected hh:mm", t);
		}
		
		return (hours * 60 + mins) * 60 * 1000;
	}
	
}
