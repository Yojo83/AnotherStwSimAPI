package yojo.stwPlugIn.Client.parser.trainlist;

import java.util.HashMap;

import yojo.stwPlugIn.Client.Messages.TrainListResponse;
import yojo.stwPlugIn.Client.parser.ResponseParser;
import yojo.stwPlugIn.Client.parser.XMLLine;
import yojo.stwPlugIn.Client.parser.XMLLineType;
import yojo.stwPlugIn.Client.parser.XmlParser.LineParserException;
import yojo.stwPlugIn.Client.util.ResponseListener;

/**
 * parses the train list message
 * @author Yojo
 *
 */
public class TrainListResponseParser implements ResponseParser {

	private HashMap<Integer, String> entrys;
	
	@Override
	public void parse(XMLLine line, ResponseListener responseListener) throws LineParserException {
		if(line.Type == XMLLineType.zugliste) {
			if(entrys != null) 
				throw new LineParserException("already parsing", line);
			entrys = new HashMap<>();
		} else if(line.Type == XMLLineType.zugliste_end) {
			entrys = null;
			responseListener.onTrainList(new TrainListResponse(entrys));
		} else {
			if(entrys == null)
				throw new LineParserException("currently not parsing schedule list", line);
			if(line.Type != XMLLineType.zug)
				throw new LineParserException("expected zug", line);
			parseTrain(line);
		}
	}
	
	
	private void parseTrain(XMLLine line) throws LineParserException {
		int tid = line.getInt("zid");
		String name = line.getString("name");
		if(name == null)
			throw new LineParserException("expected name", line);
		
		entrys.put(tid, name);
	}
	
}
