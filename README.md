XmlLibraryComparator
====================

A Maven Java project to compare existing XML manipulation frameworks.


********** Download and setup Maven on your computer **********:
If you have never used Maven, please read this "Maven in 5 minutes" page to give you an introduction: http://maven.apache.org/guides/getting-started/maven-in-five-minutes.html 

1) Download Maven 3.0.5 binary from here and unzip it to a folder : http://mirror.catn.com/pub/apache/maven/maven-3/3.0.5/binaries/apache-maven-3.0.5-bin.zip 

you should see a top level apache-maven-3.0.5 folder extracted

2) create a folder called "repository" in the apache-maven-3.0.5 folder

3) open to edit the settings.xml file in apache-maven-3.0.5/conf/settings.xml

4) add this tag right underneath the first settings opening tag (around line number 55 of the file): <localRepository>ABSOLUTE_PATH_TO_EXTRACTED_FOLDER/apache-maven-3.0.5/repository</localRepository>
and save the file

********** Install the Maven plugin on Eclipse (Juno - Android ADT bundled version) **********:

5) Open Eclipse and go to Help -> Install New Software..

6) Click Add.. and fill in the popup as follows
Name: M2E
Location: http://download.eclipse.org/technology/m2e/releases
click OK

7) It will look at available plugins for download. Uncheck the pre-checked box below named "Show only the latest versions of available software". You will see one result entitled "Maven Integration For Eclipse". Expand that tree and check the option for "m2e - Maven Integration for Eclipse" choosing Version 1.4.1.20140328-1905 

8) Click Next -> Next -> accept the terms of the license agreement -> click Finish. This will download and install the plugin for you. You will need to restart Eclipse

********** Configure the Maven plugin in Eclipse to use your downloaded version of Maven **********:

9) In Eclipse go to Window -> Preferences -> Maven. Expand the tree under the Maven heading and select "Installations"

10) click Add.. and navigate to your apache-maven-3.0.5 folder and click OK. It will have recognized it and selected it to use.

11) Click the Apply button.

12) Select the "User Settings" preferences tree item under the Maven heading.

13) click the Browsee button in the User Settings text input and find your settings.xml file in apache-maven-3.0.5/conf/settings.xml

14) click the Update Settings button, then the Reindex button and then the Apply button. Click OK to close the dialog. 

********** Import the Maven project into Eclipse  **********:

Make sure you have cloned the git repository first before proceeding.

15) In Eclipse go to File -> Import.. -> Maven -> Existing Maven Projects -> Next -> Browse.. -> find the directory where you cloned the project to, selecting the top level "XmlLibraryComparator" directory -> click OK

16) It should automatically select the pom.xml file for the xml-comparator project after analyzing. Click Next and then Finish.

You now have an imported and configured ready to be used.

********** Configure an Eclipse Maven Run Configuration **********:

17) In Eclipse go to Run -> Run Configurations. In the Maven Build option at the bottom, right click on it and click New

18) name it as 'test' and then click Browse Workspace... and select the xml-comparator project.

19) In the "Goals:" text input write: test

20) click the JRE tab and make sure to select a JDK that is 1.7+.

21) click Apply and then Run. The console will output Maven statements, download dependency jars and then execute tests under the src/test/java folder.

22) Configure another new Maven Build configuration for the xml-comparator project but called it 'clean package' and then in the "Goals:" text input, write: clean package

23) Running this will compile the source code in src/main/java, run the tests and package the code in a jar file called "xml-comparator-1.0.0-SNAPSHOT.jar" in the target folder. 


