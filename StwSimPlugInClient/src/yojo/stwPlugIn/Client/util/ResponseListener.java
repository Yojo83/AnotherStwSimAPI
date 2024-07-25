package yojo.stwPlugIn.Client.util;

import yojo.stwPlugIn.Client.Messages.*;

public class ResponseListener {
	
	/**
	 * this class needs to be the super class of your listener, which is given the PlugInClient.
	 * the Methods of this class are called when a matching message from the game arrives.
	 * all those Methods will be called from one Thread, which is opened and managed by the PlugInClient
	 * as long, as the connection to the game exists.
	 */
	public ResponseListener() {
	}

	/**
	 * called when the connection to the game is closed
	 */
	public void onClose() {
		DEBUGGER.log("socket closed");
	}
	
	/**
	 * this message is called whenever a message isn't otherwise if a child class calls the super method from ResponseListener.
	 * The ResponseType of the message will be raw if it is an error and something else if it a recall from another message type.
	 * This Message is also called when the game sends status updates or errors
	 * @param msg the recieved Message - use toString() to get a xml like representation
	 */
	public void unhandeledMessage(ResponseMessage msg) {
		DEBUGGER.log("unhandeled Message: " + msg);
	}

	/**
	 * response for the system info
	 * @param msg Information about the System
	 */
	public void onSystemInfo(SysteminfoResponse msg) {
		unhandeledMessage(msg);
	}

	/**
	 * response from the plattform list request
	 * @param msg the plattform list
	 */
	public void onPlattformList(PlattformlistResponse msg) {
		unhandeledMessage(msg);
	}

	/**
	 * response from the train list request
	 * @param msg the train list
	 */
	public void onTrainList(TrainListResponse msg) {
		unhandeledMessage(msg);
	}

	/**
	 * response from the train details request
	 * @param msg the train details
	 */
	public void onTrainDetails(TrainDetailsResponse msg) {
		unhandeledMessage(msg);
	}

	/**
	 * response from the train schedule request
	 * @param msg the train schedule
	 */
	public void onTrainSchedule(TrainScheduleResponse msg) {
		unhandeledMessage(msg);
	}

	/**
	 * calls when a registered event is triggered
	 * @param msg the triggered event
	 */
	public void onEvent(EventResponse msg) {
		unhandeledMessage(msg);
	}

	/**
	 * response from the heat request
	 * @param msg the current heat
	 */
	public void onHeat(HeatResponse msg) {
		unhandeledMessage(msg);
	}

	/**
	 * response from the stitz request
	 * @param msg common and regional stitz Tel
	 */
	public void onStitz(StitzResponse msg) {
		unhandeledMessage(msg);
	}

	/**
	 * response from the structure request
	 * @param msg the structure of the system
	 */
	public void onStructure(StructureResponse msg) {
		unhandeledMessage(msg);
	}

	/**
	 * response on fs set
	 * this is direktly propagated from the game and will called two times with success if it is set direktly,
	 * one times with unknown if the fs is not possible, one times with busy if the fs memory is already full,
	 * and not if the fs is set into fs memory (even if it is set after some time)
	 * @param msg the response from the game about the fs
	 */
	public void onFsSet(FsSetResponse msg) {
		unhandeledMessage(msg);
	}

	/**
	 * the enr from an enr4 request
	 * @param msg the enr and element name from the request
	 */
	public void onEnrFromElement(EnrFromElementResponse msg) {
		unhandeledMessage(msg);
	}

	/**
	 * the element from an element4 request
	 * @param msg the element and enr name from the request
	 */
	public void onElementFromEnr(ElementFromEnrResponse msg) {
		unhandeledMessage(msg);
	}
	
	/**
	 * response from the time request
	 * @param msg the current simulator time
	 */
	public void onTime(TimeResponse msg) {
		unhandeledMessage(msg);
	}
	
}
