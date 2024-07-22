package yojo.stwPlugIn.Client.Messages;

import java.util.Collections;
import java.util.List;

import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;
import yojo.stwPlugIn.Client.Messages.definitions.ScheduleEntry;

public class TrainScheduleResponse extends ResponseMessage {

	
	public final List<ScheduleEntry> scheduleList;
	public final int trainId;
	
	public TrainScheduleResponse(int trainId, List<ScheduleEntry> scheduleList) {
		super(ResponseType.Trainschedule);
		this.scheduleList = Collections.unmodifiableList(scheduleList);
		this.trainId = trainId;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("<zugfahrplan zid='" + trainId + "' >");
		for(ScheduleEntry entry : scheduleList) {
			str.append(entry.toString());
		}
		str.append("</zugfahrplan>");
		return str.toString();
	}
}
