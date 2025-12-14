package yojo.stwPlugIn.Client.parser.misc;

import yojo.stwPlugIn.Client.Messages.SysteminfoResponse;
import yojo.stwPlugIn.Client.parser.ResponseParser;
import yojo.stwPlugIn.Client.parser.XMLLine;
import yojo.stwPlugIn.Client.parser.XmlParser.LineParserException;
import yojo.stwPlugIn.Client.util.ResponseListener;

/**
 * parses the systeminfo message
 * @author Yojo
 *
 */
public class SystemInfoResponseParser implements ResponseParser {

	@Override
	public void parse(XMLLine line, ResponseListener responseListener) throws LineParserException {
		int sid = line.getInt("aid");

		boolean online = line.getBool("online");
		
		String region = line.getString("region");
		String build = line.getString("simbuild");
		String name = line.getString("name");
		
		
		responseListener.onSystemInfo(new SysteminfoResponse(sid, region, online, name, build));
	}

}
