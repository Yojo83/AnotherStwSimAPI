package yojo.stwPlugIn.Client.Messages;

import java.util.Collections;
import java.util.List;

import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;
import yojo.stwPlugIn.Client.Messages.definitions.ScheduleEntry;

public class TrainScheduleResponse extends ResponseMessage {

	
	public final List<ScheduleEntry> scheduleList;
	public final int trainId;
	
	public TrainScheduleResponse(String raw, int trainId, List<ScheduleEntry> scheduleList) {
		super(ResponseType.Trainschedule, raw);
		this.scheduleList = Collections.unmodifiableList(scheduleList);
		this.trainId = trainId;
	}
}
