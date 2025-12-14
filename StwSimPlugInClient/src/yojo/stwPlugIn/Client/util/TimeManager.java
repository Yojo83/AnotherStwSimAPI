package yojo.stwPlugIn.Client.util;

import yojo.stwPlugIn.Client.parser.Token;
import yojo.stwPlugIn.Client.parser.XmlParser.TokenParserException;

public class TimeManager {

	/**
	 * 
	 * @param t the token with a time value in form of hh:mm
	 * @return the miliseconds since 00:00 of the time
	 * @throws ParserException if the there are no or too many :, or hh or mm is not an integer
	 */
	public static long toLong(Token t, long emptyValue) throws TokenParserException {
		if(t.value.equals(""))
			return emptyValue;
		
		String[] args = t.value.split(":");
		if(args.length != 2)
			throw new TokenParserException("wrong time format; expected hh:mm", t);
		int hours, mins;
		try {
			hours = Integer.parseInt(args[0]);
			mins = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			throw new TokenParserException("times are not numbers; expected hh:mm", t);
		}
		
		return (hours * 60 + mins) * 60 * 1000;
	}
	
}
