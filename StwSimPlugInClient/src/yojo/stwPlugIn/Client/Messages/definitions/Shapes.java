package yojo.stwPlugIn.Client.Messages.definitions;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Shapes {


	public final List<Plattform> plattforms;
	public final List<Entry> entrys;
	public final List<Exit> exits;
	public final List<Signal> signals;
	public final List<Switch> switches;
	
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
	
	public static enum ShapeType{
		Signal(2),
		SwitchDown(3),
		SwitchUp(4),
		Plattform(5),
		Entry(6),
		Exit(7),
		Stopp(12);
		
		public final int asInt;
		
		private ShapeType(int type) {
			asInt = type;
		}
		
	}
	
	public static abstract class Shape{
		
		public final ShapeType type;
		
		public Shape(ShapeType type) {
			this.type = type;
		}
		
		@Override
		public abstract String toString();
	}
	
	public static class Plattform extends Shape {
		
		public final boolean isStop;
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
		
	}
	
	public static class Entry extends Shape {

		public final String name;
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
	}
	
	public static class Exit extends Shape {

		public final String name;
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
	}

	public static class Signal extends Shape {

		public final String name;
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
	}
	
	public static class Switch extends Shape {

		public final String name;
		public final int enr;
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
	}
	
	
}
