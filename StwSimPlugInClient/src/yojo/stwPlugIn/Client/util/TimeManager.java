package yojo.stwPlugIn.Client.util;

import yojo.stwPlugIn.Client.parser.XMLLine;
import yojo.stwPlugIn.Client.parser.XmlParser.LineParserException;

public class TimeManager {

	/**
	 * 
	 * @param t the token with a time value in form of hh:mm
	 * @return the miliseconds since 00:00 of the time
	 * @throws ParserException if the there are no or too many :, or hh or mm is not an integer
	 */
	public static long toLong(String value, XMLLine line, long emptyValue) throws LineParserException {
		if(value == null || value.equals(""))
			return emptyValue;
		
		String[] args = value.split(":");
		if(args.length != 2)
			throw new LineParserException("wrong time format; expected hh:mm", line);
		int hours, mins;
		try {
			hours = Integer.parseInt(args[0]);
			mins = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			throw new LineParserException("times are not numbers; expected hh:mm", line);
		}
		
		return (hours * 60 + mins) * 60 * 1000;
	}
	
}
