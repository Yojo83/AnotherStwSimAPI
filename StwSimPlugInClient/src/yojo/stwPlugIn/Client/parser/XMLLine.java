package yojo.stwPlugIn.Client.parser;

import java.util.HashMap;

import yojo.stwPlugIn.Client.parser.XmlParser.LineParserException;
import yojo.stwPlugIn.Client.util.TimeManager;

public class XMLLine {


	public final XMLLineType Type;
	private final HashMap<String, String> values;
	
	public XMLLine(XMLLineType type, HashMap<String, String> values){
		this.Type = type;
		this.values = values;
	}
	
	public boolean contains(String key){
		return values.containsKey(key);
	}
	
	public String getString(String key) {
		return values.get(key);
	}

	public long getLong(String key) throws LineParserException {
		String value = getString(key);
		if(value == null)
			throw new LineParserException("expected " + key, this);
		try {
			return Long.parseLong(value);
		} catch (NumberFormatException e) {
			throw new LineParserException(key + " not a number", this);
		}
	}

	public int getInt(String key) throws LineParserException {
		String value = getString(key);
		if(value == null)
			throw new LineParserException("expected " + key, this);
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			throw new LineParserException(key + " not a number", this);
		}
	}
	
	public boolean getBool(String key) {
		return Boolean.parseBoolean(getString(key));
	}

	public long getTime(String key, long emptyValue) throws LineParserException {
		String value = getString(key);
		return TimeManager.toLong(value, this, emptyValue);
	}
	
	@SuppressWarnings("incomplete-switch")
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append('<');
		switch(Type) {
		case bahnsteig_end:
		case bahnsteigliste_end:
		case wege_end:
		case zugfahrplan_end:
		case zugliste_end:
			builder.append('/');
		}
		builder.append(Type.name());
		builder.append(' ');
		values.forEach((key, value) -> {
			builder.append(key).append('=').append(value);
		});
		switch(Type) {
		case anlageninfo:
		case zugdetails:
		case ereignis:
		case hitze:
		case stitz:
		case fsset:
		case enr4:
		case element4:
		case simzeit:
			builder.append('/');
		}
		builder.append('>');
		return builder.toString();
	}

}
