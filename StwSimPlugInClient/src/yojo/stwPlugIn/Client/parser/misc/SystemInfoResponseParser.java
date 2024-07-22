package yojo.stwPlugIn.Client.parser.misc;

import yojo.stwPlugIn.Client.Messages.SysteminfoResponse;
import yojo.stwPlugIn.Client.parser.Token;
import yojo.stwPlugIn.Client.parser.XmlParser.ParserException;
import yojo.stwPlugIn.Client.util.ResponseListener;

public class SystemInfoResponseParser extends MiscParser {

	private String build;
	private String name;
	private boolean online;
	private String region;
	private int sid;
	
	private ExpectedValues ev;
	
	@Override
	protected void doAction(ResponseListener responseListener, Token t) throws ParserException {
		responseListener.onSystemInfo(new SysteminfoResponse(sid, region, online, name, build));
	}

	@Override
	protected void setExpectedValue(Token t) throws ParserException {
		switch(t.value) {
		case "simbuild": ev = ExpectedValues.Build; break;
		case "name": ev = ExpectedValues.Name; break;
		case "online": ev = ExpectedValues.Online; break;
		case "region": ev = ExpectedValues.Region; break;
		case "aid": ev = ExpectedValues.Sid; break;
		default:
			throw new ParserException("failed to deduce token to systeminfo variable", t);
		}
	}

	@Override
	protected void setValue(Token t) throws ParserException {
		switch (ev) {
		case Build:
			build = t.value;
			break;
		case Name:
			name = t.value;
			break;
		case Online:
			online = Boolean.parseBoolean(t.value);
			break;
		case Region:
			region = t.value;
			break;
		case Sid:
			try {
				sid = Integer.parseInt(t.value);
			} catch (NumberFormatException e) {
				throw new ParserException("system id not a number", t);
			}
			break;
		default:
			throw new ParserException("internal (impossible) error", t);
		}
	}

	
	private static enum ExpectedValues{
		Build,
		Name,
		Online,
		Region,
		Sid;
	}
	
}
