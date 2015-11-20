# FAQ - Frequently Asked Questions #

This is a collection of all the questions we have been receiving.

### Where _is_ my coverage file? Addressing common environment issues ###
If you can't find the coverage file, here are some suggestions which may help you to resolve the issues and to locate the file. The comments are specifically for Sun's emulator, which seems to be used more than platform-specific emulators (such as the Nokia ones).

When the emulator is started, a specific 'skin' is used e.g. the DefaultColorPhone. This may be the default value or specified in a command-line option. On Microsoft Windows operating systems the WTK creates working directories in the user's home folder e.g. for Windows XP they are in ` C:\Documents and Settings\ _the current user name_ \j2mewtk `. A sub-directory is created based on the version of the WTK e.g. for version 2.6 of the WTK it would be ` C:\Documents and Settings\ _the current user name_ \j2mewtk\2.6 ` and finally, the default folder for a clean environment when using the DefaultColorPhone is ` C:\Documents and Settings\ _the current user name_ \j2mewtk\2.6\appdb\DefaultColorPhone `

**Note** replace _the current user name_ with your current logged-in user name in the paths shown above

Within the directory for the specified skin ` filesystem\root1 ` is mapped to the root of the running J2ME emulator.

When jinjector is used to collect coverage it will create the lcov coverage results file in the root of this directory. For instance if I were logged in with a user name of joe the full path of the lcov file would be ` C:\Documents and Settings\joe\j2mewtk\2.6\appdb\DefaultColorPhone\filesystem\root1\timemidletcoverage.lcov1 `

Now I've explained how things _should_ be, there are several reasons why you may not be able to find the file where / when you expect it. Here are some possible causes and fixes

  1. Temporary versions of folders for the specified skin: if the emulator finds an in.use file for a particular skin, it will create a unique, numbered temporary directory named as temp.DefaultColorPhone55 (55 is an example, the actual number is likely to be different). By cleaning up the DefaultColorPhone directory, while the emulator is not running, you should find the file is now in the expected location. See http://stackoverflow.com/questions/538167/j2mewtk-adding-files-to-defaultcolorphone-temp-settings and http://forums.sun.com/thread.jspa?threadID=5250026 for more information about this class of problem.

Notes:
  * I think the temporary folders are sometimes purged when the emulator quits, which would mean the lcov file would also be deleted when it quits :(
  * The textual output from running the Ant script should help you to determine whether the coverage file was generated, and if so it will include the folder name and file name of the coverage file.

### Why does Ant report the following error: "Buildfile: build.xml does not exist!"? ###

No target file has been specified. use "ant -f <build-filename.xml>"

### Why does the collected coverage file coverage.lcov is empty? ###

This happens when no class has been included in the coverage list. In the properties file, under the coverage loadable edit the following property by inserting your package name:

```
coverageInclusionList=+org/package/subpackagetoinclude 
```

You can use "+" and "-" to include or exclude packages:

```
coverageInclusionList=+org/package/toinclude -org/package/toexclude
```

### What is a Loadable and how do I create one? ###

A Loadable
http://code.google.com/p/jinjector/source/browse/trunk/jinjector_tool/src/com/google/devtools/build/wireless/testing/java/injector/Loadable.java
is a component which may be loaded at runtime by JInjector to
instrument your code.

Loadables need to be specified in the properties file
http://code.google.com/p/jinjector/source/browse/trunk/jinjector_mobile/jinjector-default.properties
and JInjector loads and apply them in the same order as they are
specified.

To implement a Loadable you need to consider 3 different aspects:
  1. What your component should do BEFORE the instrumentation. This are usually initializations or actions which strictly need to be performed before any other instrumentations. This kind of pre-operations are not very common and depends in what are you trying to achieve. An example could be initializing a collection for counting or measuring the number of instrumented classes, or the number of invocation of a certain method.
  1. The instrumentation itself, which usually consist in extending a ClassAdapter and a MethodAdapter which are two visitors one for the class structure one for the method body. See http://asm.objectweb.org/ for a detailed tutorial.
  1. What your component should do at the end of the instrumentation. A sort of final operation after all the other classes have been instrumented.

Loadables can read properties directly from the properties file.
See
http://code.google.com/p/jinjector/source/browse/trunk/jinjector_tool/src/com/google/devtools/build/wireless/testing/java/injector/ReplayLoadable.java
which is a good example, simple and clear or
http://code.google.com/p/jinjector/source/browse/trunk/jinjector_tool/src/com/google/devtools/build/wireless/testing/java/injector/coverage/CoverageLoadable.java
which is a more complete example.

### Which version of the JDK do we need to use? ###
JInjector is written using features from Java 6 so you need version 6 (or newer) of the JDK. We've tested it with Open JDK and with Sun's JDK.


### Why does genhtml cannot find my classes? ###

Genhtml requires each single java file to be listed using an absolute path or a path relative from where the command is executed. JInjector guess the java file name and location using class and package name (and debug informations). However JInjector cannot guess the position of your java class in your file system.

If the source files that you have included for coverage comes from the same directory you can run genhtml from that directory and it will work.

If your source files comes from multiple directory (e.g. _src_ and _tests_) then it will be necessary to pre-process the lcov file and replacing the guessed path with the real one. This can be done with a shell script.