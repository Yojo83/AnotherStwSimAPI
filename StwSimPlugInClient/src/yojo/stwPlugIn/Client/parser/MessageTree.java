package yojo.stwPlugIn.Client.parser;

import yojo.stwPlugIn.Client.parser.XmlParser.ParserException;
import yojo.stwPlugIn.Client.parser.misc.*;
import yojo.stwPlugIn.Client.parser.plattformlist.PlattformListResponseParser;
import yojo.stwPlugIn.Client.parser.schedule.TrainScheduleResponseParser;
import yojo.stwPlugIn.Client.parser.structure.StructureResponseParser;
import yojo.stwPlugIn.Client.parser.trainlist.TrainListResponseParser;
import yojo.stwPlugIn.Client.util.ResponseListener;

public class MessageTree {
	
	private ResponseParser parser;

	public boolean isFinished() {
		return parser != null && parser.isFinished();
	}

	public void parse(Token t, ResponseListener responseListener) throws ParserException {
		if(parser != null) {
			parser.parse(t, responseListener);
			return;
		}
		
		if(t.value == null) 
			throw new ParserException("Message doesn't start with string", t);
		
		switch(t.value) {
		case "stitz":
			parser = new StitzResponseParser();
			break;
		case "zugdetails":
			parser = new TrainDetailsResponseParser();
			break;
		case "zugfahrplan": 
			parser = new TrainScheduleResponseParser();
			break;
		case "fsset":
			parser = new FsSetResponseParser();
			break;
		case "hitze":
			parser = new HeatResponseParser();
			break;
		case "element4":
			parser = new Element4ResponseParser();
			break;
		case "enr4":
			parser = new Enr4ResponseParser();
			break;
		case "anlageninfo":
			parser = new SystemInfoResponseParser();
			break;
		case "ereignis":
			parser = new EventResponseParser();
			break;
		case "zugliste":
			parser = new TrainListResponseParser();
			break;
		case "wege":
			parser = new StructureResponseParser();
			break;
		case "bahnsteigliste":
			parser = new PlattformListResponseParser();
			break;
		default:
			throw new ParserException("can't identify message", t);	
		}
	}


}
