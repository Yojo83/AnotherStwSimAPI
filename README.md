# Another StwSim-PlugIn API (ASA)

This is another Java Libary to Communicate to a game of [Stellwersimulator](stellwerksim.de) via the PlugIn API. It can be used to create PlugIns for it. 
But whats spezial about this API? Its from me and therefore perfekt!

## Contents

Besides the SourceCode and some overhead this repo has two .jar files. One as a lib and the other as a tester. 
The lib has everthing you need to get startet with programming your PlugIn. 
The Tester on the otherhand additionally consists of a little Programm to test the api connection. 
You can also extend on it to see how everthing is working.

## How To use

After importing the lib one just instatiates the PlugInClient Class and use its connect method.

´´´Java
PlugInClient client = newPlugInClient("name", "author", "version", "description");
client.connect();
´´´

now your client is connected to your game. At least if there is one, else there is an IO Exception thrown.

You can also define the host in the ´´´Java connect(String host)´´´ method.

Additonally the Lib has a DEBUGGER. In debug mode it print all debug statements in the console, and with printLog it prints all debug statements to an OutputStream.

´´´Java
DEBUGGER.debugMode = true; //activate debug mode
DEBUGGER.printLog(System.out); //print all log statements to the console
´´´

## Appendum

I know there are other APIs for this meaning out there, but there not up to date or not working quite as they should. Therefore i made this one
