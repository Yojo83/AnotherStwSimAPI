package yojo.stwPlugIn.Client.Messages.definitions;

public class ScheduleEntry {
	
	public final String plattform;
	public final String regularPlattform;
	public final long arrival;
	public final long departure;
	public final FlagData flags;
	public final String notice;
	
	public ScheduleEntry(String plattform, String regularPlattform, 
			long arrival, long departure, 
			FlagData flags, String notice) {
		this.plattform = plattform;
		this.regularPlattform = regularPlattform;
		this.arrival = arrival;
		this.departure = departure;
		this.flags = flags;
		this.notice = notice;
	}
}
