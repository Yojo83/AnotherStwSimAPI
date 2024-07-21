package yojo.stwPlugIn.Client.parser;

import yojo.stwPlugIn.Client.util.ResponseListener;

public class XmlParser {

	public void handleLine(String line, ResponseListener responseListener) {
		Token[] tokenStream = Token.toTokenArray(line);
	}

}
