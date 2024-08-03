package yojo.stwPlugIn.Client.Messages.definitions;

/**
 * This represents a combination of trains.
 * Since i didn't understood this too well it is handled very raw
 * @author Yojo
 *
 */
public class FlagData {

	/**
	 * a string representing the flags (exactly like in the xml)
	 */
	public final String flags;
	
	
	public FlagData(String flags) {
		this.flags = flags;
	}
	
	
	@Override
	public String toString() {
		return flags;
	}
}
