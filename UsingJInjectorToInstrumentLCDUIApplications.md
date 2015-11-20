# Introduction #
There are many examples of J2ME applications that use the
[LCDUI](http://java.sun.com/javame/reference/apis/jsr118/javax/microedition/lcdui/package-summary.html) library. JInjector includes classes ( [j2me](http://code.google.com/p/jinjector/source/browse/#svn/trunk/jinjector_mobile/decorators_src/com/google/test/jinjector/j2me) ) that facilitate programmatic testing of these applications, including the user-inteface.

This wiki page provides an overview of the steps required to automate tests using JInjector. Example tests are available from the [Downloads](http://code.google.com/p/jinjector/downloads/list) section of this project.

# Overview of the steps required #
Once you've set up the project (see the Common Stuff required section here) you should be able to build and run the project - worth doing before you start creating any system tests.

If you model your build scripts on those used in our examples (see the Downloads section and the directory structures in the jinjector\_mobile folder) the injected tests belong in the test\_to\_inject folder, in a parallel package to your main application.

JInjector uses a property file to control where (in the class structure) and what (e.g. the wrappers for LCDUI) to inject and include in the resulting jar file.

The build process will then preverify the injected code and package it into a jar file. The jad descriptor will be updated with the new size of the jar file (compared to the one that would be created without instrumentation).

_Note: these instructions are likely to change as we enhance the build and configuration scripts. Please visit this wiki from time to time to learn of the updates._

## Common stuff required ##
Here are the basic prerequisites required to instrument an LCDUI application.
  * Checkout the jinjector\_mobile code: svn checkout http://jinjector.googlecode.com/svn/trunk/jinjector_mobile .
  * ~~Download and copy j2meunit.jar into the lib folder~~ No longer required since [r30](http://code.google.com/p/jinjector/source/detail?r=30)
  * Copy source files for the application into src and any resource files into res
  * Create a suitable build.xml based on the [template](http://code.google.com/p/jinjector/source/browse/trunk/jinjector_mobile/build-yourprojectname.xml) and update the project name e.g. 

&lt;project name="NetClientMIDlet" default="run-WTK" basedir="."&gt;

 and machine specific settings e.g. the location of the WTK
  * Add a suitable MANIFEST.MF to the root folder - in the example build script the contents are also used to create the JAD file (they share a lot of properties).

# Wrapping the code #
LCDUI libraries cannot be sub-classed, JInjector provides [wrappers](http://code.google.com/p/jinjector/source/browse/#svn/trunk/jinjector_mobile/decorators_src/com/google/test/jinjector/j2me/wrapper) for the LCDUI classes which enable your tests to effectively query and interact with the wrappers as proxies for the original classes.

See [TimeMIDletExample](http://code.google.com/p/jinjector/wiki/TimeMIDletExample) for an example of an injected test for a simple web-based J2ME MIDlet.

# Resources #
The LinksAndResources wiki page includes a link to a video of a presentation on JInjector. The Downloads page includes links to presentations and other material that may be of interest.