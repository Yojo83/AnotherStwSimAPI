# Another StwSim-PlugIn API (ASA)

This is another Java Libary to Communicate to a game of [Stellwersimulator](stellwerksim.de) via the PlugIn API. It can be used to create PlugIns for it. 
But whats spezial about this API? Its from me and therefore perfekt!

## Contents

In all Releases you'll find two .jar files for using this. One as a lib and the other as a tester. 
The lib has everthing you need to get startet with programming your PlugIn. 
The Tester on the otherhand additionally consists of a little Programm to test the api connection. 
You can also extend on it to see how everthing is working.

## How To use

### Init
After importing the lib one just instatiates the PlugInClient Class and use its connect method.

```Java
PlugInClient client = newPlugInClient("name", "author", "version", "description");
client.connect();
```

now your client is connected to your game. At least if there is one, else there is an IO Exception thrown.

You can also define the host in the ```connect(String host)``` method.

### sending messages

with your connected Client you can use its methods to request Information from the game or register an event:

```Java
  public void registerEvent(int trainId, EventType type) throws IOException {
		sendMessageRaw("<ereignis zid=\"" + trainId + "\" art=\"" + type.forXml + "\"/>\n");
	}
	
	public void requestHeat() throws IOException {
		sendMessageRaw("<hitze />\n");
	}
	
	public void requestStitz() throws IOException {
		sendMessageRaw("<stitz />\n");
	}
	
	public void requestStructure() throws IOException {
		sendMessageRaw("<wege />\n");
	}
	
	public void requestElement4(int enr) throws IOException {
		sendMessageRaw("<enr2element enr=\"" + enr + "\" />\n");
	}
	
	public void requestEnr4(String element) throws IOException {
		sendMessageRaw("<element2enr element=\"" + element + "\" />\n");
	}
	
	public void requestSetFS(int enr1, int enr2) throws IOException {
		sendMessageRaw("<setfs start=\"" + enr1 + "\" stop=\"" + enr2 + "\" />\n");
	}
	
	public void requestDebugMode(boolean enabled) throws IOException {
		sendMessageRaw("<debug mode=\"" + enabled + "\" />\n");
	}
	
	public void requestTrainSchedule(int trainId) throws IOException {
		sendMessageRaw("<zugfahrplan zid=\"" + trainId + "\" />\n");
	}
	
	public void requestTrainDetails(int trainId) throws IOException {
		sendMessageRaw("<zugdetails  zid=\"" + trainId + "\" />\n");
	}
	
	public void requestTrainList() throws IOException {
		sendMessageRaw("<zugliste />\n");
	}
	
	public void requestPlattformList() throws IOException {
		sendMessageRaw("<bahnsteigliste />\n");
	}
	
	public void requestSystemInfo() throws IOException {
		sendMessageRaw("<anlageninfo />\n");
	}
	
	public void requestSimTime() throws IOException{
		sendMessageRaw("<simzeit sender='0' />\n");
	}
```

You can also use ```public void sendMessageRaw(String XmlMessage)``` to send a message direktly to the PlugIn Interface of the simulator.
All of those Messages return after sending the respective Message or throw an IOException.

### Receive Messages

To read incoming Messages you need to set a ResponseListener:
```Java
class MyResponseListener extends ResponseListener{
  
}

MyResponseListener listener = new MyResponseListener();
client.setResponseListener(lsitener);
```
In this Example you can process received Information by overriding a method in MyResponseListener for the specified Mesage.
An unidentifyable Message (wich also include error and connected messages) is processable by the mehtod ```unhandeledMessage(ResponseMessage msg)```.
If you don't override the method of a method, those messages wil also call ```unhandeledMessage(ResponseMessage msg)```.
You can differentiate between those two, by checking the message for the type. the Raw type means it can't be parsed to any existing type.

### DEBUGGER

Additonally the Lib has a DEBUGGER. In debug mode it print all debug statements in the console, and with printLog it prints all debug statements to an OutputStream.

```Java
DEBUGGER.debugMode = true; //activate debug mode
DEBUGGER.printLog(System.out); //print all log statements to the console
```

## Appendum

### other APIs

I know there are other APIs for this meaning out there, but there not up to date or not working quite as they should. Therefore i made this one

### contribution

this code is far away from perfect, also if the infeace updates this will leaf some legacy. 
You are invited to participate in this code with idears and implementations.

### further disclaimer

this my first public repo, so if anythings weird, missing or something, please reach out to me. I want to improve
