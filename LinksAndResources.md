# Introduction #

Here are links to various web sites related to JInjector.


# Details #

Please join the jinjector group http://groups.google.com/group/jinjector?hl=en if you would like to ask questions, submit suggestions, and examples, etc.

## Resources on JInjector ##
[GTAC Video on JInjector](http://www.youtube.com/watch?v=B2v5jQ9NLVg) We presented JInjector at Google's Test Automation Conference in Seattle in October 2008. The video is available online and may help provide an overview of our work.

http://portal.acm.org/citation.cfm?id=1514411.1514424 is the official link to the paper presented at HotMobile 2009. A copy will shortly be available for personal use from the Downloads section of this site (once I've reinstalled the software to generate the document from the latex source text files).

## Software used by JInjector ##
### Build tools ###
We use [Ant](http://ant.apache.org/) to build the code.
If you use the Eclipse IDE then [EclipseME](http://eclipseme.org/docs/index.html) enables you to build, run and debug J2ME applications in the IDE.

You might also be interested in using
[Antenna](http://antenna.sourceforge.net/), an add in tasks to Ant which includes additional support for J2ME development.

### Run-time requirements ###
The JInjector tool uses
  * [ObjectWeb ASM](http://asm.objectweb.org/) the library used to perform the code injection in JInjector.
  * [Google Collections](http://code.google.com/p/google-collections/) used by the JInjector tool.
  * [EasyMock](http://prdownloads.sourceforge.net/easymock/easymock2.4.zip) and the related [EasyMock extension](http://prdownloads.sourceforge.net/easymock/easymockclassextension2.4.zip) assist with testing JInjector
  * Junit 3.8.1 has been used as the test runner.

Running end-to-end tests
  * [J2MEUnit](http://j2meunit.sourceforge.net/) is used as the test runner for JInjector.
  * genhtml, from [Lcov](http://ltp.sourceforge.net/coverage/lcov.php) is a practical way to generate graphs of the code coverage results. It's part of the Linux Tools Project. As genhtml is a perl script it can also be used on Microsoft Windows platforms.

### Miscellaneous software ###
[Sun's Java Wireless Toolkit (WTK)](http://java.sun.com/products/sjwtoolkit/overview.html) is not actually required for JInjector, however it's the de facto implementation and our sample build scripts run the tests in the emulator from the WTK. It's commonly used to build and test J2ME applications, and used in the JInjector samples

See also [Sun's homepage for Java ME](http://java.sun.com/javame/index.jsp) also known as J2ME for background information.

## Related work ##
[Cobertura for J2me](http://www.cobertura4j2me.org/) An alternative to JInjector for code coverage.

JInjector shares some common ideas with Robot ME, e.g. using ObjectWeb's ASM library to automate tests for J2ME applications. See http://www.cs.put.poznan.pl/dweiss/site/publications/slides/robotme-idss.pdf and http://www.mobiledeveloper.pl/files/AutomatedGUITestingOfMobileJavaApplications.pdf or search for RobotME to find other related material.

## General test automation articles ##
[Using J2MEUnit and Eclipse](http://efforts.embedded.ufcg.edu.br/javame/?p=11) A good article on the subject. One additional tip, set the full package+class as the argument for j2meunit e.g. `org.mydomain.mypackage.AllInjectedTestsSuite`