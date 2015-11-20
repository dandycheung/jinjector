# Introduction #

Sun Microsystems has released the Java ME SDK 3.0 for the Microsoft Windows platforms in the spring of 2009, see http://java.sun.com/javame/downloads/sdk30.jsp  which includes some significant changes to the toolkit. Here are some notes on relevant changes which will help you to use JInjector with it. These notes are based on our experiments and you may well discover other relevant issues - if so, please send a message to the JInjector user group or comment directly to this wiki entry.

# Details #
You will need to update your ant script and your jinjector properties file with the new paths and jar filenames.

To get you started, we've added an additional 'update' file to the Downloads section of this site [here](http://jinjector.googlecode.com/files/timemidlet_updates_for_sun_javame_sdk_3_0%20%2801%20Jun%202009%29.zip)

FYI: Details of typical changes follow:

## Default directory ##
The default directory is now `C:\Java_ME_platform_SDK_3.0`

## Changes to jar files ##
  * All of the names now have a version number embedded in them, and some have the names changed e.g. midpapi20.jar is superceeded by midp\_2.0.jar
  * mmapi.jar is superceeded by jsr135\_1.1.jar
  * wma20.jar is superceeded by jsr205\_2.0.jar
  * j2me\_ws.jar is missing and we haven't found equivalent classes in 3.0

The readme file for the Java ME 3.0 SDK contains a list of the documentation for various JSRs which may help you to identify which libraries to include. If you install it in the default folder the file is here [file:///C:/Java_ME_platform_SDK_3.0/index.html](file:///C:/Java_ME_platform_SDK_3.0/index.html)

## Location of the coverage files ##
{{{C:\Documents and Settings\_yourusername_\javame-sdk\3.0\work\6\appdb\filesystem\root1}}

Replace _yourusername_ with your current login name to find the coverage file. The emulator might create and use another sub-directory (probably within javame-sdk\3.0\work) if previous directories are 'locked'. so we recommend you search for the coverage file e.g. `timemidletcoverage.lcov1` from C:\Documents and Settings\_yourusername_ if it isn't where you expect it.

## Changes to the emulator.exe command line parameters ##
  * The memory setting is no longer documented. - You can probably remove it (we did).
  * The names of the emulated devices have changed e.g. DefaultColorPhone doesn't exist anymore. You can obtain the full list of supported devices within the new IDE `Java(TM) ME Platform SDK 3.0`. To get you started, try something like `<property name="emulator.device" value="DefaultCldcPhone1" />` in your ant script.