package yojo.stwPlugIn.Client.Messages.definitions;

/**
 * An entry in the train schedule, representing one platform of the journay
 * @author Yojo
 *
 */
public class ScheduleEntry {
	
	/**
	 * the actual platform this train will drive through or stops.
	 * This includes changes from the player
	 */
	public final String plattform;
	/**
	 * the planned platform this train will drive through or stops.
	 * This doesn't include changes from the player.
	 */
	public final String regularPlattform;
	/**
	 * the planned arrival at this platform in millis after 00:00
	 */
	public final long arrival;
	/**
	 * the planned departure at this platform in millis after 00:00
	 */
	public final long departure;
	/**
	 * the flags set for this platform
	 */
	public final FlagData flags;
	/**
	 * the advisory text for this plattform
	 */
	public final String text;
	
	public ScheduleEntry(String plattform, String regularPlattform, 
			long arrival, long departure, 
			FlagData flags, String text) {
		this.plattform = plattform;
		this.regularPlattform = regularPlattform;
		this.arrival = arrival;
		this.departure = departure;
		this.flags = flags;
		this.text = text;
	}
	
	
	@Override
	public String toString() {
		return "<gleis ab='" + departure + "' name='" + plattform + "' flags='" + flags.toString() 
				+ "' plan='" + regularPlattform + "' an='" + arrival + "' />";
	}
}
