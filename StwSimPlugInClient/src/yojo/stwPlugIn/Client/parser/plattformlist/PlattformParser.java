package yojo.stwPlugIn.Client.parser.plattformlist;

import java.util.List;

import yojo.stwPlugIn.Client.Messages.definitions.PlattformData;
import yojo.stwPlugIn.Client.parser.Token;

public class PlattformParser {

	private NeighbourParser parser;
	
	private String name;
	private boolean stop;
	private List<String> neighbours;
	
	private State state;
	
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	public void parse(Token t) {
		// TODO Auto-generated method stub
		
	}

	public PlattformData getEntry() {
		// TODO Auto-generated method stub
		return null;
	}

	private static enum State{
		
	}
}
