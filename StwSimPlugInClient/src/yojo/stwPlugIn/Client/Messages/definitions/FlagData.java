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
	
	/**
	 * true if the A flag is present.
	 * The A flag allows for instant departures at the platform
	 */
	public final boolean A;
	/**
	 * 0 if the B flag is not present. between 1 to 9 (including) else, depending on the flag
	 * The B flag is used by theme scripts to change certain behaiviors
	 */
	public final byte B;
	/**
	 * true if the D flag is present
	 * with the D flag a train will not stop at the platform (also not triggering a arrival event)
	 */
	public final boolean D;
	/**
	 * null if the E flag is not present
	 * Else this contains the train id of the new train and a counting number of the flag (0 if the flag has none)
	 * The E flag gives the train a new train id and name
	 */
	public final E_Args E;
	/**
	 * null if the F flag is not present
	 * Else this contains the train id of the new train and a counting number of the flag (0 if the flag has none)
	 * The F flag splits the train into itself and a new train
	 */
	public final EKF_Args F;
	/**
	 * null if the K flag is not present
	 * Else this contains the train id of the other train and a counting number of the flag (0 if the flag has none)
	 * The K flag combines this train with another
	 */
	public final EKF_Args K;
	/**
	 * true if the L flag is present
	 * with the L the train engine will move to the other end of the train
	 */
	public final boolean L;
	/**
	 * null if the P falg is not present
	 * else this contains the direction argument of the p flag or none if not present
	 * the P flag allows for the train to spawn of the platform at play start and the direction dictates the train direction on spawn
	 */
	public final P_Args P;
	/**
	 * true if the R flag is present
	 * with the R flag changes teh direction of the train
	 */
	public final boolean R;
	/**
	 * null if the W falg is not present
	 * else this contains the enr's of the entry and exit of the engines without an order
	 * the W flag initiates an engine swap. The new engine comes from the entry and the old one leaves through the exit
	 */
	public final W_Args W;
	
	public FlagData(String flags, boolean a, byte b, boolean d, E_Args e, 
			EKF_Args f, EKF_Args k, boolean l, P_Args p, boolean r, W_Args w) {
		this.flags = flags;
		this.A = a;
		this.B = b;
		this.D = d;
		this.E = e;
		this.F = f;
		this.K = k;
		this.L = l;
		this.P = p;
		this.R = r;
		this.W = w;
	}
	
	
	@Override
	public String toString() {
		return flags;
	}

	public static class EKF_Args{
		public final byte multiCount;
		public final int zid;
		
		public EKF_Args(byte multiCount, int zid) {
			this.multiCount = multiCount;
			this.zid = zid;
		}
	}
	
	public static class E_Args extends EKF_Args{
		public final P_Args direction;
		
		public E_Args(byte multiCount, int zid, P_Args direction) {
			super(multiCount, zid);
			this.direction = direction;
		}
		
	}
	
	public static class W_Args{
		public final int enr1;
		public final int enr2;
		
		public W_Args(int enr1, int enr2) {
			this.enr1 = enr1;
			this.enr2 = enr2;
		}
	}
	
	public static enum P_Args{
		NONE,
		UP,
		DOWN,
		RIGHT,
		LEFT;
	}
}
