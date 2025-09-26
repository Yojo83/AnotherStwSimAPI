package yojo.stwPlugIn.Client.parser.schedule;

import yojo.stwPlugIn.Client.parser.Token;
import yojo.stwPlugIn.Client.parser.XmlParser.ParserException;
import yojo.stwPlugIn.Client.Messages.definitions.FlagData;
import yojo.stwPlugIn.Client.Messages.definitions.FlagData.EKF_Args;
import yojo.stwPlugIn.Client.Messages.definitions.FlagData.P_Args;
import yojo.stwPlugIn.Client.Messages.definitions.FlagData.W_Args;

public class FlagParser {

	
	public static FlagData parse(Token t) throws ParserException {
		return (new FlagParser(t)).parse();
	}
	
	private final Token raw;
	

	private boolean A = false;		
	private byte B = 0;		
	private boolean D = false;				
	private EKF_Args E = null;				
	private EKF_Args F = null;
	private EKF_Args K = null;				
	private boolean L = false;				
	private P_Args P = null;				
	private boolean R = false;				
	private W_Args W = null;

	private ExpectedToken state = ExpectedToken.FLAG;
	private LastFlag last = LastFlag.OTHER;
	
	private FlagParser(Token raw) {
		this.raw = raw;
	}
	
	private FlagData parse() throws ParserException {
		for(char c : raw.value.toUpperCase().toCharArray()) {
			switch(state) {
			case FLAG:
				parseFlag(c);
				break;
			case ID1:
				break;
			case ID2:
				break;
			case NUMBER:
				break;
			case NUMBER_OR_ID:
				break;
			case DIRECTION:
				parseDirection(c);
				break;
			default:
				throw new ParserException("invalid state at " + c, raw);
			}
		}
		
		return new FlagData(raw.value, A, B, D, E, F, K, L, P, R, W);
	}
	
	private void parseFlag(char c) throws ParserException {
		switch(c) {
		case 'A':
			A = true;
			break;
		case 'B':
			state = ExpectedToken.NUMBER;
			last = LastFlag.B;
			break;
		case 'D':
			D = true;
			break;
		case 'E':
			state = ExpectedToken.NUMBER_OR_ID;
			last = LastFlag.E;
			break;
		case 'F':
			state = ExpectedToken.NUMBER_OR_ID;
			last = LastFlag.F;
			break;
		case 'K':
			state = ExpectedToken.NUMBER_OR_ID;
			last = LastFlag.K;
			break;
		case 'L':
			L = true;
			break;
		case 'P':
			state = ExpectedToken.DIRECTION;
			break;
		case 'R':
			R = true;
			break;
		case 'W':
			state = ExpectedToken.ID2;
			last = LastFlag.W;
			break;
		default:
			throw new ParserException("Expected Flag at " + c, raw);
		}
	}
	
	private void parseDirection(char c) throws ParserException {
		switch(c) {
		case '(':
		case '[':
			break;
		case 'U':
			P = P_Args.UP;
			break;
		case 'D':
			P = P_Args.DOWN;
			break;
		case 'L':
			P = P_Args.LEFT;
			break;
		case 'R':
			P = P_Args.RIGHT;
			break;
		case ')':
		case ']':
			state = ExpectedToken.FLAG;
			break;
		default:
			throw new ParserException("expected direction at " + c, raw);
		}
	}
	
	private static enum ExpectedToken{
		FLAG,
		NUMBER,
		NUMBER_OR_ID,
		DIRECTION,
		ID1,
		ID2;
	}
	
	private static enum LastFlag{
		OTHER,
		B,
		E,
		F,
		K,
		W;
	}
}
