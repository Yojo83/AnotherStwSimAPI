package yojo.stwPlugIn.Client.Messages.definitions;

/**
 * the type of a response message.
 * The message can be casted based on this.
 * @author Yojo
 *
 */
public enum ResponseType {
	Systeminfo,
	Plattformlist,
	Trainlist,
	Traindetails,
	Trainschedule,
	Event,
	Heat,
	Stitz,
	Structure,
	FsSet,
	EnrFromElement,
	ElementFromEnr,
	Time,
	Raw;
}
