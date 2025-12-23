package yojo.stwPlugIn.Client.parser;

import yojo.stwPlugIn.Client.parser.XmlParser.TokenParserException;

public enum XMLLineType {
	bahnsteig,
	bahnsteigliste,
	zugliste,
	zugfahrplan,
	wege,
	bahnsteig_end,
	bahnsteigliste_end,
	zugliste_end,
	zugfahrplan_end,
	wege_end,
	shape,
	connector,
	gleis,
	zug,
	n,
	anlageninfo,
	zugdetails,
	ereignis,
	hitze,
	stitz,
	fsset,
	enr4,
	element4,
	simzeit,
	status;
	
	public static XMLLineType parse(Token t, boolean lineEnd) throws TokenParserException {
		XMLLineType type;
		try {
			type = valueOf(t.value);
		} catch (IllegalArgumentException e) {
			throw new TokenParserException("Message not recognised", t);
		}
		if(!lineEnd)
			return type;
		
		switch(type) {
		case bahnsteigliste: 	return bahnsteigliste_end;
		case bahnsteig:			return bahnsteig_end;
		case zugliste:			return zugliste_end;
		case zugfahrplan:		return zugfahrplan_end;
		case wege:				return wege_end;
		default:
			throw new TokenParserException("End message not recognised", t);
		}
	}
	

}
