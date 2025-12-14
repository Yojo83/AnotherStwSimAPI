package yojo.stwPlugIn.Client.parser.schedule;

import yojo.stwPlugIn.Client.Messages.definitions.FlagData;
import yojo.stwPlugIn.Client.Messages.definitions.ScheduleEntry;
import yojo.stwPlugIn.Client.parser.XMLLine;
import yojo.stwPlugIn.Client.parser.XmlParser.LineParserException;

/**
 * parses one schedule entry
 * @author Yojo
 *
 */
public class ScheduleEntryParser {
	

	public ScheduleEntry parse(XMLLine line) throws LineParserException {
		long arrival = line.getLong("an");
		long departure = line.getLong("ab");
		
		FlagData flags = FlagParser.parse(line);
		
		String text = line.getString("hinweistext");
		String plattform = line.getString("name");
		String regularPlattform = line.getString("plan");
		
		return new ScheduleEntry(plattform, regularPlattform, arrival, departure, flags, text);
	}
	
	
}
