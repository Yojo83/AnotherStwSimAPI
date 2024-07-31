package yojo.stwPlugIn.Client.parser.schedule;

import yojo.stwPlugIn.Client.Messages.definitions.FlagData;
import yojo.stwPlugIn.Client.Messages.definitions.ScheduleEntry;
import yojo.stwPlugIn.Client.parser.Token;
import yojo.stwPlugIn.Client.parser.XmlParser.ParserException;
import yojo.stwPlugIn.Client.util.TimeManager;

public class ScheduleEntryParser {
	
	
	private String plattform;
	private String regularPlattform;
	private long arrival;
	private long departure;
	private FlagData flags;
	
	
	private State state = State.NULL;
	private ExpectedValue expecVal;
	

	public boolean isFinished() {
		return state == State.Finished;
	}

	public ScheduleEntry getEntry() {		
		return new ScheduleEntry(plattform, regularPlattform, arrival, departure, flags);
	}

	public void parse(Token t) throws ParserException {
		switch(state) {
		case EXPECT_EQUAL:
			if(t != Token.EQUAL)
				throw new ParserException("Expected = but didn't found it", t);
			state = State.EXPECT_VALUE;
			break;
		case EXPECT_VALUE:
			if(t.value == null)
				throw new ParserException("Expected string but didn't found it", t);
			setValue(expecVal, t);
			state = State.NULL;
			break;
		case NULL:
			if(t == Token.SLASH) {
				state = State.Finished;
				break;
			}
			
			if(t.value == null)
				throw new ParserException("Expected string but didn't found it", t);
			state = State.EXPECT_EQUAL;
			this.expecVal = getExpectedValue(t);
			break;
		default:
			throw new ParserException("PlattformParser was not in a valid state", t);
		}
	}

	private void setValue(ExpectedValue ev, Token t) throws ParserException {
		switch (ev) {
		case Arrival:
			arrival = TimeManager.toLong(t);
			break;
		case Departure:
			departure = TimeManager.toLong(t);
			break;
		case Flags:
			flags = new FlagData(t.value);
			break;
		case Plattform:
			plattform = t.value;
			break;
		case RegularPlattform:
			regularPlattform = t.value;
			break;
		default:
			throw new ParserException("internal (impossible) error", t);
		}
	}

	private ExpectedValue getExpectedValue(Token t) throws ParserException {
		switch(t.value) {
		case "plan": return ExpectedValue.RegularPlattform;
		case "name": return ExpectedValue.Plattform;
		case "an": return ExpectedValue.Arrival;
		case "ab": return ExpectedValue.Departure;
		case "flags": return ExpectedValue.Flags;
		default:
			throw new ParserException("failed to deduce token to Plattform variable", t);
		}
	}

	private static enum State{
		NULL,
		EXPECT_EQUAL,
		EXPECT_VALUE,
		Finished;
	}
	
	private static enum ExpectedValue {
		Plattform,
		RegularPlattform,
		Arrival,
		Departure,
		Flags;
	}

}
