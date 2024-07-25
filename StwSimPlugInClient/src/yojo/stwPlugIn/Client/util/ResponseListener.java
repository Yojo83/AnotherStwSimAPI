package yojo.stwPlugIn.Client.util;

import yojo.stwPlugIn.Client.Messages.*;

public class ResponseListener {

	
	public void onClose() {
		DEBUGGER.log("socket closed");
	}
	
	
	public void unhandeledMessage(ResponseMessage msg) {
		DEBUGGER.log("unhandeled Message: " + msg);
	}

	public void onSystemInfo(SysteminfoResponse msg) {
		unhandeledMessage(msg);
	}

	public void onPlattformList(PlattformlistResponse msg) {
		unhandeledMessage(msg);
	}

	public void onTrainList(TrainListResponse msg) {
		unhandeledMessage(msg);
	}

	public void onTrainDetails(TrainDetailsResponse msg) {
		unhandeledMessage(msg);
	}

	public void onTrainSchedule(TrainScheduleResponse msg) {
		unhandeledMessage(msg);
	}

	public void onEvent(EventResponse msg) {
		unhandeledMessage(msg);
	}

	public void onHeat(HeatResponse msg) {
		unhandeledMessage(msg);
	}

	public void onStitz(StitzResponse msg) {
		unhandeledMessage(msg);
	}

	public void onStructure(StructureResponse msg) {
		unhandeledMessage(msg);
	}

	public void onFsSet(FsSetResponse msg) {
		unhandeledMessage(msg);
	}

	public void onEnrFromElement(EnrFromElementResponse msg) {
		unhandeledMessage(msg);
	}

	public void onElementFromEnr(ElementFromEnrResponse msg) {
		unhandeledMessage(msg);
	}
	
	public void onTime(TimeResponse msg) {
		unhandeledMessage(msg);
	}
	
}
