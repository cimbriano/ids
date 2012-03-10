CPATH = ../../bin:../../lib:../../lib/jpcap/jpcap.jar
BLD = .
SRC = .

JAVAOPTS = -classpath $(CPATH)
JAVAC = javac

JAVA = java

CLASSES = ParsingRules.class ReadingFromPcap.class

.PHONY: all clean

all: $(CLASSES)

clean:
	rm -fv $(CLASSES)

%.class: %.java
	$(JAVAC) $(JAVAOPTS) $<