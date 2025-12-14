package yojo.stwPlugIn.Client.parser.plattformlist;

import java.util.ArrayList;

import yojo.stwPlugIn.Client.Messages.definitions.PlattformData;
import yojo.stwPlugIn.Client.parser.XMLLine;
import yojo.stwPlugIn.Client.parser.XmlParser.LineParserException;

/**
 * parses one plattform of the plattform list
 * @author Yojo
 *
 */
public class PlattformParser {


	private String name;
	private boolean stop;
	private ArrayList<String> neighbours;
	

	public PlattformData parse(XMLLine line) throws LineParserException {
		switch(line.Type) {
		case bahnsteig:
			if(neighbours != null)
				throw new LineParserException("already parsing", line);
			
			neighbours = new ArrayList<>();
			
			name = line.getString("name");
			if(name == null)
				throw new LineParserException("expected name", line);
			
			stop = line.getBool("haltepunkt");
			break;
		case bahnsteig_end:
			PlattformData data = new PlattformData(name, stop, neighbours);
			name = null;
			neighbours = null;
			return data;
		case n:
			String value = line.getString("name");
			if(value == null)
				throw new LineParserException("expected name", line);
			neighbours.add(value);
			break;
		default:
			throw new LineParserException("expected bahnsteig, /bahnsteig or n", line);
		}
		return null;
	}
	
}
