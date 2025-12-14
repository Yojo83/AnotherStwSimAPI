package yojo.stwPlugIn.Client.parser;

import yojo.stwPlugIn.Client.parser.XmlParser.LineParserException;
import yojo.stwPlugIn.Client.util.ResponseListener;

/**
 * interface. Implementing classes are responsible for parsing a specific xml message
 */
public interface ResponseParser {

	/**
	 * parses the next token
	 * @param line the xml line to parse
	 * @param responseListener called when the message is read
	 * @throws ParserException
	 */
	void parse(XMLLine line, ResponseListener responseListener) throws LineParserException;

}
