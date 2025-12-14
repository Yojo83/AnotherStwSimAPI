package yojo.stwPlugIn.Client.parser.schedule;

import yojo.stwPlugIn.Client.Messages.definitions.FlagData;
import yojo.stwPlugIn.Client.Messages.definitions.FlagData.EKF_Args;
import yojo.stwPlugIn.Client.Messages.definitions.FlagData.P_Args;
import yojo.stwPlugIn.Client.Messages.definitions.FlagData.W_Args;
import yojo.stwPlugIn.Client.parser.XMLLine;
import yojo.stwPlugIn.Client.parser.XmlParser.LineParserException;

public class FlagParser {

	
	public static FlagData parse(XMLLine line) throws LineParserException {
		if(line == null)
			throw new LineParserException("line is null", line);
		return (new FlagParser(line, line.getString("flags"))).parse();
	}
	
	private final XMLLine line;
	private final String flags;
	

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
	
	private byte number;
	private String arg1;
	private int arg2;
	
	private FlagParser(XMLLine line, String flags) throws LineParserException {
		if(flags == null)
			throw new LineParserException("expected flags", line);
		this.line = line;
		this.flags = flags;
	}
	
	private FlagData parse() throws LineParserException {
		for(char c : flags.toUpperCase().toCharArray()) {
			switch(state) {
			case FLAG:
				parseFlag(c);
				break;
			case ID1:
				parseId1(c);
				break;
			case ID2:
				parseId2(c);
				break;
			case NUMBER:
				parseNumber(c);
				break;
			case NUMBER_OR_ID:
				parseNumberOrId(c);
				break;
			case POST_P:
				parsePostP(c);
				break;
			case DIRECTION:
				parseDirection(c);
				break;
			default:
				throw new LineParserException("invalid state at " + c, line);
			}
		}
		
		return new FlagData(flags, A, B, D, E, F, K, L, P, R, W);
	}
	
	private void parsePostP(char c) throws LineParserException {
		if(c == '(' || c == '[') {
			state = ExpectedToken.DIRECTION;
		} else {
			P = P_Args.NONE;
			state = ExpectedToken.FLAG;
			parseFlag(c);
		}
	}

	private void parseNumberOrId(char c) throws LineParserException {
		switch(c) {
		case '(':
		case '[':
			break;
		case '1': 	number = 1; 	break;
		case '2': 	number = 2; 	break;
		case '3': 	number = 3; 	break;
		case '4': 	number = 4; 	break;
		case '5': 	number = 5; 	break;
		case '6': 	number = 6; 	break;
		case '7': 	number = 7; 	break;
		case '8': 	number = 8; 	break;
		case '9': 	number = 9; 	break;
		default:
			throw new LineParserException("expected number or train id not " + c, line);
		}
		state = ExpectedToken.ID1;
	}

	private void parseNumber(char c) throws LineParserException {
		try {
			B = Byte.parseByte(c + "");
		} catch (NumberFormatException e) {
			throw new LineParserException(c + " is not a number", line);
		}
		
		if(B < 1 || B > 9)
			throw new LineParserException(c + " is not between 1 and 9", line);
		
		if(last != LastFlag.B)
			throw new LineParserException("Invalid state: expected number for B flag", line);
		
		last = LastFlag.OTHER;
		state = ExpectedToken.FLAG;
	}

	private void parseId2(char c) throws LineParserException {
		switch(c) {
		case '(':
		case '[':
			break;
		case ')':
		case ']':
			try {
				arg2 = Integer.parseInt(arg1);
			} catch (NumberFormatException e) {
				throw new LineParserException("arg not a number after " + last.name(), line);
			}
			state = ExpectedToken.ID1;
			arg1 = "";
			break;
		default:
			arg1 += c;
		}
	}

	private void parseId1(char c) throws LineParserException {
		switch(c) {
		case '(':
		case '[':
			break;
		case ')':
		case ']':
			int arg;
			try {
				arg = Integer.parseInt(arg1);
			} catch (NumberFormatException e) {
				throw new LineParserException("arg not a number after " + last.name(), line);
			}
			switch(last) {
			case E:				E = new EKF_Args(number, arg);				break;
			case F:				F = new EKF_Args(number, arg);				break;
			case K:				K = new EKF_Args(number, arg);				break;
			case W:				W = new W_Args(arg2, arg);					break;
			case B:
			case OTHER:
			default:
				throw new LineParserException("Did not expect argument after flag " + last.name(), line);
			}
			arg1 = "";
			state = ExpectedToken.FLAG;
			last = LastFlag.OTHER;
			break;
		default:
			arg1 += c;
		}
	}

	private void parseFlag(char c) throws LineParserException {
		switch(c) {
		case 'A':
			A = true;
			break;
		case 'B':
			state = ExpectedToken.NUMBER;
			number = 0; arg1 = ""; arg2 = 0;
			last = LastFlag.B;
			break;
		case 'D':
			D = true;
			break;
		case 'E':
			state = ExpectedToken.NUMBER_OR_ID;
			number = 0; arg1 = ""; arg2 = 0;
			last = LastFlag.E;
			break;
		case 'F':
			state = ExpectedToken.NUMBER_OR_ID;
			number = 0; arg1 = ""; arg2 = 0;
			last = LastFlag.F;
			break;
		case 'K':
			state = ExpectedToken.NUMBER_OR_ID;
			number = 0; arg1 = ""; arg2 = 0;
			last = LastFlag.K;
			break;
		case 'L':
			L = true;
			break;
		case 'P':
			state = ExpectedToken.POST_P;
			break;
		case 'R':
			R = true;
			break;
		case 'W':
			state = ExpectedToken.ID2;
			number = 0; arg1 = ""; arg2 = 0;
			last = LastFlag.W;
			break;
		default:
			throw new LineParserException("Expected Flag at " + c, line);
		}
	}
	
	private void parseDirection(char c) throws LineParserException {
		switch(c) {
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
			throw new LineParserException("expected direction at " + c, line);
		}
	}
	
	private static enum ExpectedToken{
		FLAG,
		NUMBER,
		NUMBER_OR_ID,
		POST_P,
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
