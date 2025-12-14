package yojo.stwPlugIn.Client.parser.schedule;

import java.util.ArrayList;

import yojo.stwPlugIn.Client.Messages.TrainScheduleResponse;
import yojo.stwPlugIn.Client.Messages.definitions.ScheduleEntry;
import yojo.stwPlugIn.Client.parser.ResponseParser;
import yojo.stwPlugIn.Client.parser.XMLLine;
import yojo.stwPlugIn.Client.parser.XMLLineType;
import yojo.stwPlugIn.Client.parser.XmlParser.LineParserException;
import yojo.stwPlugIn.Client.util.ResponseListener;

/**
 * parses the train schedule message
 * @author Yojo
 *
 */
public class TrainScheduleResponseParser implements ResponseParser {


	private ScheduleEntryParser parser;

	private int trainId;
	private ArrayList<ScheduleEntry> entrys = new ArrayList<>();
	
	@Override
	public void parse(XMLLine line, ResponseListener responseListener) throws LineParserException {
		if(line.Type == XMLLineType.zugfahrplan) {
			if(parser != null) 
				throw new LineParserException("already parsing", line);
			parser = new ScheduleEntryParser();
			trainId = line.getInt("zid");
		} else if(line.Type == XMLLineType.zugfahrplan_end) {
			parser = null;
			responseListener.onTrainSchedule(new TrainScheduleResponse(trainId, entrys));
		} else {
			if(parser == null)
				throw new LineParserException("currently not parsing schedule list", line);
			ScheduleEntry data = parser.parse(line);
			if(data != null)
				entrys.add(data);
		}
	}
	
}
