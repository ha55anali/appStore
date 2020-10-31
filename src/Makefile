JFLAGS = -g
JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	   test.java \
	   businessLayer/blFactory.java \
	   dbaseInterface/devInterface.java \
	   businessLayer/individualAppInterface.java \
	   businessLayer/App.java \
	   businessLayer/tests/dummyDB.java \
	   businessLayer/AppCollectionInterface.java \
	   businessLayer/blFactory.java \
	   blInterface/appInterface.java \
	   blInterface/userDetails.java \
	   blInterface/devInterface.java \
	   blInterface/App.java \
	   blInterface/userInterface.java \
	   dbaseInterface/appInterface.java \
	   dbaseInterface/userDetails.java \
	   dbaseInterface/devInterface.java \
	   dbaseInterface/appDetails.java \
	   dbaseInterface/dbFactory.java \
	   dbaseInterface/userInterface.java


		  
default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
	
run:
	java test
