package yojo.stwPlugIn.Client.Messages.definitions;

public class ScheduleEntry {
	
	public final String plattform;
	public final String regularPlattform;
	public final long arrival;
	public final long departure;
	public final FlagData flags;
	
	public ScheduleEntry(String plattform, String regularPlattform, 
			long arrival, long departure, 
			FlagData flags) {
		this.plattform = plattform;
		this.regularPlattform = regularPlattform;
		this.arrival = arrival;
		this.departure = departure;
		this.flags = flags;
	}
	
	
	@Override
	public String toString() {
		return "<gleis ab='" + departure + "' name='" + plattform + "' flags='" + flags.toString() 
				+ "' plan='" + regularPlattform + "' an='" + arrival + "' />";
	}
}
