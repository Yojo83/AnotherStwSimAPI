package yojo.stwPlugIn.Client.parser;

import yojo.stwPlugIn.Client.parser.XmlParser.ParserException;
import yojo.stwPlugIn.Client.util.ResponseListener;

public interface ResponseParser {

	boolean isFinished();

	void parse(Token t, ResponseListener responseListener) throws ParserException;

}
