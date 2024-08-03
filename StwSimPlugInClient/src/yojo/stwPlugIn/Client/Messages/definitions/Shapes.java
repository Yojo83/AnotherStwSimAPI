package yojo.stwPlugIn.Client.Messages.definitions;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * A class used for structuring the access to the different shapes.
 * This contains unmodifiable lists for each shape type
 * @author Yojo
 *
 */
public class Shapes {


	/**
	 * an unmodifiable list of plattforms
	 */
	public final List<Plattform> plattforms;
	/**
	 * an unmodifiable list of all entrys
	 */
	public final List<Entry> entrys;
	/**
	 * an unmodifiable list of all exits
	 */
	public final List<Exit> exits;
	/**
	 * an unodifiable list of all signals
	 */
	public final List<Signal> signals;
	/**
	 * an unsomidiable list of all switches
	 */
	public final List<Switch> switches;
	
	/**
	 * this unmodifiable map contains all shapes, mapped on with their identifier.
	 * for platforms this is their name, for all other shapes their enr
	 */
	public final Map<String, Shape> idToShape;
	
	
	public Shapes(List<Switch> switches, List<Signal> signals, List<Plattform> plattforms, 
			List<Exit> exits, List<Entry> entrys, Map<String, Shape> idToShape) 
	{
		this.plattforms = Collections.unmodifiableList(plattforms);
		this.entrys = Collections.unmodifiableList(entrys);
		this.exits = Collections.unmodifiableList(exits);
		this.signals = Collections.unmodifiableList(signals);
		this.switches = Collections.unmodifiableList(switches);
		this.idToShape = Collections.unmodifiableMap(idToShape);
	}
	
	
	/**
	 * executes a function for all shapes in order:
	 * Platforms, Entrys, Exits, Signals, Switches
	 * @param func the function to execute
	 */
	public void foreach(Consumer<Shape> func) {
		for(Plattform p : plattforms)
			func.accept(p);
		for(Entry e : entrys)
			func.accept(e);
		for(Exit e : exits)
			func.accept(e);
		for(Signal s : signals)
			func.accept(s);
		for(Switch s : switches)
			func.accept(s);		
	}
	
	/**
	 * An enum to represent the type of a shape. the shape can be parsed on this.
	 * Also this contains the xml type of the shape
	 * @author Yojo
	 *
	 */
	public static enum ShapeType{
		Signal(2),
		SwitchDown(3),
		SwitchUp(4),
		Plattform(5),
		Entry(6),
		Exit(7),
		Stopp(12);
		
		/**
		 * the xml id for this shape
		 */
		public final int asInt;
		
		private ShapeType(int type) {
			asInt = type;
		}
		
	}
	
	/**
	 * An abstract class for shapes
	 * @author User
	 *
	 */
	public static abstract class Shape{
		
		/**
		 * the type of the shape
		 */
		public final ShapeType type;
		
		public Shape(ShapeType type) {
			this.type = type;
		}
		
		/**
		 * returns the name of the shape in readable form
		 * @return the name of the shape
		 */
		public abstract String getName();
		
		@Override
		public abstract String toString();
	}
	
	/**
	 * An Shape that is a plattform (or stop)
	 * @author User
	 *
	 */
	public static class Plattform extends Shape {
		
		/**
		 * if this is a stop or a platform in a train station
		 */
		public final boolean isStop;
		/**
		 * the name of the paltform
		 */
		public final String name;
		
		public Plattform(String name, boolean isStop) {
			super(isStop ? ShapeType.Stopp : ShapeType.Plattform);
			this.isStop = isStop;
			this.name = name;
		}

		@Override
		public String toString() {
			return "<shape name='" + name + "' type='" + (isStop ? "12" : "5") + "' />";
		}

		@Override
		public String getName() {
			return name;
		}
		
	}
	
	/**
	 * A shape that is a entry, where trains can enter the signal box
	 * @author User
	 *
	 */
	public static class Entry extends Shape {

		/**
		 * the name of the entry
		 */
		public final String name;
		/**
		 * the enr of the entry
		 */
		public final int enr;
		
		public Entry(String name, int enr) {
			super(ShapeType.Entry);
			this.name = name;
			this.enr = enr;
		}

		@Override
		public String toString() {
			return "<shape name='" + name + "' enr='" + enr + "' type='6' />";
		}

		@Override
		public String getName() {
			return name;
		}
	}

	/**
	 * A shape that is a exit, where trains can exit the signal box
	 * @author User
	 *
	 */
	public static class Exit extends Shape {

		/**
		 * the name of the exit
		 */
		public final String name;
		/**
		 * the enr of the exit
		 */
		public final int enr;
		
		public Exit(String name, int enr) {
			super(ShapeType.Exit);
			this.name = name;
			this.enr = enr;
		}

		@Override
		public String toString() {
			return "<shape name='" + name + "' enr='" + enr + "' type='7' />";
		}

		@Override
		public String getName() {
			return name;
		}
	}

	/**
	 * A Shape that is a signal
	 * @author Yojo
	 *
	 */
	public static class Signal extends Shape {

		/**
		 * the signal name
		 */
		public final String name;
		/**
		 * the ner of the signal
		 */
		public final int enr;
		
		public Signal(String name, int enr) {
			super(ShapeType.Signal);
			this.name = name;
			this.enr = enr;
		}

		@Override
		public String toString() {
			return "<shape name='" + name + "' enr='" + enr + "' type='2' />";
		}

		@Override
		public String getName() {
			return name;
		}
	}
	
	/**
	 * A Shape representing a switch
	 * @author Yojo
	 *
	 */
	public static class Switch extends Shape {

		/**
		 * the name of the switch
		 */
		public final String name;
		/**
		 * the enr of the switch
		 */
		public final int enr;
		/**
		 * if this switch is turned up or down
		 */
		public final boolean isUp;
		
		public Switch(String name, int enr, boolean isUp) {
			super(isUp ? ShapeType.SwitchUp : ShapeType.SwitchDown);
			this.name = name;
			this.enr = enr;
			this.isUp = isUp;
		}

		@Override
		public String toString() {
			return "<shape name='" + name + "' enr='" + enr + "' type='" + (isUp ? "4" : "3") + "' />";
		}

		@Override
		public String getName() {
			return name;
		}
	}
	
	
}
