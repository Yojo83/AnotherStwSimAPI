package yojo.stwPlugIn.Client.parser;

import yojo.stwPlugIn.Client.parser.XmlParser.ParserException;
import yojo.stwPlugIn.Client.util.ResponseListener;

/**
 * interface. Implementing classes are responsible for parsing a specific xml message
 */
public interface ResponseParser {

	/**
	 * if the message has been ended
	 * @return true, if the message is ended, false, if the parser expects more xml lines
	 */
	boolean isFinished();

	/**
	 * parses the next token
	 * @param t the token to parse
	 * @param responseListener called when the message is read
	 * @throws ParserException
	 */
	void parse(Token t, ResponseListener responseListener) throws ParserException;

}
