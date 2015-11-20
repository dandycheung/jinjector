# Introduction #

The 6th Edition of the S40 emulator provides some very useful debugging and tracing features. Here are a few notes on using it with Injected MIDlets. We're only scratching the surface here, please add comments and tips and we'll factor them into these notes.

We used v1.0 of the 6th Edition of the S40 SDK, available from Nokia's web site try http://www.forum.nokia.com/info/sw.nokia.com/id/cc48f9a1-f5cf-447b-bdba-c4d41b3d05ce/Series_40_Platform_SDKs.html

# Details #

## Loading a MIDlet into the emulator ##
Start the emulator and answer any prompts until the UI of a Nokia homescreen is visible on screen. Use the File->Open menu and navigate to the dist folder for the instrumented MIDlet. Then pick the JAD file and load it. You should see the familiar prompts when installing a MIDlet on a real phone.

Note: I'm assuming you've built and instrumented the application beforehand e.g. using the ant script with Sun's WTK and emulator.

## Permissions ##
Set the security to maximum or trusted in the preferences menu.

## Logging ##
The emulator allows you to log all sorts of things including network responses. By judiciously enabling various settings you may be able to learn more about how the instrumented code is executing.

## Finding the coverage results file ##
Details are available in the online help when you've installed the SDK. Assuming you've installed it in the default location, details are available on your local machine using the following URL [file:///C:/Nokia/Devices/S40_6th_Edition_SDK/doc/WebHelp/UserGuide/working_with_the_pc_file_system.htm](file:///C:/Nokia/Devices/S40_6th_Edition_SDK/doc/WebHelp/UserGuide/working_with_the_pc_file_system.htm)

To save time, here's the path structure
` <SDK installation directory>\bin\Storage\<instance identifier> `
Replace the parameters in <...> to suit your machine. The instance identifier is visible when the emulator starts in the title area of the emulator application e.g. 6260000 which represents a phone number for the emulated device I guess...

JInjector currently tries to write the coverage file to the E:/ folder which represents an SD-Card (so don't disable the SD-Card in the emulator if you want to get coverage data!). There are restrictions on Nokia devices and in their emulators to prevent applications from writing to sensitive folders e.g. `C:/` or `C:/Data`. Sun's WTK emulator uses folders starting with `/root` e.g. `/root1` and doesn't restrict access to any of the folders AFAIK.