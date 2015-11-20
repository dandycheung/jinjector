# Introduction #

The original TimeMIDlet J2ME application is available online from Sun’s website. We’ve added some injected tests for it and made some tweaks to the original code to help you to learn more about the application and enable you to run it easily on phones. Details of the changes are in the javadoc in the main TimeMIDlet.java file.

The tests-to-inject consists of a single test and a test suite to run the test(s) with J2MEUnit. If you retain the current property settings in jinjector.timemidlet.properties the test should run when the MIDlet is started and code coverage data will be saved when the application is exited and/or the emulator quit.

# Integrating the sample project #
You will need to download and unpack TimeMIDlet into an fresh instance of the jinjector\_mobile project. The jinjector\_mobile project contains the rest of the files required for jinjector to work.

# Building and running the tests #
Please check the path references to Sun’s WTK in both build-timemidlet.xml (the Ant script) and jinjector.timemidlet.properties to make sure they point to where you have the WTK installed.

The Ant script should compile, build, inject and prepare TimeMIDlet.jar and TimeMIDlet.jad. These will be loaded ready to run in Sun’s emulator (see screenshot)

The test suite (of 1 injected test) will take a few seconds to run. For the test to complete successfully network connectivity is required to contact a Network Time Server using the standard NTP (Network Time Protocol) which uses IP port 13 (in case your firewall wants to ask permission and/or needs to be configured). If all goes well an empty screen is displayed (the title is Errors and Failures and there are 2 soft keys available: Select and Back). If the test fails you will be able to select it to display more detail. The Back softkey will return you to the previous screen which should have the current time in UTC. From this screen you can exit the application. Then shutdown the emulator to allow the ant script to complete. The log from the Ant script should contain the path to the emulated device e.g.
`[exec] Running with storage root C:\Documents and Settings\jharty\j2mewtk\2.6\appdb\DefaultColorPhone `
and the name of the coverage file e.g.
`[exec] [com.google.test.jinjector.coverage.CoverageLcovWriter]LCOV file [file://localhost/root1/timemidletcoverage.lcov1] written in 62ms`

The coverage file is in a popular open-source format known as LCOV and looks similar to:
{{{SF:example/TimeMIDlet.java
DA:28,0
…
DA:40,0
DA:41,0
DA:44,1
DA:45,1
DA:48,0
DA:49,0
…
DA:116,0
DA:128,1
LH:31
LF:57
end\_of\_record}}}

The last column value of 1 means the line was executed / covered, 0 means the line was not executed / covered. [the tool records when a line is executed, which may be for various reasons, apart from by the tests. However generally people assume a line was covered by a test when it’s executed](Strictly.md).

# Generating a coverage report #
genhtml is a perl program that can generate a visual coverage graph using the coverage file if it has access to the source code when processing the file. A quick, effective – if messy – hack is to copy the coverage file into the src directory, then run genhtml by calling it with perl e.g.
`perl genhtml`
This is messy as it creates the output files in the current directory by default. The program provides various command-line options which allows you to write the HTML output files, etc to a separate directory etc.

# Running the injected app in other emulators and on devices #
The jad and jar combination are in the dist folder. These can be run in other emulators e.g. in the Nokia Series 40 SDK and even on physical devices e.g. the Nokia N95. Most emulators support similar command-line parameters to the ones used by Sun’s WTK emulator.

To deploy the software on devices, try to deploy them directly from your computer as you would deploy other J2ME applications. Although the application might run if you install using the JAR file directly, using the JAD file is good practice. You can modify the JAD file to support device-specific parameters e.g. to map soft-keys on SonyEricsson devices.

As coverage results are written to the local filesystem, and as J2ME has a fairly strong security model, you may have to experiment with security settings before the application generates a coverage file. Typically run emulators with maximum or manufacturer permission to minimize the number or prompts and issues. As devices vary significantly in their security settings and requirements I wouldn’t try to tell you how to configure them and/or the application files here. My general advice is to say yes to permission requests and hope the application will be allowed to write to the filesystem. We’re working to find more flexible ways to ensure the results are able to be recorded e.g. over HTTP to a web server of your choice. As JInjector is open-sourced you’re welcome to implement your own solution 