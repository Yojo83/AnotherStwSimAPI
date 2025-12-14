package yojo.stwPlugIn.Client.parser.misc;

import yojo.stwPlugIn.Client.Messages.FsSetResponse;
import yojo.stwPlugIn.Client.Messages.definitions.FsSetResult;
import yojo.stwPlugIn.Client.parser.ResponseParser;
import yojo.stwPlugIn.Client.parser.XMLLine;
import yojo.stwPlugIn.Client.parser.XmlParser.LineParserException;
import yojo.stwPlugIn.Client.util.ResponseListener;

/**
 * parses a fsset message
 * @author Yojo
 *
 */
public class FsSetResponseParser implements ResponseParser {

	

	@Override
	public void parse(XMLLine line, ResponseListener responseListener) throws LineParserException {
		String value;
		
		int enr1 = line.getInt("start");
		int enr2 = line.getInt("stop");
		
		FsSetResult result;
		value = line.getString("result");
		if(value == null)
			throw new LineParserException("expected result type", line);
		try {
			result = FsSetResult.valueOf(value.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new LineParserException("not a result type", line);
		}
		
		
		
		responseListener.onFsSet(new FsSetResponse(enr1, enr2, result));
	}
	
}
