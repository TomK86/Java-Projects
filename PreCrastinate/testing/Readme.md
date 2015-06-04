Testing
=======
Final Project for CSCI 3308 (Software Development Methods & Tools) - Summer 2014

Created by: Matt Comerford, Matt Hong, Tom Kelly, & Laura Matuszewska

Running Unit Tests on PreCrastinate
===================================

Precrastinate is tested using JUnit - a simple framework for writing repeatable unit tests. In order to test this code, the user must have previously installed Eclipse and the Eclipse Android Emulator. Testing may be done within Eclipse by following these steps:
- Expand the desired project in the Package Explorer.
- Select the desired testing file located in the project directory at *PreCrastinate* -> *testing* -> *com.csci3308.precrastinate*.
- Go to Run the Project at *Run* -> *Run as* -> *Android JUnit Test*. This will launch the Android Emulator and run the test file.
- Upon completion of the testing, the *JUnit* view will appear in the Eclipse window with the testing results.
  - If *JUnit* view does not appear in the Eclipse window, it can be opened by selecting *Window* -> *Show View* -> *Other...*. This will open the *Show View* wizard, where you will select *Java* -> *JUnit* and select *OK*.
- The user may then view the results of his/her testing, re-run the entire test or only those tests that previously had failed, or select a new test to run. 
- These steps may be followed for running any subsequent or added testing files.


Adding New Testing Files to PreCrastinate
=========================================

The user may find him/herself wanting to add new tests to the PreCrastinate project. This can be done by:
- Selecting the source file the user desires to test, located in the project directory at *PreCrastination* ->  *src* -> *com.csci3308.precrastinate*.
- Right-clicking on the file and selecting *New* -> *JUnit Test Case*, which opens the "New JUnit Test Case" wizard
- Within the wizard.
- Set the fields to your desired specifications.
  - Select *Next* and select the desired functions which you would like to test.
  - Select *Finish*. This will create and populate the new test file, allowing the user to write the test code.
    - Note: The user doesn't need to fill out every field, only the *Source Folder*, *Name* and *Superclass* fields. The rest should be handled by Eclipse.
- Upon completing the testing file, the user may then run the tests using the steps provided in the *Testing PreCrastinate* section.
