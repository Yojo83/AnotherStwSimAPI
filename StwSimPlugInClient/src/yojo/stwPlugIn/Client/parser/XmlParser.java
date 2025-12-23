package yojo.stwPlugIn.Client.parser;

import java.util.HashMap;

import yojo.stwPlugIn.Client.Messages.RawMessage;
import yojo.stwPlugIn.Client.parser.misc.Element4ResponseParser;
import yojo.stwPlugIn.Client.parser.misc.Enr4ResponseParser;
import yojo.stwPlugIn.Client.parser.misc.EventResponseParser;
import yojo.stwPlugIn.Client.parser.misc.FsSetResponseParser;
import yojo.stwPlugIn.Client.parser.misc.HeatResponseParser;
import yojo.stwPlugIn.Client.parser.misc.StatusResponseParser;
import yojo.stwPlugIn.Client.parser.misc.StitzResponseParser;
import yojo.stwPlugIn.Client.parser.misc.SystemInfoResponseParser;
import yojo.stwPlugIn.Client.parser.misc.TimeResponseParser;
import yojo.stwPlugIn.Client.parser.misc.TrainDetailsResponseParser;
import yojo.stwPlugIn.Client.parser.plattformlist.PlattformListResponseParser;
import yojo.stwPlugIn.Client.parser.schedule.TrainScheduleResponseParser;
import yojo.stwPlugIn.Client.parser.structure.StructureResponseParser;
import yojo.stwPlugIn.Client.parser.trainlist.TrainListResponseParser;
import yojo.stwPlugIn.Client.util.DEBUGGER;
import yojo.stwPlugIn.Client.util.ResponseListener;

/**
 * a parser to parse xml messages from the game make function calls to the listener
 */
public class XmlParser {
	
	private HashMap<XMLLineType, ResponseParser> parsers = new HashMap<>();
	
	public XmlParser() {
		initializeParsers();
	}
	
	
	/**
	 * parses this message
	 * @param line the next xml line
	 * @param responseListener the listener, thats functions are called if the xml message ends
	 */
	public void handleLine(String line, ResponseListener responseListener) {
		Token[] tokens = Token.toTokenArray(line);
		try {
			XMLLine xml = parseLine(tokens);
			parsers.get(xml.Type).parse(xml, responseListener);
		} catch (TokenParserException e) {
			DEBUGGER.log("Error at Token " + e.token.toString() + " in Message: " + line + ": " + e.msg);
			e.printStackTrace();
			responseListener.unhandeledMessage(new RawMessage(line));
		} catch (LineParserException e) {
			DEBUGGER.log("Error at Line " + e.line.toString() + " in Message: " + line + ": " + e.msg);
			e.printStackTrace();
			responseListener.unhandeledMessage(new RawMessage(line));
		}
	}
	
	
	/**
	 * parses the tokens into an object representing the xml line
	 * @param tokens the line as token stream
	 * @throws TokenParserException
	 */
	private XMLLine parseLine(Token[] tokens) throws TokenParserException {
		XMLLineParser parser = new XMLLineParser();
		
		for(Token t : tokens) {
			parser.parse(t);
		}
		
		return parser.getLine();
	}
	
	private void initializeParsers() {
		ResponseParser parser;
		
		parser = new PlattformListResponseParser();
		parsers.put(XMLLineType.bahnsteigliste, parser);
		parsers.put(XMLLineType.bahnsteig, parser);
		parsers.put(XMLLineType.n, parser);
		parsers.put(XMLLineType.bahnsteig_end, parser);
		parsers.put(XMLLineType.bahnsteigliste_end, parser);
		
		parser = new TrainListResponseParser();
		parsers.put(XMLLineType.zugliste, parser);
		parsers.put(XMLLineType.zug, parser);
		parsers.put(XMLLineType.zugliste_end, parser);
		
		parser = new TrainScheduleResponseParser();
		parsers.put(XMLLineType.zugfahrplan, parser);
		parsers.put(XMLLineType.gleis, parser);
		parsers.put(XMLLineType.zugfahrplan_end, parser);
		
		parser = new StructureResponseParser();
		parsers.put(XMLLineType.wege, parser);
		parsers.put(XMLLineType.shape, parser);
		parsers.put(XMLLineType.connector, parser);
		parsers.put(XMLLineType.wege_end, parser);
		
		
		parsers.put(XMLLineType.anlageninfo, new SystemInfoResponseParser());
		parsers.put(XMLLineType.zugdetails, new TrainDetailsResponseParser());
		parsers.put(XMLLineType.ereignis, new EventResponseParser());
		parsers.put(XMLLineType.hitze, new HeatResponseParser());
		parsers.put(XMLLineType.stitz, new StitzResponseParser());
		parsers.put(XMLLineType.fsset, new FsSetResponseParser());
		parsers.put(XMLLineType.enr4, new Enr4ResponseParser());
		parsers.put(XMLLineType.element4, new Element4ResponseParser());
		parsers.put(XMLLineType.simzeit, new TimeResponseParser());
		parsers.put(XMLLineType.status, new StatusResponseParser());
	}
	
	/**
	 * thrown on any error during the parsing process.
	 * will be catch on top of the answer hierarchy to give an error to the log
	 * and call unhandeledMesage() from the listener with a raw message 
	 */
	public static class TokenParserException extends Exception{

		private static final long serialVersionUID = 1L;
		
		public final String msg;
		public final Token token;
		
		public TokenParserException(String msg, Token token) {
			super("Error at Token " + token.toString() + " : " + msg);
			this.msg = msg;
			this.token = token;
		}
	}
	
	public static class LineParserException extends Exception{

		private static final long serialVersionUID = 1L;
		
		public final String msg;
		public final XMLLine line;
		
		public LineParserException(String msg, XMLLine line) {
			super("Error at Line " + line.toString() + " : " + msg);
			this.msg = msg;
			this.line = line;
		}
	}
}
