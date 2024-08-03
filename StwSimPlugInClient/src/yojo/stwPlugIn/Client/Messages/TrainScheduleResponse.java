package yojo.stwPlugIn.Client.Messages;

import java.util.Collections;
import java.util.List;

import yojo.stwPlugIn.Client.Messages.definitions.ResponseType;
import yojo.stwPlugIn.Client.Messages.definitions.ScheduleEntry;

/**
 * The parsed response for the train schedule request containing the train id and schedule entrys
 * @author Yojo
 *
 */
public class TrainScheduleResponse extends ResponseMessage {

	/**
	 * a unmodifiable list containing the schedule entrys for the train.
	 * This represents the future way of the train
	 */
	public final List<ScheduleEntry> scheduleList;
	/**
	 * the train id, this schedule is linked to
	 */
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
			str.append(entry.toString() + "\n");
		}
		str.append("</zugfahrplan>");
		return str.toString();
	}
}
