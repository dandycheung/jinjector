# Introduction #

Here are a few notes and tips that may help you to use the Nokia S60 emulator for MIDP (Java ME applications).

# Tips #
Nokia's MIDP emulator can be used with JInjector. The following tips are related to the S60 3rd edition Feature Pack 1 SDK.

  * Full pathnames seem to be required for both the jad and jar files e.g.
C:\projects\cleanJInjector\timemidletexample\dist>C:\S60\devices\S60\_3rd\_MIDP\_SDK\_FP1\bin\emulator -Xverbose:all -Xdescriptor:C:\projects\cleanJInjector\timemidletexample\dist\TimeMIDlet.jad C:\projects\cleanJInjector\timemidletexample\dist\TimeMIDlet.jar -Xverbose:all -Xheapsize:128k
  * The documented heapsize of 1M is rejected, 128k is accepted, however the lower limit may restrict the complexity of the code you can run in the emulator. It can take a minute or more even on relatively powerful machines.
  * Some tips on how to fix 'Unhandled exceptions are available on Nokia's discussion forum http://discussion.forum.nokia.com/forum/showthread.php?t=121725&highlight=s60+java+emulator
  * be **patient** while waiting for the emulator to first load, then start your application.
  * [How to install the SDK and related tools](http://wiki.forum.nokia.com/index.php/Installing_Java_ME_development_tools_for_S60) on the Nokia discussion forum.
  * Nokia has documented how to capture various outputs, including System.out, for 3rd Edition devices, using a MIDP utility. This is very helpful if you want to obtain debug messages from devices (it also works in the emulator). See http://wiki.forum.nokia.com/index.php/How_to_get_System.out_output_from_a_MIDlet_and_save_it_to_a_file_in_S60_devices
