# Introduction #
JInjector is a flexible, general purpose software tool that uses code instrumentation to improve testing and test reporting for a variety of software platforms. For JInjector we use the term 'Code instrumentation' to mean: Modification of object code to provide additional information and/or capabilities.

# Details #
JInjector was originally envisaged as a way to create and run end-to-end (system) tests for J2ME applications, since then support has been added to generate code coverage reports, and it supports other platforms, such as BlackBerry. Tests are written using J2MEUnit syntax, which makes tests easier to write. It allows applications to be tested through their UI (unlike the existing unit tests for J2ME or BlackBerry), and as a typical commercial-grade mobile application may consist of over 50% UI code enables significantly more of an application to be tested. It also supports multi-threaded applications, again many applications use multiple-threads, while few unit testing tools support multi-threaded code.

JInjector is complementary to unit testing frameworks such as [J2MEUnit](http://j2meunit.sourceforge.net/), and an alternative using ['Cobertura for J2ME'](http://www.cobertura4j2me.org/) for code coverage.

A comparison of JInjector and Cobertura is available in our GTAC presentation see LinksAndResources, the key points are: Both use code injection and Objectweb's ['ASM library'](http://asm.objectweb.org/). JInjector runs significantly faster and is optimized for runtime performance on resource-constrained devices. Cobertura generates slightly smaller versions of the injected application.


The code can be run in simulators or on devices.