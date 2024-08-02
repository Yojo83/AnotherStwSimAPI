package yojo.stwPlugIn.Client.parser.misc;

import yojo.stwPlugIn.Client.Messages.FsSetResponse;
import yojo.stwPlugIn.Client.Messages.definitions.FsSetResult;
import yojo.stwPlugIn.Client.parser.Token;
import yojo.stwPlugIn.Client.parser.XmlParser.ParserException;
import yojo.stwPlugIn.Client.util.ResponseListener;

/**
 * parses a fsset message
 * @author Yojo
 *
 */
public class FsSetResponseParser extends MiscParser {

	
	private int enr1;
	private int enr2;
	private FsSetResult result;

	private ExpectedValue expectedValue;
	
	

	
	@Override
	protected void doAction(ResponseListener responseListener, Token t) throws ParserException {
		responseListener.onFsSet(new FsSetResponse(enr1, enr2, result));
	}

	@Override
	protected void setExpectedValue(Token t) throws ParserException {
		switch(t.value) {
		case "result": expectedValue = ExpectedValue.Result; break;
		case "stop": expectedValue = ExpectedValue.Enr2; break;
		case "start": expectedValue = ExpectedValue.Enr1; break;
		default:
			throw new ParserException("failed to deduce token to fsset variable", t);
		}
	}

	@Override
	protected void setValue(Token t) throws ParserException {
		switch(expectedValue) {
		case Enr1:
			try {
				enr1 = Integer.parseInt(t.value);
			} catch (NumberFormatException e) {
				throw new ParserException("enr1 not a number", t);
			}
			break;
		case Enr2:
			try {
				enr2 = Integer.parseInt(t.value);
			} catch (NumberFormatException e) {
				throw new ParserException("enr2 not a number", t);
			}
			break;
		case Result:
			switch(t.value.toLowerCase()) {
			case "success": result = FsSetResult.SUCCESS; break;
			case "busy": result = FsSetResult.BUSY; break;
			case "unknown": result = FsSetResult.UNKNOWN; break;
			default:
				throw new ParserException("not a result type", t);
			}
			break;
		default:
			throw new ParserException("internal (impossible) error", t);
		}
	}
	

	private static enum ExpectedValue {
		Enr1,
		Enr2,
		Result;
	}
}
