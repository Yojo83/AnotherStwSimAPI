package yojo.stwPlugIn.Client.parser.plattformlist;

import java.util.ArrayList;

import yojo.stwPlugIn.Client.Messages.PlattformlistResponse;
import yojo.stwPlugIn.Client.Messages.definitions.PlattformData;
import yojo.stwPlugIn.Client.parser.ResponseParser;
import yojo.stwPlugIn.Client.parser.XMLLine;
import yojo.stwPlugIn.Client.parser.XMLLineType;
import yojo.stwPlugIn.Client.parser.XmlParser.LineParserException;
import yojo.stwPlugIn.Client.util.ResponseListener;

/**
 * parses the plattforms message
 * @author Yojo
 *
 */
public class PlattformListResponseParser implements ResponseParser {

	private PlattformParser parser = null;
	private ArrayList<PlattformData> entrys = new ArrayList<>();
	

	@Override
	public void parse(XMLLine line, ResponseListener responseListener) throws LineParserException {
		if(line.Type == XMLLineType.bahnsteigliste) {
			if(parser != null) 
				throw new LineParserException("already parsing", line);
			parser = new PlattformParser();
		} else if(line.Type == XMLLineType.bahnsteigliste_end) {
			parser = null;
			responseListener.onPlattformList(new PlattformlistResponse(entrys));
		} else {
			if(parser == null)
				throw new LineParserException("currently not parsing plattform list", line);
			PlattformData data = parser.parse(line);
			if(data != null)
				entrys.add(data);
		}
	}
	

}
